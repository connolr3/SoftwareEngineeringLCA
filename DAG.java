/*The following sources were used in my implementation
 * https://github.com/dowlind1/CS3012/blob/DAG/DAG
 * https://alvinalexander.com/java/jwarehouse/eclipse/org.eclipse.ui.workbench.texteditor/src/org/eclipse/ui/internal/texteditor/rulers/DAG.java.shtml
 * CS2010 Algorithms and Data Structures notes (TCD) Ivana Dusparic
 */

public class DAG{
	private int V;//number of vertices 
	private int E;//# of edges 
	private int[][] adj; //adjacency list for vertex v 
	//uses indicator variable i.e: 1 means edge present 0 means no edge exists
	private int[] outdegree;//number of edges leading out of vertex v
	private int[] indegree; //number of edges leading into of vertex v
	private int[] visited;  //vertices that have been visited

	public DAG(int V){
		if(V<0){
			throw new IllegalArgumentException("A graph must have at least one vertice");
		}
		else{
			this.V = V;
			this.E = 0;
			indegree = new int[V];
			outdegree = new int[V];
			visited = new int[V];
			adj = new int[V][V];
			for(int i = 0; i<V; i++){
				for(int j=0;j<V;j++){
					adj[i][j] = 0;
				}
			}
		}
	}

	//number of vertices
	public int V(){
		return V;
	}

	//number of edges
	public int E(){
		return E;
	}

	//throws illegal exception if vertex put in less than zero / doesn't exist
	private void checkVertex(int v){
		if((v<0)||(v>=V))
			throw new IllegalArgumentException("Vertexe doesn't exist");
	}

	//adds directed edge from v to w
	public void addEdge(int w, int v){
		checkVertex(v);
		checkVertex(w);
		adj[v][w]=1;//edge now exists 
		indegree[w]++;//w has an extra edge leading to it
		outdegree[v]++;//v has an extra node leading out of it
		E++;//increase number of edges
	}

	//Removes an edge from v to w
	public void removeEdge(int v, int w){
		checkVertex(v);
		checkVertex(w);
		adj[v][w]=0;//edge no longer exists
		indegree[w]--;//w has one less edge leading to it
		outdegree[v]--;//v has one less node leading out of it
		E--;//decrease number of edges
	}

	//number of edges out of vertex v
	public int outdegree(int v){
		checkVertex(v);
		return outdegree[v];
	}

	//number of edges into vertex v
	public int indegree(int v){
		checkVertex(v);
		return indegree[v];
	}

	//returns an array of the vertices adjacent from vertex v
	public int[] adj(int v){
		checkVertex(v);
		int[] temp = new int[outdegree[v]];
		int count =0;
		for(int i=0;i<V;i++){
			if(adj[v][i]==1){
				temp[count]=i;
				count++;
			}
		}
		return temp;
	}
	
	//true if graph is has a cycle, else false if acyclic
	public boolean cycle(){
		boolean acyclic=false;
		int count = 0;
		for (int i = 0;i<V;i++) {//reset the visited to 0 in case it acyclic is run multiple times
			visited[i]=0;
		}
		for(int i =0;i<V;i++){
			visited[count]=i;
			for(int j = 0; j<V;j++){
				for(int k=0;k<V;k++){
					if(visited[k]==j && adj[i][j]==1){
						System.out.println("gone  into if to set as true");
						acyclic=true;
						return acyclic;
					}
				}	
			}
			count++;
		}
		return acyclic;
	}

//Find the LCA in a DAG
public int findLCA(int v, int w){
	checkVertex(v);
	checkVertex(w);
	if(E>0 && (cycle()==false)){
		return getLCA(v,w);
	}
	else{
	//	System.out.println(E);
		  //System.out.println(V);
		//System.out.print(not_acyclic());
		throw new IllegalArgumentException("This graph is not acyclic therefore no LCA exists.");
	}
}

//helper function for LCA
private int getLCA(int v, int w){
	int[] vArray = new int[E];
	int[] wArray = new int[E];
	boolean[] vMarked = new boolean[V];
	boolean[] wMarked = new boolean[V];
	int vCount =0;
	int wCount = 0;
	vArray[vCount]=v;
	wArray[wCount]=w;
	for(int j=0; j<V;j++){//mark all vertices as not been visited yet
		vMarked[j]=false;
		wMarked[j]=false;
	}
	for(int i =0;i<V;i++){
		vMarked[v] =true;
		wMarked[w] =true;
		for(int j = 0; j<V;j++){
			if(adj[i][j]==1 && vMarked[i]){
				vCount++;
				vArray[vCount]=j;
				vMarked[j]=true;
			}
			if(adj[i][j]==1 && wMarked[i]){
				wCount++;
				wArray[wCount]=j;
				wMarked[j]=true;
			}
			if(wArray[wCount]==vArray[vCount]){
				return wArray[wCount];
			}
		}
	}
	return -1;
}  
}
