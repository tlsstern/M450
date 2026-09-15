package ch.schule.bank.junit5;

import ch.schule.PromoYouthSavingsAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests für das Promo-Jugend-Sparkonto.
 *
 * @author Thomas Stern
 * @version 1.0
 */
public class PromoYouthSavingsAccountTests {

    private PromoYouthSavingsAccount account;

    @BeforeEach
    public void setUp() {
        account = new PromoYouthSavingsAccount("Y-1000");
    }

    @Test
    public void testDepositGetsOnePercentBonus() {
        assertTrue(account.deposit(1, 10000));
        assertEquals(10100, account.getBalance());
    }

    @Test
    public void testWithdrawLikeSavingsAccount() {
        account.deposit(1, 10000);

        assertFalse(account.withdraw(2, 20000));
        assertTrue(account.withdraw(2, 10100));
        assertEquals(0, account.getBalance());
    }
}
