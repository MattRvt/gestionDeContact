package accountGestions;

import java.util.HashMap;

public class Service_account_manager {
    private final HashMap<String, String> log;
    /**
     * mail of logged account
     */
    private String loggedAccount;
    private static Service_account_manager instance = null;

    public static synchronized Service_account_manager getInstance() {
        if (instance == null) {
            instance = new Service_account_manager();
        }
        return instance;
    }

    private Service_account_manager() {
        log = new HashMap<>();
        log.put("admin@admin.admin", "adminadmin");
        loggedAccount = null;
    }

    public String getLoggedAccount(){
        return loggedAccount;
    }

    public void addAccount(String mail, String password) {
        log.put(mail, password);
    }

    public void getAccounts() {
        //TODO
    }

    public void deleteAccount() {
        //TODO
    }

    /**
     * connect un utilisateur
     *
     * @return true si les indentifiants son correcte
     */
    public boolean connect(String motDePasse, String mail) {
        boolean logInValide;
        logInValide = log.keySet().contains(mail);
        if (logInValide && motDePasse != null && mail != null)
            logInValide = log.get(mail).equals(motDePasse);
        if (logInValide){
            loggedAccount = mail;
        }
        return logInValide;
    }
}
