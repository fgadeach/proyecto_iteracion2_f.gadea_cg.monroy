


package uniandes.isis2304.aforocc.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.aforocc.negocio.Calendario;


class SQLCalendario 
{
	
	private final static String SQL = PersistenciaParranderos.SQL;

	
	private PersistenciaParranderos pp;

	public SQLCalendario (PersistenciaParranderos pp)
	{
		this.pp = pp;
	}
	
	
	public long adicionarCalendario (PersistenceManager pm, long id, int anio,  int mes, int dia, long idHorario) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCalendario () + "(id, anio, mes, dia, idhorario) values (?, ?, ?, ?, ?)");
        q.setParameters(id, anio, mes, dia, idHorario);
        return (long) q.executeUnique();
	}

	
	public long eliminarCalendario (PersistenceManager pm, long id) 
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCalendario () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}


	
	public List<Calendario> darCalendario (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCalendario ());
		q.setResultClass(Calendario.class);
		return (List<Calendario>) q.execute();
	}
		 	
}
