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
	public void testSimpleGraphMatrix() {
		String simpleMatrix = "  1 2 3 4 5 " + "\n"+
	"1 0 1 0 0 1 " + "\n"+
	"2 1 0 0 0 1 " + "\n" +
	"3 0 0 0 1 1 " + "\n" +
	"4 0 0 1 0 1 " + "\n" +
	"5 1 1 1 1 0 " + "\n";
		
		// A matriz de adjacência está funcionando particialmente funcionando - a primeira linha e coluna não está ordenada
		Assert.assertEquals(simpleMatrix, graphManager.graphRepresentation(simpleGraph, "AM"));
		
	}
	
	@Test
	public void testSimpleGraphList() {
		String simpleList = "1 - 2 5 " + "\n" +
							"2 - 1 5 " + "\n" +
							"3 - 4 5 " + "\n" +
							"4 - 3 5 " + "\n" +
							"5 - 1 2 3 4 " + "\n";

		// Mesmo problema da matriz, a lista não está ordenada
		Assert.assertEquals(simpleList, graphManager.graphRepresentation(simpleGraph, "AL"));
	}
	
	@Test
	public void testWeightGraphMatrix() {
		String weightMatrix = "  1 2 3 4 5 6 7 8 9 10 " + "\n" +
				  			  "1 0 0.1 0 0 1 0 0 0 0 0 " + "\n" +
				  			  "2 0.1 0 0 0 0.2 -7.6 0 0 0 0 " + "\n" +
				  			  "3 0 0 0 -9.5 5 0 1 0 0 0 " + "\n" +
				  			  "4 0 0 -9.5 0 2.3 0 0 0 0 8 " + "\n" +
				  			  "5 1 0.2 5 2.3 0 0 0 0 0 0 " + "\n" +
				  			  "6 0 -7.6 0 0 0 0 2 0 0 0 " + "\n" +
				  			  "7 0 0 1 0 0 2 0 22 0 0 " + "\n" +
				  			  "8 0 0 0 0 0 0 22 0 -5.9 0 " + "\n" +
				  			  "9 0 0 0 0 0 0 0 -5.9 0 6 " + "\n" +
				  			  "10 0 0 0 8 0 0 0 0 6 0 " + "\n";


		Assert.assertEquals(weightMatrix, graphManager.graphRepresentation(weightedGraph, "AM"));
	}
	
	@Test
	public void testWeightList() {
		
		String weightList = "1 - 2(0.1) 5(1) " + "\n" +
							"2 - 1(0.1) 5(0.2) 6(-7.6) " + "\n" +
							"3 - 4(-9.5) 5(5) 7(1) " + "\n" +
							"4 - 3(-9.5) 5(2.3) 10(8) " + "\n" +
							"5 - 1(1) 2(0.2) 3(5) 4(2.3) " + "\n" +
							"6 - 2(-7.6) 7(2) " + "\n" +
							"7 - 3(1) 6(2) 8(22) " + "\n" +
							"8 - 7(22) 9(-5.9) " + "\n" +
							"9 - 8(-5.9) 10(6) " + "\n" +
							"10 - 4(8) 9(6) " + "\n";
		
		Assert.assertEquals(weightList, graphManager.graphRepresentation(weightedGraph, "AL"));
	}

}
