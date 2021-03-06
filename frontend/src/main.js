import Vue from 'vue'
import App from './App.vue'
import DataTable from 'laravel-vue-datatable';
import {BootstrapVue, IconsPlugin} from 'bootstrap-vue'
import JsonCSV from 'vue-json-csv'
import JsonExcel from 'vue-json-excel'


Vue.config.productionTip = false;
Vue.use(DataTable);
Vue.use(BootstrapVue);
Vue.use(IconsPlugin);
Vue.component('downloadCsv', JsonCSV);
Vue.component('downloadExcel', JsonExcel)


new Vue({
  render: h => h(App)
}).$mount('#app');