<template>
    <v-layout column>
        <v-flex xs8>
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
    import Panel from "@/components/Panel";

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
                console.log('received data from store getAllPracticeCategories: ' + response);
                this.categories = response;
            }, error => {
                console.log('received error from store getAllPracticeCategories: ' + error);
            });
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