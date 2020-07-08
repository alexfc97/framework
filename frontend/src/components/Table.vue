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
                            <b-form-checkbox v-for="key in Fields" :key="key.key" :value="key.key">{{key.label}}</b-form-checkbox>
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
                <b-row>
                    <b-button
                            v-b-modal.modaldownload
                            @click="download"
                            variant="primary">
                                Download Data
                    </b-button>
                </b-row>
                <b-modal id="modaldownload"
                         title="Download Page"
                         hide-footer>
                    <b-row>
                        <download-csv
                                class="btn btn-primary offset-4 mb-4"
                                :data   = "downloadData"
                                :fields = "jsonfields"
                                :name   = "downloadName"
                                separator-excel>
                            Download Data as CSV
                        </download-csv>
                    </b-row>
                    <b-row align="center">
                        <download-excel
                                class = "btn btn-primary offset-4"
                                :data = "downloadData"
                                :name = "downloadName">
                            Download Data as Excel
                        </download-excel>
                    </b-row>
                </b-modal>
            </b-col>
        </b-row>
        <b-table
            ref="table"
            :items="Data"
            :fields="Fields"
            :filter="filter"
            :filterIncludedFields="filterOn"
            striped
            hover
            sticky-header="400px"
            head-variant="dark"
            bordered
            small
            data-show-refresh=true
            show-empty
            refreshed
            v-model="downloadData"
            >
            <template v-slot:cell(mapReference)="row">
                <b-button size="sm" @click="row.toggleDetails" class="mr-2">
                    {{ row.detailsShowing ? 'Hide' : 'Show'}} Details
                </b-button>
            </template>
            <template v-slot:row-details="row">
                <b-card>
                    <b-row class="mb-2">
                        <b-col sm="3" class="text-sm-right"><b>Map Reference:</b></b-col>
                        <b-col v-if="row.item.mapReference == null">No map matched location has been set for this measurement!</b-col>
                        <b-col v-else>
                            <p>
                                Map Reference ID : {{row.item.mapReference.mapReferenceId}}
                            </p>
                            <p>
                                Map Reference Latitude : {{row.item.mapReference.mapmatched_latitude}}
                            </p>
                            <p>
                                Map Reference Longitude : {{row.item.mapReference.mapmatched_longitude}}
                            </p>
                        </b-col>
                    </b-row>
                </b-card>
            </template>
            <template v-slot:table-busy>
                <div class="text-center text-danger my-2">
                    <b-spinner class="align-middle"></b-spinner>
                    <strong>Loading...</strong>
                </div>
            </template>
        </b-table>
    </b-container>
</template>

<script>
    import axios from 'axios'
    const sourceURL = 'http://localhost:8081/sourcetypes/api/sourcetypes'
    const deviceURL = 'http://localhost:8081/devices/api/devices'
    const tripURL = 'http://localhost:8081/trips/api/trips'
    const measurementURL = 'http://localhost:8081/measurements/api/measurements'
    const TypeURL = 'http://localhost:8081//measurementtypes/api/measurementtypes'

    export default {
        data() {
            return {
                Data: [],
                Fields: [],
                jsonsourcetypefields: ["sourceTypeId","time","sourceTypeName"],
                jsondevicefields: ["deviceId","carId","time"],
                jsontripfields: ["tripId","startTime","endTime","distance","durartion","startPositionLon","startPositionLat","endPositionLon","endPositionLat"],
                jsonmeasurementfields: ["measurementId","time","value","latitude","longitude","tripid","type","mapReferenceLongitude","mapReferenceLatitude"],
                jsonmeasurementtypefields: ["type","unit"],
                jsonfields: [],
                downloadName: 'idk',
                filter: null,
                filterOn: [],
                sourceTypeData: [],
                deviceData: [],
                tripData: [],
                measurementData: [],
                typeData: [],
                sourceTypeFields:[
                    {
                        key: 'sourceTypeId', label: 'Source Type ID', sortable: true
                    },
                    {
                        key: 'time', label: 'Time', sortable: true
                    },
                    {
                        key: 'sourceTypeName', label: 'Name', sortable: true
                    }
                ],
                deviceFields:[
                    {
                        key: 'deviceId', label: 'Device ID',  sortable: true
                    },
                    {
                        key: 'carId', label: 'Car ID', sortable: true
                    },
                    {
                        key: 'time', label: 'Time', sortable: true
                    }
                ],
                tripFields:[
                    {
                        key:'tripId', label: 'Trip ID', sortable:true
                    },
                    {
                        key:'startPositionLat', label: 'Start Position (Latitude)', sortable:true
                    },
                    {
                        key:'startPositionLon', label: 'Start Position (Longitude)', sortable:true
                    },
                    {
                        key:'endPositionLat', label: 'End Position (Latitude)', sortable:true
                    },
                    {
                        key:'endPositionLon', label: 'End Position (Longitude)', sortable:true
                    },
                    {
                        key:'startTime', label: 'Start Time', sortable:true
                    },
                    {
                        key:'endTime', label: 'End Time', sortable:true
                    },

                ],
                measurementFields:[
                    {
                        key: 'measurementId', label: 'Measurement ID', sortable: true
                    },
                    {
                        key: 'tripId', label: 'Trip ID', sortable: true
                    },
                    {
                        key: 'time', label: 'Time', sortable: true
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
                    },
                    {
                        key: 'mapReferenceLatitude',  label: 'Map Matched Latitude', sortable: true
                    },
                    {
                        key: 'mapReferenceLongitude',  label: 'Map Matched Longitude', sortable: true
                    },
                ],
                typeFields:[
                    {
                        key: 'type', label: 'Type', sortable: true
                    },
                    {
                        key: 'unit', label: 'Unit', sortable: true
                    }
                ],
                dismissSecs: 3,
                dismissCountDown: 0,
                infoModal: {
                    id: 'info-modal',
                    title: '',
                    content: ''
                },
                isBusy: false,
                downloadData: []
            }
        },
        methods: {
            toggleBusy() {
                this.isBusy = !this.isBusy
            },
            download() {
                if (this.filterOn.length){
                        this.downloadName = '' + this.filterOn.join("+") + '_data' + '.csv'
                    console.log(this.downloadName)
                } else {
                    this.downloadName = 'data' + '.csv'
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
                this.toggleBusy()
                this.update()
                if (this.PickedTable === 'measurements'){
                    this.Data = this.measurementData
                    this.Fields = this.measurementFields
                    this.jsonfields = this.jsonmeasurementfields
                } else if (this.PickedTable === 'measurementTypes'){
                    this.Data = this.typeData
                    this.Fields = this.typeFields
                    this.jsonfields = this.jsonmeasurementtypefields
                } else if (this.PickedTable === 'trips') {
                    this.Data = this.tripData
                    this.Fields = this.tripFields
                    this.jsonfields = this.jsontripfields
                } else if (this.PickedTable === 'devices') {
                    this.Data = this.deviceData
                    this.Fields = this.deviceFields
                    this.jsonfields = this.jsondevicefields
                } else if (this.PickedTable === 'sourceTypes') {
                    this.Data = this.sourceTypeData
                    this.Fields = this.sourceTypeFields
                    this.jsonfields = this.jsonsourcetypefields
                }
                this.$refs.table.$forceUpdate()
                this.$refs.table.refresh()
                this.$root.$emit('bv::refresh::table', 'table')
                this.showAlert()
                this.toggleBusy()
            },
            exportData(items) {
                this.downloadData = items;
                console.log("Data updated for export!")
            },
            async update(){
                try {
                    const source = await axios.get(sourceURL)
                    const dev = await axios.get(deviceURL)
                    const trip = await axios.get(tripURL)
                    const meas = await axios.get(measurementURL)
                    const types = await axios.get(TypeURL)
                    this.sourceTypeData = source.data
                    this.deviceData = dev.data
                    this.tripData = trip.data
                    this.measurementData = meas.data
                    this.typeData = types.data
                    this.$refs.table.refresh()
                } catch (e) {
                    console.error(e)
                }
            }
        },
        created() {
            try {
                let source = axios.get(sourceURL)
                let dev = axios.get(deviceURL)
                let trip = axios.get(tripURL)
                let meas = axios.get(measurementURL)
                let types = axios.get(TypeURL)
                this.sourceTypeData = source.data
                this.deviceData = dev.data
                this.tripData = trip.data
                this.measurementData = meas.data
                this.typeData = types.data
                this.$refs.table.refresh()
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