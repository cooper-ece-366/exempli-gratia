import React, { Component } from 'react';
import {getRandomColor} from '../../util/utils';
import { getTime, getStockPrice, getUserPortfolioTickers } from "../../util/APIUtils";
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
            readableNow: null
        };

        this.updateStocksState = this.updateStocksState.bind(this);

        this.refreshTime = this.refreshTime.bind(this);
        this.refreshTicker = this.refreshTicker.bind(this);
        this.refreshStockPrice = this.refreshStockPrice.bind(this);
        this.buttonClickedRefreshTime = this.buttonClickedRefreshTime.bind(this);
        this.buttonClickedRefreshTicker = this.buttonClickedRefreshTicker.bind(this);
        this.buttonClickedRefreshTickerPrice = this.buttonClickedRefreshTickerPrice.bind(this);
        this.buttonClickedReRender = this.buttonClickedReRender.bind(this);
    }

    updateStocksState() {
        let tickerList = null;
        var stockData = [];
        getUserPortfolioTickers().then(response => {
            tickerList = response.tickers;
            tickerList.forEach(function (ticker, index) {
                getStockPrice(ticker).then(response => {
                    console.log(response);
                    let entry = {"ticker": ticker, "price":parseFloat(response.price).toFixed(2)};
                    stockData.push(entry);
                    // stockData[ticker] = parseFloat(response.price).toFixed(2);
                    Alert.success(ticker + " stock price refreshed!");
                }).catch(error => {
                    Alert.error(ticker + " stock price NOT retrieved!");
                });
            });
        }).catch(error => {
            Alert.error(this.state.stocks + " stock list NOT retrieved!");
        });
        this.setState({
            stocks: stockData
        }, () => {console.log(this.state);});

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
        // let tt = getRandomTicker();
        getUserPortfolioTickers().then(response => {
            let tickerList = response.tickers;
            console.log("Tickerlist = " + tickerList);
            let ticker = tickerList[Math.floor(Math.random() * tickerList.length)]
            console.log("Ticker = %s", ticker);
            this.setState({
                ticker: ticker
            }, () => {console.log(this.state);});
        }).catch(error => {
            this.setState({
                loading: false
            }, () => {console.log(this.state);});
            Alert.error(this.state.ticker + " stock price NOT retrieved!");
        });
    }

    refreshStockPrice() {
        // this.refreshTicker();
        getStockPrice(this.state.ticker).then(response => {
            console.log(response);
            this.setState({
                // price: response.quote.price
                price: parseFloat(response.price).toFixed(2)
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
        this.updateStocksState();
        // this.refreshStockPrice();
        // console.log("componentDidMount: state = %o", this.state);
    }

    componentWillUnmount() {
        // console.log("componentWillUnmount: state = %o", this.state);
    }

    componentDidUpdate() {
        // console.log("componentDidUpdate: state = %o", this.state);
    }

    componentWillUpdate() {
        // console.log("componentWillUpdate: state = %o", this.state);
    }

    shouldComponentUpdate() {
        // console.log("shouldComponentUpdate: state = %o", this.state);
        return(true);
    }

    buttonClickedRefreshTime() {
        // console.log('Time Button was clicked!');
        this.refreshTime();
    }

    buttonClickedRefreshTicker() {
        // console.log('Stock Button was clicked!');
        this.refreshTicker();
        // console.log(this.state.ticker);
        // this.refreshStockPrice();
        //this.refreshVersionString();
        //this.refreshQuote();
    }

    buttonClickedRefreshTickerPrice() {
        // console.log('Price Button was clicked!');
        this.refreshStockPrice();
    }

    buttonClickedReRender() {
        this.render();
    }

    render() {
        // this.updateStocksState();
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
                        <div>
                            <table className="table">
                                <thead>
                                <tr>
                                    <th style={{height: '50px'}}>Stock</th>
                                    <th style={{height: '50px'}}>Price</th>
                                </tr>
                                </thead>
                                <tbody>
                                {this.state.stocks.map(obj => {
                                    return (
                                        <tr>
                                            <td style={{backgroundColor: 'white'}}>{obj.ticker}</td>
                                            <td style={{backgroundColor: 'white'}}>{obj.price}</td>
                                        </tr>
                                    );
                                })}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>    
            </div>
        );
    }
}

export default Invest;