import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { callSelectRoomAndUserListAPI } from '../../apis/RoomAPICalls'; // Adjust the import path as needed
import { jwtDecode } from 'jwt-decode';

function ChatHeader({ roomId }) {
  const dispatch = useDispatch();
  const [userNames, setUserNames] = useState([]);
  const [userCount, setUserCount] = useState(0);

  const roomAndUser = useSelector(state => state.roomReducer.roomAndUser);

  useEffect(() => {
    if (roomId) {
      dispatch(callSelectRoomAndUserListAPI(roomId));
    }
  }, [dispatch, roomId]);

  useEffect(() => {
    if (roomAndUser.roomAndUser) {
      const { joinedUserDTO } = roomAndUser.roomAndUser;
      if (joinedUserDTO) {
        // Filter out the current user (assuming we have the current user's ID)
        const currentUserId = localStorage.getItem('jwtToken') && jwtDecode(localStorage.getItem('jwtToken')).email;
        const otherUsers = joinedUserDTO
          .filter(user => user.userId !== currentUserId)
          .map(user => user.userId);

        setUserNames(otherUsers);
        setUserCount(otherUsers.length);
        console.log(otherUsers);
      }
    }
  }, [roomAndUser]);

  return (
    <div className="flex flex-row items-center border-b border-gray-300 pb-2 mb-4">
      <div className="h-12 w-12 rounded-full border overflow-hidden">
        <img src="https://avatars3.githubusercontent.com/u/2763884?s=128" alt="Avatar" className="h-full w-full" />
      </div>
      <div className="ml-4 flex flex-col">
        <span className="font-semibold text-lg">{userNames.join(', ') || '상대 회원 이름'}</span>
        <span className="text-xs text-gray-500">방 인원: {userCount || 0}</span>
      </div>
    </div>
  );
}

export default ChatHeader;
