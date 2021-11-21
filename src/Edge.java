
class Edge{

    private int weight = 1;
    private String  name;
    private Boolean isDirectional;
    private Vertex  src;
    private Vertex  tgt;

    public Edge(int weight, String name, Boolean isDirectional, Vertex src, Vertex tgt) {
        this.weight = weight;
        this.name = name;
        this.isDirectional = isDirectional;
        this.src = src;
        this.tgt = tgt;
    }

    public Edge(String name, Boolean isDirectional, Vertex src, Vertex tgt) {
        this.name = name;
        this.isDirectional = isDirectional;
        this.src = src;
        this.tgt = tgt;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public Vertex getSrc() {
        return src;
    }

    public Vertex getTgt() {
        return tgt;
    }
    // Getters-setters //
}
