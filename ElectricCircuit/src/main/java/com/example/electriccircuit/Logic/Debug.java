package com.example.electriccircuit.Logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Debug {
    private static long startTime;

    public enum LogLevel {
        INFO, WARNING, ERROR
    }

    //Use Debug.Log(message, level)
    private static void Log(String message, LogLevel level){
        System.out.println("[" + level + "] [" + new Date() + "] " + message);
    }

    //Use Debug.Info(message, level)
    public static void Info(String message) {
        Log(message, LogLevel.INFO);
    }
    //Use Debug.Warning(message)
    public static void Warning(String message) {
        Log(message, LogLevel.WARNING);
    }
    //Use Debug.Error(message)
    public static void Error(String message) {
        Log(message, LogLevel.ERROR);
    }
    //Use Debug.Error(errorCode)
    public static void Error(int errorCode){
        Error("Error code: " + errorCode);
    }

    //Can be used to see if a condition is true
    public static void assertCondition(boolean condition, String message) {
        if (!condition) {
            Error("Assertion failed: " + message);
            throw new AssertionError(message);
        }
    }

    //Can be used to catch an exception
    /*
    catch (Exception e) {
        Debug.handleException(e);
    }  */
    public static void handleException(Exception e) {
        Error("Exception caught: " + e.getMessage());
        e.printStackTrace();
    }

    //Used to track time between code
    public static void startMonitoring() {
        startTime = System.nanoTime();
        Info("Performance monitoring started.");
    }
    public static void stopMonitoring() {
        long elapsedTime = System.nanoTime() - startTime;
        double seconds = (double) elapsedTime / 1_000_000_000.0;
        Info("Performance monitoring stopped. Elapsed time: " + seconds + " seconds.");
    }

    //Can be used as a breakpoint inside the console (pauses console execution until player input)
    public static void setBreakpoint() {
        Info("Breakpoint set. Pausing execution...");
        // Use a scanner to simulate pause
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine(); // Wait for user input
    }

    //Track a grid in a readable layout
    public static void printGrid(int[][] grid){
        for(int i = 0; i < grid[i].length; i++){
            for(int j = 0; j < grid.length; j++){
                System.out.print(grid[j][i] + " ");
            }
            System.out.println();
        }
    }
}
