package sp.com.senac.pi.conexao.model;

public class ConexaoPropriedade {

    private String porta;
    private String host;
    private String base;
    private String login;
    private String senha;
    private String drive;
    private String connectionURL;

    public ConexaoPropriedade() {
        this.porta = "";
        this.host = "";
        this.base = "";
        this.login = "";
        this.senha = "";
        this.drive = "";
        this.connectionURL = "";
    }

    public ConexaoPropriedade(String porta, String host, String base, String login, String senha, String drive, String connectionURL) {
        this.porta = porta;
        this.host = host;
        this.base = base;
        this.login = login;
        this.senha = senha;
        this.drive = drive;
        this.connectionURL = connectionURL;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    public String getConnectionURL() {
        return connectionURL;
    }

    public void setConnectionURL(String connectionURL) {
        this.connectionURL = connectionURL;
    }

}