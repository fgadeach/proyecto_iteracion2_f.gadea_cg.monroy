
package uniandes.isis2304.aforocc.negocio;


public class CalendarioVisitanteCC implements VOCalendarioVisitanteCC
{
	
	private long idCalendario;
	
	private long idCC;
	
	private long idVisitante;
	
	

	
	public CalendarioVisitanteCC() 
	
    {
    	this.idCalendario = 0;
		this.idCC = 0;
		this.idVisitante=0;
	
	}

	
    public CalendarioVisitanteCC(long id, long idL, long idV) 
    {
    	this.idCalendario = id;
		this.idCC = idL;
		this.idVisitante = idV;
		
	}


	public long getIdCalendario() {
		return idCalendario;
	}


	public void setIdCalendario(long idCalendario) {
		this.idCalendario = idCalendario;
	}


	public long getIdCC() {
		return idCC;
	}


	public void setIdCC(long idLocal) {
		this.idCC= idLocal;
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
		return "CalendarioVisitanteCC [idCalendario=" + idCalendario + ", idCC=" + idCC + ", idVisitante=" + idVisitante +"]";
	}
	

}
