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
    </v-layout>
</template>

<script>
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
                        console.log('Create Practice Category');
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
                            if (e.response.data) {
                                this.error = e.response.data;
                            }
                            this.validationerror = true;
                        });
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