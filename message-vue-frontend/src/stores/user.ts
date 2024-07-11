import api from "@/api/api";
import type User from "@/models/User";
import type UserLoginFormData from "@/models/UserLoginFormData";
import type UserLoginCredentials from "@/models/UserLoginFormData";
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
        async login(ctx: any, loginFormData: UserLoginFormData): Promise<User | String>{
            return await api.user.login(loginFormData);
        },
        async signup(ctx: any, validSignupFormData: UserSignupFormData): Promise<User | String>{
            return await api.user.register(validSignupFormData);
        }
    },
    getters:{
        user: (state: any) => state.all,
        isAuthenticated: (state: any) => !!state.all
    }
}