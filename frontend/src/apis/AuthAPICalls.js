/** Google Login
 * Google 인증 토큰을 서버에 전송하여 사용자 인증을 수행합니다.
 * @param {string} credential - Google 로그인에서 받은 인증 토큰.
 * @returns {Promise<object>} - 서버에서 반환된 데이터.
 */
export const authenticateWithGoogle = async (credential) => {

    try {
      const response = await fetch('http://localhost:8080/auth/google', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ token: credential }),
      });
  
      if (!response.ok) {
        throw new Error('Network response was not ok: ' + response.statusText);
      }
  
      const data = await response.json();
      return data;
    } catch (error) {
      console.error('Error during authentication:', error);
      throw error;
    }
  };
  

/**
 * Kakao 인증 토큰을 서버에 전송하여 사용자 인증을 수행합니다.
 * @param {string} token - Kakao 로그인에서 받은 인증 토큰.
 * @returns {Promise<object>} - 서버에서 반환된 데이터.
 */
export const authenticateWithKakao = async (accessToken) => {
  try {
    const response = await fetch('http://localhost:8080/auth/kakao', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ token: accessToken }) // TokenRequest 클래스와 일치
    });
    
    if (!response.ok) {
      const errorData = await response.json();
      throw new Error(`Network response was not ok: ${errorData.error || response.statusText}`);
    }
    const data = await response.json();
    console.log(data);
    return data;
  } catch (error) {
    console.error('Error during Kakao authentication:', error);
    throw error;
  }
};

  