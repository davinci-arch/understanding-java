package guess_game.userinterface;

import java.util.Scanner;

public class ConsoleUserOutput implements UserOutput {
    @Override
    public void printLine(String line) {
        System.out.println(line);

    }
}
