public class Store {
    
    //attributes
    private Register[] registers; // vi deklarerar att registers är en array (allokerar inte ännu minne)
   
    //constructor
    public Store () {
        this.registers = new Register[1]; // vi allokerar minne till registers-array med längd 1
        this.registers[0] = new Register();
        this.openNewRegister();
    }
    
    public Store(int r) {
        if (r <= 0) { // om 0 eller minus antal kassor: sätt antal kassor till 1
            r = 1;
        }
        this.registers = new Register[r]; // vi allokerar minne till registers-array med längd r
        for (int n = 0; n < r; n++) { // Creates r registers
            this.registers[n] = new Register();
        }
        this.openNewRegister();
    }
    
    
    public int countDoneCustomers() {    
        int counter = 0;
        for (Register r : this.registers) {
            if (r.getQueueLength() > 0 && r.currentCustomerIsDone()) {
                counter++;
            }
        }
        return counter;
    }

    

    public float getAverageQueueLength() {
        int counter = 0;
        int open = 0;
        for (Register r : this.registers) {
            if (r.isOpen()) {
                counter += r.getQueueLength();
                open++;
            }
        }
        return (float) counter / open;
    }
    
    public void newCustomer(Customer c) {
        // find register with shortest queue
        Register shortest = this.registers[0];          
        for (Register r : this.registers) {
            if (r == this.registers[0]) {
                continue; // hoppa över element 0
            }
            if (r.isOpen() && shortest.getQueueLength() > r.getQueueLength()){
                shortest = r;
            }
        }
        // place customer
        shortest.addToQueue(c);
    }

    public void step() {
        for (Register r : this.registers) {
            r.step();
        }
    }
    
    public void openNewRegister() {
        for (Register r : this.registers) {
            if (!r.isOpen()){
                r.open();
                return;
            }
        }
    }

    public Customer[] getDoneCustomers() {
        int numDone = this.countDoneCustomers();
        Customer[] done = new Customer[numDone];
        int counter = 0;
        for (Register r : this.registers) {
            if (r.getQueueLength() != 0 && r.currentCustomerIsDone()) {
                done[counter] = r.removeCurrentCustomer();
                counter++;
            }
        }
        return done;
    }

    public String toString() {
        String result = "";
        for (Register r : this.registers) {
            result += r;
        }
        return result + "\n";
    }

    //main
    public static void main (String [] args) {
        // skapa butik (med en öppen kassa)
        Store s = new Store(4);
        // öppna kassor
        s.openNewRegister();
        //s.openNewRegister();
        // skapa kunder
        Customer c1 = new Customer(1,2);
        Customer c2 = new Customer(2,4);
        Customer c3 = new Customer(3,2);
        Customer c4 = new Customer(4,3);
        Customer c5 = new Customer(5,2);
        //sätt in kunderna i store (den kortaste kassakön)
        s.newCustomer(c1);
        s.newCustomer(c2);
        s.newCustomer(c3);
        s.newCustomer(c4);
        s.newCustomer(c5);

        float average = s.getAverageQueueLength();
        System.out.println("Average queue length: " + average);
        System.out.println("\n\r");

        System.out.println(s);

        for (int n = 0; n < 7; n++) {
            s.getDoneCustomers();
            s.step();
            System.out.println(s);
        }
    }
}
