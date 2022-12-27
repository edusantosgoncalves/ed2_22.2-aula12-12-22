package BST;

public class Main {
	public static void main(String args[]){
		ArvBinBuscaSChave<Integer> arvoreBinaria = new ArvBinBuscaSChave<>();
		
		arvoreBinaria.insere(10);
		arvoreBinaria.insere(20);
		arvoreBinaria.insere(5);
		arvoreBinaria.insere(2);
		arvoreBinaria.insere(4);
		arvoreBinaria.insere(18);
		arvoreBinaria.insere(22);
		
		//Imprimindo a árvore em ordem
		arvoreBinaria.mostra();
		
		//Pulando uma linha
		System.out.println("\n");

		//Retornando o menor nó da árvore - O (log n)
		System.out.println("A menor valor da árvore é: " + arvoreBinaria.min());

		//Verificando se a árvore contem um valor x - O (log n)
		if(arvoreBinaria.contem(18))
			System.out.println("O nó de valor 18 está contido na árvore.");
		else
			System.out.println("O nó de valor 18 não está contido na árvore.");

		//Verificando se a árvore contem um valor x - O(log n)
		if(arvoreBinaria.contem(23))
			System.out.println("O nó de valor 23 está contido na árvore.");
		else
			System.out.println("O nó de valor 23 não está contido na árvore.");

		//Removendo o nó 20 da árvore - O(log n)
		arvoreBinaria.delete(20);

		//Reimprimindo a árvore após a remoção do nó 20 - O(n)
		System.out.println("\n");
		System.out.println("Impressão da árvore após remover o nó de valor 20:");
		arvoreBinaria.mostra();
	}
}
