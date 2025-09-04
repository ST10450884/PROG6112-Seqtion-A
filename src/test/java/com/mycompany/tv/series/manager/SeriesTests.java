/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.tv.series.manager;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Series CRUD and validation features.
 *
 * References:
 * JUnit Team. (2024) JUnit 5 User Guide. Available at: https://junit.org/junit5/docs/current/user-guide/ (Accessed: 2 September 2025).
 * Oracle. (2024) The Java™ Tutorials – Classes and Objects. Available at: https://docs.oracle.com/javase/tutorial/java/javaOO/ (Accessed: 2 September 2025).
 * Apache. (2023) Introduction to the POM. Available at: https://maven.apache.org/pom.html (Accessed: 2 September 2025).
 */
public class SeriesTests {

    @Test
    void TestSearchSeries() {
        Series s = new Series();
        s.addSeriesDirect(new SeriesModel("101", "Extreme Sports", "12", "10"));
        SeriesModel found = s.searchById("101");
        assertNotNull(found);
        assertEquals("Extreme Sports", found.SeriesName);
        assertEquals("12", found.SeriesAge);
        assertEquals("10", found.SeriesNumberOfEpisodes);
    }

    @Test
    void TestSearchSeries_SeriesNotFound() {
        Series s = new Series();
        s.addSeriesDirect(new SeriesModel("101", "Extreme Sports", "12", "10"));
        SeriesModel missing = s.searchById("999");
        assertNull(missing);
    }

    @Test
    void TestUpdateSeries() {
        Series s = new Series();
        s.addSeriesDirect(new SeriesModel("101", "Extreme Sports", "12", "10"));
        boolean updated = s.updateDirect("101", "Extreme Sports 2025", "10", "12");
        assertTrue(updated);
        SeriesModel after = s.searchById("101");
        assertEquals("Extreme Sports 2025", after.SeriesName);
        assertEquals("10", after.SeriesAge);
        assertEquals("12", after.SeriesNumberOfEpisodes);
    }

    @Test
    void TestDeleteSeries() {
        Series s = new Series();
        s.addSeriesDirect(new SeriesModel("101", "Extreme Sports", "12", "10"));
        assertEquals(1, s.size());
        boolean deleted = s.deleteDirect("101");
        assertTrue(deleted);
        assertEquals(0, s.size());
        assertNull(s.searchById("101"));
    }

    @Test
    void TestDeleteSeries_SeriesNotFound() {
        Series s = new Series();
        s.addSeriesDirect(new SeriesModel("101", "Extreme Sports", "12", "10"));
        boolean deleted = s.deleteDirect("777");
        assertFalse(deleted);
        assertEquals(1, s.size());
    }

    @Test
    void TestSeriesAgeRestriction_AgeValid() {
        Series s = new Series();
        assertTrue(s.isValidAgeRestriction("2"));
        assertTrue(s.isValidAgeRestriction("18"));
        assertTrue(s.isValidAgeRestriction("12"));
    }

    @Test
    void TestSeriesAgeRestriction_SeriesAgeInValid() {
        Series s = new Series();
        assertFalse(s.isValidAgeRestriction("1"));    // too low
        assertFalse(s.isValidAgeRestriction("19"));   // too high
        assertFalse(s.isValidAgeRestriction("Ten"));  // not numeric
        assertFalse(s.isValidAgeRestriction(""));     // blank
        assertFalse(s.isValidAgeRestriction("  "));   // whitespace
        assertFalse(s.isValidAgeRestriction(null));   // null
    }
}
