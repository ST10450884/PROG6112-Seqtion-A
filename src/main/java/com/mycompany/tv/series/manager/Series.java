/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.tv.series.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Handles add, search, update, delete, and reporting of TV series.
 *
 * References:
 * Oracle (2025) Java Platform, Standard Edition 21 API Specification. 
 * Available at: https://docs.oracle.com/en/java/javase/21/docs/api/index.html (Accessed: 2 September 2025).
 *
 * Bloch, J. (2020) Effective Java. 3rd edn. Boston: Addison-Wesley.
 */
public class Series {

    // In-memory “database” (requirement 1.2: arrays or array lists)
    private final List<SeriesModel> data = new ArrayList<>();

    // ---------- Helper/validation methods (also used by tests) ----------

    /** Returns true if age is numeric and within [2..18]. */
    public boolean isValidAgeRestriction(String ageStr) {
        if (ageStr == null || ageStr.isBlank()) return false;
        for (char c : ageStr.trim().toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        try {
            int age = Integer.parseInt(ageStr.trim());
            return age >= 2 && age <= 18;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private Optional<SeriesModel> findById(String id) {
        return data.stream().filter(s -> s.SeriesId.equals(id)).findFirst();
    }

    // ---------- Menu actions (Scanner variants satisfy the console UX) ----------

    /** 1) Capture a new series: prompts, validates age, then saves to memory. */
    public void CaptureSeries(Scanner sc) {
        System.out.println("**CAPTURE A NEW SERIES**");
        System.out.println("***************");

        System.out.print("Enter the series id: ");
        String id = sc.nextLine().trim();

        System.out.print("Enter the series name: ");
        String name = sc.nextLine().trim();

        String age;
        while (true) {
            System.out.print("Enter the series age restriction: ");
            age = sc.nextLine().trim();
            if (isValidAgeRestriction(age)) break;
            System.out.println("You have entered a incorrect series age!!!");
            System.out.print("Please re-enter the series age >> ");
        }

        System.out.print("Enter the number of episodes for " + name + ": ");
        String episodes = sc.nextLine().trim();

        // Save to memory (1.2, 1.4)
        data.add(new SeriesModel(id, name, age, episodes));
        System.out.println("Series processed successfully!!!");
    }

    /** 2) Search for a series by id and print appropriate output. */
    public void SearchSeries(Scanner sc) {
        System.out.print("Enter the series id to search: ");
        String id = sc.nextLine().trim();

        Optional<SeriesModel> found = findById(id);
        if (found.isPresent()) {
            SeriesModel s = found.get();
            System.out.println("---SERIES ID: " + s.SeriesId);
            System.out.println("SERIES NAME: " + s.SeriesName);
            System.out.println("SERIES AGE RESTRICTION: " + s.SeriesAge);
            System.out.println("SERIES NUMBER OF EPISODES: " + s.SeriesNumberOfEpisodes);
        } else {
            System.out.println("---Series with Series Id: " + id + " was not found!");
        }
    }

    /** 3) Update series name, age, episodes. Age is validated. */
    public void UpdateSeries(Scanner sc) {
        System.out.print("Enter the series id to update: ");
        String id = sc.nextLine().trim();

        Optional<SeriesModel> found = findById(id);
        if (found.isEmpty()) {
            System.out.println("---Series with Series Id: " + id + " was not found!");
            return;
        }

        SeriesModel s = found.get();
        System.out.print("Enter the series name: ");
        String name = sc.nextLine().trim();

        String age;
        while (true) {
            System.out.print("Enter the age restriction: ");
            age = sc.nextLine().trim();
            if (isValidAgeRestriction(age)) break;
            System.out.println("You have entered a incorrect series age!!!");
            System.out.print("Please re-enter the series age >> ");
        }

        System.out.print("Enter the number of episodes: ");
        String episodes = sc.nextLine().trim();

        s.SeriesName = name;
        s.SeriesAge = age;
        s.SeriesNumberOfEpisodes = episodes;

        System.out.println("Series updated successfully.");
    }

    /** 4) Delete series after confirmation. */
    public void DeleteSeries(Scanner sc) {
        System.out.print("Enter the series id to delete: ");
        String id = sc.nextLine().trim();

        Optional<SeriesModel> found = findById(id);
        if (found.isEmpty()) {
            System.out.println("---Series with Series Id: " + id + " was NOT found!");
            return;
        }

        System.out.println("Are you sure you want to delete series " + id + " from the system? Yes (y) to delete.");
        String confirm = sc.nextLine().trim();
        if (confirm.equalsIgnoreCase("y")) {
            data.remove(found.get());
            System.out.println("---Series with Series Id: " + id + " WAS deleted!");
        } else {
            System.out.println("---Delete cancelled.");
        }
    }

    /** 5) Print series report exactly like the sample layout. */
    public void SeriesReport() {
        if (data.isEmpty()) {
            System.out.println("No series to display.");
            return;
        }
        int i = 1;
        for (SeriesModel s : data) {
            System.out.println("Series " + i);
            System.out.println(s.toString());
            i++;
        }
    }

    /** 6) Exit application. */
    public void ExitSeriesApplication() {
        System.out.println("Exiting application. Goodbye!");
    }

    // ---------- Non-interactive overloads (used by unit tests) ----------

    /** Adds a series directly (used in tests). */
    public void addSeriesDirect(SeriesModel s) {
        data.add(s);
    }

    /** Search helper returning the model (used in tests). */
    public SeriesModel searchById(String id) {
        return findById(id).orElse(null);
    }

    /** Update helper (used in tests). Returns true if updated. */
    public boolean updateDirect(String id, String name, String age, String episodes) {
        Optional<SeriesModel> found = findById(id);
        if (found.isEmpty() || !isValidAgeRestriction(age)) return false;
        SeriesModel s = found.get();
        s.SeriesName = name;
        s.SeriesAge = age;
        s.SeriesNumberOfEpisodes = episodes;
        return true;
    }

    /** Delete helper (used in tests). Returns true if deleted. */
    public boolean deleteDirect(String id) {
        Optional<SeriesModel> found = findById(id);
        if (found.isEmpty()) return false;
        return data.remove(found.get());
    }

    /** Expose current list size (simple coverage helper). */
    public int size() {
        return data.size();
    }
}
