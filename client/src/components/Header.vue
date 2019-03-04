<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <v-toolbar fixed class="indigo" dark>
        <v-toolbar-title class="mr-4" dark @click="navigateTo({
                                name: 'home'
                            })">Home</v-toolbar-title>

        <v-toolbar-items>
            <!--<v-btn flat dark router to="/practice-category">Practice Category</v-btn>-->
            <v-menu open-on-hover transition="slide-y-transition" bottom>
                <template v-slot:activator="{ on }">
                    <v-btn class="purple" color="primary" dark v-on="on">
                        Practice
                    </v-btn>
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
    export default {
        data: () => ({
            items: [
                { title: 'PRACTICE', name: 'practice-category.index' },
                { title: 'PRACTICE CATEGORY', name: 'practice-category.index' },
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