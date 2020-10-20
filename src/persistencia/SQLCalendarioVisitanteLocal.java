
package uniandes.isis2304.aforocc.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.aforocc.negocio.CalendarioVisitanteLocal;
 

class SQLCalendarioVisitanteLocal
{
	
	private final static String SQL = PersistenciaParranderos.SQL;


	private PersistenciaParranderos pp;

	public SQLCalendarioVisitanteLocal (PersistenciaParranderos pp)
	{
		this.pp = pp;
	}
	

	public long adicionarCalendarioVisitanteLocal(PersistenceManager pm, long idCalendario, long idLocal, long idVisitante) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCalendarioVisitanteLocal () + "(idhorario, idlocal, idvisitante) values (?, ?, ?)");
        q.setParameters(idCalendario, idLocal, idVisitante);
        return (long) q.executeUnique();
	}


	public long eliminarCalendarioVisitanteLocal (PersistenceManager pm, long idCalendario, long idLocal, long idVisitante)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCalendarioVisitanteLocal () + " WHERE idhorario = ? AND idlocal = ? AND idvisitante = ?");
        q.setParameters(idCalendario, idLocal, idVisitante);
        return (long) q.executeUnique();
	}


	public List<CalendarioVisitanteLocal> darCalendarioVisitanteLocal (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCalendarioVisitanteLocal ());
		q.setResultClass(CalendarioVisitanteLocal.class);
		List<CalendarioVisitanteLocal> resp = (List<CalendarioVisitanteLocal>) q.execute();
		return resp;
	}

}