import { composeWithDevTools } from "redux-devtools-extension";
import rootReducer from "./modules";
import { legacy_createStore as createStore, applyMiddleware } from "redux";
import thunk from 'redux-thunk';

const store = createStore(
    rootReducer,
    composeWithDevTools(applyMiddleware(thunk))
);

export default store;