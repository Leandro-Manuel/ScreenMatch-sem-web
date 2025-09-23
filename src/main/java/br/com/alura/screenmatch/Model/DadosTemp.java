package br.com.alura.screenmatch.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosTemp(@JsonAlias("Season") Integer numero,
                        @JsonAlias("Episodes") List<DadosEp> episodios) {
}
