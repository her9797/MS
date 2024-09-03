import { combineReducers } from "redux";
import noticeReducer from "./NoticeModule";
import authReducer from "./AuthModules";
import joinedUserReducer from "./ChatModules";
import roomReducer from "./RoomModules";

const rootReducer = combineReducers({
    noticeReducer,
    authReducer,
    joinedUserReducer,
    roomReducer
});

export default rootReducer;