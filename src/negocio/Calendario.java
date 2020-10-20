
package uniandes.isis2304.aforocc.negocio;


public class Calendario implements VOCalendario
{
	private long id;
	

	private int anio;
	
	
	private int mes;
	
	private int dia;
	
	private long idHorario;
	


	
	public Calendario () 
	{
		this.id = 0;
		this.anio=0;
		this.mes=0;
		this.dia=0;
		this.idHorario=0;
	}

	
	public Calendario (long id,int anio, int mes,int dia, long idHorario) 
	{
		this.id = id;
		this.anio=anio;
		this.mes = mes;
		this.dia=dia;
		this.idHorario=idHorario;
	}



	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public int getAnio() {
		return anio;
	}


	public void setAnio(int anio) {
		this.anio = anio;
	}


	public int getMes() {
		return mes;
	}


	public void setMes(int mes) {
		this.mes = mes;
	}


	public int getDia() {
		return dia;
	}


	public void setDia(int dia) {
		this.dia = dia;
	}


	public long getIdHorario() {
		return idHorario;
	}


	public void setIdHorario(long idHorario) {
		this.idHorario = idHorario;
	}


	@Override
	public String toString() 
	{
		return "Calendario [id=" + id + ", anio=" + anio + ", mes=" + mes +", dia=" + dia + ", idHorario=" + idHorario  +"]";
	}

}
