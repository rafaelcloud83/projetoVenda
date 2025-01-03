package dao;

import jdbc.ConnectionFactory;
import model.Clientes;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafael
 */
public class ClientesDAO {

    private final Connection con;

    public ClientesDAO() throws SQLException {
        this.con = ConnectionFactory.getConnection();
        this.con.setAutoCommit(false);
    }

    public List<Clientes> listarClientes() {
        List<Clientes> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tb_clientes";
            PreparedStatement pst = this.con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Clientes cliente = new Clientes();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEmail(rs.getString("email"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setCep(rs.getString("cep"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setNumero(rs.getString("numero"));
                cliente.setComplemento(rs.getString("complemento"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setEstado(rs.getString("estado"));
                lista.add(cliente);
                System.out.println("Lista Clientes - " + cliente);
            }
            return lista;
        } catch (SQLException e) {
            System.err.println("Erro ao listar clientes: " + e.getMessage());
            return lista;
        }
    }

    public Boolean cadastrarCliente(Clientes cliente) throws SQLException { 
        System.out.println(cliente);
        try {
            String sql = "INSERT INTO tb_clientes (nome, cpf, email, celular, "
                    + "cep, endereco, numero, complemento, bairro, cidade, estado) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = this.con.prepareStatement(sql);
            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getCpf());
            pst.setString(3, cliente.getEmail());
            pst.setString(4, cliente.getCelular());
            pst.setString(5, cliente.getCep());
            pst.setString(6, cliente.getEndereco());
            pst.setString(7, cliente.getNumero());
            pst.setString(8, cliente.getComplemento());
            pst.setString(9, cliente.getBairro());
            pst.setString(10, cliente.getCidade());
            pst.setString(11, cliente.getEstado());
            int retorno = pst.executeUpdate();
            if (retorno == 1) {
               this.con.commit();
                return true;
            } else {
                this.con.rollback();
                return false;
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao cadastrar cliente: " + ex.getMessage());
            this.con.rollback();
            return false;
        }
    }
    
    public Boolean alterarCliente(Clientes cliente) throws SQLException { 
        System.out.println(cliente);
        try {
            String sql = "UPDATE tb_clientes SET nome = ?, cpf = ?, email = ?, celular = ?, cep = ?, "
                    + "endereco = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, estado = ? "
                    + "WHERE id = ?";
            PreparedStatement pst = this.con.prepareStatement(sql);
            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getCpf());
            pst.setString(3, cliente.getEmail());
            pst.setString(4, cliente.getCelular());
            pst.setString(5, cliente.getCep());
            pst.setString(6, cliente.getEndereco());
            pst.setString(7, cliente.getNumero());
            pst.setString(8, cliente.getComplemento());
            pst.setString(9, cliente.getBairro());
            pst.setString(10, cliente.getCidade());
            pst.setString(11, cliente.getEstado());
            pst.setInt(12, cliente.getId());
            int retorno = pst.executeUpdate();
            if (retorno == 1) {
               this.con.commit();
                return true;
            } else {
                this.con.rollback();
                return false;
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao alterar cliente: " + ex.getMessage());
            this.con.rollback();
            return false;
        }
    }
    
    public Boolean excluirCliente(Clientes cliente) throws SQLException { 
        System.out.println(cliente);
        try {
            String sql = "DELETE FROM tb_clientes WHERE id = ?";
            PreparedStatement pst = this.con.prepareStatement(sql);
            pst.setInt(1, cliente.getId());
            int retorno = pst.executeUpdate();
            if (retorno == 1) {
               this.con.commit();
                return true;
            } else {
                this.con.rollback();
                return false;
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao excluir cliente: " + ex.getMessage());
            this.con.rollback();
            return false;
        }
    }
}
