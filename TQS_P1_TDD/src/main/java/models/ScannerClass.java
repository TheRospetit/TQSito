package models;
import java.io.InputStream;
import java.util.Scanner;
public class ScannerClass {
    private final Scanner scanner;

    public ScannerClass(InputStream inputStream){
        this.scanner = new Scanner(inputStream);
    }

    public ScannerClass(Scanner scanner){
        this.scanner = scanner;
    }

    public String nextLine () { return scanner.nextLine(); }

    public int nextInt() { return scanner.nextInt(); }

    public boolean hasNextInt() { return scanner.hasNextInt(); }

    public String  next() { return scanner.next(); }

}
