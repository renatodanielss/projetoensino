import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class AutorDao {
	private PreparedStatement pst;
	private ResultSet rs;
	private Statement st;	
	private Connection con;
	
	
	public void save (Autor autor) throws ClassNotFoundException{
		String sql = "insert Autor (id,nome) values (?,?)";
		//criando preparedstatement
		try {
			pst = FactoryConnection.getInstance(Banco.POSTGRE.getDriver(),
					"url do seu banco com a porta e o nome da base", 
					"usuario do banco de dados", "senha do bd").getConnection().prepareStatement(sql);
			pst.setInt(1, autor.getId());
			pst.setString(2, autor.getNome());
			pst.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new ClassCastException("Não foi possível carregar o driver de conexão!");
		}finally {
			
		}
	}
	public void update (Autor autor) throws ClassNotFoundException,ItemNotFoundException {
		String sql = "Select * from Autor where id = ?";
		try {
			con = FactoryConnection.getInstance(Banco.POSTGRE.getDriver(),
					"url do seu banco com a porta e o nome da base", 
					"usuario do banco de dados", "senha do bd").getConnection();
		
			pst = con.prepareStatement(sql);
			pst.setInt(1, autor.getId());
			rs = pst.executeQuery();
			pst.close();
		if (rs.next()) {
			sql = "update from Autor set nome = ? where id = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, autor.getNome());
			pst.setInt(2, autor.getId());
			pst.execute();
		}
			throw new ItemNotFoundException("Não foi encontrado nenhum autor com a id selecionada!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			throw new ClassCastException("Não foi possível carregar o driver de conexão!");
		}
	}
	
	public void excluir (Autor autor) throws ClassNotFoundException{
		String sql = "delete from Autor where id = ?";
		try {
			con = FactoryConnection.getInstance(Banco.POSTGRE.getDriver(),
					"url do seu banco com a porta e o nome da base", 
					"usuario do banco de dados", "senha do bd").getConnection();
		
			pst = con.prepareStatement(sql);
			pst.setInt(1, autor.getId());
		}catch (SQLException e) {
			// TODO: handle exception
		}catch(ClassNotFoundException e) {
			throw new ClassCastException("Não foi possível carregar o driver de conexão!");
		}
	}
	
	public Autor findAutorId (int id)throws ClassNotFoundException,ItemNotFoundException{
		String sql = "Select * from Autor where id = ?";
		try {
			con = FactoryConnection.getInstance(Banco.POSTGRE.getDriver(),
					"url do seu banco com a porta e o nome da base", 
					"usuario do banco de dados", "senha do bd").getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			Autor a = new Autor();
			rs = pst.executeQuery();
			if (rs.next()) {
				a.setId(rs.getInt("id"));
				a.setNome(rs.getString("nome"));
				return a;
			}
			throw new ItemNotFoundException("Nenhum autor encontrado!");
		}catch(ClassNotFoundException e) {
			throw new ClassCastException("Não foi possível carregar o driver de conexão!");
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Autor> findAll ()throws ClassNotFoundException {
		String sql = "Select * from Autor";
		List<Autor> autores = new ArrayList<Autor>();
		try {
			con = FactoryConnection.getInstance(Banco.POSTGRE.getDriver(),
					"url do seu banco com a porta e o nome da base", 
					"usuario do banco de dados", "senha do bd").getConnection();
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next())
				autores.add(new Autor(rs.getInt("id"), rs.getString("nome")));
			return autores;
		}catch(ClassNotFoundException e) {
			throw new ClassCastException("Não foi possível carregar o driver de conexão!");
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	} 
}
