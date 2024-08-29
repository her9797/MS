// JWT를 로컬스토리지에 저장
export const setToken = (token) => {
    localStorage.setItem('token', token);
};

// 로컬스토리지에서 JWT를 가져오기
export const getToken = () => {
    return localStorage.getItem('token');
};

// 로컬스토리지에서 JWT 삭제
export const removeToken = () => {
    localStorage.removeItem('token');
};

// JWT의 만료 여부를 검토
export const isTokenExpired = (token) => {
    if (!token) return true;

    const [, payloadBase64] = token.split('.');
    const payload = JSON.parse(atob(payloadBase64));

    // 토큰 만료 시간을 초 단위로 변환하여 비교
    return payload.exp * 1000 < Date.now();
};
