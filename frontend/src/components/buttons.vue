<template>
    <div>
        <b-button variant="primary" class="mt-2 ml-1" v-b-modal.configuredevice>Configure Device</b-button>
        <b-button variant="primary" class="mt-2 ml-1" v-b-modal.configuresensor>Configure Sensor</b-button>

        <b-modal
                id="configuredevice"
                ref="modal"
                title="Submit Your New Device"
                @ok="handleOk"
        >
            <form ref="form" @submit.stop.prevent="handleSubmit(true)">
                <b-form-group
                        label="Device ID: "
                        label-for="deviceid-input"
                        invalid-feedback="Device ID is required"
                >
                    <b-form-input
                            id="deviceid-input"
                            type="number"
                            v-model="deviceform.deviceid"
                            required
                    ></b-form-input>
                </b-form-group>
                <b-form-group
                        label="Car ID: "
                        label-for="carid-input"
                        invalid-feedback="Car ID is required"
                >
                    <b-form-input
                            id="carid-input"
                            type="number"
                            v-model="deviceform.carid"
                            required
                    ></b-form-input>
                </b-form-group>
            </form>
            <p>
            <strong>
                Output:
            </strong>
            {{deviceoutput}}
            </p>
        </b-modal>
        <b-modal
                id="configuresensor"
                ref="modal"
                title="Submit Your New Sensor"
                @ok="handleOk"
        >
            <form ref="form" @submit.stop.prevent="handleSubmit(false)">
                <b-form-group
                        label="Sensor ID: "
                        label-for="sensorid-input"
                        invalid-feedback="Sensor ID is required"
                >
                    <b-form-input
                            id="sensorid-input"
                            type="number"
                            v-model="sensorform.sensorid"
                            required
                    ></b-form-input>
                </b-form-group>
                <b-form-group
                        label="Sensor Type: "
                        label-for="sensortype-input"
                        invalid-feedback="Sensor Type is required"
                >
                    <b-form-input
                            id="sensortype-input"
                            type="text"
                            v-model="sensorform.sensortype"
                            required
                    ></b-form-input>
                </b-form-group>
                <b-form-group
                        label="Device ID sensor belongs to: "
                        label-for="sensordeviceid-input"
                        invalid-feedback="A configured device is required before a sensor can be configured"
                >
                    <b-form-input
                            id="sensordeviceid-input"
                            type="number"
                            v-model="sensorform.sensordeviceid"
                            required
                    ></b-form-input>
                </b-form-group>
            </form>
            <p>
                <strong>
                    Output:
                </strong>
                {{sensoroutput}}
            </p>
        </b-modal>

        <download-csv
            class="btn btn-primary offset-7 ml-8"
            :data="items"
            name = "tripdata.csv">
        Download Data
        </download-csv>
    </div>
</template>

<script>
    import axios from 'axios';
    axios.defaults.headers.post['Content-Type'] = { "Content-Type": "application/json" }
    const deviceapiurl = 'http://localhost:8081/devices/api/inputdevice'
    const devicexistsurl = 'http://localhost:8081/devices/api/exists'
    const sensorapiurl = 'http://localhost:8081/sensors/api/inputsensor'
    const sensorexistsurl = 'http://localhost:8081/sensors/api/exists'

    export default {
        name: "buttons",
        props: [
            'items'
        ],
        data() {
            return {
                deviceform: {
                  deviceid:null,
                  carid:null
                },
                deviceIdState: null,
                carIdState: null,
                deviceoutput:'',
                sensorform:{
                    sensorid:null,
                    sensortype:null,
                    sensordeviceid:null
                },
                sensorIdState: null,
                sensorTypeState: null,
                sensoroutput:''
            }
        },
        methods: {
            checkFormValidity() {
                const valid = this.$refs.deviceform.checkValidity()
                this.deviceIdState = valid
                this.carIdState = valid
                return valid
            },
            handleOk(bvModalEvt ) {
                // Prevent modal from closing
                bvModalEvt.preventDefault()
                // Trigger submit handler
                this.handleSubmit()
            },
            checkDeviceExist(boolean) {
                if (boolean) {
                    axios.get(devicexistsurl + '/' + this.deviceform.deviceid)
                        .then(function (response) {
                            if (response.data === true) {
                                this.deviceoutput = 'A device with the specified device id already exists'
                                return true
                            } else {
                                return false
                            }
                        })
                } else {
                    axios.get(sensorexistsurl + '/' + this.sensorform.sensorid)
                        .then(function (response) {
                            if (response.data === true) {
                                this.sensoroutput = 'A device with the specified device id already exists'
                                return true
                            } else {
                                return false
                            }
                        })
                }
            },
            submitRequest(boolean) {
                if (boolean) {
                    axios.post(deviceapiurl,{
                        deviceid: this.deviceform.deviceid,
                        carid: this.deviceform.carid
                    })
                        .then((response) => {
                            if (response.status === 200){
                                this.deviceoutput = 'Device successfully configured'
                            }
                        })
                        .catch(function (error) {
                            this.deviceoutput = error.message
                        });
                } else{
                    axios.post(sensorapiurl,{
                        sensorid: this.sensorform.sensorid,
                        sensortype: {
                            type: this.sensorform.sensortype
                        },
                        device: {
                            deviceid: this.sensordeviceid
                        }
                    })
                        .then((response) => {
                            if (response.status === 200){
                                this.deviceoutput = 'Device successfully configured'
                            }
                        })
                        .catch(function (error) {
                            this.deviceoutput = error.message
                        });
                }

            },
            resetModal(boolean) {
                if (boolean) {
                    this.deviceform.deviceid = null
                    this.deviceform.carid = null
                    this.deviceoutput = ''
                } else {
                    this.sensorform.sensorid = null
                    this.sensorform.sensortype = null
                    this.sensoroutput = ''
                }
            },
            handleSubmit(boolean) {
            // Exit when the form isn't valid
            if (!this.checkFormValidity(boolean)) {
                return
            }
            if (!this.checkDeviceExist(boolean)) {
                // this.submitRequest()
                this.submitRequest(boolean)
                this.resetModal(boolean)
                } else {
                    if (boolean) {
                        this.deviceoutput = 'Error occurred during configuration'
                    } else {
                        this.sensoroutput = 'Error occurred during configuration'
                    }

            }
            }
        }
    }

</script>

<style scoped>

</style>