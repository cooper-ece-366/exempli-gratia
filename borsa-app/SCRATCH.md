# SCRATCH.md

```javascript
    componentDidMount() {
        console.log("*** componentDidMount: state = %o", this.state);
        // fetch("http://localhost:8080/portfolios/portfolio/assets")
        //     .then(res => res.json())
        //     .then(
        //         (result) => {
        //             this.setState({
        //                 isLoaded: true,
        //                 tickers: result.items
        //             });
        //         },
        //         // Note: it's important to handle errors here
        //         // instead of a catch() block so that we don't swallow
        //         // exceptions from actual bugs in components.
        //         (error) => {
        //             this.setState({
        //                 isLoaded: true,
        //                 error
        //             });
        //         }
        //     );
        this.refreshTime();
        this.refreshTickerList();
    }
```

```javascript
   updateStocksState() {
        console.log(">>>>>> updateStocksState() called")
        this.state.tickers.forEach((ticker) => {
            console.log(ticker);
            getStockPrice(ticker).then(response => {
                let entry = {"ticker": ticker, "price":parseFloat(response.price).toFixed(2)};
                this.setState({
                    stocks: [...this.state.stocks,entry]
                });
                Alert.success(ticker + " stock price refreshed!");
            }).catch(error => {
                Alert.error(ticker + " stock price NOT retrieved!");
            });
        });



        // let tickerList = null;
        // var stockData = [];
        // getUserPortfolioTickers().then(response => {
        //     tickerList = response.tickers;
        //     this.setState({
        //         tickers: tickerList
        //     });
        // }).catch(error => {
        //     Alert.error(this.state.stocks + " stock list NOT retrieved!");
        // });
        // this.setState({
        //     stocks: stockData
        // }, () => {console.log(this.state);});
        //     tickerList.forEach(function (ticker, index) {
        //         getStockPrice(ticker).then(response => {
        //             console.log("_____ " + response);
        //             let entry = {"ticker": ticker, "price":parseFloat(response.price).toFixed(2)};
        //             stockData.push(entry);
        //             // stockData[ticker] = parseFloat(response.price).toFixed(2);
        //             Alert.success(ticker + " stock price refreshed!");
        //         }).catch(error => {
        //             Alert.error(ticker + " stock price NOT retrieved!");
        //         });
        //     });
        // }).catch(error => {
        //     Alert.error(this.state.stocks + " stock list NOT retrieved!");
        // });
        // this.setState({
        //     stocks: stockData
        // }, () => {console.log(this.state);});
```