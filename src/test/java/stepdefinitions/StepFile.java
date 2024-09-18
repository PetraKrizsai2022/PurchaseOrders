package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepFile {

    @Given("^purchase_orders.xls file exists$")
    public void checkFile() {

    }

    @When("^the user opens purchase_orders.xls$")
    public void openAndReadPurchaseOrders() {

    }

    @Then("^the xls file has the following headers: Buyer, Buyer, Buyer External ID, Sales Order$")
    public void validateHeader() {

    }

    @And("^the xls file has more than 10 lines$")
    public void validateRowNumber() {

    }
}
