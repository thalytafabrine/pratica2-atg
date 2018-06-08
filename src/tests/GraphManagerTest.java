package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import manager.GraphManageable;
import manager.GraphManager;
import model.graph.Graph;
import model.vertex.Vertex;

public class GraphManagerTest {

	GraphManageable graphManager;
	
	Graph simpleGraph;
	Graph weightedGraph;
	Graph disconnectedGraph;

	
	Vertex initialVertex;
	
	String bfsResult;
	String realBFS;
	String dfsResult;
	String realDFS;
		
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
		String simpleMatrix = 
				
					"  1 2 3 4 5 " + "\n"+
					"1 0 1 0 0 1 " + "\n"+
					"2 1 0 0 0 1 " + "\n" +
					"3 0 0 0 1 1 " + "\n" +
					"4 0 0 1 0 1 " + "\n" +
					"5 1 1 1 1 0 " + "\n";
		
	
		Assert.assertEquals(simpleMatrix, graphManager.graphRepresentation(simpleGraph, "AM"));
		
	}
	
	@Test
	public void testSimpleGraphList() {
		String simpleList = 
				
				"1 - 2 5 " + "\n" +
				"2 - 1 5 " + "\n" +
				"3 - 4 5 " + "\n" +
				"4 - 3 5 " + "\n" +
				"5 - 1 2 3 4 " + "\n";

		Assert.assertEquals(simpleList, graphManager.graphRepresentation(simpleGraph, "AL"));
	}
	
	@Test
	public void testWeightGraphMatrix() {
		String weightMatrix = 
				
				"  1 2 3 4 5 6 7 8 9 10 " + "\n" +
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
		
		String weightList = 
				
				"1 - 2(0.1) 5(1) " + "\n" +
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
	
	@Test
	public void testBFSWithSimpleGraph() {	
		// InitialVertex = 1
		initialVertex = simpleGraph.getVertices().get(0);
		
		bfsResult = graphManager.BFS(simpleGraph, initialVertex);
        realBFS = "1 - 0 -" + System.lineSeparator() +
                "2 - 1 1" + System.lineSeparator()+
                "3 - 2 5" + System.lineSeparator()+
                "4 - 2 5" + System.lineSeparator()+
                "5 - 1 1";
        
		Assert.assertEquals(realBFS, bfsResult);
		
		// InitialVertex = 5
		initialVertex = simpleGraph.getVertices().get(2);
		
		bfsResult = graphManager.BFS(simpleGraph, initialVertex);
        realBFS = "1 - 1 5" + System.lineSeparator() +
                "2 - 1 5" + System.lineSeparator()+
                "3 - 1 5" + System.lineSeparator()+
                "4 - 1 5" + System.lineSeparator()+
                "5 - 0 -";
        
		Assert.assertEquals(realBFS, bfsResult);
		
		// InitialVertex = 3
		initialVertex = simpleGraph.getVertices().get(3);
		
		bfsResult = graphManager.BFS(simpleGraph, initialVertex);
        realBFS = "1 - 2 5" + System.lineSeparator() +
                "2 - 2 5" + System.lineSeparator()+
                "3 - 0 -" + System.lineSeparator()+
                "4 - 1 3" + System.lineSeparator()+
                "5 - 1 3";
        
		Assert.assertEquals(realBFS, bfsResult);
	}

	@Test
	public void testBFSWithWeightedGraph() {	
		// InitialVertex = 1
		initialVertex = weightedGraph.getVertices().get(0);
		
		bfsResult = graphManager.BFS(weightedGraph, initialVertex);
        realBFS = "1 - 0 -" + System.lineSeparator() +
                "2 - 1 1" + System.lineSeparator()+
                "3 - 2 5" + System.lineSeparator()+
                "4 - 2 5" + System.lineSeparator()+
                "5 - 1 1" + System.lineSeparator()+
                "6 - 2 2" + System.lineSeparator()+
                "7 - 3 6" + System.lineSeparator()+
                "8 - 4 7" + System.lineSeparator()+
                "9 - 4 10" + System.lineSeparator()+
                "10 - 3 4";
        
		Assert.assertEquals(realBFS, bfsResult);
		
		// InitialVertex = 8
		initialVertex = weightedGraph.getVertices().get(7);
		
		bfsResult = graphManager.BFS(weightedGraph, initialVertex);
        realBFS = "1 - 4 2" + System.lineSeparator() +
                "2 - 3 6" + System.lineSeparator()+
                "3 - 2 7" + System.lineSeparator()+
                "4 - 3 3" + System.lineSeparator()+
                "5 - 3 3" + System.lineSeparator()+
                "6 - 2 7" + System.lineSeparator()+
                "7 - 1 8" + System.lineSeparator()+
                "8 - 0 -" + System.lineSeparator()+
                "9 - 1 8" + System.lineSeparator()+
                "10 - 2 9";
        
		Assert.assertEquals(realBFS, bfsResult);
	}
	
	@Test
	public void testBFSWithDisconnectedGraph() {
		// InitialVertex = 1
		initialVertex = disconnectedGraph.getVertices().get(0);
		
		bfsResult = graphManager.BFS(disconnectedGraph, initialVertex);
        realBFS = "1 - 0 -" + System.lineSeparator() +
                "2 - 1 1" + System.lineSeparator()+
                "5 - 1 1" + System.lineSeparator()+
                "6 - 1 1";
        
		Assert.assertEquals(realBFS, bfsResult);
		
		// InitialVertex = 4
		initialVertex = disconnectedGraph.getVertices().get(4);
		
		bfsResult = graphManager.BFS(disconnectedGraph, initialVertex);
        realBFS = "3 - 1 4" + System.lineSeparator() +
                "4 - 0 -";
        
		Assert.assertEquals(realBFS, bfsResult);
		
		// InitialVertex = 6
		initialVertex = disconnectedGraph.getVertices().get(5);
		
		bfsResult = graphManager.BFS(disconnectedGraph, initialVertex);
		realBFS = "1 - 1 6" + System.lineSeparator() +
                "2 - 2 1" + System.lineSeparator()+
                "5 - 2 1" + System.lineSeparator()+
                "6 - 0 -";
		
		Assert.assertEquals(realBFS, bfsResult);
	}
	
	@Test
	public void testDFSWithSimpleGraph() {
		// InitialVertex = 1
		initialVertex = simpleGraph.getVertices().get(0);
		
		dfsResult = graphManager.DFS(simpleGraph, initialVertex);
		realDFS = "1 - 0 -" + System.lineSeparator() +
                "2 - 1 1" + System.lineSeparator()+
                "3 - 3 5" + System.lineSeparator()+
                "4 - 4 3" + System.lineSeparator()+
                "5 - 2 2";
		
		Assert.assertEquals(realDFS, dfsResult);
		
		// InitialVertex = 5
		initialVertex = simpleGraph.getVertices().get(2);
		
		dfsResult = graphManager.DFS(simpleGraph, initialVertex);
        realDFS = "5 - 0 -" + System.lineSeparator() +
                "2 - 1 5" + System.lineSeparator()+
                "1 - 2 2" + System.lineSeparator()+
                "3 - 1 5" + System.lineSeparator()+
                "4 - 2 3";
        
		Assert.assertEquals(realDFS, dfsResult);
		
		// InitialVertex = 3
		initialVertex = simpleGraph.getVertices().get(3);
				
		dfsResult = graphManager.DFS(simpleGraph, initialVertex);
        realDFS = "3 - 0 -" + System.lineSeparator() +
                "5 - 1 3" + System.lineSeparator()+
                "2 - 2 5" + System.lineSeparator()+
                "1 - 3 2" + System.lineSeparator()+
                "4 - 2 5";
        
		Assert.assertEquals(realDFS, dfsResult);
	}
	
	@Test
	public void testDFSWithWeightedGraph() {
		// InitialVertex = 1
		initialVertex = weightedGraph.getVertices().get(0);
		
		dfsResult = graphManager.DFS(weightedGraph, initialVertex);
        realDFS = "1 - 0 -" + System.lineSeparator() +
                "2 - 1 1" + System.lineSeparator()+
                "5 - 2 2" + System.lineSeparator()+
                "3 - 3 5" + System.lineSeparator()+
                "4 - 4 3" + System.lineSeparator()+
                "10 - 5 4" + System.lineSeparator()+
                "9 - 6 10" + System.lineSeparator()+
                "8 - 7 9" + System.lineSeparator()+
                "7 - 8 8" + System.lineSeparator()+
                "6 - 9 7";
        
		Assert.assertEquals(realDFS, dfsResult);
		
		// InitialVertex = 8
		initialVertex = weightedGraph.getVertices().get(7);
		
		dfsResult = graphManager.DFS(weightedGraph, initialVertex);
        realDFS = "8 - 0 -" + System.lineSeparator() +
                "7 - 1 8" + System.lineSeparator()+
                "6 - 2 7" + System.lineSeparator()+
                "2 - 3 6" + System.lineSeparator()+
                "1 - 4 2" + System.lineSeparator()+
                "5 - 5 1" + System.lineSeparator()+
                "3 - 6 5" + System.lineSeparator()+
                "4 - 7 3" + System.lineSeparator()+
                "10 - 8 4" + System.lineSeparator()+
                "9 - 9 10";
        
		Assert.assertEquals(realDFS, dfsResult);
	}
	
	@Test
	public void testDFSWithDisconnectedGraph() {
		// InitialVertex = 1
		initialVertex = disconnectedGraph.getVertices().get(0);

		dfsResult = graphManager.DFS(disconnectedGraph, initialVertex);
		realDFS = "1 - 0 -" + System.lineSeparator() +
                "2 - 1 1" + System.lineSeparator()+
                "5 - 2 2" + System.lineSeparator()+
                "6 - 1 1";
		
		Assert.assertEquals(realDFS, dfsResult);
		
		// InitialVertex = 3
		initialVertex = disconnectedGraph.getVertices().get(4);

		dfsResult = graphManager.DFS(disconnectedGraph, initialVertex);
        realDFS = "3 - 0 -" + System.lineSeparator() +
                "4 - 1 3";
        
		Assert.assertEquals(realDFS, dfsResult);
		
		// InitialVertex = 6
		initialVertex = disconnectedGraph.getVertices().get(5);

		dfsResult = graphManager.DFS(disconnectedGraph, initialVertex);
		realDFS = "6 - 0 -" + System.lineSeparator() +
                "1 - 1 6" + System.lineSeparator()+
                "2 - 2 1" + System.lineSeparator()+
                "5 - 3 2";
		
		Assert.assertEquals(realDFS, dfsResult);
	}
	
	/**
	 * Não é possível gerar a MST de um grafo desconectado.
	 */
	@Test
	public void testMSTOfDisconnectedGraph() {
		String mst = graphManager.mst(disconnectedGraph);
		Assert.assertTrue(mst.isEmpty());
	}
	
	@Test
	public void testMSTOfCiclicGraph() {
		Graph ciclicGraph = graphManager.readGraph("resources/ciclicGraph.txt");
		String mst = graphManager.mst(ciclicGraph);
		
		String expectedMST = new StringBuilder()
				.append("1 - 1 -").append(System.lineSeparator())
				.append("2 - 1 2").append(System.lineSeparator())
				.append("3 - 2 3").toString();
		
		Assert.assertEquals(expectedMST, mst);
	}
}
