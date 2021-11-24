import utilities.Util;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.Array;
import java.util.*;

public class Graph {

    //======================================================================//
    //                      Variáveis da classe grafo                       //
    //======================================================================//

    //<editor-fold Atributes>
    ArrayList<Vertex> vertexList    = new ArrayList<>();
    ArrayList<Edge>   edgeList      = new ArrayList<>();
    int               size          = 0;
    Boolean           isDirectional = false;
    Boolean           isWeighted    = true;

    //</editor-fold>

    //======================================================================//
    //                     Construtores da classe grafo                     //
    //======================================================================//


    //<editor-fold Constructor>


    public Graph() {
    }

    public Graph(Boolean isDirectional, Boolean isWeighted) {
        this.isDirectional = isDirectional;
        this.isWeighted = isWeighted;
    }

    //</editor-fold>

    //======================================================================//
    //                 Métodos utilitários da classe Grafo                   //
    //======================================================================//

    //<editor-fold Main methods>


    public void lerArquivo(String nomearquivo) {
        try {
            RandomAccessFile arqEntrada = new RandomAccessFile(new File(nomearquivo), "r");

            // Rotulo dos vertices
            String linha = arqEntrada.readLine();
            String vertices[] = linha.split(";");
            for (int i = 0; i < vertices.length; i++) {
                createVertex(vertices[i]);
            }

            linha = arqEntrada.readLine();
            int nLin = 0; // Primeia linha da matriz
            while (linha != null) {
                String lin[] = linha.split(";");
                for (int j = 0; j < lin.length; j++) {
                    if(lin[j].equals("0")) {
                    }else{
                        connectWeightedVertices(vertices[j],"-", Integer.parseInt(lin[j]),vertices[nLin]);
                    }
                }
                nLin++;
                linha = arqEntrada.readLine();
            }
            arqEntrada.close();

        } catch (IOException e) {
            Util.createErrorMessage("Leitura de arquivo invalida!");
        } catch (NumberFormatException e) {
            Util.createErrorMessage("ERRO: Formato de numero invalido!");
        }
    }

    public void createVertex(String name){
        if (size>=1) {
            for (Vertex v : vertexList) {
                if (v.getName().equalsIgnoreCase(name)) {
                    Util.createWarnMessage("name already in use");
                    return;
                }
            }
            vertexList.add(new Vertex(name.substring(0,1)));
            size++;
        }
        else{
            vertexList.add(new Vertex(name.substring(0,1)));
            size++;
        }
    }

    public void connectVertices(String nameSrc, String sym, String nameTgt){
        if (isWeighted){
            Util.createWarnMessage("the Graph is Weighted, please use the correct method");
            return;
        }
        Vertex src = getVertexByName(nameSrc);
        Vertex tgt = getVertexByName(nameTgt);
        if (isDirectional){
            if (sym.equalsIgnoreCase("-")){
                Util.createWarnMessage("the graph is directional");
                return;
            }
            else if (sym.equalsIgnoreCase(">") || sym.equalsIgnoreCase("<")){
                Boolean lR = src.checkConnectionType(tgt);
                Boolean rL = tgt.checkConnectionType(src);
                if (sym.equalsIgnoreCase(">") && !lR){
                    edgeList.add(src.connectVertex(isDirectional,tgt, sym));
                }
                else if (sym.equalsIgnoreCase("<") && !rL){
                    edgeList.add(tgt.connectVertex(isDirectional,src, sym));
                }
                else{
                    Util.createWarnMessage("Connection already Exists");
                    return;
                }
            }
            else{
                Util.createWarnMessage("connection type unknown");
            }
        }
        else{
            if (sym.equalsIgnoreCase(">") || sym.equalsIgnoreCase("<")){
                Util.createWarnMessage("the graph is not directional");
                return;
            }
            else if (sym.equalsIgnoreCase("-")){
                if (src == null || tgt == null){
                    Util.createWarnMessage("it was not possible to fine one or both of the vertices");
                }
                else{
                    edgeList.add(src.connectVertex(isDirectional,tgt, sym));
                }
            }
            else{
                Util.createWarnMessage("connection type unknown");
            }
        }
    }

    public void connectWeightedVertices(String nameSrc, String sym,int weight, String nameTgt){
        if (!isWeighted){
            Util.createWarnMessage("The graph is not weighted, please use the correct method");
            return;
        }
        Vertex src = getVertexByName(nameSrc);
        Vertex tgt = getVertexByName(nameTgt);
        if (isDirectional){
            if (sym.equalsIgnoreCase("-")){
                Util.createWarnMessage("the graph is directional");
                return;
            }
            else if (sym.equalsIgnoreCase(">") || sym.equalsIgnoreCase("<")){
                Boolean lR = src.checkConnectionType(tgt);
                Boolean rL = tgt.checkConnectionType(src);
                if (sym.equalsIgnoreCase(">") && !lR){
                    edgeList.add(src.connectWeightedVertex(isDirectional,tgt,weight, sym));
                }
                else if (sym.equalsIgnoreCase("<") && !rL){
                    edgeList.add(tgt.connectWeightedVertex(isDirectional,src,weight, sym));
                }
                else{
                    Util.createWarnMessage("Connection already Exists");
                    return;
                }
            }
            else{
                Util.createWarnMessage("connection type unknown");
            }
        }
        else{
            if (sym.equalsIgnoreCase(">") || sym.equalsIgnoreCase("<")){
                Util.createWarnMessage("the graph is not directional");
                return;
            }
            else if (sym.equalsIgnoreCase("-")){
                if (src == null || tgt == null){
                    Util.createWarnMessage("it was not possible to fine one or both of the vertices");
                }
                else{
                    if (src.checkConnection(tgt) && sym.equalsIgnoreCase("-")){
                        Util.createWarnMessage("Connection already Exists");
                    }
                    else{
                        edgeList.add(src.connectWeightedVertex(isDirectional,tgt,weight, sym));
                    }
                }
            }
            else{
                Util.createWarnMessage("connection type unknown");
            }
        }
    }

    public Vertex getVertexByName(String name){
        for (Vertex v:vertexList
             ) {
            if (v.getName().equalsIgnoreCase(name)){
                return v;
            }
        }
        Util.createWarnMessage("no vertex named "+name+" found");
        return null;
    }

    //</editor-fold>

    //======================================================================//
    //                  Métodos de impressão classe grafo                   //
    //======================================================================//

    //<editor-fold Printing>


    public void printTable(){
        System.out.print("\n  ");
        for (Vertex v: vertexList) {
            System.out.print(" "+v.getName()+" ");
        }
        System.out.println();
        for (Vertex v: vertexList) {
            System.out.print(v.getName()+" ");
            for (Vertex v1: vertexList) {
                boolean found = false;
                for (Edge e : edgeList) {
                    if (e.getSrc() == v && e.getTgt() == v1) {
                        if (!found) {
                            System.out.print(Util.ANSI_YELLOW + " 1" + Util.ANSI_RESET + " ");
                            found = true;
                        }
                    }
                    else if (e.getSrc() == v1 && e.getTgt() == v) {
                        if (!found) {
                            if (isDirectional) {
                                System.out.print(Util.ANSI_GREEN + -1 + Util.ANSI_RESET + " ");
                            } else {
                                System.out.print(Util.ANSI_YELLOW + " 1" + Util.ANSI_RESET + " ");
                            }
                            found = true;
                        }
                    }
                }
                if (!found) {
                    System.out.print(Util.ANSI_WHITE + " 0" + Util.ANSI_RESET + " ");
                }
            }
            System.out.println();
        }
    }

    public void printVertices(){
        System.out.printf("%s===================%s\n",Util.ANSI_GREEN,Util.ANSI_RESET);
        System.out.printf("%s     Vertices      %s\n",Util.ANSI_GREEN,Util.ANSI_RESET);
        System.out.printf("%s%-10s   %6s%s\n",Util.ANSI_YELLOW,"name","Degree",Util.ANSI_RESET);
        System.out.printf("%s===================%s\n",Util.ANSI_GREEN,Util.ANSI_RESET);
        for (Vertex v:vertexList) {
            System.out.printf("%-10s  %5d\n",v.getName(),v.getDegree());
        }
        System.out.printf("%s===================%s\n",Util.ANSI_GREEN,Util.ANSI_RESET);
    }

    public void printEdges(){
        System.out.printf("%s===================%s\n",Util.ANSI_GREEN,Util.ANSI_RESET);
        System.out.printf("%s       Edges       %s\n",Util.ANSI_GREEN,Util.ANSI_RESET);
        System.out.printf("%s%-10s   %6s%s\n",Util.ANSI_YELLOW,"name","Weight",Util.ANSI_RESET);
        System.out.printf("%s===================%s\n",Util.ANSI_GREEN,Util.ANSI_RESET);
        for (Edge e: edgeList) {
            System.out.printf("%-10s   %5d\n",e.getName(),e.getWeight());
        }
        System.out.printf("%s===================%s\n",Util.ANSI_GREEN,Util.ANSI_RESET);
    }

    //</editor-fold>

    //======================================================================//
    //                   Getters e Setters da classe grafo                  //
    //======================================================================//

    //<editor-fold Getters setters>


    public int getSize() {
        return size;
    }

    public Boolean getDirectional() {
        return isDirectional;
    }

    public int getEdgesAmount(){
        return edgeList.size();
    }

    //</editor-fold Getters setters>

    //======================================================================//
    //                        Algoritmos Solicitados                        //
    //======================================================================//

    //Categoria: Busca em Grafos
    //Algoritmo: Busca em profundidade(DFS)
    public String depthSearch(String name){
        Vertex v = getVertexByName(name);
        if (v == null){
            Util.createWarnMessage("Vertex não encontrado");
            return "";
        }
        ArrayList<Vertex> parentList = new ArrayList<Vertex>();
        ArrayList<Vertex> vList = new ArrayList<>();
        vList.add(v);
        do{
            boolean tmp = false;
            for (Vertex ver : v.getConnectedVertices()) {
                if (!vList.contains(ver)) {
                    parentList.add(v);
                    vList.add(ver);
                    v = ver;
                    tmp = true;
                    break;
                }
            }
            if (!tmp){
                v = parentList.get(parentList.size()-1);
                parentList.remove(parentList.size()-1);
            }
        }while(parentList.size() > 0);
        String returnList = "";
        for (Vertex ver : vList) {
            returnList+= ver.getName()+" - ";
        }
        return returnList;
    }

    //Categoria: Caminhos mínimos
    //Algoritmo: Dijkstra
    //Algoritmo construido com base em pseudocódigo encontrado em: https://www.youtube.com/watch?v=_lHSawdgXpI

    public String dijkstra(String startingVertex){
        Vertex             v0         = getVertexByName(startingVertex);
        ArrayList<Vertex>  vertices   = (ArrayList<Vertex>) vertexList.clone();
        ArrayList<Vertex>  border     = new ArrayList<Vertex>();
        ArrayList<Integer> cost       = new ArrayList<Integer>();
        ArrayList<Vertex>  parentList = new ArrayList<Vertex>();
        Vertex             u          = v0;


        for (int i = 0; i < vertices.size(); i++) {
            cost.add(Integer.MAX_VALUE);
            parentList.add(new Vertex("-1"));
        }
        cost.set(vertices.indexOf(v0), 0);
        border = (ArrayList<Vertex>) vertices.clone();

        while (border.size() > 0){
            int tmpcost = Integer.MAX_VALUE;
            for (int i = 0; i < border.size(); i++) {
                if (cost.get(vertices.indexOf(border.get(i))) < tmpcost){
                    tmpcost = cost.get(vertices.indexOf(border.get(i)));
                    u = border.get(i);
                }
            }
            border.remove(u);
            for (Vertex v: u.getConnectedVertices()) {
                int costofV = cost.get(vertices.indexOf(v));
                int costofU = cost.get(vertices.indexOf(u));
                int costofUV = v.getConnection(u).getWeight();
                if (costofV > costofU+costofUV && border.contains(v)){
                    cost.set(vertices.indexOf(v), cost.get(vertices.indexOf(u))+v.getConnection(u).getWeight());
                    parentList.set(vertices.indexOf(v), u);
                }
            }
        }

        //===================================================//
        // Construção do oputput                             //
        //===================================================//

        String output = "Vertex   /  Peso  /  Rota \n";
        for (Vertex v: vertices) {
            output += String.format("%-7s%2s/%2s%-4d%2s/%2s",v.getName(),"","",cost.get(vertices.indexOf(v)),"","");
            String costString = v.getName();
            if (v == v0){
                output += v.getName()+"\n";
                continue;
            }
            Vertex parent = parentList.get(vertices.indexOf(v));
            for (int i = 0; i < vertices.size(); i++) {
                costString = parent.getName() +" - " + costString;
                if (parent == v0){
                    break;
                }
                parent = parentList.get(vertices.indexOf(parent));

            }
            output+= costString + "\n";

        }
        return output;
    }

    public String prim(){
        ArrayList<Object> P = new ArrayList<Object>();
        int               i = 0;
        for (int j = 0; j < vertexList.size(); j++) {
            P.add(i, "-1");
        }
        P.set(i,0);
        do {
            Vertex vi = vertexList.get(i);
            Vertex j = null;
            int minweight = Integer.MAX_VALUE;
            for (Edge e: vi.getConnectedEdges()) {
                if (minweight> e.getWeight()){
                    Vertex vj;
                    if (e.getSrc() == vi){
                        vj = e.getTgt();
                    }
                    else{
                        vj = e.getSrc();
                    }
                    if (P.get(vertexList.indexOf(vj)) == "-1"){
                        j = vj;
                        minweight = e.getWeight();
                    }
                }
            }
            if (j != null){
                P.set(vertexList.indexOf(j), vi);
            }
            i++;
        }while (P.contains("-1"));

        String output = String.format("Prim:\n%5s%5s%5s\n", "v1", "wgt", "v2");
        for (int j = 1; j < vertexList.size(); j++) {
            Vertex p = (Vertex) P.get(j);
            String temp = vertexList.get(j).getConnection(p).getName()+"\n";
            output+= String.format("%5s%5s%5s", temp.split("-")[0], temp.split("-")[1], temp.split("-")[2]);
        }
        return output;
    }
    public boolean verifyEqualsHashSet(List<Object> list) {
        return new HashSet<Object>(list).size() <= 1;
    }

}