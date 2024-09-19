import axios from 'axios';
import { POST_LOGIN_USER, POST_USER } from "../modules/UserModules";
import { API_BASE_URL, headers } from './config';

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

export const callLoginUserAPI = (userDTO) => {
    return async dispatch => {
        try {
            console.log('로그인 요청 데이터:', userDTO);
            const response = await axios.post(`${API_BASE_URL}/users/normal`, userDTO, {
                headers: {
                    'Content-Type': 'application/json'
                },
            });
            console.log('API 응답 데이터:', response.data);
            dispatch({ type: POST_LOGIN_USER, payload: response.data });
            return response.data;
        } catch (error) {
            console.error('로그인 문제 발생:', error);
            throw error;
        }
    };
};

export const callUserListAPI = () => {
    return async dispatch => {
        try {
            const response = await axios.get(`${API_BASE_URL}/users` , { headers });
            console.log('API 응답 데이터:', response.data);
            dispatch({ type: POST_LOGIN_USER, payload: response.data });
            return response.data;
        } catch (error) {
            console.error('회원 리스트 조회 문제 발생:', error);
            throw error;
        }
    };
};