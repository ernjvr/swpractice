<template>
    <v-container>
        <v-layout>
            <v-flex xs12>
                <panel :title="$t('edit_context_value')">
                    <v-card-text>
                        <v-form ref="form">
                            <v-text-field v-model="value.value" prepend-icon="person" name="value" :label="$t('value')"
                                          autofocus type="text" required :rules="required" :mask="valueMask"></v-text-field>
                            <v-textarea v-model="value.description" prepend-icon="person" name="description" type="text"
                                        :label="$t('description')" required :rules="required" :maxlength="500"></v-textarea>
                        </v-form>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="indigo" dark @click="edit"><v-icon dark left>save</v-icon>{{ $t('save') }}</v-btn>
                        <v-btn color="indigo" dark @click="navigateTo({name: 'context-value.show'})">
                            <v-icon dark left>cancel</v-icon>{{ $t('cancel') }}
                        </v-btn>
                    </v-card-actions>
                </panel>
            </v-flex>
        </v-layout>
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
    import util from '@/common/util';
    import Panel from '@/components/Panel';
    import { il8n } from '@/il8n';
    import InfoDialog from '@/components/dialog/InformationDialog';

    export default {
        components: {
            Panel,
            InfoDialog
        },
        data() {
            return {
                value: {},
                valueMask: '#########',
                infoDialog: util.infoDialog,
                navigateToIndexPage: false,
                // check if value exists or return required message
                required: [(v) => !!v || il8n.tc('field_required')]
            }
        },
        async mounted() {
            let value = this.$store.state.selectedContextValue;

            if(value._links) {
                // avoid modifying store object directly by copying values into new object
                this.value = {
                    value: value.value,
                    description: value.description,
                    _links: value._links
                };
            } else {
                console.log('selected context value not found');
                this.navigateTo({name: 'context-value.index'});
            }
        },
        methods: {
            navigateTo: util.navigateTo,
            accept: util.acceptInfoDialog,
            displayEditError: util.displayEditError,
            async edit() {
                try {
                    if (this.$refs.form.validate()) {
                        let data = {
                            href: this.value._links.self.href,
                            value: {
                                value: this.value.value,
                                description: this.value.description
                            }
                        };
                        this.$store.dispatch('editContextValue', data).then(response => {
                            console.log('received data from store editContextValue: ' + response);
                            this.$store.commit('setSelectedContextValue', this.value);
                            this.navigateTo({name: 'context-value.show'});
                        }, error => {
                            console.log('received error from store editContextValue: ' + error);
                            this.displayEditError(error, 'error_not_found_context_value_text',
                                'error_not_found_context_value_detail');
                        });
                    }
                } catch (e) {
                    util.displayInfoDialog('error', this.$t('error_delete_title'), e.response.data.error,
                        this.$t('error_unknown_detail'));
                }
            },
        },
    }
</script>

<style scoped>

</style>