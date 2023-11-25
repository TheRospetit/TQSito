package models;

import java.io.ByteArrayInputStream;
import java.util.LinkedList;
import java.util.Queue;

public class Mock_ScannerClass extends ScannerClass{
    private String input;
    private Queue<String> inputsList;

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

    @Override
    public void setInput(String input) { this.input = input; }

    @Override
    public String nextLine() {
        if(inputsList != null && !inputsList.isEmpty()) {
            return inputsList.poll();
        } else if (input != null){
            return input;
        } else{
            throw new IllegalStateException("There no is more inputs for simulate nextLine");
        }
    }


}
