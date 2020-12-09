import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Exercise 5: More State Capitals
 * C65 Java Full-Stack with React
 * The Software Guild
 *
 * @author Russell Taylor
 * Date: December 8, 2020
 *
 * Instructions
 * In this lab, you will write a simple program that holds all of the states and their corresponding capitals in a Map. This lab is similar to the previous exercise, but you will create an object to hold information about the capital of each state. This object (rather than the city name string) will be the value for each state/capital pair.
 *
 * Also, instead of doing the exhaustive hand hardcoding of each capital's information, you'll load in all of the information from a given file.
 *
 * Requirements
 * Your program must have the following features:
 *
 * It will be a Java Console Application called MoreStateCapitals.
 * It will have a class called Capital with the following properties:
 * Name
 * Population
 * Square mileage
 * It will have another class with your main method called StateCapitalsApp.
 * This class will have a main method.
 * The main method will include a Map declared to map the name of a state to its corresponding Capital object. (State name is the key, Capital object is the value.)
 * Next, using your data unmarshaling strategy to use this linked file to create all appropriate Capital objects and store them under their appropriate state name in your Map:
 * MoreStateCapitals.txt (Links to an external site.)
 * Print out a message detailing how many State Capitals were loaded into your HashMap.
 * Next, print out each state and its capitals name, population, and square mileage to the screen.
 * Hint: Use the key set to get each Capital object out of the map one by one and then print each field of the Capital object to the screen.
 * Then, prompt the user for a population limit, print out all states & their capitals who have a population over that limit.
 * Hint: You will have to add code to ask the user for a minimum population. Once you have this value, go through each state/capital pair as you did for the last step, but only print the information for capitals that have a population above the limit.
 * Finally, prompt the user for an area limit, print out all states & their capitals who have an area under that limit.
 */

public class StateCapitalsApp {

    private final Map<String, Capital> capitals = new HashMap<>();

    /**
     * Reads input file data and creates a hash map of state capital information.
     */
    private void createMap() throws IOException {
        System.out.println("Importing file data");
        Scanner fin = new Scanner(new File("MoreStateCapitals.txt")).useDelimiter("::|\\r\\n");

        while (fin.hasNext()) {
            String state = fin.next();
            String capital = fin.next();
            int population = fin.nextInt();
            double squareMileage = fin.nextDouble();
            capitals.put(state, new Capital(capital, population, squareMileage));
        }
        fin.close();

        System.out.println(capitals);
    }

    /**
     * Prints the size of the hash map to the console.
     */
    private void printMapSize() {
        System.out.println("\nNumber of state capitals: " + capitals.size());
    }

    /**
     * Accesses the set of all keys in the hash map and prints the key-value pairs to the console.
     */
    private void printKeyValuePairs() {
        System.out.println("\nPrinting key-value pairs");
        Set<String> keys = capitals.keySet();
        for (String key : keys)
            System.out.printf("%s, %s, %d, %.2f\n", key, capitals.get(key).getName(), capitals.get(key).getPopulation(), capitals.get(key).getSquareMileage());
    }

    /**
     * Prompts the user to input a minimum population, then accesses the set of all keys in the hash map, and prints only the key-value pairs with a population greater than the input value.
     */
    private void limitPopulation() {
        System.out.println("\nPlease enter the lower limit for capital city population:");
        Scanner in = new Scanner(System.in);
        int minPopulation = in.nextInt();

        System.out.println("\nPrinting key-value pairs");
        Set<String> keys = capitals.keySet();
        for (String key : keys)
            if (capitals.get(key).getPopulation() >= minPopulation)
                System.out.printf("%s, %s, %d, %.2f\n", key, capitals.get(key).getName(), capitals.get(key).getPopulation(), capitals.get(key).getSquareMileage());
    }

    /**
     * Prompts the user to input a maximum square mileage, then accesses the set of all keys in the hash map, and prints only the key-value pairs with a square mileage less than the input value.
     */
    private void limitSquareMileage() {
        System.out.println("\nPlease enter the upper limit for capital city square mileage:");
        Scanner in = new Scanner(System.in);
        int maxSquareMileage = in.nextInt();

        System.out.println("\nPrinting key-value pairs");
        Set<String> keys = capitals.keySet();
        for (String key : keys)
            if (capitals.get(key).getSquareMileage() <= maxSquareMileage)
                System.out.printf("%s, %s, %d, %.2f\n", key, capitals.get(key).getName(), capitals.get(key).getPopulation(), capitals.get(key).getSquareMileage());
    }

    /**
     * Controls operation of the program
     * @param args arguments
     */
    public static void main(String[] args) throws IOException {
        StateCapitalsApp capitals = new StateCapitalsApp();
        capitals.createMap();
        capitals.printMapSize();
        capitals.printKeyValuePairs();
        capitals.limitPopulation();
        capitals.limitSquareMileage();
    }

}
