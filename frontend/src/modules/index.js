import { combineReducers } from "redux";
import noticeReducer from "./NoticeModule";
import authReducer from "./AuthModules";
import roomReducer from "./ChatModules";

const rootReducer = combineReducers({
    noticeReducer,
    authReducer,
    roomReducer
});

export default rootReducer;