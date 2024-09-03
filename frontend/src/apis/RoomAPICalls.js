import axios from 'axios';
import { GET_ROOM } from "../modules/ChatModules";
import { API_BASE_URL, headers } from './config';


export const callSelectJoinedRoomListAPI = (userId) => {
    return async dispatch => {
        try {
            const response = await axios.get(`${API_BASE_URL}/joinedUser/${userId}`, { headers });
            dispatch({ type: GET_ROOM, payload: response.data.results });
            return response.data.results;
        } catch (error) {
            console.log('방 리스트 조회에 문제 발생', error);
            return [];
        }
    };
};