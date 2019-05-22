<template>
    <v-container>
        <v-layout>
            <v-flex xs12>
                <panel :title="$t('edit_reference')">
                    <v-card-text>
                        <v-form ref="form">
                            <v-text-field v-model="reference.author" prepend-icon="person" name="author" autofocus
                                          :label="$t('author')" type="text" :maxlength="100"></v-text-field>
                            <v-text-field v-model="reference.year" prepend-icon="person" name="year"
                                          :label="$t('year')" :mask="yearMask"></v-text-field>
                            <v-textarea v-model="reference.reference" prepend-icon="person" name="reference"
                                          :label="$t('reference')" type="text" required :rules="required" :maxlength="2500"></v-textarea>
                            <v-autocomplete :label="$t('reference_type')"
                                      :items="referenceTypes"
                                      item-text="name" item-value="_links.self.href"
                                      v-model="selectedReferenceType"
                                      prepend-icon="person" name="referenceType">
                            </v-autocomplete>
                        </v-form>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="indigo" dark @click="edit"><v-icon dark left>save</v-icon>{{ $t('save') }}</v-btn>
                        <v-btn color="indigo" dark @click="navigateTo({name: 'reference.show'})">
                            <v-icon dark left>cancel</v-icon>{{ $t('cancel') }}
                        </v-btn>
                    </v-card-actions>
                </panel>
            </v-flex>
        </v-layout>
        <info-dialog :info-visibility="infoDialog.infoVisibility"
                     :info-type="infoDialog.infoType"
                     @infoAccept="accept({name: 'reference.index'})">
            <template slot="title">{{ infoDialog.title }}</template>
            <template slot="text">{{ infoDialog.text }}</template>
            <template slot="detail">{{ infoDialog.detail }}</template>
            <template slot="confirmButton">{{ $t('close')}}</template>
        </info-dialog>
    </v-container>
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
                reference: {
                    author: '',
                    year: '',
                    reference: '',
                    _links: '',
                    referenceType: ''
                },
                selectedReferenceType: '',
                yearMask: '####',
                infoDialog: util.infoDialog,
                navigateToIndexPage: false,
                required: [(v) => !!v || il8n.tc('field_required')]
            }
        },
        computed: {
          referenceTypes() {
              return this.$store.state.referenceTypes;
          }
        },
        mounted() {
            let reference = this.$store.state.selectedReference;

            if(reference._links.self.href) {
                // avoid modifying store object directly by copying values into new object
                this.reference = {
                    author: reference.author,
                    year: reference.year,
                    reference: reference.reference,
                    _links: reference._links,
                    referenceType: reference.referenceType
                };
                this.selectedReferenceType = this.reference.referenceType._links.self.href;
            } else {
                console.log('selected reference not found');
                this.navigateTo({name: 'reference.index'});
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
                            href: this.reference._links.self.href,
                            reference: {
                                author: this.reference.author,
                                year: this.reference.year,
                                reference: this.reference.reference,
                                referenceType: this.getSelectedReferenceType()._links.self.href
                            }
                        };
                        this.$store.dispatch('editReference', data).then(response => {
                            console.log('received data from store editReference: ' + response);
                            this.reference.referenceType = this.getSelectedReferenceType();
                            this.$store.commit('setSelectedReference', this.reference);
                            this.$store.commit('editReference', this.reference);
                            this.navigateTo({name: 'reference.show'});
                        }, error => {
                            console.log('received error from store editReference: ' + error);
                            this.displayEditError(error, 'error_not_found_reference_text',
                                'error_not_found_reference_detail');
                        });
                    }
                } catch (e) {
                    util.displayInfoDialog('error', this.$t('error_delete_title'), e.response.data.error,
                        this.$t('error_unknown_detail'));
                }
            },
            getSelectedReferenceType() {
              return util.getSelectedReferenceType(this.selectedReferenceType);
            },
        },
    }
</script>

<style scoped>

</style>