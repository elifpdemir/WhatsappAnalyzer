package WhatsappMessages;
import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class FileScanner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get information from user
        System.out.println("To determine the range of messaging we are examining");
        System.out.print("enter start date (dd.MM.yyyy): ");
        String startDateStr = scanner.nextLine();

        System.out.print("Enter end date (dd.MM.yyyy): ");
        String endDateStr = scanner.nextLine();
        System.out.println();

        System.out.print("Enter the name of the person messaging with: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter the another name of the person messaging with: ");
        String secondName = scanner.nextLine();
        System.out.println();

        // Prepare the dates
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.parse(startDateStr, dateFormatter);
        LocalDate endDate = LocalDate.parse(endDateStr, dateFormatter);

        // Read messages
        List<String> lines = readMessages("src/WhatsappMessages/example-messages.txt");

        // Maps for analysis
        Set<LocalDate> messageDays = new HashSet<>();
        Map<LocalDate, String> firstMessageSender = new HashMap<>();

        DateTimeFormatter fullFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm:ss");

        for (String line : lines) {
            try {
                if (!line.startsWith("[")) continue; //Skip if not message line

                int dateEnd = line.indexOf(']');
                String dateTimeStr = line.substring(1, dateEnd);
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, fullFormatter);
                LocalDate messageDate = dateTime.toLocalDate();

                if (messageDate.isBefore(startDate) || messageDate.isAfter(endDate)) {
                    continue; // Skip if not in date range
                }

                messageDays.add(messageDate);

                // Save who sent the first message
                if (!firstMessageSender.containsKey(messageDate)) {
                    int nameStart = line.indexOf("] ") + 2;
                    int nameEnd = line.indexOf(":", nameStart);
                    if (nameEnd != -1) {
                        String sender = line.substring(nameStart, nameEnd);
                        firstMessageSender.put(messageDate, sender.trim());
                    }
                }

            } catch (Exception e) {
                // If there is an error line, skip it silently.
            }
        }

        // Calculate the results
        int totalMessageDays = messageDays.size();
        
        int userStartedDays = 0;

        for (Map.Entry<LocalDate, String> entry : firstMessageSender.entrySet()) {
            String sender = entry.getValue();
            if (sender.equalsIgnoreCase(secondName)) {
              
            } else if (sender.equalsIgnoreCase(firstName)) {
                userStartedDays++;
            }
            // If you're neither, let's not count
        }

        //How many days are there between the start and end date?
        long totalDaysBetween = ChronoUnit.DAYS.between(startDate, endDate) + 1; // +1 to include

        // Show results
   
        System.out.println("Number of days messaged in a " +totalDaysBetween+"-day period: " + totalMessageDays);
        System.out.println("Number of days that "+ firstName+" started the message: " +  userStartedDays);
        System.out.println("Number of days that "+ secondName+" started the message: " + (totalMessageDays-userStartedDays));
       
    }

    // Helper method that reads the file
    private static List<String> readMessages(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("file not found: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
