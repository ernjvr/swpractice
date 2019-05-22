<template>
    <v-container>
        <v-layout>
            <v-flex xs12>
                <panel :title="$t('context_value')">
                    <v-card-text>
                        <v-form ref="form">
                            <v-text-field v-model="value.value" readonly prepend-icon="person" name="value"
                                          :label="$t('value')" type="text"></v-text-field>
                            <v-textarea v-model="value.description" readonly prepend-icon="person" name="description"
                                        :label="$t('description')" type="text"></v-textarea>
                        </v-form>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="indigo" dark @click="navigateTo({name: 'context-value.index'})">
                            <v-icon dark left>arrow_back</v-icon>{{ $t('return')}}
                        </v-btn>
                        <v-btn color="indigo" dark @click="navigateTo({
                        name: 'context-value.edit',
                        params: {id: value._links.self.href}
                        })"><v-icon dark left>edit</v-icon>{{ $t('edit')}}</v-btn>
                        <v-btn color="indigo" dark @click="deleteConfirmationVisibility=true">
                            <v-icon dark left>delete</v-icon>{{ $t('delete')}}
                        </v-btn>
                    </v-card-actions>
                </panel>
            </v-flex>
        </v-layout>
        <confirm-dialog :confirm-visibility="deleteConfirmationVisibility"
                        @confirmCancel="deleteConfirmationVisibility=false"
                        @confirmAccept="deleteValue">
            <template slot="title">{{ $t('confirm_delete_title')}}</template>
            <template slot="text">{{ $t('confirm_delete_text')}}</template>
            <template slot="confirmButton">{{ $t('delete')}}</template>
        </confirm-dialog>
        <info-dialog :info-visibility="infoDialog.infoVisibility"
                     :info-type="infoDialog.infoType"
                     @infoAccept="accept({name: 'context-value.index'})">
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
                value: {},
                infoDialog: util.infoDialog,
                navigateToIndexPage: false,
                deleteConfirmationVisibility: false
            }
        },
        mounted() {
            let value = this.$store.state.selectedContextValue;

            if(value._links) {
                this.value = value;
            } else {
                console.log('selected context value not found');
                this.navigateTo({name: 'context-value.index'});
            }
        },
        methods: {
            navigateTo: util.navigateTo,
            accept: util.acceptInfoDialog,
            displayDeleteError: util.displayDeleteError,
            async deleteValue() {
                let href = this.value._links.self.href;
                api.delete(href)
                    .then(response => {
                        console.log(href + ' delete success: ' + response);
                        this.navigateTo({name: 'context-value.index'});
                    }).catch(e => {
                    console.log(href + ' delete error: ' + e);
                    this.displayDeleteError(e, 'error_delete_context_value_text', 'error_delete_context_value_detail',
                        'error_not_found_context_value_text', 'error_not_found_context_value_detail');
                    this.deleteConfirmationVisibility = false;
                });
            }
        }
    }
</script>

<style scoped>

</style>