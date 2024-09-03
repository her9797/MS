import React from 'react';

function ChatItem({ name, unread, onClick }) {

  const colors = ['indigo', 'gray', 'orange', 'pink', 'purple'];
  const color = colors[name.length % colors.length];

  const titleStyle = {
    whiteSpace: 'nowrap',  
    overflow: 'hidden',  
    textOverflow: 'ellipsis',
    maxWidth: '150px',
  };

  return (
    <button
      className="flex flex-row items-center hover:bg-gray-100 rounded-xl p-2"
      onClick={onClick} // 클릭 시 onClick 핸들러 호출 (roomId)
    >
      <div className={`flex items-center justify-center h-8 w-8 bg-${color}-200 rounded-full`}>
        {name[0]}
      </div>
      <div className="ml-2 text-sm font-semibold" style={titleStyle}>{name}</div>
      {unread && (
        <div className="flex items-center justify-center ml-auto text-xs text-white bg-red-500 h-4 w-4 rounded leading-none">2</div>
      )}
    </button>
  );
}

export default ChatItem;