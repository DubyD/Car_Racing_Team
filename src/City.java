



//Author WD
//Some bug fixes by AW (3-23)
//EL fixed grammatical errors and added documentation

import java.util.*;


public class City {

        //Holds all the pieces of the Game
    private List<GamePiece> board;

        //Holds all the destinations to apply to the Racers destination lists
    private List<Destination> stops;

        //Holds all the competitors in an easy to access list
    private List<Car> racers;


        //Main Constructor
    public City(int dimension, int numOfCars){

            //initiates the Lists
        this.board = new ArrayList<GamePiece>();
        this.stops = new ArrayList<Destination>();
        this.racers = new ArrayList<Car>();

            //Factory Methods Setting the Lists
        this.makeDestinations(dimension);
        this.addCompetitors(dimension, numOfCars);

            //Adding the pieces to the board
            //Keeps all the pieces together
                //AW (3-23) Changed add() to addAll()
        this.board.addAll(this.stops);
        this.board.addAll(this.racers);

    }

        //No Parameter Constructor to complete class
    public City(){
        this.board = null;
        this.stops = null;
        this.racers = null;
    }

//------------------------Methods used in Factory Methods----------------------------
    
    /**
     * This method checks to see if the spot is available
     * @param x int
     * @param y int
     * @param one GamePiece
     * @return boolean
     */
    private boolean validSpot(int x, int y, GamePiece one){
        if(x == one.getX()){
            if(y == one.getY()){
                return false;
            }
        }
        return true;
    }

    /**
     * This method checks to see if the spot is available
     * @param x
     * @param y
     * @return boolean
     */
    private boolean isFreeSpace(int x, int y){
            //Loops through the Board components to see if this is a valid space
            //Loops through all the components even if they haven't been added to this.board
        for(Destination next: this.stops){
            if(!validSpot(x,y, next)){
                return false;
            }
        }

        for(Car next : this.racers){
            if(!validSpot(x, y, next)){
                return false;
            }
        }

        return true;
    }

    /**
     * This method creates a list of 4 destinations in random locations
     * @param dimension
     * @return void
     */
    private void makeDestinations(int dimension){

            //creates randomizer
        Random randomizer = new Random();
            //Names Destination
        char stop = 'A';

        for(int i = 0; i < 4; i++){

                //initiates Random X,Y locations
            int randomX;
            int randomY;

            do{
                    //Randomizers X, and Y until the space is valid
                randomX = randomizer.nextInt(dimension);
                randomY = randomizer.nextInt(dimension);
            } while(!this.isFreeSpace(randomX, randomY));

                //Creates a new Destination to add to Stops
            Destination adding = new Destination(randomX, randomY, String.valueOf(stop));
            this.stops.add(adding);

                //iterates the stops name through A, B, C, D
            stop = (char) (stop + 1);
        }
    }//End of Destination Factory

    /**
     * This method adds competitors to the board
     * @param dimension
     * @param numOfCars
     * @return void
     */
    private void addCompetitors(int dimension, int numOfCars){

            //creates randomizer
        Random randomizer = new Random();

        for(int i = 0; i < numOfCars; i++){

                //initiates Random X,Y locations
            int randomX;
            int randomY;

            do{
                    //Randomizers X, and Y until the space is valid
                randomX = randomizer.nextInt(dimension);
                randomY = randomizer.nextInt(dimension);
            } while(!this.isFreeSpace(randomX, randomY));

                //Adding a New racer in an open space
            Car adding = new Car(randomX, randomY, String.valueOf(i + 1));
                //Randomizers the order of locations the Racers visit
            this.setDestinations(adding);
            this.racers.add(adding);

        }

    }//End of Racer Factory

    /**
     * This method removes the last turns racer
     * @param remove
     * @return void
     */
    public void removeRacer(Car remove){
        if(this.racers.contains(remove)){
            this.racers.remove(remove);
            this.board.remove(remove);
        }
    }

    //-------------------------------Setters-----------------------------------------------------

    //Adds the next turns racer locations
    public void setRacers(List<Car> nextSpots){
        this.racers.addAll(nextSpots);
        this.board.addAll(nextSpots);
    }
    
        //Used to randomize particular racers check point order
    private void setDestinations(Car setting){
            //Creates a List without alternating the original list
        List <Destination> shuffledList = new ArrayList<>(this.stops);
            //Shuffles obj. in new list
        Collections.shuffle(shuffledList);
            //Adds new List to specific racer
        setting.setDestination(shuffledList);
    }//End of Shuffling racers objectives

    public void setBoard(List<GamePiece> board) {
        this.board = board;
    }

    public void setStops(List<Destination> stops) {
        this.stops = stops;
    }

    //---------------------------Getters-------------------------------------------------------

        //Exports cars to iterate their results onto individual labels
    public String[] getResults(){
            //Components needed to help sort
            //from smallest to largest
        int x = 0;
        int n = this.racers.size();
        boolean swapped;

            //Sets up an array for easier sorting than a List
        Car[] sorting = new Car[n];
            //iterates the List into an Array
        for(Car next : this.racers){
            sorting[x] = next;
            x++;
        }

        do{
                //If swapped stays false it exits the loop
            swapped = false;
            for(int i = 1; i < n; i++){

                if(sorting[i-1].getTimed() > sorting[i].getTimed()){
                        //Swaps sorting[i-1] and sorting[i]
                        //if sorting[i] finished first
                    Car temp = sorting[i-1];
                    sorting[i-1] = sorting[i];
                    sorting[i] = temp;
                        //Keeps the Loop going
                    swapped = true;
                }
            }
        } while(swapped);

            //Creates return string
        String[] reply = new String[n];
        for(int i = 0; i < n; i++){
            reply[i] = sorting[i].getResults();
        }

        return reply;
    }
    
    //Checks to see if the race is over
    public boolean getFinished(){
        boolean isDone = true;
        for(Car next: this.racers){
            isDone = isDone && next.getFinished();
        }
        return isDone;
    }

        //Give the ability to get List of Competitors
    public List<Car> getRacers(){
        return this.racers;
    }

    public List<Destination> getStops() {
        return this.stops;
    }

        //Exports all the GamePieces
    public List<GamePiece> getBoard(){
        return this.board;
    }

        //toString() method to complete class
    @Override
    public String toString() {
        String reply = "This class sets up the game board and acts" +
                "as a holder to all the backend components";
        return reply;
    }
}
