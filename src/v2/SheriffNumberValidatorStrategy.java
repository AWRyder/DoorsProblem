package v2;

/**
 * Number validator for the Sheriff.
 */
public class SheriffNumberValidatorStrategy implements INumberValidatorStrategy {

    /**
     * The sheriff doesn't want any odd number. Therefore we will validate any even number.
     * @param n The number to be evaluated.
     * @return If the number is even.
     */
    @Override
    public boolean validateNumber(int n) {
        return n%2==0;
    }
}
