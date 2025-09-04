/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.tv.series.manager;

import java.util.Scanner;

/**
 * Console user interface that precisely matches the brief's menu and messages. All operations are carried out using the Series class.
 * References:
 * Oracle (2025) Class Scanner (Java SE 21 & JDK 21 Documentation). 
 * Available at: https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Scanner.html (Accessed: 2 September 2025).
 *
 * Liang, Y.D. (2022) Introduction to Java Programming and Data Structures. 12th edn. Pearson.
 */
public class SeriesApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Series series = new Series();

        while (true) {
            System.out.println("LATEST SERIES - 2025");
            System.out.println("......");
            System.out.print("Enter (1) to launch menu or any other key to exit\n");

            String launch = sc.nextLine().trim();
            if (!"1".equals(launch)) {
                series.ExitSeriesApplication();
                break;
            }

            System.out.println("Please select one of the following menu items:");
            System.out.println("(1) Capture a new series.");
            System.out.println("(2) Search for a series.");
            System.out.println("(3) Update series age restriction");
            System.out.println("(4) Delete a series.");
            System.out.println("(5) Print series report - 2025");
            System.out.println("(6) Exit Application.");

            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1" -> series.CaptureSeries(sc);
                case "2" -> series.SearchSeries(sc);
                case "3" -> series.UpdateSeries(sc);
                case "4" -> series.DeleteSeries(sc);
                case "5" -> series.SeriesReport();
                case "6" -> {
                    series.ExitSeriesApplication();
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }

            System.out.print("Enter (1) to launch menu or any other key to exit\n");
            String again = sc.nextLine().trim();
            if (!"1".equals(again)) {
                series.ExitSeriesApplication();
                break;
            }
        }
        sc.close();
    }
}
