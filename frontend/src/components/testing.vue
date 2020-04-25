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
                <download-csv
                        class="btn btn-primary offset-4"
                        :data="measurementData"
                        :name = 'downloadName'>
                    Download Data
                </download-csv>
            </b-col>
        </b-row>
        <b-table
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
                        <b-col v-else>{{ row.item.mapReference}}</b-col>
                    </b-row>
                </b-card>
            </template>
        </b-table>

        <b-modal :id="infoModal.id" :title="infoModal.title" ok-only @hide="resetInfoModal">
            <pre>{{ infoModal.content }}</pre>
        </b-modal>
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
                Data:[],
                Fields:[],
                downloadName: 'idk',
                filter: null,
                filterOn: [],
                sourceTypeData:[],
                deviceData:[],
                tripData:[],
                measurementData: [],
                typeData:[],
                sourceTypeFields:[
                    {
                        key: 'sourceTypeId', label: 'Source Type ID', sortable: true
                    },
                    {
                        key: 'time', label: 'Time', sortable: true
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
                    },
                    {
                        key: 'mapReference',  label: 'Map Matched Location', sortable: true
                    }
                ],
                typeFields:[
                    {
                        key: 'id', label: 'ID', sortable: true
                    },
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
                }
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
                if (this.PickedTable === 'measurements'){
                    this.Data = this.measurementData
                    this.Fields = this.measurementFields
                } else if (this.PickedTable === 'measurementTypes'){
                    this.Data = this.typeData
                    this.Fields = this.typeFields
                } else if (this.PickedTable === 'trips') {
                    this.Data = this.tripData
                    this.Fields = this.tripFields
                } else if (this.PickedTable === 'devices') {
                    this.Data = this.deviceData
                    this.Fields = this.deviceFields
                } else if (this.PickedTable === 'sourceTypes') {
                    this.Data = this.sourceTypeData
                    this.Fields = this.sourceTypeFields
                }
                this.update()
                this.showAlert()
            },
            info(item, index, button) {
                this.infoModal.title = `Row index: ${index}`
                this.infoModal.content = JSON.stringify(item, null, 2)
                this.$root.$emit('bv::show::modal', this.infoModal.id, button)
            },
            resetInfoModal() {
                this.infoModal.title = ''
                this.infoModal.content = ''
            },
            async update(){
                try {
                    const ressource = await axios.get(sourceURL)
                    const resdev = await axios.get(deviceURL)
                    const restrip = await axios.get(tripURL)
                    const resmeas = await axios.get(measurementURL)
                    const restypes = await axios.get(TypeURL)
                    this.sourceTypeData = ressource.data
                    this.deviceData = resdev.data
                    this.tripData = restrip.data
                    this.measurementData = resmeas.data
                    this.typeData = restypes.data
                } catch (e) {
                    console.error(e)
                }
            }
        },
        async created() {
            try {
                const ressource = await axios.get(sourceURL)
                const resdev = await axios.get(deviceURL)
                const restrip = await axios.get(tripURL)
                const resmeas = await axios.get(measurementURL)
                const restypes = await axios.get(TypeURL)
                this.sourceTypeData = ressource.data
                this.deviceData = resdev.data
                this.tripData = restrip.data
                this.measurementData = resmeas.data
                this.typeData = restypes.data
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