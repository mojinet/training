import java.util.*;

class Player {
        private static Case[][] plateau = initPlateau();

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // game loop
        while (true) {

            // la position ou le joueur adverse à jouer au tour précedent, si -1 : premier tour
            int opponentRow = in.nextInt();
            int opponentCol = in.nextInt();

            // Le joueur adverse joue
            if (opponentRow != -1){
                playAt(opponentCol, opponentRow, Case.ADVERSAIRE);
            }

            // retourne toute les positions valide ou il est possible de jouer
            int validActionCount = in.nextInt();
            for (int i = 0; i < validActionCount; i++) {
                int row = in.nextInt();
                int col = in.nextInt();
            }

            // Reponse
            System.out.println(prendDecision());
        }
    }

    /*
    * Renvois l'action à jouer pour le prochain tour
    */
    private static String prendDecision(){
        String response = "";
        if (plateau[0][0] == Case.VIDE){ plateau[0][0] = Case.MOI; response = "0 0";}
        else if (plateau[2][2] == Case.VIDE){ plateau[2][2] = Case.MOI; response = "2 2";}
        else if (plateau[1][1] == Case.VIDE){ plateau[1][1] = Case.MOI; response = "1 1";}

        else if (plateau[0][2] == Case.VIDE){ plateau[0][2] = Case.MOI; response = "0 2";}
        else if (plateau[1][2] == Case.VIDE){ plateau[1][2] = Case.MOI; response = "1 2";}
        else if (plateau[0][1] == Case.VIDE){ plateau[0][1] = Case.MOI; response = "0 1";}

        else if (plateau[1][0] == Case.VIDE){ plateau[1][0] = Case.MOI; response = "1 0";}
        else if (plateau[2][0] == Case.VIDE){ plateau[2][0] = Case.MOI; response = "2 0";}
        else if (plateau[2][1] == Case.VIDE){ plateau[2][1] = Case.MOI; response = "2 1";}
    
        return response;
    }

    /*
    * Initialise et retourne un plateau de jeu avec des cases vide
    */
    private static Case[][] initPlateau(){
        Case[][] plateau = new Case[3][3];

        for (int x = 0; x < 3; x++) {  
            for (int y = 0; y < 3; y++) {
                plateau[x][y] = Case.VIDE;
            }
        }

        return plateau;
    }

    /*
    * Le joueur joue en x,y
    */
    private static void playAt(int x, int y, Case state){
        plateau[x][y] = state;
    }

}

    /*
    * Enumération de l'état des cases du jeu
    */
    enum Case{
        MOI,ADVERSAIRE,VIDE;
    }
