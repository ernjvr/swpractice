<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-layout column>
        <v-dialog v-model="displayDialog" persistent max-width="600px">
            <create></create>
        </v-dialog>
        <v-flex md6,xs6>
            <panel :title="$t('practice_category')">
                <v-btn slot="action" class="primary accent-2" light medium absolute
                       right middle @click="showDialog(true)">
                    <v-icon color="white">add</v-icon>{{ $t('add')}}
                </v-btn>
                <v-card-title>
                    <v-spacer></v-spacer>
                    <v-text-field v-model="search" append-icon="search" :label="$t('search')" single-line hide-details autofocus></v-text-field>
                </v-card-title>
                <v-data-table :headers="headers" :items="categories" item-key="name" :pagination.sync="pagination"
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
                            <v-btn color="indigo" dark @click="navigateToView({
                                name: 'practice-category.show',
                                params: {id: props.item.name}
                            })">{{ $t('view')}}</v-btn>
                        </td>
                    </template>
                </v-data-table>
                <div class="text-xs-center pt-2">
                    <v-pagination v-model="pagination.page" :length="pages"></v-pagination>
                </div>
            </panel>
        </v-flex>
    </v-layout>
</template>

<script>
    import Panel from "@/components/Panel";
    import constants from '@/common/constants';
    import util from '@/common/util';
    import Create from "./Create";

    export default {
        components: {
            Panel,
            Create
        },
        data() {
            return {
                categories: [],
                headers: constants.practice_category_headers,
                pagination: util.pagination,
                search: '',
                loading: true
            }
        },
        computed: {
            pages () {
                return util.pages(this.pagination);
            },
            displayDialog() {
                return this.$store.state.displayDialog;
            }
        },
        mounted() {
            this.$store.dispatch('getAllPracticeCategories').then(response => {
                console.log('received data from store getAllPracticeCategories: ' + response);
                this.categories = response;
                this.pagination.totalItems = response.length;
                this.loading = false;
            }, error => {
                console.log('received error from store getAllPracticeCategories: ' + error);
            });
        },
        methods: {
            changeSort: util.changeSort,
            navigateTo: util.navigateTo,
            showDialog: util.showDialog,
            navigateToView(route) {
                let category = this.categories.find(cat => { return cat.name === route.params.id});
                this.$store.dispatch('setSelectedPracticeCategory', category).then(_ => {
                    this.navigateTo(route);
                } , error => {
                    console.log('setSelectedPracticeCategory error: ' + error);
                });
            },
        }
    }
</script>

<style scoped>

</style>