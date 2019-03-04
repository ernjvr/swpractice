<template>
    <v-layout>
        <v-flex xs4>
            <panel title="Edit Practice Category">
                <v-card-text>
                    <v-form ref="form">
                        <v-text-field v-model="category.name" prepend-icon="person" name="name" label="Name" type="text" required :rules="required"></v-text-field>
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
        <!--<v-flex xs8>-->
        <!--<v-card-actions>-->
        <!--<v-spacer></v-spacer>-->
        <!--<v-btn color="indigo" dark @click="create">Add</v-btn>-->
        <!--</v-card-actions>-->
        <!--</v-flex>-->
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
            // console.log(this.$route.params.category);
            // this.category = this.$route.params.category;
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
                            console.log(e);
                            this.error = e.response.data.error;
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
                    this.error = e.response.data.error;
                    this.validationerror = true;
                }
            },
            cancel() {
                let encoded = this.category._links.self.href.replace(/\//g, "%2F");
                console.log(encoded);
                this.$router.push('/practice-category/' + encoded);
            }
        },
        components: {
            Panel
        }
    }
</script>

<style scoped>

</style>