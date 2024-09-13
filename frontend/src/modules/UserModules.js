import { createActions, handleActions } from 'redux-actions';

const initialState = {
    user: {}
};

export const POST_USER = 'users/POST_USER';

const actions = createActions({
    [POST_USER]: () => {},
});

const userReducer = handleActions({
    [POST_USER]: (state, {payload}) => ({user: payload}),
}, initialState);

export default userReducer;