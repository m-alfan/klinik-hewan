import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class Koneksidatabase
{
  public Connection koneksi;
  String host;
  String user;
  String pass;
 
  //konstruktor, saat object tercipta langsung membuat koneksi ke database
  public Koneksidatabase(){
    host = "jdbc:mysql://localhost/j_klinikhewan";
    user = "root";
    pass = "";
    try{
       if (cekDriver()){ //jika cekDriver==true
        koneksi = DriverManager.getConnection(host, user, pass);
        //System.out.println("connection to database established");
      }
    } catch (SQLException e){
      System.out.println("Connection failed " + e.getMessage());
    }
  }
 
  //method untuk mengecek apakah Driver untuk koneksi ke database sudah ada atau belum
  public final boolean cekDriver(){
    boolean ada = false;
    try {
      Class.forName("com.mysql.jdbc.Driver"); //di cek is driver exist ?
      //System.out.println("Driver oke");
      ada = true;
    } catch (ClassNotFoundException c){
      System.out.println("Driver tidak ada");
    }
    return ada; //kembalikan keterangan apakah driver ada atau tidak
  }
}