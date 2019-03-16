<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-layout column>
        <v-flex xs8>
            <panel :title="$t('practice_category')">
                <v-btn slot="action" class="indigo accent-2" light medium absolute
                       right middle fab @click="navigateTo({
                                name: 'practice-category.create'
                            })">
                    <v-icon>add</v-icon>
                </v-btn>
                <v-data-table :headers="headers" :items="categories" class="elevation-1">
                    <template v-slot:items="props">
                        <td>{{ props.item.name }}</td>
                        <td>
                            <v-btn color="indigo" dark @click="navigateTo({
                                name: 'practice-category.show',
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

    export default {
        components: {
            Panel
        },
        data() {
            return {
                categories: [],
                headers: constants.practice_category_headers
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

</style>