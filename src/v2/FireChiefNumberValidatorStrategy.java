package v2;

/**
 * Number validator for the Fire Chief.
 */
public class FireChiefNumberValidatorStrategy implements INumberValidatorStrategy {

    /**
     * The fire chief only wants prime numbers.
     * @param n The number to be evaluated.
     * @return If the number is prime.
     */
    @Override
    public boolean validateNumber(int n) {
        for(int i=2; i<n; i++){
            if ( n%i ==0) return false;
        }
        return true;
    }
}
