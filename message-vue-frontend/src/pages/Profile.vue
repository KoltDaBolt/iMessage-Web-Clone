<script setup lang="ts">
    import { useStore } from 'vuex';
    import DataTable from 'primevue/datatable';
    import Column from 'primevue/column';
    import InputText from 'primevue/inputtext';
    import { ref } from 'vue';
    
    const store = useStore();
    const user = store.state.user.all;

    const jsonString = JSON.stringify(user);
    const tableData = ref([JSON.parse(jsonString)]);

    let columns = [
        { data: "username" },
        { data: "firstname" },
        { data: "lastname" },
        {
            data: null,
            render(){
                return(
                    "<a href='/profile/edit'>Edit</a>"
                )
            }
        }
    ];
</script>

<template>
    <DataTable
        class="display"
        :columns="columns"
        :data="tableData"
    >
        <thead>
            <tr>
                <th>Username</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th></th>
            </tr>
        </thead>
    </DataTable>
</template>