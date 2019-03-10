<template>
    <v-layout>
        <v-flex xs4>
            <panel title="Add Practice Category">
                <v-card-text>
                    <v-form ref="form">
                        <v-text-field v-model="name" v-on:keyup="keyEvent" prepend-icon="person" name="name"
                                      label="Name" type="text" required :rules="required"></v-text-field>
                        <v-alert :value="validationError" color="error" v-html="error"></v-alert>
                    </v-form>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="indigo" dark @click="create">Add</v-btn>
                    <v-btn color="indigo" dark @click="navigateTo({
                                name: 'practice-category.index'
                            })">Cancel
                    </v-btn>
                </v-card-actions>
            </panel>
        </v-flex>
    </v-layout>
</template>

<script>
    import Panel from '@/components/Panel';

    export default {
        data() {
            return {
                name: '',
                error: null,
                validationError: false,
                // check if value exists or return required message
                required: [(v) => !!v || 'This field is required']
            }
        },
        methods: {
            async create() {
                try {
                    if (this.$refs.form.validate()) {
                        let data = {
                            name: this.name
                        };
                        this.$store.dispatch('addPracticeCategory', data).then(response => {
                            console.log('received data from store addPracticeCategory: ' + response);
                            this.validationError = false;
                            this.navigateTo('/practice-category');
                        }, error => {
                            console.log('received error from store addPracticeCategory: ' + error);
                            this.error = error;
                            this.validationError = true;
                        });
                    }
                } catch (e) {
                    console.log('catch error adding practice category: ' + e);
                    this.error = e;
                    this.validationError = true;
                }
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