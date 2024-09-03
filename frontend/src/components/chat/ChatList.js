import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import ChatItem from './ChatItem';
import { callSelectJoinedRoomListAPI } from '../../apis/RoomAPICalls';
import { jwtDecode } from 'jwt-decode';

function ChatList({ title, count }) {
  const dispatch = useDispatch();
  const rooms = useSelector(state => state.roomReducer.rooms); // Redux 상태에서 방 리스트 가져오기

  const token = localStorage.getItem('jwtToken');
  const decodedToken = jwtDecode(token); // JWT 디코딩
  const userId = decodedToken.email;

  useEffect(() => {
    if (userId) {
      dispatch(callSelectJoinedRoomListAPI(userId));
    }
  }, [dispatch, userId]);

  // rooms.results에서 방 목록 추출, results가 존재하지 않으면 빈 배열로 기본 설정
  const roomList = rooms.results || []; // results가 정의되지 않은 경우 빈 배열로 기본 설정

  return (
    <div className="flex flex-col mt-8">
      <div className="flex flex-row items-center justify-between text-xs">
        <span className="font-bold">{title}</span>
        <span className="flex items-center justify-center bg-gray-300 h-4 w-4 rounded-full">{count}</span>
      </div>
      <div className="flex flex-col space-y-1 mt-4 -mx-2 h-48 overflow-y-auto">
        {roomList.length > 0 ? (
          roomList.map((room, index) => {
            // 현재 userId를 제외한 다른 사용자 목록 필터링
            const otherUsers = room.joinedUserDTO
              .filter(user => user.userId !== userId) // 현재 userId 제외
              .map(user => user.userId); // userId 추출

            return (
              <ChatItem
                key={index}
                name={otherUsers.join(', ')} // 나머지 사용자 ID를 쉼표로 구분된 문자열로 결합
                unread={room.joinedUserDTO.length > 0} // joinedUserDTO의 길이에 따라 읽지 않은 상태 추정
              />
            );
          })
        ) : (
          <div className="text-gray-500">방이 없습니다</div> // 방이 없는 경우 메시지
        )}
      </div>
    </div>
  );
}

export default ChatList;
