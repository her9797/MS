### OAuth2 인증 흐름
```
Step 1: 사용자가 Google 로그인 버튼을 클릭
Step 2: 사용자는 Google 인증 페이지로 리디렉션
Step 3: 인증이 성공하면, Google은 리디렉션 URI에 authorization code를 포함하여 클라이언트로 리디렉션
Step 4: 클라이언트는 이 authorization code를 서버로 전송하여 최종적으로 access token을 요청
Step 5: 서버는 authorization code를 사용하여 Google의 토큰 엔드포인트에서 access token을 받음
```

### 용어 정리
```
Authorization Code
- 사용자가 Google 로그인 과정에서 받아오는 짧은 코드.
- 클라이언트 -> 서버

Access Token
- 서버가 Authorization Code를 사용하여 Google의 토큰 엔드포인트에서 받아오는 값.
- 이 토큰을 사용하여 Google API에 접근하거나 사용자 정보를 얻을 수 있음
```