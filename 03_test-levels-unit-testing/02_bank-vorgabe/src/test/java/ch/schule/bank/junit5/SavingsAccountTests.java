package ch.schule.bank.junit5;

import ch.schule.SavingsAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests für die Klasse SavingsAccount.
 *
 * @author Thomas Stern
 * @version 1.0
 */
public class SavingsAccountTests {

    private SavingsAccount account;

    @BeforeEach
    public void setUp() {
        account = new SavingsAccount("S-1000");
        account.deposit(1, 10000);
    }

    @Test
    public void testWithdrawWholeBalance() {
        assertTrue(account.withdraw(2, 10000));
        assertEquals(0, account.getBalance());
    }

    @Test
    public void testWithdrawMoreThanBalance() {
        assertFalse(account.withdraw(2, 10001));
        assertEquals(10000, account.getBalance());
    }
}
