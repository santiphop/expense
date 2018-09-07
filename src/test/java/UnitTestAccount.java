import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by 708 on 8/31/2018.
 */
public class UnitTestAccount {
    private Account account;

    @BeforeEach
    void init() {
        account = new Account();
    }

    @Test
    void testDeposit() {
        account.deposit(500);
        assertEquals(500, account.getBalance());
    }

    @Test
    void testIncome() {
        account.deposit(500);
        assertEquals(500, account.getIncome());
    }

    @Test
    void testWithdraw() {
        account.deposit(500);
        account.withdraw(200);
        assertEquals(300, account.getBalance());
    }

    @Test
    void testExpense() {
        account.deposit(500);
        account.withdraw(200);
        assertEquals(200, account.getExpense());
    }
}
