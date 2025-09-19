package br.ufrn.imd;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Assertions;
import java.math.BigDecimal;

public class MatriculaTest {

    @ParameterizedTest
    @CsvSource({
            "'Aluno A', 123, 80, 6.0, 6.0, 6.0, APR",
            "'Aluno B', 124, 80, 5.0, 6.0, 4.0, REC",
            "'Aluno C', 125, 80, 1.0, 2.0, 3.0, REP",
            "'Aluno D', 126, 70, 7.0, 8.0, 6.0, REPF",
            "'Aluno E', 127, 70, 2.0, 2.0, 2.0, REPMF"
    })
    public void testarConsolidacaoParcial(String nome, int matricula, int freq,
                                          double n1, double n2, double n3,
                                          StatusAprovacao statusEsperado) {
        Discente discente = new Discente();
        discente.setNome(nome);
        discente.setMatricula(matricula);

        Docente docente = new Docente();
        docente.setNome("Prof. Teste");
        docente.setSiape(1234);

        Disciplina disciplina = new Disciplina();
        disciplina.setNome("POO");
        disciplina.setCodigo("POO123");

        Turma turma = new Turma(docente, disciplina);
        turma.matricular(discente);

        Matricula m = turma.getMatriculas().get(turma.getMatriculas().size() - 1);
        m.cadastrarFrequencia(freq);
        m.cadastrarNota1(BigDecimal.valueOf(n1));
        m.cadastrarNota2(BigDecimal.valueOf(n2));
        m.cadastrarNota3(BigDecimal.valueOf(n3));

        m.consolidarParcialmente();

        Assertions.assertEquals(statusEsperado, m.getStatus());
    }
}
