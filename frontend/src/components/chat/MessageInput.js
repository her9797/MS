import React from 'react';

function MessageInput({ message, setMessage, onSend }) {
  const handleChange = (e) => {
    setMessage(e.target.value);
  };

  const handleSend = () => {
    if (message.trim()) {
      onSend(message);
      setMessage('');
    }
  };

  return (
    <div className="flex flex-row items-center h-16 rounded-xl bg-white w-full px-4">
      <div className="flex-grow ml-4 relative">
        <input
          type="text"
          className="flex w-full border rounded-xl focus:outline-none focus:border-indigo-300 pl-4 h-10"
          placeholder="Type a message..."
          value={message}
          onChange={handleChange}
        />
        <button
          className="absolute flex items-center justify-center h-full w-12 right-0 top-0 text-gray-400 hover:text-gray-600"
        >
          <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M14.828 14.828a4 4 0 01-5.656 0M9 10h.01M15 10h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
          </svg>
        </button>
      </div>
      <button className="ml-4 text-gray-400 hover:text-gray-600"
        onClick={handleSend}
      >
        <svg className="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
          <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M17 9l-5 5m0 0l-5-5m5 5V3" />
        </svg>
      </button>
    </div>
  );
}

export default MessageInput;
