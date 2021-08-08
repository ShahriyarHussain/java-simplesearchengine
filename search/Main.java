package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = null;
        File textFile = null;
        try {
            textFile = new File(args[1]);
            scanner = new Scanner(textFile);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        
        assert scanner != null;

        List<String> listOfPeople = new LinkedList<>();
        while (scanner.hasNextLine()) listOfPeople.add(scanner.nextLine());
        scanner.close(); textFile.delete();

        option: while (true) {
            System.out.println("=== Menu ===" + System.lineSeparator() +
                    "1. Find a person" + System.lineSeparator() +
                    "2. Print all people" + System.lineSeparator() +
                    "0. Exit");
            int menuChoice = Integer.parseInt(new Scanner(System.in).nextLine());

            switch (menuChoice) {
                case 0:
                    System.out.println("Bye!");
                    break option;

                case 1:
                    System.out.println("Enter a name or email to search all suitable people.");
                    System.out.println(findPeople(new Scanner(System.in).nextLine(), listOfPeople) + System.lineSeparator());
                    break;

                case 2:
                    System.out.println("=== List of people ===");
                    listOfPeople.forEach(System.out::println);
                    System.out.println();
                    break;

                default:
                    System.out.println("Incorrect option! Try again");
            }
        }
    }

    public static String findPeople(String searchString, List<String>listOfPeople) {

        StringBuilder matches = new StringBuilder();
        for (String people : listOfPeople) {
            if (people.toLowerCase().contains(searchString.toLowerCase()))
                matches.append(people).append(System.lineSeparator());
        }

        if (matches.length() == 0)
            return "No matching people found";
        else
            return matches.toString();

    }
}