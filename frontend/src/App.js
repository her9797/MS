import { BrowserRouter, Routes, Route } from "react-router-dom";
import Layout from "./layouts/Layout";
import Main from "./products/Main";
import Login from "./products/Login";
import Chat from "./products/Chat";

function App() {
  return (
    <BrowserRouter>
    
      <Routes>
        <Route path="login" element={<Login />} />
        <Route path="/" element={<Layout />}>
          <Route index element={<Main />} />
          <Route path="/" element={<Main />} />
          <Route path="chat" element={<Chat />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
