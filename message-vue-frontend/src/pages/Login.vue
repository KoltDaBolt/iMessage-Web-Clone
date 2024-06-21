<script setup lang="ts">
    import InputText from 'primevue/inputtext';
    import FloatLabel from 'primevue/floatlabel';
    import Password from 'primevue/password';
    import Button from 'primevue/button';
    import { ref } from 'vue';
    import router from '@/router';
    import { useStore } from 'vuex';

    const store = useStore();

    const username = ref("");
    const password = ref("");
    const errors = ref("");

    const submitLoginForm = async() => {
        var userCreds = await store.dispatch("user/getLoginCredentials", username.value);

        if(typeof userCreds == "string"){
            errors.value = userCreds;
        }else{
            if(userCreds.username == "" || userCreds.password != password.value){
                errors.value = "Username or password is incorrect!";
            }else{
                var userData = await store.dispatch("user/login", userCreds.username);
                store.commit("user/setUser", userData);
                router.replace("/home");
            }
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
