package com.ms.back.Chatting.repository.JoinedUser;

import com.ms.back.Chatting.entity.JoinedUser;
import com.ms.back.Chatting.entity.Room;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class JoinedUserRepositoryImpl implements JoinedUserRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Room> findRoomsByUserId(String userId) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Room> cq = cb.createQuery(Room.class);
        Root<Room> roomRoot = cq.from(Room.class);

        // 실제 매핑된 필드명을 사용해야 합니다
        Join<Room, JoinedUser> joinedUserJoin = roomRoot.join("joinedUser");

        cq.select(roomRoot)
                .distinct(true) // 중복된 결과 제거
                .where(cb.equal(joinedUserJoin.get("userId"), userId));

        List<Room> rooms = entityManager.createQuery(cq).getResultList();

        // 로깅 추가 (디버깅 용도)
        System.out.println("Number of rooms found: " + rooms.size());
        return rooms;
    }

}
