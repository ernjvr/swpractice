<template>
    <v-container fluid grid-list-xl>
        <v-layout row>
            <v-flex xs6>
                <panel :title="$t('context_dimension')">
                    <v-card-text>
                        <v-form ref="form">
                            <v-text-field v-model="dimension.name" readonly prepend-icon="person" name="name"
                                          :label="$t('name')" type="text"></v-text-field>
                        </v-form>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="indigo" dark @click="navigateTo({
                                name: 'context-dimension.edit',
                                params: {id: dimension._links.self.href}
                            })">{{ $t('edit')}}</v-btn>
                        <v-btn color="indigo" dark @click="deleteConfirmationVisibility=true">{{ $t('delete')}}</v-btn>
                        <v-btn color="indigo" dark @click="navigateTo({name: 'context-dimension.index'})">{{ $t('return')}}</v-btn>
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
    </v-container>
</template>

<script>
    import api from '../../services/api';
    import Panel from "@/components/Panel";
    import ConfirmDialog from '@/components/ConfirmDialog'

    export default {
        components: {
            Panel,
            ConfirmDialog
        },
        data() {
            return {
                dimension: {},
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
                });
            }
        }
    }
</script>

<style scoped>

</style>