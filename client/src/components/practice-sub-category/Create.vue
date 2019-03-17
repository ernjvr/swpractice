<template>
    <v-container fluid grid-list-xl>
        <v-layout row>
            <v-flex xs4>
                <panel :title="$t('add_practice_sub_category')">
                    <v-card-text>
                        <v-form ref="form">
                            <v-text-field v-model="data.name" v-on:keyup="keyEvent" prepend-icon="person" name="name"
                                          :label="$t('name')" type="text" required :rules="required"></v-text-field>
                            <v-textarea v-model="data.description" v-on:keyup="keyEvent" prepend-icon="person" name="description"
                                          :label="$t('description')" type="text"></v-textarea>
                            <v-select :label="$t('practice_category')"
                                      :items="practiceCategories"
                                      item-text="name" item-value="_links.self.href"
                                      v-model="selectedPracticeCategory"
                                      prepend-icon="person" name="practiceCategory" required :rules="required">
                            </v-select>
                            <v-alert :value="validationError" color="error" v-html="error"></v-alert>
                        </v-form>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="indigo" dark @click="create">{{ $t('add') }}</v-btn>
                        <v-btn color="indigo" dark @click="navigateTo({
                                    name: 'practice-sub-category.index'
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
            practiceCategories(){
                return this.$store.state.practiceCategories;
            }
        },
        methods: {
            async create() {
                try {
                    if (this.$refs.form.validate()) {
                        this.data.practiceCategory = this.getSelectedPracticeCategory()._links.self.href;
                        this.$store.dispatch('addPracticeSubCategory', this.data).then(response => {
                            console.log('received data from store addPracticeSubCategory: ' + response);
                            this.validationError = false;
                            this.navigateTo('/practice-sub-category');
                        }, error => {
                            console.log('received error from store addPracticeSubCategory: ' + error);
                            this.error = error;
                            this.validationError = true;
                        });
                    }
                } catch (e) {
                    console.log('catch error adding practice sub category: ' + e);
                    this.error = e;
                    this.validationError = true;
                }
            },
            getSelectedPracticeCategory() {
                return util.getSelectedPracticeCategory(this.selectedPracticeCategory);
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