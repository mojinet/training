import java.util.*;
import java.io.*;
import java.math.*;

class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int factoryCount = in.nextInt();    // the5 number of factories
        int linkCount = in.nextInt();       // the number of links between factories
        
        // tableau des liens
        int link[][] = new int[linkCount][3];
        int linkA[][] = new int[factoryCount][factoryCount];
        // tableau des factory
        int factory[][] = new int [factoryCount][6];
        // liste des trouppes
        ArrayList<int[]> troop = new ArrayList<int[]>();
        // liste des bombes ( 2 par joueurs)
        ArrayList<int[]> bomb = new ArrayList<int[]>();
        
        for (int i = 0; i < linkCount; i++) {
            link[i][0] = in.nextInt(); // factory 1
            link[i][1] = in.nextInt(); // factory 2
            link[i][2] = in.nextInt(); // distance
            
    		linkA[link[i][0]][link[i][1]] = link[i][2];
    		linkA[link[i][1]][link[i][0]] = link[i][2];
        }

        // premier tour ?
        boolean first = true;
        Resolve resolve = null;
        
        // game loop
        while (true) {
            int entityCount = in.nextInt(); // the number of entities (e.g. factories and troops)
            for (int i = 0; i < entityCount; i++) {
                int entityId = in.nextInt();
                String entityType = in.next();
                int arg1 = in.nextInt();
                int arg2 = in.nextInt();
                int arg3 = in.nextInt();
                int arg4 = in.nextInt();
                int arg5 = in.nextInt();
                
                // pour chaque factory
                if ( entityType.equals("FACTORY") ){
                    factory[entityId][0] = entityId; // id
                    factory[entityId][1] = arg1;     // joueur qui possï¿½de l'usine : 1 pour vous, -1 pour l'adversaire et 0 si neutre
                    factory[entityId][2] = arg2;     // nombre de cyborg dans l'usine
                    factory[entityId][3] = arg3;     // production de l'usine (entre 0 et 3)
                    factory[entityId][4] = arg4;     // nombre de tours restant avant que l'usine ne produise ï¿½ nouveau (0 signifie que l'usine produit normalement)
                    factory[entityId][5] = arg5;     // inutilisï¿½...
                }
                // pour chaque troupes
                if ( entityType.equals("TROOP") ){
                    int troopTmp[] = new int[6];
                    troopTmp[0] = entityId; // id
                    troopTmp[1] = arg1;     // joueur qui possï¿½de la troupe : 1 pour vous, -1 pour l'adversaire
                    troopTmp[2] = arg2;     // identifiant de l'usine de dï¿½part
                    troopTmp[3] = arg3;     // identifiant de l'usine d'arrivï¿½e
                    troopTmp[4] = arg4;     // nombre de cyborgs au sein de la troupe (entier strictement positif)
                    troopTmp[5] = arg5;     // nombre de tours avant d'arriver ï¿½ destination (entier strictement positif)
                    
                    troop.add(troopTmp);
                }
                // pour chaque bombe
                if ( entityType.equals("BOMB") ){
                    int bombTmp[] = new int [6];
                    bombTmp[0] = entityId; // id
                    bombTmp[1] = arg1;     // joueur qui a envoyï¿½ la bombe : 1 pour vous, -1 pour l'adversaire
                    bombTmp[2] = arg2;     // identifiant de l'usine de dï¿½part
                    bombTmp[3] = arg3;     // identifiant de l'usine d'arrivï¿½e si c'est votre bombe, -1 sinon
                    bombTmp[4] = arg4;     // nombre de tours avant d'arriver ï¿½ destination (entier strictement positif) si c'est votre bombe, -1 sinon
                    bombTmp[5] = arg5;     // inutilisï¿½...
                    
                    bomb.add(bombTmp);
                }
            }
            
            if ( first ) { resolve = new Resolve(linkA, factory, troop, bomb); first = false; } 
            else { resolve.refreshFactory(factory, troop, bomb); }
        }
    }
}

class Resolve{
    
    //*******************************************************************//
    //                             CHAMPS
    //*******************************************************************//  
    
	// La distance entre les factory :
    	// 0 -> factory 1 
    	// 1 -> factory 2 
    	// 2 -> distance (en nombre de tour)
    private int link[][];
    
    // les factory : 
        // 0 -> id 
        // 1 -> joueur qui possede l'usine : -1 = ennemie, 0 = neutre, 1 = moi
        // 2 -> nombre de cyborg dans l'usine
        // 3 -> production de l'usine (entre 0 et 3)
        // 4 -> nombre de tours restant avant que l'usine ne produise ï¿½ nouveau (0 signifie que l'usine produit normalement)
        // 5 -> ???
    private int factory[][];
    
    // liste des trouppes
        // 0 -> id
        // 1 -> joueur qui possï¿½de la troupe : 1 pour vous, -1 pour l'adversaire
        // 2 -> identifiant de l'usine de dï¿½part
        // 3 -> identifiant de l'usine d'arrivï¿½e
        // 4 -> nombre de cyborgs au sein de la troupe (entier strictement positif)
        // 5 -> nombre de tours avant d'arriver ï¿½ destination (entier strictement positif)
    private ArrayList<int[]> troop = new ArrayList<int[]>();
    
    // liste des bombes ( 2 par joueurs)
		// 0 -> id                                                                                                       
		// 1 -> joueur qui a envoyï¿½ la bombe : 1 pour vous, -1 pour l'adversaire                                         
		// 2 -> identifiant de l'usine de dï¿½part                                                                         
		// 3 -> identifiant de l'usine d'arrivï¿½e si c'est votre bombe, -1 sinon                                          
		// 4 -> nombre de tours avant d'arriver ï¿½ destination (entier strictement positif) si c'est votre bombe, -1 sinon
		// 5 -> ???                                                                                            
    private ArrayList<int[]> bomb = new ArrayList<int[]>();
        
    // tableau des differente rï¿½ponse que je vais envoyer
    private ArrayList<String> response = new ArrayList<String>();
    
    // indique si j'ai gagner la partie
    boolean notWin = false;
    
    // infos diverse
    int BASE = -1 ; 		 				// ma base de dï¿½part
    int BASE_ENNEMIE = -1 ; 		 		// la base de dï¿½part de l'ennemie
    int fatBaseEnnemie = -1; 				// la plus grosse base ennemie
    int status[];                           // met ï¿½ jour le nombre de cyborg libre : -1 = non possï¿½dï¿½
    int bombR = 2;							// reserve de bombes
    int firstBomb = -1;						// destination de la premiere bomb
    long debut;								// temps de reponse
    
    //*******************************************************************//
    //                        Methode public
    //*******************************************************************//  
    
    public Resolve(int link[][],int factory[][], ArrayList<int[]> troop, ArrayList<int[]> bomb){
    	debut = System.currentTimeMillis();
    	
        this.link = link;
        this.factory = factory;
		this.troop = troop;
		this.bomb = bomb;
        
        // ma base de depart
    	for ( int i = 0; i < factory.length; i++ ) {
    		if (factory[i][1] == 1) { this.BASE = i; }
    	}
        // la base ennemie de depart
    	for ( int i = 0; i < factory.length; i++ ) {
    		if (factory[i][1] == -1) { this.BASE_ENNEMIE = i; }
    	}
    	
    	// initialise la taille des tableaux
    	status = new int[this.factory.length];
    	
    	// envois d'une premiere reponse
    	getResponse();
    }
    
    public void refreshFactory(int[][] factory, ArrayList<int[]> troop, ArrayList<int[]> bomb) {
    	debut = System.currentTimeMillis();
    	
		this.factory = factory;	
		this.troop = troop;
		this.bomb = bomb;
		
    	// envois d'une reponse
    	getResponse();
	}

	public void getResponse(){

		String rep = "";
		
            //*******************************************************************//
            //      					INFO 
            //*******************************************************************//
			int countMe = 0;
			int countEnn = 0;
			String statusParty = "";
			
			// pour tout les cyborg dans les usine
			for (int i = 0; i < factory.length; i++) {
				if (factory[i][1] == -1) { countEnn += factory[i][2]; }
				if (factory[i][1] == 1) { countMe += factory[i][2];}
			}
			
			// pour tout les cyborg en route
			for (int i = 0; i < troop.size(); i++) {
				if (troop.get(i)[1] == -1) { countEnn += troop.get(i)[4]; }
				if (troop.get(i)[1] == 1) { countMe += troop.get(i)[4]; }
			}			
			
			if (countMe > countEnn) {
				statusParty = ":) step " + statusTarget;
			} else if (countMe < countEnn) {
				statusParty = ":( step " + statusTarget;
			} else {
				statusParty = ":/ step " + statusTarget;
			}
			
            //*******************************************************************//
            //      					Analyse 
            //*******************************************************************//	
			boolean alreadyWin = true;			
			for (int i = 0; i < factory.length; i++) { if (factory[i][1] != 1) { alreadyWin = false; } }
			
			if (! alreadyWin) {
	            response.clear();
	            trueCountCyborg();
	    		target();
	    		bombIt();
	    		analyseV3();				
			} else {
				System.out.println("WAIT");
			}

    		
            //*******************************************************************//
            //      Vï¿½rification de l'etat du jeu et envois de la reponse
            //*******************************************************************//
            
            notWin = false;
            
            // tant qu'il reste des usine qui ne m'appartiennent pas
            for (int i = 0; i < factory.length; i++){ if (factory[i][1] != 1){ notWin = true; } }
            
            // envois de la rï¿½ponse
            if ( notWin ) {
    			
            	if ( response.size() > 0 ) {
            		for ( int i = 0; i < response.size(); i++ ) { 
            			if (response.size() == i+1) {
            				rep += response.get(i);
            			} else {
            				rep += response.get(i) + ";";
            			}           			 
            		}
            	} else { rep = "WAIT"; }          	
            } else { rep = "WAIT"; }   
			
            System.out.println(rep + ";MSG <'o'> : " + ((long) System.currentTimeMillis()-debut) + " ms " + statusParty  );
            

    }
    
    
    //*******************************************************************//
    //                         Comportement par defaut
    //*******************************************************************//   
    	
    /**
     *  Gestion des bombes : envois une premiere des le debut de la partie, puis des qu'il possede une deuxieme base
     *  TODO envoye la deuxieme quand une usine ennemie a 3 en production
     */
    private void bombIt() {
    	String rep = "";
    	fatBaseEnnemie = -1;
    	int tmp = -1;
    	
    	// Definis la plus grosse base ennemie   	
    	for ( int i = 0; i < factory.length; i++ ) {   		
    		// considere l'usine comportant le plus de cyborg ennemie
    		if ( factory[i][1] == -1 ) {
    			if ( factory[i][2] > tmp ) { fatBaseEnnemie = factory[i][0]; }   				
    		}
    	} 	   	
    	
    	// lance la premiere bombe
    	if (bombR == 2) {
    		rep = "BOMB " + BASE + " " + fatBaseEnnemie;
    		response.add(rep);
    		firstBomb = fatBaseEnnemie;
    		bombR--;
    		System.err.println("bomb==2");
    	}
    	
    	// lance la deuxieme bombe
    	if (bombR == 1) {
    		// initialise valeur
        	tmp = -1;
        	int cible = -1;
        	
        	for ( int i = 0; i < factory.length; i++ ) {   		
        		// considere l'usine comportant le plus de cyborg ennemie
        		if ( factory[i][1] == -1 && factory[i][0] != firstBomb) {
        			if ( factory[i][2] > tmp ) { cible = factory[i][0]; } 
        				if (factory[BASE][1] != 1 ) {
        					for (int j=0; j < factory.length; j++) {
        						if(factory[j][1] == 1) {
                        			rep = "BOMB " + j + " " + cible;
                        			response.add(rep);
                        			bombR--;	
                        			System.err.println("bomb==1"); 	
        						}
        					}
        				} else {
                			rep = "BOMB " + BASE + " " + cible;
                			response.add(rep);
                			bombR--;	
                			System.err.println("bomb==1");        					
        				}

        		}
        	}        	
    	}    	
    }   
        
    //*******************************************************************//
    //                         Analyse du jeux 
    //*******************************************************************// 
	// tableau des cibles actuel
	private int target[];
	// etat du jeux (1 : conquerie les plus proche, 2 : conquerie equi distance, 3: expansion)
	private int statusTarget = 0;
	// nombre de cyborg par usine en tenant compte les troop en deplacement
	private int trueCountCyborg[];
	// index actuel troop
	int troopIndex = 0;
    
    /**
     * Definis un tableau de cible, des usines les plus proches au plus eloignï¿½
     */
    private void target() {
    	this.target = new int[factory.length];
    	boolean getBase = true;
    	boolean getEqual = true;
    	
    	// prend les usines plus proche de moi que de l'ennemie
    	for (int i =0; i < factory.length; i++) {
			if (link[BASE][i] < link[BASE_ENNEMIE][i] && factory[i][1] != 1) {
				this.target[i] = 1;
				getBase = false;
				statusTarget = 1;
				
			// si je la possede deja la marque -1	
			} else if (link[BASE][i] < link[BASE_ENNEMIE][i] && factory[i][1] == 1) {
				this.target[i] = -1;
			}
		}
    	
    	// si j'ai deja les bases je m'etend au usines qui sont a equa-distance
    	if (getBase) {
        	for (int i =0; i < factory.length; i++) {
    			if (link[BASE][i] == link[BASE_ENNEMIE][i] && factory[i][1] != 1) {
    				this.target[i] = 2;
    				getEqual = false;
    				statusTarget = 2;
    				
    				// si je la possede deja la marque -2	
    			} else if (link[BASE][i] == link[BASE_ENNEMIE][i] && factory[i][1] == 1) {
    				this.target[i] = -2;
    			}
    		}    		
    	}
    	
    	// si j'ai les bases a equa distance prend les autres de la plus proche a la plus eloigner
    	int tmp = 21;
    	int tmpIndex = -1;
    	if (getEqual && getBase) {
        	for (int i =0; i < factory.length; i++) {
    			if (link[BASE][i] > link[BASE_ENNEMIE][i] && factory[i][1] != 1 && this.target[i] == 0) {
    				if (link[BASE][i] < tmp) { 
    					tmp = link[BASE][i]; 
    					tmpIndex = i; 
    				} 
    				
    			// si je la possede deja la marque -3	
    			} else if (link[BASE][i] > link[BASE_ENNEMIE][i] && factory[i][1] == 1) {
    				this.target[i] = -3;
    			}
    		} 
        	if (tmpIndex == -1) {
        		System.out.println("WAIT");
        	} else {
    			this.target[tmpIndex] = 3;
    			statusTarget = 3;        		
        	}
    	} 
    	
    	//infos
//    	for (int i = 0; i < target.length; i++) {
//    		if (target[i] == 1) {
//            	System.err.println(i + " -> cible [base] [ trueCount :" + trueCountCyborg[i] + "]");    			
//    		}    
//    		if (target[i] == 2) {
//            	System.err.println(i + " -> cible [base+] [ trueCount :" + trueCountCyborg[i] + "]");    			
//    		}   
//    		if (target[i] == 3) {
//            	System.err.println(i + " -> cible [base++] [ trueCount :" + trueCountCyborg[i] + "]");    			
//    		}   
//    		if (target[i] == -1) {
//            	System.err.println(i + " -> [base] [actuel : " + factory[i][2] + "] [ trueCount :" + trueCountCyborg[i] + "]");    			
//    		}   
//    		if (target[i] == -2) {
//            	System.err.println(i + " -> [base+] [actuel : " + factory[i][2] + "] [ trueCount :" + trueCountCyborg[i] + "]");    			
//    		}   
//    		if (target[i] == -3) {
//            	System.err.println(i + " -> [base++] [actuel : " + factory[i][2] + "] [ trueCount :" + trueCountCyborg[i] + "]");    			
//    		}   
//    	}
    }

    /**
     * Compte le nombre de cyborg dans l'usine et prend en compte les troupes envoye
     */
    private void trueCountCyborg() {
    	this.trueCountCyborg = new int[factory.length];  
    	
    	//comptabilise les unite presente dans les usines
    	for (int i = 0; i < factory.length; i++) {
    		if (factory[i][1] != 1) {
    			this.trueCountCyborg[i] -= factory[i][2];	
    		} else {
    			this.trueCountCyborg[i] += factory[i][2];	
    		}   
    	}
    	
    	//comptabilise avec les troop envoye
    	for (int i = troopIndex; i < troop.size(); i++) {
    		int tr[] = troop.get(i);
    		troopIndex++;
    		
    		if (tr[1] == -1) {
    			if (factory[tr[3]][1] == 0 ) {
        			this.trueCountCyborg[tr[3]] += tr[4]; // dest

    			} else {
        			this.trueCountCyborg[tr[3]] -= tr[4]; // dest  				
    			}	
    		} else {    			
        		this.trueCountCyborg[tr[3]] += tr[4]; // dest      			
    		}
    	}
    }

    private void analyseV3() {
    	
        //*******************************************************************//
        //      	Compte mes unités à disposition dans mes factory
        //*******************************************************************//
    	
    	// compte mes unites a disposition 
    	int countUnit[] = new int[factory.length];
    	int countTotal = 0;
    	int countFactory = 0;
    	int countTarget1 = 0;
    	int countTarget2 = 0;
    	int countTarget3 = 0;
    	
    	System.err.println("*****usine disponible et nombre cyborg*****");
    	for (int i =0; i < factory.length; i++) {
    		
    		// compte les unités de toute les bases
    		countUnit[i] = factory[i][2];
    		
    		// recupere le nombre total de cyborg a disposition
    		if (factory[i][1] == 1) {
    			countTotal += factory[i][2];
    			countFactory++;
    			System.err.println(i + " -> possede " + countUnit[i] + " cyborg" + " trueCount : " + trueCountCyborg[i]);
    		}
    		
    		// recupere le nombre de base principal qu'il reste a prendre
    		//TODO remetre target[i] == 1
    		if (target[i] > 0 ) { countTarget1++; }
    		if (target[i] == 2 ) { countTarget2++; }
    		if (target[i] == 3 ) { countTarget3++; }
    	}
 
        //*******************************************************************//
        //      	trie les bases principal par ordre croissant
        //*******************************************************************//
    	
    	// tableau cost[0] = ID de l'usine base au cout le plus faible
    	int cost[] = new int[countTarget1];
    	// TODO pour equidistance...
//    	int costTarget2[] = new int[countTarget2];
//    	int costTarget3[] = new int[countTarget3];
    	
    	// tableau des usine deja trié
    	int useCost[] = new int[factory.length];    	
    	int countFactoryTmp = 0;
    	int tmpCost = 0;
    	
    	// Tri de plus petite au plus grande bases
    	while (tmpCost !=30) {
    		// pour toute les factory
    		for (int i = 0; i < factory.length; i++) {
	    		// si l'usine n'est pas à moi + qu'elle fait partie des bases principal + que le cout est inférieur à tmpCost + qu'elle n'a pas été utiliser
    			//TODO remetre target[i] == 1
	    		if (factory[i][1] != 1 && target[i] > 0 && factory[i][2] <= tmpCost && useCost[i] != 1) {
	    			
	    			tmpCost = factory[i][2];
	    			cost[countFactoryTmp] = factory[i][0];
	    			useCost[i] = 1;
	    			countFactoryTmp++;
	    		}
    		}
    		tmpCost++;
    	}
    	
    	// debug
    	System.err.println("*****cible par odre croissant*****");
    	for (int i = 0; i < cost.length; i++) {
    		System.err.println(cost[i] + " -> possede " + factory[cost[i]][2] + " cyborg " + " et trueCOunt : " + trueCountCyborg[cost[i]]);	
    	}
    	
        //*******************************************************************//
        //      				PHASE 0 : defense
        //*******************************************************************//
    	
    	System.err.println("*****PHASE repartition A : defense base acquise ******");
    	
    	int tmpIndex = -1 ;
    	// si une usine est attaqué
    	for (int i = 0; i < factory.length; i++) {
    		// si l'usine m'appartiens et qu'elle possede au moins 1 cyborg disponible (en trueCount)
    		if (factory[i][1] == 1 && trueCountCyborg[i] > 0) {
    			// pour toute les factory
    			for (int j = 0; j < factory.length; j++) {
    				// si une usine de base deja possedé et qu'elle est attaqué
    				if (trueCountCyborg[j] < 0 && target[j] == -1) {    					
    	    			if (j != -1 && j != i) {
    	    				int trueAbs = Math.abs(trueCountCyborg[j]);
    	    				int countSend = 0;
    	    				
    	    				// si j'ai au autant ou plus que l'attaque
    	    				if (trueAbs <= trueCountCyborg[i]) { countSend = trueAbs;} 
    	    				
    	    				// si j'ai moins que l'attaque : j'envois ce que j'ai de dispo
    	    				else { countSend = trueCountCyborg[i]; } 
    	    				
	                    	System.err.println("MOVE " + i + " " + j + " " + countSend);
	            			
	                    	response.add("MOVE " + i + " " + j + " " + countSend);		
	                    	countUnit[i] -= countSend;
	                    	countUnit[j] += countSend;
	                    	countTotal -= countSend; 
	                    	trueCountCyborg[i] += countSend; 
	                    	trueCountCyborg[j] += countSend;
    	    			}	   				
    				}    			
    			}
    		}
    	}
    	
        //*******************************************************************//
        //      			PHASE 1 : base plus proche
        //*******************************************************************//
    	
    	// TODO remetre == 1
    	if (statusTarget > 0) {
    		System.err.println("*****PHASE 1 ******");
    		// pour toute les usine disponible
    		for (int i = 0; i < factory.length; i++) {
    			// si c'est la mienne et que j'ai des cyborg disponible a l'attaque
    			if ( factory[i][1] == 1 && trueCountCyborg[i] > 0) {
    				// countUnit représente maintenant les unités non indispensable a la defense
    				countUnit[i] = trueCountCyborg[i];
    				// tant qu'il me reste des unités disponible dans cette base
    				while ( countUnit[i] > 0) {
    					// pour toute les factory proche a capturer
    					for (int k = 0; k < cost.length; k++) {
    						
    						// si c'est une cible, que je dispose de plus d'unités que l'usine cible
    						// TODO remetre target[cost[k]] == 1
    						if (target[cost[k]] > 0 && countUnit[i] > countUnit[cost[k]] && trueCountCyborg[cost[k]] <= 0 && i != cost[k]) {
    							int unit = (factory[cost[k]][2] + 1);
    							//debug
    							System.err.println("MOVE " + i + " " + cost[k] + " " + unit);
    							
    							response.add("MOVE " + i + " " + cost[k] + " " + unit);
    							countUnit[i] -= unit;
    							countUnit[cost[k]] -= unit;
    							countTotal -= unit;
    							trueCountCyborg[cost[k]] += unit;
    							
    						// si c'est une cible, que je dispose d'autant ou moins d'unites que la cible
    						// TODO remetre target[cost[k]] == 1
    						} else if (target[cost[k]] > 0 && countUnit[i] > 0 && trueCountCyborg[cost[k]] <= 0 && i != cost[k])  {
    							int unit = countUnit[i];
    							//debug
    							System.err.println("MOVE " + i + " " + cost[k] + " " + unit);
    							
    							response.add("MOVE " + i + " " + cost[k] + " " + unit);
    							countUnit[i] -= unit;
    							countUnit[cost[k]] -= unit;
    							countTotal -= unit; 
    							trueCountCyborg[cost[k]] += unit;
    							
    					        //*******************************************************************//
    					        //      			si j'ai un surplus d'unité
    					        //*******************************************************************//	
    							
    						} else if ( k == cost.length -1 ) {	
    							System.err.println("*****PHASE DE SURPLUS A*****");
    							for (int m = 0; m < factory.length; m++) {
    								if (countUnit[i] == 0) { break;}
    								if (factory[m][1] != 1 && trueCountCyborg[m] == 0 && i != m) {
    	    							//debug
    	    							System.err.println("MOVE " + i + " " + m + " " + 1);
    	    							
    	    							response.add("MOVE " + i + " " + m + " " + 1);
    	    							countUnit[i] -= 1;
    	    							countUnit[m] -= 1;
    	    							countTotal -= 1; 
    	    							trueCountCyborg[m] += 1;    									
    								}
    							}
    							
    							// usine peu proteger si mon usine est deja upgrader 
    							System.err.println("*****PHASE DE SURPLUS B*****");
    							for (int m = 0; m < factory.length; m++) {
    								if (countUnit[i] == 0 || factory[i][3] != 3) { break;}   
    									for (int n =1; n < 20; n++) {
    	    								if (factory[m][1] != 1 && trueCountCyborg[m] > -n  && factory[i][3] == 3) {
    	    									int absTrue = Math.abs(trueCountCyborg[m]);
    	    									int unit = countUnit[i];
    	    									if (unit >= absTrue) {
    	        	    							//debug
    	        	    							System.err.println("MOVE " + i + " " + m + " " + absTrue);
    	        	    							
    	        	    							response.add("MOVE " + i + " " + m + " " + absTrue);
    	        	    							countUnit[i] -= absTrue;
    	        	    							countUnit[m] -= absTrue;
    	        	    							countTotal -= absTrue; 
    	        	    							trueCountCyborg[m] += absTrue;     										
    	    									} else {
    	    										continue;
    	    									}
    	   									
    	    								}    										
    									}

    							} 
    							
    							System.err.println(i + " -> il me restais : " + countUnit[i] + " cyborg libre");
    							countUnit[i] = 0;
    						}   						
    					}
    				}
    			}
    		}
    	}
    	
//        //*******************************************************************//
//        //      			PHASE 2 : + increase 
//        //*******************************************************************//
    	
    	if (statusTarget > 0) {
        	System.err.println("*****PHASE 2 repartition increase ******");
        	// donne l'exedant à une base qui a besoin d'etre upgrader
        	for (int i =0; i <factory.length; i++) {  		
        		if (factory[i][1] == 1 && trueCountCyborg[i] > 0 && factory[i][3] == 3) {
        			for (int j =0; j < factory.length; j++) {
        				if (factory[j][1] == 1 && factory[j][3] < 3 && trueCountCyborg[j] < 10 && i != j) {
        					int unit = trueCountCyborg[i];
        					response.add("MOVE " + i + " " + j + " " + unit);
        					System.err.println("MOVE " + i + " " + j + " " + unit);
        					countUnit[i] -= unit;
        					countUnit[j] += unit;
        				}
        			}
        		}
        	}
        	
        	System.err.println("*****PHASE 2 increase ******");
        	// increase si < 10 trueCOunt
        	for (int i = 0; i < factory.length; i++) { 
        		// si une usine à assez de cyborg a disposition
        		if (factory[i][1] == 1 && trueCountCyborg[i] > 10 && factory[i][3] < 3) {
        			response.add("INC " + i);
        			System.err.println("INC " + i);
        		}
        	}    		
    	}
    	
    }
//    	
//        //*******************************************************************//
//        //      			PHASE 3 : 
//        //*******************************************************************//
//    	
//    	if (statusTarget == 3) {
//    		System.err.println("*****PHASE 3******");
//    	}  
 
    
}
