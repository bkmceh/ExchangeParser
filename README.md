# exchange-parser

## 💾 Information
This program receives information about the price of the last executed deal by the ticker.

The address is requested through interaction with the **_Moscow Exchange API_**.

### ❓ How to send request?
Request sends on address with _**ticker**_ path parameters:
```
http://localhost:8080/api/get
```

Example curl request:
```
curl "http://localhost:8080/api/get?ticker=aflt"
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
The server receives a get request.
Then the server makes a get request to the **_Moscow Exchange API_** and returns a response.

## 📝 How to run
### Main dependencies
- Java - 11
- Maven - 4.0.0
### Building
For building the project, write this command in terminal into the root directory of your project.
```
mvn clean install
```
### Running
For running the assembled project, write this command in terminal into the root
directory of your project.
```
java -jar .\target\ParserExchanger-1.1.jar
```