<template>
    <v-container>
        <v-layout>
            <v-flex xs12>
                <panel :title="$t('context_dimension')">
                    <v-card-text>
                        <v-form ref="form">
                            <v-text-field v-model="dimension.name" readonly prepend-icon="person" name="name"
                                          :label="$t('name')" type="text"></v-text-field>
                        </v-form>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="indigo" dark @click="navigateTo({name: 'context-dimension.index'})">
                            <v-icon dark left>arrow_back</v-icon>{{ $t('return')}}
                        </v-btn>
                        <v-btn color="indigo" dark @click="navigateTo({
                        name: 'context-dimension.edit',
                        params: {id: dimension._links.self.href}
                        })"><v-icon dark left>edit</v-icon>{{ $t('edit')}}</v-btn>
                        <v-btn color="indigo" dark @click="deleteConfirmationVisibility=true">
                            <v-icon dark left>delete</v-icon>{{ $t('delete')}}
                        </v-btn>
                    </v-card-actions>
                </panel>
            </v-flex>
        </v-layout>
        <confirm-dialog :confirm-visibility="deleteConfirmationVisibility"
                        @confirmCancel="deleteConfirmationVisibility=false"
                        @confirmAccept="deleteDimension">
            <template slot="title">{{ $t('confirm_delete_title')}}</template>
            <template slot="text">{{ $t('confirm_delete_text')}}</template>
            <template slot="confirmButton">{{ $t('delete')}}</template>
        </confirm-dialog>
        <info-dialog :info-visibility="infoDialog.infoVisibility"
                     :info-type="infoDialog.infoType"
                     @infoAccept="infoDialog.infoVisibility=false">
            <template slot="title">{{ infoDialog.title }}</template>
            <template slot="text">{{ infoDialog.text }}</template>
            <template slot="detail">{{ infoDialog.detail }}</template>
            <template slot="confirmButton">{{ $t('close')}}</template>
        </info-dialog>
    </v-container>
</template>

<script>
    import api from '../../services/api';
    import util from '@/common/util';
    import Panel from "@/components/Panel";
    import ConfirmDialog from '@/components/dialog/ConfirmDialog';
    import InfoDialog from '@/components/dialog/InformationDialog';

    export default {
        components: {
            Panel,
            ConfirmDialog,
            InfoDialog
        },
        data() {
            return {
                dimension: {},
                infoDialog: util.infoDialog,
                deleteConfirmationVisibility: false
            }
        },
        async mounted() {
            let href = this.$route.params.id;
            api.get(href)
                .then(response => {
                    console.log(href + ' get success: ' + response.data);
                    this.dimension = response.data;
                }).catch(e => {
                console.log(href + ' get error: ' + e);
            });
        },
        methods: {
            navigateTo(route) {
                this.$router.push(route);
            },
            async deleteDimension() {
                let href = this.dimension._links.self.href;
                api.delete(href)
                    .then(response => {
                        console.log(href + ' delete success: ' + response);
                        this.navigateTo({name: 'context-dimension.index'});
                    }).catch(e => {
                    console.log(href + ' delete error: ' + e);

                    if (e.response.data.toLowerCase().includes('integrity violation')) {
                        this.infoDialog.title = this.$t('error_delete_title');
                        this.infoDialog.text = this.$t('error_delete_context_dimension_text');
                        this.infoDialog.detail = this.$t('error_delete_context_dimension_detail');
                    } else {
                        this.infoDialog.title = this.$t('error_delete_title');
                        this.infoDialog.text = this.$t('error_unknown_text');
                        this.infoDialog.detail = this.$t('error_unknown_detail');
                    }
                    this.deleteConfirmationVisibility = false;
                    this.infoDialog.infoType = 'error';
                    this.infoDialog.infoVisibility = true;
                });
            }
        }
    }
</script>

<style scoped>

</style>