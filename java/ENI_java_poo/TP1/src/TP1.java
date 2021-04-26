import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TP1 {
    public static void main(String[] args) {
        ArrayList<String> dictionary = getDictionnary();
        char[]
            word = takeRandomWord(dictionary),                          // Take a random word
            mixWord = mixTheWord(word),                                 // Mix the word
            response = userResponse(mixWord).toCharArray();             // get user's response

            // Check if is good response
            if(isGoodLetter(word, response) && isInTheDico(dictionary,response) ){
                System.out.println("You win !");
            }else{
                System.out.println("You loose !");
            }
    }

    /**
     * record all word of the dictionary in Array
     * @return
     */
    private static ArrayList<String> getDictionnary() {
        ArrayList<String> motDico = new ArrayList<String>();

        try (FileInputStream file = new FileInputStream("dictionnaire.txt"); Scanner s = new Scanner(file) ){
            while (s.hasNextLine()) {
                motDico.add(s.nextLine());
            }
        } catch (IOException e) {
            System.err.println("Lecture impossible ! ");
        }
        return motDico;
    }


    /**
     * Check if All letter of user's response is in the word who must be find
     * @param word
     * @param response
     * @return
     */
    private static boolean isGoodLetter(char[] word, char[] response) {
        boolean tmpLetter = false;
        for (int i = 0; i < word.length; i++) {
            for (int j = 0; j < response.length; j++) {
                if ( Character.toUpperCase(word[i]) == Character.toUpperCase(response[j]) ){
                    tmpLetter = true;
                    j = response.length;
                }
            }
            if(! tmpLetter){
                i = word.length;
            }
        }

        return tmpLetter;
    }

    /**
     * Check if the response of user is in the dictonary
     * @param dictionary
     * @param response
     * @return
     */
    private static boolean isInTheDico(ArrayList<String> dictionary, char[] response) {
        boolean confirm = false;

        for (int i = 0; i < dictionary.size(); i++) {
            if (String.valueOf(response).equals(dictionary.get(i))) {
                confirm = true;
            }
        }
        return confirm;
    }

    /**
     * Display message and get user's response
     * @param mixWord
     * @return String, who is response of user
     */
    private static String userResponse(char[] mixWord) {
        Scanner scan = new Scanner(System.in);

        System.out.println("****************************************************");
        System.out.println("Let's Play to find the biggest word as an possible !");
        System.out.println("****************************************************");
        System.out.println("The Mix-word is : " + String.valueOf(mixWord).toUpperCase());
        System.out.print("your response : ");

        return scan.nextLine();
    }

    /**
     * return a random word of array
     * @return String who is a random item from array
     * @param dictionary
     */
    private static char[] takeRandomWord(ArrayList<String> dictionary) {
        char[] word = dictionary.get( (int) (Math.random() * dictionary.size())).toCharArray();
        System.out.println(word);
        return word;
    }

    /**
     * Mix the word. take 2 random number between 0 and length and inverse position of this
     * @param char[] word who is the random word selected by takeRandomWord()
     * @return char[] mixWord is a copy of word with shuffle mix
     */
    private static char[] mixTheWord(char[] word){
        int length = word.length;
        int rand;
        char[] mixWord = Arrays.copyOf(word,length);
        char letter;

        for (int i = 0; i < length; i++) {
            rand = (int)(Math.random() * (length - 1) );    // select value between 0 and length -1
            letter = mixWord[i];                            // C = A
            mixWord[i] = mixWord[rand];                     // A = B
            mixWord[rand] = letter;                         // B = C
        }
        return mixWord;
    }
}