package it.polito.tdp.dizionario.model;

public class Lettera {

	private char carattere;
	private int pos;
	
	public Lettera(char carattere, int pos) {
		
		this.carattere = carattere;
		this.pos = pos;
	}

	/**
	 * @return the carattere
	 */
	public char getCarattere() {
		return carattere;
	}

	/**
	 * @param carattere the carattere to set
	 */
	public void setCarattere(char carattere) {
		this.carattere = carattere;
	}

	/**
	 * @return the pos
	 */
	public int getPos() {
		return pos;
	}

	/**
	 * @param pos the pos to set
	 */
	public void setPos(int pos) {
		this.pos = pos;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + carattere;
		result = prime * result + pos;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lettera other = (Lettera) obj;
		if (carattere != other.carattere)
			return false;
		if (pos != other.pos)
			return false;
		return true;
	}

	
	
	
	
}
