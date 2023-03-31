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

    }

    // Render Methods --------------------------------------------------------------------------------------------------

    async renderBuyerId() {
        let resultArea = document.getElementById("result-info");

        let buyer = this.dataStore.get("createdBuyer");

        let html = "<ul><h1><li><h>${buyer.userId}</h></li></ul>";

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

        let createdBuyer = await this.client.createBuyer(buyerName, this.errorHandler);
        let resultArea = document.getElementById("result-info");

        let html = `<ul><h4><li><h4>${createdBuyer.userId}</h4></li></ul>`;

        if (createdBuyer) {
            resultArea.innerHTML = html;
            this.showMessage(`Create successful!`)
        } else {
            this.errorHandler("Error creating!  Try again...");
        }
    }


    async onCreateBid(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let buyerId = document.getElementById("create-bid-buyerId").value;
        let buyerName = document.getElementById("create-bid-buyerName").value;
        let vehicleId = document.getElementById("create-bid-vehicleId").value;
        let bidPrice = document.getElementById("create-bid-bidPrice").value;

        const createdBid = await this.client.createBid(buyerId, buyerName, vehicleId, bidPrice, this.errorHandler);

        if (createdBid) {
            this.showMessage(`Bid placed!`)
        } else {
            this.errorHandler("Error creating!  Try again...");
        }
    }

    async onGetBids(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        let resultArea = document.getElementById("bid-list");
        let bids = await this.client.getListOfBids(this.errorHandler);

        let html = "<ul>";

        if (bids) {
            for(let bid of bids){
                html += `<li><h3>${bid.buyerId}</h3><h4>${bid.buyerName}</h4><p>${bid.vehicleId}</p><p>${bid.bidPrice}</p></li>`;
            }

            resultArea.innerHTML = html;

        } else {
            resultArea.innerHTML = "No bids";
        }

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
