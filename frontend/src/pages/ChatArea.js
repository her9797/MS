import React, { useEffect, useState } from 'react';
import ChatHeader from '../components/chat/ChatHeader';
import ChatMessage from '../components/chat/ChatMessage';
import MessageInput from '../components/chat/MessageInput';
import { createKafka } from '../apis/KafkaAPICalls';
import {jwtDecode} from 'jwt-decode'; // Ensure this import is present

function ChatArea({roomId}) {
  const [messages, setMessages] = useState([]);
  const [message, setMessage] = useState('');
  const [socket, setSocket] = useState(null);
  const [currentUser, setCurrentUser] = useState('');

  useEffect(() => {
    const token = localStorage.getItem('jwtToken');
    const ws = new WebSocket(`ws://localhost:8080/wss/messages?token=${token}`);

    ws.onopen = () => {
      console.log('WebSocket connection opened');
    };

    ws.onmessage = (event) => {
      console.log('Message from server:', event.data);
      setMessages((prevMessages) => [...prevMessages, event.data]);
    };

    ws.onerror = (error) => {
      console.error('WebSocket error:', error);
    };

    ws.onclose = () => {
      console.log('WebSocket connection closed');
    };

    setSocket(ws);

    return () => {
      if (ws) {
        ws.close();
      }
    };
  }, []);

  useEffect(() => {
    const token = localStorage.getItem('jwtToken');
    if (token) {
      try {
        const decodedToken = jwtDecode(token);
        setCurrentUser(decodedToken.email || '');
      } catch (error) {
        console.error('Error decoding token:', error);
      }
    }
  }, []);

  const handleSendMessage = async (msg) => {
    await createKafka(msg, roomId);
    setMessage(''); 
  };

  return (
    <div className="flex flex-col flex-auto h-full p-6">
      <div className="flex flex-col flex-auto flex-shrink-0 rounded-2xl bg-gray-100 h-full p-4">
        <ChatHeader roomId={roomId} />
        <ChatMessage messages={messages} currentUser={currentUser} />
        <MessageInput
          message={message}
          setMessage={setMessage}
          onSend={handleSendMessage} // 메시지 전송
        />
      </div>
    </div>
  );
}

export default ChatArea;
