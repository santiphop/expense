import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by 708 on 9/7/2018.
 */
public class StepDefAccount {
    Account account;

    @Before
    public void init() {
        account = new Account();
    }

    @Given("^a customer with balance (//d+) exists$")
    public void a_customer_deposit_amount(double amount) {
        account.deposit(amount);
    }

    @When("^I deposit (//d+) to my account$")
    public void account_deposit(double amount) {
        account.deposit(amount);
    }

    @Then("^my account balance is (d//+)$")
    public void new_balance_is(double amount) {
        assertEquals(amount, account.getBalance());
    }
}
