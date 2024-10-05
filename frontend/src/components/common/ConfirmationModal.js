import React from 'react';

const ConfirmationModal = ({ show, onClose, onConfirm, message }) => {
    if (!show) return null; // 모달이 열리지 않을 때는 아무것도 렌더링하지 않음

    return (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50">
            <div className="bg-white rounded-lg shadow-lg p-6 w-96">
                <h2 className="text-lg font-bold mb-4">회원 탈퇴 확인</h2>
                <p className="mb-6">{message}</p>
                <div className="flex justify-end">
                    <button
                        className="mr-2 px-4 py-2 bg-gray-300 text-gray-800 rounded hover:bg-gray-400"
                        onClick={onClose}
                    >
                        취소
                    </button>
                    <button
                        className="px-4 py-2 bg-red-600 text-white rounded hover:bg-red-700"
                        onClick={onConfirm}
                    >
                        회원 탈퇴
                    </button>
                </div>
            </div>
        </div>
    );
};

export default ConfirmationModal;
