package ATM7_task.config;

import java.io.*;
import java.util.Properties;


/**
 * Created by X240 on 8/11/2018.
 */
public class PropertyHelper {
    public static String getGridHost(){
        FileInputStream fis;
        Properties property = new Properties();

        String host = null;

        try {
            fis = new FileInputStream("src/test/resources/config.properties");
            property.load(fis);
            host = property.getProperty("grid.localhost");
            System.out.println("HOST: " + host);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return host;
    }
}
