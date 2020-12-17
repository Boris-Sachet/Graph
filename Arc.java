package Graphes;

public class Arc implements Comparable<Arc> {
    private final Node begin;
    private final Node end;
    private Integer value;

    /** No value constructor
     * @param begin node from which the arc start
     * @param end node from which the arc end
     */
    public Arc(Node begin, Node end){
        this.begin = begin;
        this.end = end;
        this.value = -666;
    }

    /** No value constructor
     * @param begin node from which the arc start
     * @param end node from which the arc end
     * @param value cost of this arc
     */
    public Arc(Node begin, Node end, Integer value){
        this.begin = begin;
        this.end = end;
        this.value = value;
    }

    // GETTERS
    /** @return the starting node of the arc*/
    public Node getBegin() {
        return begin;
    }

    /** @return the ending node of the arc*/
    public Node getEnd() {
        return end;
    }
    /** @return the cost of the arc*/
    public Integer getValue() {
        return value;
    }

    // SETTERS
    /** @param value cost of this arc*/
    public void setValue(Integer value) {
        this.value = value;
    }

    // MISCS
    /** Convert the param of this arc in a string
     * @return string view of the object*/
    @Override
    public String toString(){
        if (value != -666){return "("+begin.getName()+","+end.getName()+"("+value+")"+")";}
        return "("+begin.getName()+","+end.getName()+")";
    }

    /**
     * Compare the name of the starting node of this arc to the name of the starting node of the parameter
     * @param arc Arc to compare this object with
     * @return the value 0 if the argument string is equal to this string;
     *         a value less than 0 if this string is lexicographically less than the string argument;
     *         and a value greater than 0 if this string is lexicographically greater than the string argument.*/
    @Override
    public int compareTo(Arc arc) {
        if (begin.getName() == null || arc.getBegin().getName() == null) {return 0;}
        return begin.getName().compareTo(arc.begin.getName());
    }
}
