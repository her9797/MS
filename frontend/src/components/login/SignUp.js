import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { GoogleOAuthProvider, GoogleLogin } from '@react-oauth/google';
import { useNavigate } from 'react-router-dom';
import KakaoLogin from 'react-kakao-login';
import { useDispatch } from 'react-redux';
import { actions } from '../../modules/AuthModules';
import { authenticateWithKakao } from '../../apis/AuthAPICalls';
import { callPostUserAPI } from '../../apis/UserAPICalls';

const SignUp = ({ setIsSignUp, handleSuccess, onLogout }) => {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const googleClientId = process.env.REACT_APP_GOOGLE_CLIENT_ID;
    const kakaoClientId = process.env.REACT_APP_KAKAO_CLIENT_ID;

    // 상태 변수 정의
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleSignUp = async (e) => {
        e.preventDefault(); // 폼 기본 제출 방지

        const userDTO = {
            userName: username,
            userEmail: email,
            userPwd: password
        };

        console.log('디스패치 호출 전:', userDTO);

        try {
            const result = await dispatch(callPostUserAPI(userDTO));
            console.log('회원가입 성공:', result);
            // 회원가입 성공 후 로그인 화면으로 전환
            setIsSignUp(false);
        } catch (error) {
            console.error('회원가입 오류:', error);
        }
    };

    // Google 로그인 성공 핸들러
    const successLogin = (response) => {
        if (response.credential) {
            dispatch(actions.auth.login({ token: response.credential }));
            handleSuccess(response);
            console.log(response);
        }
    };

    useEffect(() => {
        const loadKakaoSDK = () => {
            if (!window.Kakao) {
                const script = document.createElement('script');
                script.src = 'https://developers.kakao.com/sdk/js/kakao.js';
                script.async = true;
                script.onload = () => {
                    window.Kakao.init(kakaoClientId); // 카카오 SDK 초기화
                    console.log('Kakao SDK 초기화 성공');
                };
                script.onerror = () => {
                    console.error('Kakao SDK 로드 실패');
                };
                document.body.appendChild(script);
            } else {
                window.Kakao.init(kakaoClientId); // SDK가 이미 로드된 경우 초기화
            }
        };

        loadKakaoSDK();

        return () => {
            // 컴포넌트 언마운트 시 Kakao SDK 정리
            if (window.Kakao) {
                window.Kakao = undefined; // Kakao 참조 제거
                console.log('Kakao SDK 정리됨');
            }
            const script = document.querySelector('script[src="https://developers.kakao.com/sdk/js/kakao.js"]');
            if (script) {
                document.body.removeChild(script); // 스크립트 태그 제거
            }
        };
    }, [kakaoClientId]);

    // 카카오 로그인 성공 핸들러
    const kakaoSuccessLogin = async (response) => {
        try {
            const { access_token } = response.response;
            if (access_token) {
                const result = await authenticateWithKakao(access_token);
                localStorage.setItem('jwtToken', result.token);
                console.log(result.token);
                console.log('JWT Token:', result.token);

                // Redux 상태 업데이트
                dispatch(actions.auth.login({ token: result.token }));

                // 홈 페이지로 이동
                navigate('/');
            } else {
                console.error('Access token이 정의되지 않았습니다.');
            }
        } catch (error) {
            console.error('Kakao 로그인 오류:', error);
        }
    };

    // 카카오 로그인 실패 핸들러
    const kakaoFailureLogin = (error) => {
        console.error('Kakao 로그인 오류:', error);
    };

    return (
        <form onSubmit={handleSignUp} className="login__create" id="login-up">
            <h1 className="login__title">Create Account</h1>
            <Link to="/" className="login__forgot">Home</Link>
            <div className="login__box">
                <i className='bx bx-user login__icon'></i>
                <input
                    type="text"
                    placeholder="Username"
                    className="login__input"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />
            </div>
            <div className="login__box">
                <i className='bx bx-at login__icon'></i>
                <input
                    type="text"
                    placeholder="Email"
                    className="login__input"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                />
            </div>
            <div className="login__box">
                <i className='bx bx-lock login__icon'></i>
                <input
                    type="password"
                    placeholder="Password"
                    className="login__input"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
            </div>
            <button type="submit" className="login__button">Sign Up</button>
            <div>
                <span className="login__account login__account--account">Already have an Account?</span>
                <span className="login__signup login__signup--signup" onClick={() => setIsSignUp(false)}> Sign In</span>
            </div>
            <div className="login__social">
                <GoogleOAuthProvider clientId={googleClientId}>
                    <GoogleLogin
                        onSuccess={successLogin}
                        onError={(error) => console.error('Google Sign-In Error:', error)}
                    />
                </GoogleOAuthProvider>
                <KakaoLogin
                    jsKey={kakaoClientId}
                    onSuccess={kakaoSuccessLogin}
                    onError={kakaoFailureLogin}
                    style={{ width: '200px', height: '40px', background: '#F7E02F', color: '#3E3E3E', borderRadius: '5px', textAlign: 'center', lineHeight: '40px', cursor: 'pointer' }}
                >
                    카카오 로그인
                </KakaoLogin>
            </div>
        </form>
    );
};

export default SignUp;
