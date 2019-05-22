<template>
    <v-container>
        <v-layout>
            <v-flex xs12>
                <panel :title="$t('reference_type')">
                    <v-card-text>
                        <v-form ref="form">
                            <v-text-field v-model="type.name" readonly prepend-icon="person" name="name"
                                          :label="$t('name')" type="text"></v-text-field>
                        </v-form>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="indigo" dark @click="navigateTo({name: 'reference-type.index'})">
                            <v-icon dark left>arrow_back</v-icon>{{ $t('return')}}
                        </v-btn>
                        <v-btn color="indigo" dark @click="navigateTo({name: 'reference-type.edit'})">
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
                        @confirmAccept="deleteType">
            <template slot="title">{{ $t('confirm_delete_title')}}</template>
            <template slot="text">{{ $t('confirm_delete_text')}}</template>
            <template slot="confirmButton">{{ $t('delete')}}</template>
        </confirm-dialog>
        <info-dialog :info-visibility="infoDialog.infoVisibility"
                     :info-type="infoDialog.infoType"
                     @infoAccept="accept({name: 'reference-type.index'})">
            <template slot="title">{{ infoDialog.title }}</template>
            <template slot="text">{{ infoDialog.text }}</template>
            <template slot="detail">{{ infoDialog.detail }}</template>
            <template slot="confirmButton">{{ $t('close')}}</template>
        </info-dialog>
    </v-container>
</template>

<script>
    import api from '@/services/api';
    import util from '@/common/util';
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
                type: {},
                infoDialog: util.infoDialog,
                navigateToIndexPage: false,
                deleteConfirmationVisibility: false
            }
        },
        mounted() {
            let reference = this.$store.state.selectedReferenceType;

            if(reference.name) {
                this.type = reference;
            } else {
                console.log('selected reference type not found');
                this.navigateTo({name: 'reference-type.index'});
            }
        },
        methods: {
            navigateTo: util.navigateTo,
            accept: util.acceptInfoDialog,
            displayDeleteError: util.displayDeleteError,
            async deleteType() {
                let href = this.type._links.self.href;
                api.delete(href)
                    .then(response => {
                        console.log(href + ' delete success: ' + response);
                        this.navigateTo({name: 'reference-type.index'});
                    }).catch(e => {
                    console.log(href + ' delete error: ' + e.response);
                    this.displayDeleteError(e, 'error_delete_reference_type_text', 'error_delete_reference_type_detail',
                        'error_not_found_reference_type_text', 'error_not_found_reference_type_detail');
                    this.deleteConfirmationVisibility = false;
                });
            }
        }
    }
</script>

<style scoped>

</style>