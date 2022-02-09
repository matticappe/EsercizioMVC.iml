package DAO;

public class Corso {
    private String titolo;

    public Corso(String titolo) {
        this.titolo = titolo;
    }

    public String getTitolo() {
        return titolo;
    }

    @Override
    public String toString() {
        return titolo + " ";
    }
}
