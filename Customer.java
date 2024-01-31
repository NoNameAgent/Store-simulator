public class Customer {
    private int bornTime;
    protected int groceries;

    public Customer(int startTime, int noOfGroceries) {
        if (noOfGroceries < 0) {
            this.groceries = 0;
        }
        else {
            this.groceries = noOfGroceries;
        }
        this.bornTime = startTime;
    }

    public void serve(){
        if (this.groceries > 0) {
            this.groceries -= 1;
        }
      }

    public int getBornTime(){ 
        return this.bornTime;
    }

    public boolean isDone(){
        if (this.groceries != 0){
            return false;
        }
        else {
            return true;
        }
    }

    public String printCustomer(){
        return ">+o";
    }

    public String toString() {
        return "[" + this.groceries + "]" + printCustomer();
    }
}
