
package uniandes.isis2304.aforocc.negocio;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;


public class Visitante implements VOVisitante
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO del bebedor
	 */
	private long id;	
	
	/**
	 * El nombre del bebedor
	 */
	private String nombre;
	
	private String tipo;
	
	private int numTelefono;
	
	private String correo;
	
	private String nomContacto;
	
	private int numContacto;
	

	private List<Object []> visitasRealizadas;


	
	public Visitante() 
	{
		this.id = 0;
		this.nombre = "";
		this.tipo="";
		this.numTelefono = 0;
		this.correo ="";
		this.nomContacto = "";
		this.numContacto = 0;
		
		visitasRealizadas = new LinkedList<Object []> ();
	
	}

	
	public Visitante(long id, String nombre, String tipo,int numTelefono, String correo, String nomContacto, int numContacto) 
	{
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.numTelefono = numTelefono;
		this.correo = correo;
		this.nomContacto = nomContacto;
		this.numContacto = numContacto;
		
		// Estos valores no se conocen en el momento de la construcción del bebedor
		visitasRealizadas = new LinkedList<Object []> ();
		
	}

	/**
	 * @return El id del bebedor
	 */
	public long getId() 
	{
		return id;
	}

	/**
	 * @param id - El nuevo id del bebedor
	 */
	public void setId(long id) 
	{
		this.id = id;
	}

	/**
	 * @return El nombre del bebedor
	 */
	public String getNombre() 
	{
		return nombre;
	}

	/**
	 * @param nombre - El nuevo nombre del bebedor
	 */
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public List<Object []> getVisitasRealizadas() 
	{
		return visitasRealizadas;
	}

	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public int getNumTelefono() {
		return numTelefono;
	}


	public void setNumTelefono(int numTelefono) {
		this.numTelefono = numTelefono;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public String getNomContacto() {
		return nomContacto;
	}


	public void setNomContacto(String nomContacto) {
		this.nomContacto = nomContacto;
	}


	public int getNumContacto() {
		return numContacto;
	}


	public void setNumContacto(int numContacto) {
		this.numContacto = numContacto;
	}

	/**
	 * @param visitasRealizadas - La nueva lista de visitas del bebedor
	 */
	public void setVisitasRealizadas (List<Object []> visitasRealizadas) 
	{
		this.visitasRealizadas = visitasRealizadas;
	}


	@Override
	public String toString() 
	{
		return "Visitante [id=" + id + ", nombre=" + nombre + ", tipo="+tipo+", numTelefono" + numTelefono +", correo"+correo+", nomContacto"+nomContacto + ", numContacto"+ numContacto + "]";
	}

	/**
	 * @return Una cadena de caracteres con la información COMPLEtA del bebedor.
	 * Además de la información básica, contiene las visitas realizadas (una por línea) y 
	 * las bebidas que le gustan al bebedor (una por línea)
	 */
	public String toStringCompleto () 
	{
		String resp =  this.toString();
		resp += "\n --- Visitas realizadas\n";
		int i = 1;
		for (Object [] visita : visitasRealizadas)
		{
			Local bar = (Local) visita [0];
			Timestamp fecha = (Timestamp) visita [1];
			String horario = (String) visita [2];
			resp += i++ + ". " + "[" +bar.toString() + ", fecha= " + fecha + ", horario= " + horario + "]\n";
		}
		return resp;
	}

}
