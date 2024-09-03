import { createActions, handleActions } from 'redux-actions';

const initialState = {
    rooms: [],
    joinedUserList: []
};

export const GET_ROOMS_USERS = 'rooms/GET_ROOMS_USERS';

const actions = createActions({
    [GET_ROOMS_USERS]: () => {}
});

const joinedUserReducer = handleActions({
    [GET_ROOMS_USERS]: (state, {payload}) => ({rooms: payload})
}, initialState);

export default joinedUserReducer;