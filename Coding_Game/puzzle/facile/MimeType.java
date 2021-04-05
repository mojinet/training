import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // Number of elements which make up the association table.
        int Q = in.nextInt(); // Number Q of file names to be analyzed.
        String[][] extension = new String[N][2]; 
        final int EXTENSION = 0;
        final int TYPE = 1;

        for (int i = 0; i < N; i++) {
            String EXT = in.next(); // file extension
            String MT = in.next(); // MIME type.
            // si l'extension ne fait pas plus de 10 caractere et le mime 50
            if (EXT.length() <= 10 && MT.length() <= 50){
                extension[i][EXTENSION] = EXT.toLowerCase();
                extension[i][TYPE] = MT;
            }

        }
        in.nextLine();

        int lastIndex;
        String fileExtension;

        for (int i = 0; i < Q; i++) {
            String FNAME = in.nextLine(); // One file name per line.
            // si le nom du fichier ne depasse pas 256 caracteres
            if (FNAME.length() <= 256){
                if (FNAME.contains(".")){
                    // on recupere l'index du dernier "."
                    lastIndex = FNAME.lastIndexOf(".");
                    // on coupe le nom du fichier et on recupere seulement l'extension
                    fileExtension = FNAME.substring(lastIndex+1).toLowerCase();
                    String response = "UNKNOWN";
                    // on recherche si on trouve l'extension dans notre tableau
                    for (int j = 0; j < N; j++) {
                        if (fileExtension.equals(extension[j][EXTENSION])){
                            response = extension[j][TYPE];
                            j = N;
                        }
                    }
                    System.out.println(response);
                }else{
                    System.out.println("UNKNOWN");
                }

            }else{
                System.out.println("UNKNOWN");
            }
        } 
        in.close();
    }
}