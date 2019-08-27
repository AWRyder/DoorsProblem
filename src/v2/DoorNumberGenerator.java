package v2;

import java.util.*;


/**
 * Generates Door Numbers based on given validators.
 */
public class DoorNumberGenerator {

    //Static configuration variables.
    private final static int MIN_DOOR_NUMBER = 1;
    private final static int MAX_DOOR_NUMBER = 11;

    //List of validators for internal use.
    private List<INumberValidatorStrategy> validators;
    private PossibleNumberListsMap numbersListMap;
    private IPermutationStrategy permutationStrategy;

    /**
     * Constructor - Initializes the validator list.
     */
    public DoorNumberGenerator() {
        this.validators = new LinkedList<>();
        this.numbersListMap = new PossibleNumberListsMap();
    }

    /**
     * Calculates the valid door numbers of each validator and adds them to the validator's internal list of valid numbers.
     */
    public void calculateDoorNumbers() throws Exception {

        if ( validators == null || validators.isEmpty() ) {
            throw new Exception("Validators can't be null");
        }

        if ( permutationStrategy == null ) {
            throw new Exception("PermutationStrategy can't be null");
        }

        //v2.Main loop to check every number.
        for (int i = MIN_DOOR_NUMBER; i <= MAX_DOOR_NUMBER; i++) {
            for (INumberValidatorStrategy validator : validators) {
                if (validator.validateNumber(i)) {
                    numbersListMap.addPossibleNumber(validator,i);
                }
            }
        }

        List<List<Integer>> permutedResults = permutationStrategy.calculatePermutations(this.numbersListMap);
        printNumbers(permutedResults);
    }

    private void printNumbers(List<List<Integer>> numbers) {
        StringBuffer sb = new StringBuffer();
        numbers.forEach(numberCombination -> {
            StringJoiner joiner = new StringJoiner(",");
            numberCombination.forEach(number -> joiner.add(number.toString()));
            sb.append("{" + joiner.toString() + "}\n");
        });

        System.out.println(sb.toString());
        System.out.println("total: " + numbers.size());
    }

    /**
     * Adds a validator to the internal list.
     * @param validator Validator to add to the internal list.
     */
    public void addValidator(INumberValidatorStrategy validator) {
        this.validators.add(validator);
    }

    public IPermutationStrategy getPermutationStrategy() {
        return permutationStrategy;
    }

    public void setPermutationStrategy(IPermutationStrategy permutationStrategy) {
        this.permutationStrategy = permutationStrategy;
    }
}
