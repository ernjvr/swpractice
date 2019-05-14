<template>
    <v-container fluid grid-list-xl>
        <v-layout row>
            <v-flex xs6>
                <panel :title="$t('reference')">
                    <v-card-text>
                        <v-form ref="form">
                            <v-text-field v-model="reference.author" readonly prepend-icon="person" name="author"
                                          :label="$t('author')" type="text"></v-text-field>
                            <v-text-field v-model="reference.year" readonly prepend-icon="person" name="year"
                                          :label="$t('year')" type="year"></v-text-field>
                            <v-textarea v-model="reference.reference" readonly prepend-icon="person" name="reference"
                                          :label="$t('reference')" type="text"></v-textarea>
                            <v-text-field v-model="reference.referenceType.name" readonly prepend-icon="person" name="referenceType"
                                          :label="$t('reference_type')" type="text"></v-text-field>
                        </v-form>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="indigo" dark @click="navigateTo({name: 'reference.edit'})">{{ $t('edit')}}</v-btn>
                        <v-btn color="indigo" dark @click="deleteConfirmationVisibility=true">{{ $t('delete')}}</v-btn>
                        <v-btn color="indigo" dark @click="navigateTo({name: 'reference.index'})">{{ $t('return')}}</v-btn>
                    </v-card-actions>
                </panel>
            </v-flex>
        </v-layout>
        <confirm-dialog :confirm-visibility="deleteConfirmationVisibility"
                        @confirmCancel="deleteConfirmationVisibility=false"
                        @confirmAccept="deleteReference">
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
                reference: {
                    author: '',
                    year: '',
                    reference: '',
                    _links: '',
                    referenceType: ''
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
            let reference = this.$store.state.selectedReference;

            if(reference._links.self.href) {
                this.reference = reference;
            } else {
                console.log('selected reference not found');
                this.navigateTo('/reference/');
            }
        },
        methods: {
            navigateTo(route) {
                this.$router.push(route);
            },
            async deleteReference() {
                let href = this.reference._links.self.href;
                api.delete(href)
                    .then(response => {
                        console.log(href + ' delete success: ' + response);
                        this.navigateTo('/reference/');
                    }).catch(e => {
                    console.log(href + ' delete error: ' + e);

                    if (e.response.data.toLowerCase().includes('integrity violation')) {
                        this.infoDialog.title = this.$t('error_delete_title');
                        this.infoDialog.text = this.$t('error_delete_reference_text');
                        this.infoDialog.detail = this.$t('error_delete_reference_detail');
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