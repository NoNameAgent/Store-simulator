# Store-simulator
Store simulator \n
 \n
The simulation will run in the Terminal. Start the simulation by running the main-function in Simulator.java \n
 \n
Change the arguments in main-fuction to alter the simulation: \n
 \n
arg1 = no. of Registers, the amount of available registers
arg2 = intensity, meaning the intensity of shopping and customers in the store, where 100 would indicate "rush hour" \n
arg3 = max amount of groceries, meaning the maximum amount of random goceries a customer can have \n
arg4 = threshold for waiting customers, meaning the amount of customers tolerated before the store opens a new cashier \n
 \n
int steps = 100; indicates the number of iterations/steps the simulation will run \n
 \n
O[]  indicates an open register without a customers. \n
X[]  indicates a closed register. \n
O[X]>+o   indicates an open register, serving a customer with X amounts of groceries yet to scan. \n
>+o  indicates a normal customer. \n
%+o  indicates a old/retired customer, which takes twice as long time in the register than a normal customer. \n
