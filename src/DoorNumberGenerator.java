import java.util.*;


/**
 * Generates Door Numbers based on given validators.
 */
public class DoorNumberGenerator {

    //Static configuration variables.
    private final static int MIN_DOOR_NUMBER = 1;
    private final static int MAX_DOOR_NUMBER = 99;

    //List of validators for internal use.
    private List<ANumberValidator> validators;

    /**
     * Constructor - Initializes the validator list.
     */
    public DoorNumberGenerator() {
        this.validators = new LinkedList<ANumberValidator>();
    }

    /**
     * Calculates the valid door numbers of each validator and adds them to the validator's internal list of valid numbers.
     */
    public void calculateDoorNumbers() {
        //Main loop to check every number.
        for (int i = MIN_DOOR_NUMBER; i <= MAX_DOOR_NUMBER; i++) {
            for (ANumberValidator validator : validators) {
                if (validator.validateNumber(i)) {
                    validator.addValidatedNumber(i);
                }
            }
        }
    }

    /**
     * Calculates the valid permutations between all the valid numbers of all the validators.
     * @param currentIndex Current index of depth of the permutation calculator. (0 -> number of validators)
     * @return A List of lists of numbers. Each sublist containing a possible permutation of the given valid numbers.
     */
    public List<List<Integer>> calculatePermutations(int currentIndex) {
        //If it is the last iteration, there's not much to do other than prepare for the recursive unrolling.
        if (currentIndex == validators.size()) {
            // Skip the items for the last container
            List<List<Integer>> permutations = new ArrayList<List<Integer>>();
            permutations.add(new ArrayList<Integer>());
            return permutations;
        }
        //This is not the last iteration
        List<List<Integer>> permutations = new ArrayList<List<Integer>>();
        //Get the validator corresponding to the current index and it's respective valid numbers.
        ANumberValidator validator = validators.get(currentIndex);
        List<Integer> validatedNumbers = validator.getValidatedNumbers();
        // Call the method recursively, this time for the next validator.
        List<List<Integer>> suffixList = calculatePermutations(currentIndex + 1);

        //After the unrolling of the recursive call, we will attempt to permute
        int size = validatedNumbers.size();

        //And finally we combine the valid numbers of the current validator with resulting set of all the previous ones.
        for (int ii = 0; ii < size; ii++) {
            Integer number = validatedNumbers.get(ii);
            if (suffixList != null) {
                for (List<Integer> suffix : suffixList) {
                    List<Integer> nextCombination = new ArrayList<Integer>();
                    nextCombination.add(number);
                    nextCombination.addAll(suffix);
                    permutations.add(nextCombination);
                }
            }
        }

        return permutations;
    }

    /**
     * Removes any permutation that has a repeated door number as to get rid of invalid permutations.
     * @param numbers The list of calculated permutations.
     * @return A list of valid permutations.
     */
    public static List<List<Integer>> removeDuplicates(List<List<Integer>> numbers){
        List<List<Integer>> cleanedList = new LinkedList<List<Integer>>();

        //Iterates each of the possible combinations and checks their internal numbers. If any of the number is
        //repeated, then continue to the next outer loop without adding it to the list of valid permutations.
        doorNumbersLoop:
        for( List<Integer> doorNumbers : numbers){
            List<Integer> existingNumbers = new LinkedList<>();
            for( Integer doorNumber : doorNumbers) {
                if ( existingNumbers.contains(doorNumber) ){
                    continue doorNumbersLoop;
                } else {
                    existingNumbers.add(doorNumber);
                }
            }
            cleanedList.add(doorNumbers);
        }

        return cleanedList;
    }


    /**
     * Prints a list of possible permutations of door numbers.
     * @param numbers List of possible permutations to print.
     */
    public static void printNumbers(List<List<Integer>> numbers) {
        StringBuffer buffer = new StringBuffer();

        //Iterates the list of permutations.
        for (int i = 0; i < numbers.size(); i++) {
            buffer.append("{");
            //Due to the slow nature of this process, the list of door numbers of each list of possibilities is added
            //to a string builder and iterated using an iterator.
            Iterator<Integer> iter = numbers.get(i).iterator();
            while( iter.hasNext()){
                buffer.append(iter.next());
                if(iter.hasNext()){
                    buffer.append(",");
                }
            }
            buffer.append("}\n");
            //Every 10'000, print the current buffer and reinitialize it to avoid.
            if ( i%10000==0){
                System.out.print(buffer.toString());
                buffer = new StringBuffer();
            }
        }
        //Prints the final batch of combinations and the total number of them.
        System.out.print(buffer.toString());
        System.out.println("total: " + numbers.size());
    }

    /**
     * Adds a validator to the internal list.
     * @param validator Validator to add to the internal list.
     */
    public void addValidator(ANumberValidator validator) {
        this.validators.add(validator);
    }

}
