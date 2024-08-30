import React from 'react';
import { Link } from 'react-router-dom';

const SignIn = ({ setIsSignUp }) => {
    
    return (
        <form action="" className="login__register" id="login-in">
            <h1 className="login__title">Sign In</h1>
            <Link to="/" className="login__forgot">Home</Link>
            <div className="login__box">
                <i className='bx bx-user login__icon'></i>
                <input type="text" placeholder="Username" className="login__input" />
            </div>
            <div className="login__box">
                <i className='bx bx-lock login__icon'></i>
                <input type="password" placeholder="Password" className="login__input" />
            </div>
            <a href="#" className="login__forgot">Forgot Password? </a>
            <a href="#" className="login__button">Sign In</a>
            <div>
                <span className="login__account login__account--account">Don't Have an Account?</span>
                <span className="login__signin login__signin--signup" onClick={() => setIsSignUp(true)}> Sign Up</span>
            </div>
        </form>
    );
};

export default SignIn;
