import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import BuyerClient from "../api/buyerClient";

/**
 * Logic needed for the view playlist page of the website.
 */
class BuyerPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onGetBuyer', 'onCreateBid', 'onGetBids', 'renderBuyerId'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        document.getElementById('buyer-account-lookup-form').addEventListener('submit', this.onCreateBuyer);
        document.getElementById('create-bid-form').addEventListener('submit', this.onCreateBid);
        document.getElementById('find-all-bids-form').addEventListener('submit', this.onGetBids);
        this.client = new BuyerClient();

    }

    // Render Methods --------------------------------------------------------------------------------------------------

    async renderBuyerId() {
        let resultArea = document.getElementById("buyer-account-result-info");

        let buyer = this.dataStore.get("createdBuyer");

        if (buyer) {
            resultArea.innerHTML = `
                <div>Account Name: ${buyer.buyerName}</div>
                <div>Account Email: ${buyer.userId}</div>
                <div>Account Type: Buyer </div>
            `
        } else {
            resultArea.innerHTML = "No Account Exists";
        }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onGetBuyer(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let id = document.getElementById("buyerEmail").value;
        this.dataStore.set("createdBuyer", null);
        let result = await this.client.getBuyer(id, this.errorHandler);

        this.dataStore.set("createdBuyer", result);
        if (result) {
            this.showMessage(`Account for ${result.buyerName} retrieved!`)
        } else {
            this.errorHandler("Error doing GET!  Try again...");
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
                html += `<h3>Buyer ID: ${bid.buyerId}</h3>
                         <h4>Name: ${bid.buyerName}</h4>
                         <p>Vehicle ID: ${bid.vehicleId}</p>
                         <p>Bid Price: ${bid.bidPrice}</p>`;
            }

            resultArea.innerHTML = html;

        } else {
            resultArea.innerHTML = "No bids";
        }

        if (result) {
            this.showMessage(`Success!`)
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
