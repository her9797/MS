import React, { useState } from 'react';
import '../styles/tailwind.css';
import Sidebar from '../pages/Sidebar';
import ChatArea from '../pages/ChatArea';

function Chat() {
  const [roomId, setRoomId] = useState(null);

  // roomId를 설정하는 함수
  const handleRoomSelect = (id) => {
    setRoomId(id);
  };

  return (
    <div className="flex h-screen overflow-hidden">
      <Sidebar onRoomSelect={handleRoomSelect} />
      <ChatArea roomId={roomId} />
    </div>
  );
}

export default Chat;
