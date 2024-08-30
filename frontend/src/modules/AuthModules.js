import { createActions, handleActions } from 'redux-actions';

// 초기 상태 정의
const initialState = {
    isLoggedIn: false,
    token: null,
};

// 액션 타입 정의
export const LOGIN = 'auth/LOGIN';
export const LOGOUT = 'auth/LOGOUT';

// 액션 생성 함수 정의
export const actions = createActions({
    [LOGIN]: (token) => ({ token }), // 로그인 시 호출되는 액션
    [LOGOUT]: () => {} // 로그아웃 시 호출되는 액션
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
    },
    initialState
);

export default authReducer;
