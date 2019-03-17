<template>
    <v-container fluid grid-list-xl>
        <v-layout row>
            <v-flex xs6>
                <panel :title="$t('edit_practice_sub_category')">
                    <v-card-text>
                        <v-form ref="form">
                            <v-text-field v-model="editPracticeSubCategory.name" v-on:keyup="keyEvent" prepend-icon="person" name="name"
                                          :label="$t('name')" type="text" required :rules="required"></v-text-field>
                            <v-textarea v-model="editPracticeSubCategory.description" v-on:keyup="keyEvent" prepend-icon="person" name="description"
                                          :label="$t('description')" type="text"></v-textarea>
                            <v-select :label="$t('practice_category')"
                                      :items="practiceCategories"
                                      item-text="name" item-value="_links.self.href"
                                      v-model="selectedPracticeCategory"
                                      prepend-icon="person" name="practiceCategory">
                            </v-select>
                            <v-alert :value="validationError" color="error" v-html="error"></v-alert>
                        </v-form>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="indigo" dark @click="edit">{{ $t('save') }}</v-btn>
                        <v-btn color="indigo" dark @click="navigateTo({name: 'practice-sub-category.show'})">{{ $t('cancel') }}</v-btn>
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
                practiceSubCategory: {
                    name: '',
                    description: '',
                    _links: '',
                    practiceCategory: ''
                },
                editPracticeSubCategory: {
                    name: '',
                    description: '',
                    _links: '',
                    practiceCategory: ''
                },
                selectedPracticeCategory: '',
                error: null,
                validationError: false,
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
                this.practiceSubCategory = practiceSubCategory;
                this.editPracticeSubCategory = {
                    name: this.practiceSubCategory.name,
                    description: this.practiceSubCategory.description,
                    _links: this.practiceSubCategory._links,
                    practiceCategory: this.practiceSubCategory.practiceCategory
                };
                this.selectedPracticeCategory = this.practiceSubCategory.practiceCategory._links.self.href;
            } else {
                console.log('selected practice sub category not found');
                this.navigateTo('/practice-sub-category/');
            }
        },
        methods: {
            async edit() {
                try {
                    if (this.$refs.form.validate()) {
                        let data = {
                            href: this.practiceSubCategory._links.self.href,
                            practiceSubCategory: {
                                name: this.editPracticeSubCategory.name,
                                description: this.editPracticeSubCategory.description,
                                practiceCategory: this.getSelectedPracticeCategory()._links.self.href
                            }
                        };
                        console.log('dispatching to editPracticeSubCategory data:');
                        console.log(data);
                        this.$store.dispatch('editPracticeSubCategory', data).then(response => {
                            console.log('received data from store editPracticeSubCategory: ');
                            console.log(response);
                            this.editPracticeSubCategory.practiceCategory = this.getSelectedPracticeCategory();
                            console.log('updated practice category of editPracticeSubCategory:');
                            console.log(this.editPracticeSubCategory);
                            this.$store.commit('setSelectedPracticeSubCategory', this.editPracticeSubCategory);
                            this.$store.commit('editPracticeSubCategory', this.editPracticeSubCategory);
                            this.navigateTo({name: 'practice-sub-category.show'});
                        }, error => {
                            console.log('received error from store editPracticeSubCategory: ' + error);
                            this.error = error;
                            this.validationError = true;
                        });
                    }
                } catch (e) {
                    this.error = e.response.data.error;
                    this.validationError = true;
                }
            },
            getSelectedPracticeCategory() {
              return util.getSelectedPracticeCategory(this.selectedPracticeCategory);
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