<template>
    <panel :title="$t('add_reference_source_type')">
        <v-card-text>
            <v-form ref="form">
                <v-text-field v-model="data.name" prepend-icon="person" name="name" :label="$t('name')" autofocus
                              v-on:keyup.enter="create" type="text" required :rules="required" :maxlength="100"></v-text-field>
            </v-form>
        </v-card-text>
        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="indigo" dark @click="create"><v-icon dark left>add</v-icon>{{ $t('add') }}</v-btn>
            <v-btn color="indigo" dark @click="close">
                <v-icon dark left>cancel</v-icon>{{ $t('cancel') }}
            </v-btn>
        </v-card-actions>
        <info-dialog :info-visibility="infoDialog.infoVisibility"
                     :info-type="infoDialog.infoType"
                     @infoAccept="infoDialog.infoVisibility=false">
            <template slot="title">{{ infoDialog.title }}</template>
            <template slot="text">{{ infoDialog.text }}</template>
            <template slot="detail">{{ infoDialog.detail }}</template>
            <template slot="confirmButton">{{ $t('close')}}</template>
        </info-dialog>
    </panel>
</template>

<script>
    import Panel from '@/components/Panel';
    import util from '@/common/util';
    import { il8n } from '@/il8n';
    import InfoDialog from '@/components/dialog/InformationDialog';

    export default {

        components: {
            Panel,
            InfoDialog
        },
        data() {
            return {
                data: {
                    name: ''
                },
                infoDialog: util.infoDialog,
                // check if value exists or return required message
                required: [(v) => !!v || il8n.tc('field_required')]
            }
        },
        methods: {
            showDialog: util.showDialog,
            displayAddError: util.displayAddError,
            async create() {
                try {
                    if (this.$refs.form.validate()) {
                        this.$store.dispatch('addReferenceSourceType', this.data).then(response => {
                            console.log('received data from store addReferenceSourceType: ' + response);
                            this.close();
                        }, error => {
                            console.log('received error from store addReferenceSourceType: ' + error);
                            this.displayAddError(error);
                        });
                    }
                } catch (e) {
                    console.log('catch error adding reference type: ' + e);
                    this.error = e;
                    this.validationError = true;
                }
            },
            close() {
                this.data.name = '';
                this.showDialog(false);
            }
        },
    }
</script>

<style scoped>

</style>