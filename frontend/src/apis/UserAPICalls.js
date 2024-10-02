import axios from 'axios';
import { PATCH_USER, POST_LOGIN_USER, POST_USER } from "../modules/UserModules";
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

            // 서버에서 에러 메시지를 가져옴
            const errorMessage = error.response?.data || '로그인 중 문제가 발생했습니다.';

            // 사용자에게 적절한 메시지 표시
            if (error.response?.status === 403) {
                alert("계정이 비활성 상태입니다. 고객 지원에 문의하세요."); // 비활성 계정 메시지
            } else if (error.response?.status === 401) {
                alert("잘못된 이메일 또는 비밀번호입니다!"); // 잘못된 이메일/비밀번호 메시지
            } else {
                alert(errorMessage); // 다른 에러에 대한 기본 메시지
            }

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

export const callUserDetailAPI = (userEmail) => {
    return async dispatch => {
        try {
            const response = await axios.get(`${API_BASE_URL}/users/${userEmail}` , { headers });
            console.log('API 응답 데이터:', response.data);
            dispatch({ type: POST_LOGIN_USER, payload: response.data });
            return response.data;
        } catch (error) {
            console.error('회원 상세 조회 문제 발생:', error);
            throw error;
        }
    };
};

export const callModifyUser = (userEmail, userDTO) => {
    return async dispatch => {
        try {
            const response = await axios.patch(`${API_BASE_URL}/users/${userEmail}` , userDTO, { headers });
            console.log('API 응답 데이터:', response.data);
            dispatch({ type: PATCH_USER, payload: response.data });
            return response.data;
        } catch (error) {
            console.error('회원 정보 수정 문제 발생:', error);
            throw error;
        }
    };
};