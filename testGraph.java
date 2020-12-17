package Graphes;

import java.util.ArrayList;
import java.util.List;

public class testGraph {
    public static void main(String[] args) {
        // Test du graphe du dernier CC
        Graph graphe = new Graph();
        graphe.setDebug(true);
        graphe.addNode(new Node("1",new ArrayList<>(List.of("1","2","3")),new ArrayList<>(List.of("1"))));
        graphe.addNode(new Node("2",new ArrayList<>(List.of("3","4","5")),new ArrayList<>(List.of("1"))));
        graphe.addNode(new Node("3",new ArrayList<>(List.of("3","4")),new ArrayList<>(List.of("1","2","3"))));
        graphe.addNode(new Node("4",new ArrayList<>(List.of("5")),new ArrayList<>(List.of("2","3"))));
        graphe.addNode(new Node("5",new ArrayList<>(List.of("5")),new ArrayList<>(List.of("2","4","5"))));

        graphe.buildArcs();

        System.out.println(graphe);
    }
}
