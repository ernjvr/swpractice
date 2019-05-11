<template>
    <v-container fluid grid-list-xl>
        <v-layout row>
            <v-flex xs6>
                <panel :title="$t('context_value')">
                    <v-card-text>
                        <v-form ref="form">
                            <v-text-field v-model="value.value" readonly prepend-icon="person" name="value"
                                          :label="$t('value')" type="text"></v-text-field>
                            <v-textarea v-model="value.description" readonly prepend-icon="person" name="description"
                                        :label="$t('description')" type="text"></v-textarea>
                        </v-form>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="indigo" dark @click="navigateTo({
                                name: 'context-value.edit',
                                params: {id: value._links.self.href}
                            })">{{ $t('edit')}}</v-btn>
                        <v-btn color="indigo" dark @click="deleteConfirmationVisibility=true">{{ $t('delete')}}</v-btn>
                        <v-btn color="indigo" dark @click="navigateTo({name: 'context-value.index'})">{{ $t('return')}}</v-btn>
                    </v-card-actions>
                </panel>
            </v-flex>
        </v-layout>
        <confirm-dialog :confirm-visibility="deleteConfirmationVisibility"
                        @confirmCancel="deleteConfirmationVisibility=false"
                        @confirmAccept="deleteValue">
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
                value: {},
                deleteConfirmationVisibility: false
            }
        },
        async mounted() {
            let href = this.$route.params.id;
            api.get(href)
                .then(response => {
                    console.log(href + ' get success: ' + response.data);
                    this.value = response.data;
                }).catch(e => {
                console.log(href + ' get error: ' + e);
            });
        },
        methods: {
            navigateTo(route) {
                this.$router.push(route);
            },
            async deleteValue() {
                let href = this.value._links.self.href;
                api.delete(href)
                    .then(response => {
                        console.log(href + ' delete success: ' + response);
                        this.navigateTo({name: 'context-value.index'});
                    }).catch(e => {
                    console.log(href + ' delete error: ' + e);
                });
            }
        }
    }
</script>

<style scoped>

</style>