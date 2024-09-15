import { createActions, handleActions } from 'redux-actions';

const initialState = {
    user: {}
};

export const POST_USER = 'users/POST_USER';
export const POST_LOGIN_USER = 'users/POST_LOGIN_USER';

const actions = createActions({
    [POST_USER]: () => {},
    [POST_LOGIN_USER]: () => {}
});

const userReducer = handleActions({
    [POST_USER]: (state, {payload}) => ({user: payload}),
    [POST_LOGIN_USER]: (state, {payload}) => ({payload}),
}, initialState);

export default userReducer;