import java.util.*;

public class krus{	
//	Disjoint Sets Data Structure
int fathers[] = new int[1000];

int find(int x){
	if(fathers[x] == x){
		return x;
	}
	return find(fathers[x]);
}

void unite(int x, int y){
	int fx = find(x);
	int fy = find(y);
	fathers[fx] = fy;
}

public static void main(String args[]){
	Scanner stdin = new Scanner(System.in);
	krus samp = new krus();
	//	initialize fathers for the disjoint sets
	for(int i=0;i<100;i++){
		samp.fathers[i]=i;
	}
	//	declaring the variables to load input
	int n,m;
	int a,b,w;
	ArrayList<pair3> edges = new ArrayList<pair3>();
	//	loading the input
	n = stdin.nextInt();
	m = stdin.nextInt();
	for(int i=0;i<m;i++){
		a = stdin.nextInt();
		b = stdin.nextInt();
		w = stdin.nextInt();
		edges.add(new pair3(w,a,b));
	}
	//	we print a line to separate input from output
	System.out.println();
	//	We firstly declare the variables for the MST
	int mst_weight = 0, mst_edges = 0;
	int	mst_ni = 0;
	//	STEP 1:	sort the list of edges
	 Collections.sort(edges, new Comparator<pair3>() {
	        @Override 
	        public int compare(pair3 p1, pair3 p2) {
	            return p1.w - p2.w;
	        }
	 });
	//	STEP 2-3:
	while( ( mst_edges < n-1) || (mst_ni < m) ){
		//	we brake the edge into the three integers they describe it
		a = edges.get(mst_ni).a;
		b = edges.get(mst_ni).b;
		w = edges.get(mst_ni).w;
		//	we check if the edge is ok to be included in the MST 
		//	if a and b are in different trees (if they are on the same we will create a cycle)
		if( samp.find(a) != samp.find(b) ) {
			//	we unite the two trees the edge connects
			samp.unite(a,b);
			//	we add the weight of the edge
			mst_weight += w;
			//	we print the edge and count it
			System.out.println(a + " " + b + " " + w);
			mst_edges++;
		}
		//	increase the index of the edge we will be chacking
		mst_ni++;
	}
	//	Presenting the WEIGHT
	System.out.println( "\nWeight of the MST is " + mst_weight);
	//	THE END
}

}
/*	The example's input:
7 9
1 2 4
7 2 2
6 2 3
6 5 1
5 3 20
4 3 6
1 4 7
2 5 2 
2 3 1

Explanation of Input:
N M
1      
..  { The edges described as a b w -> meaning (edge from a to b with weight w)
M
*/

