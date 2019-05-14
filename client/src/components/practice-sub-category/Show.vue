<template>
    <v-container fluid grid-list-xl>
        <v-layout row>
            <v-flex xs6>
                <panel :title="$t('practice_sub_category')">
                    <v-card-text>
                        <v-form ref="form">
                            <v-text-field v-model="practiceSubCategory.name" readonly prepend-icon="person" name="name"
                                          :label="$t('name')" type="text"></v-text-field>
                            <v-textarea v-model="practiceSubCategory.description" readonly prepend-icon="person" name="description"
                                          :label="$t('description')" type="text"></v-textarea>
                            <v-text-field v-model="practiceSubCategory.practiceCategory.name" readonly prepend-icon="person" name="practiceCategory"
                                          :label="$t('practice_category')" type="text"></v-text-field>
                        </v-form>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="indigo" dark @click="navigateTo({name: 'practice-sub-category.edit'})">{{ $t('edit')}}</v-btn>
                        <v-btn color="indigo" dark @click="deleteConfirmationVisibility=true">{{ $t('delete')}}</v-btn>
                        <v-btn color="indigo" dark @click="navigateTo({name: 'practice-sub-category.index'})">{{ $t('return')}}</v-btn>
                    </v-card-actions>
                </panel>
            </v-flex>
        </v-layout>
        <confirm-dialog :confirm-visibility="deleteConfirmationVisibility"
                        @confirmCancel="deleteConfirmationVisibility=false"
                        @confirmAccept="deletePracticeSubCategory">
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
    import ConfirmDialog from '@/components/ConfirmDialog';
    import InfoDialog from '@/components/InformationDialog';

    export default {
        components: {
            Panel,
            ConfirmDialog,
            InfoDialog
        },
        data() {
            return {
                practiceSubCategory: {
                    name: '',
                    description: '',
                    _links: '',
                    practiceCategory: ''
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
            let practiceSubCategory = this.$store.state.selectedPracticeSubCategory;

            if(practiceSubCategory.name) {
                this.practiceSubCategory = practiceSubCategory;
            } else {
                console.log('selected practice sub category not found');
                this.navigateTo('/practice-sub-category/');
            }
        },
        methods: {
            navigateTo(route) {
                this.$router.push(route);
            },
            async deletePracticeSubCategory() {
                let href = this.practiceSubCategory._links.self.href;
                api.delete(href)
                    .then(response => {
                        console.log(href + ' delete success: ' + response);
                        this.navigateTo('/practice-sub-category/');
                    }).catch(e => {
                    console.log(href + ' delete error: ' + e);

                    if (e.response.data.toLowerCase().includes('integrity violation')) {
                        this.infoDialog.title = this.$t('error_delete_title');
                        this.infoDialog.text = this.$t('error_delete_practice_sub_category_text');
                        this.infoDialog.detail = this.$t('error_delete_practice_sub_category_detail');
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