import java.util.ArrayList;

public class Vertex{
    //======================================================================//
    //                      Variáveis da classe vertex                      //
    //======================================================================//

    //<editor-fold Atributes>


    private String            name;
    private int               degree;
    private ArrayList<Edge>   connectedEdges = new ArrayList<>();
    private ArrayList<Vertex> connectedVertices = new ArrayList<>();

    //</editor-fold>

    //======================================================================//
    //                     Construtores da classe Vertex                    //
    //======================================================================//

    //<editor-fold Constructors>

    public Vertex(String name) {
        this.name = name;
    }

    //</editor-fold>

    //======================================================================//
    //                 Métodos Utilitários  da classe Vertex                //
    //======================================================================//

    //<editor-fold Methods>


    public Edge connectVertex(Boolean isDirectional, Vertex tgt, String sym){
        connectedEdges.add(new Edge(name+sym+tgt.getName(), isDirectional, this, tgt));
        ArrayList<Edge> tgtedges = tgt.getConnectedEdges();
        tgtedges.add(connectedEdges.get(connectedEdges.size()-1));
        tgt.setConnectedEdges(tgtedges);
        boolean test = false;
        for (Vertex v: connectedVertices) {
            if (v == tgt){
                test = true;
            }
        }
        if (!test){
            connectedVertices.add(tgt);
            ArrayList<Vertex> tgtvertices = tgt.getConnectedVertices();
            tgtvertices.add(this);
            tgt.setConnectedVertices(tgtvertices);
        }
        degree++;
        tgt.setDegree(tgt.getDegree()+1);
        return connectedEdges.get(connectedEdges.size()-1);
    }

    public Edge connectWeightedVertex(Boolean isDirectional, Vertex tgt, int weight, String sym){
        connectedEdges.add(new Edge(weight,name+sym+weight+sym+tgt.getName(), isDirectional, this, tgt));
        ArrayList<Edge> tgtedges = tgt.getConnectedEdges();
        tgtedges.add(connectedEdges.get(connectedEdges.size()-1));
        tgt.setConnectedEdges(tgtedges);
        boolean test = false;
        for (Vertex v: connectedVertices) {
            if (v == tgt){
                test = true;
            }
        }
        if (!test){
            connectedVertices.add(tgt);
            ArrayList<Vertex> tgtvertices = tgt.getConnectedVertices();
            tgtvertices.add(this);
            tgt.setConnectedVertices(tgtvertices);
        }
        degree++;
        tgt.setDegree(tgt.getDegree()+1);
        return connectedEdges.get(connectedEdges.size()-1);

    }

    public boolean checkConnection(Vertex v){
        for (Vertex tmp: connectedVertices) {
            if (tmp == v){
                return true;
            }
        }
        return false;
    }

    public Edge getConnection(Vertex v){
        for (Edge tmp: connectedEdges) {
            if (tmp.getSrc() == v || tmp.getTgt() == v){
                return tmp;
            }
        }
        return null;
    }

    public boolean checkConnectionType(Vertex tgt){
        if (!checkConnection(tgt)){
            return false;
        }
        else{
            for (Edge e : connectedEdges) {
                if (e.getTgt() == tgt){
                    return true;
                }
            }
            return false;
        }
    }
    //</editor-fold

    //======================================================================//
    //                     Construtores da classe Vertex                    //
    //======================================================================//

    //<editor-fold Getters Setters>


    public ArrayList<Edge> getConnectedEdges() {
        return connectedEdges;
    }

    public void setConnectedEdges(ArrayList<Edge> connectedEdges) {
        this.connectedEdges = connectedEdges;
    }

    public ArrayList<Vertex> getConnectedVertices() {
        return connectedVertices;
    }

    public void setConnectedVertices(ArrayList<Vertex> connectedVertices) {
        this.connectedVertices = connectedVertices;
    }

    public String getName() {
        return name;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    //</editor-fold>

    //======================================================================//
    //                       ToString da classe Vertex                      //
    //======================================================================//

    public String toString(){
        return getName();
    }
}