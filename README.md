# react-native-openpay

An Android and iOS Openpay sdk wrapper for React Native.

Right now it only suports creating card tokens and getting the device session id as i have no need to  
create a card, any PR is welcome to extend the funtionality of this library or maybe fix an issue 🎉.

## Installing

```
yarn add react-native-openpay
```

- if your version of `react-native` is below v0.60, also do the following:

then link the library to Android/iOS native projects:

```
react-native link react-native-openpay
```

## Usage

before doing anything, setup your instance with your credentials:

```javascript
import openpay from "react-native-openpay";

openpay.setup("MERCHANT_ID", "API_KEY");

// you can pas an extra third parameter to tell the sdk that the app is in production
openpay.setup("MERCHANT_ID", "API_KEY", true);
```

create a card token, this function takes an object with the card info and returns a promise, the result is the token  
id string (it does not yet support sending an address):

```javascript
openpay
  .createCardToken({
    holder_name: "John Doe",
    card_number: "4111111111111111",
    expiration_month: "02",
    expiration_year: "20",
    cvv2: "110",
  })
  .then((token) => console.log(token));
```

get the device session id, returns a promise, the result is the session id string:

```javascript
openpay.getDeviceSessionId().then((sessionId) => console.log(sessionId));
```
