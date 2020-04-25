<template>
    <div id="main">
        <SelectTable class="select" v-on:childToParent="onTableClick"/>
        <!-- <Tables class="tables" :table="tablePicked" /> -->
        <testing :PickedTable="tablePicked"></testing>

        <!--<Tables :table="tablePicked"></Tables>-->
    </div>
</template>

<script>
    //import Tables from "./Tables";
    import SelectTable from "./SelectTable";
/*    import Tables from "./Tables";*/
    import Testing from "./testing";

    export default {
        name: "mainPage",
        data() {
            return {
                tablePicked: '',
                trip: 'something',
            }
        },
        components: {
            Testing,
            SelectTable/*,
            Tables*/
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