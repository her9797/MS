import { GET_NOTICE, POST_NOTICE } from "../modules/NoticeModule";
import axios from 'axios';
import { API_BASE_URL, headers } from './config';

export const callSelectNoticeAPI = (deleteYn, page, size) => {
    return async dispatch => {
        try {
            const response = await axios.get(`${API_BASE_URL}/notices?eleteYn=N&page=1&size=10`, { headers });
            console.log();
            dispatch({ type: GET_NOTICE, payload: response.data.results });
            return response.data.results;
        } catch (error) {
            console.log('공지 조회에 문제 발생 (없을 수도 있음)', error);
            return [];
        }
    };
};


export const callInsertNoticeAPI = (noticeDTO) => {
    return async dispatch => {
        try {
            // POST 요청을 보내면서 noticeDTO를 요청 본문에 포함
            const response = await axios.post(`${API_BASE_URL}/notices`, noticeDTO, {
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: 'Bearer ' + window.localStorage.getItem('jwtToken')
                }
            });
            // 성공적인 응답 후 상태를 업데이트
            dispatch({ type: POST_NOTICE, payload: response.data.results });
            return response.data.results;
        } catch (error) {
            console.log('공지 등록 실패', error);
            return [];
        }
    };
};
