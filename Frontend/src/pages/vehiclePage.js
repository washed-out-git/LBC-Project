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
        document.getElementById('get-by-id-form').addEventListener('submit', this.onGet);
        document.getElementById('create-vehicle-make').addEventListener('submit', this.onCreate);
        document.getElementById('create-vehicle-model').addEventListener('submit', this.onCreate);
        document.getElementById('create-vehicle-year').addEventListener('submit', this.onCreate);
        document.getElementById('create-vehicle-available').addEventListener('submit', this.onCreate);
        document.getElementById('create-vehicle-id').addEventListener('submit', this.onCreate);
        document.getElementById('create-vehicle-price').addEventListener('submit', this.onCreate);
        this.client = new VehicleClient();

        this.dataStore.addChangeListener(this.renderVehicle)
        await this.client.getAllVehicles(this.renderVehicle());
    }

    // Render Methods --------------------------------------------------------------------------------------------------

    async renderVehicle() {
        let resultArea = document.getElementById("result-info");

        const vehicles = this.dataStore.get("vehicles");

        if (vehicles) {
            resultArea.innerHTML = `
                <div>Make: ${vehicles.make}</div>
                <div>Model: ${vehicles.model}</div>
                <div>Year: ${vehicles.year}</div>
                <div>Available: ${vehicles.available}</div>
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

        let make = document.getElementById("create-vehicle-make").value;
        let model = document.getElementById("create-vehicle-model").value;
        let year = document.getElementById("create-vehicle-year").value;
        let available = document.getElementById("create-vehicle-available").value;
        let id = document.getElementById("create-vehicle-id").value;
        let price = document.getElementById("create-vehicle-price").value;
        this.dataStore.set("vehicles", null);

        let result = await (await this.client.getAllVehicles())(make, model,
            year, available, id, price, this.errorHandler);
        this.dataStore.set("vehicles", result);
        if (result) {
            this.showMessage(`Got ${result.id}!`)
        } else {
            this.errorHandler("Error doing GET!  Try again...");
        }
    }

    async onCreate(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        this.dataStore.set("vehicles", null);

        let make = document.getElementById("create-vehicle-make").value;
        let model = document.getElementById("create-vehicle-model").value;
        let year = document.getElementById("create-vehicle-year").value;
        let available = document.getElementById("create-vehicle-available").value;
        let id = document.getElementById("create-vehicle-id").value;
        let price = document.getElementById("create-vehicle-price").value;

        const createdVehicle = await this.client.createVehicle()(make, model, year, available, id, price, this.errorHandler);
        this.dataStore.set("vehicles", createdVehicle);

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