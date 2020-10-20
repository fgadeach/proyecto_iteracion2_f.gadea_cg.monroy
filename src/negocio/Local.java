
package uniandes.isis2304.aforocc.negocio;


public class Local implements VOLocal
{
	
	private long id;
	

	private String tipo;
	
	
	private String nombre;
	
	private int aforoMax;
	
	private String ubicacion;
	
	private long idCC;

	
	public Local () 
	{
		this.id = 0;
		this.tipo = "";
		this.nombre = "";
		this.aforoMax=0;
		this.ubicacion="";
		this.idCC=0;
	}

	
	public Local (long id, String tipo, String nombre, int aforo, String ubicacion, long idCC) 
	{
		this.id = id;
		this.tipo=tipo;
		this.nombre=nombre;
		this.aforoMax=aforo;
		this.ubicacion=ubicacion;
		this.idCC=idCC;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
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


	public String getUbicacion() {
		return ubicacion;
	}


	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
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
		return "Local [id=" + id + ", tipo=" + tipo + ", nombre=" + nombre +", aforoMax=" + aforoMax + ", ubicacion=" + ubicacion  +", idCC=" + idCC +"]";
	}
}
