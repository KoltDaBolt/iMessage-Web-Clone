<script setup lang="ts">
    import Toolbar from 'primevue/toolbar';
    import Button from 'primevue/button';
    import { RouterLink } from 'vue-router';
    import { useStore } from 'vuex';
    import { computed } from 'vue';

    const store = useStore();

    const isAuthenticated = computed(() => store.getters['user/isAuthenticated']);
</script>

<template>
    <Toolbar style="margin-bottom: 1rem;">
        <template v-if="isAuthenticated" #center>
            <Button label="Contacts" text plain />
            <Button label="Messages" text plain />
            <RouterLink to="/profile">
                <Button label="Profile" text plain />
            </RouterLink>
        </template>
        <template v-else #center>
            Welcome to kdbChat
        </template>
    </Toolbar>
</template>