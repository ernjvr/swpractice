<template>
    <panel :title="$t('add_reference')">
        <v-card-text>
            <v-form ref="form">
                <v-text-field v-model="data.author" prepend-icon="person" name="author" :label="$t('author')"
                              autofocus v-on:keyup.enter="create" type="text" :maxlength="100"></v-text-field>
                <v-text-field v-model="data.year" prepend-icon="person" name="year"
                              :label="$t('year')" :mask="yearMask"></v-text-field>
                <v-textarea v-model="data.reference" prepend-icon="person" name="reference" :label="$t('reference')"
                            type="text" required :rules="required" :maxlength="2500"></v-textarea>
                <v-autocomplete :label="$t('reference_type')"
                          :items="referenceTypes"
                          item-text="name" item-value="_links.self.href"
                          v-model="selectedReferenceType"
                          prepend-icon="person" name="referenceType" required :rules="required">
                </v-autocomplete>
            </v-form>
        </v-card-text>
        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="indigo" dark @click="create"><v-icon dark left>add</v-icon>{{ $t('add') }}</v-btn>
            <v-btn color="indigo" dark @click="close"><v-icon dark left>cancel</v-icon>{{ $t('cancel') }}</v-btn>
        </v-card-actions>
        <info-dialog :info-visibility="infoDialog.infoVisibility"
                     :info-type="infoDialog.infoType"
                     @infoAccept="infoDialog.infoVisibility=false">
            <template slot="title">{{ infoDialog.title }}</template>
            <template slot="text">{{ infoDialog.text }}</template>
            <template slot="detail">{{ infoDialog.detail }}</template>
            <template slot="confirmButton">{{ $t('close')}}</template>
        </info-dialog>
    </panel>
</template>

<script>
    import Panel from '@/components/Panel';
    import util from '@/common/util';
    import { il8n } from '@/il8n';
    import InfoDialog from '@/components/dialog/InformationDialog';

    export default {
        components: {
            Panel,
            InfoDialog
        },
        data() {
            return {
                data: {
                    author: '',
                    year: '',
                    reference: '',
                    _links: '',
                    referenceType: ''
                },
                selectedReferenceType: '',
                yearMask: '####',
                infoDialog: util.infoDialog,
                // check if value exists or return required message
                required: [(v) => !!v || il8n.tc('field_required')]
            }
        },
        computed: {
            referenceTypes(){
                return this.$store.state.referenceTypes;
            }
        },
        methods: {
            showDialog: util.showDialog,
            displayAddError: util.displayAddError,
            async create() {
                try {
                    if (this.$refs.form.validate()) {
                        this.data.referenceType = this.getSelectedReferenceType();
                        this.$store.dispatch('addReference', this.data).then(response => {
                            console.log('received data from store addReference: ' + response);
                            this.close();
                        }, error => {
                            console.log('received error from store addReference: ' + error);
                            this.displayAddError(error);
                        });
                    }
                } catch (e) {
                    console.log('catch error adding reference: ' + e);
                    util.displayInfoDialog('error', this.$t('error_add_title'),
                        this.$t('error_unknown_text'),
                        this.$t('error_unknown_detail'));
                }
            },
            getSelectedReferenceType() {
                return util.getSelectedReferenceType(this.selectedReferenceType);
            },
            close() {
                this.data.author = '';
                this.data.year = '';
                this.data.reference = '';
                this.selectedReferenceType = '';
                this.showDialog(false);
            }
        },
    }
</script>

<style scoped>

</style>