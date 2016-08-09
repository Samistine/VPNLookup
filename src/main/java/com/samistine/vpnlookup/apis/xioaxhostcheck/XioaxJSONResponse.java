package com.samistine.vpnlookup.apis.xioaxhostcheck;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Samuel Seidel
 */
final class XioaxJSONResponse {

    /**
     * Returns "success" or "failed" whether or not we were able to process your
     * IP request.
     */
    String status;

    /**
     * Boolean value if the requested IP belongs to a hosting organization or
     * not.
     */
    @SerializedName("host-ip")
    boolean host_ip;

    /**
     * The organization who owns the IP address. <br/>
     * <b> Only returned when host_ip is true </b>
     */
    String org;

    /**
     * Country code.<br/>
     * <b> Only returned when host_ip is true </b>
     */
    String cc;

    /**
     * String which contains information regarding why the status is failed.
     * <br/>
     * <b> Only returned when status returns "failed" </b>
     */
    String msg;

    /**
     * Check the result of the query.
     *
     * @return true if the query was successful
     */
    public boolean isSuccess() {
        return status.equals("success");
    }

    public boolean isHostIP() {
        return host_ip;
    }

    public String getHostOrganization() {
        return org != null ? org : "null";
    }

    public String getHostCountryCode() {
        return cc != null ? cc : "null";
    }

    @Override
    public String toString() {
        return "XioaxJSONResponse{" + "status=" + status + ", host_ip=" + host_ip + ", org=" + org + ", cc=" + cc + ", msg=" + msg + '}';
    }

}
