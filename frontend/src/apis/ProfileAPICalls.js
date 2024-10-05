import axios from 'axios';
import { API_BASE_URL, headers } from './config';
import { GET_PROFILE } from '../modules/ProfileModules';

export const callSelectUserProfileAPI = (userEmail) => {
    return async dispatch => {
        try {
            const response = await axios.get(`${API_BASE_URL}/profiles/${userEmail}`, { headers });
            dispatch({ type: GET_PROFILE, payload: response.data.results });
            console.log(response.data.results);
            return response.data.results;
        } catch (error) {
            console.log('프로필 사진 조회에 문제 발생', error);
            return [];
        }
    };
};

