import React from 'react';

function ChatHeader() {
  return (
    <div className="flex flex-row items-center border-b border-gray-300 pb-2 mb-4">
      <div className="h-12 w-12 rounded-full border overflow-hidden">
        <img src="https://avatars3.githubusercontent.com/u/2763884?s=128" alt="Avatar" className="h-full w-full" />
      </div>
      <div className="ml-4 flex flex-col">
        <span className="font-semibold text-lg">상대 회원 이름</span>
        <span className="text-xs text-gray-500">방에 함께하는 회원 숫자</span>
      </div>
    </div>
  );
}

export default ChatHeader;
