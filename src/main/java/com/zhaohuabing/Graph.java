package com.zhaohuabing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Graph {
  private Map<String, Node> nodes = new HashMap<String, Node>();

  public Graph() {}

  public void addEdge(String nodeName1, String nodeName2, int weight) {
    Node node1 = nodes.get(nodeName1);
    if (node1 == null) {
      node1 = new Node(nodeName1);
    }

    Node node2 = nodes.get(nodeName2);
    if (node2 == null) {
      node2 = new Node(nodeName2);
    }

    node1.addNeighbor(node2, weight);
    node2.addNeighbor(node1, weight);

    nodes.put(nodeName1, node1);
    nodes.put(nodeName2, node2);
  }

  public List<String> shortestPath(String startNodeName, String endNodeName) {
    // key node, value parent
    Map<String, String> parents = new HashMap<String, String>();
    Set<String> visited = new HashSet<String>();
    PriorityQueue<PathNode> temp = new PriorityQueue<PathNode>();

    PathNode start = new PathNode(startNodeName, null, 0);
    temp.add(start);

    while (temp.size() > 0) {
      PathNode currentPathNode = temp.remove();

      if (!visited.contains(currentPathNode.name)) {
        Node currentNode = nodes.get(currentPathNode.name);
        parents.put(currentPathNode.name, currentPathNode.parent);
        visited.add(currentPathNode.name);

        // return the shortest path if end node is reached
        if (currentPathNode.name.equals(endNodeName)) {
          return getPath(parents, endNodeName);
        }

        Node[] neighbors = nodes.get(currentPathNode.name).getNeighbors();
        for (int i = 0; i < neighbors.length; i++) {
          Node neighbor = neighbors[i];

          int distance2root =
              currentPathNode.distance2root + currentNode.getNeighborDistance(neighbor);
          // PriorityQueue ensure that the node with shortest distance to the root is put at the
          // head of the queue
          temp.add(new PathNode(neighbor.name, currentPathNode.name, distance2root));
        }

        System.out.println("current node: " + currentPathNode.name);
        System.out.println("PriorityQueue: " + temp);
        System.out.println("Parents: " + parents);
        System.out.println("Visited: " + visited);
        System.out.println("");
      }
    }
    return null;
  }

  private List<String> getPath(Map<String, String> parents, String endNodeName) {
    List<String> path = new ArrayList<String>();
    String node = endNodeName;
    while (node != null) {
      path.add(0, node);
      String parent = parents.get(node);
      node = parent;
    }
    return path;
  }
}

class Node {
  String name;
  // key: neighbor; value: distance from this node to the neighbor
  Map<Node, Integer> neighbors = new HashMap<Node, Integer>();

  public Node(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void addNeighbor(Node neighbor, int distance) {
    neighbors.put(neighbor, distance);
  }

  public Node[] getNeighbors() {
    Node[] result = new Node[neighbors.size()];
    neighbors.keySet().toArray(result);
    return result;
  }

  public int getNeighborDistance(Node node) {
    return neighbors.get(node);
  }

  public String toString() {
    return this.name;
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }

  @Override
  public boolean equals(Object another) {
    return name.equals(((Node) another).name);
  }
}

class PathNode implements Comparable<PathNode> {
  String name;
  String parent;
  // distance to the root
  int distance2root;

  public PathNode(String name, String parent, int distance2root) {
    this.name = name;
    this.parent = parent;
    this.distance2root = distance2root;
  }

  @Override
  public int compareTo(PathNode another) {
    return distance2root - another.distance2root;
  }

  @Override
  public String toString() {
    return "(" + this.name + "," + this.parent + "," + this.distance2root + ")";
  }
}
