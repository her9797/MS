import '../styles/tailwind.css';
import Sidebar from '../pages/Sidebar';
import ChatArea from '../pages/ChatArea';

function Chat() {
  return (
    <div className="flex h-screen overflow-hidden">
      <Sidebar />
      <ChatArea />
    </div>
  );
}

export default Chat;
