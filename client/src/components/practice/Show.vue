<template>
    <v-container fluid grid-list-xl>
        <v-layout row>
            <v-flex xs6>
                <panel :title="$t('practice')">
                    <v-card-text>
                        <v-form ref="form">
                            <v-text-field v-model="practice.name" readonly prepend-icon="person" name="name"
                                          :label="$t('name')" type="text"></v-text-field>
                            <v-text-field v-model="practice.description" readonly prepend-icon="person" name="description"
                                          :label="$t('description')" type="text"></v-text-field>
                            <v-text-field v-model="practice.practiceCategory.name" readonly prepend-icon="person" name="practiceCategory"
                                          :label="$t('practice_category')" type="text"></v-text-field>
                            <v-text-field v-model="practice.practiceSubCategory.name" readonly prepend-icon="person" name="practiceCategory"
                                          :label="$t('practice_sub_category')" type="text"></v-text-field>
                        </v-form>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="indigo" dark @click="navigateTo({name: 'practice.edit'})">{{ $t('edit')}}</v-btn>
                        <v-btn color="indigo" dark @click="deleteConfirmationVisibility=true">{{ $t('delete')}}</v-btn>
                        <v-btn color="indigo" dark @click="navigateTo({name: 'practice.index'})">{{ $t('return')}}</v-btn>
                    </v-card-actions>
                </panel>
            </v-flex>
        </v-layout>
        <confirm-dialog :confirm-visibility="deleteConfirmationVisibility"
                        @confirmCancel="deleteConfirmationVisibility=false"
                        @confirmAccept="deletePractice">
            <template slot="title">{{ $t('confirm_delete_title')}}</template>
            <template slot="text">{{ $t('confirm_delete_text')}}</template>
            <template slot="confirmButton">{{ $t('delete')}}</template>
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
                    practiceCategory: '',
                    practiceSubCategory: ''
                },
                deleteConfirmationVisibility: false
            }
        },
    mounted() {
            let practice = this.$store.state.selectedPractice;

            if(practice.name) {
                this.practice = practice;
            } else {
                console.log('selected practice not found');
                this.navigateTo('/practice/');
            }
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