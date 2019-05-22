<template>
    <v-layout>
        <v-flex xs12>
            <panel :title="$t('add_context_dimension')">
                <v-card-text>
                    <v-form ref="form">
                        <v-text-field v-model="name" v-on:keyup="keyEvent" prepend-icon="person" name="name"
                                      :label="$t('name')" type="text" required :rules="required" :maxlength="100"></v-text-field>
                        <v-alert :value="validationError" color="error" v-html="error"></v-alert>
                    </v-form>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="indigo" dark @click="create"><v-icon dark left>add</v-icon>{{ $t('add') }}</v-btn>
                    <v-btn color="indigo" dark @click="navigateTo({name: 'context-dimension.index'})">
                        <v-icon dark left>cancel</v-icon>{{ $t('cancel') }}
                    </v-btn>
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
                        this.$store.dispatch('addContextDimension', data).then(response => {
                            console.log('received data from store addContextDimension: ' + response);
                            this.validationError = false;
                            this.navigateTo({name: 'context-dimension.index'});
                        }, error => {
                            console.log('received error from store addContextDimension: ' + error);
                            this.error = error;
                            this.validationError = true;
                        });
                    }
                } catch (e) {
                    console.log('catch error adding context dimension: ' + e);
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