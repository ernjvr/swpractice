<template>
    <v-container fluid grid-list-xl>
        <v-layout row>
            <v-flex xs4>
                <panel :title="$t('add_practice')">
                    <v-card-text>
                        <v-form ref="form">
                            <v-text-field v-model="data.name" v-on:keyup="keyEvent" prepend-icon="person" name="name"
                                          :label="$t('name')" type="text" required :rules="required" :maxlength="100"></v-text-field>
                            <v-textarea v-model="data.description" v-on:keyup="keyEvent" prepend-icon="person" name="description"
                                          :label="$t('description')" type="text" :maxlength="500"></v-textarea>
                            <v-select :label="$t('practice_sub_category')"
                                      :items="practiceSubCategories"
                                      item-text="name" item-value="_links.self.href"
                                      v-model="selectedPracticeSubCategory"
                                      prepend-icon="person" name="practiceSubCategory" required :rules="required" autocomplete>
                            </v-select>
                            <v-select :label="$t('reference')"
                                      :items="references"
                                      item-text="reference" item-value="_links.self.href"
                                      v-model="selectedReference"
                                      prepend-icon="person" name="reference" autocomplete>
                            </v-select>
                            <v-alert :value="validationError" color="error" v-html="error"></v-alert>
                        </v-form>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="indigo" dark @click="create">{{ $t('add') }}</v-btn>
                        <v-btn color="indigo" dark @click="navigateTo({
                                    name: 'practice.index'
                                })">{{ $t('cancel') }}</v-btn>
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
                    name: '',
                    description: '',
                    practiceSubCategory: '',
                    reference: '',
                },
                selectedPracticeSubCategory: '',
                selectedReference: '',
                error: null,
                validationError: false,
                // check if value exists or return required message
                required: [(v) => !!v || il8n.tc('field_required')]
            }
        },
        computed: {
            references() {
                return this.$store.state.references;
            },
            practiceSubCategories(){
                return this.$store.state.practiceSubCategories;
            }
        },
        methods: {
            async create() {
                try {
                    if (this.$refs.form.validate()) {
                        this.data.practiceSubCategory = this.getSelectedPracticeSubCategory()._links.self.href;
                        this.data.reference = this.selectedReference === '' ? '' : this.getSelectedReference()._links.self.href;
                        this.$store.dispatch('addPractice', this.data).then(response => {
                            this.validationError = false;
                            this.navigateTo('/practice');
                        }, error => {
                            console.log('received error from store addPractice: ' + error);
                            this.error = error;
                            this.validationError = true;
                        });
                    }
                } catch (e) {
                    console.log('catch error adding practice: ' + e);
                    this.error = e;
                    this.validationError = true;
                }
            },
            getSelectedPracticeSubCategory() {
                return util.getSelectedPracticeSubCategory(this.selectedPracticeSubCategory);
            },
            getSelectedReference() {
                return util.getSelectedReference(this.selectedReference);
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