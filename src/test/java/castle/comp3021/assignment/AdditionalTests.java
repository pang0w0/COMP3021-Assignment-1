package castle.comp3021.assignment;

import castle.comp3021.assignment.mock.MockPlayer;
import castle.comp3021.assignment.piece.Archer;
import castle.comp3021.assignment.piece.Knight;
import castle.comp3021.assignment.player.ConsolePlayer;
import castle.comp3021.assignment.protocol.*;
import castle.comp3021.assignment.util.Compares;
import castle.comp3021.assignment.util.OptionalArcherImplementation;
import castle.comp3021.assignment.util.SampleTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Put your additional JUnit5 tests for Bonus Task 2 in this class.
 */
public class AdditionalTests {

    @Test
    @SampleTest
    public void myTest() {
        Configuration config;
        MockPlayer player1;
        MockPlayer player2;
        int size = 25;
        player1 = new MockPlayer(Color.PURPLE);
        player2 = new MockPlayer(Color.YELLOW);
        config = new Configuration(size, new Player[]{player1, player2});
        var knightP1 = new Knight[size*size];
        var knightP2 = new Knight[size*size];
        var archerP1 = new Archer[size*size];
        var archerP2 = new Archer[size*size];

        for(int i=0;i<knightP1.length;i++){
            knightP1[i] = new Knight(player1);
            knightP2[i] = new Knight(player2);
            archerP1[i] = new Archer(player1);
            archerP2[i] = new Archer(player2);
        }

        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(new Place(j,i).equals(config.getCentralPlace())){
                    continue;
                }
                config.addInitialPiece(knightP1[size*i+j], j, i);
            }
        }
        var game = new JesonMor(config);
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(new Place(j,i).equals(config.getCentralPlace())){
                    continue;
                }
                var moves = knightP1[size*i+j].getAvailableMoves(game, new Place(j, i));
                assertTrue(Compares.areContentsEqual(moves, new Move[0]));
            }
        }



        config = new Configuration(size, new Player[]{player1, player2});
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(new Place(j,i).equals(config.getCentralPlace())){
                    continue;
                }
                config.addInitialPiece(knightP2[size*i+j], j, i);
            }
        }
        game = new JesonMor(config);
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(new Place(j,i).equals(config.getCentralPlace())){
                    continue;
                }
                var moves = knightP2[size*i+j].getAvailableMoves(game, new Place(j, i));
                assertTrue(Compares.areContentsEqual(moves, new Move[0]));
            }
        }




        config = new Configuration(size, new Player[]{player1, player2});
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(new Place(j,i).equals(config.getCentralPlace())){
                    continue;
                }
                config.addInitialPiece(archerP1[size*i+j], j, i);
            }
        }
        game = new JesonMor(config);
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(new Place(j,i).equals(config.getCentralPlace())){
                    continue;
                }
                var moves = archerP1[size*i+j].getAvailableMoves(game, new Place(j, i));
                if(j==config.getCentralPlace().x()-1 && i ==config.getCentralPlace().y()){
                    assertTrue(Compares.areContentsEqual(moves,
                            new Move[]{new Move(j, i, j+1, i)}));
                }else if(j==config.getCentralPlace().x()+1 && i ==config.getCentralPlace().y()){
                    assertTrue(Compares.areContentsEqual(moves,
                            new Move[]{new Move(j, i, j-1, i)}));
                }else if(j==config.getCentralPlace().x() && i ==config.getCentralPlace().y()+1){
                    assertTrue(Compares.areContentsEqual(moves,
                            new Move[]{new Move(j, i, j, i-1)}));
                }else if(j==config.getCentralPlace().x() && i ==config.getCentralPlace().y()-1){
                    assertTrue(Compares.areContentsEqual(moves,
                            new Move[]{new Move(j, i, j, i+1)}));
                }else{
                    assertTrue(Compares.areContentsEqual(moves, new Move[0]));
                }
            }
        }
    }



    @Test
    @OptionalArcherImplementation
    @SampleTest
    public void myTestMoveAndDeadlock() {
        var player1 = new MockPlayer(Color.PURPLE);
        var player2 = new MockPlayer(Color.YELLOW);
        var config = new Configuration(3, new Player[]{player1, player2});
        config.addInitialPiece(new Knight(player1), 0, 0);
        config.addInitialPiece(new Archer(player1), 1, 0);
        config.addInitialPiece(new Knight(player1), 2, 0);
        config.addInitialPiece(new Knight(player2), 0, 2);
        config.addInitialPiece(new Archer(player2), 1, 2);
        config.addInitialPiece(new Knight(player2), 2, 2);
        var game = new JesonMor(config);
        player1.setNextMoves(new Move[]{
                new Move(1, 0, 1, 1),
                new Move(1, 1, 0, 1),
                new Move(0, 1, 0, 2),
                new Move(0, 2, 2, 2),
                new Move(2, 2, 0, 2),
                new Move(2, 0, 1, 2),
                new Move(0, 0, 2, 1),
        });
        player2.setNextMoves(new Move[]{
                new Move(0, 2, 1, 0),
                new Move(1, 2, 1, 1),
                new Move(1, 1, 1, 2),
                new Move(1, 0, 0, 2),
                new Move(1, 2, 2, 2),
                new Move(2, 2, 2, 1),
        });
        var winner = game.start();
        assertEquals(player1, winner);
        assertEquals(13, player1.getScore());
        assertEquals(10, player2.getScore());
        assertEquals(13, game.getNumMoves());
    }














    @Test
    @SampleTest
    public void myTestConsolePlayer() {
        ConsolePlayer player1 = new ConsolePlayer("P1");
        ConsolePlayer player2 = new ConsolePlayer("P2");
        var config = new Configuration(3, new Player[]{player1, player2});
        config.addInitialPiece(new Knight(player1), 0, 0);
        config.addInitialPiece(new Archer(player1), 1, 0);
        config.addInitialPiece(new Knight(player1), 2, 0);
        config.addInitialPiece(new Archer(player2), 1, 2);
        var game = new JesonMor(config);
        String s = "@#$#@$\n"+
                "1b->2c\n"+
                "b1->b1\n"+
                "a3->a2\n"+
                "a3->b1\n"+
                "a22->b23\n"+
                "a255->fdg23\n"+
                "xczvcxzq1->dsf3333\n"+
                "324xvz->fdg122\n"+
                "B1->B2\n"+
                "a1->b3\n";

        ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
        System.setIn(in);
        var winner = game.start();
        assertEquals(player1, winner);
        assertEquals(3, player1.getScore());
        assertEquals(0, player2.getScore());
        assertEquals(1, game.getNumMoves());






    }



}
