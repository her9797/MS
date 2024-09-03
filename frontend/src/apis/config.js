export const API_BASE_URL = 'http://localhost:8080';

export const headers = {
    'Content-Type': 'application/json; charset=UTF-8',
    Accept: '*/*',
    Authorization: 'Bearer ' + window.localStorage.getItem('jwtToken')
};