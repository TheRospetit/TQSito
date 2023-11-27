package models;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Objects;

public class TestScannerClass {
  @Test
  public void testNextLine() {
    String input = "Hello, World!";
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    ScannerClass scannerClass = new ScannerClass(inputStream);

    String result = scannerClass.nextLine();
    assert(Objects.equals(result, "Hello, World!")) : "The input does not match the receive value";
  }

  @Test
  public void testNextInt() {
    String input = "42";
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    ScannerClass scannerClass = new ScannerClass(inputStream);


    int result = scannerClass.nextInt();
    assert(result == 42) : "The input does not match the receive integer value" + input;

  }

  @Test
  public void testHasNextInt_True() {
    String input = "42";
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    ScannerClass scannerClass = new ScannerClass(inputStream);

    boolean testResult = scannerClass.hasNextInt();
    assert(testResult) : "Input values does not match: " + input;
  }

  @Test
  public void testHasNextInt_False() {
    String input = "Not an integer";
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    ScannerClass scannerClass = new ScannerClass(inputStream);

    boolean testResult = scannerClass.hasNextInt();
    assert(!testResult) : "The next value should not be an integer";
  }

  @Test
  public void testNext() {
    String input = "Hello, World!";
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    ScannerClass scannerClass = new ScannerClass(inputStream);
    String result = scannerClass.next();

    assert("Hello,".equals(result));
  }

  @Test(expected = NoSuchElementException.class)
  public void testNextInt_Exception() {
    String input = "Not an integer";
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    ScannerClass scannerClass = new ScannerClass(inputStream);

    scannerClass.nextInt(); // This should throw NoSuchElementException
  }

  @Test
  public void testSetInput() {
    String input = "Hello, World!";
    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
    ScannerClass scannerClass = new ScannerClass(inputStream);

    scannerClass.setInput(input);
    scannerClass.setInputsList(input, input);
  }


}
