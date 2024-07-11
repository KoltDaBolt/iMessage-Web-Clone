import axios from 'axios';
import Config from '../config.json';
import type UserSignupFormData from '@/models/UserSignupFormData';
import type User from '@/models/User';
import type Contact from '@/models/Contact';
import type UserLoginFormData from '@/models/UserLoginFormData';

class Api{
    readonly apiRoot: string;

    constructor(apiRoot: string){
        this.apiRoot = apiRoot;
    }

    user = ((self: Api) => {
        let result = {
            login(loginFormData: UserLoginFormData): Promise<User | String>{
                let address = self.apiRoot + `/user/login/`;
                return axios
                    .post(address, loginFormData)
                    .then((resp) => resp.data as User)
                    .catch((err) => err.response.data);
            },
            register(signupFormData: UserSignupFormData): Promise<User | String>{
                let address = self.apiRoot + '/user/register';
                return axios
                    .post(address, signupFormData)
                    .then((resp) => resp.data as User)
                    .catch((err) => err.response.data);
            }
        };
        return result;
    })(this);
    
    contact = ((self: Api) => {
        let result = {
            getContacts(username: String): Promise<Contact[] | String>{
                let address = self.apiRoot + `/contact/${username}`;
                return axios
                    .get(address)
                    .then((resp) => resp.data as Contact[]);
            },
            create(newContactFormData: Contact, username: String): Promise<Number | String>{
                let address = self.apiRoot + `/contact/${username}`;
                return axios
                    .post(address, newContactFormData)
                    .then((resp) => 201 as Number)
                    .catch((err) => err.response.data);
            }
        }
        return result;
    })(this);
}

const api = new Api(Config.API_ROOT);

export default api;