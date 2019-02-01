package v2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RecursivePermutationStrategy implements IPermutationStrategy {

    private PossibleNumberListsMap possibleNumberListsMap;
    private List<INumberValidatorStrategy> validators;

    @Override
    public List<List<Integer>> calculatePermutations(PossibleNumberListsMap possibleNumbers) {
        this.possibleNumberListsMap = possibleNumbers;
        validators = new LinkedList<>(possibleNumbers.keySet());
        return removeDuplicates(calculatePermutations(0));
    }

    private List<List<Integer>> calculatePermutations(int currentIndex) {
        //If it is the last iteration, there's not much to do other than prepare for the recursive unrolling.
        if (currentIndex == validators.size()) {
            // Skip the items for the last container
            List<List<Integer>> permutations = new ArrayList<List<Integer>>();
            permutations.add(new ArrayList<>());
            return permutations;
        }
        //This is not the last iteration
        List<List<Integer>> permutations = new ArrayList<List<Integer>>();
        //Get the validator corresponding to the current index and it's respective valid numbers.
        INumberValidatorStrategy validator = validators. get(currentIndex);
        List<Integer> validatedNumbers = possibleNumberListsMap.get(validator);
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

    private static List<List<Integer>> removeDuplicates(List<List<Integer>> numbers){
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
}
