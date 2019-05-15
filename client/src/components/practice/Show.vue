<template>
    <v-container fluid grid-list-xl>
        <v-layout row>
            <v-flex xs6>
                <panel :title="$t('practice')">
                    <v-card-text>
                        <v-form ref="form">
                            <v-text-field v-model="practice.name" readonly prepend-icon="person" name="name"
                                          :label="$t('name')" type="text"></v-text-field>
                            <v-textarea v-model="practice.description" readonly prepend-icon="person" name="description"
                                          :label="$t('description')" type="text"></v-textarea>
                            <v-text-field v-model="practice.practiceCategory.name" readonly prepend-icon="person" name="practiceCategory"
                                          :label="$t('practice_category')" type="text"></v-text-field>
                            <v-text-field v-model="practice.practiceSubCategory.name" readonly prepend-icon="person" name="practiceCategory"
                                          :label="$t('practice_sub_category')" type="text"></v-text-field>
                            <v-textarea v-model="practice.reference.reference" readonly prepend-icon="person" name="reference"
                                          :label="$t('reference')" type="text"></v-textarea>
                        </v-form>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="indigo" dark @click="navigateTo({name: 'practice.index'})">
                            <v-icon dark left>arrow_back</v-icon>{{ $t('return')}}
                        </v-btn>
                        <v-btn color="indigo" dark @click="navigateTo({name: 'practice.edit'})">
                            <v-icon dark left>edit</v-icon>{{ $t('edit')}}
                        </v-btn>
                        <v-btn color="indigo" dark @click="deleteConfirmationVisibility=true">
                            <v-icon dark left>delete</v-icon>{{ $t('delete')}}
                        </v-btn>
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
        <info-dialog :info-visibility="infoDialog.infoVisibility"
                     :info-type="infoDialog.infoType"
                     @infoAccept="infoDialog.infoVisibility=false">
            <template slot="title">{{ infoDialog.title }}</template>
            <template slot="text">{{ infoDialog.text }}</template>
            <template slot="detail">{{ infoDialog.detail }}</template>
            <template slot="confirmButton">{{ $t('close')}}</template>
        </info-dialog>
    </v-container>
</template>

<script>
    import api from '../../services/api';
    import Panel from "@/components/Panel";
    import ConfirmDialog from '@/components/dialog/ConfirmDialog';
    import InfoDialog from '@/components/dialog/InformationDialog';

    export default {
        components: {
            Panel,
            ConfirmDialog,
            InfoDialog
        },
        data() {
            return {
                practice: {
                    name: '',
                    description: '',
                    _links: '',
                    practiceCategory: '',
                    practiceSubCategory: '',
                    reference: '',
                },
                infoDialog: {
                    title: '',
                    text: '',
                    detail: '',
                    infoType: '',
                    infoVisibility: false,
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

                    if (e.response.data.toLowerCase().includes('integrity violation')) {
                        this.infoDialog.title = this.$t('error_delete_title');
                        this.infoDialog.text = this.$t('error_delete_practice_text');
                        this.infoDialog.detail = this.$t('error_delete_practice_detail');
                    } else {
                        this.infoDialog.title = this.$t('error_delete_title');
                        this.infoDialog.text = this.$t('error_unknown_text');
                        this.infoDialog.detail = this.$t('error_unknown_detail');
                    }
                    this.deleteConfirmationVisibility = false;
                    this.infoDialog.infoType = 'error';
                    this.infoDialog.infoVisibility = true;
                });
            }
        }
    }
</script>

<style scoped>

</style>