<template>
    <v-container fluid grid-list-xl>
        <v-layout row>
            <v-flex xs6>
                <panel :title="$t('edit_reference')">
                    <v-card-text>
                        <v-form ref="form">
                            <v-text-field v-model="editReference.author" v-on:keyup="keyEvent" prepend-icon="person" name="author"
                                          :label="$t('author')" type="text" :maxlength="100"></v-text-field>
                            <v-text-field v-model="editReference.year" v-on:keyup="keyEvent" prepend-icon="person" name="year"
                                          :label="$t('year')" :mask="yearMask"></v-text-field>
                            <v-textarea v-model="editReference.reference" v-on:keyup="keyEvent" prepend-icon="person" name="reference"
                                          :label="$t('reference')" type="text" required :rules="required" :maxlength="2500"></v-textarea>
                            <v-select :label="$t('reference_type')"
                                      :items="referenceTypes"
                                      item-text="name" item-value="_links.self.href"
                                      v-model="selectedReferenceType"
                                      prepend-icon="person" name="referenceType" autocomplete>
                            </v-select>
                            <v-alert :value="validationError" color="error" v-html="error"></v-alert>
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
    </v-container>
</template>

<script>
    import Panel from '@/components/Panel';
    import util from '../../common/util';
    import { il8n } from '../../il8n';

    export default {
        data() {
            return {
                reference: {
                    author: '',
                    year: '',
                    reference: '',
                    _links: '',
                    referenceType: ''
                },
                editReference: {
                    author: '',
                    year: '',
                    reference: '',
                    _links: '',
                    referenceType: ''
                },
                selectedReferenceType: '',
                yearMask: '####',
                error: null,
                validationError: false,
                // check if value exists or return required message
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
                this.reference = reference;
                this.editReference = {
                    author: this.reference.author,
                    year: this.reference.year,
                    reference: this.reference.reference,
                    _links: this.reference._links,
                    referenceType: this.reference.referenceType
                };
                this.selectedReferenceType = this.reference.referenceType._links.self.href;
            } else {
                console.log('selected reference not found');
                this.navigateTo('/reference/');
            }
        },
        methods: {
            async edit() {
                try {
                    if (this.$refs.form.validate()) {
                        let data = {
                            href: this.reference._links.self.href,
                            reference: {
                                author: this.editReference.author,
                                year: this.editReference.year,
                                reference: this.editReference.reference,
                                referenceType: this.getSelectedReferenceType()._links.self.href
                            }
                        };
                        this.$store.dispatch('editReference', data).then(_ => {
                            this.editReference.referenceType = this.getSelectedReferenceType();
                            this.$store.commit('setSelectedReference', this.editReference);
                            this.$store.commit('editReference', this.editReference);
                            this.navigateTo({name: 'reference.show'});
                        }, error => {
                            console.log('received error from store editReference: ' + error);
                            this.error = error;
                            this.validationError = true;
                        });
                    }
                } catch (e) {
                    this.error = e.response.data.error;
                    this.validationError = true;
                }
            },
            getSelectedReferenceType() {
              return util.getSelectedReferenceType(this.selectedReferenceType);
            },
            keyEvent() {
                if (this.validationError) {
                    this.validationError = false;
                }
            },
            navigateTo(route) {
                this.$router.push(route);
            },
        },
        components: {
            Panel
        }
    }
</script>

<style scoped>

</style>