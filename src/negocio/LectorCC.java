
package uniandes.isis2304.aforocc.negocio;


public class LectorCC implements VOLectorCC
{
	
	private long idLector;
	
	private long idCC;

	
	public LectorCC() 
	
    {
    	this.idLector = 0;
		this.idCC = 0;
	
	}

	
    public LectorCC(long id, long idL) 
    {
    	this.idLector = id;
		this.idCC = idL;
		
	}

   

	public long getIdLector() {
		return idLector;
	}


	public void setIdLector(long idLector) {
		this.idLector = idLector;
	}


	public long getIdCC() {
		return idCC;
	}


	public void setIdCC(long idCC) {
		this.idCC = idCC;
	}


	@Override
	
	public String toString() 
	{
		return "LectorCC [idLector=" + idLector + ", idCC=" + idCC + "]";
	}
	

}
