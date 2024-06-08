package Graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

public class DirectedWeightedGraph<V> extends BaseGraph<V>  {

  private HashMap<V, HashMap<V, Float>> adj_list = new HashMap<>();
  
  @Override
  public String toString() {
    String tmp = "Directed Weighted Graph";
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
    adj_list.remove(v);
    for (HashMap<V, Float> neighbors: adj_list.values()) {
      if (neighbors.containsKey(v)) {
        neighbors.remove(v);
      }
    }
    return v;
  }

  @Override
  public boolean areAdjacent(V v1, V v2) {
    return adj_list.containsKey(v1) && adj_list.get(v1).containsKey(v2);
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
  }

  @Override
  public boolean removeEdge(V source, V target) {
    if (areAdjacent(source, target)) {
      adj_list.get(source).remove(target);
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
    return count;
  }

  @Override
  public boolean isDirected() {
    return true;
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
    if (!adj_list.containsKey(v)) {
      return -1;
    }
    int count = 0;
    for (HashMap<V, Float> neighbors: adj_list.values()) {
      if (neighbors.containsKey(v)) {
        count++;
      }
    }
    return count;
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
    if (!adj_list.containsKey(v)) {
      return null;
    }
    HashSet<V> incoming = new HashSet<>();
    for (Entry<V, HashMap<V, Float>> entry: adj_list.entrySet()) {
      if (entry.getValue().containsKey(v)) {
        incoming.add(entry.getKey());
      }
    }
    return incoming;
  }

}