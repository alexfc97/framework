<template>
    <section>
        <h3 align="center">
            Please choose what table you would like to view!
        </h3>
        <div>
            <b-container>
                <b-row class="mb-3">
                    <b-col md="4">
                        <b-button variant="primary" v-b-modal.addmodal>Configure new Entity</b-button>
                    </b-col>
                    <b-col md="4">
                        <b-form-select v-model="tableMessage" :options="Options" v-on:change="emitToParent">
                            <template v-slot:first>
                                <b-form-select-option :value="null" disabled>-- Please select an option --</b-form-select-option>
                            </template>
                        </b-form-select>
                    </b-col>
                </b-row>
                <b-modal id="addmodal"
                         scrollable
                         title="Please choose the type of entity you would like to configure!"
                         hide-footer>
                    <strong class="my-4">Configure new Sourcetype!</strong>
                    <form ref="form">
                            <b-form-group
                                    label="Source Type Name: "
                                    label-for="name-input"
                            >
                                <b-form-input
                                        type="text"
                                        id="name-input"
                                        v-model="sourceType.name"
                                        placeholder="Please enter a sourcetype name!"
                                        required>
                                </b-form-input>
                                <b-button variant="primary" class="mt-2" v-on:click="configureSourceType">Submit Sourcetype</b-button>
                                <b-alert
                                        class="mt-2"
                                        v-model="showDismissibleAlert.sourcetype200"
                                        variant="success"
                                        dismissible>
                                    {{this.sourcetypemessage.sourcetype200}}!
                                </b-alert>
                                <b-alert
                                        class="mt-2"
                                        v-model="showDismissibleAlert.sourcetype404"
                                        variant="success"
                                        dismissible>
                                    {{this.sourcetypemessage.sourcetype404}}!
                                </b-alert>
                                <p>--------------------------------------------------------------------</p>
                            </b-form-group>
                    </form>
                    <strong class="my-4">Configure new Device!</strong>
                    <form ref="form">
                        <b-form-group label="Source Type ID:" label-for="sourcetypeid-input" invalid-feedback="Sourcetype name is required">
                            <b-form-input
                                    type="number"
                                    id="sourcetypeid-input"
                                    v-model="device.sourceTypeId"
                                    placeholder="Please enter a sourcetype id"
                                    required
                            ></b-form-input>
                        </b-form-group>
                        <b-form-group label="Car ID:" label-for="carid-input" invalid-feedback="Sourcetype id is required">
                            <b-form-input
                                    type="number"
                                    id="carid-input"
                                    v-model="device.carId"
                                    placeholder="Please enter a car id"
                                    required
                            ></b-form-input>
                            <b-button variant="primary" class="mt-2" v-on:click="configureDevice">Submit Device</b-button>
                            <b-alert class="mt-2" v-model="showDismissibleAlert.device200" variant="success" dismissible>
                                {{this.sourcetypemessage.device200}}!
                            </b-alert>
                            <b-alert class="mt-2" v-model="showDismissibleAlert.device404" variant="danger" dismissible>
                                {{this.sourcetypemessage.device404}}!
                            </b-alert>
                            <p>--------------------------------------------------------------------</p>
                        </b-form-group>
                    </form>
                    <strong class="my-4">Configure new Trip!</strong>
                    <p class="font-italic">Note that only the device id field is required</p>
                    <form>
                        <b-form-group label="Device ID:" label-for="device-input" invalid-feedback="Device ID is required">
                            <b-form-input
                                    type="number"
                                    id="device-input"
                                    v-model="trip.deviceid"
                                    placeholder="Please enter a device id"
                                    required
                            ></b-form-input>
                        </b-form-group>
                        <b-form-group label="Start Position Latitude:" label-for="startlat-input" invalid-feedback="Device ID is required">
                            <b-form-input
                                    type="number"
                                    id="startlat-input"
                                    v-model="trip.StartPositionLat"
                                    placeholder="Please enter the start position latitude"
                                    required
                            ></b-form-input>
                        </b-form-group>
                        <b-form-group label="Start Position Longitude:" label-for="startlon-input" invalid-feedback="Device ID is required">
                            <b-form-input
                                    type="number"
                                    id="startlon-input"
                                    v-model="trip.StartPositionLon"
                                    placeholder="Please enter the start position longitude"
                                    required
                            ></b-form-input>
                        </b-form-group>
                        <b-form-group label="End Position Latitude:" label-for="endlat-input" invalid-feedback="Device ID is required">
                            <b-form-input
                                    type="number"
                                    id="endlat-input"
                                    v-model="trip.EndPositionLat"
                                    placeholder="Please enter the end position latitude"
                                    required
                            ></b-form-input>
                        </b-form-group>
                        <b-form-group label="End Position Latitude:" label-for="endlon-input" invalid-feedback="Device ID is required">
                            <b-form-input
                                    type="number"
                                    id="endlon-input"
                                    v-model="trip.EndPositionLon"
                                    placeholder="Please enter the end position longitude"
                                    required
                            ></b-form-input>
                        </b-form-group>
                        <b-button variant="primary" class="mt-2" v-on:click="configureTrip">Submit Trip</b-button>
                        <b-alert class="mt-2" v-model="showDismissibleAlert.trip200" variant="success" dismissible>
                            {{this.sourcetypemessage.trip200}}!
                        </b-alert>
                        <b-alert class="mt-2" v-model="showDismissibleAlert.trip404" variant="danger" dismissible>
                            {{this.sourcetypemessage.trip404}}!
                        </b-alert>
                    </form>
                </b-modal>
            </b-container>
        </div>
    </section>
</template>

<script>
    import axios from 'axios'
    const sourcetypeUrl = 'http://localhost:8081/sourcetypes/api/inputsourcetype'
    const deviceUrl = "http://localhost:8081/devices/api/inputdevice"
    const tripUrl = 'http://localhost:8081/trips/api/createtrip'

    export default {
        name: "radioButtons",
        computed:{
            nameState() {
                return this.sourceType.name.length > 1
            }
        },
        data() {
            return {
                sourcetypemessage:{
                    sourcetype200: null,
                    sourcetype404: null,
                    device200: null,
                    device404: null,
                    trip200: null,
                    trip404: null
                },
                showDismissibleAlert: {
                    sourcetype200: false,
                    sourcetype404: false,
                    device200: false,
                    device404:false,
                    trip200: false,
                    trip404: false
                },
                measurementform:
                {
                    measurementid: null,
                    time:null,
                    value:null,
                    type:null,
                    latitude:null,
                    longitude:null
                },
                sourceType:{
                    name:null
                },
                device:{
                    sourceTypeId:null,
                    carId:null
                },
                trip:{
                    deviceid:null,
                    StartPositionLat: null,
                    StartPositionLon: null,
                    EndPositionLat: null,
                    EndPositionLon: null,

                },
                tableMessage: null,
                Options: [
                    {
                        value: 'sourceTypes',
                        text: 'Source Types'
                    },
                    {
                        value: 'devices',
                        text: 'Devices'
                    },
                    {
                        value: 'trips',
                        text: 'Trips'
                    },
                    {
                        value: 'measurements',
                        text: 'Measurements'
                    },
                    {
                        value: 'measurementTypes',
                        text: 'Measurement Types'
                    }
                ],
                dismissSecs: 3,
                dismissCountDown: 0,
                value: null,
                radiooptions: [
                    { text: 'First radio', value: 'first' },
                    { text: 'Second radio', value: 'second' },
                    { text: 'Third radio', value: 'third' }
                ]
            }
        },
        methods: {
            emitToParent () {
                this.$emit('childToParent',this.tableMessage)
            },
            async configureSourceType(){
                axios.post(sourcetypeUrl,{
                    sourceTypeName: this.sourceType.name
                })
                .then(response => {
                    if (response.status === 200){
                        this.sourcetypemessage.sourcetype200 = "Source type has successfully been configured"
                        this.showDismissibleAlert.sourcetype404 = false
                        this.showDismissibleAlert.sourcetype200 = true
                        console.log(response.status)
                    }
                    else {
                        this.sourcetypemessage.sourcetype404 = "Code " + response.status + ": " + response.message()
                        this.showDismissibleAlert.sourcetype200 = false
                        this.showDismissibleAlert.sourcetype404 = true
                    }
                })
                .catch(e => {
                    console.log(e)
                    this.sourcetypemessage.sourcetype404 = "Code " + e.response.status + ": " + e.response.data.message
                    this.showDismissibleAlert.sourcetype404 = true
                })
            },
            async configureDevice(){
                axios.post(deviceUrl,{
                    carId: this.device.carId,
                    sourceType: {
                        sourceTypeId: this.device.sourceTypeId
                    }
                })
                .then(response => {
                    if (response.status === 200){
                        this.sourcetypemessage.device200 = "Device has successfully been configured"
                        this.showDismissibleAlert.device404 = false
                        this.showDismissibleAlert.device200 = true
                        console.log(response.status)
                    }
                    else {
                        this.sourcetypemessage.device = "Code " + response.status + ": " + response.message()
                        this.showDismissibleAlert.device = true
                    }
                })
                .catch(e => {
                    console.log(e)
                    console.log(e.response.data.message)
                    this.sourcetypemessage.device404 = "Code " + e.response.status + ": " + e.response.data.message
                    this.showDismissibleAlert.device200 = false
                    this.showDismissibleAlert.device404 = true
                })
            },
            async configureTrip(){
                axios.post(tripUrl,{
                    device: {
                        deviceId: this.trip.deviceid
                    },
                    startPositionLat: this.trip.startPositionLat,
                    startPositionLon: this.trip.startPositionLon,
                    endPositionLat: this.trip.endPositionLat,
                    endPositionLon: this.trip.endPositionLon
                })
                .then(response => {
                    if (response.status === 200){
                        this.sourcetypemessage.trip200 = "Trip has successfully been configured"
                        this.showDismissibleAlert.trip404 = false
                        this.showDismissibleAlert.trip200 = true
                        console.log(response.status)
                    }
                    else {
                        this.sourcetypemessage.trip = "Code " + response.status + ": " + response.message()
                    }
                })
                .catch(e => {
                    console.log(e)
                    this.sourcetypemessage.trip404 = "Code " + e.response.status + ": " + e.response.data.message
                    this.showDismissibleAlert.trip200 = false
                    this.showDismissibleAlert.trip404 = true
                })
            },
            countDownChanged(dismissCountDown) {
                this.dismissCountDown = dismissCountDown
            }
        }
    }
</script>

<style scoped>

</style>