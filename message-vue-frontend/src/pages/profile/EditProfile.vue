<script setup lang="ts">
    import FloatLabel from 'primevue/floatlabel';
    import InputText from 'primevue/inputtext';
    import Button from 'primevue/button';
    import { reactive } from 'vue';
    import { useStore } from 'vuex';
    import type Contact from '@/models/Contact';

    const store = useStore();
    const user = store.getters['user/user'];

    const modifiedSelfContact: Contact = reactive({
        username: user.username,
        firstname: user.firstname,
        lastname: user.lastname
    });
</script>

<template>
    <div>
        <h1>Edit Profile</h1>

        <FloatLabel class="floatlabel-margin">
            <label for="firstname">First Name</label>
            <InputText id="firstname" v-model="modifiedSelfContact.firstname" />
        </FloatLabel>
        <FloatLabel class="floatlabel-margin">
            <label for="lastname">Last Name</label>
            <InputText id="lastname" v-model="modifiedSelfContact.lastname" />
        </FloatLabel>

        <RouterLink to="/profile">
            <Button label="Cancel" severity="danger" outlined />
        </RouterLink>
    </div>
</template>