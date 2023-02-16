package gerenciador;

//subclasse de espaco com unico atributo de valor fixo
public class PontoDePartida extends Espaco {
    private static final int valor = 200;
  
//metodo construtor que inicializa a classe espaco
    public PontoDePartida(String nome) {
        super(nome, "Partida");
    }
  
//metodo que retorna o atributo valor   
    public static int getValor(){
        return valor;
    }
}
