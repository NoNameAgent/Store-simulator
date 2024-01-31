public class Simulator{

    public static void main(String[] args) throws InterruptedException{
        int steps = 100; // number of steps the simulation will run
        Simulation s = new Simulation(9,80,19,7); 
        for(int i = 0; i < steps; i++){
            try {
                System.out.print("\033[2J\033[;H");
                s.step();
                System.out.println(s);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // so that Thread.interrupted() = True
            }
        }
        System.out.println("");
        System.out.println("Simulation finished!");
    }
}