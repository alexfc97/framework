<template>
    <b-container fluid>
        <b-row>
            <b-col lg="6" class="my-1">
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
            <b-col lg="6" class="my-1">
                <b-form-group
                        label="Filter On: "
                        label-cols-sm="3"
                        label-align-sm="right"
                        label-size="md"
                        description="Leave all unchecked to filter on all data"
                        class="mb-0">
                    <b-form-checkbox-group
                            v-model="filterOn"
                            class="mt-1"
                            @change="download">
                            <b-form-checkbox v-for="key in tripFields" :key="key.key" :value="key.key">{{key.label}}</b-form-checkbox>
                    </b-form-checkbox-group>
                </b-form-group>
            </b-col>
        </b-row>
        <b-row class="mb-2">
            <b-col>
                <b-button @click="updateTable" variant="primary"> Update Table</b-button>
            </b-col>
            <b-col cols="7">
                <b-alert
                        :show="dismissCountDown"
                        dismissible
                        variant="success"
                        fade
                        @dismissed="dismissCountDown=0"
                        @dismiss-count-down="countDownChanged"
                >
                    Table has successfully been updated! This alert will dismiss after {{dismissCountDown}} second(s)...
                </b-alert>
            </b-col>
            <b-col>
                <download-csv
                        class="btn btn-primary offset-4"
                        :data="measurementData"
                        :name = 'downloadName'>
                    Download Data
                </download-csv>
            </b-col>
        </b-row>

        <b-table
            :items="measurementData"
            :fields="tripFields"
            :filter="filter"
            :filterIncludedFields="filterOn"
            striped
            hover
            sticky-header="400px"
            head-variant="dark"
            bordered
            small
            >
        </b-table>
    </b-container>
</template>

<script>
    import axios from 'axios'
    const measurementURL = 'http://localhost:8081/measurements/api/measurements'
    const TypeURL = 'http://localhost:8081//measurementtypes/api/measurementtypes'
    const deviceURL = 'http://localhost:8081/devices/api/devices'

    export default {
        data() {
            return {
                downloadName: 'idk',
                filter: null,
                filterOn: [],
                measurementData: [
                ],
                deviceData:[
                ],
                typeData:[
                ],
                tripFields: [
                    {
                        key: 'measurementid', label: 'Measurement ID', sortable: true
                    },
                    {
                        key: 'tripId', label: 'Trip ID', sortable: true
                    },
                    {
                        key: 'time', label: 'Timestamp', sortable: true
                    },
                    {
                        key: 'value', label: 'Value', sortable: true
                    },
                    {
                        key: 'latitude', label: 'Latitude', sortable: true
                    },
                    {
                        key: 'longitude', label: 'Longitude', sortable: true
                    },
                    {
                        key: 'type', label: 'Type', sortable: true
                    }
                ],
                dismissSecs: 5,
                dismissCountDown: 0
            }
        },
        methods: {
            download() {
                if (this.filterOn.length){
                    this.downloadName = '' + this.filterOn.join("+") + '_data' + '.csv'
                    console.log(this.downloadName)
                } else {
                    this.downloadName = '' + '_data' + '.csv'
                    console.log(this.downloadName)
                }
            },
            showAlert(){
                this.dismissCountDown = this.dismissSecs
            },
            countDownChanged(dismissCountDown) {
                this.dismissCountDown = dismissCountDown
            },
            updateTable() {



                this.showAlert()
            }
        },
        async created() {
            try {
                const resmeas = await axios.get(measurementURL)
                const resdev = await axios.get(deviceURL)
                const restypes = await axios.get(TypeURL)
                this.measurementData = resmeas.data
                this.deviceData = resdev.data
                this.typeData = restypes.data
                console.log(this.deviceData)
                console.log(this.typeData)
            } catch (e) {
                console.error(e)
            }
        },
        props: {
            PickedTable: String
        }
    }
</script>

<style scoped>

</style>