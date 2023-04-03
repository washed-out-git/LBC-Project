import BaseClass from "../util/baseClass";
import axios from 'axios'

/**
 * Client to call the MusicPlaylistService.
 *
 * This could be a great place to explore Mixins. Currently the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
 */
export default class BuyerClient extends BaseClass {

    constructor(props = {}){
        super();
        const methodsToBind = ['clientLoaded', 'createBid', 'getBuyer', 'getListOfBids'];
        this.bindClassMethods(methodsToBind, this);
        this.props = props;
        this.clientLoaded(axios);
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     * @param client The client that has been successfully loaded.
     */
    clientLoaded(client) {
        this.client = client;
        if (this.props.hasOwnProperty("onReady")){
            this.props.onReady();
        }
    }
    /**
     * Gets the concert for the given ID.
     * @param id Unique identifier for a concert
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The concert
     */

    async getBuyer(buyerId, errorCallback) {
        try {

            const response = await this.client.get(`/buyer/${buyerId}`);
            return response.data;
        } catch (error) {
            this.handleError("getBuyer", error, errorCallback)
        }
    }

    async createBid(buyerId, buyerName, vehicleId, bidPrice, errorCallback) {
        try {
            const response = await this.client.post(`/bid`, {
                "buyerId" : buyerId,
                "buyerName" : buyerName,
                "vehicleId": vehicleId,
                "bidPrice": bidPrice
            });
            return response.data;
        } catch (error) {
            this.handleError("createBid", error, errorCallback);
        }
    }

    async getListOfBidsByBuyerId(buyerId ,errorCallback) {
        try {
            const response = await this.client.get(`/bid/all/${buyerId}`);
            return response.data;
        } catch (error) {
            this.handleError("getListOfBidsByBuyerId", error, errorCallback);
        }
    }

    async getListOfBids(errorCallback) {
        try {
            const response = await this.client.get(`/bid/listOfBids`);
            return response.data;
        } catch (error) {
            this.handleError("getListOfBids", error, errorCallback);
        }
    }

    /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(method, error, errorCallback) {
        console.error(method + " failed - " + error);
        if (error.response.data.message !== undefined) {
            console.error(error.response.data.message);
        }
        if (errorCallback) {
            errorCallback(method + " failed - " + error);
        }
    }
}
