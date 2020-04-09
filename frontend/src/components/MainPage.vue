<template>
    <div id="main">
        <SelectTable class="select" v-on:childToParent="onTableClick"/>
        <!-- <Tables class="tables" :table="tablePicked" /> -->
        <Tables :table="tablePicked"></Tables>
    </div>
</template>

<script>
    //import Tables from "./Tables";
    import SelectTable from "./SelectTable";
    import Tables from "./Tables";

    export default {
        name: "mainPage",
        data() {
            return {
                tablePicked: 'This line is where to show the table you have picked'
            }
        },
        components: {
            SelectTable,
            Tables
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