public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.lerArquivo("test5.csv");
        graph.printTable();
        System.out.println("o tamanho do grafo é: "+graph.getSize());
        System.out.println("a quantidade de Arestas é: "+graph.getEdgesAmount());
        System.out.println("Depth: -=-=-=-=-=-=-=-=-=-=-");
        System.out.println(graph.depthSearch("0"));
        System.out.println("djikstra: -=-=-=-=-=-=-=-=-=-=-");
        //System.out.println(graph.dijkstra("0"));
        System.out.println("-=-=-=-=-=-=-=-=-=-=-");
        graph.printEdges();
        graph.printVertices();
        System.out.println(graph.prim());
//        System.out.println("------------------------------------------------------------------------------------");
//        Graph graph2 = new Graph(false,true);
//        graph2.lerArquivo("n3e2.csv");
//        graph2.printTable();
//        System.out.println("o tamanho do grafo é: "+graph2.getSize());
//        System.out.println("a quantidade de Arestas é: "+graph2.getEdgesAmount());
//        graph2.printEdges();
//        graph2.printVertices();
//
//        System.out.println("------------------------------------------------------------------------------------");
//        Graph graph3 = new Graph(false,true);
//        graph3.lerArquivo("n4e5.csv");
//        graph3.printTable();
//        System.out.println("o tamanho do grafo é: "+graph3.getSize());
//        System.out.println("a quantidade de Arestas é: "+graph3.getEdgesAmount());
//        graph3.printEdges();
//        graph3.printVertices();
    }
}
