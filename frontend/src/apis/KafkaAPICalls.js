import { jwtDecode } from 'jwt-decode';


export const createKafka = async (msg) => {
    const token = localStorage.getItem('jwtToken');
    let decodedToken = {};

    if (token) {
        try {
            decodedToken = jwtDecode(token); // JWT 디코딩
            console.log(decodedToken); // 디코딩된 정보를 콘솔에 출력
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
                userName
            })
        });

        if (!response.ok) {
            throw new Error('Network response was not ok.');
        }

        return await response.json();
    } catch (error) {
        console.error('Error sending message to Kafka:', error);
        throw error;
    }
};