package v2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static v1.DoorNumberGenerator.removeDuplicates;

public class PreCalculationPermutationStrategy implements IPermutationStrategy {

    private PossibleNumberListsMap possibleNumberListsMap;
    private List<INumberValidatorStrategy> validators;
    private List<List<Integer>> results;

    @Override
    public List<List<Integer>> calculatePermutations(PossibleNumberListsMap possibleNumbers) {
        this.possibleNumberListsMap = possibleNumbers;
        this.validators = new LinkedList<>(possibleNumbers.keySet());
        this.results = new ArrayList<>();
        allCombinations();
        return removeDuplicates(results);
    }


    // Não havia portanto inventei.
    private int numberOfPositionsPossible(){
        return possibleNumberListsMap.values()
                .stream()
                .map(List::size)
                .reduce((result, positionListSize) -> result *= positionListSize )
                .get();
    }

    private void allCombinations() {
        int y = 1;
        int numb = 1;
        for (int n = 0; n < validators.size(); n++) {
            numb = numb * possibleNumberListsMap.get(validators.get(n)).size();
            for ( int m = 1; m < y; m++) {
                this.allCombinationsIncludeFunction(n, numberOfPositionsPossible()/numb, m);
            }
            y = y * possibleNumberListsMap.get(validators.get(n)).size();
        }
    }

    private void allCombinationsIncludeFunction(Integer indexOfBuildingInBuildings, Integer qwerty, Integer m) {
        int h=0;
        // y ??
        for( int n = 1; n <= m; n++) {
            for (Integer p : possibleNumberListsMap.get(validators.get(indexOfBuildingInBuildings))) { //Arrays start at 0 on real languages
                for( int i = 1; i<=qwerty; i++) {
                    ++h;
                    if ( h > this.results.size() ) {
                        this.results.add(new ArrayList<>());
                    }
                    this.results.get(h-1).add(p); //Arrays start at 0 on real languages
                }
            }
        }
    }


}
