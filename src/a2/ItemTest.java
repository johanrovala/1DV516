package a2;

import org.junit.Before;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by johanrovala on 17/10/16.
 *
 * This class is only meant to test Exercise 4 in this assignment.
 */
public class ItemTest {

    protected Item item;

    @Before
    public void setUp() throws Exception {
        item = new Item("Zack", 18.3,"2015-04-13");
    }

    /*
     * Not a very thorough test at all, since the goal of the exercise seemed to change a little bit considering we were
     * to use performer as a key, I'm not sure exactly what to test here. The equals method seems to work on the hashcodes at least.
     */

    @org.junit.Test
    public void testEquals(){
        Item dupe = new Item("Zack", 18.3, "2015-04-13");
        assertTrue(dupe.equals(item));
    }

    /*
     * Arbitrary test to make some validation of the uniqueness of the hashcodes generated in the Item class.
     */

    @org.junit.Test
    public void testHash(){
        String str = "abcdefghi";
        int j = 100;

        for (int i = 0; i < j; i++){
            Item first = new Item(shuffle(str), 18.3, "null");

            for (int k = 0; k < j; k++){
                Item second = new Item(shuffle(str), 18.3, "null");

                if(first.hashCode() == second.hashCode()){
                    System.out.println("Collision");
                }
            }
        }
    }

    private String shuffle(String string){
        List<String> letterList = Arrays.asList(string.split(""));
        Collections.shuffle(letterList);
        String shuffledString = "";
        for (String letters : letterList){
            shuffledString += letters;
        }
        return shuffledString;
    }
}