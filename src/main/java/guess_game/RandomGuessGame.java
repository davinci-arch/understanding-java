package guess_game;

import guess_game.exceptions.MissMatchRequiredFormat;
import guess_game.service.ResultHandling;
import guess_game.userinterface.UserInput;
import guess_game.userinterface.UserOutput;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RandomGuessGame {

    private final int AMOUNT_OF_TRIES = 3;
    private ResultHandling resultHandling = new ResultHandling();
    private final UserInput userInput;
    private final UserOutput userOutput;

    public RandomGuessGame(UserInput userInput, UserOutput userOutput) {
        this.userInput = userInput;
        this.userOutput = userOutput;
    }

    public void playGame(Player player, GameLevel chosenLevel) {
        var points = 0L;
        var hintMaxCount = 1;
        var hintCounts = 0;
        var requiredPoints = chosenLevel.pointsForWinning;
        for(int i = 0; i < AMOUNT_OF_TRIES; i++) {
            var guessingNumber = generateRandomNumber(chosenLevel);
            var answer = userInput.getLine();
            var hintRequiernmentAnswer = "";

            if (isAnswerValid(answer, chosenLevel)) {
                if (Integer.parseInt(answer) == guessingNumber) {
                    points += chosenLevel.pointsForWinning;
                    break;
                } {
                    userOutput.printLine(String.format("U have %d attempts left", (AMOUNT_OF_TRIES - (i + 1))));
                    userOutput.printLine("Do you require a hint? y/n");
                    hintRequiernmentAnswer = userInput.getLine();
                    if ("y".equals(hintRequiernmentAnswer.trim()) && hintCounts < hintMaxCount) {
                        userOutput.printLine("Open first number? y/n");
                        hintRequiernmentAnswer = userInput.getLine();
                        if ("y".equals(hintRequiernmentAnswer.trim())) {
                            var currentAmountOfPoints = player.getGlobalScore();
                            if (currentAmountOfPoints < requiredPoints) {
                                userOutput.printLine("Sorry, but u don't have enough points");
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
        resultHandling.savePlayerScore(player, points);
    }

    private void provideComplexHint(int guessingNumber) {
        String modifiedNumber = String.valueOf(guessingNumber).substring(0, 1);
        String replacer = "*".repeat(String.valueOf(guessingNumber).length() - 1);
        userOutput.printLine("Guessing number: " + modifiedNumber + replacer);
    }

    private void provideSimpleHint(int guessingNumber, String answer) {
        String hint = Integer.parseInt(answer) > guessingNumber ?
                "ur answer is above targeted" : "ur answer is below targeted";
        userOutput.printLine(hint);
    }

    private boolean isAnswerValid(String answer, GameLevel chosenLevel) {
        Pattern pattern = Pattern.compile("^\\d+");
        Matcher matcher = pattern.matcher(answer);
        boolean isMatch = matcher.matches();
        boolean isValid = false;
        int parsedAnswer = Integer.parseInt(answer);

        if (parsedAnswer > 0 && parsedAnswer <= chosenLevel.upperBound) {
            isValid = true;
        }

        return isMatch && isValid;
    }
    private int generateRandomNumber(GameLevel chosenLevel) {
        Random random = new Random();
        return random.nextInt(chosenLevel.upperBound);
    }

}
