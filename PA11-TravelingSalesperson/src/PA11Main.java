import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/*
 * AUTHOR: Merle Crutchfield
 * FILE: PA11Main.java
 * ASSIGNMENT: PA11 - TravelingSalesperson
 * COURSE: CSC210 Fall2020 Section 1
 * PURPOSE: This assignment teaches us about practicing new algorithms
 * involving recursive backtracking and heuristic approaches. We use a
 * new data structure of a graph to represent the shortest trip
 * through a sequence of locations while only visiting each location
 * once. We write a heursitic method first using the pseudocode in the
 * spec, which is a nested for loop that runs through. The recursive
 * backtracking is what we practiced in our last drill, and I also wrote
 * my mine method using recursive backtracking. I improved the pruning to
 * make it run faster than the default the spec had.
 */

public class PA11Main {
    // Private field
    private static int num;

    public static void main(String[] args) throws FileNotFoundException {
        DGraph town = createDGraph(args[0]);
        String command = args[1];
        if (command.equals("HEURISTIC")) {
            Trip trip = heuristic(town);
            System.out.println(trip.toString(town));
        }
        if (command.equals("BACKTRACK")) {
            Trip trip = backTrack(town);
            System.out.println(trip.toString(town));
        }
        if (command.equals("MINE")) {
            Trip trip = mine(town);
            System.out.println(trip.toString());
        }
        if (command.equals("MINE")) {
            Trip cities = new Trip(town.getNumNodes());
            Trip minTrip = new Trip(town.getNumNodes());
            cities.chooseNextCity(1);
            mineOwn(cities, town, minTrip, 1);
            System.out.println(cities.toString());
        }
        if (command.equals("TIME")) {
            time(town);
        }
    }


    /*
     * This method returns the DGraph made from the
     * file entered. It reads in a file, iterates past all
     * of the comments, and then saves the number of nodes.
     * It then runs through another while loop that adds
     * all of the edges to the DGraph. Once it has gone
     * through all the lines, the file is closed and the
     * DGraph returned.
     */
    public static DGraph createDGraph(String name)
            throws FileNotFoundException {
        Scanner file = new Scanner(new File(name));
        boolean comment = true;
        String line = file.nextLine();
        while (comment) {
            line = file.nextLine();
            comment = line.startsWith("%");
        }
        num = Integer.valueOf(line.split(" ")[0]);
        line = file.nextLine();
        DGraph town = new DGraph(num);
        while (!(comment)) {
            if (!(line == null)) {
                String[] str = line.split("\\s+");
                int v = (Integer.valueOf(str[0].trim())).intValue();
                int w = (Integer.valueOf(str[1].trim())).intValue();
                double c = (Double.valueOf(str[2].trim())).doubleValue();
                town.addEdge(v, w, c);
                if (file.hasNext())
                    line = file.nextLine();
                else
                    comment = true;
            }
            else
                comment = true;
        }
        file.close();
        return town;
    }


    /*
     * This method returns a trip using the heuristic method
     * of finding it. It creates a new trip, and then gets the
     * first city. It then iterates through a for loop, and
     * another nested one that goes through each of the
     * neighbors of the city, and checks to see if it is valid.
     * If it is, then it checks to see if the weight is less
     * than the min saved value, meaning it is a shorter path,
     * and saves it. It then goes to this city and runs until
     * completed. The path is returned.
     */
    public static Trip heuristic(DGraph town) {
        int cityNum = 1;
        Trip trip = new Trip(town.getNumNodes());
        trip.chooseNextCity(cityNum);
        for (int k = 2; k <= town.getNumNodes(); k++) {
            double val = Double.MAX_VALUE;
            int close = 0;
            for (int i : town.getNeighbors(cityNum)) {
                if (trip.isCityAvailable(i)) {
                    if (town.getWeight(cityNum, i) < val) {
                        val = town.getWeight(cityNum, i);
                        close = i;
                    }
                }
            }
            trip.chooseNextCity(close);
            cityNum = close;
        }
        return trip;
    }


    /*
     * This is my method for improving the recursive backtracking.
     * This creates two trips, one as the min to return and the
     * other to iterate on. It gets the next city and then calls
     * the backtracking helper method. The min trip is returned.
     */
    public static Trip mine(DGraph town) {
        Trip trip = new Trip(town.getNumNodes());
        Trip min = new Trip(town.getNumNodes());
        trip.chooseNextCity(1);
        myBacktrackingFunction(town, trip, min);
        return min;
    }

    /*
     * This method takes in a DGraph and two Trips passed by the
     * user. It checks to see if all the nodes are in the trip,
     * and then if the cost is less than the min trip. If so it
     * is changed using the copy method and returned. If the trip
     * so far has a lower cost then it runs through each city of
     * the ones left and chooses the next city, recursively calls
     * itself, and then unchooses.
     */
    public static void myBacktrackingFunction(DGraph town, Trip curr,
            Trip smallest) {
        if (curr.citiesLeft().isEmpty()) {
            if (curr.tripCost(town) < smallest.tripCost(town))
                smallest.copyOtherIntoSelf(curr);
            return;
        } else if (curr.tripCost(town) < smallest.tripCost(town)) {
            for (int x : curr.citiesLeft()) {
                curr.chooseNextCity(x);
                myBacktrackingFunction(town, curr, smallest);
                curr.unchooseLastCity();
            }
        }
    }


    /*
     * This method is for the recursive backtracking algorithm.
     * It takes in the DGraph as the only input. It is the same
     * as the mine method, however it calls a different helper
     * method. The min trip is returned.
     */
    public static Trip backTrack(DGraph town) {
        Trip trip = new Trip(town.getNumNodes());
        Trip min = new Trip(town.getNumNodes());
        trip.chooseNextCity(1);
        backtrackingFunction(town, trip, min);
        return min;
    }

    private static void mineOwn(Trip cities, DGraph graph, Trip minTrip,
            int prev) {
        if (cities.citiesLeft().size() == 0
                && minTrip.tripCost(graph) > cities.tripCost(graph)) {
            minTrip.copyOtherIntoSelf(cities);
            return;
        } else {
            if (cities.tripCost(graph) < minTrip.tripCost(graph)) {
                for (int i : cities.citiesLeft()) {
                    double check = cities.tripCost(graph)
                            + graph.getWeight(prev, i);
                    if (check > minTrip.tripCost(graph)) {
                        return;
                    }
                    cities.chooseNextCity(i);
                    prev = i;
                    mineOwn(cities, graph, minTrip, prev);
                    cities.unchooseLastCity();
                }
            } else {
                return;
            }
        }
    }


    /*
     * This method is the helper for the recursive backtracking
     * algorithm. It takes in the DGraph, the current trip,
     * and the min trip previously found. It checks to see if all the
     * nodes are in the trip, and then if the cost is less than the min
     * trip. If so it is changed using the copy method and returned. If
     * not then runs through a for loop of each of the cities left and
     * checks to see if it is valid. If so it chooses the next city,
     * recursively calls itself, and then unchooses.
     */
    public static void backtrackingFunction(DGraph town, Trip curr,
            Trip smallest) {
        if (curr.citiesLeft().isEmpty()) {
            if (curr.tripCost(town) < smallest.tripCost(town))
                smallest.copyOtherIntoSelf(curr);
        } else {
            for (int x : curr.citiesLeft()) {
                if (curr.tripCost(town) < smallest.tripCost(town)) {
                    curr.chooseNextCity(x);
                    backtrackingFunction(town, curr, smallest);
                    curr.unchooseLastCity();
                }
            }
        }
    }

    
    /*
     * This method is used to print out the time for each of the
     * three different ways of solving the paths. It first prints
     * out the heuristic cost in milliseconds, then mine, and then
     * recursive backtracking. The only argument passed is the
     * DGraph.
     */
    public static void time(DGraph town) {
        long startTime = System.nanoTime();
        Trip trip = heuristic(town);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("heuristic: cost = " + trip.tripCost(town) + ", "
                + duration + " milliseconds");
        long startTime2 = System.nanoTime();
        Trip trip2 = mine(town);
        long endTime2 = System.nanoTime();
        long duration2 = (endTime2 - startTime2) / 1000000;
        System.out.println("mine: cost = " + trip2.tripCost(town) + ", "
                + duration2 + " milliseconds");
        long startTime3 = System.nanoTime();
        Trip trip3 = backTrack(town);
        long endTime3 = System.nanoTime();
        long duration3 = (endTime3 - startTime3) / 1000000;
        System.out.println("backtrack: cost = " + trip3.tripCost(town) + ", "
                + duration3 + " milliseconds");

    }

}
