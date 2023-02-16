package gerenciador;

import java.util.Random;

// Rola o dado de maneira aleatória.
public class Dado {
    
    private int valorDado;

    // Random gera um número aleatório para o dado.
    public void rolar() {
        Random r = new Random();
        this.valorDado = r.nextInt(6) + 1;
    }

  //metodo acessor
    public int getValorDado() {
        return this.valorDado;
    }
    
}
