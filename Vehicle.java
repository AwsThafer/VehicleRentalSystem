public abstract class Vehicle {
    private String vehicleId;
    private String brand;
    private String model;
    private int year;
    private double dailyPrice;
    private boolean available;
    private boolean maintenanceRequired;

    public Vehicle(String vehicleId, String brand, String model,
                   int year, double dailyPrice) {
        if (vehicleId == null || vehicleId.trim().isEmpty()) {
            throw new IllegalArgumentException("Vehicle ID is required.");
        }

        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Brand is required.");
        }

        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Model is required.");
        }

        if (year <= 0) {
            throw new IllegalArgumentException("Year must be positive.");
        }

        this.vehicleId = vehicleId.trim();
        this.brand = brand.trim();
        this.model = model.trim();
        this.year = year;
        setDailyPrice(dailyPrice);
        this.available = true;
        this.maintenanceRequired = false;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(double dailyPrice) {
        if (!Double.isFinite(dailyPrice) || dailyPrice <= 0) {
            throw new IllegalArgumentException("Price must be positive.");
        }

        this.dailyPrice = dailyPrice;
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean isMaintenanceRequired() {
        return maintenanceRequired;
    }

    public void flagForMaintenance() {
        maintenanceRequired = true;
    }

    public void rent() {
        if (!available) {
            throw new IllegalStateException("Vehicle is already rented.");
        }

        if (maintenanceRequired) {
            throw new IllegalStateException("Vehicle needs maintenance.");
        }

        available = false;
    }

    public void returnVehicle() {
        if (available) {
            throw new IllegalStateException("Vehicle is not rented.");
        }

        available = true;
    }

    protected void completeMaintenance() {
        if (!available) {
            throw new IllegalStateException("Return the vehicle first.");
        }

        maintenanceRequired = false;
    }

    protected double calculateBaseCost(int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Days must be positive.");
        }

        return dailyPrice * days;
    }

    protected void displayBasicInfo() {
        System.out.println("Vehicle ID: " + vehicleId);
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Daily price: $" + dailyPrice);
        System.out.println("Available: " + available);
        System.out.println("Needs maintenance: " + maintenanceRequired);
    }

    public abstract double calculateRentalCost(int days, boolean extraService);

    public abstract void displayInfo();
}