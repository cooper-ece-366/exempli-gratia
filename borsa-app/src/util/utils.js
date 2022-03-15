import { TICKERS_LIST } from "../constants";

export function getRandomColor() {
    let colorValues = ["red", "blue", "green"];
    return colorValues[Math.floor(Math.random() * colorValues.length)];
};

export function getRandomTicker() {
    let ticker = TICKERS_LIST[Math.floor(Math.random() * TICKERS_LIST.length)];
    // console.log("Choosing randomly %s", ticker);
    return(ticker);
};
