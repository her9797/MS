import { GET_MESSAGES } from "../modules/MessageModules";
import axios from 'axios';
import { API_BASE_URL, headers } from './config';



export const callSelectMessageAPI = (roomId) => {
    return async dispatch => {
        try {
            const response = await axios.get(`${API_BASE_URL}/messages/${roomId}`, { headers });
            dispatch({ type: GET_MESSAGES, payload: response.data.results });
            return response.data.results;
        } catch (error) {
            console.log('메시지 조회에 문제 발생 (없을 수도 있음)', error);
            return [];
        }
    };
};