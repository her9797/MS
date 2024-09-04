### BACK 
```
- Java
- Spring Boot
- JPA
- SPRING SECURITY JWT
- Websocket
- 소셜 Login (구글,)
```
### FRONT 
```
- REACT
- JAVA SCRIPT
- REDUX
```
### DATA BASE
```
- MySQL
```

### First or Study Used
```
- Valid
- QueryDSL
- MappedSuperclass
- builder
- Scheduling
```
### Development Methodology
```
- TDD
  1. Service : 단위
  2. Controller : 통합

  - Notice
  - Comment
  - Alarm
  - Message
  - ROOM
  - JoinedUser
  - Kafka
```
### Plan
```
WebSocket / Kafka
DB index 설정
```

---

### LOG

- **DOCKER**
  - Docker의 공부를 위해 적극적으로 사용
  - container로 하여 한번에 돌리게 되면 굉장히 편함
  - vsCode (리액트 port:3000)의 경우엔 docker로 하다가 안하고 있음 :: 이유: 아직 docker 활용이 미숙해서, component 수정 시 곧 바로 반영이 되지 않음

- **LOGIN**
  - 스프링 Security / JWT
  - Oauth2 Social Login 구현 하며, jwt 검증 로직 및 google 회원에서 가져온 회원 정보를 검증하여 db에 없으면 저장

- **Chat**
  - 기능 : (Room / JoinedUser / Messages) 
  - kafka : docker를 이용해 kafka, zookeeper server를 동작 → kafka producer / consumer / webSocket 순서로 데이터를 보내며 읽을 수 있도록 함
  - webSocket : 웹소켓 연결 시, 해당 클라이언트의 jwt 를 검증하여, 검증 성공한 클라이언트는 연결 성공
  - @Scheduled : 해당 로직이 msgContent를 검증하여 중복 데이터가 있을 경우, 해당 데이터 1개만 db에 insert 될 수 있도록 함
    -> 참고사항1 : 중복 데이터 조건 (msgContent -> msgContent + userId) 변경 같은 방에서 인원들이 'ㅇㅇ'이라고 입력했을 때 데이터 저장 안되었기 때문
    -> 참고사항2 : db 인덱스 복합으로 주고 데이터 저장된 것 확인했을 때, kafka로 인해 순서대로 저장이 되지 않았음(msgId autoIncrement와 다름) 하여, sort 해주는 작업 추가  
 
- **Notice**
  - 기본중에 기본이라고 할 수 있는 공지사항 게시판
  - 에디터 사용 아직 안했는데, 할 예정 
