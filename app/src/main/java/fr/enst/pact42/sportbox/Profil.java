package fr.enst.pact42.sportbox;

import java.util.ArrayList;

public class Profil {

    final private int id;
    private String pseudo;
    private double lat;
    private double lg;
    private ArrayList<CasierModel> favorites;
    private ArrayList<CasierModel> openCasier;
    private ArrayList<EventModel> registrations;
    private ArrayList<EventModel> myEvents;

    public Profil(int id,String pseudo, double lat, double lg, ArrayList<CasierModel> favorites, ArrayList<CasierModel> openCasier, ArrayList<EventModel> registrations, ArrayList<EventModel> myEvents){
        this.pseudo=pseudo;
        this.lat=lat;
        this.lg=lg;
        this.favorites=favorites;
        this.openCasier=openCasier;
        this.registrations=registrations;
        this.myEvents=myEvents;
        this.id=id;
    }



}
