<template>
    <v-container>
        <v-layout>
            <v-flex xs12>
                <panel :title="$t('edit_practice_category')">
                    <v-card-text>
                        <v-form ref="form">
                            <v-text-field v-model="category.name" prepend-icon="person" name="name" autofocus
                                          :label="$t('name')" type="text" required :rules="required" :maxlength="50"></v-text-field>
                        </v-form>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="indigo" dark @click="edit"><v-icon dark left>save</v-icon>{{ $t('save') }}</v-btn>
                        <v-btn color="indigo" dark @click="navigateTo({name: 'practice-category.show'})">
                            <v-icon dark left>cancel</v-icon>{{ $t('cancel') }}
                        </v-btn>
                    </v-card-actions>
                </panel>
            </v-flex>
        </v-layout>
        <info-dialog :info-visibility="infoDialog.infoVisibility"
                     :info-type="infoDialog.infoType"
                     @infoAccept="accept({name: 'practice-category.index'})">
            <template slot="title">{{ infoDialog.title }}</template>
            <template slot="text">{{ infoDialog.text }}</template>
            <template slot="detail">{{ infoDialog.detail }}</template>
            <template slot="confirmButton">{{ $t('close')}}</template>
        </info-dialog>
    </v-container>
</template>

<script>
    import util from '@/common/util';
    import Panel from '@/components/Panel';
    import { il8n } from '@/il8n';
    import InfoDialog from '@/components/dialog/InformationDialog';

    export default {
        components: {
            Panel,
            InfoDialog
        },
        data() {
            return {
                category: {},
                infoDialog: util.infoDialog,
                navigateToIndexPage: false,
                // check if value exists or return required message
                required: [(v) => !!v || il8n.tc('field_required')]
            }
        },
        mounted() {
            let category = this.$store.state.selectedPracticeCategory;

            if(category.name) {
                // avoid modifying store object directly by copying values into new object
                this.category = {
                    name: category.name,
                    _links: category._links
                };
            } else {
                console.log('selected practice category not found');
                this.navigateTo({name: 'practice-category.index'});
            }
        },
        methods: {
            navigateTo: util.navigateTo,
            accept: util.acceptInfoDialog,
            displayEditError: util.displayEditError,
            async edit() {
                try {
                    if (this.$refs.form.validate()) {
                        let data = {
                            href: this.category._links.self.href,
                            category: {
                                name: this.category.name
                            }
                        };
                        this.$store.dispatch('editPracticeCategory', data).then(response => {
                            console.log('received data from store editPracticeCategory: ' + response);
                            this.$store.commit('setSelectedPracticeCategory', this.category);
                            this.navigateTo({name: 'practice-category.show'});
                        }, error => {
                            console.log('received error from store editPracticeCategory: ' + error);
                            this.displayEditError(error, 'error_not_found_practice_category_text',
                                'error_not_found_practice_category_detail');
                        });
                    }
                } catch (e) {
                    util.displayInfoDialog('error', this.$t('error_delete_title'), e.response.data.error,
                        this.$t('error_unknown_detail'));
                }
            },
        },
    }
</script>

<style scoped>

</style>