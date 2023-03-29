import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import LandingClient from "../api/landingClient";

/**
 * Logic needed for the view playlist page of the website.
 */
class LandingPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onGetVehicles', 'onCreate', 'renderVehicles'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        document.getElementById('vehicles').addEventListener('load', this.onGetVehicles);
        this.client = new LandingClient();
        this.client.getVehicles();
        this.dataStore.addChangeListener(this.renderVehicles);
    }

    // Render Methods --------------------------------------------------------------------------------------------------

    async renderVehicles() {
        let resultArea = document.getElementById("result-info");

        const vehicles = this.dataStore.get("vehicles");

        if (vehicles) {
            resultArea.innerHTML = `
                <div>Year: ${vehicles.year}</div>
                <div>Make: ${vehicles.make}</div>
                <div>Model: ${vehicles.model}</div>
            `
        } else {
            resultArea.innerHTML = "No Vehicles";
        }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onGetVehicles() {
        let result = await this.client.getVehicles();
        console.log(result);
        this.dataStore.set("vehicles", result);
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const landingPage = new LandingPage();
    landingPage.mount();
};

window.addEventListener('DOMContentLoaded', main);
