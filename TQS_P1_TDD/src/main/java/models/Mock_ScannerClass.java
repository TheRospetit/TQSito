package models;
import java.io.ByteArrayInputStream;
import java.util.LinkedList;
import java.util.Queue;

// This class is used in order to control when a test is required
// and needs to simulate a series of inputs.
// Is inheritance of ScannerClass
public class Mock_ScannerClass extends ScannerClass{
    private String input;
    private Queue<String> inputsList;

    // Constructors depending on if it just an element or more
    public Mock_ScannerClass(String input) {
        super(new ByteArrayInputStream(input.getBytes()));
        this.input = input;
        this.inputsList = new LinkedList<>();
    }
    public Mock_ScannerClass(String ... inputs) {
        super(new ByteArrayInputStream("".getBytes()) );
        this.inputsList = new LinkedList<>();
        for(String input : inputs){
            inputsList.add(input);
        }
    }

    // Other methods
    @Override
    public void setInputsList(String ... inputs) {
        this.inputsList = new LinkedList<>();
        for(String input : inputs){
            inputsList.add(input);
        }
    }
    @Override
    public String nextLine() {
        // Verify that there is elements inside the queue and is not null
        if(inputsList != null && !inputsList.isEmpty()) {
            return inputsList.poll();
        } else if (input != null){
            return input;
        } else{
            throw new IllegalStateException("There no is more inputs for simulate nextLine");
        }
    }
}
