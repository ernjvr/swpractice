<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-layout column>
        <v-flex xs8>
            <panel :title="$t('practice_sub_category')">
                <v-btn slot="action" class="primary accent-2" light medium absolute
                       right middle @click="navigateTo({
                                name: 'practice-sub-category.create'
                            })">
                    <v-icon color="white">add</v-icon>{{ $t('add')}}
                </v-btn>
                <v-card-title>
                    <v-spacer></v-spacer>
                    <v-text-field v-model="search" append-icon="search" :label="$t('search')" single-line hide-details></v-text-field>
                </v-card-title>
                <v-data-table :headers="headers" :items="practiceSubCategories" item-key="name" :pagination.sync="pagination"
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
                        <td>{{ props.item.description }}</td>
                        <td>{{ props.item.practiceCategory.name }}</td>
                        <td>
                            <v-btn color="indigo" dark @click="navigateToView({
                                name: 'practice-sub-category.show',
                                params: {id: props.item.name}
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
                practiceSubCategories: [],
                headers: constants.practice_sub_category_headers,
                pagination: util.pagination,
                search: '',
                loading: true
            }
        },
        async mounted() {
            await api.get(constants.practice_sub_category_url)
                .then(response => {
                    this.practiceSubCategories = response.data._embedded.practiceSubCategories.map(subCategory => ({
                        name: subCategory.name,
                        description: subCategory.description,
                        _links: subCategory._links,
                        practiceCategory: ''
                    }));
                    this.loading = false;
                }, error => {
                    console.log('practice-sub-category get error: ' + error);
                })
                .then(response => {
                    console.log(response);
                });
            this.resolvePracticeCategoryForEachPracticeSubCategory();
            this.initPracticeCategories();
        },
        methods: {
            changeSort: util.changeSort,
            navigateTo(route) {
                this.$router.push(route);
            },
            navigateToView(route) {
                let subCategory = this.$store.state.practiceSubCategories.find(subCategory => { return subCategory.name === route.params.id});
                this.$store.dispatch('setSelectedPracticeSubCategory', subCategory).then(_ => {
                    this.$router.push(route);
                    } , error => {
                    console.log('setSelectedSubCategory error: ' + error);
                });
            },
            async resolvePracticeCategoryForEachPracticeSubCategory() {
                let practiceCategories = [];
                for(let i = 0; i < this.practiceSubCategories.length; i++) {
                    let subCategory = this.practiceSubCategories[i];
                    practiceCategories.push(api.get(subCategory._links.practiceCategory.href));
                }

                let result = await axios.all(practiceCategories);
                console.log('get practice category for each practice sub category result: ');
                console.log(result);
                for(let i = 0; i < result.length; i++) {
                    let res = result[i];
                    let subCategory = this.practiceSubCategories[i];
                    subCategory['practiceCategory'] = {
                        name: res.data.name,
                        _links: res.data._links
                    };
                    this.practiceSubCategories[i] = subCategory;
                }
                this.$store.commit('addPracticeSubCategories', this.practiceSubCategories);
            },
            async initPracticeCategories() {
                this.$store.dispatch('getAllPracticeCategories').then(response => {
                    console.log('received data from store getAllPracticeCategories: ' + response);
                    console.log(response);
                }, error => {
                    console.log('received error from store getAllPracticeCategories: ' + error);
                });
            }
        },
    }
</script>

<style scoped>

</style>