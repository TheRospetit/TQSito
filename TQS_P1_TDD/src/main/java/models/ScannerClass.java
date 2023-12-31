package models;
import java.io.InputStream;
import java.util.Scanner;

// Manages and adapts the use of a Mock (Mock_ScannerClass) class to be able to do some tests.
public class ScannerClass {
    private final Scanner scanner;

    // Constructor depending on if its of a client or testing
    public ScannerClass(InputStream inputStream){
        this.scanner = new Scanner(inputStream);
    }
    public ScannerClass(Scanner scanner){
        this.scanner = scanner;
    }

    // Other methods
    public String nextLine () { return scanner.nextLine(); }
    public int nextInt() { return scanner.nextInt(); }
    public boolean hasNextInt() { return scanner.hasNextInt(); }
    public String  next() { return scanner.next(); }


    // Some methods which will be modified by Mock_ScannerClass
    public void setInputsList(String ... inputs){

    }
}
