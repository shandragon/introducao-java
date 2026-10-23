package exercicios;

import java.util.Random; // Importa a classe Random, usada para sortear números no embaralhamento
 
public class Domino {
 
    // Constante: quantidade total de peças do dominó (28)
    static final int TOTAL_PECAS = 28;
    // Constante: quantidade de jogadores na partida
    static final int NUM_JOGADORES = 4;
    // Constante: quantidade de peças que cada jogador recebe
    static final int PECAS_POR_JOGADOR = 7;
 
    public static void main(String[] args) {
 
        // ===== PASSO 1: GERAR AS 28 PEÇAS SEM REPETIÇÃO =====
 
        // Matriz de 28 linhas (peças) e 2 colunas
        // (lado A na coluna 0 e lado B na coluna 1)
        int[][] pecas = new int[TOTAL_PECAS][2];
        int indice = 0; // Contador que indica em qual linha da matriz a próxima peça será gravada
 
        for (int a = 0; a <= 6; a++) { // Percorre o primeiro valor da peça, de 0 a 6
            for (int b = a; b <= 6; b++) { // O segundo valor começa em 'a', assim (2,5) e (5,2) não se repetem
                pecas[indice][0] = a;
                pecas[indice][1] = b;
                indice++; // Avança para a próxima linha da matriz
            }
        }
 
        // ===== PASSO 2: EMBARALHAR E DISTRIBUIR AS PEÇAS =====
 
        Random random = new Random(); // Cria o objeto que gera números aleatórios
        for (int i = pecas.length - 1; i > 0; i--) { // Percorre as peças do fim para o início
            int j = random.nextInt(i + 1); // Sorteia uma posição entre 0 e i
            int[] temp = pecas[i]; // Guarda a peça da posição i em uma variável temporária
            pecas[i] = pecas[j]; // Coloca na posição i a peça sorteada
            pecas[j] = temp; // Coloca na posição j a peça que estava guardada (troca concluída)
        }
 
        // Array de 4 jogadores, cada um com 7 peças, cada peça com 2 lados
        int[][][] maos = new int[NUM_JOGADORES][PECAS_POR_JOGADOR][2];
        int posicao = 0; // Posição atual no baralho embaralhado
 
        for (int jogador = 0; jogador < NUM_JOGADORES; jogador++) { // Percorre os 4 jogadores
            for (int p = 0; p < PECAS_POR_JOGADOR; p++) { // Percorre as 7 peças que cada jogador
                maos[jogador][p][0] = pecas[posicao][0]; // Copia o lado A da peça para a mão do jogador
                maos[jogador][p][1] = pecas[posicao][1]; // Copia o lado B da peça para a mão do jogador
                posicao++; // Avança para a próxima peça do baralho
            }
        }
 
        // ===== PASSO 3: IMPRIMIR AS MÃOS E CALCULAR OS TOTAIS =====
 
        for (int jogador = 0; jogador < NUM_JOGADORES; jogador++) {
            int soma = 0; // Zera o acumulador de pontos da mão deste jogador
            System.out.print("Jogador " + (jogador + 1) + ": ");
 
            for (int p = 0; p < PECAS_POR_JOGADOR; p++) {
                int ladoA = maos[jogador][p][0];
                int ladoB = maos[jogador][p][1];
                System.out.print("(" + ladoA + "," + ladoB + ") ");
                soma = soma + ladoA + ladoB; // Soma os dois lados da peça ao total da mão
            }
 
            System.out.println("| Total: " + soma);
        }
 
        // ===== PASSO 4: DESCOBRIR QUEM TEM A PEÇA (6,6) =====
 
        int inicial = -1; // Guarda o número do jogador que começa (-1 significa "ainda não encontrado")
 
        for (int jogador = 0; jogador < NUM_JOGADORES; jogador++) {
            for (int p = 0; p < PECAS_POR_JOGADOR; p++) {
                if (maos[jogador][p][0] == 6 && maos[jogador][p][1] == 6) { // Verifica se a peça é (6,6)
                    inicial = jogador + 1; // Guarda o número do jogador (índice + 1)
                }
            }
        }
 
        System.out.println("Jogador " + inicial + " começa a partida.");
    }
}
