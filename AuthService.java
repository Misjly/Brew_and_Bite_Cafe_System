import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class AuthService {

    private final Map<String, UserAccount> accounts;


    public AuthService(Map<String, UserAccount> accounts) {
        if(accounts == null) {
            this.accounts = new HashMap<>();
        } else {
            this.accounts = new HashMap<> (accounts);
        }
    }

    public static AuthService withDefaultAccounts() {
        Map<String, UserAccount> map = new HashMap<>();

        map.put("Barista", new UserAccount("barista", "barista1", Role.BARISTA));
        map.put("Manager", new UserAccount("manager", "manager1", Role.MANAGER));

        return new AuthService(map);    
    }


    public Optional<UserAccount> login(String username, String password) {
        if (username == null || password == null) {
            return Optional.empty();
        }

        UserAccount account = accounts.get(username);
        if ( account == null) {
            return Optional.empty();
        }

        if(account.getPasswordHash().equals(password)) {
            return Optional.of(account);
        } else {
            return Optional.empty();
        }
    }


    public Map<String, UserAccount> getAccounts() {
        return Collections.unmodifiableMap(accounts);
    }

}