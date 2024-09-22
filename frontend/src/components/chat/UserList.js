import React, { useEffect, useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import ChatItem from './ChatItem';
import { callUserListAPI } from '../../apis/UserAPICalls';
import InsertChatModal from './InsertChatModal';

function UserList({ title }) {
  const dispatch = useDispatch();
  const userList = useSelector(state => state.userReducer.payload?.results?.userList) || [];
  const [isModalOpen, setModalOpen] = useState(false);

  useEffect(() => {
    dispatch(callUserListAPI());
  }, [dispatch]);

  const count = userList.length;

  const handleOpenModal = () => {
    setModalOpen(true);
  };

  const handleCloseModal = () => {
    setModalOpen(false);
  };

  return (
    <div className="flex flex-col mt-8">
      <div className="flex flex-row items-center justify-between text-xs">
        <span className="font-bold">{title}</span>
        <span className="flex items-center justify-center bg-gray-300 h-4 w-4 rounded-full">{count}</span>
        <button 
          className="flex items-center justify-center bg-green-300 h-4 w-4 rounded-full"
          onClick={handleOpenModal}
          style={{ marginLeft: '-60%' }}
        >
          +
        </button>
      </div>
      <div className="flex flex-col space-y-1 mt-4 -mx-2 h-48 overflow-y-auto">
        {Array.isArray(userList) && userList.length > 0 ? (
          userList.map((user, index) => (
            <ChatItem key={index} name={user.userName} />
          ))
        ) : (
          <span>No users found</span>
        )}
      </div>
      <InsertChatModal isOpen={isModalOpen} onClose={handleCloseModal}>
      </InsertChatModal>
    </div>
  );
}

export default UserList;
