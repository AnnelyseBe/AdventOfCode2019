package be.annelyse;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day1Test {

    Day1 day1 = new Day1();

    @Test
    void calculateFuel() {
        assertEquals(day1.calculateFuel(12L),2);
        assertEquals(day1.calculateFuel(14L),2);
        assertEquals(day1.calculateFuel(1969L),654);
        assertEquals(day1.calculateFuel(100756L),33583);
    }

    @Test
    void calculateFuelExtended() {
        assertEquals(day1.calculateFuelExtended(14L),2);
        assertEquals(day1.calculateFuelExtended(1969L),966);
        assertEquals(day1.calculateFuelExtended(100756L),50346);
    }

    @Test
    void calculateFuelExtendedStreaming() {
        assertEquals(day1.calculateFuelExtendedStreaming(14L),2);
        assertEquals(day1.calculateFuelExtendedStreaming(1969L),966);
        assertEquals(day1.calculateFuelExtendedStreaming(100756L),50346);
    }
}