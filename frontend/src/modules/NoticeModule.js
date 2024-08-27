import { createActions, handleActions } from 'redux-actions';

const initialState = {
    notices: {
        notice: [], 
        totalPages: 0,
        totalItems: 0,
        currentPage: 0
    }
};

export const GET_NOTICE = 'notice/GET_NOTICE';

const actions = createActions({
    [GET_NOTICE]: () => {}
});

const noticeReduer = handleActions({
    [GET_NOTICE]: (state, {payload}) => ({notices: payload})
}, initialState);

export default noticeReduer;