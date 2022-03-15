import React, { Component } from 'react';
import { Link, NavLink } from 'react-router-dom';
import {THE_APP_NAME} from "../constants";
import './AppHeader.css';

class AppHeader extends Component {
    render() {
        return (
            <header className="app-header">
                <div className="container">
                    <div className="app-branding">
                        <Link to="/" className="app-title">{THE_APP_NAME}</Link>
                    </div>
                    <div className="app-options">
                        <nav className="app-nav">
                                { this.props.authenticated ? (
                                    <ul>
                                        <li>
                                            <NavLink to="/invest">Invest</NavLink>
                                        </li>
                                        <li>
                                            <NavLink to="/profile">Profile</NavLink>
                                        </li>
                                        <li>
                                            <a onClick={this.props.onLogout}>Logout</a>
                                        </li>
                                    </ul>
                                ): (
                                    <ul>
                                        <li>
                                            <NavLink to="/login">Login</NavLink>        
                                        </li>
                                        <li>
                                            <NavLink to="/signup">Signup</NavLink>        
                                        </li>
                                    </ul>
                                )}
                        </nav>
                    </div>
                </div>
            </header>
        )
    }
}

export default AppHeader;