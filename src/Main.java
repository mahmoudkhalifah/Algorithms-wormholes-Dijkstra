import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int source,destination,weight, numOfEdges=0;
        Graph graph;
        System.out.print("enter number of planets: ");                              //scan number of vertices
        int numOfVertices = scanner.nextInt();
        //to prevent invalid number of vertices
        while (numOfVertices < 2) {
            System.out.print("enter VALID number of planets: ");
            numOfVertices = scanner.nextInt();
        }

        System.out.print("enter number of wormholes: ");                            //scan number of edges
        numOfEdges=scanner.nextInt();
        //to prevent invalid number of edges
        while ((numOfEdges <= 0 || numOfEdges > numOfVertices*(numOfVertices-1))) {
            System.out.print("enter VALID number of wormholes: ");
            numOfEdges = scanner.nextInt();
        }
        graph = new Graph(numOfVertices);

        for (int i=0;i<numOfEdges;i++) {
            System.out.print("enter source: ");
            source = scanner.nextInt();

            System.out.print("enter destination: ");
            destination = scanner.nextInt();

            System.out.print("enter time: ");
            weight = scanner.nextInt();
            //to prevent self edges
            if (!(source==destination)) {
                //to prevent negative edges or out of bounding indices
                if (!(source>numOfVertices-1 || source<0 || destination>numOfVertices-1 || destination<0 || weight<0)) {
                    graph.addEdge(source, destination, weight);
                } else {
                    System.out.println("please check your inputs and try to enter this edge again");
                    i--;
                }
            }
            else {
                System.out.println("self edges are NOT allowed");
                i--;
            }
        }
        graph.dijkstra();

    }
}
