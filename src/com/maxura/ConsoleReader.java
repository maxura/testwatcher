/**
 * ****************************************************************************
 * Copyright (c) 2012-2015 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * Codenvy, S.A. - initial API and implementation
 * *****************************************************************************
 */
package com.maxura;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * //
 *
 * @author Musienko Maxim
 */
public class ConsoleReader {
    private String nigtliLastJobUrl =
            "http://ci.codenvy-dev.com/jenkins/view/qa-tests-view/job/nightly-staging-ide-selenium-tests-googlechrome/lastBuild/logText/progressiveHtml";
    private String authUrl          = "http://ci.codenvy-dev.com/jenkins/j_acegi_security_check";
    private String bbb              =
            "j_username=mmusienko&j_password=vfrcbv_1978&from=%2Fjenkins%2F&json=%7B%22j_username%22%3A+%22mmusienko%22%2C+%22j_password%22%3A+%22vfrcbv_1978%22%2C+%22remember_me%22%3A+false%2C+%22from%22%3A+%22%2Fjenkins%2F%22%7D&Submit=%D0%B2%D0%BE%D0%B9%D1%82%D0%B8";

    /**
     * get user authorize session token from working with codenvy Jenkins
     * @param userName
     * @param userPassword
     * @return
     */
    public String authorizeCodenvyCi(String userName, String userPassword) {
        String fromPrefData = "from=/jenkins/";
        String jsonData = "&json={\"j_username\":" + "\"" + userName + "\"," + " \"j_password\": \"" + userPassword +
                          "\", \"remember_me\": false, \"from\": \"/jenkins/\"}&Submit=log in";
        String postDataFromFields = "j_username=" + userName + "&j_password=" + userPassword + fromPrefData + jsonData;
        HttpURLConnection http = null;
        try {
            http = (HttpURLConnection)new URL(authUrl).openConnection();
            http.setRequestMethod("POST");
            http.setAllowUserInteraction(false);
            http.setInstanceFollowRedirects(false);
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            OutputStream output = http.getOutputStream();
            output.write((bbb).getBytes());
            if (http.getResponseCode() != 302) {
                throw new RuntimeException(
                        new Exception("Can not authorize on codenvy ci side. Server response code: " + http.getResponseCode()));
            }
            output.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (http != null) {
                http.disconnect();
            }
        }
        return http.getHeaderFields().get("Set-Cookie").get(0).split(";")[0];


    }

    /**
     * get last string from console of the latest job
     * @param userName
     * @param userPassword
     * @return
     */
    public String getInfo(String userName, String userPassword) {
        HttpURLConnection connection = null;
        BufferedReader br;
        StringBuilder responseData = new StringBuilder();
        String line;
        try {
            URL url = new URL(nigtliLastJobUrl);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.addRequestProperty("Cookie", authorizeCodenvyCi(userName, userPassword));
            if (connection.getResponseCode() != 200) {
                throw new RuntimeException(new Exception(
                        "Can not get info about job from codenvy jenkins " + "server responce: " +
                        connection.getResponseCode()));
            }
            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = br.readLine()) != null) {
                responseData.append(line);
            }
            connection.getInputStream().close();
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        String revertStr = responseData.toString();

        String[] spltedArrStr = revertStr.split("<b>");

        return  spltedArrStr[spltedArrStr.length];
    }

}
