package com.ms.back.Notice.repository;


import com.ms.back.Notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

/** JpaRepository 상속받아 @Repository 를 따로 안해줘도 됨 */
public interface NoticeRepository extends JpaRepository<Notice, Integer> {

    Notice findByNoticeNo(int noticeNo);

    /** @Repository 를 하면 DataAccessException 계층 예외로 변환하여 일관되도록 예외처리를 할 수 있도록 해줌
     *  데이터 베이스 연결 오류 시, 발생할 수 있는 SQLException / PersistenceException 발생 처리를 위함
     * */

}
