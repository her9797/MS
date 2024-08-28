import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { callSelectNoticeAPI } from '../../apis/NoticeAPICalls';


function Card() {
    const dispatch = useDispatch();
    const noticeData = useSelector(state => state.noticeReducer.notices || {}); // 기본값 설정
    const { notice = [], totalPages = 0, totalItems = 0 } = noticeData; // 구조 분해 할당

    useEffect(() => {
        const page = 1;
        const size = 10;
        const deleteYn = "N";
        dispatch(callSelectNoticeAPI(page, size, deleteYn));
    }, [dispatch]);

    const displayedNotices = notice.slice(0, 3); // 3개까지만 보이도록 제한

    return (
        <div className="row gx-4 gx-lg-5">
            {displayedNotices.length === 0 ? (
                <p>공지사항이 없습니다.</p> // 데이터가 없을 때 표시
            ) : (
                displayedNotices.map(noticeItem => (
                    <div className="col-md-4 mb-5" key={noticeItem.noticeNo}>
                        <div className="card h-100">
                            <div className="card-body" >
                                <h2 className="card-title"
                                    style={{
                                        fontWeight: 'bold',
                                        fontSize: '20px',
                                        whiteSpace: 'nowrap',  // 한 줄로 표시
                                        overflow: 'hidden',     // 넘치는 부분 숨기기
                                        textOverflow: 'ellipsis', // 넘치는 부분에 ... 표시
                                    }}>
                                    {noticeItem.title}
                                </h2>
                                <p className="card-text">
                                    {noticeItem.content}
                                </p>
                            </div>
                            <div className="card-footer">
                                <a className="btn btn-primary btn-sm" href="#!">상세보기</a>
                            </div>
                        </div>
                    </div>
                ))
            )}
        </div>
    );
}

export default Card;