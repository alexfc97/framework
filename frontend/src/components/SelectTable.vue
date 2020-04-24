<template>
    <section>
        <h3 align="center">
            Please choose what table you would like to view!
        </h3>
        <div class="col-4 offset-4 mb-4">
            <b-row>
                <b-col cols="12">
                    <b-form-select :value="null" v-model="tableMessage" :options="measurementOptions" v-on:change="emitToParent"></b-form-select>
                </b-col>
            </b-row>
        </div>
    </section>
</template>

<script>
    export default {
        name: "radioButtons",
        data() {
            return {
                measurementform:
                {
                    measurementid: null,
                    time:null,
                    value:null,
                    type:null,
                    latitude:null,
                    longitude:null
                },
                tableMessage: '',
                measurementOptions: [
                    {
                        value: null,
                        text:'Please select an option'
                    },
                    {
                        value: 'sourcetypes',
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
                        value: 'measurementtypes',
                        text: 'Measurement Types'
                    }
                ]
            }
        },
        methods: {
            emitToParent () {
                this.$emit('childToParent',this.tableMessage)
            }
        }
    }
</script>

<style scoped>

</style>