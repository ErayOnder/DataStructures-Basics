package Graphs;

import java.util.HashMap;

public class UndirectedWeightedGraph<V> extends BaseGraph<V> {

  private HashMap<V, HashMap<V, Float>> adj_list = new HashMap<>();

  @Override
  public String toString() {
    String tmp = "Undirected Weighted Graph";
    return tmp;
  }

  @Override
  public void insertVertex(V v) {
    adj_list.putIfAbsent(v, new HashMap<>());     
  }

  @Override
  public V removeVertex(V v) {
    if (!adj_list.containsKey(v)) {
      return null;
    }
    for (V vertex: adj_list.get(v).keySet()) {
      adj_list.get(vertex).remove(v);
    }
    adj_list.remove(v);
    return v;
  }

  @Override
  public boolean areAdjacent(V v1, V v2) {
    return adj_list.containsKey(v1) && adj_list.containsKey(v2) && adj_list.get(v1).containsKey(v2) && adj_list.get(v2).containsKey(v1);
  }

  @Override
  public void insertEdge(V source, V target) {
    insertEdge(source, target, 1);
  }

  @Override
  public void insertEdge(V source, V target, float weight) {
    insertVertex(source);
    insertVertex(target);
    adj_list.get(source).put(target, weight);
    adj_list.get(target).put(source, weight);
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
    if (areAdjacent(source, target)) {
      return adj_list.get(source).get(target);
    }
    return Float.MAX_VALUE;
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
    for (HashMap<V, Float> edges: adj_list.values()) {
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
    return true;
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
      return adj_list.get(v).keySet();
    }
    return null;
  }

  @Override
  public Iterable<V> incomingNeighbors(V v) {
    return outgoingNeighbors(v);
  }

}