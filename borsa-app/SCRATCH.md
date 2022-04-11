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