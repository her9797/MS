import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import ChatItem from './ChatItem';
import { callUserListAPI } from '../../apis/UserAPICalls';

function UserList({ title }) {
  const dispatch = useDispatch();
  const userList = useSelector(state => state.userReducer.payload?.results?.userList) || []; // Access the nested userList

  useEffect(() => {
    dispatch(callUserListAPI());
  }, [dispatch]);

  // Calculate the count based on the userList length
  const count = userList.length;

  return (
    <div className="flex flex-col mt-8">
      <div className="flex flex-row items-center justify-between text-xs">
        <span className="font-bold">{title}</span>
        <span className="flex items-center justify-center bg-gray-300 h-4 w-4 rounded-full">{count}</span>
      </div>
      <div className="flex flex-col space-y-1 mt-4 -mx-2 h-48 overflow-y-auto">
        {Array.isArray(userList) && userList.length > 0 ? (
          userList.map((user, index) => (
            <ChatItem key={index} name={user.userName} unread={user.userStatus !== "ACTIVE"} />
          ))
        ) : (
          <span>No users found</span> // Fallback UI
        )}
      </div>
    </div>
  );
}

export default UserList;
