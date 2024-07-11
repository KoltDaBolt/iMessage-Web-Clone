<script setup lang="ts">

    const store = useStore();
    const contacts = store.getters['contacts/contacts'];

    const placeholderSearch = ref("");
    const visible = ref(false);
    const contactSuggestions = ref([]);
    const selectedContacts = ref([]);

    const searchContacts = (event: any) => {
        const query = event.query.toLowerCase();
        contactSuggestions.value = contacts.filter((contact: Contact) => {
            return contact.firstname.toLowerCase().includes(query) ||
                   contact.lastname.toLowerCase().includes(query) ||
                   contact.username.toLowerCase().includes(query);
        });
    };

    const fullNameOrUsername = (contact: Contact) => {
        if(contact.firstname || contact.lastname){
            return `${contact.firstname} ${contact.lastname} (${contact.username})`;
        }else{
            return contact.username;
        }
    };

    const createNewMessage = () => {

    }
</script>

<template>
    
</template>