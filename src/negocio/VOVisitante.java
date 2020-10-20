
package uniandes.isis2304.aforocc.negocio;

import java.util.List;


public interface VOVisitante 
{
	public long getId();

	public String getNombre();
	
	public String getTipo();
	
	public int getNumTelefono() ;
	
	public String getCorreo();
	
	public String getNomContacto();
	
	public int getNumContacto();


	public List<Object []> getVisitasRealizadas();


	
	@Override
	public String toString();

	
	public String toStringCompleto ();

}
