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

/**
 * //
 *
 * @author Musienko Maxim
 */
public class Main2 {


    public static void main(String[] args) {
        String sdf = "12:56:54</b> </span>Running com.codenvy.ide.runner.TerminalInToggleSplitterModeTest";
        System.out.println(sdf.replaceFirst("</.*", ""));
//        Pattern p = Pattern.compile(":");
//        String sampe = "01h:25m:23s";
//        String  sampe =":";
//        Matcher m = p.matcher(sampe);
//        System.out.println(m.find());
//        String []spl = sampe.split(":");
//        for (String s : spl) {
//            System.out.println(s);
    }


}
