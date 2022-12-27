package BST;

import java.util.NoSuchElementException;

public class ArvBinBuscaSChave<Valor extends Comparable> {
	private No raiz; // Nó raiz da árvore

	private class No {
		private Valor valor; // Informação armazenada.
		private No esq, dir; // Referencias para subarvores esquerda e direita

		/* Cria um no com valor e valor fornecidos + esq = dir = null. */
		public No(Valor valor) {
			this.valor = valor;
			this.esq = null;
			this.dir = null;
		}
		
		/* Cria um no com valor e valor fornecidos. As subarvores esq e dir sao
		 * passadas por parametro. */
		public No(Valor valor, No esq, No dir) {
			this.valor = valor;
			this.esq = esq;
			this.dir = dir;
		}
	}
	
	/**
	 *  Cria��o de uma �rvore vazia. 
	 */
	public ArvBinBuscaSChave()
	{
		raiz = null;
	}
	
	/** 
	 * Verifica se a �rvore est� vazia.
	 * 
	 * @return se a �rvore est� vazia ou possui algum elemento
	 */
	public boolean vazia()
	{
		return (raiz == null);
	}
	
	/**
	 * Apresenta o conte�do da �rvore em ordem sim�trica.
	 */
	public void mostra()
	{
		mostra(raiz);
	}
	
	private void mostra(No x)
	{
		/* Caso base (criterio de parada). */
		if(x == null)
			return;
		
		System.out.print("[");
		
		/* Chamada recursiva para a esquerda. */
		mostra(x.esq);
		
		/* Imprime o valor do nó corrente. */
		System.out.print("<" + x.valor + ">");
		
		/* Chamada recursiva para a direita. */
		mostra(x.dir);
		
		System.out.print("]");
	}

    /**
     * Busca um valor na BST
     */
    public boolean contem(Valor valor) {
        if (valor == null) 
        	throw new IllegalArgumentException("A valor fornecida e nula!");
        
        return get(valor) != null;
    }
	
    /**
     * Retorna o valor associado ao valor fornecido.
     */
    public Valor get(Valor valor)
    {
        return get(raiz, valor);
    }

    private Valor get(No x, Valor valor)
    {
        if(valor == null) 
        	throw new IllegalArgumentException("O valor fornecido e nulo!");
        
        /* A valor nao se encontra na arvore. */
        if(x == null)
        	return null;
        
        int cmp = valor.compareTo(x.valor);
        
        if(cmp < 0) /* Deve-se ir para a esquerda. */
        	return get(x.esq, valor);
        else if(cmp > 0) /* Deve-se ir para a direita. */
        	return get(x.dir, valor);
        else /* Chave encontrada. */
        	return x.valor;
    }
    
    /**
     * Insere na BST o valor fornecido.
     * Caso a árvore ja possua a valor especificado, o valor antigo é sobrescrito com o novo valor
     * fornecido. Remove o no de valor igual ao valor fornecido caso o valor especificado seja nulo.
     */
    public void insere(Valor valor)
    {
        if(valor == null) 
        	throw new IllegalArgumentException("O valor fornecido e nulo!");
        
        if(valor == null) {
            delete(valor);
            return;
        }
        
        raiz = insere(raiz, valor);
    }

    private No insere(No x, Valor valor)
    {
    	/* Caso base: encontrou a posicaoo de insercao. */
        if (x == null)
        	return new No(valor);
        
        int cmp = valor.compareTo(x.valor);
        
        if(cmp < 0) /* Deve-se ir para a esquerda. */
        	x.esq = insere(x.esq, valor);
        else if(cmp > 0) /* Deve-se ir para a direita. */
        	x.dir = insere(x.dir, valor);
        else /* Caso tenha encontrado no de mesma valor. */
        	x.valor = valor;
        
        return x;
    }

    /**
     * Remove o nó cujo valor seja igual ao valor fornecido
     */
    public void delete(Valor valor)
    {    	
    	raiz = delete(raiz, valor);    	
    }
    
    /* Remove o nó com o valor "val" da "árvore" a partir do nó para o qual está sendo chamada a função.
    Obs: "ref_no" é o ponteiro que referencia o nó para o qual está sendo chamada a função,
     o qual pode ter que ser modificado. Retorna false se o valor não pertencer a "árvore".
    */
    private No delete(No x, Valor valor)
    {
    	if (x == null)
    		return null;

    	int cmp = valor.compareTo(x.valor);
    	
    	if(cmp < 0)
    		x.esq = delete(x.esq, valor);
    	else if(cmp > 0)
    		x.dir = delete(x.dir, valor);
    	else
    	{ 
    		if(x.dir == null)
    			return x.esq;
    		if(x.esq  == null)
    			return x.dir;
    		
    		No t = x;

    		/* Pega o menor da subárvore direita (mais a esquerda). */
    		x = min(t.dir);

    		/* Remove o menor. */
    		x.dir = deleteMin(t.dir);

    		/* A subárvore esquerda se mantém a mesma. */
    		x.esq = t.esq;
    	}

    	return x;
    }

    /**
     * Remove o nó de menor valor da árvore.
     *
     */
    public void deleteMin()
    {
        if(vazia())
            throw new NoSuchElementException("A árvore está vazia!");

        raiz = deleteMin(raiz);
    }

    /* Método recursivo que anda sempre para a esquerda, procurando o nó
     * de menor valor a ser removido. */
    private No deleteMin(No x)
    {
        /* Caso não haja filho a esquerda, o nó corrente possui o menor valor. */
        if(x.esq == null)
            return x.dir;

        x.esq = deleteMin(x.esq);

        return x;
    }

    /**
     * Retorna o menor valor da árvore.
     */
    public Valor min()
    {
        if(vazia())
            throw new NoSuchElementException("Arvore vazia!");

        return min(raiz).valor;
    }

    private No min(ArvBinBuscaSChave.No x) {
        if (x.esq == null)
            return x;
        else
            return min(x.esq);
    }

}