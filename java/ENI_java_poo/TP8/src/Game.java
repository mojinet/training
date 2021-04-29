/**
 * Reversi game
 */
public class Game {
    public static void main(String[] args) {
        PlateauDeReversi game = new PlateauDeReversi();
        System.out.println("Welcome to reversy, game for 2 player");
        game.afficher();

        //test
        game.poser(Pion.NOIR,6,6);
    }
}
