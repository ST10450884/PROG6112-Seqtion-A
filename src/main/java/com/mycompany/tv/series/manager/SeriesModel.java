/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.tv.series.manager;

/**
 * TV series data model.  The specification is followed by using public fields but for better practice, a constructor and toString are added.
 * References:
 * Oracle (2024) The Java™ Tutorials – Classes and Objects. 
 * Available at: https://docs.oracle.com/javase/tutorial/java/javaOO/ (Accessed: 2 September 2025).
 *
 * JUnit Team (2023) JUnit 5 User Guide. 
 * Available at: https://junit.org/junit5/docs/current/user-guide/ (Accessed: 2 September 2025).
 */
public class SeriesModel {
    public String SeriesId;
    public String SeriesName;
    public String SeriesAge;              // stored as String per spec
    public String SeriesNumberOfEpisodes; // stored as String per spec

    public SeriesModel(String id, String name, String age, String episodes) {
        this.SeriesId = id;
        this.SeriesName = name;
        this.SeriesAge = age;
        this.SeriesNumberOfEpisodes = episodes;
    }

    @Override
    public String toString() {
        return "---\n" +
               "SERIES ID: " + SeriesId + "\n" +
               "SERIES NAME: " + SeriesName + "\n" +
               "SERIES AGE RESTRICTION: " + SeriesAge + "\n" +
               "NUMBER OF EPISODES: " + SeriesNumberOfEpisodes + "\n" +
               "---";
    }
}
