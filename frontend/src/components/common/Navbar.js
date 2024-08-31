import React, { useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import { actions } from '../../modules/AuthModules';

const NavBar = () => {
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const isLoggedIn = useSelector(state => state.authReducer.isLoggedIn);

    useEffect(() => {
        // isLoggedIn이 false로 변경되면 navigate('/main') 호출
        if (!isLoggedIn) {
            navigate('/');
        }
    }, [isLoggedIn, navigate]);

    const handleLogout = () => {
        // 로컬 스토리지에서 토큰 제거
        localStorage.removeItem('jwtToken');
        // 로그아웃 액션 디스패치
        alert('로그아웃 되었습니다');
        dispatch(actions.auth.logout()); // 로그아웃 액션 호출
    };

    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
            <div className="container px-5">
                <a className="navbar-brand" href="/">DeBug</a>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul className="navbar-nav ms-auto mb-2 mb-lg-0">
                        <li className="nav-item"><a className="nav-link active" aria-current="page" href="#!">Home</a></li>
                        <li className="nav-item"><a className="nav-link" href="#!">Community</a></li>
                        <Link to="/notice" className="nav-item"><a className="nav-link" href="#!">Notice</a></Link>
                        {isLoggedIn ? (
                            <>
                                <li className="nav-item">
                                    <a className="nav-link" onClick={handleLogout} style={{ cursor: 'pointer' }}>Logout</a>
                                </li>
                                <Link to="/chat" style={{ textDecoration: 'none' }}><a className="nav-link">Chat</a></Link>
                            </>
                        ) : (
                            <Link to="/login" style={{ textDecoration: 'none' }}><a className="nav-link">Login</a></Link>
                        )}
                    </ul>
                </div>
            </div>
        </nav>
    );
};

export default NavBar;
