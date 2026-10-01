package ch.tbz.m450.util;

import ch.tbz.m450.repository.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressComparatorTest {

    private AddressComparator comparator;

    @BeforeEach
    public void setUp() {
        comparator = new AddressComparator();
    }

    @Test
    public void testSortByLastname() {
        Address meier = new Address(1, "Peter", "Meier", "0791111111", new Date());
        Address brunner = new Address(2, "Lisa", "Brunner", "0792222222", new Date());
        Address huber = new Address(3, "Anna", "Huber", "0793333333", new Date());

        List<Address> list = new ArrayList<>(List.of(meier, brunner, huber));
        list.sort(comparator);

        assertEquals(brunner, list.get(0));
        assertEquals(huber, list.get(1));
        assertEquals(meier, list.get(2));
    }

    // Aufgabe 2: Gleicher Nachname -> Vorname entscheidet
    @Test
    public void testSameLastnameComparesFirstname() {
        Address peter = new Address(1, "Peter", "Huber", "0791111111", new Date());
        Address anna = new Address(2, "Anna", "Huber", "0792222222", new Date());

        List<Address> list = new ArrayList<>(List.of(peter, anna));
        list.sort(comparator);

        assertEquals(anna, list.get(0));
        assertEquals(peter, list.get(1));
    }

    // Aufgabe 2: Gleicher Vor- und Nachname -> Telefonnummer entscheidet
    @Test
    public void testSameNameComparesPhonenumber() {
        Address anna2 = new Address(1, "Anna", "Huber", "0792222222", new Date());
        Address anna1 = new Address(2, "Anna", "Huber", "0791111111", new Date());

        List<Address> list = new ArrayList<>(List.of(anna2, anna1));
        list.sort(comparator);

        assertEquals(anna1, list.get(0));
        assertEquals(anna2, list.get(1));
    }

    // Mehrere Adressen mit unterschiedlichen und gleichen Attributen sortieren
    @Test
    public void testSortListMultipleAddresses() {
        Address meier = new Address(1, "Beat", "Meier", "0794444444", new Date());
        Address huberPeter = new Address(2, "Peter", "Huber", "0793333333", new Date());
        Address huberAnna2 = new Address(3, "Anna", "Huber", "0799999999", new Date());
        Address brunner = new Address(4, "Lisa", "Brunner", "0791111111", new Date());
        Address huberAnna1 = new Address(5, "Anna", "Huber", "0791111111", new Date());

        List<Address> list = new ArrayList<>(List.of(meier, huberPeter, huberAnna2, brunner, huberAnna1));
        list.sort(comparator);

        // Erwartete Reihenfolge:
        // 0: Brunner Lisa
        // 1: Huber Anna (0791111111)
        // 2: Huber Anna (0799999999)
        // 3: Huber Peter (0793333333)
        // 4: Meier Beat (0794444444)
        assertEquals(brunner, list.get(0));
        assertEquals(huberAnna1, list.get(1));
        assertEquals(huberAnna2, list.get(2));
        assertEquals(huberPeter, list.get(3));
        assertEquals(meier, list.get(4));
    }

    @Test
    public void testCompareEqual() {
        Address a1 = new Address(1, "Anna", "Huber", "0791111111", new Date());
        Address a2 = new Address(2, "Anna", "Huber", "0791111111", new Date());

        assertEquals(0, comparator.compare(a1, a2));
    }
}
