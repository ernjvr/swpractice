<template>
    <v-layout>
        <v-flex xs4>
            <panel :title="$t('add_practice_category')">
                <v-card-text>
                    <v-form ref="form">
                        <v-text-field v-model="name" v-on:keyup="keyEvent" prepend-icon="person" name="name"
                                      :label="$t('name')" type="text" required :rules="required" :maxlength="50"></v-text-field>
                        <v-alert :value="validationError" color="error" v-html="error"></v-alert>
                    </v-form>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="indigo" dark @click="create">{{ $t('add') }}</v-btn>
                    <v-btn color="indigo" dark @click="navigateTo({
                                name: 'practice-category.index'
                            })">{{ $t('cancel') }}</v-btn>
                </v-card-actions>
            </panel>
        </v-flex>
    </v-layout>
</template>

<script>
    import Panel from '@/components/Panel';
    import { il8n } from '../../il8n';

    export default {
        data() {
            return {
                name: '',
                error: null,
                validationError: false,
                // check if value exists or return required message
                required: [(v) => !!v || il8n.tc('field_required')]
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