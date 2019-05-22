<template>
    <v-container>
        <v-layout>
            <v-flex xs12>
                <panel :title="$t('edit_practice_sub_category')">
                    <v-card-text>
                        <v-form ref="form">
                            <v-text-field v-model="practiceSubCategory.name" prepend-icon="person" name="name" autofocus
                                          :label="$t('name')" type="text" required :rules="required" :maxlength="100"></v-text-field>
                            <v-textarea v-model="practiceSubCategory.description"  prepend-icon="person" name="description"
                                          :label="$t('description')" type="text" :maxlength="500"></v-textarea>
                            <v-autocomplete :label="$t('practice_category')"
                                      :items="practiceCategories"
                                      item-text="name" item-value="_links.self.href"
                                      v-model="selectedPracticeCategory"
                                      prepend-icon="person" name="practiceCategory">
                            </v-autocomplete>
                        </v-form>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="indigo" dark @click="edit"><v-icon dark left>save</v-icon>{{ $t('save') }}</v-btn>
                        <v-btn color="indigo" dark @click="navigateTo({name: 'practice-sub-category.show'})">
                            <v-icon dark left>cancel</v-icon>{{ $t('cancel') }}
                        </v-btn>
                    </v-card-actions>
                </panel>
            </v-flex>
        </v-layout>
        <info-dialog :info-visibility="infoDialog.infoVisibility"
                     :info-type="infoDialog.infoType"
                     @infoAccept="accept({name: 'practice-sub-category.index'})">
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
                practiceSubCategory: {
                    name: '',
                    description: '',
                    _links: '',
                    practiceCategory: ''
                },
                selectedPracticeCategory: '',
                infoDialog: util.infoDialog,
                navigateToIndexPage: false,
                // check if value exists or return required message
                required: [(v) => !!v || il8n.tc('field_required')]
            }
        },
        computed: {
          practiceCategories() {
              return this.$store.state.practiceCategories;
          }
        },
        mounted() {
            let practiceSubCategory = this.$store.state.selectedPracticeSubCategory;

            if(practiceSubCategory.name) {
                // avoid modifying store object directly by copying values into new object
                this.practiceSubCategory = {
                    name: practiceSubCategory.name,
                    description: practiceSubCategory.description,
                    _links: practiceSubCategory._links,
                    practiceCategory: practiceSubCategory.practiceCategory
                };
                this.selectedPracticeCategory = this.practiceSubCategory.practiceCategory._links.self.href;
            } else {
                console.log('selected practice sub category not found');
                this.navigateTo({name: 'practice-sub-category.index'});
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
                            href: this.practiceSubCategory._links.self.href,
                            practiceSubCategory: {
                                name: this.practiceSubCategory.name,
                                description: this.practiceSubCategory.description,
                                practiceCategory: this.getSelectedPracticeCategory()._links.self.href
                            }
                        };
                        this.$store.dispatch('editPracticeSubCategory', data).then(response => {
                            console.log('received data from store editPracticeSubCategory: ' + response);
                            this.practiceSubCategory.practiceCategory = this.getSelectedPracticeCategory();
                            this.$store.commit('setSelectedPracticeSubCategory', this.practiceSubCategory);
                            this.$store.commit('editPracticeSubCategory', this.practiceSubCategory);
                            this.navigateTo({name: 'practice-sub-category.show'});
                        }, error => {
                            console.log('received error from store editPracticeSubCategory: ' + error);
                            this.displayEditError(error, 'error_not_found_practice_sub_category_text',
                                'error_not_found_practice_sub_category_detail');
                        });
                    }
                } catch (e) {
                    util.displayInfoDialog('error', this.$t('error_delete_title'), e.response.data.error,
                        this.$t('error_unknown_detail'));
                }
            },
            getSelectedPracticeCategory() {
              return util.getSelectedPracticeCategory(this.selectedPracticeCategory);
            },
        },
    }
</script>

<style scoped>

</style>