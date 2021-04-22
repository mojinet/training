package fr.modji.puzzle.facile;

import java.util.Scanner;

public class ChuckNorris {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String MESSAGE = in.nextLine();
        System.err.println(MESSAGE);
        System.err.println((int)'%');
        System.err.println(Integer.toBinaryString(MESSAGE.charAt(0)));

        // Convert String to binary String
        String binaryMessage = convertToBinary(MESSAGE);

        // Return result to the convert in chuck norris's style
        System.out.println(calculReponse(binaryMessage));
    }

    private static String convertToBinary(String msg){
        StringBuilder response = new StringBuilder();
        char[] msgChar = msg.toCharArray();
        String tmp;

        for (int i : msgChar){
            if (Integer.toBinaryString(i).length() < 7){
                tmp = "0" + Integer.toBinaryString(i);
            }else{
                tmp = Integer.toBinaryString(i);
            }
            response.append(tmp);
        }

        return response.toString();
    }

    private static String calculReponse(String str){
        StringBuilder response = new StringBuilder();
        char[] strChar = str.toCharArray();
        int cumul;
        int j;
        String currentNumber;

        for (int i = 0; i < strChar.length; i++){
            currentNumber = strChar[i] == '0' ? "00 " : "0 ";
            cumul = 1;
            response.append(currentNumber);
            j = i;

            while ( (j+1 < strChar.length) && (strChar[j] == strChar[j+1]) ){

                cumul++;
                j++;
                i = j;
            }

            for (int k = 0; k < cumul; k++) {
                response.append("0");
            }
            response.append(" ");

        }

        return response.toString().trim();
    }
}
