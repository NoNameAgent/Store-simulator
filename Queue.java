public class Queue {

    private Node first;
    private Node last;
    private int length;

    public Queue () {
        this.length = 0;
    }


    public int length() {
        assert (this.length >= 0) : "Error: Negative queue length.";
        return this.length;
    }

    public void enqueue(Customer c){
        if (this.length() == 0) {
            Node n = new Node(c);
            this.first = n;
            this.last = n;
        }
        else {
            Node n = new Node(c);
            this.last.next = n;
            this.last = this.last.next;
        }
        this.length++;
    }

    public Customer dequeue() {
        Customer c = this.first();
        if (this.length() == 0) throw new EmptyQueueException();

        try {
            if (this.length() > 2) {
                this.first = this.first.next;
            }
            else if (this.length() == 2){
                this.first = this.last;
            }
            else if (this.length() == 1){
                assert (this.first == this.last) : "Error: References are incorrect.";
                this.first = null;
                this.last = null;
            }
            this.length--;
        }
        catch(EmptyQueueException ex) {
            ex.printStackTrace();
        }
        catch(NullPointerException ex) {
            ex.printStackTrace();
        }
        return c;
    }

    public Customer first() {
        Customer c = null;
        try {
            if (this.length() > 0) {
            c = this.first.element;
            }
        }
        catch(NullPointerException ex) {
            ex.printStackTrace();
        }
        return c;
    }

    private String printWaitingCustomers() {
        String customers = "  ";
        Node n = this.first;
        while(n.hasNext()) {
            n = n.next;
            customers += n.element.printCustomer();
        }
        return customers;
    }

    public String toString() {
        String s = null;
        try {
            if (this.length() > 0) {
                s = this.first() + printWaitingCustomers();
            }
            else {
                s = "[ ]";
            }
        }
        catch(NullPointerException ex) {
            ex.printStackTrace();
        }
        return s;
    }

    private class Node {                // nested class
        
        public Customer element;        // attributes
        public Node next;

        public Node(Customer c){        // constructor
            this.element = c;  
            this.next = null;
        }

        public boolean hasNext() {
            return (this.next != null);
        }

        public String toString() {
            String next;
            if (this.hasNext()) {
                next = new String("some pointer");
            }
            else {
                next = new String("null");
            }
            return "[Element: C" + this.element + ", next: " + next + "]";
        }
    }

    public class EmptyQueueException extends RuntimeException{
    }
}

