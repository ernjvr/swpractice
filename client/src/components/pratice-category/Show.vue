<template>
    <v-container>
        <v-layout>
            <v-flex xs12>
                <panel :title="$t('practice_category')">
                    <v-card-text>
                        <v-form ref="form">
                            <v-text-field v-model="category.name" readonly prepend-icon="person" name="name"
                                          :label="$t('name')" type="text"></v-text-field>
                        </v-form>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="indigo" dark @click="navigateTo({name: 'practice-category.index'})">
                            <v-icon dark left>arrow_back</v-icon>{{ $t('return')}}
                        </v-btn>
                        <v-btn color="indigo" dark @click="navigateTo({name: 'practice-category.edit'})">
                            <v-icon dark left>edit</v-icon>{{ $t('edit')}}
                        </v-btn>
                        <v-btn color="indigo" dark @click="deleteConfirmationVisibility=true">
                            <v-icon dark left>delete</v-icon>{{ $t('delete')}}
                        </v-btn>
                    </v-card-actions>
                </panel>
            </v-flex>
        </v-layout>
        <confirm-dialog :confirm-visibility="deleteConfirmationVisibility"
                        @confirmCancel="deleteConfirmationVisibility=false"
                        @confirmAccept="deleteCategory">
            <template slot="title">{{ $t('confirm_delete_title')}}</template>
            <template slot="text">{{ $t('confirm_delete_text')}}</template>
            <template slot="confirmButton">{{ $t('delete')}}</template>
        </confirm-dialog>
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
    import api from '@/services/api';
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
                category: {},
                infoDialog: util.infoDialog,
                navigateToIndexPage: false,
                deleteConfirmationVisibility: false
            }
        },
        mounted() {
            let category = this.$store.state.selectedPracticeCategory;

            if(category.name) {
                this.category = category;
            } else {
                console.log('selected practice category not found');
                this.navigateTo({name: 'practice-category.index'});
            }
        },
        methods: {
            navigateTo: util.navigateTo,
            accept: util.acceptInfoDialog,
            displayDeleteError: util.displayDeleteError,
            async deleteCategory() {
                let href = this.category._links.self.href;
                api.delete(href)
                    .then(response => {
                        console.log(href + ' delete success: ' + response);
                        this.navigateTo({name: 'practice-category.index'});
                    }).catch(e => {
                    console.log(href + ' delete error: ' + e);
                    this.displayDeleteError(e, 'error_delete_practice_category_text',
                        'error_delete_practice_category_detail',
                        'error_not_found_practice_category_text', 'error_not_found_practice_category_detail');
                    this.deleteConfirmationVisibility = false;
                });
            },
        }
    }
</script>

<style scoped>

</style>