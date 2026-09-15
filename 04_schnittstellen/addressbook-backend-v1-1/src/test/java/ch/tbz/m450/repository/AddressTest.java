package ch.tbz.m450.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class AddressTest {

    private Address address;
    private Date date;

    @BeforeEach
    public void setUp() {
        date = new Date();
        address = new Address(1, "Max", "Muster", "0791234567", date);
    }

    @Test
    public void testCreateAddress() {
        assertEquals(1, address.getId());
        assertEquals("Max", address.getFirstname());
        assertEquals("Muster", address.getLastname());
        assertEquals("0791234567", address.getPhonenumber());
        assertEquals(date, address.getRegistrationDate());
    }

    @Test
    public void testCreateEmptyAddress() {
        Address empty = new Address();

        assertEquals(0, empty.getId());
        assertNull(empty.getFirstname());
        assertNull(empty.getLastname());
    }

    @Test
    public void testChangeAddress() {
        address.setFirstname("Anna");
        address.setPhonenumber("0761111111");

        assertEquals("Anna", address.getFirstname());
        assertEquals("0761111111", address.getPhonenumber());
        assertEquals("Muster", address.getLastname());
    }
}
