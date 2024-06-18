export const user = {
    namespaced: true,
    state(){
        return{
            all: {}
        }
    },
    mutations:{
        setUser(state, userData){
            state.all = userData;
        }
    },
    actions:{
        
    }
}