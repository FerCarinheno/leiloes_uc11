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
    
    public void cadastrarProduto (ProdutosDTO produto){
         
        conn = new conectaDAO().connectDB();
        String sql = "INSERT INTO PRODUTOS (nome, valor, status) VALUES (?,?,?)";
        try {
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setDouble(2,produto.getValor());
            prep.setString(3,produto.getStatus());
            prep.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Produto cadastrado com sucesso");
            
        }catch (Exception e){
            System.out.println("Erro ao cadastrar" + e.getMessage());
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
       ArrayList<ProdutosDTO> listagem = new ArrayList<ProdutosDTO>();
        
        conn = new conectaDAO().connectDB();
        String sql = "SELECT * FROM produtos";
        
        
        
        try {
            PreparedStatement prep = conn.prepareStatement(sql);
            
            ResultSet resposta = prep.executeQuery();

            while (resposta.next()) {
                ProdutosDTO p = new ProdutosDTO();

                p.setId(resposta.getInt("id"));
                p.setNome(resposta.getString("nome"));
                p.setValor(resposta.getDouble("valor"));
                p.setStatus(resposta.getString("status"));

                listagem.add(p);
            }
            
        }catch (Exception e){
            System.out.println("Erro ao abrir a listagem" + e.getMessage());
        }
        
        
        return listagem;
    }
    
    public void atualizarProduto (ProdutosDTO produto){
         
        conn = new conectaDAO().connectDB();
        String sql = "UPDATE PRODUTOS SET nome=?, valor=?, status=? WHERE id=?;";
        //String sql = "UPDATE PRODUTOS SET status=? WHERE id=?;";
        try {
            PreparedStatement prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setDouble(2,produto.getValor());
            prep.setString(3,produto.getStatus());
            prep.setInt(4, produto.getId());
            prep.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Produto atualizado com sucesso");
            
        }catch (Exception e){
            System.out.println("Erro ao cadastrar" + e.getMessage());
        }
    }
    
        
}

