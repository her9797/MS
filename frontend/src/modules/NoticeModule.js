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
export const POST_NOTICE = 'notice/POST_NOTICE';


const actions = createActions({
    [GET_NOTICE]: () => {},
    [POST_NOTICE]: () => {}
});

const noticeReduer = handleActions({
    [GET_NOTICE]: (state, {payload}) => ({notices: payload}),
    [POST_NOTICE]: (state, {payload}) => ({notice: payload})
}, initialState);

export default noticeReduer;