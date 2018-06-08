package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import manager.GraphManageable;
import manager.GraphManager;
import model.graph.Graph;
import model.vertex.VNormal;
import model.vertex.VWeighted;
import model.vertex.Vertex;

public class GraphManagerTest {
	
	GraphManageable graphManager;
	Graph simpleGraph;
	Graph weightedGraph;
	Graph disconnectedGraph;
	Vertex initialVertex;
	Vertex finalVertex;
	
	@Before
	public void setUp() {
		graphManager = new GraphManager();
		simpleGraph = graphManager.readGraph("resources/simpleGraph.txt");
		weightedGraph = graphManager.readWeightedGraph("resources/weightedGraph.txt");
		disconnectedGraph = graphManager.readGraph("resources/disconnectedGraph.txt");
	}
	
	@Test
	public void testGetVertexNumber() {
		Assert.assertEquals(5, graphManager.getVertexNumber(simpleGraph));
		Assert.assertEquals(10, graphManager.getVertexNumber(weightedGraph));
		Assert.assertEquals(6, graphManager.getVertexNumber(disconnectedGraph));
	}
	
	@Test
	public void testGetEdgeNumber() {
		Assert.assertEquals(6, graphManager.getEdgeNumber(simpleGraph));
		Assert.assertEquals(13, graphManager.getEdgeNumber(weightedGraph));
		Assert.assertEquals(5, graphManager.getEdgeNumber(disconnectedGraph));
	}
	
	@Test
	public void testGetMeanEdge() {
		float meanEdgeSimpleGraph = (2*6)/(float)5;
		float meanEdgeWeightedGraph =  (2*13)/(float)10;
		float meanEdgeDisconnectedGraph =  (2*5)/(float)6;

		Assert.assertEquals(meanEdgeSimpleGraph, graphManager.getMeanEdge(simpleGraph), 0);
		Assert.assertEquals(meanEdgeWeightedGraph, graphManager.getMeanEdge(weightedGraph), 0);
		Assert.assertEquals(meanEdgeDisconnectedGraph, graphManager.getMeanEdge(disconnectedGraph), 0);
	}
	
	@Test
	public void testConnected() {
		Assert.assertTrue(graphManager.connected(simpleGraph));
		Assert.assertFalse(graphManager.connected(disconnectedGraph));
		Assert.assertTrue(graphManager.connected(weightedGraph));
	}
	
	@Test
	public void testShortestPathSimpleGraph() {
		initialVertex = new VNormal("1");
		finalVertex = new VNormal("4");
		String expected = "1 5 4";
		String actual = graphManager.shortestPath(simpleGraph, initialVertex, finalVertex);
		Assert.assertEquals(expected, actual);		
	}
	
	@Test
	public void testeShortesPathSimpleGraphReturning() {
		initialVertex = new VNormal("3");
		finalVertex = new VNormal("2");
		String expected = "3 5 2";
		String actual = graphManager.shortestPath(simpleGraph, initialVertex, finalVertex);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testShortestPathWeightedGraph() {
		initialVertex = new VWeighted("1");
		finalVertex = new VWeighted("10");
		String expected = "1 2 6 7 3 4 10";
		String actual = graphManager.shortestPath(weightedGraph, initialVertex, finalVertex);
		Assert.assertEquals(expected, actual);
	}	
}
