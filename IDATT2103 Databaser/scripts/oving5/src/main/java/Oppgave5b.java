import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

class Oppgave5b{

    private static Map<String, String> getProperties() {
        Map<String, String> result = new HashMap<>();
        try (InputStream input = new FileInputStream("config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            result.put("user", prop.getProperty("DBUsername"));
            result.put("pass", prop.getProperty("DBPassword"));
            result.put("url", prop.getProperty("DBUrl"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static Connection connect()throws SQLException {
        String pass = getProperties().get("pass");
        String user = getProperties().get("user");
        String url = getProperties().get("url");
        Connection c = DriverManager.getConnection(url,user,pass);
        return c;
    }

    public static void getInfoByIsbn(String isbn){
        try{
            Connection c = connect();
            PreparedStatement stmt;
            PreparedStatement stmt2;
            stmt = c.prepareStatement("select forfatter, tittel from boktittel where isbn = ?");
            stmt2 = c.prepareStatement("SELECT COUNT(*) as antall FROM eksemplar WHERE isbn = ?");
            stmt.setString(1, isbn);
            stmt2.setString(1, isbn);
            printInfo(stmt.executeQuery(), stmt2.executeQuery());
            c .close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    private static void printInfo(ResultSet s,ResultSet t) throws SQLException {
        while(s.next() && t.next()) {
            System.out.println("forfatter: " + s.getString("forfatter") + "\n" +
                    "tittel: " + s.getString("tittel") + "\n" +
                    "antall: " + t.getInt("antall"));
        }
    }

    public static int updateInfoByIsbn(String isbn, String laant_av, int eks_nr){
        try{
            Connection c = connect();
            PreparedStatement stmt;
            stmt = c.prepareStatement("update eksemplar set laant_av = ? "+
                    "where isbn = ? and eks_nr = ? and laant_av is null;");
            stmt.setString(1, laant_av);
            stmt.setString(2,isbn);
            stmt.setInt(3, eks_nr);
            return stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            return  -1;
        }
    }

    public static void main(String[] args) {
        getInfoByIsbn("0-596-00");
        System.out.println("rows affected: "+updateInfoByIsbn("0-596-00123-1","Per Olsen", 1));
    }





}