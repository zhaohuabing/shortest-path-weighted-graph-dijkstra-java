package com.zhaohuabing;

import static org.junit.Assert.assertEquals;

import java.util.List;
import org.junit.Test;

public class GraphTest {

  @Test
  public void testShortestPath1() {
    Graph graph = this.constructGraph();
    List<String> result = graph.shortestPath("A", "F");
    System.out.println("shortest path between A and F: " + result);
    assertEquals(result.size(), 5);
    assertEquals(result.get(0), "A");
    assertEquals(result.get(1), "C");
    assertEquals(result.get(2), "B");
    assertEquals(result.get(3), "D");
    assertEquals(result.get(4), "F");
  }

  @Test
  public void testShortestPath2() {
    Graph graph = this.constructGraph();
    List<String> result = graph.shortestPath("D", "A");
    System.out.println("shortest path between D and A: " + result);
    assertEquals(result.size(), 4);
    assertEquals(result.get(0), "D");
    assertEquals(result.get(1), "B");
    assertEquals(result.get(2), "C");
    assertEquals(result.get(3), "A");
  }

  @Test
  public void testShortestPath3() {
    Graph graph = this.constructGraph();
    List<String> result = graph.shortestPath("C", "F");
    System.out.println("shortest path between C and F: " + result);
    assertEquals(result.size(), 4);
    assertEquals(result.get(0), "C");
    assertEquals(result.get(1), "B");
    assertEquals(result.get(2), "D");
    assertEquals(result.get(3), "F");
  }

  /**
   * Create a graph for testing
   *
   *
   *         B    1     D   6
   *    5  .-+---------.'-------F
   *    .-'  |       .' |
   * A.'     |2    .'   |
   *   `.    |   .'4    |3
   *    1`-. | .'       |
   *        `.'---------+
   *         C     8    E
   *
   *
   */
  private Graph constructGraph() {
    Graph graph = new Graph();
    graph.addEdge("A", "B", 5);
    graph.addEdge("A", "C", 1);
    graph.addEdge("B", "C", 2);
    graph.addEdge("B", "D", 1);
    graph.addEdge("C", "D", 4);
    graph.addEdge("C", "E", 8);
    graph.addEdge("D", "E", 3);
    graph.addEdge("D", "F", 6);
    return graph;
  }
}
