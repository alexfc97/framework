<template>
    <section>
        <h3 align="center">
            Please choose what table you would like to view!
        </h3>
        <div class="col-4 offset-4 mb-4">
            <b-row>
                <b-col>
                    <b-form-select v-model="tableMessage" :options="Options" v-on:change="emitToParent"></b-form-select>
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
                Options: [
                    {
                        value: null,
                        text:'Please select an option'
                    },
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
            resetModal(){
                this.value=null
            }
        }
    }
</script>

<style scoped>

</style>