package castle.comp3021.assignment.player;

import castle.comp3021.assignment.protocol.*;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

/**
 * The player that makes move according to user input from console.
 */
public class ConsolePlayer extends Player {
    public ConsolePlayer(String name, Color color) {
        super(name, color);
    }

    public ConsolePlayer(String name) {
        this(name, Color.GREEN);
    }

    /**
     * Choose a move from available moves.
     * This method will be called by {@link Game} object to get the move that the player wants to make when it is the
     * player's turn.
     * <p>
     * {@link ConsolePlayer} returns a move according to user's input in the console.
     * The console input format should conform the format described in the assignment description.
     * (e.g. {@literal a1->b3} means move the {@link Piece} at {@link Place}(x=0,y=0) to {@link Place}(x=1,y=2))
     * Note that in the {@link Game}.board, the index starts from 0 in both x and y dimension, while in the console
     * display, x dimension index starts from 'a' and y dimension index starts from 1.
     * <p>
     * Hint: be sure to handle invalid input to avoid invalid {@link Move}s.
     * <p>
     * <strong>Attention: Student should make sure the {@link Move} returned is valid.</strong>
     * <p>
     * <strong>Attention: {@link Place} object uses integer as index of x and y-axis, both starting from 0 to
     * facilitate programming.
     * This is VERY different from the coordinate used in console display.</strong>
     *
     * @param game           the current game object
     * @param availableMoves available moves for this player to choose from.
     * @return the chosen move
     */
    @Override
    public @NotNull Move nextMove(Game game, Move[] availableMoves) {
        //  student implementation
        int size = game.getConfiguration().getSize();
        Scanner sc = new Scanner(System.in);
        int sourceX, sourceY, destX, destY;
        while(true) {
            String temp = sc.nextLine();
            var input = temp.split("->");
            if(input.length != 2){
                System.out.println("invalid input");
                continue;
            }

            if(input[0].length() < 2 || input[0].length() > 3 ||
                    input[1].length() < 2 || input[1].length() > 3){
                System.out.println("invalid input");
                continue;
            }

            sourceX = input[0].charAt(0) - 'a';
            try {
                sourceY = Integer.parseInt(input[0].substring(1)) - 1;
            } catch (NumberFormatException e){
                System.out.println("invalid input");
                continue;
            }

            destX = input[1].charAt(0) - 'a';
            try{
                destY = Integer.parseInt(input[1].substring(1)) - 1;
            } catch (NumberFormatException e){
                System.out.println("invalid input");
                continue;
            }


            if(sourceX >= size || sourceX < 0 || sourceY >= size || sourceY < 0 ||
                    destX >= size || destX < 0 || destY >= size || destY < 0){
                System.out.println("invalid input");
                continue;
            }

            var m = new Move(sourceX, sourceY, destX, destY);
            for(var x : availableMoves){
                if(x.equals(m)){
                    return m;
                }
            }

            System.out.println("invalid input");
        }
    }
}
