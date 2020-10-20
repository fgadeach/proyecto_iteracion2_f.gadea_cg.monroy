
package uniandes.isis2304.aforocc.negocio;


public class HoraLocal implements VOHoraLocal
{
	
	private long idHorario;
	
	private long idLocal;

	
	public HoraLocal() 
	
    {
    	this.idHorario = 0;
		this.idLocal = 0;
	
	}

	
    public HoraLocal(long id, long idL) 
    {
    	this.idHorario = id;
		this.idLocal = idL;
		
	}

   
	
	
	public long getIdHorario() {
		return idHorario;
	}


	public void setIdHorario(long idHorario) {
		this.idHorario = idHorario;
	}


	public long getIdLocal() {
		return idLocal;
	}


	public void setIdLocal(long idLocal) {
		this.idLocal = idLocal;
	}


	@Override
	
	public String toString() 
	{
		return "HoraLocal [idHorario=" + idHorario + ", idLocal=" + idLocal + "]";
	}
	

}
