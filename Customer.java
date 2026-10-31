public class Customer {
    private String customerId;
    private String fullName;
    private String phone;
    private String email;
    private String drivingLicenseNumber;

    public Customer(String customerId, String fullName, String phone,
                    String email, String drivingLicenseNumber) {
        if (customerId == null || customerId.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer ID is required.");
        }

        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Full name is required.");
        }

        if (drivingLicenseNumber == null ||
            drivingLicenseNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Driving license is required.");
        }

        this.customerId = customerId.trim();
        this.fullName = fullName.trim();
        this.drivingLicenseNumber = drivingLicenseNumber.trim();
        setPhone(phone);
        setEmail(email);
    }

    public Customer(String customerId, String fullName, String phone,
                    String drivingLicenseNumber) {
        this(customerId, fullName, phone, "", drivingLicenseNumber);
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number is required.");
        }

        this.phone = phone.trim();
    }

    public void setEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null.");
        }

        email = email.trim();

        if (!email.isEmpty()) {
            int at = email.indexOf('@');
            int dot = email.lastIndexOf('.');

            if (at <= 0 || dot <= at + 1 || dot == email.length() - 1 ||
                at != email.lastIndexOf('@') || email.contains(" ")) {
                throw new IllegalArgumentException("Invalid email address.");
            }
        }

        this.email = email;
    }

    public void displayInfo() {
        System.out.println("Customer ID: " + customerId);
        System.out.println("Full name: " + fullName);
        System.out.println("Phone: " + phone);

        if (email.isEmpty()) {
            System.out.println("Email: Not provided");
        } else {
            System.out.println("Email: " + email);
        }

        System.out.println("Driving license: " + drivingLicenseNumber);
    }
}