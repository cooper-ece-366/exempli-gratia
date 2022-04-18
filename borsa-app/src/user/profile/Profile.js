import React, { Component } from 'react';
import './Profile.css';
// import LoadingIndicator from "../../common/LoadingIndicator";
import {ACCESS_TOKEN, API_BASE_URL} from "../../constants";

class Profile extends Component {
    constructor(props) {
        super(props);
        console.log("Props = %o",this);
    }

    buttonClickedCreatePortfolio() {
        if(!localStorage.getItem(ACCESS_TOKEN)) {
            return Promise.reject("No access token set.");
        }
        // Send data to the backend via POST
        // fetch('http://------------:8080/', {  // Enter your IP address here
        //
        //     method: 'POST',
        //     mode: 'cors',
        //     body:  // body data type must match "Content-Type" header
        // })
        // return request({
        //     url: API_BASE_URL + "/portfolio/ticker" + stock,
        //     method: 'POST'
        // });
    }

    render() {
        return (
            <div className="profile-container">
                <div className="container">
                    <div className="profile-info">
                        <div className="profile-avatar">
                            { 
                                this.props.currentUser.imageUrl ? (
                                    <img src={this.props.currentUser.imageUrl} alt={this.props.currentUser.name}/>
                                ) : (
                                    <div className="text-avatar">
                                        <span>{this.props.currentUser.name && this.props.currentUser.name[0]}</span>
                                    </div>
                                )
                            }
                        </div>
                        <div className="profile-name">
                           <h2>{this.props.currentUser.name}</h2>
                           <p className="profile-email">{this.props.currentUser.email}</p>
                        </div>
                    </div>
                    <div className="profile-portfolio">
                        <h2>{this.props.currentUser.name}'s Portfolio</h2>
                        <input
                            type="text"
                            value={this.props.value}
                            onChange={event => console.log("value changed!")}
                        />
                        <button className="button" onClick={this.buttonClickedCreatePortfolio}>Click to Re-Render</button>                        <p>// place for errors</p>
                    </div>
                </div>    
            </div>
        );
    }
}

export default Profile;