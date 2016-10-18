package a2;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.*;

/**
 * Created by johanrovala on 12/10/16.
 */
public class MainTest {

    protected Main main;

    protected ArrayList<A2Item> unsortedList;
    protected ArrayList<A2Item> sortedList;


    protected Item firstItem;
    protected Item secondItem;
    protected Item thirdItem;
    protected Item fourthItem;


    @org.junit.Before
    public void setUp() throws Exception {
        main = new Main();

        unsortedList = new ArrayList<>();
        firstItem = new Item("Zack", 18.3, "2009-10-05");
        secondItem = new Item("John", 4.4, "2011-02-21");
        thirdItem = new Item("Bob", 22.5, "2012-04-17");
        fourthItem = new Item("Albert", 16.1, "2010-08-13");
        unsortedList.add(firstItem);
        unsortedList.add(secondItem);
        unsortedList.add(thirdItem);
        unsortedList.add(fourthItem);

        sortedList = new ArrayList<>();
        sortedList.add(secondItem);
        sortedList.add(fourthItem);
        sortedList.add(firstItem);
        sortedList.add(thirdItem);

    }

    @org.junit.Test
    public void testReadCSVFile() throws Exception {
        for (int i = 0; i < unsortedList.size(); i++){
            assertEquals(unsortedList.get(i).toString(), main.readCSVFile("csv.txt").get(i).toString());
        }
    }

    @org.junit.Test
    public void testBubbleSortByTransactionValue() throws Exception {
        List<A2Item> compareBubble = main.readCSVFile("csv.txt");
        main.bubbleSortByTransactionValue(compareBubble);

        for (int i = 0; i < sortedList.size(); i++){
            assertEquals(sortedList.get(i).toString(), compareBubble.get(i).toString());
        }
    }

    @org.junit.Test
    public void testQuickSortByTransactionValue() throws Exception {
        List<A2Item> compareQS = main.readCSVFile("csv.txt");

        main.quickSortByTransactionValue(compareQS);

        for(int i = 0; i < compareQS.size(); i++){
            assertEquals(sortedList.get(i).toString(), compareQS.get(i).toString());
        }
    }

    @org.junit.Test
    public void testHeapSortByTransactionValue() throws Exception {
        List <A2Item> compareHS = main.readCSVFile("csv.txt");

        main.heapSortByTransactionValue(compareHS);

        for(int i = 0; i < compareHS.size(); i++){
            assertEquals(sortedList.get(i).toString(), compareHS.get(i).toString());
        }
    }

    /*
     * Not quite sure how to test this one since the time will differ from different runs.
     * It also seems that sometimes quicksort works faster and sometimes heapsort works faster.
     */

    @org.junit.Test
    public void testCompareAlgorithms() throws Exception {
        assertEquals(main.compareAlgorithms(unsortedList).size(), 3);
    }

    /*
     * Sort of the same problem here, i could try and to some sort of mocking to see if a System.out.println call
     * has been made. But that seems quite unnecessary. 
     */

    @org.junit.Test
    public void testPrintResults() throws Exception {

    }
}