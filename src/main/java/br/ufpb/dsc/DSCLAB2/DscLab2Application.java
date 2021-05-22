package br.ufpb.dsc.DSCLAB2;

import br.ufpb.dsc.DSCLAB2.entidades.Disciplina;
import br.ufpb.dsc.DSCLAB2.servicos.DisciplinaServico;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class DscLab2Application {

    public static void main(String[] args) {
        SpringApplication.run(DscLab2Application.class, args);
    }

    @Bean
    //sempre que o sistema for executado o commandLineRunner será executado adicionando as disciplinasSi.JSON no banco
    CommandLineRunner runner(DisciplinaServico disciplinaServico) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Disciplina>> typeReference = new TypeReference<List<Disciplina>>(){};
            InputStream input = TypeReference.class.getResourceAsStream("/JSON-disciplinas/disciplinasSI.json");
            try {
                List<Disciplina> disciplinas = mapper.readValue(input, typeReference);
                //antes de salvar verifica se as disciplinas ja nao estao cadastradas no sistema
                //verifica somente se os objetos no json nao tiverem o atributo id
                // verificacao pode ser feita buscando no banco de dados se aquela disciplina ja existe
                disciplinaServico.saveAllDisciplinas(disciplinas);
                System.out.println("Disciplinas Salvas no BD! ");
            } catch (IOException e) {
                System.out.println("Não foi possível salvar as disciplinas: "+ e.getMessage());
            }
        };
    }
}
