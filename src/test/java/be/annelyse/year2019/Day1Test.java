package be.annelyse.year2019;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day1Test {

    @Test
    void calculateFuel() {
        assertEquals(Day1.calculateFuel(12L),2);
        assertEquals(Day1.calculateFuel(14L),2);
        assertEquals(Day1.calculateFuel(1969L),654);
        assertEquals(Day1.calculateFuel(100756L),33583);
    }

    @Test
    void calculateFuelExtended() {
        assertEquals(Day1.calculateFuelExtended(14L),2);
        assertEquals(Day1.calculateFuelExtended(1969L),966);
        assertEquals(Day1.calculateFuelExtended(100756L),50346);
    }

    @Test
    void calculateFuelExtendedStreaming() {
        assertEquals(Day1.calculateFuelExtendedStreaming(14L),2);
        assertEquals(Day1.calculateFuelExtendedStreaming(1969L),966);
        assertEquals(Day1.calculateFuelExtendedStreaming(100756L),50346);
    }
}