import BaseClass from "../util/baseClass";
import axios from 'axios'
import DataStore from "../util/DataStore";

/**
 * Client to call the sellerService.
 *
 * This could be a great place to explore Mixins. Currently the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
 */
export default class SellerClient extends BaseClass {

    constructor(props = {}){
        super();
        const methodsToBind = ['clientLoaded', 'getSeller', 'updateVehicle', 'deleteVehicle'];
        this.bindClassMethods(methodsToBind, this);
        this.props = props;
        this.clientLoaded(axios);
        this.dataStore = new DataStore();
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
     * Gets the seller for the given ID.
     * @param id Unique identifier for a seller
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The concert
     */
    async getSeller(sellerId, errorCallback) {
        try {
            const response = await this.client.get(`/createAccount/${sellerId}`);
            return response.data;
        } catch (error) {
            this.handleError("getSeller", error, errorCallback)
        }
    }

    async updateVehicle (sellerId, vehicleId, make, model, year, price, errorCallback){
        try {
            const response = await this.client.put(`/vehicle`, {
                id: vehicleId,
                make: make,
                model: model,
                year: year,
                price: price,
                sellerId: sellerId
            });
            return response.data;
        } catch (error) {
            this.handleError("updateVehicle", error, errorCallback)
        }
    }

    async deleteVehicle (sellerId, vehicleId, errorCallback){
        try {
            const response = await this.client.delete(`/vehicle/${vehicleId}`);
            if (response) {
                this.showMessage(`Vehicle ${vehicleId} removed!`)}
            return response.data;
        } catch (error) {
            this.handleError("deleteVehicle", error, errorCallback)
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
