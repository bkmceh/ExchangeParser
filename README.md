# exchange-parser

## 💾 Information
This program receives information about the price of the last executed deal by the ticker.

The address is requested through interaction with the **_Moscow Exchange API_**

The program simulates the work of the client, as well as the response from the server.

The client simulation is located in `inforest/parserexchanger/client`

The server code is located in `inforest/parserexchanger/server`

### ❓ What does the client do?
The client enters the name of the ticker into the console and sends a get request with the name of the ticker on address:
```
http://localhost:8080/api/get?ticker=tickerName
```
If successful, it receives a JSON response.
Example:
```json5
{
  "response": {
    "time": "2021-09-09 18:48:17",
    "ticker_name": "AFLT",
    "last_price": "66.62"
  }
}
```

### ❓ What does the server do?
The server receives a get request from our client.
Then the server makes a get request to the **_Moscow Exchange API_** and returns a response.

## 📝 How to run
Just clone the repository to yourself
- Run `ParserExchangerApplication.java`
- Run `client/Main.java`
