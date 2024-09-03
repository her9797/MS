# Node.js 이미지를 기반으로 합니다
FROM node:16

# 애플리케이션 디렉터리 생성
WORKDIR /app

# 패키지 파일 복사
COPY package*.json ./

# 패키지 설치
RUN npm install

# 애플리케이션 소스 복사
COPY . .

# 빌드
RUN npm run build

# 컨테이너가 수신할 포트 지정
EXPOSE 3000

# 컨테이너가 시작할 명령어
CMD ["npm", "start"]
