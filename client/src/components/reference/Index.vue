<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-layout column>
        <v-flex xs8>
            <panel :title="$t('reference')">
                <v-btn slot="action" class="indigo accent-2" light medium absolute
                       right middle fab @click="navigateTo({name: 'reference.create'})">
                    <v-icon>add</v-icon>
                </v-btn>
                <v-card-title>
                    <v-spacer></v-spacer>
                    <v-text-field v-model="search" append-icon="search" :label="$t('search')" single-line hide-details></v-text-field>
                </v-card-title>
                <v-data-table :headers="headers" :items="references" item-key="reference" :pagination.sync="pagination"
                              :search="search" class="elevation-1">
                    <template slot="headers" slot-scope="props">
                        <tr>
                            <th v-for="header in props.headers" :key="header.text"
                                :class="['column sortable', pagination.descending ? 'desc' : 'asc', header.value === pagination.sortBy ? 'active' : '']"
                                @click="changeSort(header.value)">
                                <v-icon small>arrow_upward</v-icon>
                                {{ header.text }}
                            </th>
                        </tr>
                    </template>
                    <template v-slot:items="props">
                        <td>{{ props.item.author }}</td>
                        <td class="text-xs-right">{{ props.item.year }}</td>
                        <td>{{ props.item.reference }}</td>
                        <td>{{ props.item.referenceType.name }}</td>
                        <td>
                            <v-btn color="indigo" dark @click="navigateToView({
                                name: 'reference.show',
                                params: {id: props.item._links.self.href}
                            })">{{ $t('view')}}</v-btn>
                        </td>
                    </template>
                </v-data-table>
            </panel>
        </v-flex>
    </v-layout>
</template>

<script>
    import Panel from "@/components/Panel";
    import api from '../../services/api'
    import axios from 'axios';
    import constants from '../../common/constants';
    import util from '../../common/util';

    export default {
        components: {
            Panel
        },
        data() {
            return {
                references: [],
                headers: constants.reference_headers,
                pagination: util.pagination,
                search: ''
            }
        },
        async mounted() {
            await api.get(constants.reference_url)
                .then(response => {
                    this.references = response.data._embedded.references.map(reference => ({
                        author: reference.author,
                        year: reference.year === 0 ? 'n.d': reference.year,
                        reference: reference.reference,
                        _links: reference._links,
                        referenceType: ''
                    }));
                }, error => {
                    console.log('reference get error: ' + error);
                })
                .then(response => {
                    console.log(response);
                });
            this.resolveReferenceTypeForEachReference();
            this.initReferenceTypes();
        },
        methods: {
            changeSort: util.changeSort,
            navigateTo(route) {
                this.$router.push(route);
            },
            navigateToView(route) {
                let reference = this.$store.state.references.find(reference => { return reference._links.self.href === route.params.id});
                this.$store.dispatch('setSelectedReference', reference).then(_ => {
                    this.$router.push(route);
                    } , error => {
                    console.log('setSelectedReference error: ' + error);
                });
            },
            async resolveReferenceTypeForEachReference() {
                let referenceTypes = [];

                for(let i = 0; i < this.references.length; i++) {
                    let reference = this.references[i];
                    referenceTypes.push(api.get(reference._links.referenceType.href));
                }

                let result = await axios.all(referenceTypes);

                for(let i = 0; i < result.length; i++) {
                    let res = result[i];
                    let reference = this.references[i];
                    reference['referenceType'] = {
                        name: res.data.name,
                        _links: res.data._links
                    };
                    this.references[i] = reference;
                }
                this.$store.commit('addReferences', this.references);
            },
            async initReferenceTypes() {
                this.$store.dispatch('getAllReferenceTypes').then(response => {
                    console.log('received data from store getAllReferenceTypes: ' + response);
                    console.log(response);
                }, error => {
                    console.log('received error from store getAllReferenceTypes: ' + error);
                });
            }
        },
    }
</script>

<style scoped>

</style>