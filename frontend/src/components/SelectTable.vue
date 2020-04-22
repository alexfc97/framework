<template>
    <section>
        <h3 align="center">
            Please choose what table you would like to view!
        </h3>
        <div class="col-4 offset-4 mb-4">
            <b-form-select v-model="tableMessage" :options="measurementOptions" v-on:change="emitToParent"></b-form-select>
        </div>
    </section>
</template>

<script>
    export default {
        name: "radioButtons",
        data() {
            return {
                tableMessage: '',
                measurementOptions: [
                    {
                        value: null,
                        text:'Please select an option'
                    },
                    {
                        value: 'Measurements',
                        text: 'Measurements'
                    },
                    {
                        value: 'Sensors',
                        text: 'Sensors'
                    },
                    {
                        value: 'Sensor Types',
                        text: 'Sensor Types'
                    },
                    {
                        value: 'Measurement Types',
                        text: 'Measurement Types'
                    },
                    {
                        value: 'Devices',
                        text: 'Devices'
                    }
                ],
                sensorOptions: [

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