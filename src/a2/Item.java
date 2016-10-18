package a2;

import java.util.HashMap;

/**
 * Created by johanrovala on 07/10/16.
 */
public class Item extends HashMap implements A2Item{

    /*
     * Variables
     */

    private String performer;
    private double transactionval;
    private String date;

    /*
     * Constructors - One empty and one allowing for the entire Item to be initialized.
     */

    public Item(String performer, double transactionval, String date){
        this.performer = performer;
        this.transactionval = transactionval;
        this.date = date;
    }

    public Item(){}

    /*
     * getPerformer - returns String performer.
     */

    @Override
    public String getPerformer() {
        return this.performer;
    }

    /*
     * getTransactionValue - returns double transactionval.
     */

    @Override
    public double getTransactionValue() {
        return this.transactionval;
    }

    /*
     * getDate - returns String date.
     */

    @Override
    public String getDate() {
        return this.date;
    }

    @Override
    public void setPerformer(String name) {
        this.performer = name;
    }

    @Override
    public void setTransactionValue(double value) {
        this.transactionval = value;
    }

    @Override
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString(){
        return this.getPerformer() + " " + this.getTransactionValue() + " " + this.getDate();
    }

    /*
     * hashCode - returns getHash, also checks if key value is to big in order to account for eventual performance issues.
     */

    @Override
    public int hashCode() {

        // In the case of incredibly long names or someone trying to break something.

        if (this.getPerformer().length() > 17){
            return getHash();
        }
        return getHash();
    }

    /*
     * getHash - Performs a hashing algorithm and returns the result.
     */

    private int getHash(){
        int hash = 7;
        for (int i = 0; i < this.getPerformer().length(); i++){
            hash = hash * 31 + this.getPerformer().charAt(i);
        }
        return hash;
    }

    /*
     * equals - Compares the hashcode of to given Objects.
     */

    @Override
    public boolean equals(Object obj) {
        if (obj != null){

            // This feels strange

            return ((Item) obj).getPerformer().equals(this.getPerformer()) && ((Item) obj).getDate().equals(this.getDate()) && Double.compare(((Item) obj).getTransactionValue(), this.getTransactionValue()) == 0 && ((Item) obj).hashCode() == this.hashCode();
        }
        else{
            return false;
        }
    }
}
