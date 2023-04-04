import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import VehicleClient from "../api/vehicleClient";

/**
 * Logic needed for the view playlist page of the website.
 */
class LandingPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onGet', 'renderVehicle'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        this.client = new VehicleClient();
        this.dataStore.addChangeListener(this.renderVehicle);
        window.onload = this.onGet();

    }

    // Render Methods --------------------------------------------------------------------------------------------------

    async renderVehicle() {
        let resultArea = document.getElementById("vehicle-list");

        const vehicles = this.dataStore.get("vehicles");

        if (vehicles) {
            let result = "";
            result += "<ul>";

            for (let vehicle of vehicles) {
                result += `<li>`;
                result += `<h3> Vehicle ID: ${vehicle.id} </h3>`;
                result += `<p> Year: ${vehicle.year} </p>`;
                result += `<p>  Make: ${vehicle.make} </p>`;
                result += `<p>  Model: ${vehicle.model} </p>`;
                result += `<p>  Current Bid: ${vehicle.price} </p>`;
                result += `</li>`;
            }
            result += "</ul>";

            resultArea.innerHTML = result;
        } else {
            resultArea.innerHTML = "No vehicles";
        }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onGet() {
        let result = await this.client.getAllVehicles(this.errorHandler);
        this.dataStore.set("vehicles", result);

        if (result) {
            console.log(`Got vehicles!`)
        } else {
            console.log("Error doing GET!  Try again...");
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const landingPage = new LandingPage();
    await landingPage.mount();
};

window.addEventListener('DOMContentLoaded', main);

