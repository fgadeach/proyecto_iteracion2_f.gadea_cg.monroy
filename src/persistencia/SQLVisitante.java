

package uniandes.isis2304.aforocc.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.aforocc.negocio.Visitante;

class SQLVisitante 
{
	private final static String SQL = PersistenciaParranderos.SQL;

	
	private PersistenciaParranderos pp;

	
	public SQLVisitante (PersistenciaParranderos pp)
	{
		this.pp = pp;
	}
	
	
	public long adicionarVisitante (PersistenceManager pm, long id, String nombre, String tipo, int numTelefono, String correo, String nomContacto, int numContacto) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaVisitante () + "(id, nombre, tipo, numTelefono, correo, nomContacto, numContacto) values (?, ?, ?, ?, ?,?)");
        q.setParameters(id, nombre, tipo, numTelefono, correo, nomContacto, numContacto);
        return (long) q.executeUnique();
	}
	
	
	public long eliminarVisitantePorNombre (PersistenceManager pm, String nombreVisitante)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVisitante () + " WHERE nombre = ?");
        q.setParameters(nombreVisitante);
        return (long) q.executeUnique();
	}

	public long eliminarVisitantePorId (PersistenceManager pm, long idVisitante)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVisitante () + " WHERE id = ?");
        q.setParameters(idVisitante);
        return (long) q.executeUnique();
	}

	
	public Visitante darVisitantePorId (PersistenceManager pm, long idVisitante) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVisitante () + " WHERE id = ?");
		q.setResultClass(Visitante.class);
		q.setParameters(idVisitante);
		return (Visitante) q.executeUnique();
	}

	
	public List<Visitante> darVisitantesPorNombre (PersistenceManager pm, String nombreVisitante) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVisitante () + " WHERE nombre = ?");
		q.setResultClass(Visitante.class);
		q.setParameters(nombreVisitante);
		return (List<Visitante>) q.executeList();
	}

	
	public List<Visitante> darVisitantes (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVisitante ());
		q.setResultClass(Visitante.class);
		return (List<Visitante>) q.executeList();
	}

}
