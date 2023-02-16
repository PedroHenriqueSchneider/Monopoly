package gerenciador;

//subclasse de propriedade
public class Utilidade extends Propriedade {

//metodo construtor que inicializa a classe propriedade
    public Utilidade(String nome, int preco) {
        super(nome, "Utilidade", preco);
    }

//sobrescreve o metodo calcularAluguel da classe pai propriedade
//retorna o numero retirado no dado * 4, se o proprietario possuir somente 1 utilidade ou o numero retirado no dado * 10, se o proprietario possuir as 2 utilidades
    @Override
    public int calcularAluguel(int valorDados) {
        if (this.proprietario.getUtilidades() == 1)
            return valorDados * 4;
        else
            return valorDados * 10;
    }

}
