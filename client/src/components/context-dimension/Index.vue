<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-layout column>
        <v-flex xs8>
            <panel :title="$t('context_dimension')">
                <v-btn slot="action" class="primary accent-2" light medium absolute
                       right middle @click="navigateTo({
                                name: 'context-dimension.create'
                            })">
                    <v-icon color="white">add</v-icon>{{ $t('add')}}
                </v-btn>
                <v-card-title>
                    <v-spacer></v-spacer>
                    <v-text-field v-model="search" append-icon="search" :label="$t('search')" single-line hide-details></v-text-field>
                </v-card-title>
                <v-data-table :headers="headers" :items="dimensions" item-key="name" :pagination.sync="pagination"
                              :search="search" class="elevation-1" :loading="loading">
                    <template slot="headers" slot-scope="props">
                        <tr>
                            <th v-for="header in props.headers" :key="header.text"
                                :class="['black--text text-md-left body-2 font-weight-black column sortable',
                                pagination.descending ? 'desc' : 'asc', header.value === pagination.sortBy ? 'active' : '']"
                                @click="changeSort(header.value)">
                                <v-icon small>arrow_upward</v-icon>
                                {{ header.text }}
                            </th>
                        </tr>
                    </template>
                    <template v-slot:items="props">
                        <td>{{ props.item.name }}</td>
                        <td>
                            <v-btn color="indigo" dark @click="navigateTo({
                                name: 'context-dimension.show',
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
    import constants from '../../common/constants';
    import util from '../../common/util';

    export default {
        components: {
            Panel
        },
        data() {
            return {
                dimensions: [],
                headers: constants.context_dimension_headers,
                pagination: util.pagination,
                search: '',
                loading: true
            }
        },
        async mounted() {
            this.$store.dispatch('getAllContextDimensions').then(response => {
                console.log('received data from store getAllContextDimensions: ' + response);
                this.dimensions = response;
                this.loading = false;
            }, error => {
                console.log('received error from store getAllContextDimensions: ' + error);
            });
        },
        methods: {
            changeSort: util.changeSort,
            navigateTo(route) {
                this.$router.push(route);
            }
        }
    }
</script>

<style scoped>

</style>