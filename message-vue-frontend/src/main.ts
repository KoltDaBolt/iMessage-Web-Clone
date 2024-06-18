import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import PrimeVue from 'primevue/config';
import { store } from './stores/store';
import 'primevue/resources/themes/aura-dark-blue/theme.css';
import './style.scss';

const app = createApp(App)

app.use(router);
app.use(PrimeVue);
app.use(store);

app.mount('#app');
