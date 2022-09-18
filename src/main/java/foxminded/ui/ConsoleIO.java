package foxminded.ui;

import java.util.Scanner;

public class ConsoleIO {

    private Scanner scanner;

    public ConsoleIO() {
        super();
        this.scanner = new Scanner(System.in);
    }

    public char userOptionChar() {
        return scanner.nextLine().charAt(0);
    }

    public int nextInt() {
        return scanner.nextInt();
    }

    public String nextLine() {
        return scanner.nextLine();
    }

    public void scannerClose() {
        scanner.close();
    }

}
