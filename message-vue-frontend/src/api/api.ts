import axios from 'axios';
import Config from '../config.json';
import type SignupFormData from '@/models/SignupFormData';

class Api{
    readonly apiRoot: string;

    constructor(apiRoot: string){
        this.apiRoot = apiRoot;
    }

    user = ((self: Api) => {
        let result = {
            register(signupFormData: SignupFormData){
                let address = self.apiRoot + '/user/register'
                return axios
                    .post(address, signupFormData)
                    .then()
            }
        }
        return result
    })(this);
}

const api = new Api(Config.API_ROOT);

export default api;