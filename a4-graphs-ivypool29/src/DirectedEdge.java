/******************************************************************************
 * THIS FILES IS FULLY IMPLEMENTED, DO NOT MODIFY
 *****************************************************************************/

import java.util.Objects;

/**
 * This class represents a given edge in a directed, weighted graph. This class
 * allows you to group together a source, destination, and weight for the edge
 * together into a single object. This object will then be used in conjunction
 * with the graph's adjacency list to build the graph.
 */
public class DirectedEdge implements Comparable<DirectedEdge> {
    public int from;        /**< The id for the source vertex */
    public int to;          /**< The id for the destination vertex */
    public double weight;   /**< The weight for the edge */

    /**
     * The default constructor for the DirectedEdge class
     * @param from the source id
     * @param to the destination id
     * @param weight The weight of the edge
     */
    public DirectedEdge(int from, int to, double weight){
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public int from() {
        return from;
    }

    public int to() {
        return to;
    }

    public double weight() {
        return weight;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectedEdge that = (DirectedEdge) o;
        return from == that.from &&
                to == that.to &&
                Double.compare(that.weight, weight) == 0;
    }

    public int hashCode() {
        return Objects.hash(from, to, weight);
    }

    public int compareTo(DirectedEdge o) {
        if(this.to == o.to() && this.from == o.from() && this.weight == o.weight()) return 0;
        else if(this.weight < o.weight) return -1;
        return 1;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("from:").append(from).append(" to:").append(to).append(" weight:").append(weight).append("\n");
        return s.toString();
    }
}
