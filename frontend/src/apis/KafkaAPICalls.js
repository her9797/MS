import { jwtDecode } from 'jwt-decode';

export const createKafka = async (msg, roomId) => {
    const token = localStorage.getItem('jwtToken');
    let decodedToken = {};

    if (token) {
        try {
            decodedToken = jwtDecode(token); // JWT 디코딩
        } catch (error) {
            console.error('Error decoding token:', error);
        }
    }

    const userId = decodedToken.email || ''; // 디코딩된 이메일이 없으면 빈 문자열
    const userName = decodedToken.name || ''; // 디코딩된 이름이 없으면 빈 문자열

    try {
        const response = await fetch('http://localhost:8080/kafka/messages', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify({
                topic: 'test1',
                msgContent: msg,
                userId,
                userName,
                roomId
            })
        });

        // 응답을 텍스트로 읽어 콘솔에 출력
        const textResponse = await response.text();

        // 응답이 성공적이지 않으면 에러 발생
        if (!response.ok) {
            throw new Error('Network response was not ok.');
        }

        // 응답이 JSON 형식인 경우에만 JSON으로 파싱
        if (response.headers.get('Content-Type')?.includes('application/json')) {
            try {
                return JSON.parse(textResponse);
            } catch (jsonError) {
                return textResponse;
            }
        } else {
            // JSON이 아닌 경우, 텍스트로 반환
            return textResponse;
        }
    } catch (error) {
        console.error('Error sending message to Kafka:', error);
        throw error;
    }
};
