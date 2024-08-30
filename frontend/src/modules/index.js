import { combineReducers } from "redux";
import noticeReducer from "./NoticeModule";
import authReducer from "./AuthModules";

const rootReducer = combineReducers({
    noticeReducer,
    authReducer
});

export default rootReducer;