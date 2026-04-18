package com.nivetha;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Expense {
    private String category;
    private double cost;

    public Expense(String category, double cost) {
        this.category = category;
        this.cost = cost;
    }

    public String getCategory() {
        return category;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return category + ": " + cost;
    }
}

class Event {
    private String name;
    private double budget;
    private List<Expense> expenses;

    public Event(String name, double budget) {
        this.name = name;
        this.budget = budget;
        this.expenses = new ArrayList<>();
    }

    public void addExpense(String category, double cost) {
        Expense expense = new Expense(category, cost);
        expenses.add(expense);
    }

    public double calculateTotalExpense() {
        double total = 0;
        for (Expense e : expenses) {
            total += e.getCost();
        }
        return total;
    }

    public double calculateAverageExpense() {
        if (expenses.isEmpty()) return 0;
        return calculateTotalExpense() / expenses.size();
    }

    public void printReport() {
        double totalExpense = calculateTotalExpense();

        System.out.println("\n====== Event Budget Report ======");
        System.out.println("Event Name: " + name);
        System.out.println("Total Budget: " + budget);
        System.out.println("Total Expense: " + totalExpense);

        System.out.println("\nExpense Breakdown:");
        for (Expense e : expenses) {
            System.out.println(" - " + e);
        }

        if (totalExpense > budget) {
            System.out.println("\n⚠️ You are OVER BUDGET by: " + (totalExpense - budget));
        } else if (totalExpense < budget) {
            System.out.println("\n✅ You are UNDER BUDGET. Savings: " + (budget - totalExpense));
        } else {
            System.out.println("\nPerfect! You matched the budget exactly.");
        }

        System.out.println("Average Expense per Category: " + calculateAverageExpense());
    }
}

public class EventBudgetCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("====== Event Budget Planning Calculator ======");
        System.out.print("Enter Event Name: ");
        String eventName = sc.nextLine();

        System.out.print("Enter Total Budget: ");
        double budget = sc.nextDouble();

        Event event = new Event(eventName, budget);

        System.out.print("Enter number of expense categories: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        for (int i = 0; i < n; i++) {
            System.out.print("Enter category " + (i + 1) + " name: ");
            String category = sc.nextLine();

            System.out.print("Enter cost for " + category + ": ");
            double cost = sc.nextDouble();
            sc.nextLine(); // consume newline

            event.addExpense(category, cost);
        }

        event.printReport();

        sc.close();
    }
}
