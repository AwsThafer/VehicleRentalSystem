public class Motorcycle extends Vehicle implements Maintainable {
    private int engineCapacity;
    private boolean helmetIncluded;
    private static final double HELMET_PER_DAY = 5;

    public Motorcycle(String vehicleId, String brand, String model,
                      int year, double dailyPrice, int engineCapacity) {
        super(vehicleId, brand, model, year, dailyPrice);

        if (engineCapacity <= 0) {
            throw new IllegalArgumentException("Engine capacity must be positive.");
        }

        this.engineCapacity = engineCapacity;
        this.helmetIncluded = false;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public boolean isHelmetIncluded() {
        return helmetIncluded;
    }

    public void setHelmetIncluded(boolean helmetIncluded) {
        this.helmetIncluded = helmetIncluded;
    }

    @Override
    public double calculateRentalCost(int days, boolean helmet) {
        double total = calculateBaseCost(days);

        if (helmet) {
            total = total + HELMET_PER_DAY * days;
        }

        return total;
    }

    @Override
    public void displayInfo() {
        System.out.println("Type: Motorcycle");
        displayBasicInfo();
        System.out.println("Engine capacity: " + engineCapacity + " CC");
        System.out.println("Helmet included in current rental: " + helmetIncluded);
    }

    @Override
    public boolean needsMaintenance() {
        return isMaintenanceRequired();
    }

    @Override
    public void performMaintenance() {
        completeMaintenance();
        System.out.println("Motorcycle chain, brakes and tires checked.");
        System.out.println("Maintenance completed.");
    }
}