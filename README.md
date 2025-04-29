# WhatsApp Chat Analyzer 📊

This Java project analyzes a WhatsApp chat exported as a `.txt` file. It calculates how many days two people communicated within a given date range and identifies who initiated the conversation on each day.

![Ekran görüntüsü 2025-04-29 200816](https://github.com/user-attachments/assets/80487b85-717e-4f52-ade6-63831777d6ef)


## Features

- ✅ Takes two dates as input and calculates how many days apart they are
- ✅ Counts how many days messages were exchanged between the selected dates
- ✅ Identifies which person initiated the conversation on each day
- ✅ Displays how many times each person started a conversation
- ✅ Supports messages exported from WhatsApp in `.txt` format

## Technologies Used

- Java
- File reading (`BufferedReader`)
- Regular expressions
- Date handling (`LocalDate`, `DateTimeFormatter`)

## Example Input Format

Make sure your `.txt` file looks like this (WhatsApp default export):



## How to Use

1. Export a WhatsApp chat as a `.txt` file.
2. Place the file in the `src` folder of this project.
3. Run the program and enter:
   - The full file name (e.g., `chat.txt`)
   - The name of the other person in the conversation (e.g., `Bob`)
   - Start and end dates for analysis (e.g., `27.02.2025` to `05.03.2025`)
4. The program will display:
   - Total days between given dates
   - Number of days messages were exchanged
   - Who started the conversation each day
   - Total number of conversation initiations by each participant

## Project Purpose

This project was developed to reinforce understanding of object-oriented programming (OOP), file I/O, and date manipulation in Java. The goal was to create something useful, clear, and practical for real-world data analysis.

## Sample Chat File

Check out the example chat file in the `src` folder:  
📄 [`example-messages.txt`](src/example-messages.txt)

## Author

Elif Demir – Computer Engineering Student  
[GitHub Profile](https://github.com/elifpdemir)

---

Feel free to fork the project, give feedback, or suggest improvements!
