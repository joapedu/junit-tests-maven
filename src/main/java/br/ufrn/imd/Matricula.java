package br.ufrn.imd;

import java.math.BigDecimal;

public class Matricula {
	private final Discente discente;
	
	private final Turma turma;
	
	private BigDecimal nota1;

	private BigDecimal nota2;

	private BigDecimal nota3;

	private Integer frequencia;
	
	private StatusAprovacao status;

	Matricula(Discente discente, Turma turma) {
		this.discente = discente;
		this.turma = turma;
	}

	public BigDecimal getNota1() {
		return nota1;
	}

	public void cadastrarNota1(BigDecimal nota1) {
		this.nota1 = nota1;
	}

	public BigDecimal getNota2() {
		return nota2;
	}

	public void cadastrarNota2(BigDecimal nota2) {
		this.nota2 = nota2;
	}

	public BigDecimal getNota3() {
		return nota3;
	}

	public void cadastrarNota3(BigDecimal nota3) {
		this.nota3 = nota3;
	}

	public Integer getFrequencia() {
		return frequencia;
	}

	public void cadastrarFrequencia(Integer frequencia) {
		this.frequencia = frequencia;
	}

	public Discente getDiscente() {
		return discente;
	}

	public Turma getTurma() {
		return turma;
	}

    public void consolidarParcialmente() {
        BigDecimal mediaParcial = calcularMediaParcial();

        boolean frequenciaOK = this.frequencia != null && this.frequencia >= 75;
        boolean todasNotasAcimaDeQuatro = nota1.compareTo(BigDecimal.valueOf(4.0)) >= 0 &&
                nota2.compareTo(BigDecimal.valueOf(4.0)) >= 0 &&
                nota3.compareTo(BigDecimal.valueOf(4.0)) >= 0;

        if (!frequenciaOK && mediaParcial.compareTo(BigDecimal.valueOf(3.0)) < 0) {
            this.setStatus(StatusAprovacao.REPMF);
        } else if (!frequenciaOK) {
            this.setStatus(StatusAprovacao.REPF);
        } else if (mediaParcial.compareTo(BigDecimal.valueOf(3.0)) < 0) {
            this.setStatus(StatusAprovacao.REP);
        } else if (mediaParcial.compareTo(BigDecimal.valueOf(6.0)) >= 0 && todasNotasAcimaDeQuatro) {
            this.setStatus(StatusAprovacao.APR);
        } else {
            if (notaReposicao == null) {
                this.setStatus(StatusAprovacao.REC);
            } else {
                substituirMenorNota();

                BigDecimal mediaFinal = nota1.add(nota2).add(nota3).divide(BigDecimal.valueOf(3), 2, BigDecimal.ROUND_HALF_UP);

                boolean todasNotasPosReposicaoAcimaDeQuatro = nota1.compareTo(BigDecimal.valueOf(4.0)) >= 0 &&
                        nota2.compareTo(BigDecimal.valueOf(4.0)) >= 0 &&
                        nota3.compareTo(BigDecimal.valueOf(4.0)) >= 0;

                if (mediaFinal.compareTo(BigDecimal.valueOf(6.0)) >= 0 && todasNotasPosReposicaoAcimaDeQuatro) {
                    this.setStatus(StatusAprovacao.APR);
                } else {
                    this.setStatus(StatusAprovacao.REP);
                }
            }
        }
    }


    private BigDecimal calcularMediaParcial() {
        final BigDecimal mediaParcial = nota1.add(nota2).add(nota3).divide(BigDecimal.valueOf(3), 2, BigDecimal.ROUND_HALF_UP);
        final BigDecimal notaMinima = BigDecimal.valueOf(4.0);
        final BigDecimal mediaAprovacao = BigDecimal.valueOf(6.0);
        final BigDecimal mediaReposicao = BigDecimal.valueOf(3.0);

        boolean frequenciaOK = this.frequencia != null && this.frequencia >= 75;
        boolean todasNotasAcimaDeQuatro = nota1.compareTo(notaMinima) >= 0 &&
                nota2.compareTo(notaMinima) >= 0 &&
                nota3.compareTo(notaMinima) >= 0;

        if (!frequenciaOK && mediaParcial.compareTo(mediaReposicao) < 0) {
            this.setStatus(StatusAprovacao.REPMF);
        } else if (!frequenciaOK) {
            this.setStatus(StatusAprovacao.REPF);
        } else if (mediaParcial.compareTo(mediaReposicao) < 0) {
            this.setStatus(StatusAprovacao.REP);
        } else if (mediaParcial.compareTo(mediaAprovacao) >= 0 && todasNotasAcimaDeQuatro) {
            this.setStatus(StatusAprovacao.APR);
        } else {
            this.setStatus(StatusAprovacao.REC);
        }
        return mediaParcial;
    }


    public StatusAprovacao getStatus() {
		return status;
	}

	private void setStatus(StatusAprovacao status) {
		this.status = status;
	}

    private BigDecimal notaReposicao;

    public void cadastrarNotaReposicao(BigDecimal notaReposicao) {
        this.notaReposicao = notaReposicao;
    }

    public BigDecimal getNotaReposicao() {
        return notaReposicao;
    }

    private void substituirMenorNota() {
        if (notaReposicao == null) return;

        if (nota1.compareTo(nota2) <= 0 && nota1.compareTo(nota3) <= 0) {
            nota1 = notaReposicao;
        } else if (nota2.compareTo(nota1) <= 0 && nota2.compareTo(nota3) <= 0) {
            nota2 = notaReposicao;
        } else {
            nota3 = notaReposicao;
        }
    }
}