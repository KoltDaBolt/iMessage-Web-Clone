import api from "@/api/api";
import type User from "@/models/User";
import type UserLoginCredentials from "@/models/UserLoginCredentials";
import type UserSignupFormData from "@/models/UserSignupFormData";

export const user = {
    namespaced: true,
    state(){
        return{
            all: {}
        }
    },
    mutations:{
        setUser(state: any, userData: User){
            state.all = userData;
        }
    },
    actions:{
        async getLoginCredentials(ctx: any, username: String): Promise<UserLoginCredentials | String>{
            return await api.user.getLoginCredentials(username);
        },
        async login(ctx: any, username: String): Promise<User | String>{
            return await api.user.login(username);
        },
        async signup(ctx: any, validSignupFormData: UserSignupFormData): Promise<User | String>{
            return await api.user.register(validSignupFormData);
        }
    }
}