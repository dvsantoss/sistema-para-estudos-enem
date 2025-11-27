package br.ufrn.tads.repository.imp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.tads.repository.*;
import br.ufrn.tads.model.*;
public class UserDao implements InterfaceDao<User>{
    DbConnection dbConnection = new DbConnection();
    

    @Override
    public User findById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User findByName(String name) {
        // TODO Auto-generated method stub
        User user = null;
        String sql = "select * from Usuario where LOWER(nome)=?";
        Connection conn = null;
        
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            conn = DbConnection.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name.toString().toLowerCase());
            //verificando Entrada no banco
            System.out.println("conecção: "+preparedStatement);
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
                System.out.println("output de valor:"+user.getName());
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
    public List<User> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean save(User t) {
        User comparador = findByName(t.getName());
        
        if(comparador != null){
            return false;
        }
                                                                                                                // n   s   m  qf  qc  qe
            String sql = "INSERT INTO USUARIO(nome,senha,email,quest_feitas,quest_certas,quest_erradas)"+" VALUES(?,   ?,  ?, ?,  ?,  ?)";
            Connection conn = null;
            
            PreparedStatement preparedStatement = null;

            try {
                conn = DbConnection.getConnection();
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, t.getName());
                preparedStatement.setString(2,t.getSenha());
                preparedStatement.setString(3, t.getEmail());
                preparedStatement.setInt(4, 0);
                preparedStatement.setInt(5, 0);
                preparedStatement.setInt(6, 0);

                //System.out.println("state "+preparedStatement);
                //System.out.println("Usuario Criado Com Sucesso");
                preparedStatement.execute();
            } catch (Exception e) {
                e.printStackTrace();
            //fechar coneccoes 
            } finally {
                try {
                    if (preparedStatement != null) preparedStatement.close();
                    if (conn != null) conn.close();
                    return true;
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
            return true;
    }
    @Override
    public boolean delete(Long id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean update(User t, String[] params) {
        // TODO Auto-generated method stub
        String sql = "update usuario set quest_feitas = quest_feitas+ ? ,quest_certas = quest_certas+ ? ,quest_erradas = quest_erradas+ ? where id_user = ? ;";
        Connection conn = null;
        
        PreparedStatement preparedStatement = null;

        try {
            conn = DbConnection.getConnection();
            preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, t.getQuest_feitas());
            preparedStatement.setInt(2, t.getQuest_certas());
            preparedStatement.setInt(3, t.getQuest_erradas());
            preparedStatement.setLong(4, t.getId());
            
            preparedStatement.execute(); //it is not a query. It is an insert command
            
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            // close all connections
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (conn != null) conn.close();
                return true;
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void salvarEstatisticaDiaria(User t) {
    String sql = "INSERT INTO user_stats_daily (id_user, data, quest_feitas, quest_certas, quest_erradas) VALUES (?, CURRENT_DATE, ?, ?, ?) ON CONFLICT (id_user, data) DO UPDATE SET quest_feitas = user_stats_daily.quest_feitas + EXCLUDED.quest_feitas, quest_certas = user_stats_daily.quest_certas + EXCLUDED.quest_certas,quest_erradas = user_stats_daily.quest_erradas + EXCLUDED.quest_erradas;";

    try (Connection conn = DbConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setLong(1, t.getId());
        ps.setInt(2, t.getQuest_feitas());
        ps.setInt(3, t.getQuest_certas());
        ps.setInt(4, t.getQuest_erradas());

        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public List<UserDailyStats> getEstatisticaDiaria(Long userId) {
    List<UserDailyStats> lista = new ArrayList<>();

    String sql = "SELECT data, quest_certas, quest_erradas FROM user_stats_daily WHERE id_user = ? ORDER BY data";

    try (Connection conn = DbConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setLong(1, userId);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            lista.add(new UserDailyStats(
                rs.getDate("data").toLocalDate(),
                rs.getInt("quest_certas"),
                rs.getInt("quest_erradas")
            ));
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return lista;
}




}
