package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import manager.GraphManageable;
import manager.GraphManager;
import model.graph.Graph;

public class GraphManagerTest {
	
	GraphManageable graphManager;
	Graph simpleGraph;
	Graph weightedGraph;
	Graph disconnectedGraph;
	
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
	public void testGraphRepresentation() {
		
		String simpleMatrix = "  1 2 3 4 5" + "\n"+
				"1 0 1 0 0 1" + "\n"+
				"2 1 0 0 0 1" + "\n" +
				"3 0 0 0 1 1" + "\n" +
				"4 0 0 1 0 1" + "\n" +
				"5 1 1 1 1 0" + "\n";
		
		Assert.assertEquals(simpleMatrix, graphManager.graphRepresentation(simpleGraph, "AM"));
		
	}

}
