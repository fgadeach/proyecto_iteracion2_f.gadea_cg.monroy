
package uniandes.isis2304.aforocc.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.aforocc.negocio.HoraLocal;


class SQLHoraLocal 
{

	private final static String SQL = PersistenciaParranderos.SQL;


	private PersistenciaParranderos pp;


	public SQLHoraLocal (PersistenciaParranderos pp)
	{
		this.pp = pp;
	}
	
	
	public long adicionarHoraLocal(PersistenceManager pm, long idHorario, long idLocal) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHoraLocal () + "(idhorario, idlocal) values (?, ?)");
        q.setParameters(idHorario, idLocal);
        return (long) q.executeUnique();
	}

	
	public long eliminarHoraLocal (PersistenceManager pm, long idHorario, long idLocal)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHoraLocal () + " WHERE idhorario = ? AND idlocal = ?");
        q.setParameters(idHorario, idLocal);
        return (long) q.executeUnique();
	}

	
	public List<HoraLocal> darHoraLocal (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHoraLocal ());
		q.setResultClass(HoraLocal.class);
		List<HoraLocal> resp = (List<HoraLocal>) q.execute();
		return resp;
	}

}