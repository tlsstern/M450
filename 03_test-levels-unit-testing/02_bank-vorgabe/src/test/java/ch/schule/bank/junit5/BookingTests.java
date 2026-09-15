package ch.schule.bank.junit5;

import ch.schule.BankUtils;
import ch.schule.Booking;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests für die Klasse Booking.
 *
 * @author Luigi Cavuoti
 * @version 1.1
 */
public class BookingTests {

    private Booking booking;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        booking = new Booking(13576, 12000);
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testInitialization() {
        assertEquals(13576, booking.getDate());
        assertEquals(12000, booking.getAmount());
    }

    @Test
    public void testPrint() {
        booking.print(5000);

        String expected = "17.09.2007 " + BankUtils.formatAmount(12000)
                + " " + BankUtils.formatAmount(17000);
        assertEquals(expected, output.toString().trim());
    }
}
