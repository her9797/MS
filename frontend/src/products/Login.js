import React, { useState } from 'react';
import '../styles/login.css';  // CSS 파일 import
import { Link } from 'react-router-dom/dist';


function Login() {
  const [isSignUp, setIsSignUp] = useState(false);

  return (
    <div className="login">
      <div className="login__content">
        <div className="login__img">
          <img src="/img/login.png" alt="user login" />
        </div>
        <div className="login__forms">
          {/* Sign In Form */}
          {!isSignUp && (
            <form action="" className="login__register" id="login-in">
              <h1 className="login__title">Sign In</h1>
              <Link to="/" href="#" className="login__forgot">Home</Link>
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
                <span className="login__signin login__signin--signup" onClick={() => setIsSignUp(true)}>  Sign Up</span>
              </div>
            </form>
          )}

          {/* Sign Up Form */}
          {isSignUp && (
            <form action="" className="login__create" id="login-up">
              <h1 className="login__title">Create Account</h1>
              <Link to="/" href="#" className="login__forgot">Home</Link>
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
                <span className="login__signup login__signup--signup" onClick={() => setIsSignUp(false)}>  Sign In</span>
              </div>
              <div className="login__social">
                <a href="#" className="login__social--icon"><i className='bx bxl-facebook'></i></a>
                <a href="#" className="login__social--icon"><i className='bx bxl-twitter'></i></a>
                <a href="#" className="login__social--icon"><i className='bx bxl-google'></i></a>
                <a href="#" className="login__social--icon"><i className='bx bxl-github'></i></a>
              </div>
            </form>
          )}
        </div>
      </div>
    </div>
  );
}

export default Login;
