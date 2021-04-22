import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

	public static void main(String args[]) {              
		Scanner in = new Scanner(System.in);
		Resolve r = new Resolve();                        
		int opponentRow;
		int opponentCol;

		// ************* game loop *****************//
		while (true) { 
			opponentRow = in.nextInt();                
			opponentCol = in.nextInt();              
			int validActionCount = in.nextInt();           
			for (int i = 0; i < validActionCount; i++) {   
				int row = in.nextInt();                     
				int col = in.nextInt(); 

				// permet de connaitre la grille disponible pour jouer                
				r.getTrueCases(row, col);
			}  
			System.out.println( r.getSolved(opponentRow,opponentCol) ); 
		}
	}
}    

class Resolve {

	// ========================================================================================//
	//                              		 CHAMPS
	// ========================================================================================//

	/*
	 * repr�sente les cases du jeu, compos� elle meme de cases
	 *
	 * [8][] represente la case
	 * [][0] represente le colonne
	 * [][1] represente le row
	 * [][2] represente le status d'ocupation de la case     
	 */
	// grille �talon
	private int cases[][] = new int[9][3];

	// nos 9 cases g�n�ral
	private int cases1[][] = new int[9][3];
	private int cases2[][] = new int[9][3];
	private int cases3[][] = new int[9][3];
	private int cases4[][] = new int[9][3];
	private int cases5[][] = new int[9][3];
	private int cases6[][] = new int[9][3];
	private int cases7[][] = new int[9][3];
	private int cases8[][] = new int[9][3];
	private int cases9[][] = new int[9][3];

	// ref�rence vers la case actuelement jouer
	private int[][] casesActuel;

	//repr�sentation global de la grille 
	private int casesGlobal[] = new int[9];
	private int casesGlobalProb[] = new int[9];

	//constante des cases GLOBAL/LOCAL et valeur
	private final int VIDE        = 0;
	private final int MOI         = 1;
	private final int ADVERSAIRE  = 2; 
	private final int COL         = 0;
	private final int ROW         = 1;
	private final int JOUEUR      = 2;
	private final int GLOBAL      = 0;
	private final int LOCAL       = 1;
	private final int ISEMPTY     = -1;

	//donn�es utilitaire
	int indexActuel;                // int index GLOBAL actuel	
	int sC_Global[]; 				// int[] (PLAYER) -> qui � remporter case GLOBAL
	int sC_Local[];					// int[] (PLAYER) -> qui � jouer la case LOCAL
	int cP_Moi[]; 					// int[] (>0 si vrai) -> la case i peut me faire gagner le GLOBAL
	int cP_Adv[]; 					// int[] (>0 si vrai) -> la case i peut faire gagner le GLOBAL a l'adversaire
	int sPG_Moi[]; 					// int[] (>0 si vrai) -> je peux remporter la partie avec un coup de plus
	int sPG_Adv[]; 					// int[] (>0 si vrai) -> l'adversaire peux remporter la partie avec un coup de plus		
	int wOn_Moi[]; 					// int[] (>0 si vrai) -> je peux remporter la case i avec un coup de plus
	int wOn_Adv[]; 					// int[] (>0 si vrai) -> l'adversaire peux remporter la case i avec un coup de plus	
	int p1[]; 						// int[] (>0 si vrai) -> possibilit� gagnante LOCAL a profondeur +1
	int p2[]; 						// int[] (count) -> possibilit� gagnante LOCAL a profondeur +2
	int hCp_Adv[];					// int[] (count) -> Nombres de case deja jouer 
	int hCp_Moi[];					// int[] (count) -> Nombres de case deja jouer 	
	int pG1[];                      // int[] (>0 si vrai) -> possibilit� gagnante GLOBAL a profondeur +1
    int pG2[];                      // int[] (count) -> possibilit� gagnante GLOBAL a profondeur +2	
    
	// ========================================================================================//
	//                 			  PARAMETRAGE SCORING EVALUATION 
	// ========================================================================================//
    
	// ************************************** FIXE **********************************************  
    
    // lors du choix : Case GLOBAL / LOCAL non jouable
	private final int GLOBAL_ALREADY_USED     = 9999;     
	private final int LOCAL_ALREADY_USED      = 9999; 	     
	// PARTY | GLOBAL
	private final int PARTY_LOOSE             = 8000;   
	private final int PARTY_WIN               = 8000;  	
	
	// ************************************** BAD (-) ********************************************** 
	
	// redirection
	private final int TO_LOOSE_LOCAL_POSITION = 10; 		// vers une position gagnante -> ADVERSAIRE	
	private final int TO_WIN_LOCAL_POSITION   = 10; 		// vers position gagnante -> MOI
	private final int TO_SELECTED_POSITION    = 100; 	    // vers une position deja jouer -> ADVERSAIRE : AVANTAGE         
	// Anticipation
	private final int LOCAL_BAD_ANTICIP       = 10; 		// J'anticipe a p+1 MAIS je redigige mal l'adversaire	
	// Infos
	private final int ONE_PLAYED              = 30;     // L'adversaire � jouer une fois sur case GLOBAL   
	private final int TWO_PLAYED              = 40;         
	private final int THREE_PLAYED            = 50;         
    private final int DONT_PLAY_THAT          = 1000; // redirige l'adversaire vers une de mes cases GLOBAL qui peut me faire gagner PARTY
	// ************************************** GOOD (+) *********************************************
	
	// je gagne PARTY ou peux gagner GLOBAL    
	private final int GLOBAL_WIN              = 150;        
	private final int PARTY_HOPE              = 10;  
	// je casse le jeu adverse
	private final int GLOBAL_LOOSE     		  = 100;  // cette position est gagnante pour l'adversaire      	
	private final int PARTY_HOPE_BREAKER      = 10;  // l'adversaire peut gagner PARTY si il remporte cette case GLOBAL    
	private final int PARTY_LOOSE_BREAKER     = 7000;  // l'adversaire peut gagner PARTY avec UN SEUL coup de plus 
	private final int BREAK_PARTY_LOOSE       = 7000;
	// Anticipation
	private final int LOCAL_GOOD_ANTICIP      = 40;  // Au prochain tour je peux gagner une case GLOBAL ET je ne redirige pas l'adversaire vers une position AVANTAGEUSE
	private final int LOCAL_DBL_ANTICIP       = 10;  // A partir d'une position dans la grille analyse le nombre de combinaison gagnante      			
	private final int GLOBAL_ANTICIP          = 10;     
	private final int GLOBAL_DBL_ANTICIP      = 10;  
	private final int ROAD_TO_PARTY_WIN       = 3000; // a partir du moment ou je suis a une case de remporter PARTY, je tente de controler les mouvement adverse
	// Infos
	private final int NOT_PLAYED              = 10; // L'adversaire n'a pas jouer sur case GLOBAL        
	
	//========================================================================================//
	//                 	M�thode g�n�ral de scoring d'evaluation LOCAL/GLOBAL
	// ========================================================================================//

	/**
	 * Methode d'evaluation de la meilleur case LOCAL a renvoyer
	 * 
	 * @return int qui repr�sente la case LOCAL qui � le meilleur score
	 */
	private int evalLocal() {
		int caseLocal = ISEMPTY;
		int scoreRep = -10000;
		int score[] = new int [9];	
		int scoreBonus[] = new int[9];

        // TODO si il reste qu'une case GLOBAL a l'adversaire et SURTOUT si il gagne avec un seul coup : ne pas le rediriger vers une case non VIDE

	
			for (int i = 0; i < 9; i++) {
			    score[i] = 0; 
    			System.err.print("* Case LOCAL " + i + " ");
    			
		    //========================================================================================//
			//                 			Controle de l'adversaire TODO
			// ========================================================================================//    				
    			// si j'ai une case GLOBAL qui peut me faire gagner PARTY
		        if (cP_Moi[i] > ISEMPTY){
		            score[i] -= DONT_PLAY_THAT; System.err.print("DONT_PLAY_THAT ");
		            for (int j =0; j < 9; j++){
		                // si l'adveraire � une case gagnante qui n'est pas celle que je veux remporter
		                if ( sPG_Adv[j] > ISEMPTY && (i != j) ){
		                    for (int k = 0; k < 9; k++){
		                        // la case cible est elle meme ou une deja remport�,  et la case est disponible  TODO ce n'est pas sa combinaison final && (sPG_Adv[k] == ISEMPTY)
		                        int tmp_wOn_Adv[] = winOrNot(returnGoodCases(j), ADVERSAIRE);
		                        if ( tmp_wOn_Adv[k] > ISEMPTY && sC_Global[k] != VIDE  && sC_Local[i] == VIDE ){ 
		                            scoreBonus[j] += ROAD_TO_PARTY_WIN; 
		                            System.err.println("-> Ma GLOBAL WIN i : "+i+" la GLOBAL WIN ADVERSE : "+j+" la case LOCAL adverse : "+k);
		                        } 		                        
		                    }
		                }
		            }
		        }
		    //========================================================================================//
			//                 			LOCAL : la case LOCAL est libre
			// ========================================================================================//
    				if ( sC_Local[i] == VIDE ) {
    				    
    				// si la case � un score bonus    
    		        if (scoreBonus[i] > 0 ){ score[i] += scoreBonus[i]; scoreBonus[i] = 0; System.err.print("-> [SCORE BONUS] ");}   
    		        
    				// profondeur +1
    				if (p1[i] > ISEMPTY) { 
    				    if (wOn_Adv[i] > ISEMPTY || sPG_Moi[i] > ISEMPTY) { score[i] += LOCAL_BAD_ANTICIP; 	System.err.print("LOCAL_BAD_ANTICIP "); } 
    				    else { score[i] += LOCAL_GOOD_ANTICIP; 			                                    System.err.print("LOCAL_GOOD_ANTICIP "); }
    				}
    				
    				// profondeur +2
    				if (p2[i] > 0 ) { score[i] += p2[i]*LOCAL_DBL_ANTICIP; 		System.err.print("LOCAL_DBL_ANTICIP "); }
    				
    				// Gagne la case GLOBAL MOI | ADVERSAIRE
    				if (wOn_Adv[i] > ISEMPTY) {score[i] -= GLOBAL_LOOSE; 		                        System.err.print("GLOBAL_LOOSE ");}
    				// marche pas...
    				if (wOn_Adv[i] > ISEMPTY && cP_Adv[i] > ISEMPTY) {score[i] -= BREAK_PARTY_LOOSE; 	System.err.print("BREAK_PARTY_LOOSE ");}    				
    				if (wOn_Moi[i] > ISEMPTY) {score[i] += GLOBAL_WIN; 			                        System.err.print("GLOBAL_WIN "); }			
                    if (wOn_Moi[i] > ISEMPTY && cP_Moi[i] > ISEMPTY) {score[i] += PARTY_WIN;            System.err.print("PARTY_WIN "); }
                    
    				//========================================================================================//
    				//                 		GLOBAL : la case GLOBAL est libre
    				// ========================================================================================//    	
    
    				if ( sC_Global[i] == VIDE ){ 
    					
    					// Nombre de cases jouer ADVERSAIRE
    					if (hCp_Adv[i] == 0 ) {score[i] += NOT_PLAYED; 			System.err.print("NOT_PLAYED ");}
    					if (hCp_Adv[i] == 1 ) {score[i] -= ONE_PLAYED; 			System.err.print("ONE_PLAYED ");}
    					if (hCp_Adv[i] == 2 ) {score[i] -= TWO_PLAYED;  		System.err.print("TWO_PLAYED ");}
    					if (hCp_Adv[i] == 3 ) {score[i] -= THREE_PLAYED; 		System.err.print("THREE_PLAYED ");}
    					
    					// Redirection
    					if (sPG_Adv[i] > ISEMPTY) {score[i] -= TO_LOOSE_LOCAL_POSITION; 		System.err.print("TO_LOOSE_LOCAL_POSITION ");}
    					if (sPG_Moi[i] > ISEMPTY) {score[i] -= TO_WIN_LOCAL_POSITION; 		System.err.print("TO_WIN_LOCAL_POSITION ");}	
    
    				} else if ( casesGlobal[i] == MOI ) { score[i] -= TO_SELECTED_POSITION; 		System.err.print("===> GLOBAL deja remporter par moi ");
    				} else if ( casesGlobal[i] == ADVERSAIRE ) { score[i] -= TO_SELECTED_POSITION; 	System.err.print("===> GLOBAL deja remporter par l'adversaire "); }
    				
    			} else { score[i] -= LOCAL_ALREADY_USED;	System.err.print("===> LOCAL deja jouer "); }
    			System.err.println("[Score LOCAL : " + score[i] + "]");
			}
		
		// D�finie le score MAX
		for ( int i = 0; i < 9; i++) {													    
			if (scoreRep < score[i]) { scoreRep = score[i] ; }	
		}
		
        // Compte les cases dont le score est egal au score max
        int count = 0;
        int pos[] = new int[9];
		for ( int i = 0; i < 9; i++) {													    
			if (scoreRep == score[i]) { pos[count] = i; count++; } 	
		}
		
		// Random si plusieurs cases ont les meme valeurs
        int rdm = (int) (Math.random() * count);
        System.err.println("-> [possibilit� equivalente : " + count + "] - [Random case LOCAL choisie : " + rdm +"]");
        
		return caseLocal = pos[rdm];
	}


	/**
	 * Methode d'evaluation de la meilleur case GLOBAL a renvoyer 
	 * 
	 * @return int qui repr�sente la case LOCAL qui � le meilleur score
	 */
	private void evalGlobal() {
		int caseGlobal = ISEMPTY;
		int scoreRep = -10000;
		int score[] = new int [9];	
		
		// TODO prendre en compte la situation general : ne pas chercher a gagner une case GLOBAL qui ne sert a rien : ANTICIP_GLOBAL
			//========================================================================================//
			//                 			A VOIR 
			// ========================================================================================//	
/*			for (int i = 0; i < 9; i++) {
			    score[i] = 0;    
    			System.err.print("* Case LOCAL " + i + " ");
    			if ( sC_Local[i] == VIDE ) {
    				
    			// si j'ai une case GLOBAL qui peut me faire gagner PARTY
		        if (cP_Moi[i] > ISEMPTY){
		            // si en plus j'ai une combinaison gagnante 
		            if (sPG_Moi[i] > ISEMPTY){ score[i] += PARTY_WIN; System.err.print("PARTY_WIN ");}
		            for (int j =0; j < 9; j++){
		                // si l'adveraire � une case gagnante ET (la case cible est elle meme ou une deja remport�) que ce n'est pas sa combinaison final 
		                if ( wOn_Adv[j] > ISEMPTY && ( sC_Global[j] != VIDE || i == j ) && (cP_Adv[j] == ISEMPTY)  ){ score[i] += ROAD_TO_PARTY_WIN; System.err.print("ROAD_TO_PARTY_WIN ");}    
		            }
		        }*/
		        
		for (int i = 0; i < 9; i++) {
			score[i] = 0; 
			System.err.print("* Case GLOBAL " + i);
			
			// si la case n'est pas encore remporter par un joueur
			if ( sC_Global[i] == VIDE ) { 
					
				// profondeur +1
				if ( pG1[i] > ISEMPTY ) { score[i] += GLOBAL_ANTICIP;                        System.err.print("GLOBAL_ANTICIP "); }
				
				// Profondeur +2
				if ( pG2[i] > 0 ) { score[i] += p2[i]*GLOBAL_DBL_ANTICIP; 		         	 System.err.print("GLOBAL_DBL_ANTICIP ");}
				
				// si je peux gagner la partie // TODO NE MARCHE PAS BIEN....
				if ( cP_Moi[i] > 0 && sPG_Moi[i] > 0 ) { score[i] += PARTY_WIN; 			 System.err.print("PARTY_WIN "); } 

				// si je peux esperer gagner la partie en gagnant cette case
				if ( cP_Moi[i] > 0 ) { score[i] += PARTY_HOPE; 							 	 System.err.print("PARTY_HOPE "); }
				
				// si je peux eviter a l'adversaire de gagner le global
				if ( cP_Adv[i] > 0  ) { score[i] += PARTY_HOPE_BREAKER;           			 System.err.print("PARTY_HOPE_BREAKER "); }  
				
				//si l'adversaire peut gagner la partie
				if ( cP_Adv[i] > 0 && sPG_Moi[i] > 0 ) { score[i] += PARTY_LOOSE_BREAKER;    System.err.print("PARTY_LOOSE_BREAKER "); }
				
				// si l'adversaire � possibilit� de gagner une case
				if ( sPG_Adv[i] > 0 ) { score[i] -= GLOBAL_LOOSE;                            System.err.print("GLOBAL_LOOSE "); } 
				
				//si je peux gagner une case 
				if ( sPG_Moi[i] > 0 ) { score[i] += GLOBAL_WIN;                              System.err.print("GLOBAL_WIN "); }
				
			} else {score[i] -= GLOBAL_ALREADY_USED; 										 System.err.print("===> GLOBAL deja remporter "); }

			System.err.println("[Score GLOBAL : " + score[i] + "]");    
		}

		// renvois la case avec le meilleur score
		for ( int i = 0; i < 9; i++) {													    
			if (scoreRep < score[i]) { scoreRep = score[i] ; }	
		}
		
        // Compte les cases dont le score est egal au score max
        int count = 0;
        int pos[] = new int[9];
		for ( int i = 0; i < 9; i++) {													    
			if (scoreRep == score[i]) { pos[count] = i; count++; } 	
		}
		
		// Random si plusieurs cases ont les meme valeurs
        int rdm = (int) (Math.random() * count);
        System.err.println("-> [possibilit� equivalente : " + count + "] - [Random case GLOBAL choisie : " + rdm +"]");
        System.err.println();
		this.casesActuel = returnGoodCases(pos[rdm]);	
	}

	// ========================================================================================//
	//                          	    Methode Principal
	// ========================================================================================//                      

	/**
	 * Envois une r�ponse sous forme "0 0" correspondant au coordon�es � jouer selon l'analyse
	 * 
	 * @param oRow la coordon�e row du joueur oppos� au precedent tour
	 * @param oCol la coordon�e col du joueur oppos� au precedent tour
	 * @return String
	 */
	public String getSolved(int oRow, int oCol){
		
		int myCases[][];
		int myCase;

		// on recherche quel cases est concern� par le pr�cedent tour de l'adversaire
		int convert[] = converXYToCase(oRow, oCol); 					
		myCases = returnGoodCases(convert[GLOBAL]); 
		myCase = convert[LOCAL]; 
		
		// on modifie la case jouer par l'adversaire si il a jouer au tour precedent
		if (oRow != -1) {asPlayedCase(myCases, myCase, ADVERSAIRE);} 
		
		// on met � jour le status d'occupation des cases GLOBAL
		sC_Global = getNewStatusGlobal(); 

		// Analyse la case GLOBAL � choisir
		if ( casesGlobal[convert[LOCAL]] != VIDE ){ evalGlobal();} 
		
		// ========================================================================================//
		//      	   initialise des �l�ments de status et envois la r�ponse
		//=========================================================================================// 
		
		// permet de determiner quel est le num�ro de la case GLOBAL jouer actuelement
		indexActuel = converXYToCase(casesActuel[0][ROW], casesActuel[0][COL])[GLOBAL];
		
		// l'etat actuel LOCAL et GLOBAL
		sC_Global = getNewStatusGlobal();    
		sC_Local = statusCase(casesActuel);
		
		// Analyse les probabilit� GLOBAL et LOCAL
		cP_Moi = controlPlayed(MOI);
		cP_Adv = controlPlayed(ADVERSAIRE);
		sPG_Moi = getNewStatusProbGlobal(MOI);
		sPG_Adv = getNewStatusProbGlobal(ADVERSAIRE);
		
		// Analyse de pr�cision des cases LOCAL
		wOn_Moi = winOrNot(casesActuel, MOI);
		wOn_Adv = winOrNot(casesActuel, ADVERSAIRE);
		
		// Analyse les possibilit� gagnante LOCAL a profondeur +1 et +2
		p1 = anticip(casesActuel);
		p2 = analystDoubleAnticip(casesActuel);
		
		// Analyse les possibilit� gagnante GLOBAL a profondeur +1 et +2
		pG1 = anticipGlobal();
		pG2 = analystDoubleAnticipGlobal();
		
		// Analyse combien de case LOCAL � jouer l'adversaire
		hCp_Adv = howCasePlayed(ADVERSAIRE);
		hCp_Moi = howCasePlayed(MOI);
		
		// analyse la r�ponse LOCAL � donner
		int response = evalLocal();																													   

		// Met � jour le status de la case jouer
		asPlayedCase(casesActuel, response, MOI);

		// retoune les coordonn�e de la case a jouer
		return  casesActuel[response][ROW] + " " + casesActuel[response][COL] ;
	}        

	// ========================================================================================//                                                                                                                                                                        
	//                             Methodes evaluation GLOBAL                                                                                                                                                                                                     
	// ========================================================================================//                                                                                                                                                                        

	/**
	 * Methode qui analyse les cases GLOBAL afin de determiner si il existe une possibilit� de gagner la partie pour PLAYER
	 * => si deux cases GLOBAL sont remplis par un joueur ET qu'une combinaison gagnante est possible avec une case VIDE
	 * @param PLAYER qui repr�sente le joueur 
	 * @return int[i] repr�sentant une case GLOBAL, si i vaut -1, ce n'est pas une combinaison gagnante
	 */
	private int[] controlPlayed(int PLAYER) {
		int solution[] = new int[9];   	

		// Ligne : 3 possibilit� MAX                                                                                                                                                                                                                                                                                                                                                                                                                                            
		if ( casesGlobal[0] == PLAYER && casesGlobal[1] == PLAYER &&  casesGlobal[2] == VIDE) { solution[2] = PLAYER; }                                                                                                                             
		if ( casesGlobal[1] == PLAYER && casesGlobal[2] == PLAYER &&  casesGlobal[0] == VIDE) { solution[0] = PLAYER; }                                                                                                                             
		if ( casesGlobal[0] == PLAYER && casesGlobal[2] == PLAYER &&  casesGlobal[1] == VIDE) { solution[1] = PLAYER; }   
		if ( casesGlobal[3] == PLAYER && casesGlobal[4] == PLAYER &&  casesGlobal[5] == VIDE) { solution[5] = PLAYER; }                                                                                                                             
		if ( casesGlobal[4] == PLAYER && casesGlobal[5] == PLAYER &&  casesGlobal[3] == VIDE) { solution[3] = PLAYER; }                                                                                                                             
		if ( casesGlobal[3] == PLAYER && casesGlobal[5] == PLAYER &&  casesGlobal[4] == VIDE) { solution[4] = PLAYER; }  
		if ( casesGlobal[6] == PLAYER && casesGlobal[7] == PLAYER &&  casesGlobal[8] == VIDE) { solution[8] = PLAYER; }                                                                                                                             
		if ( casesGlobal[7] == PLAYER && casesGlobal[8] == PLAYER &&  casesGlobal[6] == VIDE) { solution[6] = PLAYER; }                                                                                                                             
		if ( casesGlobal[6] == PLAYER && casesGlobal[8] == PLAYER &&  casesGlobal[7] == VIDE) { solution[7] = PLAYER; }  
		                                                                                                            
		// Colonne : 3 possibilit�s MAX                                                                                                                                                                                                                                                                                                                                                                                                                                            
		if ( casesGlobal[0] == PLAYER && casesGlobal[3] == PLAYER &&  casesGlobal[6] == VIDE) { solution[6] = PLAYER; }                                                                                                                             
		if ( casesGlobal[3] == PLAYER && casesGlobal[6] == PLAYER &&  casesGlobal[0] == VIDE) { solution[0] = PLAYER; }                                                                                                                             
		if ( casesGlobal[0] == PLAYER && casesGlobal[6] == PLAYER &&  casesGlobal[3] == VIDE) { solution[3] = PLAYER; } 
		if ( casesGlobal[1] == PLAYER && casesGlobal[4] == PLAYER &&  casesGlobal[7] == VIDE) { solution[7] = PLAYER; }                                                                                                                             
		if ( casesGlobal[4] == PLAYER && casesGlobal[7] == PLAYER &&  casesGlobal[1] == VIDE) { solution[1] = PLAYER; }                                                                                                                             
		if ( casesGlobal[1] == PLAYER && casesGlobal[7] == PLAYER &&  casesGlobal[4] == VIDE) { solution[4] = PLAYER; } 
		if ( casesGlobal[2] == PLAYER && casesGlobal[5] == PLAYER &&  casesGlobal[8] == VIDE) { solution[8] = PLAYER; }                                                                                                                             
		if ( casesGlobal[5] == PLAYER && casesGlobal[8] == PLAYER &&  casesGlobal[2] == VIDE) { solution[2] = PLAYER; }                                                                                                                             
		if ( casesGlobal[2] == PLAYER && casesGlobal[8] == PLAYER &&  casesGlobal[5] == VIDE) { solution[5] = PLAYER; }  
		                                                                                                            
		// Diagonal : 2 possibilit�s MAX                                                                                                                                                                                                                                                                                                                                                                                                                                        
		if ( casesGlobal[6] == PLAYER && casesGlobal[4] == PLAYER &&  casesGlobal[2] == VIDE) { solution[2] = PLAYER; }                                                                                                                             
		if ( casesGlobal[4] == PLAYER && casesGlobal[2] == PLAYER &&  casesGlobal[6] == VIDE) { solution[6] = PLAYER; }                                                                                                                             
		if ( casesGlobal[6] == PLAYER && casesGlobal[2] == PLAYER &&  casesGlobal[4] == VIDE) { solution[4] = PLAYER; }  
		if ( casesGlobal[0] == PLAYER && casesGlobal[4] == PLAYER &&  casesGlobal[8] == VIDE) { solution[8] = PLAYER; }                                                                                                                             
		if ( casesGlobal[4] == PLAYER && casesGlobal[8] == PLAYER &&  casesGlobal[0] == VIDE) { solution[0] = PLAYER; }                                                                                                                             
		if ( casesGlobal[0] == PLAYER && casesGlobal[8] == PLAYER &&  casesGlobal[4] == VIDE) { solution[4] = PLAYER; }  

		for (int i = 0; i < 9; i++) {
			if (solution[i] != PLAYER) { 
				solution[i] = ISEMPTY;
			}
		}
		
		return solution;
	}

	/**
	 * Met � jour la variable casesGlobal[] : la valeur � l'index i repr�sente le joueur qui � remporter la case (0 = VIDE, 1 = MOI, 2 = ADVERSAIRE)
	 * @return int[] ou i repr�sente quel joueur a remporter la case GLOBAL
	 * 
	 */
	private int[] getNewStatusGlobal() {
		int myCase[][] = new int[9][3]; 

		for (int i = 0; i < 9; i++) {
			myCase = returnGoodCases(i);

			// ligne
			if ( myCase[0][JOUEUR] == myCase[1][JOUEUR] && myCase[1][JOUEUR] == myCase[2][JOUEUR] ) { if (myCase[0][JOUEUR] != VIDE) { casesGlobal[i] = myCase[0][JOUEUR]; } } 
			if ( myCase[1][JOUEUR] == myCase[2][JOUEUR] && myCase[2][JOUEUR] == myCase[0][JOUEUR] ) { if (myCase[1][JOUEUR] != VIDE) { casesGlobal[i] = myCase[0][JOUEUR]; } }                                                                                                                              
			if ( myCase[0][JOUEUR] == myCase[2][JOUEUR] && myCase[2][JOUEUR] == myCase[1][JOUEUR] ) { if (myCase[0][JOUEUR] != VIDE) { casesGlobal[i] = myCase[0][JOUEUR]; } }   
			if ( myCase[3][JOUEUR] == myCase[4][JOUEUR] && myCase[4][JOUEUR] == myCase[5][JOUEUR] ) { if (myCase[3][JOUEUR] != VIDE) { casesGlobal[i] = myCase[3][JOUEUR]; } }                                                                                                                             
			if ( myCase[4][JOUEUR] == myCase[5][JOUEUR] && myCase[5][JOUEUR] == myCase[3][JOUEUR] ) { if (myCase[4][JOUEUR] != VIDE) { casesGlobal[i] = myCase[3][JOUEUR]; } }                                                                                                                              
			if ( myCase[3][JOUEUR] == myCase[5][JOUEUR] && myCase[5][JOUEUR] == myCase[4][JOUEUR] ) { if (myCase[3][JOUEUR] != VIDE) { casesGlobal[i] = myCase[3][JOUEUR]; } }   
			if ( myCase[6][JOUEUR] == myCase[7][JOUEUR] && myCase[7][JOUEUR] == myCase[8][JOUEUR] ) { if (myCase[6][JOUEUR] != VIDE) { casesGlobal[i] = myCase[6][JOUEUR]; } }                                                                                                                              
			if ( myCase[7][JOUEUR] == myCase[8][JOUEUR] && myCase[8][JOUEUR] == myCase[6][JOUEUR] ) { if (myCase[7][JOUEUR] != VIDE) { casesGlobal[i] = myCase[6][JOUEUR]; } }                                                                                                                             
			if ( myCase[6][JOUEUR] == myCase[8][JOUEUR] && myCase[8][JOUEUR] == myCase[7][JOUEUR] ) { if (myCase[6][JOUEUR] != VIDE) { casesGlobal[i] = myCase[6][JOUEUR]; } }                                                                                                                                                                                                                                                                                                                                                                                            
			// colonne                                                                                                                                                                                                                                                                                                                                                                                                                                           
			if ( myCase[0][JOUEUR] == myCase[3][JOUEUR] && myCase[3][JOUEUR] == myCase[6][JOUEUR] ) { if (myCase[3][JOUEUR] != VIDE) { casesGlobal[i] = myCase[3][JOUEUR]; } }                                                                                                                             
			if ( myCase[3][JOUEUR] == myCase[6][JOUEUR] && myCase[6][JOUEUR] == myCase[0][JOUEUR] ) { if (myCase[6][JOUEUR] != VIDE) { casesGlobal[i] = myCase[3][JOUEUR]; } }                                                                                                                              
			if ( myCase[0][JOUEUR] == myCase[6][JOUEUR] && myCase[6][JOUEUR] == myCase[3][JOUEUR] ) { if (myCase[6][JOUEUR] != VIDE) { casesGlobal[i] = myCase[3][JOUEUR]; } } 
			if ( myCase[1][JOUEUR] == myCase[4][JOUEUR] && myCase[4][JOUEUR] == myCase[7][JOUEUR] ) { if (myCase[4][JOUEUR] != VIDE) { casesGlobal[i] = myCase[4][JOUEUR]; } }                                                                                                                             
			if ( myCase[4][JOUEUR] == myCase[7][JOUEUR] && myCase[7][JOUEUR] == myCase[1][JOUEUR] ) { if (myCase[7][JOUEUR] != VIDE) { casesGlobal[i] = myCase[4][JOUEUR]; } }                                                                                                                             
			if ( myCase[1][JOUEUR] == myCase[7][JOUEUR] && myCase[7][JOUEUR] == myCase[4][JOUEUR] ) { if (myCase[7][JOUEUR] != VIDE) { casesGlobal[i] = myCase[4][JOUEUR]; } } 
			if ( myCase[2][JOUEUR] == myCase[5][JOUEUR] && myCase[5][JOUEUR] == myCase[8][JOUEUR] ) { if (myCase[5][JOUEUR] != VIDE) { casesGlobal[i] = myCase[5][JOUEUR]; } }                                                                                                                              
			if ( myCase[5][JOUEUR] == myCase[8][JOUEUR] && myCase[8][JOUEUR] == myCase[2][JOUEUR] ) { if (myCase[8][JOUEUR] != VIDE) { casesGlobal[i] = myCase[5][JOUEUR]; } }                                                                                                                             
			if ( myCase[2][JOUEUR] == myCase[8][JOUEUR] && myCase[8][JOUEUR] == myCase[5][JOUEUR] ) { if (myCase[8][JOUEUR] != VIDE) { casesGlobal[i] = myCase[5][JOUEUR]; } }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
			// diagonal                                                                                                                                                                                                                                                                                                                                                                                                                   
			if ( myCase[6][JOUEUR] == myCase[4][JOUEUR] && myCase[4][JOUEUR] == myCase[2][JOUEUR] ) { if (myCase[4][JOUEUR] != VIDE) { casesGlobal[i] = myCase[4][JOUEUR]; } }                                                                                                                             
			if ( myCase[4][JOUEUR] == myCase[2][JOUEUR] && myCase[2][JOUEUR] == myCase[6][JOUEUR] ) { if (myCase[2][JOUEUR] != VIDE) { casesGlobal[i] = myCase[4][JOUEUR]; } }                                                                                                                              
			if ( myCase[6][JOUEUR] == myCase[2][JOUEUR] && myCase[2][JOUEUR] == myCase[4][JOUEUR] ) { if (myCase[2][JOUEUR] != VIDE) { casesGlobal[i] = myCase[4][JOUEUR]; } }  
			if ( myCase[0][JOUEUR] == myCase[4][JOUEUR] && myCase[4][JOUEUR] == myCase[8][JOUEUR] ) { if (myCase[4][JOUEUR] != VIDE) { casesGlobal[i] = myCase[4][JOUEUR]; } }                                                                                                                              
			if ( myCase[4][JOUEUR] == myCase[8][JOUEUR] && myCase[8][JOUEUR] == myCase[0][JOUEUR] ) { if (myCase[8][JOUEUR] != VIDE) { casesGlobal[i] = myCase[4][JOUEUR]; } }                                                                                                                             
			if ( myCase[0][JOUEUR] == myCase[8][JOUEUR] && myCase[8][JOUEUR] == myCase[4][JOUEUR] ) { if (myCase[8][JOUEUR] != VIDE) { casesGlobal[i] = myCase[4][JOUEUR]; } } 

		}    
		
		return casesGlobal;
	} 

	/**
	 * Methode qui analyse les cases GLOBAL afin de determiner si il existe une possibilit� de gagner une case GLOBAL pour PLAYER
	 * => si une case GLOBAL est remplis par un joueur ET qu'une combinaison gagnante est possible avec une case VIDE
	 * @param PLAYER qui repr�sente le joueur 
	 * @return int[i] repr�sentant une case GLOBAL, si i vaut -1, ce n'est pas une combinaison gagnante
	 */
	private int[] getNewStatusProbGlobal(int PLAYER) {  	
		int myCase[][] = new int[9][3]; 
		int tcasesGlobalProb[] = new int[9];
		for (int i = 0; i < 9; i++) {
			myCase = returnGoodCases(i); 

			// si on � 2 item de l'adversaire dans une meme ligne ET une case vide                                                                                                                                                                                                                                                                                                                                                                                                                                               
			if ( myCase[0][JOUEUR] == PLAYER && myCase[1][JOUEUR] == PLAYER &&  myCase[2][JOUEUR] == VIDE) { tcasesGlobalProb[i] = PLAYER; }                                                                                                                             
			if ( myCase[1][JOUEUR] == PLAYER && myCase[2][JOUEUR] == PLAYER &&  myCase[0][JOUEUR] == VIDE) { tcasesGlobalProb[i] = PLAYER; }                                                                                                                             
			if ( myCase[0][JOUEUR] == PLAYER && myCase[2][JOUEUR] == PLAYER &&  myCase[1][JOUEUR] == VIDE) { tcasesGlobalProb[i] = PLAYER; }   

			if ( myCase[3][JOUEUR] == PLAYER && myCase[4][JOUEUR] == PLAYER &&  myCase[5][JOUEUR] == VIDE) { tcasesGlobalProb[i] = PLAYER; }                                                                                                                             
			if ( myCase[4][JOUEUR] == PLAYER && myCase[5][JOUEUR] == PLAYER &&  myCase[3][JOUEUR] == VIDE) { tcasesGlobalProb[i] = PLAYER; }                                                                                                                             
			if ( myCase[3][JOUEUR] == PLAYER && myCase[5][JOUEUR] == PLAYER &&  myCase[4][JOUEUR] == VIDE) { tcasesGlobalProb[i] = PLAYER; }  

			if ( myCase[6][JOUEUR] == PLAYER && myCase[7][JOUEUR] == PLAYER &&  myCase[8][JOUEUR] == VIDE) { tcasesGlobalProb[i] = PLAYER; }                                                                                                                             
			if ( myCase[7][JOUEUR] == PLAYER && myCase[8][JOUEUR] == PLAYER &&  myCase[6][JOUEUR] == VIDE) { tcasesGlobalProb[i] = PLAYER; }                                                                                                                             
			if ( myCase[6][JOUEUR] == PLAYER && myCase[8][JOUEUR] == PLAYER &&  myCase[7][JOUEUR] == VIDE) { tcasesGlobalProb[i] = PLAYER; }                                                                                                                                                                                                                                                                                                                                                                                            

			if ( myCase[0][JOUEUR] == PLAYER && myCase[3][JOUEUR] == PLAYER &&  myCase[6][JOUEUR] == VIDE) { tcasesGlobalProb[i] = PLAYER; }                                                                                                                             
			if ( myCase[3][JOUEUR] == PLAYER && myCase[6][JOUEUR] == PLAYER &&  myCase[0][JOUEUR] == VIDE) { tcasesGlobalProb[i] = PLAYER; }                                                                                                                             
			if ( myCase[0][JOUEUR] == PLAYER && myCase[6][JOUEUR] == PLAYER &&  myCase[3][JOUEUR] == VIDE) { tcasesGlobalProb[i] = PLAYER; } 

			if ( myCase[1][JOUEUR] == PLAYER && myCase[4][JOUEUR] == PLAYER &&  myCase[7][JOUEUR] == VIDE) { tcasesGlobalProb[i] = PLAYER; }                                                                                                                             
			if ( myCase[4][JOUEUR] == PLAYER && myCase[7][JOUEUR] == PLAYER &&  myCase[1][JOUEUR] == VIDE) { tcasesGlobalProb[i] = PLAYER; }                                                                                                                             
			if ( myCase[1][JOUEUR] == PLAYER && myCase[7][JOUEUR] == PLAYER &&  myCase[4][JOUEUR] == VIDE) { tcasesGlobalProb[i] = PLAYER; } 

			if ( myCase[2][JOUEUR] == PLAYER && myCase[5][JOUEUR] == PLAYER &&  myCase[8][JOUEUR] == VIDE) { tcasesGlobalProb[i] = PLAYER; }                                                                                                                             
			if ( myCase[5][JOUEUR] == PLAYER && myCase[8][JOUEUR] == PLAYER &&  myCase[2][JOUEUR] == VIDE) { tcasesGlobalProb[i] = PLAYER; }                                                                                                                             
			if ( myCase[2][JOUEUR] == PLAYER && myCase[8][JOUEUR] == PLAYER &&  myCase[5][JOUEUR] == VIDE) { tcasesGlobalProb[i] = PLAYER; }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  

			if ( myCase[6][JOUEUR] == PLAYER && myCase[4][JOUEUR] == PLAYER &&  myCase[2][JOUEUR] == VIDE) { tcasesGlobalProb[i] = PLAYER; }                                                                                                                             
			if ( myCase[4][JOUEUR] == PLAYER && myCase[2][JOUEUR] == PLAYER &&  myCase[6][JOUEUR] == VIDE) { tcasesGlobalProb[i] = PLAYER; }                                                                                                                             
			if ( myCase[6][JOUEUR] == PLAYER && myCase[2][JOUEUR] == PLAYER &&  myCase[4][JOUEUR] == VIDE) { tcasesGlobalProb[i] = PLAYER; }  

			if ( myCase[0][JOUEUR] == PLAYER && myCase[4][JOUEUR] == PLAYER &&  myCase[8][JOUEUR] == VIDE) { tcasesGlobalProb[i] = PLAYER; }                                                                                                                             
			if ( myCase[4][JOUEUR] == PLAYER && myCase[8][JOUEUR] == PLAYER &&  myCase[0][JOUEUR] == VIDE) { tcasesGlobalProb[i] = PLAYER; }                                                                                                                             
			if ( myCase[0][JOUEUR] == PLAYER && myCase[8][JOUEUR] == PLAYER &&  myCase[4][JOUEUR] == VIDE) { tcasesGlobalProb[i] = PLAYER; }

			}
		
		for (int i = 0; i < 9; i++) {
			if (tcasesGlobalProb[i] != PLAYER) { 
				tcasesGlobalProb[i] = ISEMPTY;
			}			
		}
		
		return tcasesGlobalProb;
	}

	// ========================================================================================//                                                                                                                                                                        
	//                             Methodes evaluation LOCAL                                                                                                                                                                                                      
	// ========================================================================================//    

	/**
	 * Methode qui analyse avec pr�cision une case GLOBAL et determine quel cases est gagnante pour PLAYER afin de remporter une case GLOBAL 
	 * 
	 * @param myCase[][] qui est la cases que l'ont souhaite analyser
	 * @return int[] qui repr�sente chaque case LOCAL, si i vaut -1 la case n'est pas une combinaison gagnante pour PLAYER
	 */
	private int[] winOrNot(int[][] myCase, int PLAYER){
		int solution[] = new int [9];                                                                                                                                                                                                                                                   

		// Ligne                                                                                                                                                                                                                                                                                                                                                                                                                            
		if ( statusCase(myCase)[0] == PLAYER && statusCase(myCase)[1] == PLAYER &&  statusCase(myCase)[2] == VIDE) { solution[2] = PLAYER; }                                                                                                                                           
		if ( statusCase(myCase)[1] == PLAYER && statusCase(myCase)[2] == PLAYER &&  statusCase(myCase)[0] == VIDE) { solution[0] = PLAYER; }                                                                                                                                           
		if ( statusCase(myCase)[0] == PLAYER && statusCase(myCase)[2] == PLAYER &&  statusCase(myCase)[1] == VIDE) { solution[1] = PLAYER; } 
		if ( statusCase(myCase)[3] == PLAYER && statusCase(myCase)[4] == PLAYER &&  statusCase(myCase)[5] == VIDE) { solution[5] = PLAYER; }                                                                                                                                           
		if ( statusCase(myCase)[4] == PLAYER && statusCase(myCase)[5] == PLAYER &&  statusCase(myCase)[3] == VIDE) { solution[3] = PLAYER; }                                                                                                                                           
		if ( statusCase(myCase)[3] == PLAYER && statusCase(myCase)[5] == PLAYER &&  statusCase(myCase)[4] == VIDE) { solution[4] = PLAYER; }  
		if ( statusCase(myCase)[6] == PLAYER && statusCase(myCase)[7] == PLAYER &&  statusCase(myCase)[8] == VIDE) { solution[8] = PLAYER; }                                                                                                                                           
		if ( statusCase(myCase)[7] == PLAYER && statusCase(myCase)[8] == PLAYER &&  statusCase(myCase)[6] == VIDE) { solution[6] = PLAYER; }                                                                                                                                           
		if ( statusCase(myCase)[6] == PLAYER && statusCase(myCase)[8] == PLAYER &&  statusCase(myCase)[7] == VIDE) { solution[7] = PLAYER; }  
		// Colonne                                                                                                                                                                                                                                                                                                                                                                                                                                   
		if ( statusCase(myCase)[0] == PLAYER && statusCase(myCase)[3] == PLAYER &&  statusCase(myCase)[6] == VIDE) { solution[6] = PLAYER; }                                                                                                                                           
		if ( statusCase(myCase)[3] == PLAYER && statusCase(myCase)[6] == PLAYER &&  statusCase(myCase)[0] == VIDE) { solution[0] = PLAYER; }                                                                                                                                           
		if ( statusCase(myCase)[0] == PLAYER && statusCase(myCase)[6] == PLAYER &&  statusCase(myCase)[3] == VIDE) { solution[3] = PLAYER; }  
		if ( statusCase(myCase)[1] == PLAYER && statusCase(myCase)[4] == PLAYER &&  statusCase(myCase)[7] == VIDE) { solution[7] = PLAYER; }                                                                                                                                           
		if ( statusCase(myCase)[4] == PLAYER && statusCase(myCase)[7] == PLAYER &&  statusCase(myCase)[1] == VIDE) { solution[1] = PLAYER; }                                                                                                                                           
		if ( statusCase(myCase)[1] == PLAYER && statusCase(myCase)[7] == PLAYER &&  statusCase(myCase)[4] == VIDE) { solution[4] = PLAYER; }   
		if ( statusCase(myCase)[2] == PLAYER && statusCase(myCase)[5] == PLAYER &&  statusCase(myCase)[8] == VIDE) { solution[8] = PLAYER; }                                                                                                                                           
		if ( statusCase(myCase)[5] == PLAYER && statusCase(myCase)[8] == PLAYER &&  statusCase(myCase)[2] == VIDE) { solution[2] = PLAYER; }                                                                                                                                           
		if ( statusCase(myCase)[2] == PLAYER && statusCase(myCase)[8] == PLAYER &&  statusCase(myCase)[5] == VIDE) { solution[5] = PLAYER; } 
		// Diagonal                                                                                                                                                                                                                                                                                                                                                                                                                                                   
		if ( statusCase(myCase)[6] == PLAYER && statusCase(myCase)[4] == PLAYER &&  statusCase(myCase)[2] == VIDE) { solution[2] = PLAYER;}                                                                                                                                           
		if ( statusCase(myCase)[4] == PLAYER && statusCase(myCase)[2] == PLAYER &&  statusCase(myCase)[6] == VIDE) { solution[6] = PLAYER;}                                                                                                                                           
		if ( statusCase(myCase)[6] == PLAYER && statusCase(myCase)[2] == PLAYER &&  statusCase(myCase)[4] == VIDE) { solution[4] = PLAYER;}  
		if ( statusCase(myCase)[0] == PLAYER && statusCase(myCase)[4] == PLAYER &&  statusCase(myCase)[8] == VIDE) { solution[8] = PLAYER;}                                                                                                                                           
		if ( statusCase(myCase)[4] == PLAYER && statusCase(myCase)[8] == PLAYER &&  statusCase(myCase)[0] == VIDE) { solution[0] = PLAYER;}                                                                                                                                           
		if ( statusCase(myCase)[0] == PLAYER && statusCase(myCase)[8] == PLAYER &&  statusCase(myCase)[4] == VIDE) { solution[4] = PLAYER;} 		
		
		for (int i = 0; i < 9; i++) {
			if (solution[i] != PLAYER) { 
				solution[i] = ISEMPTY;
			}
		}	
		
		return solution;                                                                                                                                                                                                                                               
	} 
  
	/**
	 * M�thode qui analyse les possibilit�s LOCAL gagnante � profondeur +1
	 * 
	 * @param global[][] qui repr�sente une case GLOBAL
	 * @return int[] qui renvois 1 si la cases jouer peut faire gagner une case GLOBAL au prochain tour sinon ISEMPTY
	 */
	private int[] anticip(int[][] global) {
		int solution[] = new int[9];
		int tmp;
		                                  
		for (int i = 0; i < 9; i++){
			tmp = global[i][JOUEUR];
			global[i][JOUEUR] = MOI;
			for (int j = 0; j < 9; j++){
				if (winOrNot(global, MOI)[j] == MOI) { 
				    solution[i] = 1; 
				    break; 
				}    
			}
		    global[i][JOUEUR] = tmp;
		}
		   	    
		for (int i = 0; i < 9; i++) {
			if (solution[i] != 1) { 
				solution[i] = ISEMPTY;
			}
		}	
		
		return solution;
	}

	/**
	 * M�thode qui analyse les possibilit�s GLOBAL gagnante � profondeur +1
	 * 
	 * @return int[] qui renvois 1 si la cases jouer peut faire gagner la partie avec une case GLOBAL de plus
	 */
	private int[] anticipGlobal() {
		int solution[] = new int[9];
		int tmp;
		                                  
		for (int i = 0; i < 9; i++){
			tmp = sC_Global[i];
			sC_Global[i] = MOI;
			for (int j = 0; j < 9; j++){
				if (controlPlayed(MOI)[j] == MOI) { 
				    solution[i] = 1; 
				    break; 
				}    
			}
		    sC_Global[i] = tmp;
		}
		   	    
		for (int i = 0; i < 9; i++) {
			if (solution[i] != 1) { 
				solution[i] = ISEMPTY;
			}
		}	
		
		return solution;
	}

	/**
	 * M�thode qui analyse les possibilit�s gagnante LOCAL � profondeur +2
	 * 
	 * @param global[][] qui repr�sente une case GLOBAL
	 * @return int[] qui indique combien de combinaison gagnante sont possible pour chaque case LOCAL i sinon ISEMPTY
	 */
	private int[] analystDoubleAnticip(int[][] global){
		int solution[] = new int[9];

		for (int i = 0; i < 9; i++ ) {
			if ( i == 0 ){ 
				if (global[1][JOUEUR] == VIDE && global[2][JOUEUR] == VIDE ) {solution[0] += 1;}
				if (global[4][JOUEUR] == VIDE && global[8][JOUEUR] == VIDE ) {solution[0] += 1;}
				if (global[3][JOUEUR] == VIDE && global[6][JOUEUR] == VIDE ) {solution[0] += 1;}
			}		
			if ( i == 1 ){
				if (global[0][JOUEUR] == VIDE && global[2][JOUEUR] == VIDE) {solution[1] += 1;}
				if (global[4][JOUEUR] == VIDE && global[7][JOUEUR] == VIDE) {solution[1] += 1;} 
			}		
			if ( i == 2 ){
				if (global[1][JOUEUR] == VIDE && global[0][JOUEUR] == VIDE) {solution[2] += 1;}
				if (global[5][JOUEUR] == VIDE && global[8][JOUEUR] == VIDE) {solution[2] += 1;}
				if (global[4][JOUEUR] == VIDE && global[6][JOUEUR] == VIDE) {solution[2] += 1;} 
			}		
			if ( i == 3 ){ 
				if (global[0][JOUEUR] == VIDE && global[6][JOUEUR] == VIDE) {solution[3] += 1;} 
				if (global[4][JOUEUR] == VIDE && global[5][JOUEUR] == VIDE) {solution[3] += 1;} 
			}		
			if ( i == 4 ){ 
				if (global[0][JOUEUR] == VIDE && global[7][JOUEUR] == VIDE) {solution[4] += 1;} 
				if (global[2][JOUEUR] == VIDE && global[6][JOUEUR] == VIDE) {solution[4] += 1;} 
				if (global[1][JOUEUR] == VIDE && global[7][JOUEUR] == VIDE) {solution[4] += 1;} 
				if (global[3][JOUEUR] == VIDE && global[5][JOUEUR] == VIDE) {solution[4] += 1;}  
			}		
			if ( i == 5 ){ 
				if (global[2][JOUEUR] == VIDE && global[7][JOUEUR] == VIDE) {solution[5] += 1;}  
				if (global[3][JOUEUR] == VIDE && global[4][JOUEUR] == VIDE) {solution[5] += 1;}  
			}		
			if ( i == 6 ){ 
				if (global[7][JOUEUR] == VIDE && global[8][JOUEUR] == VIDE) {solution[6] += 1;} 
				if (global[0][JOUEUR] == VIDE && global[3][JOUEUR] == VIDE) {solution[6] += 1;} 
				if (global[4][JOUEUR] == VIDE && global[2][JOUEUR] == VIDE) {solution[6] += 1;}  
			}
			if ( i == 7 ){ 
				if (global[6][JOUEUR] == VIDE && global[8][JOUEUR] == VIDE) {solution[7] += 1;} 
				if (global[4][JOUEUR] == VIDE && global[1][JOUEUR] == VIDE) {solution[7] += 1;} 
			}
			if ( i == 8 ){
				if (global[6][JOUEUR] == VIDE && global[7][JOUEUR] == VIDE) {solution[8] += 1;} 
				if (global[2][JOUEUR] == VIDE && global[5][JOUEUR] == VIDE) {solution[8] += 1;} 
				if (global[0][JOUEUR] == VIDE && global[4][JOUEUR] == VIDE) {solution[8] += 1;} 
			}
		}
		
		for (int i = 0; i < 9; i++) {
			if (solution[i] < 1) { 
				solution[i] = ISEMPTY;
			}
		}	
		
		return solution; 
	}   

	/**
	 * M�thode qui analyse les possibilit�s gagnante GLOBAL � profondeur +2
	 * 
	 * @return int[] qui indique combien de combinaison gagnante sont possible pour chaque case GLOBAL i sinon ISEMPTY
	 */
	private int[] analystDoubleAnticipGlobal(){
		int solution[] = new int[9];

		for (int i = 0; i < 9; i++ ) {
			if ( i == 0 ){ 
				if (sC_Global[1] == VIDE && sC_Global[2] == VIDE ) {solution[0] += 1;}
				if (sC_Global[4] == VIDE && sC_Global[8] == VIDE ) {solution[0] += 1;}
				if (sC_Global[3] == VIDE && sC_Global[6] == VIDE ) {solution[0] += 1;}
			}		
			if ( i == 1 ){
				if (sC_Global[0] == VIDE && sC_Global[2] == VIDE) {solution[1] += 1;}
				if (sC_Global[4] == VIDE && sC_Global[7] == VIDE) {solution[1] += 1;} 
			}		
			if ( i == 2 ){
				if (sC_Global[1] == VIDE && sC_Global[0] == VIDE) {solution[2] += 1;}
				if (sC_Global[5] == VIDE && sC_Global[8] == VIDE) {solution[2] += 1;}
				if (sC_Global[4] == VIDE && sC_Global[6] == VIDE) {solution[2] += 1;} 
			}		
			if ( i == 3 ){ 
				if (sC_Global[0] == VIDE && sC_Global[6] == VIDE) {solution[3] += 1;} 
				if (sC_Global[4] == VIDE && sC_Global[5] == VIDE) {solution[3] += 1;} 
			}		
			if ( i == 4 ){ 
				if (sC_Global[0] == VIDE && sC_Global[7] == VIDE) {solution[4] += 1;} 
				if (sC_Global[2] == VIDE && sC_Global[6] == VIDE) {solution[4] += 1;} 
				if (sC_Global[1] == VIDE && sC_Global[7] == VIDE) {solution[4] += 1;} 
				if (sC_Global[3] == VIDE && sC_Global[5] == VIDE) {solution[4] += 1;}  
			}		
			if ( i == 5 ){ 
				if (sC_Global[2] == VIDE && sC_Global[7] == VIDE) {solution[5] += 1;}  
				if (sC_Global[3] == VIDE && sC_Global[4] == VIDE) {solution[5] += 1;}  
			}		
			if ( i == 6 ){ 
				if (sC_Global[7] == VIDE && sC_Global[8] == VIDE) {solution[6] += 1;} 
				if (sC_Global[0] == VIDE && sC_Global[3] == VIDE) {solution[6] += 1;} 
				if (sC_Global[4] == VIDE && sC_Global[2] == VIDE) {solution[6] += 1;}  
			}
			if ( i == 7 ){ 
				if (sC_Global[6] == VIDE && sC_Global[8] == VIDE) {solution[7] += 1;} 
				if (sC_Global[4] == VIDE && sC_Global[1] == VIDE) {solution[7] += 1;} 
			}
			if ( i == 8 ){
				if (sC_Global[6] == VIDE && sC_Global[7] == VIDE) {solution[8] += 1;} 
				if (sC_Global[2] == VIDE && sC_Global[5] == VIDE) {solution[8] += 1;} 
				if (sC_Global[0] == VIDE && sC_Global[4] == VIDE) {solution[8] += 1;} 
			}
		}
		
		for (int i = 0; i < 9; i++) {
			if (solution[i] < 1) { 
				solution[i] = ISEMPTY;
			}
		}	
		
		return solution; 
	}  

	/**
	 * M�thode qui permet de savoir combien de case LOCAL ont �t� jouer sur une case GLOBAL pour PLAYER
	 * 
	 * @param myCase qui est la case GLOBAL
	 * @param PLAYER qui est le joueur concern�
	 * @return un int repr�sentant combien de case � jouer le joueur
	 */
	private int[] howCasePlayed(int PLAYER) {
		int count[] = new int[9];
		int global[][];
		
		for (int i = 0; i < 9; i++) {
			global = returnGoodCases(i);
			for (int j = 0; j < 9; j++) {
				if (global[j][JOUEUR] == PLAYER) { count[i]++; }	
			}
			
		}

		return count;
	}

	/**
	 * M�thode qui identifie � qui appartiens chaque case LOCAL
	 * 
	 * @param myCase qui est la case GLOBAL
	 * @return int[] ou i prend la valeur du joueur (0 = VIDE, 1 = MOI, 2 = ADVERSAIRE)
	 */
	private int[] statusCase(int myCase[][]) {
		int tab[] = new int[9]; 						

		for (int i = 0; i < 9; i++ ) {
			if (myCase[i][JOUEUR] == VIDE) { tab[i] = VIDE; }
			if (myCase[i][JOUEUR] == MOI) { tab[i] = MOI; }
			if (myCase[i][JOUEUR] == ADVERSAIRE) { tab[i] = ADVERSAIRE; }
		}

		return tab;    	
	}    

	// ========================================================================================//
	//                                  Conversion
	// ========================================================================================//    

	/**
	 * Retourne une cases g�n�ral suivant un index
	 * 
	 * @param index qui repr�sente le tableau que l'ont veut recup�r� ( index 0 = tableau 1 ...)
	 * @return le tableaux demander
	 */
	private int[][] returnGoodCases(int index){
		if (index == 0) {return cases1 ;}
		if (index == 1) {return cases2 ;}
		if (index == 2) {return cases3 ;}
		if (index == 3) {return cases4 ;}
		if (index == 4) {return cases5 ;}
		if (index == 5) {return cases6 ;}
		if (index == 6) {return cases7 ;}
		if (index == 7) {return cases8 ;}
		if (index == 8) {return cases9 ;}
		return null;
	} 

	/**
	 * m�thode qui retourne la cases GLOBAL et la case LOCAL correspondant au coordonn�es fournis
	 * 
	 * @param row qui repr�sente la coordonn�e row de la cases g�n�ral recherch�
	 * @param col qui repr�sente la coordonn�e row de la cases g�n�ral recherch�
	 * @return un tableau qui indique en index 0 : la cases g�n�ral et en index 1: la case
	 */
	private int[] converXYToCase(int row, int col){
		int[] tab = new int[2];

		if ( row >= 0 && row <= 2 ) { //case 1/2/3
			if ( col >= 0 && col <= 2 ) {tab[GLOBAL] = 0; tab[LOCAL] = returnIndexOfCase(cases1, row, col); }
			if ( col >= 3 && col <= 5 ) {tab[GLOBAL] = 1; tab[LOCAL] = returnIndexOfCase(cases2, row, col); }
			if ( col >= 6 && col <= 8 ) {tab[GLOBAL] = 2; tab[LOCAL] = returnIndexOfCase(cases3, row, col); }
		} 
		if ( row >= 3 && row <= 5 ) {
			if ( col >= 0 && col <= 2 ) {tab[GLOBAL] = 3; tab[LOCAL] = returnIndexOfCase(cases4, row, col); }
			if ( col >= 3 && col <= 5 ) {tab[GLOBAL] = 4; tab[LOCAL] = returnIndexOfCase(cases5, row, col); }
			if ( col >= 6 && col <= 8 ) {tab[GLOBAL] = 5; tab[LOCAL] = returnIndexOfCase(cases6, row, col); }
		} 
		if ( row >= 6 && row <= 8 ) {
			if ( col >= 0 && col <= 2 ) {tab[GLOBAL] = 6; tab[LOCAL] = returnIndexOfCase(cases7, row, col); }
			if ( col >= 3 && col <= 5 ) {tab[GLOBAL] = 7; tab[LOCAL] = returnIndexOfCase(cases8, row, col); }
			if ( col >= 6 && col <= 8 ) {tab[GLOBAL] = 8; tab[LOCAL] = returnIndexOfCase(cases9, row, col); }
		}      	
		return tab;
	}       

	/**
	 * Retourne l'index d'une case LOCAL 
	 * 
	 * @param Mycases qui repr�sente la cases LOCAL
	 * @param row la coordon�e row de la case � retourner
	 * @param col la coordon�e row de la case � retourner
	 * @return int l'index recherch�
	 */
	private int returnIndexOfCase(int[][] Mycases, int row, int col){
		int j = -1;

		for (int i =0; i < Mycases.length; i++) {
			if ( Mycases[i][COL] == col && Mycases[i][ROW] == row) { j = i; break;}
		}           
		return j;
	}

	// ========================================================================================//
	//                 		Methode D'initialisation et utilitaire
	// ========================================================================================//        

	/**
	 * Initialise les valeur des tableaux cases en fonction de leur emplacements
	 */
	public Resolve(){
		cases[0][COL] = 0; cases[0][ROW] = 0; cases[0][JOUEUR] = VIDE;
		cases[1][COL] = 1; cases[1][ROW] = 0; cases[1][JOUEUR] = VIDE;
		cases[2][COL] = 2; cases[2][ROW] = 0; cases[2][JOUEUR] = VIDE;
		cases[3][COL] = 0; cases[3][ROW] = 1; cases[3][JOUEUR] = VIDE;
		cases[4][COL] = 1; cases[4][ROW] = 1; cases[4][JOUEUR] = VIDE;
		cases[5][COL] = 2; cases[5][ROW] = 1; cases[5][JOUEUR] = VIDE;
		cases[6][COL] = 0; cases[6][ROW] = 2; cases[6][JOUEUR] = VIDE;
		cases[7][COL] = 1; cases[7][ROW] = 2; cases[7][JOUEUR] = VIDE;
		cases[8][COL] = 2; cases[8][ROW] = 2; cases[8][JOUEUR] = VIDE;

		cases1 = getTab(0,0);
		cases2 = getTab(3,0);
		cases3 = getTab(6,0); 
		cases4 = getTab(0,3); 
		cases5 = getTab(3,3); 
		cases6 = getTab(6,3); 
		cases7 = getTab(0,6); 
		cases8 = getTab(3,6); 
		cases9 = getTab(6,6); 
	}

	/**
	 * Met � jour la r�f�rence casesActuel
	 * 
	 * @param row une coordonn�e row disponible pour le prochain tour
	 * @param col une coordon�e col disponible pour le prochain tour
	 */
	public void getTrueCases(int row, int col){
		int ctc[] = converXYToCase(row, col);
		casesActuel = returnGoodCases(ctc[GLOBAL]);        	
	}

	/**
	 * Permet de cr�er les cases avec les coordonn�es correcte correspondante a leur position
	 * 
	 * @param moreCol qui est la valeur � ajouter au col
	 * @param moreRow qui est la valeur � ajouter au row
	 * @return int[][] repr�sentant une des cases de jeu
	 */
	private int[][] getTab(int moreCol, int moreRow){
		int tab[][] = new int[9][3];

		for (int i = 0; i < 9; i++ ){
			tab[i][COL] = cases[i][COL] + moreCol;
			tab[i][ROW] = cases[i][ROW] + moreRow;
			tab[i][JOUEUR] = VIDE ;
		}
		return tab;
	}  
	
	/**
	 * methode qui modifie le status d'une case jouer
	 * 
	 * @param myCase qui repr�sente la cases g�n�ral jouer
	 * @param index qui repr�sente la case
	 * @param player qui repr�sente le joueur qui as jouer cette case
	 */
	private void asPlayedCase(int myCase[][], int index, int player){
		myCase[index][JOUEUR] = player;
	}
}