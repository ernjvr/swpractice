<template>
    <v-container>
        <v-layout>
            <v-flex xs12>
                <panel :title="$t('edit_practice')">
                    <v-card-text>
                        <v-form ref="form">
                            <v-text-field v-model="practice.name" prepend-icon="person" name="name" autofocus
                                          :label="$t('name')" type="text" required :rules="required" :maxlength="100"></v-text-field>
                            <v-textarea v-model="practice.description" prepend-icon="person" name="description"
                                          :label="$t('description')" type="text" required :rules="required" :maxlength="500"></v-textarea>
                            <v-autocomplete :label="$t('practice_sub_category')"
                                      :items="practiceSubCategories"
                                      item-text="name" item-value="_links.self.href"
                                      v-model="selectedPracticeSubCategory"
                                      prepend-icon="person" name="practiceSubCategory">
                            </v-autocomplete>
                            <v-autocomplete :label="$t('reference')"
                                      :items="references"
                                      item-text="reference" item-value="_links.self.href"
                                      v-model="selectedReference"
                                      prepend-icon="person" name="reference">
                            </v-autocomplete>
                        </v-form>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="indigo" dark @click="edit"><v-icon dark left>save</v-icon>{{ $t('save') }}</v-btn>
                        <v-btn color="indigo" dark @click="navigateTo({name: 'practice.show'})">
                            <v-icon dark left>cancel</v-icon>{{ $t('cancel') }}
                        </v-btn>
                    </v-card-actions>
                </panel>
            </v-flex>
        </v-layout>
        <info-dialog :info-visibility="infoDialog.infoVisibility"
                     :info-type="infoDialog.infoType"
                     @infoAccept="accept({name: 'practice.index'})">
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
    import {il8n} from '@/il8n';
    import InfoDialog from '@/components/dialog/InformationDialog';

    export default {
        components: {
            Panel,
            InfoDialog
        },
        data() {
            return {
                practice: {
                    name: '',
                    description: '',
                    _links: '',
                    practiceCategory: '',
                    practiceSubCategory: '',
                    reference: '',
                },
                selectedPracticeSubCategory: '',
                selectedReference: '',
                infoDialog: util.infoDialog,
                navigateToIndexPage: false,
                // check if value exists or return required message
                required: [(v) => !!v || il8n.tc('field_required')]
            }
        },
        computed: {
            references() {
                return this.$store.state.references;
            },
            practiceSubCategories() {
                return this.$store.state.practiceSubCategories;
            }
        },
        mounted() {
            let practice = this.$store.state.selectedPractice;

            if(practice.name) {
                // avoid modifying store object directly by copying values into new object
                this.practice = {
                    name: practice.name,
                    description: practice.description,
                    _links: practice._links,
                    practiceSubCategory: practice.practiceSubCategory,
                    practiceCategory: practice.practiceCategory,
                    reference: practice.reference
                };
                this.selectedPracticeSubCategory = this.practice.practiceSubCategory._links.self.href;
                this.selectedReference = this.practice.reference  === '' ? '' : this.practice.reference._links.self.href;
            } else {
                console.log('selected practice not found');
                this.navigateTo({name: 'practice.index'});
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
                            href: this.practice._links.self.href,
                            practice: {
                                name: this.practice.name,
                                description: this.practice.description,
                                practiceSubCategory: this.getSelectedPracticeSubCategory()._links.self.href,
                                reference: this.selectedReference === '' ? '' : this.getSelectedReference()._links.self.href
                            }
                        };
                        this.$store.dispatch('editPractice', data).then(_ => {
                            this.practice.reference = this.selectedReference === '' ? '' : this.getSelectedReference();
                            this.practice.practiceSubCategory = this.getSelectedPracticeSubCategory();
                            this.practice.practiceCategory = this.getPracticeCategory();
                            this.$store.commit('setSelectedPractice', this.practice);
                            this.$store.commit('editPractice', this.practice);
                            this.navigateTo({name: 'practice.show'});
                        }, error => {
                            console.log('received error from store editPractice: ' + error);
                            this.displayEditError(error, 'error_not_found_practice_text',
                                'error_not_found_practice_detail');
                        });
                    }
                } catch (e) {
                    util.displayInfoDialog('error', this.$t('error_delete_title'), e.response.data.error,
                        this.$t('error_unknown_detail'));
                }
            },
            getSelectedPracticeSubCategory() {
              return util.getSelectedPracticeSubCategory(this.selectedPracticeSubCategory);
            },
            getSelectedReference() {
              return util.getSelectedReference(this.selectedReference);
            },
            getPracticeCategory() {
                let practiceSubCat = this.getSelectedPracticeSubCategory();
                return util.getSelectedPracticeCategory(practiceSubCat.practiceCategory._links.self.href);
            },
        },
    }
</script>

<style scoped>

</style>