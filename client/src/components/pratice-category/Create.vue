<template>
    <v-layout>
        <v-flex xs4>
            <panel title="Add Practice Category">
                <v-card-text>
                    <v-form ref="form">
                        <v-text-field v-model="name" v-on:keyup="keyEvent" prepend-icon="person" name="name" label="Name" type="text" required :rules="required"></v-text-field>
                        <v-alert :value="validationerror" color="error" v-html="error"></v-alert>
                    </v-form>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="indigo" dark @click="create">Add</v-btn>
                    <v-btn color="indigo" dark @click="navigateTo({
                                name: 'practice-category.index'
                            })">Cancel</v-btn>
                </v-card-actions>
            </panel>
        </v-flex>
        <!--<v-flex xs8>-->
            <!--<v-card-actions>-->
                <!--<v-spacer></v-spacer>-->
                <!--<v-btn color="indigo" dark @click="create">Add</v-btn>-->
            <!--</v-card-actions>-->
        <!--</v-flex>-->
    </v-layout>
    <!--<v-layout align-center justify-center>-->
        <!--<v-flex xs12 sm8 md4>-->
            <!--<v-card class="elevation-12">-->
                <!--<v-toolbar dark color="indigo">-->
                    <!--<v-toolbar-title>Add Practice Category</v-toolbar-title>-->
                    <!--<v-spacer></v-spacer>-->
                    <!--<v-tooltip right>-->
                        <!--<v-btn slot="activator" icon large href="https://codepen.io/johnjleider/pen/wyYVVj" target="_blank">-->
                            <!--<v-icon large>mdi-codepen</v-icon>-->
                        <!--</v-btn>-->
                        <!--<span>Codepen</span>-->
                    <!--</v-tooltip>-->
                <!--</v-toolbar>-->
                <!--<v-card-text>-->
                    <!--<v-form>-->
                        <!--<v-text-field v-model="name" prepend-icon="person" name="name" label="Name" type="text"></v-text-field>-->
                        <!--<v-alert :value="validationerror" color="error" v-html="error"></v-alert>-->
                    <!--</v-form>-->
                <!--</v-card-text>-->
                <!--<v-card-actions>-->
                    <!--<v-spacer></v-spacer>-->
                    <!--<v-btn color="indogo" dark @click="create">Add</v-btn>-->
                <!--</v-card-actions>-->
            <!--</v-card>-->
        <!--</v-flex>-->
    <!--</v-layout>-->
</template>

<script>
    // import PracticeCategoryService from '@/services/PracticeCategoryService';
    import api from '../../services/api';
    import Panel from '@/components/Panel';

    export default {
        data() {
            return {
                name: '',
                error: null,
                validationerror: false,
                // check if value exists or return required message
                required: [ (v) => !!v || 'This field is required']
            }
        },
        methods: {
            async create() {
                try {
                    if (this.$refs.form.validate()) {
                        console.log('Edit Practice Category');
                        let data = {
                            name: this.name
                        };
                        api.post('practice-category', data)
                            .then(response => {
                                this.validationerror = false;
                                console.log(response.data);
                                let category = response.data;
                                console.log(category);
                                this.$store.dispatch('addPracticeCategory', response.data);
                                this.$router.push('/practice-category');
                            }).catch(e => {
                            console.log('error adding practice category: ' + e);
                            console.log(e.response);
                            if (e.response.data.message) {
                                if (e.response.data.message.includes("Constraint")) {
                                    this.error = 'Practice Category already exists.';
                                } else {
                                    this.error = e.response.data.message;
                                }
                            }
                            this.validationerror = true;
                        });
                        // const response = await PracticeCategoryService.create({
                        //     name: this.name
                        // });
                        // this.validationerror = false;
                        // console.log(response.data);
                        // let category = response.data;
                        // console.log(category);
                        // this.$store.dispatch('addPracticeCategory', response.data);
                        // this.$router.push('/practice-category');
                    }
                } catch (e) {
                    console.log('catch error adding practice category: ' + e)
                    this.error = e;
                    this.validationerror = true;
                }
            },
            navigateTo(route) {
                this.$router.push(route);
            },
            keyEvent() {
                if (this.validationerror) {
                    this.validationerror = false;
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