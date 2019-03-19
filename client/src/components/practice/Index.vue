<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-layout column>
        <v-flex xs8>
            <panel :title="$t('practice')">
                <v-btn slot="action" class="indigo accent-2" light medium absolute
                       right middle fab @click="navigateTo({
                                name: 'practice.create'
                            })">
                    <v-icon>add</v-icon>
                </v-btn>
                <v-data-table :headers="headers" :items="practices" class="elevation-1">
                    <template v-slot:items="props">
                        <td>{{ props.item.name }}</td>
                        <td>{{ props.item.description }}</td>
                        <td>{{ props.item.practiceCategory.name }}</td>
                        <td>{{ props.item.practiceSubCategory.name }}</td>
                        <td>
                            <v-btn color="indigo" dark @click="navigateToView({
                                name: 'practice.show',
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

    export default {
        components: {
            Panel
        },
        data() {
            return {
                practices: [],
                headers: constants.practice_headers
            }
        },
        async mounted() {
            await api.get('/practice')
                .then(response => {
                    this.practices = response.data._embedded.practices.map(practice => ({
                        name: practice.name,
                        description: practice.description,
                        _links: practice._links,
                        practiceSubCategory: '',
                        practiceCategory: '',
                    }));
                }, error => {
                    console.log('practice get error: ' + error);
                })
                .then(response => {
                    console.log(response);
                });
            this.resolveCategoryForEachPractice();
            this.initPracticeCategories();
            this.initPracticeSubCategories();
        },
        methods: {
            navigateTo(route) {
                this.$router.push(route);
            },
            navigateToView(route) {
                let practice = this.$store.state.practices.find(practice => { return practice.name === route.params.id});
                this.$store.dispatch('setSelectedPractice', practice).then(_ => {
                    this.$router.push(route);
                }, error => {
                    console.log('setSelectedPractice error: ' + error);
                });
            },
            async resolveCategoryForEachPractice() {
                let practiceSubCategories = [];
                let practiceCategories = [];

                for(let i = 0; i < this.practices.length; i++) {
                    let practice = this.practices[i];
                    practiceSubCategories.push(api.get(practice._links.practiceSubCategory.href));
                }
                let subCatResult = await axios.all(practiceSubCategories);

                for(let i = 0; i < subCatResult.length; i++) {
                    let res = subCatResult[i];
                    practiceCategories.push(api.get(res.data._links.practiceCategory.href));
                }
                let catResult = await axios.all(practiceCategories);

                for(let i = 0; i < subCatResult.length; i++) {
                    let subCatRes = subCatResult[i];
                    let catRes = catResult[i];
                    let practice = this.practices[i];
                    let practiceSubCategory = {
                        name: subCatRes.data.name,
                        _links: subCatRes.data._links
                    };
                    let practiceCategory = {
                        name: catRes.data.name,
                        _links: catRes.data._links
                    };
                    practice['practiceSubCategory'] = practiceSubCategory;
                    practice['practiceCategory'] = practiceCategory;
                    this.practices[i] = practice;
                }
                this.$store.commit('addPractices', this.practices);
            },
            async initPracticeCategories() {
                this.$store.dispatch('getAllPracticeCategories').then(response => {
                    console.log('received data from store getAllPracticeCategories: ' + response);
                    console.log(response);
                }, error => {
                    console.log('received error from store getAllPracticeCategories: ' + error);
                });
            },
            async initPracticeSubCategories() {
                this.$store.dispatch('getAllPracticeSubCategories').then(response => {
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