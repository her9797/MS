import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { callLoginUserAPI } from '../../apis/UserAPICalls'; 
import { actions } from '../../modules/AuthModules';

const SignIn = ({ setIsSignUp }) => {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const userDTO = {
                userEmail: email,
                userPwd: password
            };
            const result = await dispatch(callLoginUserAPI(userDTO));
            console.log('로그인 성공:', result.token);
            localStorage.setItem('jwtToken', result.token)
            dispatch(actions.auth.login({ token: result }));
            navigate('/');
        } catch (error) {
            console.error('로그인 실패:', error);
            setError('로그인 실패. 이메일과 비밀번호를 확인해 주세요.');
        }
    };

    return (
        <form onSubmit={handleSubmit} className="login__register" id="login-in">
            <h1 className="login__title">Sign In</h1>
            <Link to="/" className="login__forgot">Home</Link>
            <div className="login__box">
                <i className='bx bx-user login__icon'></i>
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
            {error && <p className="error-message">{error}</p>}
            <button type="submit" className="login__button">Sign In</button>
            <div>
                <span className="login__account login__account--account">Don't Have an Account?</span>
                <span className="login__signin login__signin--signup" onClick={() => setIsSignUp(true)}> Sign Up</span>
            </div>
        </form>
    );
};

export default SignIn;
