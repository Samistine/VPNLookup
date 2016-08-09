package com.samistine.vpnlookup;

import com.samistine.vpnlookup.exception.VPNLookupException;

/**
 *
 * @author Samuel Seidel
 */
public interface Check {

    /**
     * @param IP to check
     * @return response
     * @throws com.samistine.vpnlookup.exception.VPNLookupException when
     * encountering issues
     */
    public Response check(String IP) throws VPNLookupException;

}
