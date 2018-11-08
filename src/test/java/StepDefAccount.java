import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by 708 on 9/7/2018.
 */
public class StepDefAccount {
    Account account;

    @Before
    public void init() {
        account = new Account(Main.nameAccount);
    }

    @Given("^a user with balance (\\d+) exists$")
    public void add_deposit_init(double amount) {
        account.add(new Transaction(LocalDate.now(), amount, ""));
    }

    @When("^I deposit (\\d+)$")
    public void deposit(double amount) {
        account.add(new Transaction(LocalDate.now(), amount, ""));
    }

    @When("^I expense (\\d+)$")
    public void expense(double amount) {
        account.add(new Transaction(LocalDate.now(), - amount, ""));
    }

    @Then("^my account have balance (\\d+) exists$")
    public void balanceMoreThan0(double amount) {
        assertEquals(amount, account.getBalance());
    }

    @Then("^my account is in debt for (\\d+)$")
    public void balanceLessThan0(double amount) {
        assertEquals(amount, - account.getBalance());
    }

    Transaction transaction;

    @Given("^a transaction (\\S+) with (\\d+) (\\S+) at (\\d+)-(\\d+)-(\\d+)$")
    public void edit_deposit_init(String note, double amount, String type, int year, int month, int day) {
        transaction = new Transaction(LocalDate.of(year, month, day), amount, type, note);
    }

    @When("^I edit date to (\\d+)-(\\d+)-(\\d+)$")
    public void edit_date(int year, int month, int day) {
        transaction.setDate(LocalDate.of(year, month, day));
    }

    @When("^I edit amount to (\\d+)")
    public void edit_amount(double amount) {
        transaction.setAmount(amount);
    }

    @When("^I change type$")
    public void edit_type() {
        transaction.setAmount(-transaction.getAmount());
    }

    @When("^I edit note to (\\S+)$")
    public void edit_note(String note) {
        transaction.setNote(note);
    }

    @Then("^transaction is (\\S+) with (\\d+) (\\S+) at (\\d+)-(\\d+)-(\\d+)")
    public void format(String note, double amount, String type, int year, int month, int day) {
        assertEquals(note, transaction.getNote());
        assertEquals(amount, Math.abs(transaction.getAmount()));
        assertEquals(type, transaction.getType());
        assertEquals(LocalDate.of(year, month, day), transaction.getDate());
    }


}
