import React from 'react';
import { Link } from 'react-router-dom';
import { GoogleOAuthProvider, GoogleLogin } from '@react-oauth/google';

const SignUp = ({ setIsSignUp, handleSuccess }) => {
    const googleClientId = process.env.REACT_APP_GOOGLE_CLIENT_ID;

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
                        onSuccess={handleSuccess} 
                        onError={(error) => console.error('Google Sign-In Error:', error)}
                    />
                </GoogleOAuthProvider>
            </div>
        </form>
    );
};

export default SignUp;
