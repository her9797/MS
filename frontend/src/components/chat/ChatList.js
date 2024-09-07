import React, { useEffect, useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import ChatItem from './ChatItem';
import { callSelectJoinedRoomListAPI } from '../../apis/JoinedUserAPICalls';
import { jwtDecode } from 'jwt-decode';
import '../../styles/chatList.css';

function ChatList({ title, count, onRoomClick }) {
  const dispatch = useDispatch();
  const rooms = useSelector(state => state.joinedUserReducer.rooms);

  const [contextMenu, setContextMenu] = useState(null);
  const [selectedRoomId, setSelectedRoomId] = useState(null);

  const token = localStorage.getItem('jwtToken');
  const decodedToken = jwtDecode(token);
  const userId = decodedToken.email;

  useEffect(() => {
    if (userId) {
      dispatch(callSelectJoinedRoomListAPI(userId));
    }
  }, [dispatch, userId]);

  const roomList = rooms.results || [];

  const handleRoomClick = (roomId) => {
    if (onRoomClick) {
      onRoomClick(roomId);
    }
  };

  const handleContextMenu = (event, roomId) => {
    event.preventDefault(); // Prevent the default context menu
    setSelectedRoomId(roomId);
    
    setContextMenu({
      top: event.clientY - 400, 
      left: event.clientX - 150, 
    });
  };

  const handleMenuClick = (action) => {
    if (action === 'leave') {
      console.log(`Leave room ${selectedRoomId}`);
      // 여기에 방을 나가는 로직 추가
    }
    setContextMenu(null);
  };

  return (
    <div className="relative flex flex-col mt-8">
      <div className="flex flex-row items-center justify-between text-xs">
        <span className="font-bold">{title}</span>
        <span className="flex items-center justify-center bg-gray-300 h-4 w-4 rounded-full">{count}</span>
      </div>
      <div className="flex flex-col space-y-1 mt-4 -mx-2 h-48 overflow-y-auto">
        {roomList.length > 0 ? (
          roomList.map((room) => {
            const otherUsers = room.joinedUserDTO
              .filter(user => user.userId !== userId)
              .map(user => user.userId);

            return (
              <ChatItem
                key={room.roomDTO.roomId}
                onClick={() => handleRoomClick(room.roomDTO.roomId)}
                onContextMenu={(e) => handleContextMenu(e, room.roomDTO.roomId)}
                name={otherUsers.join(', ')}
                unread={room.joinedUserDTO.length > 0}
              />
            );
          })
        ) : (
          <div className="text-gray-500">방이 없습니다</div>
        )}
      </div>

      {contextMenu && (
        <div 
          className="absolute bg-white border border-gray-300 shadow-lg rounded" 
          style={{ top: contextMenu.top, left: contextMenu.left }}
          onMouseLeave={() => setContextMenu(null)}
        >
          <button className="block px-4 py-2 text-gray-700 hover:bg-gray-100" onClick={() => handleMenuClick('leave')}>
            방 나가기
          </button>
        </div>
      )}
    </div>
  );
}

export default ChatList;
