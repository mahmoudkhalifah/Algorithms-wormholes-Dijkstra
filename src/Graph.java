public class Graph {
    private int numOfVertices;
    private int[][] matrix;
    private boolean[][] isSetMatrix;

    public Graph(int numOfVertices) {
        this.numOfVertices = numOfVertices;
        matrix = new int[numOfVertices][numOfVertices];
        isSetMatrix = new boolean[numOfVertices][numOfVertices];
    }

    public void addEdge(int source, int destination, int weight) {
        matrix[source][destination] = weight;
        isSetMatrix[source][destination] = true;
    }

    public int nearestVertex(int[] time,boolean[] visited) {
        int min=Integer.MIN_VALUE;
        int v = -1;
        for (int j=0;j<numOfVertices;j++) {
            if ( !visited[j] &&(v==-1 || time[j]<min )) {
                min = time[j];
                v=j;
            }
        }
        return v;
    }

    public int dijkstra() {
        int[] parents = new int[numOfVertices];
        int[] time = new int[numOfVertices];
        boolean[] visited = new boolean[numOfVertices];
        int newTime = 0;
        for (int i=1;i<numOfVertices;i++) {
            time[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }
        time[0] = 0;
        parents[0] = -1;

        for(int i=0;i<numOfVertices;i++) {
            int nearest = nearestVertex(time,visited);
            visited[nearest] = true;
            if(nearest==numOfVertices-1) {
                break;
            }
            for (int j = 0; j < numOfVertices; j++) {
                if (isSetMatrix[nearest][j] && !visited[j] && time[nearest] != Integer.MAX_VALUE) {
                    newTime = time[nearest] + matrix[nearest][j];
                    if (newTime < time[j]) {
                        time[j] = newTime;
                        parents[j] = nearest;
                    }
                }
            }
        }
        System.out.print("total time from planet [" + 0 + "] to planet [" + (numOfVertices-1) + "] is " + time[numOfVertices-1] + "s" + " path : 0 ");
        printBestPath(numOfVertices-1,parents);
        return time[numOfVertices-1];
    }
    public void printBestPath(int current,int[] parents){
        if (parents[current] == -1) {
            return;
        }
        printBestPath(parents[current], parents);
        System.out.print(current + " ");
    }

}
