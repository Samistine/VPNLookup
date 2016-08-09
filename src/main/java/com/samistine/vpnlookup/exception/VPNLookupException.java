package com.samistine.vpnlookup.exception;

/**
 *
 * @author Samuel
 */
public class VPNLookupException extends Exception {

    /**
     * Creates a new instance of <code>NetworkException</code> without detail
     * message.
     */
    public VPNLookupException() {
    }

    /**
     * Constructs an instance of <code>NetworkException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public VPNLookupException(String msg) {
        super(msg);
    }
}
