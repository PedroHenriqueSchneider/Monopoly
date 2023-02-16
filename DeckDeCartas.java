package gerenciador;

import java.util.Random;

public class DeckDeCartas {
    private final Carta[] cartas;

//metodo construtor
    public DeckDeCartas(String tipo) {
        this.cartas = new Carta[16];
        
        if (tipo.equals("Sorte")) {
            this.cartas[0] = new CartaDeMovimento(0, 0, EnumCarta.SORTE_01.getDescricao());
            this.cartas[1] = new CartaDeMovimento(0, 32, EnumCarta.SORTE_02.getDescricao());
            this.cartas[2] = new CartaDeMovimento(0, 16 , EnumCarta.SORTE_03.getDescricao());
            this.cartas[3] = new CartaDeMovimento(0, 3, EnumCarta.SORTE_04.getDescricao());
            this.cartas[4] = new CartaDeMovimento(1, 5, EnumCarta.SORTE_05.getDescricao());
            this.cartas[5] = new CartaDeMovimento(-1, 3, EnumCarta.SORTE_06.getDescricao());
            this.cartas[6] = new CartaDeMovimento(0, -2, EnumCarta.SORTE_07.getDescricao());

            this.cartas[7] = new CartaDeDinheiro(-1, 50, EnumCarta.SORTE_08.getDescricao());
            this.cartas[8] = new CartaDeDinheiro(1, 75, EnumCarta.SORTE_09.getDescricao());
            this.cartas[9] = new CartaDeDinheiro(-1, 50, EnumCarta.SORTE_10.getDescricao());
            this.cartas[10] = new CartaDeDinheiro(1, 100, EnumCarta.SORTE_11.getDescricao());
            this.cartas[11] = new CartaDeDinheiro(1, 50, EnumCarta.SORTE_12.getDescricao());
            this.cartas[12] = new CartaDeDinheiro(1, 150, EnumCarta.SORTE_13.getDescricao());
            this.cartas[13] = new CartaDeDinheiro(-1, 40, EnumCarta.SORTE_14.getDescricao());
            this.cartas[14] = new CartaDeDinheiro(1, 200, EnumCarta.SORTE_15.getDescricao());

            this.cartas[15] = new CartaVaParaCadeia(EnumCarta.SORTE_16.getDescricao());
            
        } else {
            this.cartas[0] = new CartaDeMovimento(0, 13, EnumCarta.COFRE_01.getDescricao());
            this.cartas[1] = new CartaDeMovimento(0, 39, EnumCarta.COFRE_02.getDescricao());
            this.cartas[2] = new CartaDeMovimento(0, 6, EnumCarta.COFRE_03.getDescricao());
            this.cartas[3] = new CartaDeMovimento(0, 23, EnumCarta.COFRE_04.getDescricao());
            this.cartas[4] = new CartaDeMovimento(1, 2, EnumCarta.COFRE_05.getDescricao());
            this.cartas[5] = new CartaDeMovimento(-1, 4, EnumCarta.COFRE_06.getDescricao());
            this.cartas[6] = new CartaDeMovimento(0, -1, EnumCarta.COFRE_07.getDescricao());

            this.cartas[7] = new CartaDeDinheiro(1, 100, EnumCarta.COFRE_08.getDescricao());
            this.cartas[8] = new CartaDeDinheiro(1, 10, EnumCarta.COFRE_09.getDescricao());
            this.cartas[9] = new CartaDeDinheiro(1, 200, EnumCarta.COFRE_10.getDescricao());
            this.cartas[10] = new CartaDeDinheiro(1, 25, EnumCarta.COFRE_11.getDescricao());
            this.cartas[11] = new CartaDeDinheiro(-1, 75, EnumCarta.COFRE_12.getDescricao());
            this.cartas[12] = new CartaDeDinheiro(-1, 100, EnumCarta.COFRE_13.getDescricao());
            this.cartas[13] = new CartaDeDinheiro(1, 100, EnumCarta.COFRE_14.getDescricao());
            this.cartas[14] = new CartaDeDinheiro(-1, 45, EnumCarta.COFRE_15.getDescricao());

            this.cartas[15] = new CartaVaParaCadeia(EnumCarta.COFRE_16.getDescricao());
        }
       this.embaralhar();
    }

    // Função embaralha o deck, usa random para tal.
    public final void embaralhar() {
        int j;
        
        Random r = new Random();
        for (int i = 15; i > 0; i--) {
            j = r.nextInt(i);
            if (j != i) {
                Carta temp = this.cartas[i];
                this.cartas[i] = this.cartas[j];
                this.cartas[j] = temp;
            }
        }
    }

    // Função retira a última carta do deck, insere novamente no inicio do deck e retorna para o usuário a carta retirada.
    public Carta puxarCarta() {
        
        Carta tirada = this.cartas[15];
        for (int i = 15; i > 0; i--) {
            this.cartas[i] = this.cartas[i - 1];
        }
        this.cartas[0] = tirada;
        
        return tirada;
    }

}
