<template>
    <div id="main">
        <SelectTable class="select"/>
        <Tables class="tables" table=""/>
    </div>
</template>

<script>
    import Tables from "./Tables";
    import SelectTable from "./SelectTable";

    export default {
        name: "main",
        components: {
            SelectTable,
            Tables
        }
    }
</script>

<style scoped>

</style>