import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import ChatItem from './ChatItem';
import { callSelectJoinedRoomListAPI } from '../../apis/JoinedUserAPICalls';
import { jwtDecode } from 'jwt-decode';

function ChatList({ title, count, onRoomClick }) {
  const dispatch = useDispatch();
  const rooms = useSelector(state => state.joinedUserReducer.rooms);

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

  return (
    <div className="flex flex-col mt-8">
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
                name={otherUsers.join(', ')}
                unread={room.joinedUserDTO.length > 0}
              />
            );
          })
        ) : (
          <div className="text-gray-500">방이 없습니다</div>
        )}
      </div>
    </div>
  );
}

export default ChatList;
