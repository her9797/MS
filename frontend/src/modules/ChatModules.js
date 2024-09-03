import { createActions, handleActions } from 'redux-actions';

const initialState = {
    rooms: [],
    joinedUserList: []
};

export const GET_ROOM = 'rooms/GET_ROOMS';

const actions = createActions({
    [GET_ROOM]: () => {}
});

const roomReducer = handleActions({
    [GET_ROOM]: (state, {payload}) => ({rooms: payload})
}, initialState);

export default roomReducer;