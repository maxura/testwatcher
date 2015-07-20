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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * //
 *
 * @author Musienko Maxim
 */
public class Watcher {
    String   abortedMess         = "Finished: ABORTED";
    String   finishedFailureMess = "Finished: FAILURE";
    String   finishedSuccessMess = "Finished: SUCCESS";
    String[] mavenFifishMess     = {abortedMess, finishedFailureMess, finishedSuccessMess};

    /**
     * parse last String from jenkins job if String contains any info about finish job
     * return empty string
     *
     * @return parsed String (String time in format HH:mm;ss ) or empty string
     */
    protected String getTimeFromJenkins() {
        String login = RecourcesReader.getLogin();
        String password = RecourcesReader.getPassword();
        ConsoleReader reader = new ConsoleReader();
        String lastStringFromJenkins = reader.getInfo(login, password);
        for (String mess : mavenFifishMess) {
            if (lastStringFromJenkins.contains(mess))
                return "";

        }
        return lastStringFromJenkins.replaceFirst("</.*", "");
    }

    /**
     * @return last format Date in format (HH:mm:ss) from jenkins
     * @throws ParseException
     *         Date parse exception
     */
    protected Date getTimeFromJenkinswatchStateCheck() throws ParseException {
        Date date = null;
        String fromJenkins = getTimeFromJenkins();
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
        date = formatter.parse(fromJenkins);
        return date;
    }


    protected void watchStateCheck() {
//while (1000*3600){

    }

}


