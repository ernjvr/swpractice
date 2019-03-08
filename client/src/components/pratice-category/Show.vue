<template>
    <v-container fluid grid-list-xl>
        <v-layout row>
            <v-flex xs6>
                <panel title="Practice Category">
                    <v-layout>
                        <v-flex xs6>
                            <div class="title">
                                <div class="title">
                                    {{ category.name }}
                                </div>
                            </div>
                            <v-btn color="indigo" dark @click="navigateTo({
                                name: 'practice-category.edit',
                                params: {id: category._links.self.href}
                            })">Edit</v-btn>
                            <v-btn color="indigo" dark @click="deleteConfirmationVisibility=true">Delete</v-btn>
                            <v-btn color="indigo" dark @click="navigateTo({
                                name: 'practice-category.index'
                            })">Return</v-btn>
                        </v-flex>
                    </v-layout>
                </panel>
            </v-flex>
        </v-layout>
        <confirm-dialog :confirm-visibility="deleteConfirmationVisibility"
                        @confirmCancel="deleteConfirmationVisibility=false"
                        @confirmAccept="deleteCategory">
            <template slot="title">Confirm Delete</template>
            <template slot="text">Are you sure you want to delete?</template>
            <template slot="confirmButton">Delete</template>
        </confirm-dialog>
    </v-container>
</template>

<script>
    import api from '../../services/api';
    import Panel from "../Panel";
    import ConfirmDialog from '../ConfirmDialog'
    export default {
        components: {
            Panel,
            ConfirmDialog
        },
        data() {
            return {
                category: {},
                deleteConfirmationVisibility: false
            }
        },
        async mounted() {
            console.log(this.$route.params);
            api.get(this.$route.params.id)
                .then(response => {
                    this.category = response.data;
                    console.log(response.data);
                }).catch(e => {
                console.log(e);
            });
        },
        methods: {
            navigateTo(route) {
                this.$router.push(route);
            },
            async deleteCategory() {
                console.log("delete");
                api.delete(this.category._links.self.href)
                    .then(response => {
                        console.log(response);
                        // this.$store.dispatch('removePracticeCategory', response.data);
                        this.$router.push('/practice-category/');
                    }).catch(e => {
                    console.log(e);
                });
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