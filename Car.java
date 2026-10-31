public class Car extends Vehicle implements Maintainable {
    private int seats;
    private String transmissionType;
    private static final double INSURANCE_PER_DAY = 15;

    public Car(String vehicleId, String brand, String model,
               int year, double dailyPrice, int seats,
               String transmissionType) {
        super(vehicleId, brand, model, year, dailyPrice);

        if (seats <= 0) {
            throw new IllegalArgumentException("Seats must be positive.");
        }

        if (transmissionType == null || transmissionType.trim().isEmpty()) {
            throw new IllegalArgumentException("Transmission type is required.");
        }

        this.seats = seats;
        this.transmissionType = transmissionType.trim();
    }

    public int getSeats() {
        return seats;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    @Override
    public double calculateRentalCost(int days, boolean insurance) {
        double total = calculateBaseCost(days);

        if (insurance) {
            total = total + INSURANCE_PER_DAY * days;
        }

        return total;
    }

    @Override
    public void displayInfo() {
        System.out.println("Type: Car");
        displayBasicInfo();
        System.out.println("Seats: " + seats);
        System.out.println("Transmission: " + transmissionType);
    }

    @Override
    public boolean needsMaintenance() {
        return isMaintenanceRequired();
    }

    @Override
    public void performMaintenance() {
        completeMaintenance();
        System.out.println("Car oil, brakes and tires checked.");
        System.out.println("Maintenance completed.");
    }
}