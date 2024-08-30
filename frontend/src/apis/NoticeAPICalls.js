import { GET_NOTICE } from "../modules/NoticeModule";
import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080';

const headers = {
    'Content-Type': 'application/json; charset=UTF-8',
    Accept: '*/*',
    Authorization: 'Bearer ' + window.localStorage.getItem('jwtToken') // 로컬스토리지에 key : jwtToken 으로 설정
};


export const callSelectNoticeAPI = (deleteYn,page, size) => {
    return async dispatch => {
        try {
            const response = await axios.get(`${API_BASE_URL}/notices?eleteYn=N&page=1&size=10`, { headers });
            console.log();
            dispatch({ type: GET_NOTICE, payload: response.data.results });
            return response.data.results;
        } catch (error) {
            console.log('공지 조회에 문제 발생', error);
            return [];
        }
    };
};