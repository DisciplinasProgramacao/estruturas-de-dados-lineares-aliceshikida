import java.util.function.Function;
import java.util.function.Predicate;

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

    // metodo novo da aula:
    public Fila<T> filtrar(Predicate<T> condicional, int quantidade) {
        Fila<T> novaFila = new Fila<>();
        Celula<T> atual = primeiro.getProximo();
        int count = 0;

        while (atual != null && count < quantidade) {
            if (condicional.test(atual.getItem())) {
                novaFila.inserir(atual.getItem());
            }
            atual = atual.getProximo();
            count++;
        }

        return novaFila;
    }

        //novo metodo tb
      public double calcularValorMedio(Function<T, Double> extrator, int quantidade) {
        Celula<T> atual = primeiro.getProximo();
        int count = 0;
        double soma = 0.0;

        while (atual != null && count < quantidade) {
            Double valor = extrator.apply(atual.getItem());
            if (valor != null) {
                soma += valor;
            }
            atual = atual.getProximo();
            count++;
        }

        return (count == 0) ? 0.0 : soma / count;
    }
}
