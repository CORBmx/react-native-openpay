type CreateCardTokenParams = {
    holder_name: string;
    card_number: string;
    expiration_month: string;
    expiration_year: string;
    cvv2: string;
}

interface RNOpenpay{
    /**
     * Setup the instance with the credentials
     */
    setup: (merchantID: string, apiKey: string, isProduction?:boolean) => void;
    /**
     * Create a card token, this function takes an object with the card info and returns a promise, the result is the token
     * id string (it does not yet support sending an address):
     */
    createCardToken: (params: CreateCardTokenParams)=> Promise<string>;
    /**
     * Get the device session id, returns a promise, the result is the session id string.
     */
    getDeviceSessionId: ()=>Promise<string>
}   

declare module 'react-native-openpay' {
    export default RNOpenpay;
}

