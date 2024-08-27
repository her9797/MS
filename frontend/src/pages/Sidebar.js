import React from 'react';
import ChatList from '../components/chat/ChatList';
import UserList from '../components/chat/UserList';

function Sidebar() {
  return (
    <div className="flex flex-col py-8 pl-6 pr-2 w-64 bg-white flex-shrink-0">
      <div className="flex flex-row items-center justify-center h-12 w-full">
        <div className="flex items-center justify-center rounded-2xl text-indigo-700 bg-indigo-100 h-10 w-10">
          <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M8 10h.01M12 10h.01M16 10h.01M9 16H5a2 2 0 01-2-2V6a2 2 0 012-2h14a2 2 0 012 2v8a2 2 0 01-2 2h-5l-5 5v-5z" />
          </svg>
        </div>
        <div className="ml-2 font-bold text-2xl">실시간 채팅</div>
      </div>
      <div className="flex flex-col items-center bg-gray-200 border border-gray-200 mt-4 w-full py-6 px-4 rounded-lg">
        <div className="h-20 w-20 rounded-full border overflow-hidden">
          <img src="https://avatars3.githubusercontent.com/u/2763884?s=128" alt="Avatar" className="h-full w-full" />
        </div>
        <div className="text-sm font-semibold mt-2">내 이름</div>
        <div className="text-xs text-gray-500">내 설명</div>
        
      </div>
      <ChatList title="내 방 목록" count={4} />
      <UserList title="회원 목록" count={7} />
    </div>
  );
}

export default Sidebar;
