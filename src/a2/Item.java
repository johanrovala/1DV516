package a2;

/**
 * Created by johanrovala on 07/10/16.
 */
public class Item implements A2Item{

    private String performer;
    private double transactionval;
    private String date;

    public Item(String performer, double transactionval, String date){
        this.performer = performer;
        this.transactionval = transactionval;
        this.date = date;
    }

    public Item(){

    }

    @Override
    public String getPerformer() {
        return this.performer;
    }

    @Override
    public double getTransactionValue() {
        return this.transactionval;
    }

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
}
