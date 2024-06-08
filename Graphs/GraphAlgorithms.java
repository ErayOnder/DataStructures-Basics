package Graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * The class that holds graph algorithm implementations
 * - Depth first search
 * - Breadth first search
 * - Dijkstra's single-source all-destinations shortest path algorithm
*/
public class GraphAlgorithms<V extends Comparable<V>> {
  
  public static boolean usageCheck = false;
  
  public Iterable<V> iterableToSortedIterable(Iterable<V> inIterable) {
    usageCheck = true;
    List<V> sorted = new ArrayList<>();
    for (V i : inIterable) {
      sorted.add(i);
    }
    Collections.sort(sorted);
    return sorted;
  }
  
  /*
   * Runs depth first search on the given graph G and
   * returns a list of vertices in the visited order, 
   * starting from the startvertex.
   */
  public List<V> DFS(BaseGraph<V> G, V startVertex) {
    usageCheck = false;
    HashSet<V> visited = new HashSet<>();
    ArrayList<V> result = new ArrayList<>();
    dfsVisit(G, startVertex, visited, result);
    return result;
  }
  
  /*
   * Runs breadth first search on the given graph G and
   * returns a list of vertices in the visited order, 
   * starting from the startvertex.
   */
  public List<V> BFS(BaseGraph<V> G, V startVertex) {
    usageCheck = false;
    HashSet<V> visited = new HashSet<>();
    Queue<V> queue = new LinkedList<>();
    ArrayList<V> result = new ArrayList<>();

    visited.add(startVertex);
    queue.add(startVertex);
    while (!queue.isEmpty()) {
      V currentVertex = queue.poll();
      result.add(currentVertex);
      for (V neighbor: iterableToSortedIterable(G.outgoingNeighbors(currentVertex))) {
        if (!visited.contains(neighbor)) {
          visited.add(neighbor);
          queue.add(neighbor);
        }
      }
    }
    return result;
  }
  
  /*
   * Runs Dijkstras single source all-destinations shortest path 
   * algorithm on the given graph G and returns a map of vertices
   * and their associated minimum costs, starting from the startvertex.
   */
  public HashMap<V,Float> Dijkstras(BaseGraph<V> G, V startVertex) {
    usageCheck = false;
    HashMap<V, Float> distances = new HashMap<>();
    PriorityQueue<V> queue = new PriorityQueue<>(Comparator.comparing(distances::get));

    for (V vertex: G.vertices()) {
      distances.put(vertex, Float.MAX_VALUE);
    }
    distances.put(startVertex, 0f);
    queue.add(startVertex);
    while (!queue.isEmpty()) {
      V currentVertex = queue.poll();
      for (V neighbor: G.outgoingNeighbors(currentVertex)) {
        float newDist = distances.get(currentVertex) + G.getEdgeWeight(currentVertex, neighbor);
        if (newDist < distances.get(neighbor)) {
          distances.put(neighbor, newDist);
          queue.add(neighbor);
        } 
      }
    }
    Iterator<Map.Entry<V, Float>> iterator = distances.entrySet().iterator();
    while (iterator.hasNext()) {
        Map.Entry<V, Float> entry = iterator.next();
        if (entry.getValue().equals(Float.MAX_VALUE)) {
            iterator.remove();
        }
    }
    return distances;
  }
  
  /*
   *  Returns true if the given graph is cyclic, false otherwise
   */
  public boolean isCyclic(BaseGraph<V> G) {
    HashSet<V> visited = new HashSet<>();
    if (G.isDirected()) {
      HashSet<V> recStack = new HashSet<>();
      for (V vertex: G.vertices()) {
        if (directedCyclic(G, vertex, visited, recStack)) {
          return true;
        }
      }
    } else {
      for (V vertex: G.vertices()) {
        if (!visited.contains(vertex)) {
          if (undirectedCyclic(G, vertex, null, visited)) {
            return true;
          }
        }
      }
    }
    return false;
  }
  
  private void dfsVisit(BaseGraph<V> G, V vertex, HashSet<V> visited, List<V> result) {
    visited.add(vertex);
    result.add(vertex);
    for (V neighbor: iterableToSortedIterable(G.outgoingNeighbors(vertex))) {
      if (!visited.contains(neighbor)) {
        dfsVisit(G, neighbor, visited, result);
      }
    }
  }

  private boolean directedCyclic(BaseGraph<V> G, V vertex, HashSet<V> visited, HashSet<V> recStack) {
    if (recStack.contains(vertex)) {
      return true;
    }
    if (visited.contains(vertex)) {
      return false;
    }
    visited.add(vertex);
    recStack.add(vertex);
    for (V neighbor: G.outgoingNeighbors(vertex)) {
      if (directedCyclic(G, neighbor, visited, recStack)) {
        return true;
      }
    }
    recStack.remove(vertex);
    return false;
  }

  private boolean undirectedCyclic(BaseGraph<V> G, V vertex, V parent, HashSet<V> visited) {
    visited.add(vertex);
    for (V neighbor: G.outgoingNeighbors(vertex)) {
      if (!visited.contains(neighbor)) {
        if (undirectedCyclic(G, neighbor, vertex, visited)) {
          return true;
        }
      } else if (!neighbor.equals(parent)) {
        return true;
      }
    }
    return false;
  }

}