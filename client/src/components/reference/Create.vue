<template>
    <v-container fluid grid-list-xl>
        <v-layout row>
            <v-flex xs4>
                <panel :title="$t('add_reference')">
                    <v-card-text>
                        <v-form ref="form">
                            <v-text-field v-model="data.author" v-on:keyup="keyEvent" prepend-icon="person" name="author"
                                          :label="$t('author')" type="text" :maxlength="100"></v-text-field>
                            <v-text-field v-model="data.year" v-on:keyup="keyEvent" prepend-icon="person" name="year"
                                          :label="$t('year')" :mask="yearMask"></v-text-field>
                            <v-textarea v-model="data.reference" v-on:keyup="keyEvent" prepend-icon="person" name="reference"
                                          :label="$t('reference')" type="text" required :rules="required" :maxlength="2500"></v-textarea>
                            <v-autocomplete :label="$t('reference_type')"
                                      :items="referenceTypes"
                                      item-text="name" item-value="_links.self.href"
                                      v-model="selectedReferenceType"
                                      prepend-icon="person" name="referenceType" required :rules="required">
                            </v-autocomplete>
                            <v-alert :value="validationError" color="error" v-html="error"></v-alert>
                        </v-form>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="indigo" dark @click="create">{{ $t('add') }}</v-btn>
                        <v-btn color="indigo" dark @click="navigateTo({name: 'reference.index'})">{{ $t('cancel') }}</v-btn>
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
                data: {
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
            referenceTypes(){
                return this.$store.state.referenceTypes;
            }
        },
        methods: {
            async create() {
                try {
                    if (this.$refs.form.validate()) {
                        this.data.referenceType = this.getSelectedReferenceType()._links.self.href;
                        this.$store.dispatch('addReference', this.data).then(response => {
                            this.validationError = false;
                            this.navigateTo('/reference');
                        }, error => {
                            console.log('received error from store addReference: ' + error);
                            this.error = error;
                            this.validationError = true;
                        });
                    }
                } catch (e) {
                    console.log('catch error adding reference: ' + e);
                    this.error = e;
                    this.validationError = true;
                }
            },
            getSelectedReferenceType() {
                return util.getSelectedReferenceType(this.selectedReferenceType);
            },
            navigateTo(route) {
                this.$router.push(route);
            },
            keyEvent() {
                if (this.validationError) {
                    this.validationError = false;
                }
            }
        },
        components: {
            Panel
        }
    }
</script>

<style scoped>

</style>