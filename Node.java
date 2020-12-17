package Graphes;

import java.util.ArrayList;

public class Node implements Comparable<Node> {
    private final String name;
    private int value;
    private final ArrayList<String> successors,predecessors;

    //
    // CONSTRUCTORS
    //
    /**
     * Name only constructor
     * @param name name of this node, must be unique in a graph
     */
    public Node(String name){
        this.name = name;
        this.value = -666;
        this.successors = new ArrayList<>();
        this.predecessors = new ArrayList<>();
    }

    /**
     * Name and value only constructor
     * @param name name of this node, must be unique in a graph
     * @param value cost of this node
     */
    public Node(String name, int value){
        this.name = name;
        this.value = value;
        this.successors = new ArrayList<>();
        this.predecessors = new ArrayList<>();
    }

    /**
     * Successors only constructor (predecessors lists can be build withing the graph and added after)
     * @param name name of this node, must be unique in a graph
     * @param successors list of names of the successors nodes of this node
     */
    public Node(String name, ArrayList<String> successors){
        this.name = name;
        this.value = -666;
        this.successors = successors;
        this.predecessors = new ArrayList<>();
    }

    /**
     * Full constructor no value
     * @param name name of this node, must be unique in a graph
     * @param successors list of names of the successors nodes of this node
     * @param predecessors list of names of the predecessors nodes of this node
     */
    public Node(String name, ArrayList<String> successors, ArrayList<String> predecessors){
        this.name = name;
        this.value = -666;
        this.successors = successors;
        this.predecessors = predecessors;
    }

    /**
     * Full constructor
     * @param name name of this node, must be unique in a graph
     * @param successors list of names of the successors nodes of this node
     * @param predecessors list of names of the predecessors nodes of this node
     * @param value cost of this node
     */
    public Node(String name, ArrayList<String> successors, ArrayList<String> predecessors, int value){
        this.name = name;
        this.value = value;
        this.successors = successors;
        this.predecessors = predecessors;
    }

    //
    // GETTERS
    //
    /**
     * @return name of the node*/
    public String getName() {return name;}

    /**
     * @return value of the node*/
    public int getValue() {return value;}

    /**
     * @return list of successors nodes of the node*/
    public ArrayList<String> getSuccessors() {return successors;}

    /**
     * @return list of predecessors nodes of the node*/
    public ArrayList<String> getPredecessors() {return predecessors;}

    //
    // Setters
    //
    /**
     * @param value the new value of the node*/
    public void setValue(Integer value){this.value = value;}

    //
    // PREDECESSORS / SUCCESSORS METHODS
    //
    /**
     * Add a successor to this node
     * @param successorName name a another node to be listed as successor of this node*/
    public void newSuccessor(String successorName) {successors.add(successorName);}

    /**
     * Remove a successor from this node
     * @param successorName name a another node to be removed as successor of this node*/
    public void removeSuccessor(String successorName) {successors.remove(successorName);}

    /**
     * Add a predecessor from this node
     * @param predecessorName name a another node to be listed as predecessor of this node*/
    public void newPredecessor(String predecessorName) {predecessors.add(predecessorName);}

    /**
     * Remove a predecessor from this node
     * @param predecessorName name a another node to be removed as predecessor of this node*/
    public void removePredecessor(String predecessorName) {predecessors.remove(predecessorName);}

    //
    // MISC
    //
    /**
     * @return a string description of the node*/
    @Override
    public String toString() {
        if (value != -666) {return name + " value=" + value + " predecessors=" + predecessors.toString() + " successors=" + successors.toString();}
        return name + " predecessors=" + predecessors.toString() + " successors=" + successors.toString();
    }

    /**
     * Compare the name of this node to the name of the parameter
     * @param node Node to compare this object with
     * @return the value 0 if the argument string is equal to this string;
     *         a value less than 0 if this string is lexicographically less than the string argument;
     *         and a value greater than 0 if this string is lexicographically greater than the string argument.*/
    @Override
    public int compareTo(Node node) {
        if (this.getName() == null || node.getName() == null) {return 0;}
        return this.getName().compareTo(node.getName());
    }
}
