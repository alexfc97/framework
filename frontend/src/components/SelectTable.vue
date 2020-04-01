<template>
    <section>
        <h3 align="center">
            Please choose what table you would like to view!
        </h3>
        <div class="col-4 offset-4">
            <b-form-select v-model="selected" :options="options"></b-form-select>
            <div align="center" class="mt-3">
                Selected:
                <strong>{{ selected }}
                </strong>
            </div>
        </div>
    </section>
</template>

<script>
    export default {
        name: "radioButtons",
        data() {
            return {
                selected:null,
                options: [
                    {
                        value: null,
                        text:'Please select an option'
                    },
                    {
                        value: 'Measurements',
                        text: 'Measurements Table'
                    },
                    {
                        value: 'Sensors',
                        text: 'Sensor Table'
                    },
                    {
                        value: 'Sensor Types',
                        text: 'Sensor Types'
                    },
                    {
                        value: 'Measurement Types',
                        text: 'Measurement Types'
                    }
                ]

            }
        }
    }
</script>

<style scoped>

</style>