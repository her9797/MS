### Docker : SpringBoot + kafka With WebSocket 연동

### 1. Docker Desktop
### 2. docker-compose.yml 작성
```
version: '3.8'
services:
  zookeeper:
    image: wurstmeister/zookeeper:latest
    container_name: zookeeper
    ports:
      - "2181:2181"
  kafka:
    image: wurstmeister/kafka:latest
    container_name: kafka
    ports:
      - "9092:9092"
    environment:
      KAFKA_ADVERTISED_HOST_NAME: 127.0.0.1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
    volumes:
      - /var/run/docker.sock:/var/run/docker.sock
      
-------------------------------------------------------------
docker-compose.yml에 작성된 내용은 다음과 같다.

ZooKeeper 서비스

image: wurstmeister/zookeeper:latest를 사용.
container_name: zookeeper로 설정.
ports: 2181 포트를 호스트와 매핑.

Kafka 서비스
image: wurstmeister/kafka:latest를 사용.
container_name: kafka로 설정.
ports: 9092 포트를 호스트와 매핑.
environment: Kafka의 광고 호스트 이름을 127.0.0.1로 설정하고, ZooKeeper와의 연결을 zookeeper:2181로 설정.
volumes: Docker 소켓을 컨테이너에 마운트.
```

### 3. Container 실행
```
1. 컨테이너 실행
docker-compose up -d

2. 컨테이너 상태 확인
docker-compose ps

3. zookeeper 로그 확인
docker-compose logs zookeeper

4. kafka 로그 확인
docker-compose logs kafka
```

### 4. PostMan 을 이용한 테스트
```
1. 웹소켓 연결
2. 내 PostMapping 으로 바디에 값을 넘겨주며 http 요청
3. Producer -> Consumer를 거치며 WebSocket 도착
4. Scheduling으로 보낸 시간으로부터 5분 후, 데이터 DB 저장
```
