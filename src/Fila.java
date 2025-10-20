public class Fila<T> {
    private Celula<T> primeiro, ultimo;

    public Fila() {
        primeiro = new Celula<>(); // célula sentinela
        ultimo = primeiro;
    }

    public void inserir(T x) {
        ultimo.setProximo(new Celula<>(x));
        ultimo = ultimo.getProximo();
    }

    public T remover() throws Exception {
        if (primeiro == ultimo) {
            throw new Exception("Erro! A fila está vazia.");
        }

        Celula<T> removida = primeiro.getProximo();
        T itemRemovido = removida.getItem();

        primeiro.setProximo(removida.getProximo());

        // Caso tenha removido o último, atualiza o ponteiro 'ultimo'
        if (removida == ultimo) {
            ultimo = primeiro;
        }

        return itemRemovido;
    }

    public boolean vazia() {
        return primeiro == ultimo;
    }

    public void mostrar() {
        System.out.print("Fila: [ ");
        for (Celula<T> i = primeiro.getProximo(); i != null; i = i.getProximo()) {
            System.out.print(i.getItem() + " ");
        }
        System.out.println("]");
    }
}
