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
        document.getElementById('list-all-vehicles').addEventListener('submit', this.onGet);
        this.client = new VehicleClient();

        this.dataStore.addChangeListener(this.renderVehicle);

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
                result += `<h3> Vehicle Id: ${vehicle.id} </h3>`;
                result += `<h4> Year: ${vehicle.year} </h4>`;
                result += `<p>  Make: ${vehicle.make} </p>`;
                result += `<p>  Model: ${vehicle.model} </p>`;
                result += `<p>  Price: ${vehicle.price} </p>`;
                result += `</li>`;
            }
            result += "</ul>";

            resultArea.innerHTML = result;
        } else {
            resultArea.innerHTML = "No vehicles";
        }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onGet(event) {
        event.preventDefault();

        let result = await this.client.getAllVehicles(this.errorHandler);
        this.dataStore.set("vehicles", result);

        if (result) {
            this.showMessage(`Got vehicles!`)
        } else {
            this.errorHandler("Error doing GET!  Try again...");
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