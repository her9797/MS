import React from 'react';
import ChatHeader from '../components/chat/ChatHeader';
import ChatMessage from '../components/chat/ChatMessage';
import MessageInput from '../components/chat/MessageInput';

function ChatArea() {
  return (
    <div className="flex flex-col flex-auto h-full p-6">
      <div className="flex flex-col flex-auto flex-shrink-0 rounded-2xl bg-gray-100 h-full p-4">
        <ChatHeader />
        <ChatMessage />
        <MessageInput />
      </div>
    </div>
  );
}

export default ChatArea;
