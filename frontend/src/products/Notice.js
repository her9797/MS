import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { callSelectNoticeAPI } from '../apis/NoticeAPICalls';
import '../styles/notice.css';

function Notice() {
    const dispatch = useDispatch();
    const noticeData = useSelector(state => state.noticeReducer.notices || {}); // 기본값 설정
    const { notice = [], totalPages = 0, totalItems = 0 } = noticeData; // 구조 분해 할당

    console.log(notice)

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

    

    return (
        <section>
            <h1>무한스크롤이라 페이징이 필요가 없네</h1>
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
                                <tr key={index}>
                                    <td>{item.noticeNo}</td> 
                                    <td>{item.title}</td> 
                                    <td>{item.content}</td> 
                                    <td>{item.userId}</td>
                                    <td>{formatDate(item.createdDate)}</td>
                                </tr>
                            ))
                        ) : (
                            <tr>
                                <td colSpan="4">공지사항 없음</td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
        </section>
    );
}

export default Notice;
