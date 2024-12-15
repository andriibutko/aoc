## How to Run

### Running the Application from the Command Line

1. To run the application of a specific day, use the following command:
    ```bash
    ./gradlew run --args="<day_number> <part_number>"
    ```
      Replace `<day_number>` with the day number and `<part_number>` with the part number (e.g., for Day 2, part 2, use `--args="2 2"`).
2. If you're working on the current day, you can run part 1 without specifying the day:
    ```bash
    ./gradlew run
    ```

### Running with VSCode

If you're using VSCode, this project includes a launch template that will prompt you for the day and part. When prompted:

- Enter the day number to run part 1 (e.g., for Day 3 part 1, enter `3`).
- Enter the day number followed by the part number to run part 2 (e.g., for Day 4 part 2, enter `4 2`).
