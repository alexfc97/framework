<template>
    <div align="center" class="table-group">
        <div class="w-75">
            <h1 align="center">Trip Data</h1>
            <b-container>
                <b-table align="center" sticky-header="500px" bordered small striped hover :items="tripData" :fields="fields">
                    <b-tfoot>
                        <b-tr>
                            <b-td colspan="7" variant="secondary" class="text-right">
                                Total Rows: <b>5</b>
                            </b-td>
                        </b-tr>
                    </b-tfoot>
                </b-table>
            </b-container>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';


    const baseURL = 'http://localhost:8081/measurements/api/measurements';

    export default {
        name: "Tables",
        data() {
            return {
                tripData: [],
                fields: [
                    {
                        key:'measurementid',
                        sortable:true
                    },{
                        key: 'tripid',
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