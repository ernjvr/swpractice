<template>
    <div>
        <h1>{{ name }}</h1>
    </div>
</template>

<script>
    import { il8n } from '../il8n';

    export default {
        data() {
            return {
                name: il8n.tc('home')
            }
        }
    }
</script>

<style scoped>

</style>