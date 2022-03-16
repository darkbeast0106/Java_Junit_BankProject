package hu.petrik.bankproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {
    Bank bank;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        bank.ujSzamla("Teszt Elek", "1234");
    }

    @Test
    void ujSzamlaEgyenlegNulla(){
        long egyenleg = bank.egyenleg("1234");
        assertEquals(0, egyenleg);
    }

    @Test
    void ujSzamlaEgyenlegFeltoltesMegfeleloEgyenleg(){
        bank.egyenlegFeltolt("1234", 10000);
        long egyenleg = bank.egyenleg("1234");
        assertEquals(10000, egyenleg);
    }

    @Test
    void egyenlegTobbszoriFeltoltesMegfeleloEgyenleg(){
        bank.egyenlegFeltolt("1234", 10000);
        assertEquals(10000, bank.egyenleg("1234"));
        bank.egyenlegFeltolt("1234", 20000);
        assertEquals(30000, bank.egyenleg("1234"));
    }

    @Test
    void tobbSzamlaFeltoltEgyenlegMegfeleloreKerul(){
        bank.ujSzamla("Gipsz Jakab", "5678");
        bank.egyenlegFeltolt("1234", 10000);
        bank.egyenlegFeltolt("1234", 20000);
        bank.egyenlegFeltolt("5678", 15000);
        assertEquals(30000, bank.egyenleg("1234"));
        assertEquals(15000, bank.egyenleg("5678"));
    }
    @Test
    void ujSzamlaMeglevoSzamlaszammal(){
        assertThrows(IllegalArgumentException.class, this::segedFuggveny);
    }

    void segedFuggveny(){
        bank.ujSzamla("Gipsz Jakab", "1234");
    }

    @Test
    void nemLetezoSzamlaEgyenlegeKivetel(){
        assertThrows(HibasSzamlaszamException.class,() -> bank.egyenleg("5678"));
    }
}