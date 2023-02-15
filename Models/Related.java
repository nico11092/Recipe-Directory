package Models;

public class Related {
    private String ref;
    private String name;

    public Related(String _ref, String _name){
        this.ref = _ref;
        this.name = _name;
    }

    public String getRef(){ return this.ref; }
    public String getName(){ return this.name; }

    @Override
    public String toString(){
        return "Related to n°"+this.ref+" : "+this.name;
    }
}
