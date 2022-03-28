package fr.enst.pact42.sportbox;

public class CasierModel {

    private int id;
    private String typeMaterial;
    private String condition;
    private String localisation;
    private float lat;
    private float lg;
    private boolean access;

    public CasierModel(int id,String typeMaterial, String condition, String localisation,float lat, float lg, boolean access){
        this.typeMaterial=typeMaterial;
        this.condition=condition;
        this.lat=lat;
        this.lg=lg;
        this.localisation=localisation;
        this.access=access;
        this.id=id;
    }
     public String getTypeMaterial(){
        return typeMaterial;
     }

    public String getCondition() {
        return condition;
    }

    public String getLocalisation() {
        return localisation;
    }

    public boolean getAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }
}
