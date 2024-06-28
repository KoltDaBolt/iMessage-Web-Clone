import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import PrimeVue from 'primevue/config';
import { store } from './stores/store';
import 'primevue/resources/themes/aura-dark-blue/theme.css';
import './style.scss';
import DataTable from 'datatables.net-vue3';
import DataTablesCore from 'datatables.net';

DataTable.use(DataTablesCore);

const app = createApp(App)

app
    .component("DataTable", DataTable)
    .use(router)
    .use(PrimeVue)
    .use(store)

app.mount('#app');
