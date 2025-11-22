package br.ufrn.tads.servicy.imp;


import br.ufrn.tads.servicy.*;
import br.ufrn.tads.repository.imp.*;
import br.ufrn.tads.model.*;

public class Login implements InterfaceLogin{
    private String login;
    private String senha;
    private static User userAtual;
    private UserDao userDao;
    private User user = null;
    public Login(){
        userDao = new UserDao();
    }
    
    @Override
    public boolean loginRequest(String login, String senha) { 
        if(login.length() > 20){
            throw new IndexOutOfBoundsException();
        }
        if(senha.length() > 20){
            throw new IndexOutOfBoundsException();
        }
        this.login = login;
        this.senha = senha;
        user = userDao.findByName(login);
        try {
            if(user.getName().toLowerCase().equals(this.login.toLowerCase()) && user.getSenha().equals(this.senha)){
            System.out.println("Logado");
            userAtual = user;
            return true;
        }
        } catch (Exception e) {
            System.out.println("Usuario Não encontrado");
        }
        
        
        return false;
    }

    @Override
    public void registerRequest(User user){
        if(userDao.save(user)){
            System.out.println("Usuario Criado Com Exito");
            
        }
        else{
            System.out.println("Usuario Ja existe");
            throw new IllegalArgumentException();
        }
        
    }
    public static User getUserAtual() {
        return userAtual;
    }
    public static void setUserAtual(User userAtual) {
        Login.userAtual = userAtual;
    }
}
