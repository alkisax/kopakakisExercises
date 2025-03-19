# Theater Seat Booking System

This is a Java project designed to simulate a theater seat booking and cancellation system for a theater with 30 rows and 12 columns. The program uses a 2D array (boolean[][]) to represent the seating layout, where `true` indicates that a seat is available and `false` means it has been booked.

## Project Structure

### 1. **Main Class: `Exercise105`**

The project consists of a single class `Exercise105` which handles the booking and cancellation of theater seats. The core functionality is implemented through a set of methods that interact with the 2D array representing the theater's seating chart.

### 2. **Key Methods**

#### `main(String[] args)`
This is the entry point of the application. It initiates the theater array, prompts the user for input, and continuously asks for booking or cancellation choices until the user decides to exit.

#### `reRun(Scanner in)`
This method prompts the user whether they want to exit the program or perform another operation (book/cancel a seat). It returns `true` if the user wants to continue and `false` otherwise.

#### `userInt(Scanner in, boolean[][] theater)`
This method asks the user whether they want to book or cancel a seat. It then prompts for the column letter (A-L) and row number (1-30), and checks if the seat is available for booking or cancellation. It returns an array with the user's choice (1 for booking, 2 for cancellation), column number, and row number.

#### `columnNumInterface(Scanner in)`
Prompts the user for a valid column letter (A-L) and row number (1-30). It ensures that the user provides valid input and returns the corresponding column number and row number.

#### `letterNumConventor(String ch)`
Converts a column letter (A-L) to its corresponding column number. For example, 'A' becomes 1, 'B' becomes 2, and so on.

#### `numLetterConventor(int num)`
Converts a column number back to its corresponding letter. For example, 1 becomes 'A', 2 becomes 'B', etc.

#### `checkAvailability(int column, int row, boolean[][] theater)`
Checks if a specific seat (given by column and row) is available (not booked). Returns `true` if the seat is available and `false` if it is already booked.

#### `arrayInitialiser(boolean[][] theater)`
Initializes the theater array, marking all seats as available (`true`). This method sets up the seating layout before any bookings are made.

#### `book(char column, int row)`
Books a seat at the specified column and row by setting the corresponding value in the theater array to `false`.

#### `cancel(char column, int row)`
Cancels the booking of a seat at the specified column and row by setting the corresponding value in the theater array to `true`.

#### `clearScreen()`
Clears the console screen by printing 40 empty lines. This helps in creating a cleaner output interface for the user.

#### `displayBooked()`
Displays all the booked seats in the theater by iterating through the theater array. Booked seats are shown in the format "ColumnRow", e.g., "A1", "B2".

#### `isEmpty()`
Checks if all seats in the theater are available (not booked). Returns `true` if all seats are available, and `false` if any seat is booked.

## Usage

1. **Book a Seat:**
   To book a seat, the user chooses the option to book (1), then selects a seat by specifying the column letter (A-L) and row number (1-30).

2. **Cancel a Seat:**
   To cancel a booking, the user chooses the option to cancel (2), then selects the seat they wish to cancel by specifying the column letter (A-L) and row number (1-30).

3. **Exit or Re-run:**
   After each booking or cancellation, the user is prompted whether they want to exit the program or continue with another operation.


