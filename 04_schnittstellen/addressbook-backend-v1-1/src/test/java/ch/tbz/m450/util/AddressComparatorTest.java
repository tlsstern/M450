package ch.tbz.m450.util;

import ch.tbz.m450.repository.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddressComparatorTest {

    private AddressComparator comparator;

    @BeforeEach
    public void setUp() {
        comparator = new AddressComparator();
    }

    @Test
    public void testCompareLastname() {
        Address huber = new Address(1, "Anna", "Huber", "0791111111", new Date());
        Address meier = new Address(2, "Peter", "Meier", "0792222222", new Date());

        assertTrue(comparator.compare(huber, meier) < 0);
        assertTrue(comparator.compare(meier, huber) > 0);
    }

    @Test
    public void testCompareEqual() {
        Address a1 = new Address(1, "Anna", "Huber", "0791111111", new Date());
        Address a2 = new Address(2, "Anna", "Huber", "0791111111", new Date());

        assertEquals(0, comparator.compare(a1, a2));
    }

    @Test
    public void testSortList() {
        Address meier = new Address(1, "Peter", "Meier", "0791111111", new Date());
        Address brunner = new Address(2, "Lisa", "Brunner", "0792222222", new Date());
        Address huber = new Address(3, "Anna", "Huber", "0793333333", new Date());

        List<Address> list = new ArrayList<>(List.of(meier, brunner, huber));
        list.sort(comparator);

        assertEquals(List.of(brunner, huber, meier), list);
    }
}
