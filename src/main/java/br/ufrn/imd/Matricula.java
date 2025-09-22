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
		if (nota1 == null || nota1.compareTo(BigDecimal.ZERO) < 0 || nota1.compareTo(BigDecimal.TEN) > 0) {
			throw new IllegalArgumentException("Nota deve estar entre 0 e 10");
		}
		this.nota1 = nota1;
	}

	public BigDecimal getNota2() {
		return nota2;
	}

	public void cadastrarNota2(BigDecimal nota2) {
		if (nota2 == null || nota2.compareTo(BigDecimal.ZERO) < 0 || nota2.compareTo(BigDecimal.TEN) > 0) {
			throw new IllegalArgumentException("Nota deve estar entre 0 e 10");
		}
		this.nota2 = nota2;
	}

	public BigDecimal getNota3() {
		return nota3;
	}

	public void cadastrarNota3(BigDecimal nota3) {
		if (nota3 == null || nota3.compareTo(BigDecimal.ZERO) < 0 || nota3.compareTo(BigDecimal.TEN) > 0) {
			throw new IllegalArgumentException("Nota deve estar entre 0 e 10");
		}
		this.nota3 = nota3;
	}

	public Integer getFrequencia() {
		return frequencia;
	}

	public void cadastrarFrequencia(Integer frequencia) {
		if (frequencia == null || frequencia < 0 || frequencia > 100) {
			throw new IllegalArgumentException("Frequência deve estar entre 0 e 100");
		}
		this.frequencia = frequencia;
	}

	public Discente getDiscente() {
		return discente;
	}

	public Turma getTurma() {
		return turma;
	}

    public void consolidarParcialmente() {
        if (nota1 == null || nota2 == null || nota3 == null) {
            return;
        }
        
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
            this.setStatus(StatusAprovacao.REC);
        }
    }


    public BigDecimal calcularMediaParcial() {
        if (nota1 == null || nota2 == null || nota3 == null) {
            return BigDecimal.ZERO;
        }
        return nota1.add(nota2).add(nota3).divide(BigDecimal.valueOf(3), 2, BigDecimal.ROUND_HALF_UP);
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