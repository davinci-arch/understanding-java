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

    private final Map<Player, Long> score;
    private Scanner scanner = new Scanner(System.in);
    private final int AMOUNT_OF_TRIES = 3;
    public RandomGuessGame() {
        score = new HashMap<>();
    }

    public void playGame(Player player, GameLevel chosenLevel) {
        var points = 0L;
        for(int i = 0; i < AMOUNT_OF_TRIES; i++) {
            var guessingNumber = generateRandomNumber(chosenLevel);
            var answer = scanner.nextLine();
            if (isAnswerValid(answer)) {
                if (Integer.parseInt(answer) == guessingNumber) {
                    points += chosenLevel.pointsForWinning;
                    break;
                } {
                    //TODO: need minor fix
                    System.out.printf("U have %d left", (AMOUNT_OF_TRIES - points));
                }
            } else {
                throw new MissMatchRequiredFormat(String.format("Your answer \"%s\" do not match the format", answer));
            }
        }
        score.put(player, points);
    }

    private boolean isAnswerValid(String answer) {
        Pattern pattern = Pattern.compile("^\\d+");
        Matcher matcher = pattern.matcher(answer);
        return matcher.find();
    }
    private int generateRandomNumber(GameLevel chosenLevel) {
        Random random = new Random();
        int upperBound = 0;
        switch (chosenLevel) {
            case EASY -> {
                upperBound = 100;
            }
            case MEDIUM -> {
                upperBound = 300;
            }
            case DIFFICULT -> {
                upperBound = 1100;
            }
            case IMPOSSIBLE -> {
                upperBound = 5000;
            }
        }
        return random.nextInt(upperBound);
    }

    public Map<Player, Long> getScoreBoard(){
        return score.entrySet().stream()
                .collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
