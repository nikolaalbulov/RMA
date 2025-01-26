package com.example.recepti;

import java.util.Objects;

public class Recepti {
    private String ime;
    private String sastojci;
    private String instrukcije;
    private int slika;
    private boolean sacuvan;

    public Recepti(String ime, String sastojci, String instrukcije, int slika, boolean sacuvan) {
        this.ime = ime;
        this.sastojci = sastojci;
        this.instrukcije = instrukcije;
        this.slika = slika;
        this.sacuvan = sacuvan;
    }

    public String getName() {
        return ime;
    }

    public String getSastojci() {
        return sastojci;
    }

    public String getInstrukcije() {
        return instrukcije;
    }

    public int getImageResId() {
        return slika;
    }

    public boolean isSacuvan() {
        return sacuvan;
    }

    public void setSacuvan(boolean sacuvan) {
        this.sacuvan = sacuvan;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recepti recepti = (Recepti) o;
        return slika == recepti.slika && sacuvan == recepti.sacuvan &&
                Objects.equals(ime, recepti.ime) &&
                Objects.equals(sastojci, recepti.sastojci) &&
                Objects.equals(instrukcije, recepti.instrukcije);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ime, sastojci, instrukcije, slika, sacuvan);
    }
}
