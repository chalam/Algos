package datastructures.graph;

/**
 * Created by Lamuel on 4/26/2016.
 */
public class FlowEdge {
    private final int v, w;         // from and to
    private final double capacity;  // capacity don't change
    private double flow;            // flow f(v, w) <= capacity(v, w)

    /**
     * Init Flow Edge
     * @param v
     * @param w
     * @param capacity
     */
    public FlowEdge(int v, int w, double capacity) {
        this.v        = v;
        this.w        = w;
        this.capacity = capacity;
    }

    /**
     * vertex this edge is from
     * @return v
     */
    public int from() {
        return v;
    }

    /**
     * vertex this edge is to
     * @return w
     */
    public int to() {
        return w;
    }

    /**
     * c - edge capacity
     * @return capacity
     */
    public double capacity() {
        return capacity;
    }

    /**
     * f - edge flow
     * @return flow
     */
    public double flow() {
        return flow;
    }

    /**
     * give the other vertex of the edge
     * @param vertex
     * @return vertex
     */
    public int other(int vertex) {
        if      (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new IllegalArgumentException("Illegal vertex");
    }

    /**
     * cf = c - f on the forward edge
     * @param vertex
     * @return flow
     */
    public double residualCapacityTo(int vertex) {
        if      (vertex == v) return flow;
        else if (vertex == w) return capacity - flow;
        else throw new IllegalArgumentException("Illegal vertex");
    }

    /**
     * residual = capacity - residual on the reverse edge
     * @param vertex
     * @param delta
     */
    public void addResidualFlowTo(int vertex, double delta) {
        if      (vertex == v) flow += delta;
        else if (vertex == w) flow -= delta;
        else throw new IllegalArgumentException("Illegal vertex");
    }

    /*************************************************************************
     *  Check integrity of data structure.
     ***************************************************************************/
    private boolean check() {
        if (!isCapacityConstraint())    System.out.println("failed Capacity constraints");
        if (!isSkewSymmetry())          System.out.println("failed Skew Symmetry");
        if (!isFlowConservation())      System.out.println("failed Flow conservation");
        return isCapacityConstraint() && isSkewSymmetry() && isFlowConservation();
    }

    private boolean isCapacityConstraint() {
        return flow <= capacity;
    }

    private boolean isSkewSymmetry() {
        return true;
    }

    private boolean isFlowConservation() {
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FlowEdge{");
        sb.append("v=").append(v);
        sb.append(", w=").append(w);
        sb.append(", capacity=").append(capacity);
        sb.append(", flow=").append(flow);
        sb.append('}');
        return sb.toString();
    }
}
