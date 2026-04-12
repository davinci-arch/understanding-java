package guess_game;

import guess_game.exceptions.MissMatchRequiredFormat;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RandomGuessGame {

    private Map<Player, Long> score;
    private Scanner scanner = new Scanner(System.in);
    private final int AMOUNT_OF_TRIES = 3;
    private final int SCORE_POINT_FOR_WIN = 100;
    public RandomGuessGame() {
        score = new HashMap<>();
    }

    public void playGame(Player player) {
        var points = 0L;
        for(int i = 0; i < AMOUNT_OF_TRIES; i++) {
            var guessingNumber = generateRandomNumber();
            var answer = scanner.nextLine();
            if (isAnswerValid(answer)) {
                if (Integer.parseInt(answer) == guessingNumber) {
                    points += SCORE_POINT_FOR_WIN;
                    break;
                } {
                    System.out.printf("U have %d left", (AMOUNT_OF_TRIES - points));
                }
            } else {
                throw new MissMatchRequiredFormat(String.format("Your answer \"%s\" do not match the format", answer));
            }
        }
        score.put(player, points);
    }

    //TODO: change pattern to specific game level
    private boolean isAnswerValid(String answer) {
        Pattern pattern = Pattern.compile("^\\d+");
        Matcher matcher = pattern.matcher(answer);
        return matcher.find();
    }
    //TODO: takes in parameter level
    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(100);
    }

    public Map<Player, Long> getScoreBoard(){
        return score.entrySet().stream()
                .collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
