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

    const editingRows = ref([]);

    const onRowEditSave = (event: { newData: any, index: any }) => {
        let { newData, index } = event;
        tableData.value[index] = newData;
    }
</script>

<template>
    <DataTable
        v-model:editingRows="editingRows"
        :value="tableData"
        editMode="row"
        tableStyle="width: 50rem;"
        @row-edit-save="onRowEditSave"
    >
        <Column field="username" header="Username"></Column>
        <Column field="firstname" header="First Name">
            <template #editor="{ data, field }">
                <InputText v-model="data[field]" />
            </template>
        </Column>
        <Column field="lastname" header="Last Name">
            <template #editor="{ data, field }">
                <InputText v-model="data[field]" />
            </template>
        </Column>
        <Column :rowEditor="true" style="width: 10%; min-width: 8rem;" bodyStyle="text-align:center"></Column>
    </DataTable>
</template>