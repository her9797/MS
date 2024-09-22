import React, { useEffect, useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { callSelectMessageAPI } from '../../apis/MessageAPICalls';

function ChatMessage({ messages, currentUser, roomId }) {
  const dispatch = useDispatch();
  const apiMsg = useSelector(state => state.messageReducer.msg) || { msg: [] };
  const [localMessages, setLocalMessages] = useState(apiMsg.msg || []);
  const [userMessages, setUserMessages] = useState([]);

  // API에서 메시지를 가져오는 효과
  useEffect(() => {
    if (roomId) {
      dispatch(callSelectMessageAPI(roomId));
    }
  }, [dispatch, roomId]);

  // Redux 상태가 변경될 때 상태 업데이트
  useEffect(() => {
    setLocalMessages(apiMsg.msg || []);
  }, [apiMsg]);

  // 새로운 메시지가 변경될 때
  useEffect(() => {
    if (messages?.length) {
      const parsedMessages = messages.map(msg => {
        try {
          return JSON.parse(msg);
        } catch (e) {
          console.error('Error parsing individual message:', e);
          return null;
        }
      }).filter(msg => msg !== null);
      
      // 사용자 메시지만 추가
      setUserMessages(parsedMessages);
    }
  }, [messages]);

  // 최종 메시지 리스트
  const displayMessages = [...localMessages, ...userMessages];

  return (
    <div className="flex flex-col flex-auto h-full overflow-x-auto mb-4">
      <div className="flex flex-col flex-auto">
        {displayMessages.length === 0 ? (
          <div className="text-center text-gray-500">No messages</div>
        ) : (
          displayMessages.map((msg, index) => {
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
                      <div className="text-xs text-gray-600 mb-1">{userEmail}</div>
                      <div className={`p-3 rounded-xl max-w-xs bg-gray-300`}>
                        <p>{msgContent}</p>
                      </div>
                    </>
                  )}
                  {isCurrentUser && (
                    <>
                      <div className="text-xs text-gray-600 mt-1 text-right">{userEmail}</div>
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
