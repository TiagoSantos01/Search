package com.tiagosantos.search.domain;

public class Artist {
    private String fistName;
    private String lastName;

    public Artist(){}
    public Artist(String fistName, String lastName){
        this.fistName= fistName;
        this.lastName= lastName;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName(){
        return String.format("%s %s",this.fistName,this.lastName);
    }
    @Override
    public String toString() {
        return "Artist{" +
                "fistName='" + fistName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
