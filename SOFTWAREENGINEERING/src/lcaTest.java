import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class lcaTest {

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
	@Test
	void testLCAwithDAG() {
		//The tree being created can be visualised as;
		//https://algorithms.tutorialhorizon.com/topological-sort/
		lca tree = new lca(); 
		tree.root = new Node(7); 
		tree.root.left = new Node(5); 
		tree.root.right = new Node(6); 
		tree.root.right.right = new Node(3); 
		tree.root.right.left = new Node(4);
		tree.root.left.right=new Node(4);
		tree.root.left.left=new Node(2);
		tree.root.left.left.right=new Node(1);
		tree.root.right.right.left = new Node(1); 
		tree.root.right.right.left=new Node(0);
		assertEquals(7,tree.findLCA(5,6),"LCA of 5 and 6");
		assertEquals(7,tree.findLCA(2,3),"LCA of 2 and 3");
		assertEquals(5,tree.findLCA(4,0),"LCA of 5 and 0");
	}


}
