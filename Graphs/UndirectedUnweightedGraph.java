package Graphs;

import java.util.HashMap;
import java.util.HashSet;

public class UndirectedUnweightedGraph<V> extends BaseGraph<V> {
  
  private HashMap<V, HashSet<V>> adj_list = new HashMap<>();

  @Override
  public String toString() {
    String tmp = "Undirected Unweighted Graph";
    return tmp;
  }

  @Override
  public void insertVertex(V v) {
    adj_list.putIfAbsent(v, new HashSet<>());
  }

  @Override
  public V removeVertex(V v) {
    if (!adj_list.containsKey(v)) {
      return null;
    }
    for (V vertex: adj_list.get(v)) {
      adj_list.get(vertex).remove(v);
    }
    adj_list.remove(v);
    return v;
  }

  @Override
  public boolean areAdjacent(V v1, V v2) {
    return adj_list.containsKey(v1) && adj_list.containsKey(v2) && adj_list.get(v1).contains(v2) && adj_list.get(v2).contains(v1);
  }

  @Override
  public void insertEdge(V source, V target) {
    insertVertex(source);
    insertVertex(target);
    adj_list.get(source).add(target);
    adj_list.get(target).add(source);
  }

  @Override
  public void insertEdge(V source, V target, float weight) {
    insertEdge(source, target); 
  }

  @Override
  public boolean removeEdge(V source, V target) {
    if (areAdjacent(source, target)) {
      adj_list.get(source).remove(target);
      adj_list.get(target).remove(source);
      return true;
    }
    return false;
  }

  @Override
  public float getEdgeWeight(V source, V target) {
    return areAdjacent(source, target) ? 1 : 0;
  }

  @Override
  public int numVertices() {
    return adj_list.size();
  }

  @Override
  public Iterable<V> vertices() {
    return adj_list.keySet();
  }

  @Override
  public int numEdges() {
    int count = 0;
    for (HashSet<V> edges: adj_list.values()) {
      count += edges.size();
    }
    return count / 2;
  }

  @Override
  public boolean isDirected() {
    return false;
  }

  @Override
  public boolean isWeighted() {
    return false;
  }

  @Override
  public int outDegree(V v) {
    return adj_list.containsKey(v) ? adj_list.get(v).size(): -1;
  }

  @Override
  public int inDegree(V v) {
    return outDegree(v);
  }

  @Override
  public Iterable<V> outgoingNeighbors(V v) {
    if (adj_list.containsKey(v)) {
      return adj_list.get(v);
    }
    return null;
  }

  @Override
  public Iterable<V> incomingNeighbors(V v) {
    return outgoingNeighbors(v);
  }

}