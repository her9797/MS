import axios from 'axios';
import { GET_ROOMS_USERS } from "../modules/ChatModules";
import { API_BASE_URL, headers } from './config';

export const callSelectJoinedRoomListAPI = (userId) => {
    return async dispatch => {
        try {
            const response = await axios.get(`${API_BASE_URL}/joinedUser/${userId}`, { headers });
            dispatch({ type: GET_ROOMS_USERS, payload: response.data.results });
            return response.data.results;
        } catch (error) {
            console.log('joinedUser 조회에 문제 발생', error);
            return [];
        }
    };
};


