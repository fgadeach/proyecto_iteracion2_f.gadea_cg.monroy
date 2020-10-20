
package uniandes.isis2304.aforocc.negocio;


public interface VOLocal 
{

	public long getId();
	public String getTipo();
	public String getNombre() ;
	public int getAforoMax() ;
	public String getUbicacion();
	public long getIdCC();

	@Override
	public String toString();

}
