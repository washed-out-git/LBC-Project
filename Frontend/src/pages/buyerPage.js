import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import BuyerClient from "../api/buyerClient";

/**
 * Logic needed for the view playlist page of the website.
 */
class BuyerPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onCreateBuyer', 'onCreateBid', 'onGetBids'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        document.getElementById('create-buyer-form').addEventListener('submit', this.onCreateBuyer);
        document.getElementById('create-bid-form').addEventListener('submit', this.onCreateBid);
        document.getElementById('find-all-bids-form').addEventListener('submit', this.onGetBids);
        this.client = new BuyerClient();

        //.dataStore.addChangeListener(this.renderBuyerId)
       // await this.client.createBuyer();
    }

    // Render Methods --------------------------------------------------------------------------------------------------

    async renderBuyerId() {
        let resultArea = document.getElementById("buyer-id-display");

        let buyer = this.dataStore.get("createdBuyer");

        let html = "<li><h3>${buyer.buyerId}</h3>";

        if (buyer) {

            resultArea.innerHTML = html;

        } else {
            resultArea.innerHTML = "No buyerId entered";
        }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------
    async onCreateBuyer(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let buyerName = document.getElementById("create-buyer-name").value;

        const createdBuyer = await this.client.createBuyer(buyerName, this.errorHandler);
        this.dataStore.set("createdBuyer", createdBuyer);

        if (createdBuyer) {
            this.showMessage(`Created ${createdBuyer.buyerId}!`)
        } else {
            this.errorHandler("Error creating!  Try again...");
        }
    }


    async onCreateBid(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        this.dataStore.set("example", null);

        let buyerId = document.getElementById("create-bid-buyerId").value;
        let buyerName = document.getElementById("create-bid-buyerName").value;
        let vehicleId = document.getElementById("create-bid-vehicleId").value;
        let bidPrice = document.getElementById("create-bid-bidPrice").value;

        const createdExample = await this.client.createExample(name, this.errorHandler);
        this.dataStore.set("example", createdExample);

        if (createdExample) {
            this.showMessage(`Created ${createdExample.name}!`)
        } else {
            this.errorHandler("Error creating!  Try again...");
        }
    }

    async onGetBids(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let id = document.getElementById("id-field").value;
        this.dataStore.set("example", null);

        let result = await this.client.getExample(id, this.errorHandler);
        this.dataStore.set("example", result);
        if (result) {
            this.showMessage(`Got ${result.name}!`)
        } else {
            this.errorHandler("Error doing GET!  Try again...");
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const buyerPage = new BuyerPage();
    buyerPage.mount();
};

window.addEventListener('DOMContentLoaded', main);
