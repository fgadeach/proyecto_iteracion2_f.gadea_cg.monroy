
package uniandes.isis2304.aforocc.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.aforocc.negocio.Lector;


class SQLLector 
{
	
	private final static String SQL = PersistenciaParranderos.SQL;

	
	private PersistenciaParranderos pp;

	
	public SQLLector (PersistenciaParranderos pp)
	{
		this.pp = pp;
	}
	
	public long adicionarLector (PersistenceManager pm, long idLector, String ubicacion) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaLector  () + "(id, ubicacion) values (?, ?)");
        q.setParameters(idLector, ubicacion);
        return (long) q.executeUnique();            
	}

	
	
	public long eliminarLectorPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaLector  () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();            
	}

	
	public Lector darLectorPorId (PersistenceManager pm, long idLector) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaLector  () + " WHERE id = ?");
		q.setResultClass(Lector.class);
		q.setParameters(idLector);
		return (Lector) q.executeUnique();
	}

	
	public List<Lector> darLectoresPorUbicacion (PersistenceManager pm, String ubicacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaLector  () + " WHERE ubicacion = ?");
		q.setResultClass(Lector.class);
		q.setParameters(ubicacion);
		return (List<Lector>) q.executeList();
	}

	
	public List<Lector> darLectores(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaLector  ());
		q.setResultClass(Lector.class);
		return (List<Lector>) q.executeList();
	}

}
