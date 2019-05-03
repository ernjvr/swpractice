<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-layout column>
        <v-flex xs8>
            <panel :title="$t('reference_type')">
                <v-btn slot="action" class="indigo accent-2" light medium absolute
                       right middle fab @click="navigateTo({name: 'reference-type.create'})">
                    <v-icon>add</v-icon>
                </v-btn>
                <v-data-table :headers="headers" :items="types" class="elevation-1">
                    <template v-slot:items="props">
                        <td>{{ props.item.name }}</td>
                        <td>
                            <v-btn color="indigo" dark @click="navigateTo({
                                name: 'reference-type.show',
                                params: {id: props.item._links.self.href}
                            })">{{ $t('view')}}</v-btn>
                        </td>
                    </template>
                </v-data-table>
            </panel>
        </v-flex>
    </v-layout>
</template>

<script>
    import Panel from "@/components/Panel";
    import constants from '../../common/constants';

    export default {
        components: {
            Panel
        },
        data() {
            return {
                types: [],
                headers: constants.reference_type_headers
            }
        },
        async mounted() {
            this.$store.dispatch('getAllReferenceTypes').then(response => {
                console.log('received data from store getAllReferenceTypes: ' + response);
                this.types = response;
            }, error => {
                console.log('received error from store getAllReferenceTypes: ' + error);
            });
        },
        methods: {
            navigateTo(route) {
                this.$router.push(route);
            }
        }
    }
</script>

<style scoped>

</style>