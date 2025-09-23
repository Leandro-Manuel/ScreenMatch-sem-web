package br.com.alura.screenmatch.principal;
import br.com.alura.screenmatch.Model.DadosEp;
import br.com.alura.screenmatch.Model.DadosSerie;
import br.com.alura.screenmatch.Model.DadosTemp;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String APIKEY = "&apikey=a8824027";
    public void exibirMenu () throws JsonProcessingException {
        System.out.println("Digite o nome da série para busca: ");
        var nomeSerie = scanner.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + APIKEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

        ArrayList<DadosTemp> listaTemporadas = new ArrayList<>();

        for(int i = 1; i <= dados.totalTemporadas(); i++) {
            var jsonb = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&Season=" + i + APIKEY);
            DadosTemp temporada = conversor.obterDados(jsonb, DadosTemp.class);
            listaTemporadas.add(temporada);
        }
        listaTemporadas.forEach(System.out::println);

        for(int i = 0; i <= dados.totalTemporadas(); i++) {
            List<DadosEp> episodiosTemporada = listaTemporadas.get(i).episodios();
            for(int j = 0; j < episodiosTemporada.size(); j++) {
                System.out.println(episodiosTemporada.get(j).titulo());
            }
        }

        listaTemporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
    }
}
