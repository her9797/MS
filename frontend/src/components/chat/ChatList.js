import React from 'react';
import ChatItem from './ChatItem';

function ChatList({ title, count }) {
  return (
    <div className="flex flex-col mt-8">
      <div className="flex flex-row items-center justify-between text-xs">
        <span className="font-bold">{title}</span>
        <span className="flex items-center justify-center bg-gray-300 h-4 w-4 rounded-full">{count}</span>
      </div>
      <div className="flex flex-col space-y-1 mt-4 -mx-2 h-48 overflow-y-auto">
        {['이인호', '김준표', '이유성', '정환준', '김윤기'].map((name, index) => (
          <ChatItem key={index} name={name} unread={index === 1} />
        ))}
      </div>
    </div>
  );
}

export default ChatList;
