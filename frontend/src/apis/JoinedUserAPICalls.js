import axios from 'axios';
import { GET_ROOMS_USERS, PATCH_JOINED_USER } from "../modules/JoinedUserModules";
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


export const callPatchJoinedUserAPI = (roomId, joinedUserDTO) => {
    return async dispatch => {
        try {
            const response = await axios.patch(`${API_BASE_URL}/joinedUser/${roomId}`,joinedUserDTO, { headers });
            dispatch({ type: PATCH_JOINED_USER, payload: response.data.results });
            console.log(response.data.results);
            return response.data.results;
        } catch (error) {
            console.log('room 나가기 문제 발생', error);
            throw error;
        }
    };
};
