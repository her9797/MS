import React from 'react';
import { Link } from 'react-router-dom';

const NavBar = () => {

    return (
        
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container px-5">
                <a class="navbar-brand" href="/">DeBug</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="#!">Home</a></li>
                        {/* <li class="nav-item"><a class="nav-link" href="#!">Introduce</a></li> */}
                        <li class="nav-item"><a class="nav-link" href="#!">Community</a></li>
                        <li class="nav-item"><a class="nav-link" href="#!">Notice</a></li>
                        <Link to="/login" style={{textDecoration: 'none'}} ><a class="nav-link" >Login</a></Link>
                        <Link to="/chat" style={{textDecoration: 'none'}} ><a class="nav-link" >Chat</a></Link>
                    </ul>
                </div>
            </div>
        </nav>
    );
};

export default NavBar;
