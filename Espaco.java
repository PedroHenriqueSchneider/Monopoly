package gerenciador;

// Posições para onde o jogador irá se movimentar. Tem-se 8 espaços.
public class Espaco {
    protected final String nome;
    protected final String tipo;

  //metodo construtor 
    public Espaco(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }
  //metodos acessores
    public String getNome() {
        return this.nome;
    }
    
    public String getTipo() {
        return this.tipo;
    }
}
