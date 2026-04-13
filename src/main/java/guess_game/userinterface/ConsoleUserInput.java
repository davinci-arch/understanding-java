package guess_game.userinterface;

import java.util.Scanner;

public class ConsoleUserInput implements UserInput {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getLine() {
        return scanner.nextLine();

    }
}
