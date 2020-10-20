
package uniandes.isis2304.aforocc.negocio;


public class Lector implements VOLector
{
	
	private long id;
	private String ubicacion;
	

	public Lector() 
	{
		this.id = 0;
		this.ubicacion = "";
	}

	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getUbicacion() {
		return ubicacion;
	}


	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}


	public Lector(long id, String nombre) 
	{
		this.id = id;
		this.ubicacion = nombre;
	}


	@Override
	public String toString() 
	{
		return "lector [id=" + id + ", ubicacion=" + ubicacion + "]";
	}

}
