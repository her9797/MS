import React, { useState } from 'react';
import '../styles/login.css';
import { useNavigate } from 'react-router-dom';
import SignIn from '../components/login/SignIn';
import SignUp from '../components/login/SignUp';
import { authenticateWithGoogle } from './../apis/AuthAPICalls';

function Login() {
  const [isSignUp, setIsSignUp] = useState(false);
  const navigate = useNavigate();

  const handleSuccess = async (response) => {
    const { credential } = response;
    console.log(response);
    if (credential) {
      try {
        const data = await authenticateWithGoogle(credential);
        localStorage.setItem('jwtToken', data.token);
        console.log('JWT Token:', data.token);
        
        navigate('/');
      } catch (error) {
        console.error('Error:', error);
      }
    } else {
      console.error('Authorization code is undefined');
    }
  };

  return (
    <div className="login">
      <div className="login__content">
        <div className="login__img">
          <img src="/img/login.png" alt="user login" />
        </div>
        <div className="login__forms">
          {!isSignUp ? (
            <SignIn setIsSignUp={setIsSignUp} />
          ) : (
            <SignUp setIsSignUp={setIsSignUp} handleSuccess={handleSuccess} />
          )}
        </div>
      </div>
    </div>
  );
}

export default Login;