package v2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class PossibleNumberListsMap extends HashMap<INumberValidatorStrategy, List<Integer>> {

    /**
     *
     * @param validator
     * @param number
     */
    public void addPossibleNumber(INumberValidatorStrategy validator, Integer number) {
        if (!this.containsKey(validator)){
            this.put(validator, new LinkedList<>());
        }
        this.get(validator).add(number);
    }
}
