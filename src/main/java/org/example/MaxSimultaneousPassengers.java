package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaxSimultaneousPassengers {
    /**
     * Solução para o problema: dado um conjunto de intervalos [E[i], S[i]],
     * encontrar o número máximo de intervalos que se intersectam num ponto
     * (com interseção de comprimento positivo).
     */
    /**
     * Representa um evento na linha do tempo.
     * @param time tempo do evento
     * @param delta +1 para início de intervalo, -1 para fim
     */
    private record Event(long time, int delta) {}

    /**
     * Retorna o número máximo de intervalos sobrepostos.
     *
     * @param starts array com os tempos de início (E)
     * @param ends   array com os tempos de fim (S)
     * @return máximo de sobreposições
     * @throws IllegalArgumentException se os arrays forem nulos ou de tamanhos diferentes
     */
    public static int maxOverlap(int[] starts, int[] ends) {
        if (starts == null || ends == null || starts.length != ends.length) {
            throw new IllegalArgumentException("Arrays de início e fim devem ser não nulos e de mesmo tamanho.");
        }

        int n = starts.length;
        List<Event> events = new ArrayList<>(2 * n);

        // Cria os eventos: início (+1) e fim (-1)
        for (int i = 0; i < n; i++) {
            events.add(new Event(starts[i], +1));
            events.add(new Event(ends[i], -1));
        }

        // Ordena: primeiro por tempo; em caso de empate, eventos de fim (-1) vêm antes de início (+1)
        // Isso garante que quando um intervalo termina exatamente no mesmo tempo que outro começa,
        // eles não são contados como sobrepostos (interseção de ponto único é ignorada).
        events.sort(Comparator
                .comparingLong(Event::time)
                .thenComparingInt(Event::delta));

        int current = 0;
        int maxOverlap = 0;

        for (Event e : events) {
            current += e.delta();      // atualiza contagem de ativos
            if (current > maxOverlap)
                maxOverlap = current;  // registra o máximo
        }
        return maxOverlap;
    }
}
