import React from 'react';
import { useDispatch } from 'react-redux';
import { callDeleteNoticeAPI, callSelectNoticeAPI } from '../../apis/NoticeAPICalls';

const NtcModal = ({ isOpen, onClose, notice }) => {
    const dispatch = useDispatch();
    if (!isOpen || !notice) return null;
    const noticeNo = notice.noticeNo;

    
    const handleDeleteNotice = async () => {
        try {
            // 비동기 작업을 기다리도록 수정
            await dispatch(callDeleteNoticeAPI(noticeNo));
            await dispatch(callSelectNoticeAPI());
            onClose(); // 모달 닫기
        } catch (error) {
            console.error('공지사항 삭제 실패', error);
        }
    };


    return (
        <div className="fixed inset-0 flex items-center justify-center bg-gray-800 bg-opacity-50">
            <div className="bg-white p-6 rounded-lg shadow-lg w-full relative" style={{ maxWidth: '50%', maxHeight: '80%' }}>
                <h2 className="text-lg font-semibold mb-4">공지사항 상세</h2>
                <div className="space-y-4">
                    <div className="flex flex-col space-y-2">
                        <label className="text-gray-700">제목</label>
                        <input
                            type="text"
                            className="border border-gray-300 p-2 rounded w-full"
                            value={notice.title}
                            readOnly
                        />
                    </div>
                    <div className="flex flex-col space-y-2">
                        <label className="text-gray-700">작성자</label>
                        <input
                            type="text"
                            className="border border-gray-300 p-2 rounded w-full"
                            value={notice.userEmail}
                            readOnly
                        />
                    </div>
                    <div className="flex flex-col space-y-2">
                        <label className="text-gray-700">작성일자</label>
                        <input
                            type="text"
                            className="border border-gray-300 p-2 rounded w-full"
                            value={new Date(notice.createdDate).toISOString().split('T')[0]}
                            readOnly
                        />
                    </div>
                    <div className="flex flex-col space-y-2">
                        <label className="text-gray-700">내용</label>
                        <textarea
                            className="border border-gray-300 p-2 rounded w-full"
                            value={notice.content}
                            readOnly
                            style={{ height: '200px', resize: 'none', boxSizing: 'border-box' }}
                        />
                    </div>
                </div>
                <div className="mt-6 flex justify-end space-x-4">
                    <button
                        onClick={onClose}
                        className="bg-gray-300 text-gray-700 px-4 py-2 rounded hover:bg-gray-400"
                    >
                        닫기
                    </button>
                    <button
                        className="bg-red-400 text-gray-700 px-4 py-2 rounded hover:bg-red-500"
                        onClick={handleDeleteNotice}
                    >
                        삭제
                    </button>
                </div>
            </div>
        </div>
    );
};

export default NtcModal;
