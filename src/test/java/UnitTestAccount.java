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
        account = new Account(Main.nameAccount);
    }

    @Test
    void getCurrencyTest() {
        assertEquals(0, account.getBalance());
    }

    @Test
    void addIncomeTest() {
        account.add(new Transaction(LocalDate.now(), 100, "deposit"));
        assertEquals(100, account.getBalance());
    }

    @Test
    void addExpenseTest() {
        account.add(new Transaction(LocalDate.now(), 100, "expense"));
        assertEquals(-100, account.getBalance());
    }

    @Test
    void expenseMoreThanIncomeTest() {
        account.add(new Transaction(LocalDate.now(), 100, "expense"));
        account.add(new Transaction(LocalDate.now(), 50, "deposit"));
        assertEquals(-50, account.getBalance());
    }

    @Test
    void incomeMoreThanExpenseTest() {
        account.add(new Transaction(LocalDate.now(), 100, "deposit"));
        account.add(new Transaction(LocalDate.now(), 50, "expense"));
        assertEquals(50, account.getBalance());
    }

}
