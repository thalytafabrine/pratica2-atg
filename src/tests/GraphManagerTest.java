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
		// Sem peso
		String simpleMatrix = "  1 2 3 4 5" + "\n"+
				"1 0 1 0 0 1" + "\n"+
				"2 1 0 0 0 1" + "\n" +
				"3 0 0 0 1 1" + "\n" +
				"4 0 0 1 0 1" + "\n" +
				"5 1 1 1 1 0" + "\n";
		
		Assert.assertEquals(simpleMatrix, graphManager.graphRepresentation(simpleGraph, "AM"));
		
		String simpleList = "1 - 2 5" + "\n" +
							"2 - 1 5" + "\n" +
							"3 - 4 5" + "\n" +
							"4 - 3 5" + "\n" +
							"5 - 1 2 3 4";
		
		Assert.assertEquals(simpleList, graphManager.graphRepresentation(simpleGraph, "AL"));
		
		// Com peso
		String weightMatrix = "  1 2 3 4 5" + "\n" +
							  "1 0 0.5 0 0 2" + "\n" +
							  "2 0.5 0 0 0 1" + "\n" +
							  "3 0 0 0 -2 4" + "\n" +
							  "4 0 0 -2 0 2" + "\n" +
							  "5 2 1 4 2 0";
		
		Assert.assertEquals(weightMatrix, graphManager.graphRepresentation(weightedGraph, "AM"));
		
		String weightList = "1 - 0.5 2" + "\n" +
							"2 - 0.5 1" + "\n" +
							"3 - -2 4" + "\n" +
							"4 - -2 2" + "\n" +
							"5 - 2 1 4 2";
		
		Assert.assertEquals(weightList, graphManager.graphRepresentation(weightedGraph, "AL"));
		
		
	}

}
