import React from 'react';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Provider } from 'react-redux';
import store from './Store';
import Layout from "./layouts/Layout";
import Main from "./products/Main";
import Login from "./products/Login";
import Chat from "./products/Chat";
import Notice from "./products/Notice";
import AccountSettings from './pages/AccountSettings';

function App() {
  return (
    <Provider store={store}>
      <BrowserRouter>
        <Routes>
          <Route path="login" element={<Login />} />
          <Route path="/" element={<Layout />}>
            <Route index element={<Main />} />
            <Route path="notice" element={<Notice />} />
            <Route path="chat" element={<Chat />} />
            <Route path="accountSettings" element={<AccountSettings />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </Provider>
  );
}

export default App;
