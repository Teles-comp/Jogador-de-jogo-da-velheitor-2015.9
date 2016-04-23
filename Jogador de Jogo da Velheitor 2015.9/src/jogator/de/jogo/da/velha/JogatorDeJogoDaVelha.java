/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogator.de.jogo.da.velha;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Lucas Teles
 */
public class JogatorDeJogoDaVelha {

    public static void main(String[] args) {

        teste();

    }

    public static void teste() {
        //
        /*      TESTES BASICOS
        comp jdv = new comp();
        jdv.zerar();
        jdv.exibir_board();
        Scanner scan = new Scanner(System.in);
        scan.nextShort();
        //
        //*/
        //
        //*      TESTE   PLAY
        play jdv = new play();
        jdv.iniciar();
        //
        //*/
    }

}

//CLASSE ONDE SE JOGA
class play {

    private char resp;
    private Scanner scan = new Scanner(System.in);

    private pvp jogador = new pvp();
    private pvcom computador = new pvcom();

    private boolean pc = false;
    private boolean inicio = true;
    private boolean wait = true;
    boolean cont = true;

    private void questionar() {
        if (inicio) {
            boolean wait = true;
            do {
                System.out.print("\n\n\t"
                        + "Deseja jogar contra um jogador (j) \n\t"
                        + "ou contra o computador (c) ? \n\t"
                        + "          (j/c)\n\t"
                        + "            ");
                resp = scan.next().charAt(0);
                if (resp == 'j') {
                    pc = false;
                    wait = false;
                } else if (resp == 'c') {
                    pc = true;
                    wait = false;
                }
            } while (wait);
            inicio = false;
        } else {
            wait = true;
            do {
                System.out.print("\n\n\t"
                        + "Deseja continuar jogando no mesmo modo? \n\t"
                        + "               (s/n)\n\t"
                        + "                 ");
                resp = scan.next().charAt(0);
                if (resp == 's') {
                    wait = false;
                } else if (resp == 'n') {
                    wait = true;
                    do {

                        do {

                            System.out.print("\n\t"
                                    + "Deseja continuar jogando? \n\t"
                                    + "               (s/n)\n\t"
                                    + "                 ");
                            resp = scan.next().charAt(0);
                            if (resp == 'n') {
                                wait = false;
                                cont = false;
                            } else if (resp == 's') {
                                System.out.print("\n\t"
                                        + "Deseja jogar contra um jogador (j) \n\t"
                                        + "ou contra o computador (c) ? \n\t"
                                        + "          (j/c)\n\t"
                                        + "            ");
                                resp = scan.next().charAt(0);
                                if (resp == 'j') {
                                    pc = false;
                                    wait = false;
                                } else if (resp == 'c') {
                                    pc = true;
                                    wait = false;
                                }
                            }
                        } while (wait);

                    } while (wait);
                }
            } while (wait);
        }
    }

    void iniciar() {
        questionar();

        cont = true;

        do {
            if (pc) {
                computador.jogo();
            } else {
                jogador.jogo();
            }
            questionar();
        } while (cont);

    }

}

// COLOQUEM AKI NESSA CLASSE O CÓDIGO DO PLAYER VS PLAYER
class pvp {

    private char resp;
    private Scanner scan = new Scanner(System.in);
    private Scanner scan2;

    private velha jdv = new velha();

    private boolean jog = true;

    void jogo() {
        System.out.print("\n\t~~Jogador 1 começa a jogar~~\n\n");
        do {
            //JOGADOR 1
            jog = true;
            while (jog && jdv.vencedor() == 0) {
                int j;
                jdv.exibir_board();
                System.out.print("\t(insira o bloco em que quer jogar)"
                        + "\n\n\tJogador 1: ");
                scan2 = new Scanner(System.in);
                j = scan2.nextInt();
                if (jdv.j_poss(j)) {
                    jdv.j1(j);
                    jog = false;
                }
            }

            //JOGADOR 2
            jog = true;
            while (jog && jdv.vencedor() == 0) {
                int j;
                jdv.exibir_board();
                System.out.print("\t(insira o bloco em que quer jogar)"
                        + "\n\n\tJogador 2: ");
                scan2 = new Scanner(System.in);
                j = scan2.nextInt();
                if (jdv.j_poss(j)) {
                    jdv.j2(j);
                    jog = false;
                }
            }
        } while (jdv.vencedor() == 0);

        //EXIBIR VENCEDORES (ou perdedores)
        jdv.exibir_board();
        if (jdv.vencedor() == 1) {
            System.out.print("\n\n"
                    + "\nXXXXXXXXXXXXXXXXXXXX"
                    + "\nX                  X"
                    + "\nX JOGADOR 1 VENCEU X"
                    + "\nX                  X"
                    + "\nXXXXXXXXXXXXXXXXXXXX");
        } else if (jdv.vencedor() == 2) {
            System.out.print("\n\n"
                    + "\nOOOOOOOOOOOOOOOOOOOO"
                    + "\nO                  O"
                    + "\nO JOGADOR 2 VENCEU O"
                    + "\nO                  O"
                    + "\nOOOOOOOOOOOOOOOOOOOO");
        } else if (jdv.vencedor() == 3) {
            System.out.print("\n\n"
                    + "\nPERDEU~~PERDEU~~PERDEU~~PERDEU~"
                    + "\n~                             ~"
                    + "\n~ E todo mundo perdeu, acabou P"
                    + "\nP                             E"
                    + "\nERDEU~~PERDEU~~PERDEU~~PERDEU~~");
        }
        jdv.zerar();
    }
}

//CLASSE QUE EXECUTA O PLAYER VS COMPUTADOR
class pvcom {

    private char resp;
    private Scanner scan = new Scanner(System.in);
    private Scanner scan2;
    private boolean jog = true;
    boolean altern = true;
    int uj;

    private velha jdv = new velha();

    void jogo() {
        if (altern) {
            System.out.print("\n\t~~Computador começa a jogar~~\n\n");
            do {
                //COMPUTADOR
                jdv.j2(computador());
                uj=jdv.uj();
                
                //PESSOA
                jog = true;
                while (jog && jdv.vencedor() == 0) {
                    int j;
                    jdv.exibir_board();
                    System.out.print("\t(insira o bloco em que quer jogar)"
                            + "\n\n\tVOCÊ: ");
                    scan2 = new Scanner(System.in);
                    j = scan2.nextInt();
                    if (jdv.j_poss(j)) {
                        jdv.j1(j);
                        uj=jdv.uj();
                        jog = false;
                    }
                }
            } while (jdv.vencedor() == 0);
        } else {

        }

        //EXIBIR VENCEDORES (ou perdedores)
        jdv.exibir_board();
        if (jdv.vencedor() == 2) {
            System.out.print("\n\n"
                    + "\nOOOOOOOOOOOOOOOOOOOOO"
                    + "\nO                   O"
                    + "\nO COMPUTADOR VENCEU O"
                    + "\nO                   O"
                    + "\nOOOOOOOOOOOOOOOOOOOOO");
        } else if (jdv.vencedor() == 3) {
            System.out.print("\n\n"
                    + "\nPERDEU~~PERDEU~~PERDEU~~PERDEU~"
                    + "\n~                             ~"
                    + "\n~ E todo mundo perdeu, acabou P"
                    + "\nP                             E"
                    + "\nERDEU~~PERDEU~~PERDEU~~PERDEU~~");
        }
        jdv.zerar();

        //faz a alternancia para o próximo jogo
        if (altern) {
            altern = false;
        } else {
            altern = true;
        }
    }

    int computador() {
        int jogada = 1;
        //'altern' tb mostra quem começou a jogar
        if (altern) {
            
        } //
        else {

        }

        return jogada;
    }
    
    void indut(){
        
    }
}

//CLASSE QUE GERENCIA O TABULEIRO
class velha {

    private int[][] board = new int[3][3];

    int get_b(int bloco) {
        /*
            Para cada bloco (de 1 a 9) essas devem ser as coordenadas iniciais:
                1-00 | 2-01 | 3-02
                ---- ---- ----
                4-10 | 5-11 | 6-12
                ---- ---- ----
                7-20 | 8-21 | 9-20
         */

        int l = 0;
        int c = 2;
        if (bloco > 3) {
            l = l + 1;
            if (bloco > 6) {
                l = l + 1;
            }
        }
        for (int i = 0; i < bloco; i++) {
            if (c == 2) {
                c = 0;
            } else if (c == 0) {
                c = 1;
            } else if (c == 1) {
                c = 2;
            }
        }

        return this.board[l][c];
    }

    private int ultim_bloco;

    int uj() {
        return ultim_bloco;
    }

    void zerar() {
        //reinicia o tabuleiro
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.board[i][j] = 0;
            }
        }
    }

    void exibir_board() {
        System.out.print("\n\t      ~Jogo~\n\n            ");
        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    System.out.print("   ");

                } else if (board[i][j] == 1) {
                    System.out.print(" X ");

                } else if (board[i][j] == 2) {
                    System.out.print(" O ");
                }
                if (j == 0 || j == 1) {
                    System.out.print("|");
                }
                if (j == 2 && i == 0) {
                    System.out.print("\n   1 2 3    ---|---|---\t\n   4 5 6    ");
                }
                if (j == 2 && i == 1) {
                    System.out.print("\n   7 8 9    ---|---|---\n\t    ");
                }

            }

        }
        System.out.print("\n\n");
    }

    boolean j_poss(int bloco) {
        /*
            Para cada bloco (de 1 a 9) essas devem ser as coordenadas iniciais:
                1-00 | 2-01 | 3-02
                ---- ---- ----
                4-10 | 5-11 | 6-12
                ---- ---- ----
                7-20 | 8-21 | 9-20
         */

        int l = 0;
        int c = 2;
        if (bloco > 3) {
            l = l + 1;
            if (bloco > 6) {
                l = l + 1;
            }
        }
        for (int i = 0; i < bloco; i++) {
            if (c == 2) {
                c = 0;
            } else if (c == 0) {
                c = 1;
            } else if (c == 1) {
                c = 2;
            }
        }
        if (this.board[l][c] == 0) {
            return true;

        } else {
            return false;
        }

    }

    void j1(int bloco) {
        /*
            Para cada bloco (de 1 a 9) essas devem ser as coordenadas iniciais:
                1-00 | 2-01 | 3-02
                ---- ---- ----
                4-10 | 5-11 | 6-12
                ---- ---- ----
                7-20 | 8-21 | 9-20
         */

        int l = 0;
        int c = 2;
        if (bloco > 3) {
            l = l + 1;
            if (bloco > 6) {
                l = l + 1;
            }
        }
        for (int i = 0; i < bloco; i++) {
            if (c == 2) {
                c = 0;
            } else if (c == 0) {
                c = 1;
            } else if (c == 1) {
                c = 2;
            }
        }

        this.board[l][c] = 1;
        ultim_bloco = this.board[l][c];
    }

    void j2(int bloco) {
        /*
            Para cada bloco (de 1 a 9) essas devem ser as coordenadas iniciais:
                1-00 | 2-01 | 3-02
                ---- ---- ----
                4-10 | 5-11 | 6-12
                ---- ---- ----
                7-20 | 8-21 | 9-20
         */

        int l = 0;
        int c = 2;
        if (bloco > 3) {
            l = l + 1;
            if (bloco > 6) {
                l = l + 1;
            }
        }
        for (int i = 0; i < bloco; i++) {
            if (c == 2) {
                c = 0;
            } else if (c == 0) {
                c = 1;
            } else if (c == 1) {
                c = 2;
            }
        }

        this.board[l][c] = 2;
        ultim_bloco = this.board[l][c];
    }

    int vencedor() {
        boolean jogando = true;
        int vencedor = 0;
        //verifica se tem vencedor nas linhas
        for (int l = 1; l < 8 && jogando; l = l + 3) {
            if (get_b(l) == get_b(l + 1) && get_b(l + 1) == get_b(l + 2) && get_b(l) != 0) {
                vencedor = get_b(l);
                jogando = false;
            }
        }

        //verifica se tem vencedor nas colunas
        for (int c = 1; c < 4 && jogando; c++) {
            if (get_b(c) == get_b(c + 3) && get_b(c + 3) == get_b(c + 6) && get_b(c) != 0) {
                vencedor = get_b(c);
                jogando = false;
            }
        }

        //verifica se tem vencedor nas diagonais
        if ((get_b(1) == get_b(5)) && (get_b(5) == get_b(9)) && (get_b(1) != 0) && (jogando)) {
            vencedor = get_b(1);
            jogando = false;
        } else if ((get_b(3) == get_b(5)) && (get_b(5) == get_b(7)) && (get_b(3) != 0) && (jogando)) {
            vencedor = get_b(3);
            jogando = false;
        }

        //verifica se não há mais jogadas possíveis
        for (int b = 1; b < 10 && jogando; b++) {
            vencedor = 3;
            if (get_b(b) == 0) {
                vencedor = 0;
                jogando = false;
            }
        }

        return vencedor;
    }
}
