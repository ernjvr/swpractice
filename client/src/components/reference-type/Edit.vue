<template>
    <v-layout>
        <v-flex xs4>
            <panel :title="$t('edit_reference_type')">
                <v-card-text>
                    <v-form ref="form">
                        <v-text-field v-model="type.name" v-on:keyup="keyEvent" prepend-icon="person" name="name"
                                      :label="$t('name')" type="text" required :rules="required" :maxlength="100"></v-text-field>
                        <v-alert :value="validationError" color="error" v-html="error"></v-alert>
                    </v-form>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="indigo" dark @click="edit"><v-icon dark left>save</v-icon>{{ $t('save') }}</v-btn>
                    <v-btn color="indigo" dark @click="cancel"><v-icon dark left>cancel</v-icon>{{ $t('cancel') }}</v-btn>
                </v-card-actions>
            </panel>
        </v-flex>
    </v-layout>
</template>

<script>
    import api from '../../services/api';
    import Util from '../../common/util';
    import Panel from '@/components/Panel';
    import { il8n } from '../../il8n';

    export default {
        data() {
            return {
                type: {},
                error: null,
                validationError: false,
                // check if value exists or return required message
                required: [(v) => !!v || il8n.tc('field_required')]
            }
        },
        async mounted() {
            let href = this.$route.params.id;
            api.get(href)
                .then(response => {
                    console.log(href + ' get success: ' + response.data);
                    this.type = response.data;
                }).catch(e => {
                console.log(href + ' get error: ' + e);
            });
        },
        methods: {
            async edit() {
                try {
                    if (this.$refs.form.validate()) {
                        let data = {
                            href: this.type._links.self.href,
                            type: {
                                name: this.type.name
                            }
                        };
                        this.$store.dispatch('editReferenceType', data).then(response => {
                            console.log('received data from store editReferenceType: ' + response);
                            this.type.name = response.name;
                            this.navigateToSingleView();
                        }, error => {
                            console.log('received error from store editReferenceType: ' + error);
                            this.error = error;
                            this.validationError = true;
                        });
                    }
                } catch (e) {
                    this.error = e.response.data.error;
                    this.validationError = true;
                }
            },
            cancel() {
                this.navigateToSingleView();
            },
            keyEvent() {
                if (this.validationError) {
                    this.validationError = false;
                }
            },
            navigateToSingleView() {
                this.$router.push('/reference-type/' + Util.encode(this.type._links.self.href));
            }
        },
        components: {
            Panel
        }
    }
</script>

<style scoped>

</style>