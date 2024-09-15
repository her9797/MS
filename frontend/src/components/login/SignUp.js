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

            // API에서 반환한 메시지가 result.message에 있다고 가정
            const message = result || '회원가입 성공';
            alert(message); // API 반환 메시지 표시

            // 회원가입 성공일 때만 setIsSignUp(false) 호출
            if (message == '회원가입 성공') { // result.success가 true인 경우
                setIsSignUp(false); // 회원가입 성공 후 로그인 화면으로 전환
            }
        } catch (error) {
            console.error('회원가입 오류:', error);

            const message = error?.response?.data || '회원가입 실패';
            alert(message); // API 반환 메시지 표시
        }
    };

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
                    window.Kakao.init(kakaoClientId);
                    console.log('Kakao SDK 초기화 성공');
                };
                script.onerror = () => {
                    console.error('Kakao SDK 로드 실패');
                };
                document.body.appendChild(script);
            } else {
                window.Kakao.init(kakaoClientId);
            }
        };

        loadKakaoSDK();

        return () => {
            if (window.Kakao) {
                window.Kakao = undefined;
                console.log('Kakao SDK 정리됨');
            }
            const script = document.querySelector('script[src="https://developers.kakao.com/sdk/js/kakao.js"]');
            if (script) {
                document.body.removeChild(script);
            }
        };
    }, [kakaoClientId]);

    const kakaoSuccessLogin = async (response) => {
        try {
            const { access_token } = response.response;
            if (access_token) {
                const result = await authenticateWithKakao(access_token);
                localStorage.setItem('jwtToken', result.token);
                console.log(result.token);
                console.log('JWT Token:', result.token);

                dispatch(actions.auth.login({ token: result.token }));

                navigate('/');
            } else {
                console.error('Access token이 정의되지 않았습니다.');
            }
        } catch (error) {
            console.error('Kakao 로그인 오류:', error);
        }
    };

    const kakaoFailureLogin = (error) => {
        console.error('Kakao 로그인 오류:', error);
    };

    return (
        <>
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
        </>
    );
};

export default SignUp;
