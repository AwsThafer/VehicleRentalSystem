public class Truck extends Vehicle implements Maintainable {
    private double maxLoadCapacity;
    private static final double SERVICE_FEE = 50;

    public Truck(String vehicleId, String brand, String model,
                 int year, double dailyPrice, double maxLoadCapacity) {
        super(vehicleId, brand, model, year, dailyPrice);

        if (!Double.isFinite(maxLoadCapacity) || maxLoadCapacity <= 0) {
            throw new IllegalArgumentException("Load capacity must be positive.");
        }

        this.maxLoadCapacity = maxLoadCapacity;
    }

    public double getMaxLoadCapacity() {
        return maxLoadCapacity;
    }

    @Override
    public double calculateRentalCost(int days, boolean extraService) {
        return calculateBaseCost(days) + SERVICE_FEE;
    }

    @Override
    public void displayInfo() {
        System.out.println("Type: Truck");
        displayBasicInfo();
        System.out.println("Maximum load: " + maxLoadCapacity + " tons");
    }

    @Override
    public boolean needsMaintenance() {
        return isMaintenanceRequired();
    }

    @Override
    public void performMaintenance() {
        completeMaintenance();
        System.out.println("Truck brakes, suspension and load platform checked.");
        System.out.println("Maintenance completed.");
    }
}