
package uniandes.isis2304.aforocc.negocio;


public class LectorLocal implements VOLectorLocal
{
	
	private long idLector;
	
	private long idLocal;

	
	public LectorLocal() 
	
    {
    	this.idLector = 0;
		this.idLocal = 0;
	
	}

	
    public LectorLocal(long id, long idL) 
    {
    	this.idLector = id;
		this.idLocal = idL;
		
	}

   
	
	

	public long getIdLector() {
		return idLector;
	}


	public void setIdLector(long idLector) {
		this.idLector = idLector;
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
		return "LectorLocal [idLector=" + idLector + ", idLocal=" + idLocal + "]";
	}
	

}
