import java.util.ArrayList;

public class RentalSystem {
    private ArrayList<Vehicle> vehicles;
    private ArrayList<Customer> customers;
    private ArrayList<Rental> rentals;

    public RentalSystem() {
        vehicles = new ArrayList<Vehicle>();
        customers = new ArrayList<Customer>();
        rentals = new ArrayList<Rental>();
    }

    public void addVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle is required.");
        }

        if (!(vehicle instanceof Maintainable)) {
            throw new IllegalArgumentException("Vehicle must support maintenance.");
        }

        for (Vehicle v : vehicles) {
            if (v.getVehicleId().equalsIgnoreCase(vehicle.getVehicleId())) {
                throw new IllegalArgumentException("Vehicle ID already exists.");
            }
        }

        vehicles.add(vehicle);
    }

    public Vehicle findVehicle(String vehicleId) {
        if (vehicleId == null || vehicleId.trim().isEmpty()) {
            throw new IllegalArgumentException("Vehicle ID is required.");
        }

        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleId().equalsIgnoreCase(vehicleId.trim())) {
                return vehicle;
            }
        }

        throw new IllegalArgumentException("Vehicle not found.");
    }

    public void updateVehiclePrice(String vehicleId, double price) {
        Vehicle vehicle = findVehicle(vehicleId);
        vehicle.setDailyPrice(price);
    }

    public void displayVehicles() {
        displayVehicles(false);
    }

    public void displayVehicles(boolean availableOnly) {
        boolean found = false;

        for (Vehicle vehicle : vehicles) {
            Maintainable maintainable = (Maintainable) vehicle;

            if (!availableOnly ||
                (vehicle.isAvailable() && !maintainable.needsMaintenance())) {
                vehicle.displayInfo();
                System.out.println("--------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching vehicles.");
        }
    }

    public void displayRentedVehicles() {
        boolean found = false;

        for (Vehicle vehicle : vehicles) {
            if (!vehicle.isAvailable()) {
                vehicle.displayInfo();
                System.out.println("--------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No rented vehicles.");
        }
    }

    public void registerCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer is required.");
        }

        for (Customer c : customers) {
            if (c.getCustomerId().equalsIgnoreCase(customer.getCustomerId())) {
                throw new IllegalArgumentException("Customer ID already exists.");
            }
        }

        customers.add(customer);
    }

    public Customer findCustomer(String customerId) {
        if (customerId == null || customerId.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer ID is required.");
        }

        for (Customer customer : customers) {
            if (customer.getCustomerId().equalsIgnoreCase(customerId.trim())) {
                return customer;
            }
        }

        throw new IllegalArgumentException("Customer not found.");
    }

    public void updateCustomerPhone(String customerId, String phone) {
        Customer customer = findCustomer(customerId);
        customer.setPhone(phone);
    }

    public void updateCustomerEmail(String customerId, String email) {
        Customer customer = findCustomer(customerId);
        customer.setEmail(email);
    }

    public void displayCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers registered.");
            return;
        }

        for (Customer customer : customers) {
            customer.displayInfo();
            System.out.println("--------------------");
        }
    }

    public Rental rentVehicle(String customerId, String vehicleId,
                              int days, boolean extraService) {
        Customer customer = findCustomer(customerId);
        Vehicle vehicle = findVehicle(vehicleId);

        Rental rental = new Rental(customer, vehicle, days, extraService);
        rentals.add(rental);

        return rental;
    }

    public Rental findRental(int rentalId) {
        for (Rental rental : rentals) {
            if (rental.getRentalId() == rentalId) {
                return rental;
            }
        }

        throw new IllegalArgumentException("Rental not found.");
    }

    public void returnVehicle(int rentalId) {
        Rental rental = findRental(rentalId);
        rental.completeRental();
    }

    public void displayRentals() {
        displayRentals(false);
    }

    public void displayRentals(boolean activeOnly) {
        boolean found = false;

        for (Rental rental : rentals) {
            if (!activeOnly || rental.isActive()) {
                rental.displayInfo();
                System.out.println("--------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching rentals.");
        }
    }

    public void displayCustomerHistory(String customerId) {
        Customer customer = findCustomer(customerId);
        boolean found = false;

        for (Rental rental : rentals) {
            if (rental.getCustomer() == customer) {
                rental.displayInfo();
                System.out.println("--------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("This customer has no rental history.");
        }
    }

    public void displayCustomerRentedVehicles(String customerId) {
        Customer customer = findCustomer(customerId);
        boolean found = false;

        for (Rental rental : rentals) {
            if (rental.getCustomer() == customer && rental.isActive()) {
                rental.getVehicle().displayInfo();
                System.out.println("--------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("This customer has no active rentals.");
        }
    }

    public double calculateTotalRevenue() {
        double total = 0;

        for (Rental rental : rentals) {
            if (!rental.isActive()) {
                total = total + rental.getTotalCost();
            }
        }

        return total;
    }

    public void flagVehicleForMaintenance(String vehicleId) {
        Vehicle vehicle = findVehicle(vehicleId);
        vehicle.flagForMaintenance();
    }

    public void displayVehiclesNeedingMaintenance() {
        boolean found = false;

        for (Vehicle vehicle : vehicles) {
            Maintainable maintainable = (Maintainable) vehicle;

            if (maintainable.needsMaintenance()) {
                vehicle.displayInfo();
                System.out.println("--------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No vehicles need maintenance.");
        }
    }

    public void performVehicleMaintenance(String vehicleId) {
        Vehicle vehicle = findVehicle(vehicleId);
        Maintainable maintainable = (Maintainable) vehicle;
        maintainable.performMaintenance();
    }
}