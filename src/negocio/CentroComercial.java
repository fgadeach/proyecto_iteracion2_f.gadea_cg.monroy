
package uniandes.isis2304.aforocc.negocio;


public class CentroComercial implements VOCentroComercial
{

	private long id;
	private String nombre;
	private int aforoMax;
	
	public CentroComercial() 
	{
		this.id = 0;
		this.nombre = "";
		this.aforoMax = 0;
		
	}

	public CentroComercial(long id, String nombre, int aforo) 
	{
		this.id = id;
		this.nombre = nombre;
		this.aforoMax=aforo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAforoMax() {
		return aforoMax;
	}

	public void setAforoMax(int aforoMax) {
		this.aforoMax = aforoMax;
	}

	@Override
	public String toString() 
	{
		return "CentroComercial [id=" + id + ", nombre=" + nombre +", aforoMax=" + aforoMax + "]";
	}
	
}
