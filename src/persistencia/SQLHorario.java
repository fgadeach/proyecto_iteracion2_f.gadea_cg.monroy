package uniandes.isis2304.aforocc.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.aforocc.negocio.Horario;


class SQLHorario
{
	// 			Constantes
	 
	private final static String SQL = PersistenciaParranderos.SQL;

	// 			Atributos
	
	private PersistenciaParranderos pp;

	//			Métodos
	
	public SQLHorario (PersistenciaParranderos pp)
	{
		this.pp = pp;
	}
	
	
	public long adicionarHorario(PersistenceManager pm, long idHorario, long horaEntrada, long horaSalida) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darHorario () + "(idhorario, horaentrada, horasalida) values (?, ?, ?)");
        q.setParameters(idHorario, horaEntrada, horaSalida);
        return (long) q.executeUnique();
	}

	
	public long eliminarHorario (PersistenceManager pm, long idHorario, long horaEntrada, long horaSalida)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darHorario () + " WHERE idhorario = ?");
        q.setParameters(idHorario, horaEntrada, horaSalida);
        return (long) q.executeUnique();
	}

	
	public List<Horario> darHorario (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darHorario());
		q.setResultClass(Horario.class);
		List<Horario> resp = (List<Horario>) q.execute();
		return resp;
	}

}