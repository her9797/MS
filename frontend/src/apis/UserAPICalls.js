import axios from 'axios';
import { POST_USER } from "../modules/UserModules";
import { API_BASE_URL } from './config';

export const callPostUserAPI = (userDTO) => {
    return async dispatch => {
        try {
            console.log('회원가입 요청 데이터:', userDTO);
            const response = await axios.post(`${API_BASE_URL}/users`, userDTO, {
                headers: {
                    'Content-Type': 'application/json'
                },
            });
            console.log('API 응답 데이터:', response.data);
            dispatch({ type: POST_USER, payload: response.data });
            return response.data;
        } catch (error) {
            console.error('회원가입 문제 발생:', error);
            throw error;
        }
    };
};

