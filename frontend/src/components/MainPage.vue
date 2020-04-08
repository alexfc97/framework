<template>
    <div id="main">
        <SelectTable class="select" v-on:childToParent="onTableClick"/>
        <!-- <Tables class="tables" :table="tablePicked" /> -->
        <TablesTesting/>
    </div>
</template>

<script>
    //import Tables from "./Tables";
    import SelectTable from "./SelectTable";
    import TablesTesting from "./TablesTesting";

    export default {
        name: "mainPage",
        data() {
            return {
                tablePicked: '',
            }
        },
        components: {
            SelectTable,
            TablesTesting
        },
        methods: {
            onTableClick (value) {
                this.tablePicked = value
                console.log(value);
            }
        }
    }
</script>

<style scoped>

</style>