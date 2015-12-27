/* 
 * Java anagram and missing letters program. 
 * Currently just reads through whole file on each iteration.
 * 
 * To Do:
 * Read file(s) into an array and then use binary search.
 */

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Anagram {
    public static void main(String[] args) throws IOException
    {

        boolean dots, valid;
        int found, i;
        String word;
        char arr[];
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            /* Get user's word. */
            System.out.print("Enter a word, or use 'q' to quit: ");
            word = br.readLine();
            if (word.equals("q")) break;

            /* Change to lower case. */
            word = word.toLowerCase();

            /* Have we got any dots? */
            dots = false;
            if (word.contains(".")) dots = true;

            /* Convert to char array to make check and sort easier. */
            arr = word.toCharArray();

            /* Reject invalid strings. */
            valid = check(arr);
            if (!valid) {
                System.out.println("Invalid word.");
                continue;
            }

            found = 0;
            /* Call appropriate search method. */
            if (dots == true) found = match(arr, "dictionary.txt");
            else found = find(arr, "anag.txt");

            if (found == 0) 
                System.out.println("No matches found.");
        }
    }

    /* Sorts letters within word. */
    private static String letsort(char arr[]){
        int i, j;
        char t;

        /* Uses inserstion sort. */
        for (i = 1; i < arr.length; i++)
        {
            for (j = i, t = arr[j]; j > 0 && arr[j - 1] > t; j--)
                arr[j] = arr[j-1];
            arr[j] = t;
        }
        return new String(arr);
    }


    /* Checks the word only contains letters or '.'s. */
    private static boolean check(char arr[]){
        
        for (char c: arr){
            if (!Character.isLetter(c) && c != '.')
                return false;
        }
        return true;
    }

    /* Looks for anagrams. Returns number of matches found. */
    private static int find(char arr[], String fname) throws IOException {

        int mid, found = 0;

        /* Get the word as a sorted string. */
        String orig = new String(arr);
        String word = letsort(arr);
        File f = new File(fname);
        FileReader in = new FileReader(f);
        BufferedReader br = new BufferedReader(new BufferedReader(in));
        String str;

        /* Read through the lines and check. */
        while ((str = br.readLine()) != null) {
            mid = str.length() / 2;
            if (word.equals(str.substring(mid)) && !orig.equals(str.substring(0, mid))){
                System.out.println(str.substring(0, mid));
                found++;
            }
        }
        in.close();
        
        return found;
    }

    /* Looks for words that match the pattern in the string.
     * Returns the number of matches found. */
    private static int match(char arr[], String fname) throws IOException {

        int found = 0;
        int len = arr.length;
        String str;

        /* Create regex from user's string. */
        String pat = "^" + new String(arr) + "$";

        /* Set up regular expression. */
        Pattern p = Pattern.compile(pat);

        /* Prepare to read text from file. */
        File f = new File(fname);
        FileReader in = new FileReader(f);
        BufferedReader br = new BufferedReader(new BufferedReader(in));

        /* Read through the lines and check. */
        while ((str = br.readLine()) != null) {
            /* Only test words the right length. */
            if (str.length() == len) {
                Matcher mat = p.matcher(str);
                if (mat.find()){
                    found++;
                    System.out.println(str);
                }
            }

        }
            return found;
    
    }
}
