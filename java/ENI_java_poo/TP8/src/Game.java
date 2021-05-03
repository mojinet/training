import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Jeu du reversi, pour 2 joueur
 */
public class Game {
    private PlateauDeReversi board = new PlateauDeReversi();
    private static Game game = new Game();
    private Scanner scan = new Scanner(System.in);
    private int x,y;

    public static void main(String[] args) {
        game.start();
    }

    /**
     * Boucle de jeu, tant qu'au moins un des joueur à une position à jouer on continue la partie
     */
    public void start(){
        boolean checkGame = true;

        System.out.println("Welcome to reversy, game for 2 player");
        do{
            if ( (board.peutJouer(Pion.NOIR)) || (board.peutJouer(Pion.BLANC) ) ){
                game.playerPlay(Pion.NOIR);
                game.playerPlay(Pion.BLANC);
            }else{
                checkGame = false;
            }
        }while(checkGame);
    }

    /**
     * On demande au joueur (param pion) de choisir une position à jouer
     */
    public void playerPlay(Pion pion){
        boolean aJouer = false;

        // Si le joueur à au moins une position jouable
        if (board.peutJouer(pion)){
            board.afficher();
            System.out.println("turn of  " + pion.getSymbol());

            // L'utilisateur saisie la ligne/colonne qu'il veut jouer
            do{
                try{
                    System.out.print("ligne : ");
                    x = scan.nextInt();
                    scan.nextLine();
                    System.out.print("colonne : ");
                    y = scan.nextInt();
                    scan.nextLine();
                }catch (InputMismatchException e){
                    System.out.println("/!\\  incorect ! retry ! /!\\ ");
                    scan.nextLine();
                }

                // On test la position que l'utilisateur veut jouer
                if (board.tester(pion,x,y) >= 1){
                    board.poser(pion,x,y);
                    aJouer = true;
                }else{
                    System.out.println("/!\\ You can't play here ! retry /!\\");
                }
            }while(!aJouer);
        }else{
            System.out.println("/!\\ The color " + pion.getSymbol() + " doesn't have position to play /!\\");
        }
    }
}