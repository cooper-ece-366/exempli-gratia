import React, { Component } from 'react';
import { getRandomColor, getRandomTicker } from '../../util/utils';
import { getTime, getStockPrice } from "../../util/APIUtils";
import LoadingIndicator from '../../common/LoadingIndicator';
import Alert from 'react-s-alert';
import 'react-s-alert/dist/s-alert-default.css';
import 'react-s-alert/dist/s-alert-css-effects/slide.css';
import './Invest.css';

class Invest extends Component {
    constructor(props) {
        super(props);
        this.state = {
            now: null,
            readableNow: null,
            ticker: getRandomTicker(),
            price: null
        };
        this.refreshTime = this.refreshTime.bind(this);
        this.refreshTicker = this.refreshTicker.bind(this);
        this.loadCurrentTicker = this.refreshStockPrice.bind(this);
        this.buttonClickedRefreshTime = this.buttonClickedRefreshTime.bind(this);
        this.buttonClickedRefreshTicker = this.buttonClickedRefreshTicker.bind(this);
        this.buttonClickedRefreshTickerPrice = this.buttonClickedRefreshTickerPrice.bind(this);
        this.buttonClickedReRender = this.buttonClickedReRender.bind(this);
    }

    refreshTime() {
        getTime()
        .then(response => {
            this.setState({
                now: response.datetime
            }, () => {console.log(this.state);});
            var ora = new Date(this.state.now*1000);
            this.setState({
                readableNow: ora.toLocaleString()
            }, () => {console.log(this.state);});
            }).catch(error => {
            this.setState({
                loading: false
            }, () => {console.log(this.state);});
            return; 
        });
        Alert.success("Time refreshed!");
    }

    refreshTicker() {
        let tt = getRandomTicker();
        console.log("Ticker = %s", tt);
        this.setState({
            ticker: tt
        }, () => {console.log(this.state);});
    }

    refreshStockPrice() {
        // this.refreshTicker();
        getStockPrice(this.state.ticker).then(response => {
            this.setState({
                price: response.quote.price
            }, () => {console.log(this.state);});
            Alert.success(this.state.ticker + " stock price refreshed!");
        }).catch(error => {
            this.setState({
                loading: false
              }, () => {console.log(this.state);});
              Alert.error(this.state.ticker + " stock price NOT retrieved!");
        });
    }

    componentDidMount() {
        this.refreshTime();
        this.refreshStockPrice();
        console.log("componentDidMount: state = %o", this.state);
    }

    componentWillUnmount() {
        console.log("componentWillUnmount: state = %o", this.state);
    }

    componentDidUpdate() {
        console.log("componentDidUpdate: state = %o", this.state);
    }

    componentWillUpdate() {
        console.log("componentWillUpdate: state = %o", this.state);
    }

    shouldComponentUpdate() {
        console.log("shouldComponentUpdate: state = %o", this.state);
        return(true);
    }

    buttonClickedRefreshTime() {
        console.log('Time Button was clicked!');
        this.refreshTime();
    }

    buttonClickedRefreshTicker() {
        console.log('Stock Button was clicked!');
        this.refreshTicker();
        console.log(this.state.ticker);
        //this.refreshStockPrice();
        //this.refreshVersionString();
        //this.refreshQuote();
    }

    buttonClickedRefreshTickerPrice() {
        console.log('Price Button was clicked!');
        this.refreshStockPrice();
    }

    buttonClickedReRender() {
        this.render();
    }

    render() {
        if(this.state.loading) {
            return <LoadingIndicator />
        }
        console.log("Render() -> state = %o",this.state);
        return (
            <div className="invest-container">
                <div className="container">
                    <div className="invest-info">
                        <p>The time now is</p>
                        <div style={{background: `${getRandomColor()}`}}>
                            {this.state.readableNow}
                        </div>
                        <p>The last closing price for {this.state.ticker} is</p>
                        <div style={{background: `${getRandomColor()}`}}>
                            $ {this.state.price}
                        </div>
                        <button className="button" onClick={this.buttonClickedReRender}>Click to Re-Render</button>
                        <button className="button" onClick={this.buttonClickedRefreshTime}>Click to Refresh Time</button>
                        <button className="button" onClick={this.buttonClickedRefreshTicker}>Click to Refresh Ticker</button>
                        <button className="button" onClick={this.buttonClickedRefreshTickerPrice}>Click to Refresh Stock Price</button>
                    </div>
                </div>    
            </div>
        );
    }
}

export default Invest;