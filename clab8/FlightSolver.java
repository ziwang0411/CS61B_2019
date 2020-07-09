import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {
    private ArrayList<Flight> flights;
    public FlightSolver(ArrayList<Flight> flights) {
        /* FIX ME */
        this.flights = flights;

    }

    public int solve() {
        /* FIX ME */
//        return -1;
        Comparator<Flight> minStartComp = (o1, o2) -> (o1.startTime() - o2.startTime());
        Comparator<Flight> minEndComp = (o1, o2) -> (o1.endTime() - o2.endTime());
        PriorityQueue<Flight> minStartPQ = new PriorityQueue<>(flights.size(), minStartComp);
        PriorityQueue<Flight> minEndPQ = new PriorityQueue<>(flights.size(), minEndComp);

        for (Flight f: flights) {
            minStartPQ.add(f);
            minEndPQ.add(f);
        }

        int maxVal = 0;
        int tally = 0;
        
        while (minStartPQ.size() > 0 && minEndPQ.size() >0) {
            int startTime = minStartPQ.peek().startTime();
            int endTime = minEndPQ.peek().endTime();

            if (startTime <=endTime) {
                Flight f = minStartPQ.poll();
                tally +=f.passengers();
            } else {
                Flight f = minEndPQ.poll();
                tally -=f.passengers();
            }

            maxVal = (tally > maxVal)? tally: maxVal;

        }
        return maxVal;
    }

//    public static void main(String[] args) {
//        test1();
//        test2();
//    }
//
//    public static void test1() {
//        Flight f1 = new Flight(2, 4, 1);
//        Flight f2 = new Flight(1, 3, 1);
//        Flight f3 = new Flight(5, 10, 1);
//        Flight f4 = new Flight(0, 7, 1);
//        ArrayList<Flight> L = new ArrayList<>();
//        L.add(f1);
//        L.add(f2);
//        L.add(f3);
//        L.add(f4);
//        FlightSolver fs = new FlightSolver(L);
//        System.out.println(fs.solve());
//    }
//
//
//    public static void test2() {
//        Flight f1 = new Flight(1, 3, 1);
//        Flight f2 = new Flight(3, 5, 1);
//        Flight f3 = new Flight(5, 7, 1);
//        Flight f4 = new Flight(7, 9, 1);
//        ArrayList<Flight> L = new ArrayList<>();
//        L.add(f1);
//        L.add(f2);
//        L.add(f3);
//        L.add(f4);
//        FlightSolver fs = new FlightSolver(L);
//        System.out.println(fs.solve());
//    }
}
