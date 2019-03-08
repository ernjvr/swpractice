<template>
    <v-layout row justify-center>
        <v-dialog v-model="confirmVisibility" persistent max-width="290">
            <v-card>
                <v-card-title :class="classType"><slot name="title"></slot></v-card-title>
                <v-card-text><slot name="text"></slot></v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn class="bold" color="indigo" flat @click="confirmCancel">Cancel</v-btn>
                    <v-btn class="bold" color="indigo" flat @click="confirmAccept"><slot name="confirmButton"></slot></v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-layout>
</template>

<script>
    export default {
        name: 'ConfirmDialog',
        props: ['confirmType', 'confirmVisibility'],
        methods: {
            confirmCancel() {
                this.$emit('confirmCancel');
            },
            confirmAccept() {
                this.$emit('confirmAccept');
            }
        },
        computed: {
            classType() {
                if (this.confirmType === 'error') {
                    return "error headline";
                } else {
                    return "headline";
                }
            }
        }
    }
</script>

<style scoped>
    .bold {
        font-weight: bold;
    }
</style>
