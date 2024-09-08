import { combineReducers } from "redux";
import noticeReducer from "./NoticeModule";
import authReducer from "./AuthModules";
import joinedUserReducer from "./JoinedUserModules";
import roomReducer from "./RoomModules";
import messageReducer from "./MessageModules";

const rootReducer = combineReducers({
    noticeReducer,
    authReducer,
    joinedUserReducer,
    roomReducer,
    messageReducer
});

export default rootReducer;