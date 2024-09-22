import axios from 'axios';
import { API_BASE_URL, headers } from './config';
import { POST_ROOM_USER } from '../modules/RoomAndUserModules';

export const callPostRoomAndUserAPI = (roomAndUserDTO) => {
    return async dispatch => {
        try {
            const response = await axios.post(`${API_BASE_URL}/roomAndUser`, roomAndUserDTO, { headers });
            dispatch({ type: POST_ROOM_USER, payload: response.data.results });
            console.log(response.data.results);
            return response.data.results;
        } catch (error) {
            console.log('방 등록에 문제 발생', error);
            return [];
        }
    };
};

