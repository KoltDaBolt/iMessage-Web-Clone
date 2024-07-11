import api from "@/api/api";
import type Contact from "@/models/Contact";

export const contacts = {
    namespaced: true,
    state(){
        return{
            all: null
        }
    },
    mutations:{
        setContacts(state: any, contacts: Contact[]){
            state.all = contacts;
        },
        addContact(state: any, contact: Contact){
            state.all.push(contact);
        }
    },
    actions:{
        async getContacts(ctx: any){
            let username = ctx.rootState.user.all.username;
            return await api.contact.getContacts(username);
        },
        async createContact(ctx: any, newContactFormData: Contact){
            let username = ctx.rootState.user.all.username;
            return await api.contact.create(newContactFormData, username);
        }
    },
    getters:{
        contacts: (state: any) => state.all
    }
}