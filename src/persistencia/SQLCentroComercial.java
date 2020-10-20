
package uniandes.isis2304.aforocc.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.aforocc.negocio.CentroComercial;


class SQLCentroComercial 
{
	
	private final static String SQL = PersistenciaParranderos.SQL;

	private PersistenciaParranderos pp;

	public SQLCentroComercial (PersistenciaParranderos pp)
	{
		this.pp = pp;
	}
	
	
	public long adicionarCentroComercial(PersistenceManager pm, long id, String nombre, int aforoMax) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCentroComercial () + "(id, nombre, aforoMax) values (?, ?, ?)");
        q.setParameters(id, nombre, aforoMax);
        return (long) q.executeUnique();
	}

	
	public long eliminarCentroComercial (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCentroComercial () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

	
	public List<CentroComercial> darCentroComercial (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCentroComercial ());
		q.setResultClass(CentroComercial.class);
		List<CentroComercial> resp = (List<CentroComercial>) q.execute();
		return resp;
	}

}
