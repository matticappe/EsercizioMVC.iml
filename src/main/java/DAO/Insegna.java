package DAO;

public class Insegna {
    private String codDocente;
    private String corso;

    public Insegna(String codDocente, String corso) {
        this.codDocente = codDocente;
        this.corso = corso;
    }

    public String getCodDocente() {
        return codDocente;
    }

    public String getCorso() {
        return corso;
    }

    @Override
    public String toString() {
        return  codDocente + " " + corso;
    }
}
