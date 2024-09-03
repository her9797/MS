import React from 'react';

function ChatMessage({ messages }) {
  return (
    <div className="flex flex-col flex-auto h-full overflow-x-auto mb-4">
      <div className="flex flex-col flex-auto">
        <div className="grid grid-cols-12 gap-y-2">
          {messages.map((msg, index) => {
            // JSON 문자열을 객체로 파싱
            let parsedMsg = {};
            try {
              parsedMsg = JSON.parse(msg); // JSON 문자열을 객체로 변환
            } catch (e) {
              console.error('Error parsing JSON:', e);
              parsedMsg = { msgContent: msg }; // JSON 파싱에 실패하면 원본 메시지를 사용
            }
            return (
              <div
                key={index}
                className={`col-start-1 col-end-8 bg-green-200 p-3 rounded-xl ${index % 2 === 0 ? 'self-start' : 'self-end'}`}
              >
                <p>{parsedMsg.msgContent || msg}</p> {/* 객체에서 msgContent를 추출 */}
              </div>
            );
          })}
        </div>
      </div>
    </div>
  );
}

export default ChatMessage;
