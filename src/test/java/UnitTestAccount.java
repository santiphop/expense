import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

/**
 * Created by 708 on 8/31/2018.
 */
public class UnitTestAccount {
    private Account account;

    @BeforeEach
    void init() {
        account = new Account("Santiphop");
    }

    @Test
    void getCurrencyTest() {
        assertEquals(0, account.getBalance());
    }

    @Test
    void addIncomeTest() {
        account.add(new Transaction(LocalDate.now(), 100, ""));
        assertEquals(100, account.getBalance());
    }

    @Test
    void addExpenseTest() {
        account.add(new Transaction(LocalDate.now(), -100, ""));
        assertEquals(-100, account.getBalance());
    }

    @Test
    void expenseMoreThanIncomeTest() {
        account.add(new Transaction(LocalDate.now(), -100, ""));
        account.add(new Transaction(LocalDate.now(), 50, ""));
        assertEquals(-50, account.getBalance());
    }

    @Test
    void incomeMoreThanExpenseTest() {
        account.add(new Transaction(LocalDate.now(), 100, ""));
        account.add(new Transaction(LocalDate.now(), -50, ""));
        assertEquals(50, account.getBalance());
    }

}
