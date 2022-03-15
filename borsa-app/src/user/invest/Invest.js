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
            ticker: null,
            price: null
        };
        this.refreshTime = this.refreshTime.bind(this);
        this.loadCurrentTicker = this.refreshStockPrice.bind(this);
    }

    refreshTime() {
        getTime()
        .then(response => {
            this.setState({
                now: response.datetime,
                readableNow: ora.toLocaleString()
            });
        }).catch(error => {
            this.setState({
                loading: false
              });
            return; 
        });
        var ora = new Date(this.state.now*1000);
        this.setState({
            readableNow: ora.toLocaleString()
        });

        Alert.success("Time refreshed!");
    };

    refreshStockPrice() {
        let ticker = getRandomTicker();
        console.log("Ticker = %s", ticker);
        this.setState({
            ticker: getRandomTicker()
        });
        getStockPrice(ticker).then(response => {
            this.setState({
                price: response.quote.price
            });
        }).catch(error => {
            this.setState({
                loading: false
              });          
        });
        Alert.success(ticker + " stock price refreshed!");
    };

    componentDidMount() {
        this.refreshTime();
        this.refreshStockPrice();
      }

    render() {
        if(this.state.loading) {
            return <LoadingIndicator />
        }
        console.log(this.state);
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

                    </div>
                </div>    
            </div>
        );
    }
}

export default Invest;