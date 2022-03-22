export const THE_APP_NAME = "Project Borsa";
export const API_BASE_URL = 'http://localhost:8080';
export const ACCESS_TOKEN = 'accessToken';

export const OAUTH2_REDIRECT_URI = 'http://localhost:3000/oauth2/redirect'

export const GOOGLE_AUTH_URL = API_BASE_URL + '/oauth2/authorize/google?redirect_uri=' + OAUTH2_REDIRECT_URI;
export const FACEBOOK_AUTH_URL = API_BASE_URL + '/oauth2/authorize/facebook?redirect_uri=' + OAUTH2_REDIRECT_URI;
export const GITHUB_AUTH_URL = API_BASE_URL + '/oauth2/authorize/github?redirect_uri=' + OAUTH2_REDIRECT_URI;

export const apiUrlPrefix = "http://localhost:8080";
export const quoteApiUrl = apiUrlPrefix.concat("/api/quote");
export const timeApiUrl = apiUrlPrefix.concat("/api/time");
export const appVersionApiUrl = apiUrlPrefix.concat("/api/version/");
export const TICKERS_LIST = ['AAPL','AMZN','GOOG','NVDA','INTC','FB','ORCL','NFLX'];
export const quoteDelay = 3000; // in milliseconds
export const delay = 5000; // in milliseconds
export const versionDelay = 15000; // in milliseconds
export const tickerDelay = 60000; // in milliseconds
