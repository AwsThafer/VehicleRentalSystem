# Vehicle Rental Management System

A Java console application for managing vehicle rentals.

## Features

- Add and search for cars, motorcycles, and trucks.
- Display all, available, and rented vehicles.
- Update vehicle rental prices.
- Register customers and update their contact information.
- Rent and return vehicles.
- Display rental history and active rentals.
- Calculate revenue from completed rentals.
- Flag vehicles for maintenance and perform maintenance.
- Validate input and reject invalid operations.

## Project Files

- Main.java: Starts the application.
- RentalApp.java: Displays the menu and reads user input.
- RentalSystem.java: Manages vehicles, customers, and rentals.
- Vehicle.java: Abstract parent class for vehicles.
- Car.java: Car information and optional insurance cost.
- Motorcycle.java: Motorcycle information and optional helmet cost.
- Truck.java: Truck information and service fee.
- Maintainable.java: Maintenance interface.
- Customer.java: Customer information.
- Rental.java: Rental information and return processing.

## Requirements

Java Development Kit (JDK) 8 or later.

## Compile

Open a terminal in the project folder and run:

javac *.java

## Run

java Main

Choose an option from the menu and enter the requested information.
Choose 0 to exit.

## Rental Costs

- Car: Daily price multiplied by days, plus $15 per day if insurance is selected.
- Motorcycle: Daily price multiplied by days, plus $5 per day if a helmet is selected.
- Truck: Daily price multiplied by days, plus a fixed $50 service fee.

## Data Storage

Data is stored in ArrayList collections while the program is running.
Closing the program removes the stored data.
No database or file storage is used.