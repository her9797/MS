import axios from 'axios';
import { GET_ROOMS, PATCH_ROOM } from "../modules/RoomModules";
import { API_BASE_URL, headers } from './config';
import { type } from '@testing-library/user-event/dist/type';

export const callSelectRoomAndUserListAPI = (roomId) => {
    return async dispatch => {
        try {
            const response = await axios.get(`${API_BASE_URL}/rooms/${roomId}`, { headers });
            dispatch({ type: GET_ROOMS, payload: response.data.results });
            console.log(response.data.results);
            return response.data.results;
        } catch (error) {
            console.log('room 조회에 문제 발생', error);
            return [];
        }
    };
};

