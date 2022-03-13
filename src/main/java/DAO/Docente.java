package DAO;

public class Docente {
        private String account;
        private String password;
        private String ruolo;
        private String nomeCognome;
        private String attivo;

    public Docente(String account, String password, String ruolo, String nomeCognome, String attivo) {
        this.account = account;
        this.password = password;
        this.ruolo = ruolo;
        this.nomeCognome = nomeCognome;
        this.attivo = attivo;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getRuolo() {
        return ruolo;
    }

    public String getNomeCognome() {
        return nomeCognome;
    }

    public String getAttivo() {
        return attivo;
    }

    @Override
    public String toString() {
        return "Docente{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", ruolo='" + ruolo + '\'' +
                ", nomeCognome='" + nomeCognome + '\'' +
                ", attivo='" + attivo + '\'' +
                '}';
    }
}
