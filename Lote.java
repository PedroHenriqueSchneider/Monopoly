package gerenciador;

//subclasse de propriedade
public class Lote extends Propriedade {
    private final String cor;
    private final int aluguel;
    private final int precoCasaHotel;
    private final int aluguelComCasa;
    private boolean temCasa;
    private boolean temHotel;
    private boolean temMonopolio;

//metodo construtor
    public Lote(String nome, String cor, int preco, int precoCasaHotel, int aluguel, int aluguelComCasa) {
        super(nome, "Lote", preco);
        this.cor = cor;
        this.aluguel = aluguel;
        this.precoCasaHotel = precoCasaHotel;
        this.aluguelComCasa = aluguelComCasa;
        this.temCasa = false;
        this.temHotel = false;
        this.temMonopolio = false;
    }
//metodos pre-definidos 
    @Override
    public int calcularAluguel(int valor) {
        int multiplicador = 1;
        if (this.temMonopolio)
            multiplicador = 2;
        
        if (temCasa)
            return this.aluguelComCasa * multiplicador;
        else if (temHotel)
            return this.aluguelComCasa * 2 * multiplicador;
        return this.aluguel * multiplicador;
    }
    
    public void setCasa(boolean b) {
        this.temCasa = b;
    }
    
    public void setHotel(boolean b) {
        this.temHotel = b;
    }

    public int getPrecoCasa() {
        return this.precoCasaHotel;
    }

    public int getPrecoHotel() {
        return this.precoCasaHotel;
    }

    public int getAluguelComCasa() {
        return this.aluguelComCasa;
    }

    public boolean temCasa() {
        return this.temCasa;
    }

    public boolean temHotel() {
        return this.temHotel;
    }

    public boolean temMonopolio(){
      return this.temMonopolio;
    }

    public void setMonopolio(boolean t){
      this.temMonopolio = t;
    }
}
