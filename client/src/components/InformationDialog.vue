<template>
    <v-layout row justify-center>
        <v-dialog v-model="infoVisibility" persistent max-width="390">
            <v-card>
                <v-card-title :class="classType">
                    <v-icon large dark>{{iconType}}</v-icon>
                    <div class="pad"></div>
                    <slot name="title"></slot>
                </v-card-title>
                <v-card-text class="bold black--text">
                    <p><slot name="text"></slot></p>
                    <p><slot name="detail"></slot></p>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn class="bold" color="indigo" flat @click="infoAccept"><slot name="confirmButton"></slot></v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-layout>
</template>

<script>
    export default {
        name: 'InfoDialog',
        props: ['infoType', 'infoVisibility'],
        methods: {
            infoAccept() {
                this.$emit('infoAccept');
            }
        },
        computed: {
            classType() {
                if (this.infoType === 'error') {
                    return "red white--text headline";
                } else {
                    return "indigo white--text headline";
                }
            },
            iconType() {
                if (this.infoType === 'error') {
                    return "error";
                } else {
                    return "info";
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
