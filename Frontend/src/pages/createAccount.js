import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import CreateAccountClient from "../api/createAccountClient";

/**
 * Logic needed for the view playlist page of the website.
 */
class CreatePage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onCreate', 'renderUser'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        document.getElementById('enter-name-form').addEventListener('submit', this.onCreate);
        this.client = new CreateAccountClient();

        this.dataStore.addChangeListener(this.renderUser)
    }

    async renderUser() {
        let resultArea = document.getElementById("create-user-result-info");
        let name = document.getElementById("create-name").value;
        let email = document.getElementById("create-email").value;
        let userType = document.querySelector('input[name = "type-of-user"]:checked').value;

        const createdUser = this.dataStore.get("user");

        if (createdUser) {
            resultArea.innerHTML = `
                <div>Account Name: ${name}</div>
                <div>Account Email: ${email}</div>
                <div>Account Type: ${userType} </div>
            `
        } else {
            resultArea.innerHTML = "No Account Created";
        }
    }


    async onCreate(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        this.dataStore.set("user", null);

        let name = document.getElementById("create-name").value;
        let email = document.getElementById("create-email").value;
        let userType = document.querySelector('input[name = "type-of-user"]:checked').value;

        const createdUser = await this.client.createUser(name, email, userType, this.errorHandler);
        this.dataStore.set("user", createdUser);

        if (createdUser) {
            this.showMessage(`Created ${userType} Account!`)
        } else {
            this.errorHandler("Error creating!  Try again...");
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const createAccount = new CreatePage();
    await createAccount.mount();

};

window.addEventListener('DOMContentLoaded', main);