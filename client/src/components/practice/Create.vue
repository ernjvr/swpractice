<template>
    <panel :title="$t('add_practice')">
        <v-card-text>
            <v-form ref="form">
                <v-text-field v-model="data.name" prepend-icon="person" name="name" :label="$t('name')" autofocus
                              v-on:keyup.enter="create" type="text" required :rules="required" :maxlength="100"></v-text-field>
                <v-textarea v-model="data.description" prepend-icon="person" name="description"
                              :label="$t('description')" type="text" :maxlength="500"></v-textarea>
                <v-autocomplete :label="$t('practice_sub_category')"
                          :items="practiceSubCategories"
                          item-text="name" item-value="_links.self.href"
                          v-model="selectedPracticeSubCategory"
                          prepend-icon="person" name="practiceSubCategory" required :rules="required">
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
                    name: '',
                    description: '',
                    practiceSubCategory: '',
                    reference: '',
                },
                selectedPracticeSubCategory: '',
                selectedReference: '',
                infoDialog: util.infoDialog,
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
            showDialog: util.showDialog,
            displayAddError: util.displayAddError,
            async create() {
                try {
                    if (this.$refs.form.validate()) {
                        this.data.practiceSubCategory = this.getSelectedPracticeSubCategory();
                        this.data.reference = this.selectedReference === '' ? '' : this.getSelectedReference();
                        this.$store.dispatch('addPractice', this.data).then(response => {
                            console.log('received data from store addPractice: ' + response);
                            this.close();
                        }, error => {
                            console.log('received error from store addPractice: ' + error);
                            this.displayAddError(error);
                        });
                    }
                } catch (e) {
                    console.log('catch error adding practice: ' + e);
                    util.displayInfoDialog('error', this.$t('error_add_title'),
                        this.$t('error_unknown_text'),
                        this.$t('error_unknown_detail'));
                }
            },
            getSelectedPracticeSubCategory() {
                return util.getSelectedPracticeSubCategory(this.selectedPracticeSubCategory);
            },
            getSelectedReference() {
                return util.getSelectedReference(this.selectedReference);
            },
            close() {
                this.data.name = '';
                this.data.description = '';
                this.selectedPracticeSubCategory = '';
                this.selectedReference = '';
                this.showDialog(false);
            }
        },
    }
</script>

<style scoped>

</style>