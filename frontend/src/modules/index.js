import { combineReducers } from "redux";
import noticeReducer from "./NoticeModule";
import authReducer from "./AuthModules";
import joinedUserReducer from "./JoinedUserModules";
import roomReducer from "./RoomModules";
import messageReducer from "./MessageModules";
import userReducer from "./UserModules";
import roomAndUserReducer from "./RoomAndUserModules";
import profileReducer from "./ProfileModules";

const rootReducer = combineReducers({
    noticeReducer,
    authReducer,
    joinedUserReducer,
    roomReducer,
    messageReducer,
    userReducer,
    roomAndUserReducer,
    profileReducer
});

export default rootReducer;