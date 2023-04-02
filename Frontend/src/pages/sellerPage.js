import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import SellerClient from "../api/SellerClient";

/**
 * Logic needed for the view playlist page of the website.
 */
class SellerPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onGetSeller', 'renderSeller'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        document.getElementById('seller-account-lookup-form').addEventListener('submit', this.onGetSeller);
        this.client = new SellerClient();
        this.dataStore.addChangeListener(this.renderSeller);
    }

    // Render Methods --------------------------------------------------------------------------------------------------

    async renderSeller() {
        let resultArea = document.getElementById("seller-account-result-info");

        const createdSeller = this.dataStore.get("createdSeller");

        if (createdSeller) {
            resultArea.innerHTML = `
                <div>Account Name: ${createdSeller.sellerName}</div>
                <div>Account Email: ${createdSeller.userId}</div>
                <div>Account Type: Seller </div>
            `
        } else {
            resultArea.innerHTML = "No Account Exists";
        }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onGetSeller(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let id = document.getElementById("sellerEmail").value;
        this.dataStore.set("createdSeller", null);
        let result = await this.client.getSeller(id, this.errorHandler);

        this.dataStore.set("createdSeller", result);
        if (result) {
            this.showMessage(`Account for ${result.sellerName} retrieved!`)
        } else {
            this.errorHandler("Error doing GET!  Try again...");
        }
    }

}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const sellerPage = new SellerPage();
    sellerPage.mount();
};

window.addEventListener('DOMContentLoaded', main);
