package v1;

import java.util.LinkedList;
import java.util.List;

/**
 * Abstract class of a validator. Defines the requirements for a number validator.
 */
public abstract class ANumberValidator {

    //Internal list of validated numbers.
    private List<Integer> validatedNumbers;

    /**
     * Constructor that initializes the list of validated numbers.
     */
    public ANumberValidator(){
        validatedNumbers = new LinkedList<Integer>();
    }

    /**
     * Abstract method of the number validator rule.
     * @param n The number to be validated.
     * @return If the number was valid or not.
     */
    public abstract boolean validateNumber(int n);

    /**
     * Adds a number to the list of validated numbers.
     * @param number The number to be added to the list of valid numbers.
     */
    public void addValidatedNumber(Integer number){
        this.validatedNumbers.add(number);
    }

    // Getters and Setters

    /**
     * Gets the list of valid numbers.
     * @return The list of valid numbers.
     */
    public List<Integer> getValidatedNumbers() {
        return validatedNumbers;
    }

}
