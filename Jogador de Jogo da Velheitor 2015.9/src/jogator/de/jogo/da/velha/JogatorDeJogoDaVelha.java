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
                //no modo player vs computador, a cada rodada altera quem será o jogador inicial
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
    int pj;

    private velha jdv = new velha();

    void jogo() {
        for (int i = 0; i < 5; i++) {
            way[i] = 0;
        }
        uj = 0;
        pj = 0;

        if (altern) {
            System.out.print("\n\t~~Computador começa a jogar~~\n\n");
            do {
                //COMPUTADOR
                jdv.j2(computador());
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
                        uj = j;
                        jog = false;
                    }
                }
            } while (jdv.vencedor() == 0);
        } else {
            System.out.print("\n\t~~Você começa a jogar~~\n\n");
            do {
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
                        uj = j;
                        jog = false;
                    }
                }

                //COMPUTADOR
                if (jdv.vencedor() == 0) {
                    jdv.j2(computador());
                }
            } while (jdv.vencedor() == 0);
        }

        //EXIBIR VENCEDORES (ou perdedores)
        jdv.exibir_board();
        if (jdv.vencedor() == 1) {
            System.out.print("\n\n"
                    + "\nXXXXXXXXXXXXXXXXXXXXXXXX"
                    + "\nO                      X"
                    + "\nO código com erro, lek X"
                    + "\nO                      X"
                    + "\nOOOOOOOOOOOOOOOOOOOOOXXX");
        } else if (jdv.vencedor() == 2) {
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

    //auxilia o computador a saber que caminho de jogadas deve seguir
    int[] way = new int[5];

    //aki é onde as jogadas do computador são 'pensadas'
    int computador() {
        int jogada = 0;
        //'altern' tb mostra quem começou a jogar
        //computador sendo o PRIMEIRO a jogar
        if (altern) {
            if (way[0] == 0) {
                jogada = 1;
                if (uj == 3 || uj == 7 || uj == 5) {
                    way[0] = 1;
                } else if (uj == 2 || uj == 4) {
                    way[0] = 3;
                } else if (uj == 6 || uj == 8) {
                    way[0] = 4;
                } else if (uj == 9) {
                    way[0] = 5;
                }
            }

            if (way[0] == 1) {
                jogada = 9;
                if (way[1] == 1) {
                    jogada = induz();
                }
                way[1] = 1;
            }

            if (way[0] == 3) {
                jogada = 5;
                if (way[2] == 0) {
                    if (uj == 2) {
                        way[2] = 1;
                    } else if (uj == 4) {
                        way[2] = 2;
                    }
                }

                if (way[1] == 1) {
                    jogada = induz();
                    if (uj == 9) {
                        if (way[2] == 1) {
                            jogada = 7;
                        } else if (way[2] == 2) {
                            jogada = 3;
                        }
                    }
                }
                way[1] = 1;
            }

            if (way[0] == 4) {
                jogada = 5;
                if (way[1] == 1) {
                    jogada = induz();
                }
                way[1] = 1;
            }

            if (way[0] == 5) {
                jogada = 3;
                if (way[1] == 1) {
                    if (uj != 7) {
                        jogada = 7;
                    } else {
                        jogada = induz();
                    }
                    if (way[2] == 1) {
                        jogada = induz();
                    }
                    way[2] = 1;
                }
                way[1] = 1;
            }
            //computador sendo o SEGUNDO a jogar
        } else {
            if (way[0] == 0) {
                if (uj == 1 || uj == 3 || uj == 7 || uj == 9) {
                    way[0] = 1;
                    pj = uj;
                } else if (uj == 2 || uj == 4 || uj == 6 || uj == 8) {
                    way[0] = 2;
                    pj = uj;
                } else if (uj == 5) {
                    way[0] = 3;
                    pj = uj;
                }
            }

            if (way[0] == 1) {
                jogada = 5;

                if (way[1] == 0) {
                    if ((pj == 1 && uj == 9) || (pj == 9 && uj == 1) || (pj == 3 && uj == 7) || (pj == 7 && uj == 3)) {
                        way[1] = 1;
                    } else if ((pj == 1 && (uj == 2 || uj == 3 || uj == 4 || uj == 7))
                            || (pj == 3 && (uj == 1 || uj == 2 || uj == 6 || uj == 7))
                            || (pj == 7 && (uj == 1 || uj == 4 || uj == 8 || uj == 9))
                            || (pj == 9 && (uj == 7 || uj == 8 || uj == 3 || uj == 6))) {
                        way[1] = 2;
                    } else if ((pj == 1 && (uj == 6 || uj == 8))
                            || (pj == 3 && (uj == 4 || uj == 8))
                            || (pj == 7 && (uj == 2 || uj == 6))
                            || (pj == 9 && (uj == 2 || uj == 4))) {
                        way[1] = 3;
                    }
                }

                if (way[1] == 1) {
                    jogada = 6;
                    if (way[2] == 1) {
                        jogada = induz();
                    }
                    way[2] = 1;
                }

                if (way[1] == 2) {
                    jogada = induz();
                }

                if (way[1] == 3) {
                    if (way[2] == 0) {
                        if (pj == 1 && uj == 6) {
                            jogada = 8;
                        } else if (pj == 1 && uj == 8) {
                            jogada = 6;
                        } else if (pj == 3 && uj == 8) {
                            jogada = 4;
                        } else if (pj == 3 && uj == 4) {
                            jogada = 8;
                        } else if (pj == 7 && uj == 6) {
                            jogada = 2;
                        } else if (pj == 7 && uj == 2) {
                            jogada = 6;
                        } else if (pj == 9 && uj == 4) {
                            jogada = 2;
                        } else if (pj == 9 && uj == 2) {
                            jogada = 4;
                        }
                    }
                    if (way[2] == 1) {
                        jogada = induz();
                    }
                    way[2] = 1;
                }
            }
            if (way[0] == 2) {
                jogada = 5;

                if (way[1] == 0) {
                    if ((pj == 2 && uj == 8) || (pj == 8 && uj == 2) || (pj == 4 && uj == 6) || (pj == 6 && uj == 4)) {
                        way[1] = 1;
                    } else if ((pj == 2 && (uj == 1 || uj == 3))
                            || (pj == 4 && (uj == 1 || uj == 7))
                            || (pj == 6 && (uj == 3 || uj == 9))
                            || (pj == 8 && (uj == 7 || uj == 9))) {
                        way[1] = 2;
                    } else if ((pj == 2 && (uj == 7 || uj == 9 || uj == 4 || uj == 6))
                            || (pj == 4 && (uj == 3 || uj == 9 || uj == 2 || uj == 8))
                            || (pj == 6 && (uj == 1 || uj == 7 || uj == 2 || uj == 8))
                            || (pj == 8 && (uj == 1 || uj == 3 || uj == 4 || uj == 6))) {
                        way[1] = 3;
                    }
                }

                if (way[1] == 1) {
                    jogada = 3;
                    if (way[2] == 1) {
                        jogada = induz();
                    }
                    way[2] = 1;
                }

                if (way[1] == 2) {
                    jogada = induz();
                }

                if (way[1] == 3) {
                    if (way[2] == 0) {
                        if (pj == 2 && (uj == 7 || uj == 4)) {
                            jogada = 1;
                        } else if (pj == 2 && (uj == 9 || uj == 6)) {
                            jogada = 3;
                        } else if (pj == 4 && (uj == 3 || uj == 2)) {
                            jogada = 1;
                        } else if (pj == 4 && (uj == 9 || uj == 8)) {
                            jogada = 7;
                        } else if (pj == 6 && (uj == 1 || uj == 2)) {
                            jogada = 3;
                        } else if (pj == 6 && (uj == 7 || uj == 8)) {
                            jogada = 9;
                        } else if (pj == 8 && (uj == 1 || uj == 4)) {
                            jogada = 7;
                        } else if (pj == 8 && (uj == 3 || uj == 6)) {
                            jogada = 9;
                        }
                    }
                    if (way[2] == 1) {
                        jogada = induz();
                        System.out.print("coco");
                    }
                    way[2] = 1;
                }

            }
            if (way[0] == 3) {
                jogada = 7;

                if (way[1] == 0) {
                    if (uj == 4 || uj == 8) {
                        way[1] = 1;
                    } else if (uj == 3) {
                        way[1] = 2;
                    } else if (uj == 1 || uj == 2 || uj == 6 || uj == 9) {
                        way[1] = 3;
                    }
                }

                if (way[1] == 1) {
                    jogada = induz();
                    if (uj == 3) {
                        if (jdv.j_poss(1)) {
                            jogada = 1;
                        } else if (jdv.j_poss(9)) {
                            jogada = 9;
                        } else {
                            jogada = induz();
                        }
                    }
                }

                if (way[1] == 2) {
                    jogada = 1;
                    if (way[2] == 1) {
                        jogada = induz();
                    }
                    way[2] = 1;
                }

                if (way[1] == 3) {
                    jogada = induz();
                }
            }
        }
        return jogada;
    }

    int bloco(int linha, int coluna) {
        int bloco = 0;
        if (linha == 1 && coluna == 1) {
            bloco = 1;
        } else if (linha == 1 && coluna == 2) {
            bloco = 2;
        } else if (linha == 1 && coluna == 3) {
            bloco = 3;
        } else if (linha == 2 && coluna == 1) {
            bloco = 4;
        } else if (linha == 2 && coluna == 2) {
            bloco = 5;
        } else if (linha == 2 && coluna == 3) {
            bloco = 6;
        } else if (linha == 3 && coluna == 1) {
            bloco = 7;
        } else if (linha == 3 && coluna == 2) {
            bloco = 8;
        } else if (linha == 3 && coluna == 3) {
            bloco = 9;
        }
        return bloco;
    }

    int induz() {
        int jogada = 0;
        boolean jogando = true;
        //TENTA GANHAR
        //verifica se possível ganhar nas linhas
        for (int l = 1; l < 4 && jogando; l++) {
            if (jdv.board(l, 1) == 0 && jdv.board(l, 2) == 2 && jdv.board(l, 3) == 2) {
                jogada = bloco(l, 1);
                jogando = false;
            } else if (jdv.board(l, 1) == 2 && jdv.board(l, 2) == 0 && jdv.board(l, 3) == 2) {
                jogada = bloco(l, 2);
                jogando = false;
            } else if (jdv.board(l, 1) == 2 && jdv.board(l, 2) == 2 && jdv.board(l, 3) == 0) {
                jogada = bloco(l, 3);
                jogando = false;
            }
        }

        //verifica se possível ganhar nas colunas
        for (int c = 1; c < 4 && jogando; c++) {
            if (jdv.board(1, c) == 0 && jdv.board(2, c) == 2 && jdv.board(3, c) == 2) {
                jogada = bloco(1, c);
                jogando = false;
            } else if (jdv.board(1, c) == 2 && jdv.board(2, c) == 0 && jdv.board(3, c) == 2) {
                jogada = bloco(2, c);
                jogando = false;
            } else if (jdv.board(1, c) == 2 && jdv.board(2, c) == 2 && jdv.board(3, c) == 0) {
                jogada = bloco(3, c);
                jogando = false;
            }
        }

        //verifica se possível ganhar nas diagonais
        if (jdv.board(1, 1) == 0 && jdv.board(2, 2) == 2 && jdv.board(3, 3) == 2 && jogando) {
            jogada = bloco(1, 1);
            jogando = false;
        } else if (jdv.board(1, 3) == 0 && jdv.board(2, 2) == 2 && jdv.board(3, 1) == 2 && jogando) {
            jogada = bloco(1, 3);
            jogando = false;
        } else if (jdv.board(1, 1) == 2 && jdv.board(2, 2) == 0 && jdv.board(3, 3) == 2 && jogando) {
            jogada = bloco(2, 2);
            jogando = false;
        } else if (jdv.board(1, 3) == 2 && jdv.board(2, 2) == 0 && jdv.board(3, 1) == 2 && jogando) {
            jogada = bloco(2, 2);
            jogando = false;
        } else if (jdv.board(1, 1) == 2 && jdv.board(2, 2) == 2 && jdv.board(3, 3) == 0 && jogando) {
            jogada = bloco(3, 3);
            jogando = false;
        } else if (jdv.board(1, 3) == 2 && jdv.board(2, 2) == 2 && jdv.board(3, 1) == 0 && jogando) {
            jogada = bloco(3, 1);
            jogando = false;
        }

        //IMPEDIR
        //verifica se precisa impedir nas linhas
        for (int l = 1; l < 4 && jogando; l++) {
            if (jdv.board(l, 1) == 0 && jdv.board(l, 2) == 1 && jdv.board(l, 3) == 1) {
                jogada = bloco(l, 1);
                jogando = false;
            } else if (jdv.board(l, 1) == 1 && jdv.board(l, 2) == 0 && jdv.board(l, 3) == 1) {
                jogada = bloco(l, 2);
                jogando = false;
            } else if (jdv.board(l, 1) == 1 && jdv.board(l, 2) == 1 && jdv.board(l, 3) == 0) {
                jogada = bloco(l, 3);
                jogando = false;
            }
        }

        //verifica se precisa impedir nas colunas
        for (int c = 1; c < 4 && jogando; c++) {
            if (jdv.board(1, c) == 0 && jdv.board(2, c) == 1 && jdv.board(3, c) == 1) {
                jogada = bloco(1, c);
                jogando = false;
            } else if (jdv.board(1, c) == 1 && jdv.board(2, c) == 0 && jdv.board(3, c) == 1) {
                jogada = bloco(2, c);
                jogando = false;
            } else if (jdv.board(1, c) == 1 && jdv.board(2, c) == 1 && jdv.board(3, c) == 0) {
                jogada = bloco(3, c);
                jogando = false;
            }
        }

        //verifica se precisa impedir nas diagonais
        if (jdv.board(1, 1) == 0 && jdv.board(2, 2) == 1 && jdv.board(3, 3) == 1 && jogando) {
            jogada = bloco(1, 1);
            jogando = false;
        } else if (jdv.board(1, 3) == 0 && jdv.board(2, 2) == 1 && jdv.board(3, 1) == 1 && jogando) {
            jogada = bloco(1, 3);
            jogando = false;
        } else if (jdv.board(1, 1) == 1 && jdv.board(2, 2) == 0 && jdv.board(3, 3) == 1 && jogando) {
            jogada = bloco(2, 2);
            jogando = false;
        } else if (jdv.board(1, 3) == 1 && jdv.board(2, 2) == 0 && jdv.board(3, 1) == 1 && jogando) {
            jogada = bloco(2, 2);
            jogando = false;
        } else if (jdv.board(1, 1) == 1 && jdv.board(2, 2) == 1 && jdv.board(3, 3) == 0 && jogando) {
            jogada = bloco(3, 3);
            jogando = false;
        } else if (jdv.board(1, 3) == 1 && jdv.board(2, 2) == 1 && jdv.board(3, 1) == 0 && jogando) {
            jogada = bloco(3, 1);
            jogando = false;
        }

        //MARCAR PROPÍCIO
        //verifica onde é melhor nas linhas
        for (int l = 1; l < 4 && jogando; l++) {
            if (jdv.board(l, 1) == 2 && jdv.board(l, 2) == 0 && jdv.board(l, 3) == 0) {
                jogada = bloco(l, 2);
                jogando = false;
            } else if (jdv.board(l, 1) == 0 && jdv.board(l, 2) == 2 && jdv.board(l, 3) == 0) {
                jogada = bloco(l, 1);
                jogando = false;
            } else if (jdv.board(l, 1) == 0 && jdv.board(l, 2) == 0 && jdv.board(l, 3) == 2) {
                jogada = bloco(l, 2);
                jogando = false;
            }
        }

        //verifica onde é melhor nas colunas
        for (int c = 1; c < 4 && jogando; c++) {
            if (jdv.board(1, c) == 2 && jdv.board(2, c) == 0 && jdv.board(3, c) == 0) {
                jogada = bloco(2, c);
                jogando = false;
            } else if (jdv.board(1, c) == 0 && jdv.board(2, c) == 2 && jdv.board(3, c) == 0) {
                jogada = bloco(1, c);
                jogando = false;
            } else if (jdv.board(1, c) == 0 && jdv.board(2, c) == 0 && jdv.board(3, c) == 2) {
                jogada = bloco(2, c);
                jogando = false;
            }
        }

        //verifica onde é melhor nas diagonais
        if (jdv.board(1, 1) == 2 && jdv.board(2, 2) == 0 && jdv.board(3, 3) == 0 && jogando) {
            jogada = bloco(2, 2);
            jogando = false;
        } else if (jdv.board(1, 3) == 2 && jdv.board(2, 2) == 0 && jdv.board(3, 1) == 0 && jogando) {
            jogada = bloco(2, 2);
            jogando = false;
        } else if (jdv.board(1, 1) == 0 && jdv.board(2, 2) == 2 && jdv.board(3, 3) == 0 && jogando) {
            jogada = bloco(1, 1);
            jogando = false;
        } else if (jdv.board(1, 3) == 0 && jdv.board(2, 2) == 2 && jdv.board(3, 1) == 0 && jogando) {
            jogada = bloco(1, 3);
            jogando = false;
        } else if (jdv.board(1, 1) == 0 && jdv.board(2, 2) == 0 && jdv.board(3, 3) == 2 && jogando) {
            jogada = bloco(2, 2);
            jogando = false;
        } else if (jdv.board(1, 3) == 0 && jdv.board(2, 2) == 0 && jdv.board(3, 1) == 2 && jogando) {
            jogada = bloco(2, 2);
            jogando = false;
        }

        //SE NADA DER CERTO ESCOLHE O PRIMEIRO LUGAR LIVRE PRA MARCAR
        for (int b = 1; b < 10 && jogando; b++) {
            if (jdv.getb(b) == 0) {
                jogada = b;
                jogando = false;
            }
        }

        return jogada;
    }
}

//CLASSE QUE GERENCIA O TABULEIRO
class velha {

    private int[][] board = new int[3][3];

    int getb(int bloco) {
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

    int board(int linha, int coluna) {
        //de 1 à 3
        return this.board[linha - 1][coluna - 1];
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
    }

    int vencedor() {
        boolean jogando = true;
        int vencedor = 0;
        //verifica se tem vencedor nas linhas
        for (int l = 1; l < 8 && jogando; l = l + 3) {
            if (getb(l) == getb(l + 1) && getb(l + 1) == getb(l + 2) && getb(l) != 0) {
                vencedor = getb(l);
                jogando = false;
            }
        }

        //verifica se tem vencedor nas colunas
        for (int c = 1; c < 4 && jogando; c++) {
            if (getb(c) == getb(c + 3) && getb(c + 3) == getb(c + 6) && getb(c) != 0) {
                vencedor = getb(c);
                jogando = false;
            }
        }

        //verifica se tem vencedor nas diagonais
        if ((getb(1) == getb(5)) && (getb(5) == getb(9)) && (getb(1) != 0) && (jogando)) {
            vencedor = getb(1);
            jogando = false;
        } else if ((getb(3) == getb(5)) && (getb(5) == getb(7)) && (getb(3) != 0) && (jogando)) {
            vencedor = getb(3);
            jogando = false;
        }

        //verifica se não há mais jogadas possíveis
        for (int b = 1; b < 10 && jogando; b++) {
            vencedor = 3;
            if (getb(b) == 0) {
                vencedor = 0;
                jogando = false;
            }
        }

        return vencedor;
    }
}
