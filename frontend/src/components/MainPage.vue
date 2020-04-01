<template>
    <div id="main">
        <SelectTable class="select" v-on:childToParent="onTableClick"/>
        <Tables class="tables" :table="tablePicked" />
    </div>
</template>

<script>
    import Tables from "./Tables";
    import SelectTable from "./SelectTable";

    export default {
        name: "main",
        data() {
            return {
                tablePicked: '',
            }
        },
        components: {
            SelectTable,
            Tables
        },
        methods: {
            onTableClick (value) {
                this.tablePicked = value
            }
        }
    }
</script>

<style scoped>

</style>