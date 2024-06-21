<script setup lang="ts">
    import Toolbar from 'primevue/toolbar';
    import Button from 'primevue/button';
    import { RouterLink } from 'vue-router';
</script>

<template>
    <Toolbar style="margin-bottom: 1rem;">
        <template #center>
            <Button label="Messages" text plain />
            <RouterLink to="/profile">
                <Button label="Profile" text plain />
            </RouterLink>
        </template>
    </Toolbar>
</template>