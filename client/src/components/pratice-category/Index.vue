<template>
    <v-layout column>
        <v-flex xs8>
            <!--<panel title="Search for Practice Categories">-->
                <!--<v-text-field label="Practice Category"></v-text-field>-->
            <!--</panel>-->
            <panel title="Practice Category">
                <v-btn slot="action" class="indigo accent-2" light medium absolute
                       right middle fab @click="navigateTo({
                                name: 'practice-category.create'
                            })">
                    <v-icon>add</v-icon>
                </v-btn>
                <div v-for="category in categories" :key="category._links.self.href">
                    <v-layout>
                        <v-flex xs6>
                            <div class="title">
                                {{ category.name }}
                            </div>
                            <!--<div class="title">-->
                                <!--{{ category._links.self.href }}-->
                            <!--</div>-->
                            <v-btn color="indigo" dark @click="navigateTo({
                                name: 'practice-category.show',
                                params: {id: category._links.self.href}
                            })">View
                            </v-btn>
                        </v-flex>
                    </v-layout>
                </div>
            </panel>
        </v-flex>
    </v-layout>
</template>

<script>
    import Panel from "../Panel";

    export default {
        components: {
            Panel
        },
        data() {
            return {
                categories: []
            }
        },
        async mounted() {
            this.$store.dispatch('getAllPracticeCategories').then(response => {
                console.log('received data');
                this.categories = response;
            }, error => {
                console.log('no data on server: error: ' + error);
            });
            this.categories = this.$store.state.practiceCategories;
        },
        methods: {
            navigateTo(route) {
                this.$router.push(route);
            }
        }
    }
</script>

<style scoped>
    .title {
        padding: 10px;
        overflow: hidden;
        text-align: left;
    }
</style>