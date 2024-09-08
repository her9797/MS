import { createActions, handleActions } from 'redux-actions';

const initialState = {
    roomAndUser: [],
};

export const GET_ROOMS = 'rooms/GET_ROOMS';

const actions = createActions({
    [GET_ROOMS]: () => {},
});

const roomReducer = handleActions({
    [GET_ROOMS]: (state, {payload}) => ({roomAndUser: payload}),
}, initialState);

export default roomReducer;