<template>
    <panel :title="$t('add_practice_sub_category')">
        <v-card-text>
            <v-form ref="form">
                <v-text-field v-model="data.name" prepend-icon="person" name="name" :label="$t('name')" autofocus
                              v-on:keyup.enter="create" type="text" required :rules="required" :maxlength="100"></v-text-field>
                <v-textarea v-model="data.description" prepend-icon="person" name="description"
                              :label="$t('description')" type="text" :maxlength="500"></v-textarea>
                <v-autocomplete :label="$t('practice_category')"
                          :items="practiceCategories"
                          item-text="name" item-value="_links.self.href"
                          v-model="selectedPracticeCategory"
                          prepend-icon="person" name="practiceCategory" required :rules="required">
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
                    practiceCategory: ''
                },
                selectedPracticeCategory: '',
                infoDialog: util.infoDialog,
                // check if value exists or return required message
                required: [(v) => !!v || il8n.tc('field_required')]
            }
        },
        computed: {
            practiceCategories(){
                return this.$store.state.practiceCategories;
            }
        },
        methods: {
            showDialog: util.showDialog,
            displayAddError: util.displayAddError,
            async create() {
                try {
                    if (this.$refs.form.validate()) {
                        this.data.practiceCategory = this.getSelectedPracticeCategory();
                        this.$store.dispatch('addPracticeSubCategory', this.data).then(response => {
                            console.log('received data from store addPracticeSubCategory: ' + response);
                            this.close();
                        }, error => {
                            console.log('received error from store addPracticeSubCategory: ' + error);
                            this.displayAddError(error);
                        });
                    }
                } catch (e) {
                    console.log('catch error adding practice sub category: ' + e);
                    util.displayInfoDialog('error', this.$t('error_add_title'),
                        this.$t('error_unknown_text'),
                        this.$t('error_unknown_detail'));
                }
            },
            getSelectedPracticeCategory() {
                return util.getSelectedPracticeCategory(this.selectedPracticeCategory);
            },
            close() {
                this.data.name = '';
                this.data.description = '';
                this.selectedPracticeCategory = '';
                this.showDialog(false);
            }
        },
    }
</script>

<style scoped>

</style>