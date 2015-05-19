package net.kibotu;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Jan Rabe on 19/05/15.
 */
public class SessionIdentifierGenerator {
    private SecureRandom random = new SecureRandom();

    public String nextSessionId() {
        return new BigInteger(130, random).toString(32);
    }
}