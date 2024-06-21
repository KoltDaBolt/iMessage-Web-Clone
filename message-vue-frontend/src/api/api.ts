import axios from 'axios';
import Config from '../config.json';
import type UserSignupFormData from '@/models/UserSignupFormData';
import type User from '@/models/User';
import type UserLoginCredentials from '@/models/UserLoginCredentials';

class Api{
    readonly apiRoot: string;

    constructor(apiRoot: string){
        this.apiRoot = apiRoot;
    }

    user = ((self: Api) => {
        let result = {
            getLoginCredentials(username: String): Promise<UserLoginCredentials | String>{
                let address = self.apiRoot + `/user/register/${username}`;
                return axios
                    .get(address)
                    .then((resp) => resp.data as UserLoginCredentials)
                    .catch((err) => "There was an error with logging in.");
            },
            login(username: String): Promise<User | String>{
                let address = self.apiRoot + `/user/login/${username}`;
                return axios
                    .get(address)
                    .then((resp) => resp.data as User)
                    .catch((err) => "There was an error logging in.");
            },
            register(signupFormData: UserSignupFormData): Promise<User>{
                let address = self.apiRoot + '/user/register';
                return axios
                    .post(address, signupFormData)
                    .then((resp) => resp.data as User)
                    .catch((err) => err.response.data);
            }
        };
        return result;
    })(this);
}

const api = new Api(Config.API_ROOT);

export default api;