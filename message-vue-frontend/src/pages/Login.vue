<script setup lang="ts">
    import InputText from 'primevue/inputtext';
    import FloatLabel from 'primevue/floatlabel';
    import Password from 'primevue/password';
    import Button from 'primevue/button';
    import { ref, reactive } from 'vue';
    import router from '@/router';
    import { useStore } from 'vuex';

    const store = useStore();

    const loginFormData: UserLoginFormData = reactive({
        username: "",
        lastname: ""
    });

    const errors = ref("");

    const submitLoginForm = async() => {
        var user: User = await store.dispatch("user/login", loginFormData);

        if(typeof user == "string"){
            errors.value = user;
        }else{
            store.commit("user/setUser", user);
            router.replace("/home");
        }
    }
</script>

<template>
    <h1>Login</h1>

    <FloatLabel class="floatlabel-margin">
        <label for="username">Username</label>
        <InputText id="username" v-model="loginFormData.username" />
    </FloatLabel>
    <FloatLabel class="floatlabel-margin">
        <label for="password">Password</label>
        <Password id="password" v-model="loginFormData.password" :feedback="false" />
    </FloatLabel>

    <div class="floatlabel-margin">
        <RouterLink to="/signup">
            <Button label="New User? Sign Up Here" severity="info" outlined />
        </RouterLink>
        <Button label="Login" severity="info" @click.prevent="submitLoginForm()" />
    </div>

    <p style="color: red;" v-if="errors">{{ errors }}</p>
</template>
