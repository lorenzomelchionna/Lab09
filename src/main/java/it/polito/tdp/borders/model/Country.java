package it.polito.tdp.borders.model;

public class Country {
	
	private int codice;
	private String abbreviazione;
	private String nomeStato;

	public Country(int codice, String abbreviazione, String nomeStato) {
		super();
		this.codice = codice;
		this.abbreviazione = abbreviazione;
		this.nomeStato = nomeStato;
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public String getAbbreviazione() {
		return abbreviazione;
	}

	public void setAbbreviazione(String abbreviazione) {
		this.abbreviazione = abbreviazione;
	}

	public String getNomeStato() {
		return nomeStato;
	}

	public void setNomeStato(String nomeStato) {
		this.nomeStato = nomeStato;
	}
	
	@Override
	public String toString() {
		return nomeStato;
	}
	
}
