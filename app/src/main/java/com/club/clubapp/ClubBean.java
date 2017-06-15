package com.club.clubapp;

public class ClubBean {
    private String clubName;
    private String clubLoc;
    private String clubRating;

    public ClubBean(String clubName, String clubLoc, String clubRating){
        this.clubName = clubName;
        this.clubLoc = clubLoc;
        this.clubRating = clubRating;
    }

    public String getClubName() {
        return clubName;
    }

    public String getClubLoc() {
        return clubLoc;
    }

    public String getClubRating() {
        return clubRating;
    }
}
