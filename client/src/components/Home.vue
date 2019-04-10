<template>
    <div>
        <h2>{{ title }}</h2>
        <br>
        <p v-html="description"> {{ description }}</p>
        <br>
        <h2>{{ background_title }}</h2>
        <br>
        <p v-html="background_description">{{ background_description }}</p>
    </div>
</template>

<script>
    import { il8n } from '../il8n';

    export default {
        data() {
            return {
                title: il8n.tc('home_title'),
                description: il8n.tc('home_description'),
                background_title: il8n.tc('background_title'),
                background_description: il8n.tc('background_description'),
            }
        }
    }
</script>

<style scoped>

</style>