package fr.enst.pact42.sportbox;

public class EventModel {

    private String typeEvent;
    private String localisation;
    private String level;
    private int nbParticipations;
    private int limitParticipations;

    public EventModel(String typeEvent, String localisation, String level, int nbParticipants, int participations){
        this.typeEvent=typeEvent;
        this.localisation=localisation;
        this.level=level;
        this.nbParticipations=nbParticipants;
        limitParticipations=participations;
    }
    public boolean isAvailable(){
        return nbParticipations!=limitParticipations;
    }

    public String getTypeEvent() {
        return typeEvent;
    }

    public int getNbParticipations() {
        return nbParticipations;
    }

    public void setNbParticipations(int nbParticipations) {
        this.nbParticipations = nbParticipations;
    }

    public String getLocalisation() {
        return localisation;
    }

    public int getLimitParticipations() {
        return limitParticipations;
    }

    public String getLevel() {
        return level;
    }
}
