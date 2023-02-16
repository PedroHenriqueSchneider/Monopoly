package gerenciador;

import java.util.Scanner;

public class Gerenciador {

    /**
     *   Classe que instância o jogo, acolhe o número de jogadores e seus respectivos nomes e      * inicia assim, o monopoly.  
     */
    public static void main(String[] args) {
        System.out.println("SEJAM BEM VINDOS AO MONOPOLY!!!!!");
        System.out.println("Quantos jogadores irão participar? (2 a 4)");
        int jogadores = 0;
        while (true) {
            try {
                jogadores = Jogo.s.nextInt();
                validarJogadores(jogadores);
                break;
            }
            catch (Exception e) {
                System.out.println("Este não é um número válido, tente novamente!");
                Jogo.s.nextLine();
            }
        }
        Jogo.s.nextLine();
        String[] nomes = new String[jogadores];
        System.out.println("Insira o nome de cada jogador");
        for (int i = 0; i < jogadores; i++) {
            nomes[i] = Jogo.s.nextLine();
        }
        
        Jogo monopoly;
        
        if (jogadores == 2)
            monopoly = new Jogo(jogadores, nomes[0], nomes[1]); 
        else if (jogadores == 3)
            monopoly = new Jogo(jogadores, nomes[0], nomes[1], nomes[2]);
        else
            monopoly = new Jogo(jogadores, nomes[0], nomes[1], nomes[2], nomes[3]);
        
        monopoly.jogar();
        Jogo.s.close(); 
    }
    
    public static void validarJogadores(int n) throws Exception {
        if (n < 2 || n > 4)
            throw new Exception();
    }

}  
