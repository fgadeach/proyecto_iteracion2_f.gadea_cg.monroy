
package uniandes.isis2304.aforocc.negocio;


public class Carnet implements VOCarnet
{
	
	private long id;
	
	
	private long idVisitante;

	
	public Carnet() 
	
    {
    	this.id = 0;
		this.idVisitante = 0;
	
	}
	
    public Carnet(long id, long nombre) 
    {
    	this.id = id;
		this.idVisitante = nombre;
		
	}

   
	public long getId() 
	{
		return id;
	}
	
	/**
	 * @param id - El nuevo id del bar
	 */
	public void setId(long id) 
	{
		this.id = id;
	}
	

	
	public long getIdVisitante() {
		return idVisitante;
	}


	public void setIdVisitante(long idVisitante) {
		this.idVisitante = idVisitante;
	}


	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString() 
	{
		return "Bar [id=" + id + ", idVisitante=" + idVisitante + "]";
	}
	

}
