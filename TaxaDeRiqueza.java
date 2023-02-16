package gerenciador;

//subclasse de espaco
//possui como atributo o valor padrao do imposto
public class TaxaDeRiqueza extends Espaco {
    private static final int taxa = 200;

//metodo construtor que inicializa a classe espaco
    public TaxaDeRiqueza(String nome) {
        super(nome, "Taxa");
    }

//retorna a o valor da taxa de imposto
    public static int getTaxa() {
        return taxa;
    }

}
