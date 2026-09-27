import java.util.ArrayList;
import java.util.Scanner;

class Vehicle {
    int id;
    String name;
    String type;
    double pricePerDay;
    boolean available;

    Vehicle(int id, String name, String type, double pricePerDay) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.pricePerDay = pricePerDay;
        this.available = true;
    }
}

public class VehicleRentalManagement {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Vehicle> vehicles = new ArrayList<>();

    static String customerName = "";
    static Vehicle rentedVehicle = null;
    static int rentalDays = 0;

    public static void addVehicles() {
        vehicles.add(new Vehicle(1, "Swift", "Car", 1500));
        vehicles.add(new Vehicle(2, "Honda City", "Car", 2000));
        vehicles.add(new Vehicle(3, "Activa", "Bike", 700));
        vehicles.add(new Vehicle(4, "Royal Enfield", "Bike", 1200));
    }

    public static void viewVehicles() {

        System.out.println("\n===== VEHICLE LIST =====");

        for (Vehicle v : vehicles) {
            System.out.println(
                "ID: " + v.id +
                " | " + v.name +
                " | Type: " + v.type +
                " | Price/Day: Rs." + v.pricePerDay +
                " | Status: " +
                (v.available ? "Available" : "Rented")
            );
        }
    }

    public static void rentVehicle() {

        if (rentedVehicle != null) {
            System.out.println("You already have a rented vehicle.");
            return;
        }

        System.out.print("Enter customer name: ");
        customerName = sc.nextLine();

        viewVehicles();

        System.out.print("\nEnter Vehicle ID: ");
        int id = sc.nextInt();

        Vehicle selected = null;

        for (Vehicle v : vehicles) {
            if (v.id == id) {
                selected = v;
                break;
            }
        }

        if (selected == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        if (!selected.available) {
            System.out.println("Vehicle is already rented.");
            return;
        }

        System.out.print("Enter number of rental days: ");
        rentalDays = sc.nextInt();

        if (rentalDays <= 0) {
            System.out.println("Invalid number of days.");
            return;
        }

        rentedVehicle = selected;
        rentedVehicle.available = false;

        double total = rentedVehicle.pricePerDay * rentalDays;

        System.out.println("\n===== RENTAL CONFIRMED =====");
        System.out.println("Customer: " + customerName);
        System.out.println("Vehicle: " + rentedVehicle.name);
        System.out.println("Type: " + rentedVehicle.type);
        System.out.println("Days: " + rentalDays);
        System.out.println("Total Cost: Rs." + total);
    }

    public static void viewRental() {

        if (rentedVehicle == null) {
            System.out.println("No active rental found.");
            return;
        }

        double total = rentedVehicle.pricePerDay * rentalDays;

        System.out.println("\n===== RENTAL DETAILS =====");
        System.out.println("Customer: " + customerName);
        System.out.println("Vehicle: " + rentedVehicle.name);
        System.out.println("Type: " + rentedVehicle.type);
        System.out.println("Rental Days: " + rentalDays);
        System.out.println("Total Cost: Rs." + total);
    }

    public static void returnVehicle() {

        if (rentedVehicle == null) {
            System.out.println("No vehicle to return.");
            return;
        }

        rentedVehicle.available = true;

        System.out.println(
            rentedVehicle.name + " returned successfully!"
        );

        rentedVehicle = null;
        customerName = "";
        rentalDays = 0;
    }

    public static void main(String[] args) {

        addVehicles();

        while (true) {

            System.out.println("\n==============================");
            System.out.println(" VEHICLE RENTAL MANAGEMENT");
            System.out.println("==============================");
            System.out.println("1. View Vehicles");
            System.out.println("2. Rent Vehicle");
            System.out.println("3. View Rental");
            System.out.println("4. Return Vehicle");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    viewVehicles();
                    break;

                case 2:
                    rentVehicle();
                    break;

                case 3:
                    viewRental();
                    break;

                case 4:
                    returnVehicle();
                    break;

                case 5:
                    System.out.println("Thank you for using the system!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
