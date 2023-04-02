import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import BuyerClient from "../api/buyerClient";

/**
 * Logic needed for the view playlist page of the website.
 */
class BuyerPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onAccountLookUp', 'onCreateBid', 'onGetBids', 'renderBuyerId', 'onGetBidsByBuyerId'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        document.getElementById('buyer-account-lookup-form').addEventListener('submit', this.onAccountLookUp);
        document.getElementById('create-bid-form').addEventListener('submit', this.onCreateBid);
        document.getElementById('find-all-bids-form').addEventListener('submit', this.onGetBids);

        const findAllBidsByBuyerForm = document.getElementById('find-all-bids-by-buyer-form');
        if(findAllBidsByBuyerForm){
            findAllBidsByBuyerForm .addEventListener('submit', this.onGetBidsByBuyerId);
        }

        this.client = new BuyerClient();

        if(this.dataStore) {
            this.dataStore.addChangeListener(this.renderBuyerId);
        }
    }

    // Render Methods --------------------------------------------------------------------------------------------------
    async renderBuyerId() {
        let resultArea = document.getElementById("buyer-account-result-info");

        const buyer = this.dataStore.get("createdBuyer");

        if (buyer) {
            resultArea.innerHTML = `
                <div>Account Name: ${buyer.buyerName}</div>
                <div>Account Email: ${buyer.userId}</div>
                <div>Account Type: Buyer </div>`
        } else {
            resultArea.innerHTML = "No Account Exists";
        }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------
    async onAccountLookUp(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        this.dataStore.set("createdBuyer", null);

        let buyerId = document.getElementById("buyer-email").value;

        let createdBuyer = await this.client.getBuyer(buyerName, this.errorHandler);
        this.dataStore.set("createdBuyer", createdBuyer);

        if (createdBuyer) {
            this.showMessage(`Account for ${createdBuyer.buyerName} retrieved!`)
            let result = await this.client.getBuyer(buyerId, this.errorHandler);
            this.dataStore.set("createdBuyer", result);

            if (result) {
                this.showMessage(`Account for ${result.userId} retrieved!`)
            } else {
                this.errorHandler("Error doing GET!  Try again...");
            }
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

    async onGetBidsByBuyerId(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        let buyerId = document.getElementById("get-bids-buyerId").value;
        let printingArea = document.getElementById("bids-by-buyer-list");
        let bids = await this.client.getListOfBidsByBuyerId(buyerId, this.errorHandler);

        let html = "<ul>";

        if (bids) {
            for(let bid of bids){
                html += `<h3>Buyer ID: ${bid.buyerId}</h3>
                         <h4>Name: ${bid.buyerName}</h4>
                         <p>Vehicle ID: ${bid.vehicleId}</p>
                         <p>Bid Price: ${bid.bidPrice}</p>`;
            }

            printingArea.innerHTML = html;

        } else {
            printingArea.innerHTML = "No bids";
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
