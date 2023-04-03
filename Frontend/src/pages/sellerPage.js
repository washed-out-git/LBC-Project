import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import SellerClient from "../api/SellerClient";
import VehicleClient from "../api/vehicleClient";

/**
 * Logic needed for the view playlist page of the website.
 */
class SellerPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onGetSeller', 'renderSeller', 'onUpdateVehicle', 'onRemoveVehicle', 'onGetVehicleBySeller', 'renderVehicles'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        document.getElementById('seller-account-lookup-form').addEventListener('submit', this.onGetSeller);
        document.getElementById('seller-account-lookup-form').addEventListener("submit", this.onGetVehicleBySeller)
        document.getElementById('modify-vehicle-form').addEventListener('submit', this.onUpdateVehicle);
        document.getElementById('remove-vehicle-form').addEventListener('submit', this.onRemoveVehicle);

        this.client = new SellerClient();
        this.dataStore.addChangeListener(this.renderSeller);
        this.dataStore.addChangeListener(this.renderVehicles);
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

    async renderVehicles(){
        let vehicleResultArea = document.getElementById("seller-vehicle-result-info");
        let id = document.getElementById("sellerEmail").value;

        const vehiclesList = this.dataStore.get("vehicles");

        if (vehiclesList) {
            let result = "";
            result += "<ul>";

            for(let vehicle of vehiclesList){
                if(vehicle.sellerId === id) {
                    result += "<li>";
                    result += `<h3> Vehicle Id: ${vehicle.id} </h3>`;
                    result += `<h3> Seller Email: ${vehicle.sellerId} </h3>`;
                    result += `<h4> Year: ${vehicle.year} </h4>`;
                    result += `<p>  Make: ${vehicle.make} </p>`;
                    result += `<p>  Model: ${vehicle.model} </p>`;
                    result += `<p>  Price: ${vehicle.price} </p>`;
                    result += `</li>`;
                }
            }
            result += "</ul>";

            vehicleResultArea.innerHTML = result;
        } else {
            vehicleResultArea.innerHTML = "No vehicles";
        }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onGetSeller(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        this.client = new SellerClient();

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

    async onUpdateVehicle(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        this.client = new VehicleClient();

        let sellerId = document.getElementById("sellerEmail-for-update").value;
        let vehicleId = document.getElementById("update-vehicleId").value;
        let make = document.getElementById("update-make").value;
        let model = document.getElementById("update-model").value;
        let year = document.getElementById("update-year").value;
        let price = document.getElementById("update-price").value;

        const vehicleToUpdate = await this.client.getVehicle(vehicleId, this.errorHandler);
        if(vehicleToUpdate.sellerId === sellerId) {
            this.client = new SellerClient();
            const updatedVehicle = await this.client.updateVehicle(sellerId, vehicleId, make, model, year, price, this.errorHandler);
            if (updatedVehicle) {
                this.showMessage(`Vehicle ${vehicleId} updated!`)

                let resultArea = document.getElementById("update-vehicle-result-info");
                resultArea.innerHTML = `
                <div>Seller Email: ${updatedVehicle.sellerId} </div>
                <div>Vehicle ID: ${updatedVehicle.id} </div>
                <h4>Old Listing</h4>
                <div>Make: ${vehicleToUpdate.make} </div>
                <div>Model: ${vehicleToUpdate.model} </div>
                <div>Year: ${vehicleToUpdate.year} </div>
                <div>Price: ${vehicleToUpdate.price} </div>
                <h4>New Listing</h4>
                <div>Make: ${updatedVehicle.make} </div>
                <div>Model: ${updatedVehicle.model} </div>
                <div>Year: ${updatedVehicle.year} </div>
                <div>Price: ${updatedVehicle.price} </div>
            `
            } else {
                this.errorHandler("Error updating!  Try again...");
            }
        } else {
            this.showMessage('Vehicle ID and seller email do not match')
        }

    }
    async onRemoveVehicle(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        this.client = new VehicleClient();

        let sellerId = document.getElementById("sellerEmail-for-remove").value;
        let vehicleId = document.getElementById("vehicleId-to-remove").value;

        const vehicleToRemove = await this.client.getVehicle(vehicleId, this.errorHandler);
         if(vehicleToRemove.sellerId === sellerId){
             this.client = new SellerClient();
             await this.client.deleteVehicle(sellerId, vehicleId, this.errorHandler);
             let resultArea = document.getElementById("remove-vehicle-result-info");
             resultArea.innerHTML = `
                <div>Seller Email: ${vehicleToRemove.sellerId} </div>
                <h4>Vehicle Below Has Been Removed</h4>
                <div>Vehicle ID: ${vehicleToRemove.id} </div>
                <div>Make: ${vehicleToRemove.make} </div>
                <div>Model: ${vehicleToRemove.model} </div>
                <div>Year: ${vehicleToRemove.year} </div>
                <div>Price: ${vehicleToRemove.price} </div>
            `

         } else {
             this.showMessage('Vehicle ID and seller email do not match')
         }

    }

    async onGetVehicleBySeller(event){
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        this.client = new VehicleClient();

        let result = await this.client.getAllVehicles(this.errorHandler);
        this.dataStore.set("vehicles", result);
        if (result) {
            this.showMessage(`List of Vehicles retrieved!`)
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

// credit Teresa Bowen