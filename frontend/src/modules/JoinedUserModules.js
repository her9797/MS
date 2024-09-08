import { createActions, handleActions } from 'redux-actions';

const initialState = {
    rooms: [],
    joinedUserList: []
};

export const GET_ROOMS_USERS = 'rooms/GET_ROOMS_USERS';
export const PATCH_JOINED_USER = 'joinedUser/PATCH_JOINED_USER'

const actions = createActions({
    [GET_ROOMS_USERS]: () => {},
    [PATCH_JOINED_USER]: () => {}
});

const joinedUserReducer = handleActions({
    [GET_ROOMS_USERS]: (state, {payload}) => ({rooms: payload}),
    [PATCH_JOINED_USER]: (state, {payload}) => ({patchUser: payload}),
}, initialState);

export default joinedUserReducer;