package br.ufrn.tads.repository.imp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import br.ufrn.tads.repository.*;
import br.ufrn.tads.model.*;
public class UserDao implements InterfaceDao{
    DbConnection dbConnection = new DbConnection();
    

    @Override
    public Object findById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User findByName(String name) {
        // TODO Auto-generated method stub
        User user = null;
        String sql = "select * from Usuario where nome = ?";
        Connection conn = null;
        
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            conn = DbConnection.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name.toString());
            //verificando Entrada no banco
            //System.out.println("conecção: "+preparedStatement);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                user = new User();
                user.setId(resultSet.getLong("id_user"));
                user.setName(resultSet.getString("nome"));
                user.setSenha(resultSet.getString("senha"));
                user.setEmail(resultSet.getString("email"));
                user.setQuest_certas(resultSet.getInt("quest_certas"));
                user.setQuest_erradas(resultSet.getInt("quest_erradas"));
                user.setQuest_feitas(resultSet.getInt("quest_feitas"));
                System.out.println("output de valor:"+user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(preparedStatement != null) preparedStatement.close();
                if(conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public List findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean save(Object t) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean update(Object t, String[] params) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean delete(Long id) {
        // TODO Auto-generated method stub
        return false;
    }
}
