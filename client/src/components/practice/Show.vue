<template>
    <v-container fluid grid-list-xl>
        <v-layout row>
            <v-flex xs6>
                <panel title="Practice">
                    <v-card-text>
                        <v-form ref="form">
                            <v-text-field v-model="practice.name" readonly prepend-icon="person" name="name"
                                          label="Name" type="text"></v-text-field>
                            <v-text-field v-model="practice.description" readonly prepend-icon="person" name="description"
                                          label="Description" type="text"></v-text-field>
                            <v-text-field v-model="practice.practiceCategory.name" readonly prepend-icon="person" name="practiceCategory"
                                          label="Practice Category" type="text"></v-text-field>
                        </v-form>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="indigo" dark @click="navigateTo({name: 'practice.edit'})">Edit</v-btn>
                        <v-btn color="indigo" dark @click="deleteConfirmationVisibility=true">Delete</v-btn>
                        <v-btn color="indigo" dark @click="navigateTo({name: 'practice.index'})">Return</v-btn>
                    </v-card-actions>
                </panel>
            </v-flex>
        </v-layout>
        <confirm-dialog :confirm-visibility="deleteConfirmationVisibility"
                        @confirmCancel="deleteConfirmationVisibility=false"
                        @confirmAccept="deletePractice">
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
                practice: {
                    name: '',
                    description: '',
                    _links: '',
                    practiceCategory: ''
                },
                deleteConfirmationVisibility: false
            }
        },
    mounted() {
        console.log('mounted here');
        let practice = this.$store.state.practice;
        if(practice) {
            this.practice = practice;
        } else {
            console.log('selected practice not found');
            this.navigateTo('/practice/');
        }
        console.log('mounted done');
        },
        methods: {
            navigateTo(route) {
                this.$router.push(route);
            },
            async deletePractice() {
                let href = this.practice._links.self.href;
                api.delete(href)
                    .then(response => {
                        console.log(href + ' delete success: ' + response);
                        this.navigateTo('/practice/');
                    }).catch(e => {
                    console.log(href + ' delete error: ' + e);
                });
            }
        }
    }
</script>

<style scoped>

</style>