package AVL;

import java.io.*;
import java.util.*;

public class AVLTree {
    private int height (Node N) {
        if (N == null)
            return 0;
        return N.getHeight();
    }

    public Node insert(Node node, int value) {
        /* 1. Inserção de valor na árvore */
        if (node == null) {
            return(new Node(value));
        }

        if (value < node.getValue())
            node.setLeft(insert(node.getLeft(), value));
        else
            node.setRight(insert(node.getRight(), value));

        /* 2. Atualizar a altura do nó */
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);

        /* 3. Verificar fator de equilíbrio */
        int balance = getBalance(node);

        // Se estiver desequilibrado, existem 4 casos possíveis

        // Caso Esquerda Esquerda
        if (balance > 1 && value < node.getLeft().getValue())
            return rightRotate(node);

        // Caso Direita Direita
        if (balance < -1 && value > node.getRight().getValue())
            return leftRotate(node);

        // Caso Esquerda Direita
        if (balance > 1 && value > node.getLeft().getValue())
        {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }

        // Caso Direita Esquerda
        if (balance < -1 && value < node.getRight().getValue())
        {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        return node;
    }

    private Node rightRotate(Node y) {
        Node x = y.getLeft();
        Node T2 = x.getRight();

        // Realizar rotação
        x.setRight(y);
        y.setLeft(T2);

        // Atualizar altura
        y.setHeight(Math.max(height(y.getLeft()), height(y.getRight()))+1);
        x.setHeight(Math.max(height(x.getLeft()), height(x.getRight()))+1);

        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.getRight();
        Node T2 = y.getLeft();

        // Realizar rotação
        y.setLeft(x);
        x.setRight(T2);

        // Atualizar altura
        x.setHeight(Math.max(height(x.getLeft()), height(x.getRight()))+1);
        y.setHeight(Math.max(height(y.getLeft()), height(y.getRight()))+1);

        return y;
    }

    // Pega fator de equilíbrio de um nó N
    private int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.getLeft()) - height(N.getRight());
    }

    public void preOrder(Node root) {
        if (root != null) {
            preOrder(root.getLeft());
            System.out.printf("%d ", root.getValue());
            preOrder(root.getRight());
        }
    }

    private Node minValueNode(Node node) {
        Node current = node;
        /* Achar folha de menor valor */
        while (current.getLeft() != null)
            current = current.getLeft();
        return current;
    }

    public int lowestNode(Node node) {
        Node current = node;
        /* Achar folha de menor valor */
        while (current.getLeft() != null)
            current = current.getLeft();
        return current.getValue();
    }

    public int highestNode(Node node) {
        Node current = node;
        /* Achar folha de menor valor */
        while (current.getRight() != null)
            current = current.getRight();
        return current.getValue();
    }

    public Node deleteNode(Node root, int value) {
        // Deletar nó

        if (root == null)
            return root;

        // Se o valor a ser deletado é menor que o valor da raiz,
        // então ele fica na subarvore da esquerda
        if ( value < root.getValue() )
            root.setLeft(deleteNode(root.getLeft(), value));

            // Se o valor a ser deletado é maior que o valor da raiz,
            // então ele fica na subarvore da direita
        else if( value > root.getValue() )
            root.setRight(deleteNode(root.getRight(), value));

            // se o valor é o mesmo da raiz, então esse é o que deve ser deletado

        else {
            // nó com apenas um ou nenhum filho
            if( (root.getLeft() == null) || (root.getRight() == null) ) {

                Node temp;
                if (root.getLeft() != null)
                    temp = root.getLeft();
                else
                    temp = root.getRight();

                // Caso Sem Filho
                if(temp == null) {
                    temp = root;
                    root = null;
                }
                else // Caso Um Filho
                    root = temp;

                temp = null;
            }
            else {
                // No com dois filhos: Pegar o sucessor da ordem (menor da subarvore da direita)
                Node temp = minValueNode(root.getRight());

                // Copiar o valor do sucessor nesse nó
                root.setValue(temp.getValue());

                // Deletar o sucessor
                root.setRight(deleteNode(root.getRight(), temp.getValue()));
            }
        }

        // Se a árvore tiver apenas um nó, então o retorna
        if (root == null)
            return root;

        // Atualizar altura
        root.setHeight(Math.max(height(root.getLeft()), height(root.getRight())) + 1);

        // Verificar fator de equilíbrio
        int balance = getBalance(root);

        // Se estiver desequilibrado, existem 4 casos possíveis

        // Caso Esquerda Esquerda
        if (balance > 1 && getBalance(root.getLeft()) >= 0)
            return rightRotate(root);

        // Caso Esquerda Direita
        if (balance > 1 && getBalance(root.getLeft()) < 0) {
            root.setLeft(leftRotate(root.getLeft()));
            return rightRotate(root);
        }

        // Caso Direita Direita
        if (balance < -1 && getBalance(root.getRight()) <= 0)
            return leftRotate(root);

        // Caso Direita Esquerda
        if (balance < -1 && getBalance(root.getRight()) > 0) {
            root.setRight(rightRotate(root.getRight()));
            return leftRotate(root);
        }

        return root;
    }

    public Node searchNode(Node root, int value) {
        Node current = root;
        while (current != null) {
            if (current.getValue() == value) {
                break;
            }

            if (current.getValue() < value)
                current = current.getRight();

            else if (current.getValue() > value)
                current = current.getLeft();

            //current = current.key < key ? current.right : current.left;
        }
        return current;
    }

    public int soma(Node root, int valor1, int valor2){
        Node node1 = searchNode(root, valor1);
        Node node2 = searchNode(root, valor2);
        return node1.getValue() + node2.getValue();
    }

    public void print(Node root) {

        if(root == null) {
            System.out.println("(XXXXXX)");
            return;
        }

        int height = root.getHeight(),
                width = (int)Math.pow(2, height-1);

        // Preparar variáveis
        List<Node> current = new ArrayList<Node>(1),
                next = new ArrayList<Node>(2);
        current.add(root);

        final int maxHalfLength = 4;
        int elements = 1;

        StringBuilder sb = new StringBuilder(maxHalfLength*width);
        for(int i = 0; i < maxHalfLength*width; i++)
            sb.append(' ');
        String textBuffer;

        for(int i = 0; i < height; i++) {

            sb.setLength(maxHalfLength * ((int)Math.pow(2, height-1-i) - 1));

            textBuffer = sb.toString();

            // Impressão dos elementos do nó
            for(Node n : current) {

                System.out.print(textBuffer);

                if(n == null) {

                    System.out.print("        ");
                    next.add(null);
                    next.add(null);

                } else {

                    System.out.printf("(%6d)", n.getValue());
                    next.add(n.getLeft());
                    next.add(n.getRight());

                }

                System.out.print(textBuffer);

            }

            System.out.println();

            if(i < height - 1) {

                for(Node n : current) {

                    System.out.print(textBuffer);

                    if(n == null)
                        System.out.print("        ");
                    else
                        System.out.printf("%s      %s",
                                n.getLeft() == null ? " " : "/", n.getRight() == null ? " " : "\\");

                    System.out.print(textBuffer);

                }

                System.out.println();

            }

            // Renovar indicadores
            elements *= 2;
            current = next;
            next = new ArrayList<Node>(elements);

        }

    }
}
