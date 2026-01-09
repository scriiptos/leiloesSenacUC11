/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO p){
        
        
        String sql = "INSERT INTO produtos (nome, valor) VALUES (?,?)";

        try (Connection con = conectaDAO.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setInt(2, p.getValor());
            stmt.execute();

            JOptionPane.showMessageDialog(null, "Produto inserido com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Não foi possível inserir os dados! Por favor, verifique os valores digitados.");
        }
        
        
    }
    
    public List<ProdutosDTO> listarProdutos(){
        
        List<ProdutosDTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try (Connection con = conectaDAO.conectar();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ProdutosDTO p = new ProdutosDTO();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setValor(rs.getInt("valor"));
                p.setStatus(rs.getString("status"));

                lista.add(p);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Erro ao tentar listar os filmes!");
        }

        return lista;  
    }
    
    
    public void venderProduto(int id) {

    String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

    try (Connection con = conectaDAO.conectar();
         PreparedStatement stmt = con.prepareStatement(sql)) {

        stmt.setInt(1, id);
        stmt.execute();

        JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null,
            "Erro ao vender o produto!");
    }
}
        
}

