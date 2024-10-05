import { createActions, handleActions } from 'redux-actions';

const initialState = {
    user: {}
};

export const GET_PROFILE = 'profiles/GET_PROFILE';

const actions = createActions({
    [GET_PROFILE]: () => {}
});

const profileReducer = handleActions({
    [GET_PROFILE]: (state, {payload}) => ({payload})
}, initialState);

export default profileReducer;