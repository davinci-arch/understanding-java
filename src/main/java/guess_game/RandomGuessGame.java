package guess_game;

import guess_game.exceptions.MissMatchRequiredFormat;

import java.util.*;
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
        var hintMaxCount = 1;
        var hintCounts = 0;
        var requiredPoints = chosenLevel.pointsForWinning;
        for(int i = 0; i < AMOUNT_OF_TRIES; i++) {
            var guessingNumber = generateRandomNumber(chosenLevel);
            var answer = scanner.nextLine();
            var hintRequiernmentAnswer = "";

            if (isAnswerValid(answer)) {
                if (Integer.parseInt(answer) == guessingNumber) {
                    points += chosenLevel.pointsForWinning;
                    break;
                } {
                    System.out.printf("U have %d attempts left", (AMOUNT_OF_TRIES - (i + 1)));
                    System.out.println("Do you require a hint? y/n");
                    hintRequiernmentAnswer = scanner.nextLine();
                    if (hintRequiernmentAnswer.trim().equalsIgnoreCase("y") && hintCounts < hintMaxCount) {
                        System.out.println("Open first number? y/n");
                        hintRequiernmentAnswer = scanner.nextLine();
                        if (hintRequiernmentAnswer.trim().equalsIgnoreCase("y")) {
                            var currentAmountOfPoints = player.getGlobalScore();
                            if (currentAmountOfPoints < requiredPoints) {
                                System.out.println("Sorry, but u don't have enough points");
                            } else {
                                provideComplexHint(guessingNumber);
                                player.setGlobalScore(currentAmountOfPoints - requiredPoints);
                            }
                        } else {
                            provideSimpleHint(guessingNumber, answer);
                        }
                        hintCounts++;
                    }
                    System.out.println(hintCounts);
                }
            } else {
                throw new MissMatchRequiredFormat(String.format("Your answer \"%s\" do not match the format", answer));
            }
        }
        score.put(player, points);
    }

    private void provideComplexHint(int guessingNumber) {
        String modifiedNumber = String.valueOf(guessingNumber).substring(0, 1);
        String replacer = "*".repeat(String.valueOf(guessingNumber).length() - 1);
        System.out.println("Guessing number: " + modifiedNumber + replacer);
    }

    private void provideSimpleHint(int guessingNumber, String answer) {
        String hint = Integer.parseInt(answer) > guessingNumber ?
                "ur answer is above targeted" : "ur answer is below targeted";
        System.out.println(hint);
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
