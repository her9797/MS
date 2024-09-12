import React, { useEffect } from 'react';
import { Link } from 'react-router-dom';
import { GoogleOAuthProvider, GoogleLogin } from '@react-oauth/google';
import { useNavigate } from 'react-router-dom';
import KakaoLogin from 'react-kakao-login';
import { useDispatch } from 'react-redux';
import { actions } from '../../modules/AuthModules';
import { authenticateWithKakao } from '../../apis/AuthAPICalls';

const SignUp = ({ setIsSignUp, handleSuccess }) => {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const googleClientId = process.env.REACT_APP_GOOGLE_CLIENT_ID;
    const kakaoClientId = process.env.REACT_APP_KAKAO_CLIENT_ID;

    const successLogin = (response) => {
        if (response.credential) {
            dispatch(actions.auth.login({ token: response.credential }));
            handleSuccess(response);
            console.log(response);
        }
    };

    useEffect(() => {
        if (!window.Kakao) {
            const script = document.createElement('script');
            script.src = 'https://developers.kakao.com/sdk/js/kakao.js';
            script.async = true;
            script.onload = () => {
                window.Kakao.init(kakaoClientId); // 카카오 클라이언트 아이디로 초기화
                console.log('Kakao SDK initialized successfully');
            };
            script.onerror = () => {
                console.error('Failed to load Kakao SDK');
            };
            document.body.appendChild(script);
        } else {
            window.Kakao.init(kakaoClientId); // SDK가 이미 로드되었다면 초기화
        }
    }, [kakaoClientId]);

    const kakaoSuccessLogin = async (response) => {
        try {
            const { access_token } = response.response;
            if (access_token) {
                const result = await authenticateWithKakao(access_token);
                localStorage.setItem('jwtToken', result.token);
                console.log(result.token);
                console.log('JWT Token:', result.token);
                
                // Dispatch login action to update Redux state
                dispatch(actions.auth.login({ token: result.token }));

                // Navigate to the home page
                navigate('/');
            } else {
                console.error('Access token is undefined');
            }
        } catch (error) {
            console.error('Kakao Login Error:', error);
        }
    };

    const kakaoFailureLogin = (error) => {
        console.error('Kakao Sign-In Error:', error);
    };

    return (
        <form action="" className="login__create" id="login-up">
            <h1 className="login__title">Create Account</h1>
            <Link to="/" className="login__forgot">Home</Link>
            <div className="login__box">
                <i className='bx bx-user login__icon'></i>
                <input type="text" placeholder="Username" className="login__input" />
            </div>
            <div className="login__box">
                <i className='bx bx-at login__icon'></i>
                <input type="text" placeholder="Email" className="login__input" />
            </div>
            <div className="login__box">
                <i className='bx bx-lock login__icon'></i>
                <input type="password" placeholder="Password" className="login__input" />
            </div>
            <a href="#" className="login__button">Sign Up</a>
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
