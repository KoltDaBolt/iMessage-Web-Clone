<script setup lang="ts">
    import InputText from 'primevue/inputtext';
    import FloatLabel from 'primevue/floatlabel';
    import Password from 'primevue/password';
    import Button from 'primevue/button';
    import api from '@/api/api';
    import { ref } from 'vue';
    import type UserLoginCredentials from '@/models/UserLoginCredentials';
    import router from '@/router';

    const username = ref("");
    const password = ref("");
    const errors = ref("");

    const submitLoginForm = async() => {
        try{
            var userCreds: UserLoginCredentials = await api.user.getLoginCredentials(username.value);

            if(userCreds.username == "" || userCreds.password != password.value){
                errors.value = "Username or password is incorrect!";
            }else{
                router.replace("/home");
            }
        }catch(err: any){
            errors.value = err.response.data;
        }
    }
</script>

<template>
    <h1>Login</h1>
    <FloatLabel class="floatlabel-margin">
        <label for="username">Username</label>
        <InputText id="username" v-model="username" />
    </FloatLabel>
    <FloatLabel class="floatlabel-margin">
        <label for="password">Password</label>
        <Password id="password" v-model="password" :feedback="false" />
    </FloatLabel>

    <div class="floatlabel-margin">
        <RouterLink to="/signup">
            <Button label="New User? Sign Up Here" severity="info" outlined />
        </RouterLink>
        <Button label="Login" severity="info" @click.prevent="submitLoginForm()" />
    </div>

    <p style="color: red;" v-if="errors">{{ errors }}</p>
</template>
  