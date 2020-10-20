
package uniandes.isis2304.aforocc.persistencia;

import java.util.List;



import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.aforocc.negocio.Carnet;


class SQLCarnet 
{
	
	private final static String SQL = PersistenciaParranderos.SQL;

	private PersistenciaParranderos pp;

	
	public SQLCarnet (PersistenciaParranderos pp)
	{
		this.pp = pp;
	}
	
	public long adicionarCarnet (PersistenceManager pm, long id, long idVisitante) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCarnet () + "(id, idvisitante) values (?, ?, ?)");
        q.setParameters(id, idVisitante);
        return (long)q.executeUnique();            
	}

	public long eliminarCarnet (PersistenceManager pm, long id, long idVisitante) 
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCarnet () + " WHERE id = ? AND idVisitante = ?");
        q.setParameters(id, idVisitante);
        return (long) q.executeUnique();            
	}

	public List<Carnet> darCarnet (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCarnet ());
		q.setResultClass(Carnet.class);
		return (List<Carnet>) q.execute();
	}
}
