<template>
    <b-container fluid>
        <!-- User Interface controls -->
        <b-row>
            <b-col class="col-5 offset-3 mt-2">
                <b-form-group
                        label="Filter"
                        label-cols-sm="3"
                        label-align-sm="right"
                        label-size="sm"
                        label-for="filterInput"
                        class="mb-0"
                >
                    <b-input-group size="sm">
                        <b-form-input
                                v-model="filter"
                                type="search"
                                id="filterInput"
                                placeholder="Type to Search"
                        ></b-form-input>
                        <b-input-group-append>
                            <b-button :disabled="!filter" @click="filter = ''">Clear</b-button>
                        </b-input-group-append>
                    </b-input-group>
                </b-form-group>
            </b-col>
        </b-row>

        <!-- Main table element -->
        <b-table
                small
                striped
                hover
                sticky-header="500px"
                :items="items"
                :fields="fields"
                :filter="filter"
                class="mt-2"
        >
        </b-table>
    </b-container>
</template>

<script>
    import axios from 'axios';

    const measurementURL = 'http://localhost:8081/measurements/api/measurements';
    // const sensorURL = 'http://localhost:8081/sensors/api/sensors';

    export default {
        name: "Tables",
        props:{
            table: String
        },
        data() {
            return {
                items: [
                {
                    "measurementid":69,
                    "tripid":420,
                    "time":"2020-04-06T07:14:01.071",
                    "type":"Temperature",
                    "value": 120,
                    "latitude":32.68741,
                    "longitude":25.01773
                },
                {
                    "measurementid":100,
                    "tripid":200,
                    "time":"2020-04-06T07:14:01.071",
                    "type":"Temperature",
                    "value": 300,
                    "latitude":32.01773,
                    "longitude":25.01773
            },{
                    "measurementid":69,
                    "tripid":420,
                    "time":"2020-04-06T07:14:01.071",
                    "type":"Temperature",
                    "value": 120,
                    "latitude":32.68741,
                    "longitude":25.01773
                },
                {
                    "measurementid":100,
                    "tripid":200,
                    "time":"2020-04-06T07:14:01.071",
                    "type":"Temperature",
                    "value": 300,
                    "latitude":32.01773,
                    "longitude":25.01773
                },{
                    "measurementid":69,
                    "tripid":420,
                    "time":"2020-04-06T07:14:01.071",
                    "type":"Temperature",
                    "value": 120,
                    "latitude":32.68741,
                    "longitude":25.01773
                },
                {
                    "measurementid":100,
                    "tripid":200,
                    "time":"2020-04-06T07:14:01.071",
                    "type":"Temperature",
                    "value": 300,
                    "latitude":32.01773,
                    "longitude":25.01773
                },{
                    "measurementid":69,
                    "tripid":420,
                    "time":"2020-04-06T07:14:01.071",
                    "type":"Temperature",
                    "value": 120,
                    "latitude":32.68741,
                    "longitude":25.01773
                },
                {
                    "measurementid":100,
                    "tripid":200,
                    "time":"2020-04-06T07:14:01.071",
                    "type":"Temperature",
                    "value": 300,
                    "latitude":32.01773,
                    "longitude":25.01773
                },{
                    "measurementid":69,
                    "tripid":420,
                    "time":"2020-04-06T07:14:01.071",
                    "type":"Temperature",
                    "value": 120,
                    "latitude":32.68741,
                    "longitude":25.01773
                },
                {
                    "measurementid":100,
                    "tripid":200,
                    "time":"2020-04-06T07:14:01.071",
                    "type":"Temperature",
                    "value": 300,
                    "latitude":32.01773,
                    "longitude":25.01773
                },{
                    "measurementid":69,
                    "tripid":420,
                    "time":"2020-04-06T07:14:01.071",
                    "type":"Temperature",
                    "value": 120,
                    "latitude":32.68741,
                    "longitude":25.01773
                },
                {
                    "measurementid":100,
                    "tripid":200,
                    "time":"2020-04-06T07:14:01.071",
                    "type":"Temperature",
                    "value": 300,
                    "latitude":32.01773,
                    "longitude":25.01773
                },{
                    "measurementid":69,
                    "tripid":420,
                    "time":"2020-04-06T07:14:01.071",
                    "type":"Temperature",
                    "value": 120,
                    "latitude":32.68741,
                    "longitude":25.01773
                },
                {
                    "measurementid":100,
                    "tripid":200,
                    "time":"2020-04-06T07:14:01.071",
                    "type":"Temperature",
                    "value": 300,
                    "latitude":32.01773,
                    "longitude":25.01773
                },{
                    "measurementid":69,
                    "tripid":420,
                    "time":"2020-04-06T07:14:01.071",
                    "type":"Temperature",
                    "value": 120,
                    "latitude":32.68741,
                    "longitude":25.01773
                    }],
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
                    }],
                filter: null,
                filterOn: []
            }
        },
        async created() {
            try {
                const res = await axios.get(measurementURL);
                this.tripData = res.data;
                console.log(res);
            } catch (e) {
                console.error(e)
            }
        }
    }
</script>

<style scoped>

</style>