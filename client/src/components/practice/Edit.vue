<template>
    <v-container fluid grid-list-xl>
        <v-layout row>
            <v-flex xs6>
                <panel title="Edit Practice">
                    <v-card-text>
                        <v-form ref="form">
                            <v-text-field v-model="editPractice.name" v-on:keyup="keyEvent" prepend-icon="person" name="name"
                                          label="Name" type="text" required :rules="required"></v-text-field>
                            <v-text-field v-model="editPractice.description" v-on:keyup="keyEvent" prepend-icon="person" name="description"
                                          label="Description" type="text"></v-text-field>
                            <v-select label="Practice Category"
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
                        <v-btn color="indigo" dark @click="edit">Save</v-btn>
                        <v-btn color="indigo" dark @click="navigateTo({name: 'practice.show'})">Cancel</v-btn>
                    </v-card-actions>
                </panel>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    import Panel from '@/components/Panel';

    export default {
        data() {
            return {
                practice: {
                    name: '',
                    description: '',
                    _links: '',
                    practiceCategory: ''
                },
                editPractice: {
                    name: '',
                    description: '',
                    _links: '',
                    practiceCategory: ''
                },
                selectedPracticeCategory: '',
                items: ['one', 'two'],
                error: null,
                validationError: false,
                // check if value exists or return required message
                required: [(v) => !!v || 'This field is required']
            }
        },
        computed: {
          practiceCategories() {
              return this.$store.state.practiceCategories;
          }
        },
        mounted() {
            let practice = this.$store.state.practice;
            if(practice) {
                this.practice = practice;
                this.editPractice = {
                    name: this.practice.name,
                    description: this.practice.description,
                    _links: this.practice._links,
                    practiceCategory: this.practice.practiceCategory
                };
                this.selectedPracticeCategory = this.practice.practiceCategory._links.self.href;
            } else {
                console.log('selected practice not found');
                this.navigateTo('/practice/');
            }
        },
        methods: {
            async edit() {
                try {
                    if (this.$refs.form.validate()) {
                        let data = {
                            href: this.practice._links.self.href,
                            practice: {
                                name: this.editPractice.name,
                                description: this.editPractice.description,
                                practiceCategory: this.getSelectedPracticeCategory()._links.self.href
                            }
                        };
                        console.log('dispatching to editPractice data:');
                        console.log(data);
                        this.$store.dispatch('editPractice', data).then(response => {
                            console.log('received data from store editPractice: ');
                            console.log(response);
                            this.editPractice.practiceCategory = this.getSelectedPracticeCategory();
                            console.log('updated practice category of editPractice:');
                            console.log(this.editPractice);
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
            getSelectedPracticeCategory() {
              let category = this.practiceCategories.find(category => { return category._links.self.href === this.selectedPracticeCategory});
              console.log('getSelectedPracticeCategory');
              console.log(category);
              return category;
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