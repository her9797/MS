import { createActions, handleActions } from 'redux-actions';

const initialState = {
    roomAndUser: [],
};

export const POST_ROOM_USER ='rooms/POST_ROOM_USER';

const actions = createActions({
    [POST_ROOM_USER]: () => {}
});

const roomAndUserReducer = handleActions({
    [POST_ROOM_USER]: (state, {payload}) => ({postRoomAndUser: payload})
}, initialState);

export default roomAndUserReducer;