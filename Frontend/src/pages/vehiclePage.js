import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import VehicleClient from "../api/vehicleClient";


class VehiclePage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onGet', 'onCreate', 'renderVehicle'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        document.getElementById('create-vehicle-form').addEventListener('submit', this.onCreate);
        document.getElementById('get-by-id-form').addEventListener('submit', this.onGet);
        this.client = new VehicleClient();

        this.dataStore.addChangeListener(this.renderVehicle)
        //this.client.getAllVehicles(this.renderVehicle());
    }

    // Render Methods --------------------------------------------------------------------------------------------------

    async renderVehicle() {
        let resultArea = document.getElementById("result-info");

        const vehicles = this.dataStore.get("vehicle");

        if (vehicles) {
            resultArea.innerHTML = `
                <div>Make: ${vehicles.make}</div>
                <div>Model: ${vehicles.model}</div>
                <div>Year: ${vehicles.year}</div>
                <div>Id: ${vehicles.id}</div>
                <div>Price: ${vehicles.price}</div>
            `
        } else {
            resultArea.innerHTML = "No Item";
        }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onGet(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let make = document.getElementById("vehicle-make").value;
        let model = document.getElementById("vehicle-model").value;
        let year = document.getElementById("vehicle-year").value;
        let id = document.getElementById("vehicle-id").value;
        let price = document.getElementById("vehicle-price").value;
        this.dataStore.set("vehicle", null);

        let result = await (await this.client.getAllVehicles())(make, model,
            year, id, price, this.errorHandler);
        this.dataStore.set("vehicle", result);
        if (result) {
            this.showMessage(`Got ${result.id}!`)
        } else {
            this.errorHandler("Error doing GET!  Try again...");
        }
    }

    async onCreate(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        this.dataStore.set("vehicle", null);

        let make = document.getElementById("vehicle-make").value;
        let model = document.getElementById("vehicle-model").value;
        let year = document.getElementById("vehicle-year").value;
        let id = document.getElementById("vehicle-id").value;
        let price = document.getElementById("vehicle-price").value;
        const createdVehicle = await this.client.createVehicle(make, model, year, id, price, this.errorHandler);
        this.dataStore.set("vehicle", createdVehicle);

        if (createdVehicle) {
            this.showMessage(`Created ${createdVehicle.id}!`)
        } else {
            this.errorHandler("Error creating!  Try again...");
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const vehiclePage = new VehiclePage();
    await vehiclePage.mount();
};

window.addEventListener('DOMContentLoaded', main);