<script setup lang="ts">
    import { useStore } from 'vuex';
    import { RouterLink } from 'vue-router';
    import Button from 'primevue/button';

    const store = useStore();
    const user = store.getters['user/user'];
</script>

<template>
    <div>
        <h1>Profile</h1>
        <p>Username: {{ user.username }}</p>
        <p>First Name: {{ user.firstname }}</p>
        <p>Last Name: {{ user.lastname }}</p>
        <RouterLink to="/profile/edit">
            <Button label="Edit" severity="info" outlined />
        </RouterLink>
    </div>
</template>