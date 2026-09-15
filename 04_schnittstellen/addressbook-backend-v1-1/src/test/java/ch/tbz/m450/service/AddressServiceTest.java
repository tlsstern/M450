package ch.tbz.m450.service;

import ch.tbz.m450.repository.Address;
import ch.tbz.m450.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    private Address meier;
    private Address huber;

    @BeforeEach
    public void setUp() {
        meier = new Address(1, "Peter", "Meier", "0791111111", new Date());
        huber = new Address(2, "Anna", "Huber", "0792222222", new Date());
    }

    @Test
    public void testSave() {
        when(addressRepository.save(meier)).thenReturn(meier);

        Address saved = addressService.save(meier);

        assertEquals(meier, saved);
        verify(addressRepository, times(1)).save(meier);
    }

    @Test
    public void testGetAllIsSorted() {
        when(addressRepository.findAll()).thenReturn(List.of(meier, huber));

        List<Address> result = addressService.getAll();

        assertEquals(2, result.size());
        assertEquals("Huber", result.get(0).getLastname());
        assertEquals("Meier", result.get(1).getLastname());
        verify(addressRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllEmpty() {
        when(addressRepository.findAll()).thenReturn(List.of());

        assertTrue(addressService.getAll().isEmpty());
    }

    @Test
    public void testGetAddressFound() {
        when(addressRepository.findById(1)).thenReturn(Optional.of(meier));

        Optional<Address> result = addressService.getAddress(1);

        assertTrue(result.isPresent());
        assertEquals("Meier", result.get().getLastname());
        verify(addressRepository).findById(1);
    }

    @Test
    public void testGetAddressNotFound() {
        when(addressRepository.findById(99)).thenReturn(Optional.empty());

        Optional<Address> result = addressService.getAddress(99);

        assertFalse(result.isPresent());
        verify(addressRepository).findById(99);
    }
}
