import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { callSelectNoticeAPI } from '../apis/NoticeAPICalls';
import '../styles/notice.css';
import PostNtcModal from '../components/notice/postNtcModal';
import NtcModal from '../components/notice/ntcModal';

function Notice() {
    const dispatch = useDispatch();
    const noticeData = useSelector(state => state.noticeReducer.notices || {}); // 기본값 설정
    const { notice = [], totalPages = 0, totalItems = 0 } = noticeData; // 구조 분해 할당
    const [isModalOpen, setModalOpen] = useState(false);
    const [selectedNotice, setSelectedNotice] = useState(null); // 선택된 공지사항 저장 상태

    console.log(notice);

    useEffect(() => {
        const page = 1;
        const size = 10;
        const deleteYn = "Y";
        dispatch(callSelectNoticeAPI(page, size, deleteYn));
    }, [dispatch]);

    const formatDate = (dateString) => {
        const date = new Date(dateString);
        return date.toISOString().split('T')[0]; // "YYYY-MM-DD" 형식으로 변환
    };

    const handleRowClick = (notice) => {
        setSelectedNotice(notice);
        setModalOpen(true);
    };

    const handleCloseModal = () => {
        setModalOpen(false);
        setSelectedNotice(null); // 모달 닫을 때 선택된 공지사항 초기화
    };

    return (
        <section>
            <h1>개발자가 공지사항 전달드립니다.</h1>
            <div>
                <button
                    className='btn btn-primary'
                    style={{ marginBottom: '10px', marginLeft: '95%' }}
                    onClick={() => setModalOpen(true)}
                >
                    등록하기
                </button>
                <PostNtcModal isOpen={isModalOpen} onClose={handleCloseModal} />
            </div>
            <div className="tbl-header">
                <table cellPadding="0" cellSpacing="0" border="0">
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>내용</th>
                            <th>작성자</th>
                            <th>작성일자</th>
                        </tr>
                    </thead>
                </table>
            </div>
            <div className="tbl-content">
                <table cellPadding="0" cellSpacing="0" border="0">
                    <tbody>
                        {notice.length > 0 ? (
                            notice.map((item, index) => (
                                <tr key={index} onClick={() => handleRowClick(item)}>
                                    <td>{item.noticeNo}</td>
                                    <td>{item.title}</td>
                                    <td>{item.content}</td>
                                    <td>{item.userId}</td>
                                    <td>{formatDate(item.createdDate)}</td>
                                </tr>
                            ))
                        ) : (
                            <tr>
                                <td colSpan="5">공지사항 없음</td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
            {selectedNotice && (
                <NtcModal
                    isOpen={isModalOpen}
                    onClose={handleCloseModal}
                    notice={selectedNotice} // 선택된 공지사항 전달
                />
            )}
        </section>
    );
}

export default Notice;
