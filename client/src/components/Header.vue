<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-toolbar fixed class="indigo" dark>
        <v-toolbar-title class="mr-4" dark @click="navigateTo({ name: 'home' })">{{ $t('home') }}</v-toolbar-title>

        <v-toolbar-items>
            <v-menu open-on-hover transition="slide-y-transition" bottom>
                <template v-slot:activator="{ on }">
                    <v-btn class="menu" color="primary" dark v-on="on">{{ $t('practice') }}</v-btn>
                </template>
                <v-list>
                    <v-list-tile class="menu" v-for="item in items" :key="item.name" @click="navigateTo({ name: item.name })">
                        <v-list-tile-title class="menu-item mr-4">{{ item.title }}</v-list-tile-title>
                    </v-list-tile>
                </v-list>
            </v-menu>
        </v-toolbar-items>

        <v-spacer></v-spacer>
    </v-toolbar>
</template>

<script>
    import { il8n } from '../il8n';

    export default {
        data: () => ({
            items: [
                { title: il8n.tc('practice'), name: 'practice.index' },
                { title: il8n.tc('practice_category'), name: 'practice-category.index' },
                { title: il8n.tc('practice_sub_category'), name: 'practice-sub-category.index' },
            ]
        }),
        methods: {
            navigateTo(route) {
                this.$router.push(route);
            }
        }
    }
</script>

<style scoped>
    .mr-4 {
        cursor: pointer;
    }
    .mr-4:hover {
        color: black;
        background-color: white;
    }
    .menu {
        color: white;
        background-color: blue;
        border: 1px white;

    }
    .menu-item {
        color: white;
        border: white;
        background-color: blue;
        height: 30px;
        padding: 5px;
    }
</style>