<script setup lang="ts">
    import Card from 'primevue/card';
    import FloatLabel from 'primevue/floatlabel';
    import InputText from 'primevue/inputtext';
    import Password from 'primevue/password';
    import Divider from 'primevue/divider';
    import Button from 'primevue/button';
    import { reactive } from 'vue';
    import { newUserSchema } from '@/validations/NewUserValidation'

    const signupFormData = reactive({
        firstName: "",
        lastName: "",
        username: "",
        password: ""
    })

    const submitSignupForm = () => {
        newUserSchema.validate(signupFormData, { abortEarly: false })
            .then((validSignupFormData) => {
                console.log(validSignupFormData)
            })
            .catch((err: any) => {
                err.inner.forEach((error: { path: string, message: string; }) => {
                    console.log("Invalid Field: " + error.path + " | Error Message: " + error.message)
                });
            })
    }
</script>

<template>
    <Card class="card">
        <template #title>Sign Up</template>
        <template #subtitle></template>
        <template #content>
            <FloatLabel class="floatlabel-margin">
                <label for="username">First Name</label>
                <InputText id="username" v-model="signupFormData.firstName" />
            </FloatLabel>
            <FloatLabel class="floatlabel-margin">
                <label for="username">Last Name</label>
                <InputText id="username" v-model="signupFormData.lastName" />
            </FloatLabel>
            <FloatLabel class="floatlabel-margin">
                <label for="username">Username</label>
                <InputText id="username" v-model="signupFormData.username" />
            </FloatLabel>
            <FloatLabel class="floatlabel-margin">
                <label for="password">Password</label>
                <Password id="password" v-model="signupFormData.password">
                    <template #footer>
                        <Divider />
                        <p class="mt-2">Requirements</p>
                        <ul class="pl-2 ml-2 mt-0">
                            <li>At least one lowercase</li>
                            <li>At least one uppercase</li>
                            <li>At least one numeric</li>
                            <li>At least 8 characters</li>
                        </ul>
                    </template>
                </Password>
            </FloatLabel>
        </template>
        <template #footer>
            <div>
                <RouterLink to="/login">
                    <Button label="Back to Login" severity="info" outlined />
                </RouterLink>
                <Button label="Join Now" severity="info" @click.prevent="submitSignupForm()" />
            </div>
        </template>
    </Card>
</template>
