public class Register {
    //attributes
    boolean open; //true om kassan är öppen, annars false
    Queue queue;

    //constructor
    public Register(){
        this.open = false;
        this.queue = new Queue();
    }

    //methods
    public void open() { //  öppna kassan.
        this.open = true;
    }

    public void close() {
        this.open = false;
    }

    public boolean isOpen() {
     return (this.open == true);
    }

    public void step() {  //  låt tiden gå ett steg i kassan. Det betyder att kunden som står först i kön får en vara registrerad.
        if (this.isOpen() && this.hasCustomers()) {
            try {
                Customer c = this.queue.first();
                c.serve();
            }
            catch (NullPointerException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public boolean hasCustomers(){
        return (queue.length() != 0);
    }

    public boolean currentCustomerIsDone(){
        if (this.hasCustomers() == false) throw queue.new EmptyQueueException();
        Customer c = queue.first();
        return c.isDone();
    }
    
    public void addToQueue(Customer c){
        queue.enqueue(c);
    }

    public Customer removeCurrentCustomer() {
        if (this.hasCustomers() == false) throw queue.new EmptyQueueException();
        return queue.dequeue();
    }
    
    public int getQueueLength(){
        return queue.length();
    }

    public String toString() {
        if (this.isOpen()) {
            return "O " + this.queue + "\n";
        }
        else {
            return "X " + this.queue + "\n";
        }
    }

}
