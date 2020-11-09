import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class lcaTest {

	//The first half test binary tree implementation, the second half test DAG 
	@Test
	public void testConstructor() {
		lca tree = new lca(); 
		assertEquals(null ,tree.root,"No node tree - root should be null");
		tree.root = new Node(1); 
		assertEquals(null ,tree.root.left,"One node tree - root.left should be null");
		assertEquals(null ,tree.root.right,"One node tree - root.right should be null");
	}
	@Test
	void testLCABasicCase() {
		lca tree = new lca(); 
		tree.root = new Node(1); 
		tree.root.left = new Node(2); 
		tree.root.right = new Node(3); 
		tree.root.left.left = new Node(4); 
		tree.root.left.right = new Node(5); 
		tree.root.right.left = new Node(6); 
		tree.root.right.right = new Node(7); 
		assertEquals(2,tree.findLCA(4,5),"LCA of 4,5");
		assertEquals(1,tree.findLCA(4,6),"LCA of 4,6");
		assertEquals(1,tree.findLCA(3,4),"LCA of 3,4");
		assertEquals(2,tree.findLCA(2,4),"LCA of 2,4"); 
	}
	@Test
	void testLCANullNodes() {
		lca tree = new lca(); 
		assertEquals(-1,tree.findLCA(4,5),"LCA of nodes not created");
		tree.root = new Node(1); 
		assertEquals(-1,tree.findLCA(1,5),"LCA with one node not created");
	}
	@Test
	void testLCASameNodes() {
		lca tree = new lca(); 
		tree.root = new Node(1); 
		assertEquals(1,tree.findLCA(1,1),"LCA of root and root");
		tree.root.left = new Node(2); 
		assertEquals(2,tree.findLCA(2,2),"LCA of same node");
	}
	
	//DAG TESTS
	@Test
	public void testConstructorDAG() {
		DAG dagtree =new DAG(8);
		DAG dagtree2 =new DAG(0);
	}
	
	
	@Test
	void testBasicLCAwithDAG() {
		//The tree being created can be visualised as;
		//https://algorithms.tutorialhorizon.com/topological-sort/
		DAG dagtree =new DAG(8);
		dagtree.addEdge(7, 5);
		dagtree.addEdge(7, 6);
		dagtree.addEdge(5, 4);
		dagtree.addEdge(6, 4);
		dagtree.addEdge(5, 2);
		dagtree.addEdge(2, 1);
		dagtree.addEdge(6, 3);
		dagtree.addEdge(3, 1);
		dagtree.addEdge(1, 0);
		lca tree = new lca(); 
		assertEquals(7,dagtree.findLCA(5,6),"LCA of 5 and 6");
		assertEquals(7,dagtree.findLCA(2,3),"LCA of 2 and 3");
		assertEquals(6,dagtree.findLCA(4,0),"LCA of 4 and 0");		
	}

	@Test
	void testLCANullNodesDAG() {
		DAG tree = new DAG(0); 
		assertEquals(-1,tree.findLCA(4,5),"LCA both nodes not created");
		DAG tree2 = new DAG(1); 
		assertEquals(-1,tree2.findLCA(0,5),"LCA with one node not created");
	}
	@Test
	void testLCASameNodesDAG() {
		DAG dagtree =new DAG(8);
		dagtree.addEdge(7, 5);
		dagtree.addEdge(7, 6);
		dagtree.addEdge(5, 4);
		dagtree.addEdge(6, 4);
		dagtree.addEdge(5, 2);
		dagtree.addEdge(2, 1);
		dagtree.addEdge(6, 3);
		dagtree.addEdge(3, 1);
		dagtree.addEdge(1, 0);
		assertEquals(5,dagtree.findLCA(5,5),"LCA of 5 and 5");
		assertEquals(7,dagtree.findLCA(7,7),"LCA of root and root");
		assertEquals(0,dagtree.findLCA(0,0),"LCA of 0 and 0");	
	}
	@Test
	void testLCAgraphwithcyclesDAG() {
		DAG dagtree =new DAG(5);
		dagtree.addEdge(0, 1);
		dagtree.addEdge(1, 2);
		dagtree.addEdge(2, 3);
		dagtree.addEdge(3, 4);
		dagtree.addEdge(4, 0); 
		assertEquals(-1,dagtree.findLCA(0,0),"LCA of cyclic graph");
		assertEquals(-1,dagtree.findLCA(3,2),"LCA of cyclic graph");
		assertEquals(-1,dagtree.findLCA(2,2),"LCA of cyclic graph");
	}

}
