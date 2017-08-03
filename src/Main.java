import java.util.List;

/**
 * Main class of the program.
 */
public class Main {

    /**
     * Main method of the program.
     * @param args Received arguments.
     */
    public static void main(String[] args) {
        //Instantiates a new generator.
        DoorNumberGenerator generator = new DoorNumberGenerator();

        //Load the validators.
        generator.addValidator(new SheriffNumberValidator());
        generator.addValidator(new FireChiefNumberValidator());
        generator.addValidator(new CourtNumberValidator());

        //Calculate all the possible door numbers of each validator.
        generator.calculateDoorNumbers();

        //Calculate the possible permutations
        List<List<Integer>> numbers = generator.calculatePermutations(0);

        //Remove the duplicates from the list of permutations.
        numbers = DoorNumberGenerator.removeDuplicates(numbers);

        //Print the final list of valid configurations for door numbers.
        DoorNumberGenerator.printNumbers(numbers);
    }
}
