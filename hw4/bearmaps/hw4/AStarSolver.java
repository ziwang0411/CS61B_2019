package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.*;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome outcome;
    private double solutionWeight;
    private LinkedList<Vertex> solution;
    private double timeSpent = 0;
    private int numStatesExplored = 0;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {

        ArrayHeapMinPQ<Vertex> pq = new ArrayHeapMinPQ<>();
        Stopwatch sw = new Stopwatch();
        Map<Vertex, Double> distTo = new HashMap<>();
        Map<Vertex, Vertex> edgeTo = new HashMap<>();
        distTo.put(start, 0.0);
        pq.add(start, distTo.get(start) + input.estimatedDistanceToGoal(start, end));


        while (pq.size() > 0 && !pq.getSmallest().equals(end) && sw.elapsedTime() < timeout) {
            Vertex v = pq.removeSmallest();
            numStatesExplored++;
            List<WeightedEdge<Vertex>> neighborEdges = input.neighbors(v);
            for (WeightedEdge<Vertex> e : neighborEdges) {
                Vertex p = e.from();
                Vertex q = e.to();
                double w = e.weight();
/*
                timeSpent = sw.elapsedTime();
                if (timeSpent > timeout) {

                    break;
                }
*/
                if (!distTo.containsKey(q) || distTo.get(p) + w < distTo.get(q)) {
                    distTo.put(q, distTo.get(p) + w);
                    edgeTo.put(q, p);
                    if (pq.contains(q)) {
                        pq.changePriority(q, distTo.get(q) + input.estimatedDistanceToGoal(q, end));
                    } else {
                        pq.add(q, distTo.get(q) + input.estimatedDistanceToGoal(q, end));
                    }
                }
            }
        }
        timeSpent = sw.elapsedTime();
        if (pq.size() == 0) {
            outcome = SolverOutcome.UNSOLVABLE;
        } else if (pq.getSmallest().equals(end)) {
            outcome = SolverOutcome.SOLVED;
            solutionWeight = distTo.get(end);
            Vertex i = end;
            solution = new LinkedList<>();
            while (i != null) {
                solution.addFirst(i);
                i = edgeTo.get(i);
            }
        } else {
            outcome = SolverOutcome.TIMEOUT;
        }
    }

    public SolverOutcome outcome() {
        return outcome;
    }

    public List<Vertex> solution() {
        if (outcome.equals(SolverOutcome.SOLVED)) {
            return solution;
        }
        return List.of();
    }

    public double solutionWeight() {if (outcome.equals(SolverOutcome.SOLVED)) {
        return solutionWeight;
    }
        return 0;}

    public int numStatesExplored() {return numStatesExplored;}

    public double explorationTime() {return timeSpent; }
}
