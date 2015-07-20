package com.maxura;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by maxura on 14.07.2015.
 */
public class RecourcesReader {

   public static final ResourceBundle     IDE_SETTINGS    = ResourceBundle.getBundle("credentials");
   static String getLogin(){return IDE_SETTINGS.getString("jenkins.authorize.login");}
   static String getPassword(){return IDE_SETTINGS.getString("jenkins.authorize.password");}
   private void readProperties(String filePath) throws IOException {
        Properties properties = new Properties();
        FileInputStream propertiesFile = new FileInputStream(filePath);
        properties.load(propertiesFile);
        propertiesFile.close();
    }
}
