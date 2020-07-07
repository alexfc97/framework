<template>
    <div id="main">
        <SelectTable class="select" v-on:childToParent="onTableClick"/>
        <Table :PickedTable="tablePicked"></Table>
    </div>
</template>

<script>
    import SelectTable from "./SelectTable";
    import Table from "./Table";

    export default {
        name: "mainPage",
        data() {
            return {
                tablePicked: '',
                trip: 'something',
            }
        },
        components: {
            Table,
            SelectTable
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