import java.util.Random;

public class Simulation {

    final Random random = new Random();
    Store store;
    int time;
    int intensity;
    int maxGroceries;
    int thresholdForNewRegisters;
    int statServed;
    int statMaxWait;
    int statAvgWait;
    int totalWaitTime;

    public Simulation(int noRegisters, int intens, int maxgroc, int threshold) {
        this.store = new Store(noRegisters);
        this.time = 0;
        this.statServed = 0;
        this.statMaxWait = 0;
        this.statAvgWait = 0;
        
        if (maxgroc < 1) {
            maxgroc = 1;
        }
        
        if (threshold < 1) {
            threshold = 1;
        }
        
        if (intens < 0) {
            intens = 0;
        }
        else if (intens > 100) {
            intens = 100;
        }
        
        this.maxGroceries = maxgroc;
        this.thresholdForNewRegisters = threshold;
        this.intensity = intens;
    }

    //methods
    
    public Customer makeRandomCustomer() {
        int time = this.time;
        int groceries = random.nextInt(this.maxGroceries);
        Customer c = null;
        int retired = random.nextInt(5);
        if (retired == 0) {
            c = new RetiredCustomer(time, groceries);
        }
        else {
            c = new Customer(time, groceries);
        }
        return c;
    }
    public void step() {
        // 1. time passes
        store.step();
        this.time += 1;
        // 2. see if new customer should be created
        int new_customer = random.nextInt(100);
        if (new_customer < this.intensity ) {
            Customer c = makeRandomCustomer();
            this.store.newCustomer(c);
        }
        // 3. see if new register should be opened
        if (this.store.getAverageQueueLength() > thresholdForNewRegisters) {
            this.store.openNewRegister();
        }
        // 4. get done customers & update statistics
        Customer [] done = this.getDone();
        this.statistics(done);
    }

    private Customer [] getDone() {
        return this.store.getDoneCustomers();

    }
    
    private void statistics(Customer [] done) {
        //update statServed
        this.statServed += done.length; 

        for (Customer c : done) {
            int waitTime = this.time - c.getBornTime();
            //update statMaxWait (if applicable)
            if (this.statMaxWait < waitTime) {
                this.statMaxWait = waitTime;
            }
            //update statAvgWait
            this.totalWaitTime += waitTime;
            this.statAvgWait =  this.totalWaitTime / this.statServed;
        }
    }
    
    private String printTime() {
        return "Time: " + this.time + "\n";
    }

    private String printServed() {
        return "Number of customers served: " + this.statServed + "\n";
    }

    private String printMaxWait() {
        return "Max waiting time today: " + this.statMaxWait + "\n";
    }

    private String printAvgWait() {
        return "Average wait-time: " + this.statAvgWait + "\n";
    }

    public String toString() {
        return this.printTime() + this.printServed() + this.printMaxWait() + this.printAvgWait() + "\n" + this.store;
    }
    
}
