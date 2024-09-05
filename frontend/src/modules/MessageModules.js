import { createActions, handleActions } from 'redux-actions';

const initialState = {
    msg: []
};

export const GET_MESSAGES = 'messages/GET_MESSAGES';

const actions = createActions({
    [GET_MESSAGES]: () => {}
});

const messageReducer = handleActions({
    [GET_MESSAGES]: (state, {payload}) => ({msg: payload})
}, initialState);

export default messageReducer;