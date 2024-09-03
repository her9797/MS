import React from 'react';

function ChatMessage({ messages, currentUser }) {
  return (
    <div className="flex flex-col flex-auto h-full overflow-x-auto mb-4">
      <div className="flex flex-col flex-auto">
        {messages.map((msg, index) => {
          // JSON 문자열을 객체로 파싱
          let parsedMsg = {};
          try {
            parsedMsg = JSON.parse(msg); // JSON 문자열을 객체로 변환
          } catch (e) {
            console.error('Error parsing JSON:', e);
            parsedMsg = { msgContent: msg, userId: '' }; // JSON 파싱에 실패하면 기본값 사용
          }

          // 현재 사용자와 메시지의 사용자 ID를 비교
          const isCurrentUser = parsedMsg.userId === currentUser;

          return (
            <div
              key={index}
              className={`flex ${isCurrentUser ? 'justify-end' : 'justify-start'} mb-2`}
            >
              <div
                className={`p-3 rounded-xl max-w-xs ${
                  isCurrentUser ? 'bg-blue-300' : 'bg-gray-300'
                }`}
              >
                <p>{parsedMsg.msgContent || msg}</p> {/* 객체에서 msgContent를 추출 */}
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
}

export default ChatMessage;
