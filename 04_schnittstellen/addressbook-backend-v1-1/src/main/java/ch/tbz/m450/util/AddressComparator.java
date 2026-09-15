package ch.tbz.m450.util;

import ch.tbz.m450.repository.Address;

import java.util.Comparator;

public class AddressComparator implements Comparator<Address> {

    @Override
    public int compare(Address a1, Address a2) {
        int result = a1.getLastname().compareTo(a2.getLastname());

        // same lastname, then compare the firstname
        if (result == 0) {
            result = a1.getFirstname().compareTo(a2.getFirstname());
        }

        // same name, then compare the phonenumber
        if (result == 0) {
            result = a1.getPhonenumber().compareTo(a2.getPhonenumber());
        }

        return result;
    }

}
