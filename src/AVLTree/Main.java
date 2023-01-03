package AVL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        AVLTree t = new AVLTree();
        Node root = null;
        while (true) {
            System.out.println("(1) Inserir");
            System.out.println("(2) Deletar");
            System.out.println("(3) Menor no");
            System.out.println("(4) Maior no");
            System.out.println("(5) Procurar no");
            System.out.println("(6) Imprimir em pre-ordem");
            System.out.println("(7) Somar dois nos");
            System.out.println("(0) Fim");

            try {
                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                String s = bufferRead.readLine();

                if (Integer.parseInt(s) == 0) {
                    return;
                }
                else if (Integer.parseInt(s) == 1) {
                    System.out.print("Valor a ser inserido: ");
                    root = t.insert(root, Integer.parseInt(bufferRead.readLine()));
                    t.print(root);
                }
                else if (Integer.parseInt(s) == 2) {
                    System.out.print("Valor a ser deletado: ");
                    root = t.deleteNode(root, Integer.parseInt(bufferRead.readLine()));
                    t.print(root);
                }
                else if (Integer.parseInt(s) == 3){
                    System.out.println(t.lowestNode(root));
                }
                else if (Integer.parseInt(s) == 4){
                    System.out.println(t.highestNode(root));
                }
                else if (Integer.parseInt(s) == 5){
                    System.out.print("Valor a ser procurado: ");
                    Node node = t.searchNode(root, Integer.parseInt(bufferRead.readLine()));
                    System.out.println(node.getValue() + " foi encontrado.");
                }
                else if (Integer.parseInt(s) == 6){
                    t.preOrder(root);
                }
                else if (Integer.parseInt(s) == 7){
                    System.out.println("Insira o primeiro valor da soma: ");
                    int valor1 = Integer.parseInt(bufferRead.readLine());
                    System.out.println("Insira o segundo valor da soma: ");
                    int valor2 = Integer.parseInt(bufferRead.readLine());
                    System.out.println("O resultado da soma e: " + t.soma(root, valor1, valor2));
                }
                else {
                    System.out.println("Numero invalido!");
                }
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}
