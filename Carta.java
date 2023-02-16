package gerenciador;

public class Carta {
    protected String descricao; // O que cada carta faz.
    protected String tipoCarta; // Os tipos de cada carta: movimento, dinheiro e "Vá para cadeia".

//metodo construtor
    public Carta(String descricao, String tipoCarta){
        this.descricao = descricao;
        this.tipoCarta = tipoCarta;
    }

//metodos acessores
    public String getDescricao() {
        return this.descricao;
    }
    
    public String getTipoCarta(){
        return this.tipoCarta;
    }
    
}

