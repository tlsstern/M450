package ch.schule.bank.junit5;

import ch.schule.Account;
import ch.schule.SalaryAccount;
import ch.schule.SavingsAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests für die Klasse Account.
 *
 * @author Thomas Stern
 * @version 1.0
 */
public class AccountTests {

    private Account account;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        account = new SalaryAccount("P-1000", -1000000);
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testInit() {
        assertEquals("P-1000", account.getId());
        assertEquals(0, account.getBalance());
        assertTrue(account.canTransact(0));
    }

    @Test
    public void testDeposit() {
        assertTrue(account.deposit(10, 5000));
        assertEquals(5000, account.getBalance());

        assertFalse(account.deposit(11, -100));
        assertEquals(5000, account.getBalance());
    }

    @Test
    public void testWithdraw() {
        account.deposit(10, 5000);

        assertTrue(account.withdraw(11, 2000));
        assertEquals(3000, account.getBalance());

        assertFalse(account.withdraw(12, -100));
        assertEquals(3000, account.getBalance());
    }

    @Test
    public void testReferences() {
        Account savings = new SavingsAccount("S-1000");

        assertFalse(savings.withdraw(1, 100));
        assertEquals(0, savings.getBalance());
    }

    @Test
    public void testCanTransact() {
        account.deposit(100, 5000);

        assertFalse(account.canTransact(99));
        assertTrue(account.canTransact(100));
        assertTrue(account.canTransact(101));

        assertFalse(account.deposit(50, 1000));
        assertFalse(account.withdraw(50, 1000));
        assertEquals(5000, account.getBalance());
    }

    @Test
    public void testPrint() {
        account.deposit(0, 5000);
        account.withdraw(31, 2000);

        account.print();

        String text = output.toString();
        assertTrue(text.contains("Kontoauszug 'P-1000'"));
        assertTrue(text.contains("01.01.1970"));
        assertTrue(text.contains("02.02.1970"));
    }

    @Test
    public void testMonthlyPrint() {
        account.deposit(5, 1000);
        account.deposit(35, 2000);
        account.deposit(65, 3000);

        account.print(1970, 2);

        String text = output.toString();
        assertTrue(text.contains("Monat: 2.1970"));
        assertTrue(text.contains("06.02.1970"));
        assertFalse(text.contains("06.01.1970"));
        assertFalse(text.contains("06.03.1970"));
    }
}
