import java.sql.*;
import java.util.Scanner;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MySQLDemoPro {

	public static void main(String[] args) throws Exception  {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String  url="jdbc:mysql://localhost:3306/baseprueba?characterEncoding=utf8";
		String username="root";
		String password="Afrodita03";
		Connection con =DriverManager.getConnection(url,username,password);
		
		Statement stmt =con.createStatement();
		ResultSet rs;
		PreparedStatement st;
		
		String qry="";
		int id,age,choice;
		String name,city;
		
		Scanner in = new Scanner(System.in);
		Scanner str = new Scanner(System.in);
		
		while(true)
		{
			System.out.println("MySQL Java CRUD Carina Villasanti CaC 23648");
			System.out.println("1. Insert");
			System.out.println("2. Update");
			System.out.println("3. Delete");
			System.out.println("4. Select");
			System.out.println("5. Salir");
			System.out.print("Ingrese una opción por favor: ");
			choice = in.nextInt();
			System.out.println("-----------------------------------------");
			
                        switch(choice){
			case 1:
				System.out.println("1. INSERTAR DATOS");
				
				System.out.println("Ingrese Nombre : ");
				name=str.nextLine();
				System.out.println("Ingrese Edad : ");
				age=in.nextInt();
				System.out.println("Ingrese Ciudad : ");
				city=str.nextLine();
				
				qry="insert into users (Nombre,Edad,Ciudad) values(?,?,?)";
				st= con.prepareStatement(qry);
				st.setString(1, name);
				st.setInt(2, age);
				st.setString(3, city);
				
				st.executeUpdate();
				System.out.println("Ingreso de Datos Exitoso");
				
				break;
			case 2:
				System.out.println("2. MODIFICAR DATOS");
				
				System.out.println("Ingrese ID : ");
				id=in.nextInt();
				System.out.println("Ingrese Nombre : ");
				name=str.nextLine();
				System.out.println("Ingrese Edad : ");
				age=in.nextInt();
				System.out.println("Ingrese Ciudad : ");
				city=str.nextLine();
				
				qry="update users set Nombre=?,Edad=?,Ciudad=? where ID=?";
				st= con.prepareStatement(qry);
				
				st.setString(1, name);
				st.setInt(2, age);
				st.setString(3, city);
				st.setInt(4, id);
				st.executeUpdate();
				System.out.println("Modificación Exitosa");

				break;
			case 3:
				System.out.println("3. BORRAR DATOS");
				System.out.println("Ingrese ID : ");
				id=in.nextInt();
				
				qry="delete from users where ID=?";
				st= con.prepareStatement(qry);
				st.setInt(1, id);
				
				st.executeUpdate();
				System.out.println("Borrado Exitoso");
				
				break;
			case 4:
				System.out.println("4. IMPRIME TODOS LOS REGISTROS");
				qry="SELECT ID,Nombre,Edad,Ciudad from users";
				rs=stmt.executeQuery(qry);
				
                                String filePath = "C:/Users/cvillasanti/Downloads/MysqlDemo/MySQLDemoPro/oradores.xml";
              
                                Path path = Paths.get(filePath);
                                Files.delete(path);
               
                                String line = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";  //Coloco la cabecera
                                FileWriter cb = new FileWriter(filePath, true);
                                cb.write(line);
                                cb.close();
     
                                line = "<oradores>"; //abro el XML
                                FileWriter ap = new FileWriter(filePath, true);
                                ap.write(line);
                                ap.close();
                                
                                while(rs.next())
				{
					id=rs.getInt("ID");
					name=rs.getString("Nombre");
					age=rs.getInt("Edad");
					city=rs.getString("Ciudad");
					
					System.out.print(id+" ");
					System.out.print(name+" ");
					System.out.print(age+" ");
					System.out.println(city+" ");
                                        
                                        line = "<orador><nombre>" + rs.getString("Nombre") + "</nombre><edad>" 
                                        + rs.getString("Edad") + "</edad><ciudad>" 
                                        + rs.getString("Ciudad") + "</ciudad></orador>";
           
                                        FileWriter fw = new FileWriter(filePath, true);
                                        fw.write(line);
                                        fw.close();
					
				}
                                        
                                line = "</oradores>";
                                FileWriter fo = new FileWriter(filePath, true);
                                fo.write(line);
                                fo.close();
                                
				break;
			case 5:
				System.out.println("GRACIAS");
				System.exit(0);
				break;
			default:
				System.out.println("Selección Inválida");
				break;
			}
			System.out.println("-----------------------------------------");
		}
	}
        
}
