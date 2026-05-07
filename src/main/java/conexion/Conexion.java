package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;

public class Conexion {

    public static Connection getConexion() {
        Connection con = null;

        try {
            Properties props = new Properties();

            InputStream input = Conexion.class
                    .getClassLoader()
                    .getResourceAsStream("db.properties");

            if (input == null) {
                System.out.println("❌ ERROR: No se encontró db.properties");
                return null;
            }

            props.load(input);

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);

            System.out.println("✅ Conexión exitosa");

        } catch (Exception e) {
            System.out.println("❌ Error conexión: " + e.getMessage());
        }

        return con;
    }
}