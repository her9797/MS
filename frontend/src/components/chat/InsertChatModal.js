import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { callUserListAPI } from '../../apis/UserAPICalls';
import { callPostRoomAndUserAPI } from '../../apis/RoomAndUserAPICalls';
import { jwtDecode } from 'jwt-decode';
import { callSelectJoinedRoomListAPI } from '../../apis/JoinedUserAPICalls';

function InsertChatModal({ isOpen, onClose }) {
    const dispatch = useDispatch();
    const [selectedMembers, setSelectedMembers] = useState([]);
    const memberList = useSelector(state => state.userReducer.payload?.results?.userList) || [];
    const token = localStorage.getItem('jwtToken');
    const decodedToken = jwtDecode(token);
    const userEmail = decodedToken.email;

    useEffect(() => {
        if (isOpen) {
            dispatch(callUserListAPI());
            document.body.style.overflow = 'hidden';
        } else {
            document.body.style.overflow = 'auto';
        }

        return () => {
            document.body.style.overflow = 'auto';
        };
    }, [isOpen, dispatch]);

    const handleSelection = (memberEmail) => {
        setSelectedMembers(prev => {
            if (prev.includes(memberEmail)) {
                return prev.filter(email => email !== memberEmail);
            } else {
                return [...prev, memberEmail].slice(0, 10);
            }
        });
    };

    const handleSubmit = async () => {
        const allMembers = [...selectedMembers, userEmail];
        const joinedUserDTO = allMembers.map(email => ({
            userEmail: email,
            createdAt: new Date().toISOString(),
        }));

        const roomAndUserDTO = {
            roomDTO: {},
            joinedUserDTO: joinedUserDTO,
        };

        const result = await dispatch(callPostRoomAndUserAPI(roomAndUserDTO));
        console.log("방 생성 결과:", result);

        if (result) {
            // 방 목록을 다시 가져오는 API 호출
            const response = await dispatch(callSelectJoinedRoomListAPI(userEmail));
            console.log("업데이트된 방 목록:", response.results);
            // 필요한 경우 상태를 업데이트 할 수 있습니다.
        }

        onClose();
    };

    const handleClose = () => {
        setSelectedMembers([]);
        onClose();
    };

    if (!isOpen) return null;

    return (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
            <div className="bg-white p-4 rounded shadow-lg w-1/3" style={{ maxHeight: '70vh' }}>
                <h2 className="text-lg font-bold mb-4">방 만들기</h2>
                <div className="overflow-y-auto" style={{ maxHeight: '50vh', marginRight: '-2%' }}>
                    <div className="flex flex-col space-y-2">
                        {memberList.length > 0 ? (
                            memberList.map((member) => (
                                <div key={member.userEmail} className="flex items-center justify-between border-b py-2">
                                    <span className="flex-grow">{member.userName} ({member.userEmail})</span>
                                    <input
                                        type="radio"
                                        value={member.userEmail}
                                        checked={selectedMembers.includes(member.userEmail)}
                                        onChange={() => handleSelection(member.userEmail)}
                                        className="ml-4"
                                        style={{ marginRight: '2%' }}
                                    />
                                </div>
                            ))
                        ) : (
                            <span>회원을 찾을 수 없습니다</span>
                        )}
                    </div>
                </div>
                <div className="mt-4 flex justify-end space-x-2">
                    <button
                        className="bg-green-500 text-white px-4 py-2 rounded"
                        onClick={handleSubmit}
                        disabled={selectedMembers.length === 0}
                    >
                        생성
                    </button>
                    <button
                        className="bg-gray-500 text-white px-4 py-2 rounded"
                        onClick={handleClose}
                    >
                        닫기
                    </button>
                </div>
            </div>
        </div>
    );
}

export default InsertChatModal;
