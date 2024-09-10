import { createActions, handleActions } from 'redux-actions';

// 초기 상태 정의
const initialState = {
    isLoggedIn: !!localStorage.getItem('jwtToken'), // 토큰이 있으면 로그인 상태로 설정
    token: localStorage.getItem('jwtToken'),
};

// 액션 타입 정의
export const LOGIN = 'auth/LOGIN';
export const LOGOUT = 'auth/LOGOUT';
export const KAKAO_LOGIN = 'auth/KAKAO_LOGIN';

// 액션 생성 함수 정의
export const actions = createActions({
    [LOGIN]: (token) => ({ token }),
    [LOGOUT]: () => {},
    [KAKAO_LOGIN]: (token) => ({ token })
});

// 리듀서 정의
const authReducer = handleActions(
    {
        [LOGIN]: (state, { payload }) => ({
            ...state,
            isLoggedIn: true,
            token: payload.token,
        }),
        [LOGOUT]: (state) => ({
            ...state,
            isLoggedIn: false,
            token: null,
        }),
        [KAKAO_LOGIN]: (state, { payload }) => ({
            ...state,
            isLoggedIn: true,
            token: payload.token, 
        })
    },
    initialState
);

export default authReducer;
