package v2;

/**
 * Abstract class of a validator. Defines the requirements for a number validator.
 */
public interface INumberValidatorStrategy {

    //Internal list of validated numbers.

    /**
     * Abstract method of the number validator rule.
     * @param n The number to be validated.
     * @return If the number was valid or not.
     */
    boolean validateNumber(int n);

}
