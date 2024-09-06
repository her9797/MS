import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { callInsertNoticeAPI, callSelectNoticeAPI } from '../../apis/NoticeAPICalls';
import { jwtDecode } from 'jwt-decode';

const PostNtcModal = ({ isOpen, onClose }) => {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const dispatch = useDispatch();
    const token = localStorage.getItem('jwtToken');

    if (!isOpen) return null;

    const decodedToken = jwtDecode(token); // JWT 디코딩

    const handleRegister = async () => {
        // 제목과 내용이 비어 있는지 확인
        if (!title.trim() || !content.trim()) {
            alert('제목과 내용은 필수 입력 사항입니다.');
            return;
        }

        try {
            const noticeDTO = {
                title,
                content,
                userId: decodedToken.email  
            };
            await dispatch(callInsertNoticeAPI(noticeDTO));
            dispatch(callSelectNoticeAPI());
            console.log(noticeDTO);
            onClose(); // 모달 닫기
            // 상태 초기화
            setTitle('');
            setContent('');
        } catch (error) {
            console.error('공지사항 등록 실패', error);
            alert('공지사항 등록에 실패했습니다. 다시 시도해 주세요.');
        }
    };

    return (
        <div className="fixed inset-0 flex items-center justify-center bg-gray-800 bg-opacity-50">
            <div className="bg-white p-6 rounded-lg shadow-lg w-full relative" style={{ maxWidth: '50%', maxHeight: '80%' }}>
                <h2 className="text-lg font-semibold mb-4">공지사항 등록</h2>
                <div className="space-y-4">
                    <div className="flex items-center space-x-4">
                        <label className="text-gray-700 w-24 flex-shrink-0">제목</label>
                        <input
                            type="text"
                            className="border border-gray-300 p-2 rounded flex-1"
                            placeholder="제목을 입력해주세요"
                            value={title}
                            onChange={(e) => setTitle(e.target.value)}
                        />
                    </div>
                    <div className="flex items-center space-x-4">
                        <label className="text-gray-700 w-24 flex-shrink-0">내용</label>
                        <textarea
                            className="border border-gray-300 p-2 rounded flex-1"
                            placeholder="내용을 입력하세요"
                            style={{ height: '200px', resize: 'none', boxSizing: 'border-box' }}
                            value={content}
                            onChange={(e) => setContent(e.target.value)}
                        />
                    </div>
                </div>
                <div className="mt-6 flex justify-end space-x-4">
                    <button
                        onClick={onClose}
                        className="bg-gray-300 text-gray-700 px-4 py-2 rounded hover:bg-gray-400"
                    >
                        취소
                    </button>
                    <button
                        onClick={handleRegister}
                        className="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600"
                    >
                        등록
                    </button>
                </div>
            </div>
        </div>
    );
};

export default PostNtcModal;
