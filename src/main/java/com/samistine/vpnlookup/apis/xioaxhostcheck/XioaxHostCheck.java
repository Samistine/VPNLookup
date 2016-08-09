package com.samistine.vpnlookup.apis.xioaxhostcheck;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.samistine.vpnlookup.Check;
import com.samistine.vpnlookup.Response;
import com.samistine.vpnlookup.ResponseHost;
import com.samistine.vpnlookup.exception.VPNLookupException;
import com.samistine.vpnlookup.utils.HTTPClient;
import java.net.SocketTimeoutException;

/**
 *
 * @author Samuel Seidel
 */
public class XioaxHostCheck implements Check {

    final String api;
    final String apiKey;
    final HTTPClient client;
    final Gson gson;

    public XioaxHostCheck() {
        Properties prop = new Properties();
        try {
            prop.load(getClass().getResourceAsStream("XioaxHostCheck.properties"));
        } catch (IOException ex) {
            Logger.getLogger(XioaxHostCheck.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.api = prop.getProperty("api", "http://tools.xioax.com/networking/ip/");
        this.apiKey = prop.getProperty("apikey", null);
        this.client = new HTTPClient();
        this.gson = new Gson();
    }

    public XioaxHostCheck(String api, String key) {
        this.api = api;
        this.apiKey = key;
        this.client = new HTTPClient();
        this.gson = new Gson();
    }

    @Override
    public Response check(String IP) throws VPNLookupException {
        String address = apiKey == null
                ? (api + IP)
                : (api + IP + "/" + apiKey);
        try {
            String data = client.get(address);
            XioaxJSONResponse json = gson.fromJson(data, XioaxJSONResponse.class);

            if (json.isSuccess()) {
                if (json.isHostIP()) {
                    return new ResponseHostImpl(json.org, json.cc);
                } else return () -> false;
            } else {
                throw new VPNLookupException("XioaxHostCheck::check() API returned with status=" + json.status + ", message given: " + json.msg);
            }
        } catch (IOException ex) {
            if (ex instanceof SocketTimeoutException) {
                throw new VPNLookupException("XioaxHostCheck::check() API Timed Out");
            } else {
                ex.printStackTrace();
                throw new VPNLookupException(ex.getMessage());
            }
        }
    }

    private static class ResponseHostImpl implements ResponseHost {

        private final String org;
        private final String cc;

        public ResponseHostImpl(String org, String cc) {
            this.org = org;
            this.cc = cc;
        }

        @Override
        public boolean isHostIP() {
            return true;
        }

        @Override
        public String getHostOrganization() {
            return org;
        }

        @Override
        public String getHostCountryCode() {
            return cc;
        }

    }

}
