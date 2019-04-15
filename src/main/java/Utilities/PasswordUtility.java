package Utilities;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtility {

    public static boolean checkPassword(String password) {
        //Minimaal lengte van 8, 1 hoofdletter, 1 kleineletter, 1 getal, 1 @$%*#?& teken
        String regexMinimumRequirements = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";

        if (password.matches(regexMinimumRequirements)) {
            return true;
        } else {
            return false;
        }
    }

    public static String hashPassword(String password) {
        String generatedSecuredPasswordHash = BCrypt.hashpw(password, BCrypt.gensalt(12));

        return generatedSecuredPasswordHash;
    }

    public static boolean checkHash(String dbPasswd, String oldUnhashedPasswd) {

        try {
            boolean matched = BCrypt.checkpw(oldUnhashedPasswd, dbPasswd);
            return matched;

        } catch (Exception noentry) {
            return false;
        }
    }
}
