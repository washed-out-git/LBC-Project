import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import CreateAccountClient from "../api/createAccountClient";

/**
 * Logic needed for the view playlist page of the website.
 */
class CreateAccount extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onCreate'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        document.getElementById('enter-name-form').addEventListener('submit', this.onCreate);
        this.client = new CreateAccountClient();

        //this.dataStore.addChangeListener(this.renderUser)
    }

    // async renderUser() {
    //     let resultArea = document.getElementById("result-info");
    //
    //     const example = this.dataStore.get("example");
    //
    //     if (example) {
    //         resultArea.innerHTML = `
    //             <div>ID: ${example.id}</div>
    //             <div>Name: ${example.name}</div>
    //         `
    //     } else {
    //         resultArea.innerHTML = "No Item";
    //     }
    // }


    async onCreate(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        this.dataStore.set("user", null);

        let name = document.getElementById("create-name").value;
        let email = document.getElementById("create-email").value;
        let userType = document.querySelector('input[name = "type-of-user"]:checked').value;
        const createdUser = await this.client.createUser(name, email, this.errorHandler);
        this.dataStore.set("user", createdUser);

        if (createdUser) {
            this.showMessage(`Created ${userType}!`)
        } else {
            this.errorHandler("Error creating!  Try again...");
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const createAccount = new CreateAccount();
    createAccount.mount();
};

window.addEventListener('DOMContentLoaded', main);
