<template>
    <v-layout>
        <v-flex xs4>
            <panel :title="$t('add_context_value')">
                <v-card-text>
                    <v-form ref="form">
                        <v-text-field v-model="data.value" v-on:keyup="keyEvent" prepend-icon="person" name="value"
                                      :label="$t('value')" type="text" required :rules="required" :mask="valueMask"></v-text-field>
                        <v-textarea v-model="data.description" v-on:keyup="keyEvent" prepend-icon="person" name="description"
                                    :label="$t('description')" type="text" required :rules="required" :maxlength="500"></v-textarea>
                        <v-alert :value="validationError" color="error" v-html="error"></v-alert>
                    </v-form>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="indigo" dark @click="create"><v-icon dark left>add</v-icon>{{ $t('add') }}</v-btn>
                    <v-btn color="indigo" dark @click="navigateTo({name: 'context-value.index'})">
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
                data: {
                    value: '',
                    description: ''
                },
                valueMask: '#########',
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
                        this.$store.dispatch('addContextValue', this.data).then(response => {
                            console.log('received data from store addContextValue: ' + response);
                            this.validationError = false;
                            this.navigateTo({name: 'context-value.index'});
                        }, error => {
                            console.log('received error from store addContextValue: ' + error);
                            this.error = error;
                            this.validationError = true;
                        });
                    }
                } catch (e) {
                    console.log('catch error adding context value: ' + e);
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