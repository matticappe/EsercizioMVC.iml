package DAO;

public class Utente {
    private String account;
    private String password;
    private int ruolo;


    public Utente(String account, String password, int ruolo) {
        this.account = account;
        this.password = password;
        this.ruolo = ruolo;
        //ruolo ==1 admin
        //ruolo ==2
        //ruolo ==0 to be defined
    }


    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public int getRuolo() {
        return ruolo;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", ruolo=" + ruolo +
                '}';
    }
}
