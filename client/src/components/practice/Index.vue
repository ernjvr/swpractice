<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-layout column>
        <v-flex xs8>
            <panel title="Practice">
                <v-btn slot="action" class="indigo accent-2" light medium absolute
                       right middle fab @click="navigateTo({
                                name: 'practice.create'
                            })">
                    <v-icon>add</v-icon>
                </v-btn>
                <!--<v-dialog v-model="dialog" max-width="500px">-->
                    <!--<template  v-slot:activator="{ on }">-->
                        <!--<v-btn slot="action"  class="indigo accent-2" light medium absolute-->
                               <!--right middle fab v-on="on">-->
                            <!--<v-icon>add</v-icon>-->
                        <!--</v-btn>-->
                    <!--</template>-->
                <!--</v-dialog>-->
                <!--<div v-for="practice in practices" :key="practice._links.self.href">-->
                    <!--<v-layout>-->
                        <!--<v-flex xs6>-->
                            <!--<div class="title">-->
                                <!--{{ practice.name }}-->
                            <!--</div>-->
                            <!--<v-btn color="indigo" dark @click="navigateTo({-->
                                <!--name: 'practice.show',-->
                                <!--params: {id: practice._links.self.href}-->
                            <!--})">View-->
                            <!--</v-btn>-->
                        <!--</v-flex>-->
                    <!--</v-layout>-->
                <!--</div>-->
                <v-data-table :headers="headers" :items="practices" class="elevation-1">
                    <template v-slot:items="props">
                        <td>{{ props.item.name }}</td>
                        <td>{{ props.item.description }}</td>
                        <td>{{ props.item.practiceCategory.name }}</td>
                        <td>
                            <v-btn color="indigo" dark @click="navigateToView({
                                name: 'practice.show',
                                params: {id: props.item.name}
                            })">View</v-btn>
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
                        practiceCategory: ''
                    }));
                }, error => {
                    console.log('practice get error: ' + error);
                })
                .then(response => {
                    console.log(response);
                });
            this.resolvePracticeCategoryForEachPractice();
            this.initPracticeCategories();
        },
        methods: {
            navigateTo(route) {
                this.$router.push(route);
            },
            navigateToView(route) {
                console.log('navigateToView');
                let practice = this.$store.state.practices.find(practice => { return practice.name === route.params.id});
                console.log('practice');
                console.log(practice);
                this.$store.commit('setSelectedPractice', practice);
                this.$router.push(route);
            },
            async resolvePracticeCategoryForEachPractice() {
                let practiceCategories = [];
                for(let i = 0; i < this.practices.length; i++) {
                    let practice = this.practices[i];
                    practiceCategories.push(api.get(practice._links.practiceCategory.href));
                }

                let result = await axios.all(practiceCategories);
                console.log('get practice category for each practice result: ');
                console.log(result);
                for(let i = 0; i < result.length; i++) {
                    let res = result[i];
                    let practice = this.practices[i];
                    let practiceCategory = {
                        name: res.data.name,
                        _links: res.data._links
                    };
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
            }
        },
    }
</script>

<style scoped>

</style>