package ch.schule.bank.junit5;

import ch.schule.Bank;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests für die Klasse Bank.
 *
 * @author Thomas Stern
 * @version 1.0
 */
public class BankTests {

    private Bank bank;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        bank = new Bank();
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testCreate() {
        assertEquals("S-1000", bank.createSavingsAccount());
        assertEquals("Y-1001", bank.createPromoYouthSavingsAccount());
        assertEquals("P-1002", bank.createSalaryAccount(-5000));

        assertNull(bank.createSalaryAccount(5000));
    }

    @Test
    public void testDeposit() {
        String id = bank.createSavingsAccount();

        assertTrue(bank.deposit(id, 1, 10000));
        assertEquals(10000, bank.getBalance(id));

        assertFalse(bank.deposit("S-9999", 1, 10000));
    }

    @Test
    public void testWithdraw() {
        String id = bank.createSavingsAccount();
        bank.deposit(id, 1, 10000);

        assertTrue(bank.withdraw(id, 2, 4000));
        assertEquals(6000, bank.getBalance(id));

        assertFalse(bank.withdraw(id, 3, 7000));
        assertFalse(bank.withdraw("S-9999", 3, 1000));
    }

    @Test
    public void testPrint() {
        String id = bank.createSavingsAccount();
        bank.deposit(id, 1, 10000);

        bank.print(id);
        assertTrue(output.toString().contains("Kontoauszug 'S-1000'"));

        output.reset();
        bank.print("S-9999");
        assertEquals("", output.toString());
    }

    @Test
    public void testMonthlyPrint() {
        String id = bank.createSavingsAccount();
        bank.deposit(id, 1, 10000);

        bank.print(id, 1970, 1);
        assertTrue(output.toString().contains("Kontoauszug 'S-1000' Monat: 1.1970"));

        output.reset();
        bank.print("S-9999", 1970, 1);
        assertEquals("", output.toString());
    }

    @Test
    public void testBalance() {
        String id1 = bank.createSavingsAccount();
        String id2 = bank.createSalaryAccount(-5000);
        bank.deposit(id1, 1, 3000);
        bank.withdraw(id2, 1, 1000);

        assertEquals(-2000, bank.getBalance());
        assertEquals(0, bank.getBalance("S-9999"));
    }

    @Test
    public void testTop5() {
        createSixAccounts();

        bank.printTop5();

        String[] lines = output.toString().trim().split("\r?\n");
        assertEquals(5, lines.length);
        assertEquals("S-1005: 6000", lines[0]);
        assertEquals("S-1001: 2000", lines[4]);
    }

    @Test
    public void testBottom5() {
        createSixAccounts();

        bank.printBottom5();

        String[] lines = output.toString().trim().split("\r?\n");
        assertEquals(5, lines.length);
        assertEquals("S-1000: 1000", lines[0]);
        assertEquals("S-1004: 5000", lines[4]);
    }

    private void createSixAccounts() {
        for (int i = 1; i <= 6; i++) {
            String id = bank.createSavingsAccount();
            bank.deposit(id, 1, i * 1000);
        }
    }
}
