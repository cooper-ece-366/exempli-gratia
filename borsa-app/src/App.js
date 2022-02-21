import React, { useState } from 'react';
import useInterval from './useInterval';
import react_logo from './react-logo.svg';
import spring_java_logo from './spring-boot-java.jpg';
import './App.css';

function getRandomColor() {
  let colorValues = ["red", "blue", "green"];
  return colorValues[Math.floor(Math.random() * colorValues.length)];
}

function App() {
  const apiUrlPrefix = "http://localhost:8080";
  const tickers = ['AAPL','AMZN','GOOG','NVDA','INTC','FB','ORCL','NFLX'];
  const [currentTime, setCurrentTime] = useState(Date.now());
  const [currentReadableTime, setCurrentReadableTime] = useState('0');
  const [currentTicker, setCurrentTicker] = useState('-');
  const [currentStockPrice, setCurrentStockPrice] = useState('0');
  const [currentVersionString, setCurrentVersionString] = useState('<null>');
  const [quoteText, setQuoteText] = useState('To be or not to be.');
  const [quoteAuthor, setQuoteAuthor] = useState('William Shakespeare');

  const quoteDelay = 3000; // in milliseconds
  const delay = 5000; // in milliseconds
  const versionDelay = 15000; // in milliseconds
  const tickerDelay = 60000; // in milliseconds

  const quoteApiUrl = apiUrlPrefix.concat("/api/quote");
  App.refreshQuote = () => {
    console.log("Refreshing ... quote ...");
    fetch(quoteApiUrl)
      .then(response => response.json())
      .then(data => {
        setQuoteText(data.quote);
        setQuoteAuthor(data.author);
        console.log(data);
      })
      .catch(err => {
        console.log("Cannot connect to API endpoint: %s", quoteApiUrl);
      });
    console.log("Refreshed quote.");  
  }
  useInterval(() => {
    App.refreshQuote();
  }, quoteDelay);

  const timeApiUrl = apiUrlPrefix.concat("/api/time");
  App.refreshTime = () => {
    console.log("Refreshing ... time ...");
    fetch(timeApiUrl)
      .then(response => response.json())
      .then(data => {
        setCurrentTime(data.datetime);
        //let ora = new Intl.DateTimeFormat('en-US', { year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit', second: '2-digit' }).format(currentTime * 1000);
        var ora = new Date(currentTime*1000)
        setCurrentReadableTime(ora.toLocaleString());
        console.log(currentReadableTime);
      })
      .catch(err => {
        console.log("Cannot connect to API endpoint: %s", timeApiUrl);
      });
    console.log("Refreshed time.");  
  }
  useInterval(() => {
    App.refreshTime();
  }, delay);

  App.refreshStockPrice = () => {
    console.log("Refreshing ... %s stock price ...", currentTicker);
    var ticker = tickers[Math.floor(Math.random() * tickers.length)];
    setCurrentTicker(ticker);
    var stockApiUrl = apiUrlPrefix.concat("/api/stock/",ticker);
      fetch(stockApiUrl)
      .then(response => response.json())
      .then(data => {
        setCurrentStockPrice(parseFloat(data.quote.previousClose + data.quote.change).toFixed(2));
        console.log(data);
      })
      .catch(err => {
        console.log("Cannot connect to API endpoint: %s", stockApiUrl);
      });
    console.log("Refreshed %s stock price.", currentTicker);
  }
  useInterval(() => {
    App.refreshStockPrice();
  }, tickerDelay);

  const appVersionApiUrl = apiUrlPrefix.concat("/api/version/");
  App.refreshVersionString = () => {
    console.log("Refreshing ... random string ...");
    fetch(appVersionApiUrl)
      .then(response => response.json())
      .then(data => {
        setCurrentVersionString(data.build_version + data.maven_version);
        console.log(data);
      })
      .catch(err => {
        console.log("Cannot connect to API endpoint: %s", appVersionApiUrl);
      });
    console.log("Refreshed random string.");  
  }
  useInterval(() => {
    App.refreshVersionString();
  }, versionDelay);

  App.buttonClicked = () => {
    console.log('Button was clicked!');
    App.refreshTime();
    App.refreshStockPrice();
    App.refreshVersionString();
    App.refreshQuote();
  }

  return (
    <div className="App">
      <header className="App-header">
      <img src={react_logo} className="App-logo" alt="react-logo" />
        <img src={spring_java_logo} className="Platform-logo" alt="spring_java_logo" />
        <a
          className="App-link"
          href="https://github.com/cooper-ece-366/exempli-gratia"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn how to spin React with Java & Spring Boot with API endpoints
        </a>
        <br></br>
        <button className="button" onClick={App.buttonClicked}>Click to Refresh</button>
        <p>The current time is</p>
        <div style={{background: `${getRandomColor()}`}}>
          {currentReadableTime}
        </div>
        <p>The last closing price for ${currentTicker} is</p>
        <div style={{background: `${getRandomColor()}`}}>
          $ {currentStockPrice}
        </div>
        <p>The current running version string is</p>
        <div style={{background: `${getRandomColor()}`}}>
          {currentVersionString}
        </div>
        <p>The current quote is</p>
        <div style={{background: `${getRandomColor()}`}}>
          <blockquote>
          <p>{quoteText}</p>
          <footer>— <cite>{quoteAuthor}</cite>
          </footer>
          </blockquote>
        </div>
        <p></p>
      </header>
    </div>
  );
}

export default App;
