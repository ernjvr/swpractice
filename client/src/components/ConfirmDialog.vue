<template>
    <v-layout row justify-center>
        <v-dialog v-model="confirmVisibility" persistent max-width="290">
            <v-card>
                <v-card-title :class="classType">
                    <v-icon large dark>thumb_up</v-icon>
                    <div class="pad"></div>
                    <slot name="title"></slot>
                </v-card-title>
                <v-card-text class="bold black--text"><slot name="text"></slot></v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn class="bold" color="indigo" flat @click="confirmCancel">{{ $t('cancel')}}</v-btn>
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
                    return "indigo white--text headline";
                }
            }
        }
    }
</script>

<style scoped>
    .bold {
        font-weight: bold;
    }
    .pad {
        padding: 3px
    }
</style>
