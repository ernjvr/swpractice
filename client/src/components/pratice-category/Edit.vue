<template>
    <v-layout>
        <v-flex xs4>
            <panel title="Edit Practice Category">
                <v-card-text>
                    <v-form ref="form">
                        <v-text-field v-model="category.name" v-on:keyup="keyEvent" prepend-icon="person" name="name" label="Name" type="text" required :rules="required"></v-text-field>
                        <v-alert :value="validationerror" color="error" v-html="error"></v-alert>
                    </v-form>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="indigo" dark @click="edit">Save</v-btn>
                    <v-btn color="indigo" dark @click="cancel">Cancel</v-btn>
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
                category: {},
                error: null,
                validationerror: false,
                // check if value exists or return required message
                required: [ (v) => !!v || 'This field is required']
            }
        },
        async mounted() {
            console.log(this.$route.params);
            api.get(this.$route.params.id)
                .then(response => {
                    this.category = response.data;
                    console.log(response.data);
                }).catch(e => {
                console.log(e);
            });
        },
        methods: {
            async edit() {
                try {
                    if (this.$refs.form.validate()) {
                        console.log('Edit Practice Category');
                        console.log(this.category);
                            let data = {
                                name: this.category.name
                            };

                        api.put(this.category._links.self.href, data)
                            .then(response => {
                                this.category.name = response.data.name;
                                console.log('Practice Category Edit: response: ' + response.data);
                                this.$store.dispatch('editPracticeCategory', this.category);

                                let encoded = this.category._links.self.href.replace(/\//g, "%2F");
                                console.log(encoded);
                                this.$router.push('/practice-category/' + encoded);
                            }).catch(e => {
                            console.log('error adding practice category: ' + e);
                            if (e.response.data) {
                                this.error = e.response.data;
                            }
                            this.validationerror = true;
                        });
                    }
                } catch (e) {
                    this.error = e.response.data.error;
                    this.validationerror = true;
                }
            },
            cancel() {
                let encoded = this.category._links.self.href.replace(/\//g, "%2F");
                console.log(encoded);
                this.$router.push('/practice-category/' + encoded);
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