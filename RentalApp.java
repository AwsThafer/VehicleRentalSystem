import java.util.Scanner;
import java.util.NoSuchElementException;

public class RentalApp {
    private RentalSystem system;
    private Scanner input;

    public RentalApp() {
        system = new RentalSystem();
        input = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();

            try {
                int choice = readInt("Choose an option: ");

                switch (choice) {
                    case 1:
                        addVehicle();
                        break;

                    case 2:
                        system.displayVehicles();
                        break;

                    case 3:
                        searchVehicle();
                        break;

                    case 4:
                        system.displayVehicles(true);
                        break;

                    case 5:
                        registerCustomer();
                        break;

                    case 6:
                        system.displayCustomers();
                        break;

                    case 7:
                        rentVehicle();
                        break;

                    case 8:
                        returnVehicle();
                        break;

                    case 9:
                        system.displayRentals();
                        break;

                    case 10:
                        displayCustomerHistory();
                        break;

                    case 11:
                        System.out.printf(java.util.Locale.US, "Total revenue: $%.2f%n",
                         system.calculateTotalRevenue());
                         break;

                    case 12:
                        flagMaintenance();
                        break;

                    case 13:
                        system.displayVehiclesNeedingMaintenance();
                        break;

                    case 14:
                        performMaintenance();
                        break;

                    case 15:
                        updateVehiclePrice();
                        break;

                    case 16:
                        searchCustomer();
                        break;

                    case 17:
                        updateCustomerPhone();
                        break;

                    case 18:
                        updateCustomerEmail();
                        break;

                    case 19:
                        system.displayRentedVehicles();
                        break;

                    case 20:
                        system.displayRentals(true);
                        break;

                    case 21:
                        displayCustomerRentedVehicles();
                        break;

                    case 0:
                        running = false;
                        System.out.println("Goodbye.");
                        break;

                    default:
                        System.out.println("Invalid option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            } catch (NoSuchElementException e) {
                System.out.println("Input ended.");
                running = false;
            }
        }

        input.close();
    }

    private void displayMenu() {
        System.out.println();
        System.out.println("Vehicle Rental Management System");
        System.out.println("1. Add New Vehicle");
        System.out.println("2. Display All Vehicles");
        System.out.println("3. Search for Vehicle");
        System.out.println("4. Display Available Vehicles");
        System.out.println("5. Register New Customer");
        System.out.println("6. Display All Customers");
        System.out.println("7. Rent a Vehicle");
        System.out.println("8. Return a Vehicle");
        System.out.println("9. Display All Rentals");
        System.out.println("10. Display Customer Rental History");
        System.out.println("11. Display Total Revenue");
        System.out.println("12. Flag Vehicle for Maintenance");
        System.out.println("13. Display Vehicles Requiring Maintenance");
        System.out.println("14. Perform Vehicle Maintenance");
        System.out.println("15. Update Vehicle Price");
        System.out.println("16. Search for Customer");
        System.out.println("17. Update Customer Phone");
        System.out.println("18. Update Customer Email");
        System.out.println("19. Display Rented Vehicles");
        System.out.println("20. Display Active Rentals");
        System.out.println("21. Display Customer's Current Vehicles");
        System.out.println("0. Exit");
    }

    private String readText(String message) {
        System.out.print(message);
        return input.nextLine().trim();
    }

    private int readInt(String message) {
        return Integer.parseInt(readText(message));
    }

    private double readDouble(String message) {
        double value = Double.parseDouble(readText(message));

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Enter a finite number.");
        }

        return value;
    }

    private boolean readYesNo(String message) {
        while (true) {
            String answer = readText(message);

            if (answer.equalsIgnoreCase("yes") ||
                answer.equalsIgnoreCase("y")) {
                return true;
            }

            if (answer.equalsIgnoreCase("no") ||
                answer.equalsIgnoreCase("n")) {
                return false;
            }

            System.out.println("Enter yes or no.");
        }
    }

    private void addVehicle() {
        System.out.println("1. Car");
        System.out.println("2. Motorcycle");
        System.out.println("3. Truck");

        int type = readInt("Choose vehicle type: ");

        if (type < 1 || type > 3) {
            throw new IllegalArgumentException("Invalid vehicle type.");
        }

        String id = readText("Vehicle ID: ");
        String brand = readText("Brand: ");
        String model = readText("Model: ");
        int year = readInt("Manufacturing year: ");
        double price = readDouble("Daily price: ");

        Vehicle vehicle;

        if (type == 1) {
            int seats = readInt("Number of seats: ");
            String transmission = readText("Transmission type: ");

            vehicle = new Car(id, brand, model, year, price,
                              seats, transmission);
        } else if (type == 2) {
            int capacity = readInt("Engine capacity in CC: ");

            vehicle = new Motorcycle(id, brand, model, year,
                                     price, capacity);
        } else {
            double load = readDouble("Maximum load in tons: ");

            vehicle = new Truck(id, brand, model, year, price, load);
        }

        system.addVehicle(vehicle);
        System.out.println("Vehicle added successfully.");
    }

    private void searchVehicle() {
        String id = readText("Vehicle ID: ");
        Vehicle vehicle = system.findVehicle(id);
        vehicle.displayInfo();
    }

    private void registerCustomer() {
        String id = readText("Customer ID: ");
        String name = readText("Full name: ");
        String phone = readText("Phone number: ");
        String email = readText("Email, or press Enter to skip: ");
        String license = readText("Driving license number: ");

        Customer customer;

        if (email.isEmpty()) {
            customer = new Customer(id, name, phone, license);
        } else {
            customer = new Customer(id, name, phone, email, license);
        }

        system.registerCustomer(customer);
        System.out.println("Customer registered successfully.");
    }

    private void rentVehicle() {
        String customerId = readText("Customer ID: ");
        system.findCustomer(customerId);

        String vehicleId = readText("Vehicle ID: ");
        Vehicle vehicle = system.findVehicle(vehicleId);

        if (!vehicle.isAvailable()) {
            throw new IllegalStateException("Vehicle is already rented.");
        }

        Maintainable maintainable = (Maintainable) vehicle;

        if (maintainable.needsMaintenance()) {
            throw new IllegalStateException("Vehicle needs maintenance.");
        }

        int days = readInt("Rental days: ");

        if (days <= 0) {
            throw new IllegalArgumentException("Days must be positive.");
        }

        boolean extraService = false;

        if (vehicle instanceof Car) {
            extraService = readYesNo("Add insurance for $15/day? (yes/no): ");
        } else if (vehicle instanceof Motorcycle) {
            extraService = readYesNo("Add a helmet for $5/day? (yes/no): ");
        }

        Rental rental = system.rentVehicle(customerId, vehicleId,
                                           days, extraService);

        System.out.println("Vehicle rented successfully.");
        rental.displayInfo();
    }

    private void returnVehicle() {
        int rentalId = readInt("Rental ID: ");
        system.returnVehicle(rentalId);

        System.out.println("Vehicle returned successfully.");
        system.findRental(rentalId).displayInfo();
    }

    private void displayCustomerHistory() {
        String id = readText("Customer ID: ");
        system.displayCustomerHistory(id);
    }

    private void flagMaintenance() {
        String id = readText("Vehicle ID: ");
        system.flagVehicleForMaintenance(id);
        System.out.println("Vehicle flagged for maintenance.");
    }

    private void performMaintenance() {
        String id = readText("Vehicle ID: ");
        system.performVehicleMaintenance(id);
    }

    private void updateVehiclePrice() {
        String id = readText("Vehicle ID: ");
        double price = readDouble("New daily price: ");

        system.updateVehiclePrice(id, price);
        System.out.println("Price updated successfully.");
    }

    private void searchCustomer() {
        String id = readText("Customer ID: ");
        Customer customer = system.findCustomer(id);
        customer.displayInfo();
    }

    private void updateCustomerPhone() {
        String id = readText("Customer ID: ");
        String phone = readText("New phone number: ");

        system.updateCustomerPhone(id, phone);
        System.out.println("Phone updated successfully.");
    }

    private void updateCustomerEmail() {
        String id = readText("Customer ID: ");
        String email = readText("New email: ");

        system.updateCustomerEmail(id, email);
        System.out.println("Email updated successfully.");
    }

    private void displayCustomerRentedVehicles() {
        String id = readText("Customer ID: ");
        system.displayCustomerRentedVehicles(id);
    }
}