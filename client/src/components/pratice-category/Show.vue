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
    import Panel from "@/components/Panel";
    import ConfirmDialog from '@/components/ConfirmDialog'
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
            let href = this.$route.params.id;
            api.get(href)
                .then(response => {
                    console.log(href + ' get success: ' + response.data);
                    this.category = response.data;
                }).catch(e => {
                console.log(href + ' get error: ' + e);
            });
        },
        methods: {
            navigateTo(route) {
                this.$router.push(route);
            },
            async deleteCategory() {
                let href = this.category._links.self.href;
                api.delete(href)
                    .then(response => {
                        console.log(href + ' delete success: ' + response);
                        this.navigateTo('/practice-category/');
                    }).catch(e => {
                    console.log(href + ' delete error: ' + e);
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