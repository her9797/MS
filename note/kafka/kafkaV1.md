### #1 Kafka
```
Version : 3.7.0 (스프링부트와 맞는지 체크 필요)
```

### TEST (WINDOW)
``` 
Zookeeper 실행
- cd C:\tool\kafka_2.13-3.8.0
- .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

Kafka 서버 실행
- cd C:\tool\kafka_2.13-3.8.0
- .\bin\windows\kafka-server-start.bat .\config\server.properties

Kafka 테스트

- cd C:\tool\kafka_2.13-3.8.0

토픽 생성
- .\bin\windows\kafka-topics.bat --create --topic test1 --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1

토픽 확인
- .\bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092 (확인)

메시지 전송
- .\bin\windows\kafka-console-producer.bat --topic test --bootstrap-server localhost:9092

메시지 읽기
- .\bin\windows\kafka-console-consumer.bat --topic test --from-beginning --bootstrap-server localhost:9092

서버 종료 ctrl + c
```