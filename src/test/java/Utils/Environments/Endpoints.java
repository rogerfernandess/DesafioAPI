package Utils.Environments;

import org.apache.commons.lang3.StringUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Endpoints {

    public static Properties prop = new Properties();
    public static String BASE_PATH_GET_RESTRICAO;
    public static String BASE_PATH_GET_SIMULACAO;
    public static String BASE_PATH_POST;

    static {
        String propertiesPath = "/application.properties";
        InputStream is = Endpoints.class.getResourceAsStream(propertiesPath);
        try {
            prop.load(is);
            setEndpointsVariables();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }


    private static void setEndpointsVariables() {
        BASE_PATH_GET_RESTRICAO = prop.getProperty("restricao");
        BASE_PATH_GET_SIMULACAO = prop.getProperty("simulacao");
    }
}