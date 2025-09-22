package br.ufrn.imd;

import java.math.BigDecimal;

public class App {
    public static void main(String[] args) {
        Discente aluno1 = new Discente();
        aluno1.setNome("João Eduardo");
        aluno1.setMatricula(12345);

        Docente professor = new Docente();
        professor.setNome("EIJI ADACHI MEDEIROS BARBOSA");
        professor.setSiape(98765);

        Disciplina disciplina = new Disciplina();
        disciplina.setNome("TESTE DE SOFTWARE I");
        disciplina.setCodigo("DIM0507");

        Turma turma = new Turma(professor, disciplina);
        turma.matricular(aluno1);

        Matricula matricula = turma.getMatriculas().get(0);
        matricula.cadastrarFrequencia(80);
        matricula.cadastrarNota1(BigDecimal.valueOf(7.0));
        matricula.cadastrarNota2(BigDecimal.valueOf(8.0));
        matricula.cadastrarNota3(BigDecimal.valueOf(6.0));

        matricula.consolidarParcialmente();

        System.out.println("Aluno: " + matricula.getDiscente().getNome());
        System.out.println("Média Parcial: " + matricula.calcularMediaParcial());
        System.out.println("Frequência: " + matricula.getFrequencia() + "%");
        System.out.println("Status: " + matricula.getStatus());
    }
}
