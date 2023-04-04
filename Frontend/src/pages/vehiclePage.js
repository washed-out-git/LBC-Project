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
    }

    // Render Methods --------------------------------------------------------------------------------------------------

    async renderVehicle() {
        let resultArea = document.getElementById("result-info");

        const vehicles = this.dataStore.get("vehicle");

        if (vehicles) {
            resultArea.innerHTML = `
                <div>Id: ${vehicles.id}</div>
                <div>Year: ${vehicles.year}</div>
                <div>Make: ${vehicles.make}</div>
                <div>Model: ${vehicles.model}</div>
                <div>Price: ${vehicles.price}</div>
                <div>Seller Email: ${vehicles.sellerId}</div>
            `
        } else {
            resultArea.innerHTML = "No Item";
        }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onGet(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let id = document.getElementById("id-field").value;
        this.dataStore.set("vehicle", null);

        let result = await this.client.getVehicle(id, this.errorHandler);
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
        let price = document.getElementById("vehicle-price").value;
        let sellerId = document.getElementById("vehicle-sellerId").value;

        const createdVehicle = await this.client.createVehicle(make, model, year, price, sellerId, this.errorHandler);
        this.dataStore.set("vehicle", createdVehicle);

        if (createdVehicle) {
            this.showMessage(`Created vehicle for auction!`)
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
    console.log("page loaded")
};

window.addEventListener('DOMContentLoaded', main);