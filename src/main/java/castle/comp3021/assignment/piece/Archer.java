package castle.comp3021.assignment.piece;

import castle.comp3021.assignment.protocol.Game;
import castle.comp3021.assignment.protocol.Move;
import castle.comp3021.assignment.protocol.Piece;
import castle.comp3021.assignment.protocol.Place;
import castle.comp3021.assignment.protocol.Player;

import java.util.ArrayList;

/**
 * Archer piece that moves similar to cannon in chinese chess.
 * Rules of move of Archer can be found in wikipedia (https://en.wikipedia.org/wiki/Xiangqi#Cannon).
 * <p>
 * <strong>Attention: If you want to implement Archer as the bonus task, you should remove "{@code throw new
 * UnsupportedOperationException();}" in the constructor of this class.</strong>
 *
 * @see <a href='https://en.wikipedia.org/wiki/Xiangqi#Cannon'>Wikipedia</a>
 */
public class Archer extends Piece {
    public Archer(Player player) {
        super(player);
        //throw new UnsupportedOperationException(); // remove this line if you plan to implement Archer
    }

    @Override
    public char getLabel() {
        return 'A';
    }

    /**
     * Returns an array of moves that are valid given the current place of the piece.
     * Given the {@link Game} object and the {@link Place} that current knight piece locates, this method should
     * return ALL VALID {@link Move}s according to the current {@link Place} of this knight piece.
     * All the returned {@link Move} should have source equal to the source parameter.
     * <p>
     * Hint: you should consider corner cases when the {@link Move} is not valid on the gameboard.
     * Several tests are provided and your implementation should pass them.
     * <p>
     * <strong>Attention: Student should make sure all {@link Move}s returned are valid.</strong>
     *
     * @param game   the game object
     * @param source the current place of the piece
     * @return an array of available moves
     */
    @Override
    public Move[] getAvailableMoves(Game game, Place source) {
        // TODO student implementation
        var configuration = game.getConfiguration();
        var size = configuration.getSize();
        var beforeX = source.x();
        var beforeY = source.y();
        ArrayList<Move> temp = new ArrayList<>();

        //north
        boolean jumped = false;
        for(int i=beforeY+1;i<size && !jumped;i++){
            if(game.getPiece(beforeX, i) == null){
                temp.add(new Move(source, new Place(beforeX, i)));
            }
            else{
                jumped = true;
                for(int j=i+1;j<size;j++){
                    if(game.getPiece(beforeX, j) != null){
                        if((!game.getPiece(beforeX, j).getPlayer().equals(game.getCurrentPlayer())) &&
                                configuration.getNumMovesProtection() <= game.getNumMoves()){//if it is enemy and not protect
                            temp.add(new Move(source, new Place(beforeX, j)));
                        }
                        break;//break the j for loop
                    }
                }
            }
        }

        //south
        jumped = false;
        for(int i=beforeY-1;i>=0 && !jumped;i--){
            if(game.getPiece(beforeX, i) == null){
                temp.add(new Move(source, new Place(beforeX, i)));
            }
            else{
                jumped = true;
                for(int j=i-1;j>=0;j--){
                    if(game.getPiece(beforeX, j) != null){
                        if((!game.getPiece(beforeX, j).getPlayer().equals(game.getCurrentPlayer())) &&
                                configuration.getNumMovesProtection() <= game.getNumMoves()){//if it is enemy and not protect
                            temp.add(new Move(source, new Place(beforeX, j)));
                        }
                        break;//break the j for loop
                    }
                }
            }
        }

        //west
        jumped = false;
        for(int i=beforeX+1;i<size && !jumped;i++){
            if(game.getPiece(i, beforeY) == null){
                temp.add(new Move(source, new Place(i, beforeY)));
            }
            else{
                jumped = true;
                for(int j=i+1;j<size;j++){
                    if(game.getPiece(j, beforeY) != null){
                        if((!game.getPiece(j, beforeY).getPlayer().equals(game.getCurrentPlayer())) &&
                                configuration.getNumMovesProtection() <= game.getNumMoves()){//if it is enemy and not protect
                            temp.add(new Move(source, new Place(j, beforeY)));
                        }
                        break;//break the j for loop
                    }
                }
            }
        }

        //east
        jumped = false;
        for(int i=beforeX-1;i>=0 && !jumped;i--){
            if(game.getPiece(i, beforeY) == null){
                temp.add(new Move(source, new Place(i, beforeY)));
            }
            else{
                jumped = true;
                for(int j=i-1;j>=0;j--){
                    if(game.getPiece(j, beforeY) != null){
                        if((!game.getPiece(j, beforeY).getPlayer().equals(game.getCurrentPlayer())) &&
                                configuration.getNumMovesProtection() <= game.getNumMoves()){//if it is enemy and not protect
                            temp.add(new Move(source, new Place(j, beforeY)));
                        }
                        break;//break the j for loop
                    }
                }
            }
        }

        Move[] m = new Move[temp.size()];
        m = temp.toArray(m);

        return m;
    }
}
