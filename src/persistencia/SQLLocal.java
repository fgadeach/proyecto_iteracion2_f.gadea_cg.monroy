

package uniandes.isis2304.aforocc.persistencia;

import java.util.List;


import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.aforocc.negocio.Local;


class SQLLocal 
{

	private final static String SQL = PersistenciaParranderos.SQL;

	
	private PersistenciaParranderos pp;

	
	public SQLLocal (PersistenciaParranderos pp)
	{
		this.pp = pp;
	}
	
	
	public long adicionarLocal (PersistenceManager pm, long id, String tipo, String nombre, int aforoMax, String ubicacion, long idCC) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaLocal () + "(id, tipo, nombre, aforoMax, ubicacion, idCC) values (?, ?, ?, ?, ?,?)");
        q.setParameters(id, tipo, nombre, aforoMax, ubicacion, idCC);
        return (long) q.executeUnique();
	}
	
	
	public long eliminarLocalPorNombre (PersistenceManager pm, String nombreLocal)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaLocal () + " WHERE nombre = ?");
        q.setParameters(nombreLocal);
        return (long) q.executeUnique();
	}

	public long eliminarLocalPorId (PersistenceManager pm, long idLocal)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaLocal () + " WHERE id = ?");
        q.setParameters(idLocal);
        return (long) q.executeUnique();
	}

	
	public Local darLocalPorId (PersistenceManager pm, long idLocal) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaLocal () + " WHERE id = ?");
		q.setResultClass(Local.class);
		q.setParameters(idLocal);
		return (Local) q.executeUnique();
	}

	
	public List<Local> darLocalesPorNombre (PersistenceManager pm, String nombreLocal) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaLocal () + " WHERE nombre = ?");
		q.setResultClass(Local.class);
		q.setParameters(nombreLocal);
		return (List<Local>) q.executeList();
	}

	
	public List<Local> darLocales (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaLocal ());
		q.setResultClass(Local.class);
		return (List<Local>) q.executeList();
	}
	
}
