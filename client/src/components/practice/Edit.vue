<template>
    <v-container fluid grid-list-xl>
        <v-layout row>
            <v-flex xs6>
                <panel :title="$t('edit_practice')">
                    <v-card-text>
                        <v-form ref="form">
                            <v-text-field v-model="editPractice.name" v-on:keyup="keyEvent" prepend-icon="person" name="name"
                                          :label="$t('name')" type="text" required :rules="required"></v-text-field>
                            <v-text-field v-model="editPractice.description" v-on:keyup="keyEvent" prepend-icon="person" name="description"
                                          :label="$t('description')" type="text"></v-text-field>
                            <v-select :label="$t('practice_sub_category')"
                                      :items="practiceSubCategories"
                                      item-text="name" item-value="_links.self.href"
                                      v-model="selectedPracticeSubCategory"
                                      prepend-icon="person" name="practiceSubCategory">
                            </v-select>
                            <v-alert :value="validationError" color="error" v-html="error"></v-alert>
                        </v-form>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="indigo" dark @click="edit">{{ $t('save') }}</v-btn>
                        <v-btn color="indigo" dark @click="navigateTo({name: 'practice.show'})">{{ $t('cancel') }}</v-btn>
                    </v-card-actions>
                </panel>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    import Panel from '@/components/Panel';
    import util from '../../common/util';
    import api from '../../services/api';
    import { il8n } from '../../il8n';

    export default {
        data() {
            return {
                practice: {
                    name: '',
                    description: '',
                    _links: '',
                    practiceCategory: '',
                    practiceSubCategory: ''
                },
                editPractice: {
                    name: '',
                    description: '',
                    _links: '',
                    practiceCategory: '',
                    practiceSubCategory: ''
                },
                selectedPracticeSubCategory: '',
                error: null,
                validationError: false,
                // check if value exists or return required message
                required: [(v) => !!v || il8n.tc('field_required')]
            }
        },
        computed: {
          practiceSubCategories() {
              return this.$store.state.practiceSubCategories;
          }
        },
        mounted() {
            let practice = this.$store.state.selectedPractice;

            if(practice.name) {
                this.practice = practice;
                this.editPractice = {
                    name: this.practice.name,
                    description: this.practice.description,
                    _links: this.practice._links,
                    practiceSubCategory: this.practice.practiceSubCategory,
                    practiceCategory: this.practice.practiceCategory
                };
                this.selectedPracticeSubCategory = this.practice.practiceSubCategory._links.self.href;
            } else {
                console.log('selected practice not found');
                this.navigateTo('/practice/');
            }
        },
        methods: {
            async edit() {
                try {
                    if (this.$refs.form.validate()) {
                        let practiceCat;
                        await this.getPracticeCategory().then(response => {
                            practiceCat = response;
                        });

                        let data = {
                            href: this.practice._links.self.href,
                            practice: {
                                name: this.editPractice.name,
                                description: this.editPractice.description,
                                practiceSubCategory: this.getSelectedPracticeSubCategory()._links.self.href
                            }
                        };
                        this.$store.dispatch('editPractice', data).then(_ => {
                            this.editPractice.practiceSubCategory = this.getSelectedPracticeSubCategory();
                            this.editPractice.practiceCategory = practiceCat;
                            this.$store.commit('setSelectedPractice', this.editPractice);
                            this.$store.commit('editPractice', this.editPractice);
                            this.navigateTo({name: 'practice.show'});
                        }, error => {
                            console.log('received error from store editPractice: ' + error);
                            this.error = error;
                            this.validationError = true;
                        });
                    }
                } catch (e) {
                    this.error = e.response.data.error;
                    this.validationError = true;
                }
            },
            getSelectedPracticeSubCategory() {
              return util.getSelectedPracticeSubCategory(this.selectedPracticeSubCategory);
            },
            async getPracticeCategory() {
                let practiceCat;
                let practiceSubCat;
                let href = this.selectedPracticeSubCategory;

                await api.get(href)
                    .then(response => {
                        practiceSubCat = response.data;
                    }).catch(e => {
                        console.log(href + ' get error: ' + e);
                    });
                await api.get(practiceSubCat._links.practiceCategory.href)
                    .then(response => {
                        practiceCat = response.data;
                    }).catch(e => {
                        console.log(href + ' get error: ' + e);
                    });
                return practiceCat;
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