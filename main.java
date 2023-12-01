import java.util.*;

public class DDA { 

    public static final String[] ACTIONS = {"⬆️", "➡️", "⬇️", "⬅️", "⏫"};
    public static final String[] RESPONSES = {"UP", "RIGHT", "DOWN", "LEFT", "JUMP"};
    public static final String STAR = "⭐";

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        Random rand = new Random();
        System.out.println("Welcome to Console Dance Dance Revolution");
        System.out.println(); 
        System.out.print("How many rounds would you like to play? "); 
        int roundNumber = console.nextInt(); 
        double[] rounds = new double[roundNumber];
        double[] totalMoves = new double[roundNumber]; 
        for (int i = 0; i < roundNumber; i++) {
            System.out.println();
            System.out.println("Round #" + (i+1) + ": ");
            showActions();
            String[] gameMoves = makeMoves(console, rand);
            totalMoves[i] = gameMoves.length; 
            double playerScore = playGame(console, gameMoves);
            rounds[i] = playerScore; 
        }
        System.out.println(); 
        endScreen(rounds, totalMoves);
    }

   //Displays the possible actions in the game for the user. 
    public static void showActions() {
        System.out.println("These are the possible actions and their correct responses:");
        for (int i = 0; i < ACTIONS.length; i++) {
            System.out.println(ACTIONS[i] + ": " + RESPONSES[i]);
        }
        System.out.println(); 
    }

    //Asks the user for how many moves they would like to play.
    //Parameters: 
    //    Scanner console - to gain user input of how many moves
    //    Random rand - to produce random action 
    //Returns:
    //    actions - returns the random actions
    public static String[] makeMoves(Scanner console, Random rand) {
        System.out.print("How many moves would you like to play? ");
        int moves = console.nextInt(); 
        String[] actions = new String[moves];
        for (int i = 0; i < moves; i++) {
            int number = rand.nextInt(ACTIONS.length); 
            actions[i] = ACTIONS[number];
        }
        return actions; 
    }

    //Checks if the user input matches expected output and attributes score.
    //Parameters: 
    //    Scanner console - for user to input intended moves
    //    String[] gameMoves - randomly generated actions
    //Returns:
    //    double score = total score of user 
    public static double playGame(Scanner console, String[] gameMoves) {
        double score = 0; 
        System.out.println(); 
        System.out.println("Let's Dance!");
        for (int i = 0; i < gameMoves.length; i++) {
            for (int j = 0; j < ACTIONS.length; j++) {
                if (gameMoves[i] == ACTIONS[j]) {
                    String expectedResponse = RESPONSES[j].toUpperCase(); 
                    System.out.print("(" + (i+1) + ") " + gameMoves[i] + ": ");
                    String userInput = console.next().toUpperCase(); 
                    if (userInput.equals(expectedResponse)) {
                        score = score + 1; 
                    } else if (userInput.indexOf(expectedResponse) != -1) {
                        score = score + 0.5; 
                    }
                }
            }
        }
        return score;
    }

    //End screen that displays final score and stars of user.
    //Parameters: 
    //    double rounds - number of rounds/scores in array
    //    double totalMoves - max score in array values
    public static void endScreen(double[] rounds, double[] totalMoves) {
        System.out.println("Woah that was groovy!");
        System.out.print("You Scored: ");
        System.out.println();
        double totalScore = 0; 
        double combinedMoves = 0; 
        for (int i = 0; i < rounds.length; i++) {
            System.out.print("Round #" + (i+1) + ": ");
            totalScore = totalScore + rounds[i]; 
            combinedMoves = combinedMoves + totalMoves[i]; 
            double percentage = rounds[i]/totalMoves[i]; 
            scoreCheck(percentage);
            System.out.println(" (" + rounds[i] + "/" + totalMoves[i] + ")");
        }
        System.out.println();
        System.out.println("Overall Score:");
        double totalPercentage = totalScore/combinedMoves; 
        scoreCheck(totalPercentage);
        System.out.println(" (" + totalScore + "/" + combinedMoves + ")");
        System.out.println(); 
        System.out.println("Thanks for playing!"); 
    }

    //Checks how many stars to display
    //Parameters: 
    //    double percentage - percentage value of correct responses
    public static void scoreCheck(double percentage) {
        if (percentage < 0.4) {
            System.out.print(STAR);
        } else if (percentage < 0.6) {
            System.out.print(STAR + STAR);
        } else if (percentage < 0.8) {
            System.out.print(STAR + STAR + STAR);
        } else if (percentage < 1) {
            System.out.print(STAR + STAR + STAR + STAR);
        } else {
            System.out.print(STAR + STAR + STAR + STAR + STAR);
        }
    }
}
