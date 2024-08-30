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
  