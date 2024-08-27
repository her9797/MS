import React from 'react';

function ChatMessage() {
  return (
    <div className="flex flex-col flex-auto h-full overflow-x-auto mb-4">
      <div className="flex flex-col flex-auto">
        <div className="grid grid-cols-12 gap-y-2">
          {/* Placeholder for chat messages */}
          <div className="col-start-1 col-end-8 bg-blue-200 p-3 rounded-xl">
            <p>메시지</p>
          </div>
          <div className="col-start-6 col-end-13 bg-gray-200 p-3 rounded-xl">
            <p>메시지 ㅎㅇㅎㅇ</p>
          </div>
        </div>
      </div>
    </div>
  );
}

export default ChatMessage;
