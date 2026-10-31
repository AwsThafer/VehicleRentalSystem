public class Rental {
    private static int nextRentalId = 1;

    private int rentalId;
    private Customer customer;
    private Vehicle vehicle;
    private int days;
    private double totalCost;
    private boolean active;

    public Rental(Customer customer, Vehicle vehicle,
                  int days, boolean extraService) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer is required.");
        }

        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle is required.");
        }

        if (days <= 0) {
            throw new IllegalArgumentException("Days must be positive.");
        }

        double cost = vehicle.calculateRentalCost(days, extraService);

        if (!Double.isFinite(cost) || cost <= 0) {
            throw new IllegalArgumentException("Invalid rental cost.");
        }

        vehicle.rent();

        if (vehicle instanceof Motorcycle) {
            Motorcycle motorcycle = (Motorcycle) vehicle;
            motorcycle.setHelmetIncluded(extraService);
        }

        this.rentalId = nextRentalId;
        nextRentalId++;
        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
        this.totalCost = cost;
        this.active = true;
    }

    public int getRentalId() {
        return rentalId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getDays() {
        return days;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public boolean isActive() {
        return active;
    }

    public String getStatus() {
        if (active) {
            return "Active";
        }

        return "Completed";
    }

    public void completeRental() {
        if (!active) {
            throw new IllegalStateException("Rental is already completed.");
        }

        vehicle.returnVehicle();

        if (vehicle instanceof Motorcycle) {
            Motorcycle motorcycle = (Motorcycle) vehicle;
            motorcycle.setHelmetIncluded(false);
        }

        active = false;
    }

    public void displayInfo() {
        System.out.println("Rental ID: " + rentalId);
        System.out.println("Customer ID: " + customer.getCustomerId());
        System.out.println("Customer name: " + customer.getFullName());
        System.out.println("Vehicle ID: " + vehicle.getVehicleId());
        System.out.println("Vehicle: " + vehicle.getBrand()
                           + " " + vehicle.getModel());
        System.out.println("Days: " + days);
       System.out.printf(java.util.Locale.US, "Total cost: $%.2f%n", totalCost);
        System.out.println("Status: " + getStatus());
    }
}