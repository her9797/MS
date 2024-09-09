import React, { useEffect, useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { callSelectMessageAPI } from '../../apis/MessageAPICalls';

function ChatMessage({ messages, currentUser, roomId }) {
  const dispatch = useDispatch();
  const apiMsg = useSelector(state => state.messageReducer.msg) || { msg: [] }; // Redux 상태에서 메시지 가져오기
  const [localMessages, setLocalMessages] = useState(apiMsg); // 상태로 메시지 저장

  // API에서 메시지를 가져오는 효과
  useEffect(() => {
    if (roomId) {
      dispatch(callSelectMessageAPI(roomId)); // API 호출
    }
  }, [dispatch, roomId]);

  // Redux 상태가 변경될 때 상태 업데이트
  useEffect(() => {
    setLocalMessages(apiMsg);
  }, [apiMsg]);

  // 새로운 메시지를 문자열 배열에서 객체 배열로 변환하여 상태에 추가
  useEffect(() => {
    if (messages && messages.length > 0) {
      try {
        // 배열의 각 항목을 JSON으로 파싱
        const parsedMessages = messages.map(msg => {
          try {
            return JSON.parse(msg);
          } catch (e) {
            console.error('Error parsing individual message:', e);
            return null; // 파싱 오류가 발생한 경우, null을 반환하거나 적절히 처리
          }
        }).filter(msg => msg !== null); // null 값을 필터링

        // 상태 업데이트: 새로운 메시지를 기존 메시지 배열에 추가
        setLocalMessages(prevMessages => ({
          ...prevMessages,
          msg: [...prevMessages.msg, ...parsedMessages]
        }));
      } catch (e) {
        console.error('Error processing messages:', e);
      }
    }
  }, [messages]);

  return (
    <div className="flex flex-col flex-auto h-full overflow-x-auto mb-4">
      <div className="flex flex-col flex-auto">
        {localMessages.length === 0 ? (
          <div className="text-center text-gray-500">No messages</div>
        ) : (
          localMessages.msg.map((msg, index) => {
            const { msgContent, userEmail } = msg;
            const isCurrentUser = userEmail === currentUser;

            return (
              <div
                key={index}
                className={`flex ${isCurrentUser ? 'justify-end' : 'justify-start'} mb-2`}
              >
                <div className="flex flex-col items-start">
                  {!isCurrentUser && (
                    <>
                      <div className="text-xs text-gray-600 mb-1">
                        {userEmail}
                      </div>
                      <div className={`p-3 rounded-xl max-w-xs bg-gray-300`}>
                        <p>{msgContent}</p>
                      </div>
                    </>
                  )}
                  {isCurrentUser && (
                    <>
                      <div className="text-xs text-gray-600 mt-1 text-right">
                        {userEmail}
                      </div>
                      <div className={`p-3 rounded-xl max-w-xs bg-blue-300`}>
                        <p>{msgContent}</p>
                      </div>
                    </>
                  )}
                </div>
              </div>
            );
          })
        )}
      </div>
    </div>
  );
}

export default ChatMessage;
