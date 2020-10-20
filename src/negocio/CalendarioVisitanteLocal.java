
package uniandes.isis2304.aforocc.negocio;


public class CalendarioVisitanteLocal implements VOCalendarioVisitanteLocal
{
	
	private long idCalendario;
	
	private long idLocal;
	
	private long idVisitante;
	
	

	
	public CalendarioVisitanteLocal() 
	
    {
    	this.idCalendario = 0;
		this.idLocal = 0;
		this.idVisitante=0;
	
	}

	
    public CalendarioVisitanteLocal(long id, long idL, long idV) 
    {
    	this.idCalendario = id;
		this.idLocal = idL;
		this.idVisitante = idV;
		
	}


	public long getIdCalendario() {
		return idCalendario;
	}


	public void setIdCalendario(long idCalendario) {
		this.idCalendario = idCalendario;
	}


	public long getIdLocal() {
		return idLocal;
	}


	public void setIdLocal(long idLocal) {
		this.idLocal = idLocal;
	}


	public long getIdVisitante() {
		return idVisitante;
	}


	public void setIdVisitante(long idVisitante) {
		this.idVisitante = idVisitante;
	}


	@Override
	
	public String toString() 
	{
		return "CalendarioVisitanteLocal [idCalendario=" + idCalendario + ", idLocal=" + idLocal + ", idVisitante=" + idVisitante +"]";
	}
	

}
