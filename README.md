# react-native-openpay

An Android and iOS Openpay sdk wrapper for React Native.

Right now it only suports creating card tokens and getting the device session id as i have no need to  
create a card, any PR is welcomed and also critiques, i am mostly a JS programmer, and i know too little of Java and  
Obj-c, just enough to ge to this point :), if you know a better way to do something, please, open an issue or PR, lets  
have fun :).

## Installing
requires version >= 0.47 of `react-native`
```
npm install -S react-native-openpay
```

then link the library to Android/iOS native projects (i havent tested `rnpm` for this proyect yet):
```
react-native link react-native-openpay
```

## Manually link
### Android
* in `android/app/build.gradle`:
```diff
dependencies {
    ...
    compile "com.facebook.react:react-native:+"  // From node_modules
+   compile project(':react-native-openpay')
}
```
* in `android/settings.gradle`:
```diff
...
include ':app'
+ include ':react-native-openpay'
+ project(':react-native-openpay').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-openpay/android')

```

**With React Native 0.29+**
* in `MainApplication.java`
```diff
+ import com.RNOpenpay.RNOpenpayPackage;

public class MainApplication extends Application implements ReactApplication {
    // .......
    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
+         new RNOpenpayPackage(),
          new MainReactPackage()
      );
    }
    
    // ......
}
```
**With older versions of React Native:**
* in `MainActivity.java`
```diff
+ import com.RNOpenpay.RNOpenpayPackage;

public class MainActivity extends ReactActivity {
    // ......
    
    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
+       new RNOpenpayPackage(),
        new MainReactPackage()
      );
    }
    
    // .....
}
```
## Usage

before doing anything, setup your instance with your credentials:
```javascript
import openpay from 'react-native-openpay';

openpay.setup('MERCHANT_ID', 'API_KEY');

// you can pas an extra third parameter to tell the sdk that the app is in production
openpay.setup('MERCHANT_ID', 'API_KEY', true);
```

create a card token, this function takes an object with the card info and returns a promise, the result is the token  
id string (it does not yet support sending an address):
```javascript
openpay.createCardToken({
    holder_name: 'John Doe',
    card_number: '4111111111111111',
    expiration_month: '02',
    expiration_year: '20',
    cvv2: '110'
  })
  .then(token => console.log(token));
```

get the device session id, returns a promise, the result is the session id string:
```javascript
openpay.getDeviceSessionId().then(sessionId => console.log(sessionId));
```
