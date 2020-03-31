<template>
    <div align="center" class="table-group">
        <div class="w-75">
            <h1 align="center">Trip Data</h1>
            <b-table align="center" sticky-header="500px" bordered small striped hover :items="tripData" :fields="fields"></b-table>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';


    const baseURL = 'http://localhost:8081/trip/api/triplist';

    export default {
        name: "Tables",
        data() {
            return {
                tripData: [],
                fields: [
                    {
                        key:'id',
                        sortable:true
                    },{
                        key: 'sensor',
                        sortable:true
                    },{
                        key: 'time',
                        sortable:true
                    },{
                        key: 'type',
                        sortable:true
                    },{
                        key: 'value',
                        sortable:true
                    },{
                        key: 'latitude',
                        sortable:true
                    },{
                        key: 'longitude',
                        sortable:true
                    }]
            }
        },
        async created() {
            try {
                const res = await axios.get(baseURL);
                this.tripData = res.data;
                console.log(res);
            } catch (e) {
                console.error(e)
            }
        }
    }
</script>

<style>

</style>