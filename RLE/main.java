import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        main m = new main();
        String path = "blume.txt";
        char[] blume = m.readFile(path);
        char[] kompBlume = m.komprimieren(blume);
        m.printCharArray(kompBlume);
        char[] dekomp = m.dekomprimieren(kompBlume);
        m.printCharArray(dekomp);

        double kompFaktor = kompBlume.length / blume.length;
        System.out.println(kompBlume.length + "/" + blume.length + "; Komprimierungsfaktor: " + kompFaktor);
    }

    public char[] komprimieren(char[] input){
        char[] result;
        char lastChar = ' ';
        int anzahl = 0;
        String res = "";

        for (int i = 0; i < input.length; i++) {
            if(input[i] != lastChar){
                if(anzahl >= 3){
                    res += anzahl + "" + lastChar;
                }else if(anzahl == 1){
                    res += lastChar;
                }else if(anzahl == 2){
                    res += lastChar + "" + lastChar;
                }
                anzahl = 1;
            }else{
                anzahl++;
            }
            lastChar = input[i];
        }
        result = res.toCharArray();

        return result;
    }

    public char[] dekomprimieren(char[] input){
        char[] result;
        String res = "";
        String digit = "";
        char lastChar = ' ';

        for (int i = 0; i < input.length; i++) {
            if(Character.isDigit(input[i])){
                digit += input[i];
            }else{
                if(digit != ""){
                    for (int j = 0; j < Integer.parseInt(digit); j++) {
                        res += input[i];
                    }
                }else{
                    res += input[i];
                }
                digit = "";
            }
            lastChar = input[i];
        }

        result = res.toCharArray();

        return result;
    }

    public char[] readFile(String path){
        Scanner scan = null;
        char[] result;
        String everything = "";

        try {
            scan = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(scan.hasNext()){
            String temp = scan.nextLine();
            everything += temp + "n";
        }
        scan.close();
        result = everything.toCharArray();
        return result;
    }

    public void printCharArray(char[] input){
        String ausgabe = "";
        for(int i = 0; i < input.length; i++){
            if(input[i] == 'n'){
                System.out.println(ausgabe);
                ausgabe = "";
            }else{
                ausgabe+="" + input[i];
            }
        }
    }
}