package Graphes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.StringJoiner;

// TODO predecessors list builder since they're optionals to build the graph
// TODO remove
public class Graph {
    private final ArrayList<Node> nodes;
    private ArrayList<Arc> arcs;
    private boolean debug = false;

    //
    // CONSTRUCTOR
    //
    /**
     * Constructor*/
    public Graph(){
        this.nodes = new ArrayList<>();
        this.arcs = new ArrayList<>();
    }

    //
    // GETTERS
    //
    /**
     * @return the list of all nodes present in the graph*/
    public ArrayList<Node> listNodes (){return nodes;}

    /**
     * @return the list of all arcs present in the graph*/
    public ArrayList<Arc> listArcs() {return arcs;}

    /**
     * @param node to get successors of
     * @return list of of nodes names successors of this node*/
    public ArrayList<String> listSuccessors(Node node) {return node.getSuccessors();}

    /**
     * @param node to get successors of
     * @return list of of nodes names predecessors of this node*/
    public ArrayList<String> listPredecessors(Node node) {return node.getPredecessors();}

    //
    // ARCS METHODS
    //
    /**
     * Get an arc object from the graph, the arc must exist
     * @param begin node from which the arc start
     * @param end node from which the arc end
     * @return the arc between the two nodes if it exists, or null*/
    private Arc getArc(Node begin, Node end){
        for (Arc arc: listArcs()) {
            if (arc.getBegin() == begin && arc.getEnd() == end) {return arc;}
        }
        return null;
    }

    /**
     * Look for an arc between two nodes in the arc list of the graph
     * @param begin node from which the arc start
     * @param end node from which the arc end
     * @return true if an arc exist between the two nodes*/
    public Boolean existArc(Node begin, Node end){
        return existArc(begin,end,listArcs());
    }

    /**
     * Look for an arc between two nodes in one arc list
     * Private because graph user don't any other arc list
     * Static because it doesn't need to be and instance of the object
     * @param begin node from which the arc start
     * @param end node from which the arc end
     * @param arcsList a list of arcs
     * @return true if an arc exist between the two nodes*/
    private static Boolean existArc(Node begin, Node end, ArrayList<Arc> arcsList){
        for (Arc arc: arcsList) {
            if (arc.getBegin() == begin && arc.getEnd() == end) {return true;}
        }
        return false;
    }

    /**
     * Get the cost of an arc, the arc must exist
     * @param begin node from which the arc start
     * @param end node from which the arc end
     * @return the value of the requested arc, or -1 if it doesn't exist*/
    public Integer getArcValue (Node begin, Node end){
        if (existArc(begin,end)) {return Objects.requireNonNull(getArc(begin, end)).getValue();}
        return -1;
    }

    /**
     * Set the cost of an arc, the arc must exist
     * @param begin node from which the arc start
     * @param end node from which the arc end*/
    public void setArcValue (Node begin, Node end, Integer value){
        if (existArc(begin,end)) {Objects.requireNonNull(getArc(begin, end)).setValue(value);}
    }

    /**
     * Add a new arc between two
     * private method because arc list is auto generated from node successors
     * @param begin node from which the arc start
     * @param end node from which the arc end*/
    private void addArc(Node begin, Node end){
        if (!existArc(begin, end)){
            arcs.add(new Arc(begin,end));
        }
    }

    /**
     * Add a new valued arc between two nodes
     * private method because arc list is auto generated from node successors
     * @param begin node from which the arc start
     * @param end node from which the arc end
     * @param value cost of this arc*/
    private void addArc(Node begin, Node end, Integer value){
        if (!existArc(begin, end)){
            arcs.add(new Arc(begin,end,value));
        }
    }

    /**
     * Remove an arc between two nodes
     * private method because arc list is auto generated from node successors
     * @param begin node from which the arc start
     * @param end node from which the arc end*/
    private void removeArc(Node begin, Node end){
        if (existArc(begin, end)){arcs.remove(getArc(begin, end));}
    }

    //
    // NODE METHODS
    //
    /**
     * Access a node with just his name, each node must have an unique name
     * @param nodeName name of the node to retrieve
     * @return the node object corresponding to the requested name or null if it doesn't exist*/
    public Node getNode(String nodeName){
        for (Node node: nodes) {if (node.getName().equalsIgnoreCase(nodeName)) {return node;}}
        System.out.println("Error : node " + nodeName + " does not exist in this graph");
        return null;
    }

    /**
     * Access a node with just his name, each node must have an unique name
     * Private because not required outside this class
     * @param nodeName name of the node to retrieve
     * @return true if the node exist in graph*/
    private Boolean existNode(String nodeName){
        for (Node node: nodes) {if (node.getName().equalsIgnoreCase(nodeName)) {return true;}}
        return false;
    }

    /**
     * Access a node with just his name, each node must have an unique name
     * Private because not required outside this class
     * @param node node to check
     * @return true if the node exist in graph*/
    private Boolean existNode(Node node){
        return nodes.contains(node);
    }

    /**
     * Add a new node to the graph
     * @param node node to be added to the graph*/
    public void addNode(Node node){
        if (!existNode(node)) {nodes.add(node);}
    }

    /**
     * Remove a node from the graph
     * @param node node to be removed from the graph*/
    public void removeNode(Node node){
        if (existNode(node)) {nodes.remove(node);}
    }

    //
    // BUILDERS
    //
    /**
     * Build all the arcs from this node / to the successors of this node
     * @param node node to build arcs from*/
    private void buildArcsFromNode(Node node, ArrayList<Arc> arcsList){
        if (debug) System.out.println("Building arc from "+node.getName());

        for (String nodeName: node.getSuccessors()) {
            // If node exist
            if (existNode(nodeName)) {
                // If arc don't already exist
                if (!existArc(node,getNode(nodeName))){
                    // If arc don't already in the arcsList create it and add it to the list
                    if (!existArc(getNode(nodeName),node,arcsList)) {
//                        System.out.println("Arc=" + getArc(node,getNode(nodeName)));
                        arcsList.add(new Arc(node,getNode(nodeName)));
                        if (debug) System.out.println("arc from "+node.getName()+" to "+nodeName+" added");
                    } else {if (debug) System.out.println("arc from "+node.getName()+" to "+nodeName+" already exist");}
                } else {
                    // If exist add it to the list if it's not already in it
                    if (!arcsList.contains(getArc(node,getNode(nodeName)))) {
                        arcsList.add(getArc(node,getNode(nodeName)));
                        if (debug) System.out.println("existing arc from "+node.getName()+" to "+nodeName+" added");
                    }
                }
            }
            else {System.out.println("Error in node "+ node.getName() +" : successor " + nodeName + " does not exist in graph");}
        }
        if (debug) System.out.println();
    }

    /**
     * Build all the arcs from this node / to the successors of this node
     * @param node node to build arcs from*/
    private void buildArcsToNode(Node node, ArrayList<Arc> arcsList){
        if (debug) System.out.println("Building arc to "+node.getName());

        for (String nodeName: node.getPredecessors()) {
            // If node exist
            if (existNode(nodeName)) {
                // If arc don't already exist
                if (!existArc(getNode(nodeName),node)){
                    // If arc don't already in the arcList create it and add it to the list
                    if (!existArc(getNode(nodeName),node,arcsList)) {
                        arcsList.add(new Arc(getNode(nodeName),node));
                        if (debug) System.out.println("arc from "+node.getName()+" to "+nodeName+" added");
                    } else {if (debug) System.out.println("arc from "+node.getName()+" to "+nodeName+" already exist");}
                }
                // If exist add it to the list
                else {
                    if (!arcsList.contains(getArc(getNode(nodeName),node))) {
                        arcsList.add(getArc(getNode(nodeName),node));
                        if (debug) System.out.println("existing arc to "+node.getName()+" from "+nodeName+" added");
                    }
                }
            }
            else {if (debug) System.out.println("Error in node " + node.getName() +" : predecessor " + nodeName + " does not exist in graph");}
        }
        if (debug) System.out.println();
    }
    
    /**
     * Build the list of arcs of the graph*/
    public void buildArcs(){
        ArrayList<Arc> arcsList = new ArrayList<>();
        for (Node node: nodes) {
//            buildArcsFromNode(node,arcsList);
            buildArcsToNode(node,arcsList);
        }
        Collections.sort(arcsList);
        this.arcs = arcsList;
    }

    //
    // MISC
    //
    /**
     * Enable or disable graph debug mode / verbose
     * @param debug set to true to enable debug mode, false to disable it*/
    public void setDebug(boolean debug) { this.debug = debug; }

    //
    // PRINTERS
    //
    /**
     * Return the list of nodes
     * @return A multiline string listing all the nodes*/
    public String nodesToString(){
        StringBuilder nodeString = new StringBuilder();
        for (Node node: nodes) {
            nodeString.append(node).append(System.lineSeparator());
        }
        return nodeString.toString();
    }

    /**
     * Return the list of arcs
     * @return A multiline string listing all the arcs*/
    public String arcsToString(){
        StringJoiner arcsString = new StringJoiner(",");
        for (Arc arc: arcs) {
            arcsString.add(arc.toString());
        }
        return "{"+arcsString.toString()+"}";
    }
}
