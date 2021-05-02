import java.util.Scanner;

/**
 * Reversi game
 */
public class Game {
    private PlateauDeReversi board = new PlateauDeReversi();
    private Scanner scan = new Scanner(System.in);
    private int x,y;

    public static void main(String[] args) {

        Game game = new Game();
        System.out.println("Welcome to reversy, game for 2 player");

        // Game loop
        do{
            game.playerPlay(Pion.NOIR);
            game.playerPlay(Pion.BLANC);
        }while(true);

    }

    public void playerPlay(Pion pion){
        boolean aJouer = false;

        // Si il existe au moin une position jouable
        if (board.peutJouer(pion)){
            // Tant que le joueur n'a pas jouer une position
            System.out.println("******************");
            System.out.println("turn of  " + pion.getSymbol());
            board.afficher();
            do{
                System.out.print("ligne : ");
                x = scan.nextInt();
                scan.nextLine();
                System.out.print("colonne : ");
                y = scan.nextInt();
                scan.nextLine();
                if (board.tester(pion,x,y) >= 1){
                    try{
                        board.poser(pion,x,y);
                        aJouer = true;
                    }catch (Exception e){
                        System.out.println("/!\\  incorect ! retry ! /!\\ ");
                    }
                }else{
                    System.out.println("/!\\ You can't play here ! retry /!\\");
                }
            }while(!aJouer);
        }else{
            System.out.println("/!\\ The color " + pion.getSymbol() + " doesn't have position to play /!\\");
        }
    }
}