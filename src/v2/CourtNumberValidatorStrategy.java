package v2;

/**
 * Number validator for the court.
 */
public class CourtNumberValidatorStrategy implements INumberValidatorStrategy {

    /**
     * The court doesn't want any number that ends in 3.
     * @param n The number to be evaluated.
     * @return If the number does not end in 3.
     */
    @Override
    public boolean validateNumber(int n) {
        return n%10!=3;
    }
}
