package br.ufrn.tads.model;

public class User {
    private Long id;
    private String name;
    private String senha;
    private String email;
	private int quest_feitas;
	private int quest_certas;
	private int quest_erradas;

    public User(){
        
    }

    public User(Long id,
                String name,
                String senha,
                String email,
	            int quest_feitas,
                int quest_certas,
	            int quest_erradas){

    this.id = id;
    this.name = name;
    this.senha = senha;
    this.email =email;
    this.quest_certas = quest_certas;
    this.quest_erradas = quest_erradas;
    this.quest_feitas =quest_feitas;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public int getQuest_feitas() {
        return quest_feitas;
    }


    public void setQuest_feitas(int quest_feitas) {
        this.quest_feitas = quest_feitas;
    }


    public int getQuest_certas() {
        return quest_certas;
    }


    public void setQuest_certas(int quest_certas) {
        this.quest_certas = quest_certas;
    }


    public int getQuest_erradas() {
        return quest_erradas;
    }


    public void setQuest_erradas(int quest_erradas) {
        this.quest_erradas = quest_erradas;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((senha == null) ? 0 : senha.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + quest_feitas;
        result = prime * result + quest_certas;
        result = prime * result + quest_erradas;
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (senha == null) {
            if (other.senha != null)
                return false;
        } else if (!senha.equals(other.senha))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (quest_feitas != other.quest_feitas)
            return false;
        if (quest_certas != other.quest_certas)
            return false;
        if (quest_erradas != other.quest_erradas)
            return false;
        return true;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    
}
