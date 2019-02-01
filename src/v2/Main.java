package v2;

/**
 * v1.Main class of the program.
 */
public class Main {

    /**
     * v1.Main method of the program.
     * @param args Received arguments.
     */
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        //Instantiates a new generator.
        DoorNumberGenerator generator = new DoorNumberGenerator();

        //Load the validators.
        generator.addValidator(new SheriffNumberValidatorStrategy());
        generator.addValidator(new FireChiefNumberValidatorStrategy());
        generator.addValidator(new CourtNumberValidatorStrategy());

        generator.setPermutationStrategy(new RecursivePermutationStrategy());
        //Calculate all the possible door numbers of each validator.
        try {
            generator.calculateDoorNumbers();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        long endTime = System.nanoTime();
        System.out.println("Exec time: "+(endTime - startTime)/1000000 + " ms.");
    }
}
