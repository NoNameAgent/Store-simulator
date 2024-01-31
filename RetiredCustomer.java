public class RetiredCustomer extends Customer {
    boolean wait;

    public RetiredCustomer(int startTime, int noOfGroceries) {  // körs automatiskt
        super(startTime, noOfGroceries);
        this.wait = true;
    }

    public void serve(){            // method1
        if (this.wait && this.groceries > 0) {
            this.groceries -= 1;
        }
        this.wait = !(this.wait);
      }

    public String printCustomer() {
        return "%>o";
    }

    public String toString() {
        return "[" + this.groceries + "]" + printCustomer();
    }
}
