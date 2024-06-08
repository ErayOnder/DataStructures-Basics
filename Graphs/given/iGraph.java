package Graphs.given;

/*
 * Copyright 2018 Baris Akgun
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted 
 * provided that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright notice, this list of 
 * conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of 
 * conditions and the following disclaimer in the documentation and/or other materials provided 
 * with the distribution.
 * 
 * 3. Neither the name of the copyright holder nor the names of its contributors may be used to 
 * endorse or promote products derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR 
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND 
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL 
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, 
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER 
 * IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT 
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * Most importantly, this software is provided for educational purposes and should not be used for
 * anything else.
 * 
 * AUTHORS: Baris Akgun
 *
 * DO NOT MODIFY 
 * 
 * */

/*
 * A graph interface similar to the one given in the book.
 * The difference is that there is no explicit edge representation
 * Moreover, you may want to have your own vertex class which holds the "V" class
 * 
 * An idea is to implement nested vertex and edge classes inside the graph that you are implementing
 * 
 */
public interface iGraph<V> { 
  
  /*
   *  Insert a vertex into the graph. 
   *  Does nothing if the vertex is already in the graph.
   *  
   */
  public void insertVertex(V v);
  
  /*
   *  Removes and return the given vertex from the graph. 
   *  Returns null if the vertex is not in the graph
   */
  public V removeVertex(V v);
  
  /*
   *  Returns true if two vertices are adjacent.
   *  For directed graphs, areAdjacent(v1, v2)
   *  - v1 -> v2 should return true !
   *  - v2 -> v1 should return false!
   *  Returns false if:
   *  - the vertices are not adjacent
   *  - v1 and/or v2 is not in the graph
   */
  public boolean areAdjacent(V v1, V v2);
  
  /*
   *  Creates an edge between vertices source and target
   *  CAREFUL: Adds the vertices if they are not already in the graph! This is different than most graph ADTs
   *  Does nothing if there is already an edge
   *  
   *  Equivalent to insertEdge(V target, V source) for undirected graphs
   *  Insert the edge with a default weight of your choosing if the graph is weighted
   *  
   */
  public void insertEdge(V source, V target);
  
  /*
   *  Creates an edge between vertices source and target with the given weight and returns it.
   *  CAREFUL: Adds the vertices if they are not already in the graph! This is different than most graph ADTs
   *  CAREFUL: If an edge already exists between the vertices: 
   *  - Updates the weight of the edge for weighted graphs and returns the update edge
   *  - Does nothing for unweighted graphs
   *  
   *  
   *  Equivalent to insertEdge(V target, V source, weight) for undirected graphs
   *  Safely ignore the weight parameters if the graph is unweighted i.e.
   *  make it equivalent to insertEdge(source, target) 
   *  
   */
  public void insertEdge(V source, V target, float weight);
  
  /*
   * Removes the edge between source and vertex.
   * Returns true if it is successful
   * Returns false if there is no such edge
   * 
   * Equivalent to removeEdge(target, source) for undirected graphs
   */
  public boolean removeEdge(V source, V target); 
  
  
  /*
   * Returns the weight of the edge between the given vertices:
   * - given weight for the weighted graphs
   * - 1 for unweighted graphs
   * If there is no edge or if one or both vertices do not exist:
   * - Return 0 for unweighted graphs
   * - Return Float.MAX_VALUE for weighted graphs
   * 
   * Equivalent to getEdgeWeight(target, source) for undirected graphs
   */
  public float getEdgeWeight(V source, V target);
  
  /*
   * Returns the number of vertices in the graph
   */
  public int numVertices();
  
  /*
   * Returns an iterable container of all the vertices in the graph
   */
  public Iterable<V> vertices();
  
  /*
   * Returns the number of edges in the graph
   */
  public int numEdges();
   
  /*
   * Returns true if the edges are directed
   */
  public boolean isDirected();
  
  /*
   * Returns true if the edges are weighted
   */
  public boolean isWeighted();
  
  /*
   *  Returns the number of outgoing edges for the vertex v.
   *  Return -1 if v is null
   */
  public int outDegree(V v);
  
  /*
   *  Returns the number of incoming for the vertex v.
   *  Return -1 if v is null or not in the graph
   *  Should be equal to  outDegree(v) for an undirected graph
   */
  public int inDegree(V v);
   
  /*
   *   Returns an iterable container of all the outgoing neighbors of the vertex x
   *   Return null if v is null or not in the graph
   */
  public Iterable<V> outgoingNeighbors(V v);
  
  /*
   *   Returns an iterable container of all the outgoing neighbors of the vertex x
   *   Return null if v is null or not in the graph
   *   
   *   Should be equal to  outgoingNeighbors(v) for an undirected graph
   */
  public Iterable<V> incomingNeighbors(V v);

}
