
package uniandes.isis2304.aforocc.negocio;



public class Horario implements VOHorario
{
	private long id;
	private int horaEntrada;
	private int horaSalida;

	
	public Horario() 
	{
		this.id = 0;
		this.horaEntrada = 0;
		this.horaSalida = 0;
	}

	public Horario(long id, int horaE, int horaS) 
	{
		this.id = id;
		this.horaEntrada = horaE;
		this.horaSalida = horaS;
		
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(int horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public int getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(int horaSalida) {
		this.horaSalida = horaSalida;
	}

	@Override
	public String toString() 
	{
		return "Horario [id=" + id + ", horaEntrada=" + horaEntrada + ", horaSalida=" + horaSalida+ "]";
	}
}
