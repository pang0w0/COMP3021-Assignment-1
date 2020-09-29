package castle.comp3021.assignment.piece;

import castle.comp3021.assignment.protocol.Game;
import castle.comp3021.assignment.protocol.Move;
import castle.comp3021.assignment.protocol.Piece;
import castle.comp3021.assignment.protocol.Place;
import castle.comp3021.assignment.protocol.Player;

import java.util.ArrayList;

/**
 * Knight piece that moves similar to knight in chess.
 * Rules of move of Knight can be found in wikipedia (https://en.wikipedia.org/wiki/Knight_(chess)).
 *
 * @see <a href='https://en.wikipedia.org/wiki/Knight_(chess)'>Wikipedia</a>
 */
public class Knight extends Piece {
    public Knight(Player player) {
        super(player);
    }

    @Override
    public char getLabel() {
        return 'K';
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
        //  student implementation // Done
        var size = game.getConfiguration().getSize();
        int x = source.x();
        int y = source.y();
        ArrayList<Move> temp = new ArrayList<>();

        //east
        if(x - 2 >= 0){
            if(game.getPiece(x-1,y) == null) {
                if (y - 1 >= 0) {
                    if (game.getPiece(x - 2, y - 1) != null) {
                        if (!(game.getPiece(source).getPlayer().equals(game.getPiece(x - 2, y - 1).getPlayer()))) {
                            //the source piece and the destination piece is different player
                            if (game.getConfiguration().getNumMovesProtection() <= game.getNumMoves()) {
                                temp.add(new Move(source, new Place(x - 2, y - 1)));
                            }
                        }
                    } else {
                        temp.add(new Move(source, new Place(x - 2, y - 1)));
                    }
                }

                if (y + 1 < size) {
                    if (game.getPiece(x - 2, y + 1) != null) {
                        if (!(game.getPiece(source).getPlayer().equals(game.getPiece(x - 2, y + 1).getPlayer()))) {
                            //the source piece and the destination piece is different player
                            if (game.getConfiguration().getNumMovesProtection() <= game.getNumMoves()) {
                                temp.add(new Move(source, new Place(x - 2, y + 1)));
                            }
                        }
                    } else {
                        temp.add(new Move(source, new Place(x - 2, y + 1)));
                    }
                }
            }
        }

        //west
        if(x + 2 < size){
            if(game.getPiece(x+1,y) == null) {
                if (y - 1 >= 0) {
                    if (game.getPiece(x + 2, y - 1) != null) {
                        if (!(game.getPiece(source).getPlayer().equals(game.getPiece(x + 2, y - 1).getPlayer()))) {
                            //the source piece and the destination piece is different player
                            if (game.getConfiguration().getNumMovesProtection() <= game.getNumMoves()) {
                                temp.add(new Move(source, new Place(x + 2, y - 1)));
                            }
                        }
                    } else {
                        temp.add(new Move(source, new Place(x + 2, y - 1)));
                    }
                }
                if (y + 1 < size) {
                    if (game.getPiece(x + 2, y + 1) != null) {
                        if (!(game.getPiece(source).getPlayer().equals(game.getPiece(x + 2, y + 1).getPlayer()))) {
                            //the source piece and the destination piece is different player
                            if (game.getConfiguration().getNumMovesProtection() <= game.getNumMoves()) {
                                temp.add(new Move(source, new Place(x + 2, y + 1)));
                            }
                        }
                    } else {
                        temp.add(new Move(source, new Place(x + 2, y + 1)));
                    }
                }
            }
        }

        //south
        if(y - 2 >= 0){
            if(game.getPiece(x,y-1) == null) {
                if (x - 1 >= 0) {
                    if (game.getPiece(x - 1, y - 2) != null) {
                        if (!(game.getPiece(source).getPlayer().equals(game.getPiece(x - 1, y - 2).getPlayer()))) {
                            //the source piece and the destination piece is different player
                            if (game.getConfiguration().getNumMovesProtection() <= game.getNumMoves()) {
                                temp.add(new Move(source, new Place(x - 1, y - 2)));
                            }
                        }
                    } else {
                        temp.add(new Move(source, new Place(x - 1, y - 2)));
                    }
                }
                if (x + 1 < size) {
                    if (game.getPiece(x + 1, y - 2) != null) {
                        if (!(game.getPiece(source).getPlayer().equals(game.getPiece(x + 1, y - 2).getPlayer()))) {
                            //the source piece and the destination piece is different player
                            if (game.getConfiguration().getNumMovesProtection() <= game.getNumMoves()) {
                                temp.add(new Move(source, new Place(x + 1, y - 2)));
                            }
                        }
                    } else {
                        temp.add(new Move(source, new Place(x + 1, y - 2)));
                    }
                }
            }
        }

        //nouth
        if(y + 2 < size){
            if(game.getPiece(x,y+1) == null) {
                if (x - 1 >= 0) {
                    if (game.getPiece(x - 1, y + 2) != null) {
                        if (!(game.getPiece(source).getPlayer().equals(game.getPiece(x - 1, y + 2).getPlayer()))) {
                            //the source piece and the destination piece is different player
                            if (game.getConfiguration().getNumMovesProtection() <= game.getNumMoves()) {
                                temp.add(new Move(source, new Place(x - 1, y + 2)));
                            }
                        }
                    } else {
                        temp.add(new Move(source, new Place(x - 1, y + 2)));
                    }
                }
                if (x + 1 < size) {
                    if (game.getPiece(x + 1, y + 2) != null) {
                        if (!(game.getPiece(source).getPlayer().equals(game.getPiece(x + 1, y + 2).getPlayer()))) {
                            //the source piece and the destination piece is different player
                            if (game.getConfiguration().getNumMovesProtection() <= game.getNumMoves()) {
                                temp.add(new Move(source, new Place(x + 1, y + 2)));
                            }
                        }
                    } else {
                        temp.add(new Move(source, new Place(x + 1, y + 2)));
                    }
                }
            }
        }

        Move[] m = new Move[temp.size()];
        m = temp.toArray(m);

        return m;
    }
}
