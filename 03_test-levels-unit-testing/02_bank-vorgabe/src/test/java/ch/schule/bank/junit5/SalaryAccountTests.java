package ch.schule.bank.junit5;

import ch.schule.SalaryAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests der Klasse SalaryAccount.
 *
 * @author Thomas Stern
 * @version 1.1
 */
public class SalaryAccountTests {

    private SalaryAccount account;

    @BeforeEach
    public void setUp() {
        account = new SalaryAccount("P-1000", -5000);
    }

    @Test
    public void testWithdrawUpToCreditLimit() {
        assertTrue(account.withdraw(1, 5000));
        assertEquals(-5000, account.getBalance());
    }

    @Test
    public void testWithdrawOverCreditLimit() {
        assertFalse(account.withdraw(1, 5001));
        assertEquals(0, account.getBalance());
    }
}
