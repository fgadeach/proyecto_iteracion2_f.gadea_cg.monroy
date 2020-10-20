package uniandes.isis2304.aforocc.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.aforocc.negocio.LectorLocal;


class SQLLectorLocal
{
	// 			Constantes
	 
	private final static String SQL = PersistenciaParranderos.SQL;

	// 			Atributos
	
	private PersistenciaParranderos pp;

	//			Métodos
	
	public SQLLectorLocal (PersistenciaParranderos pp)
	{
		this.pp = pp;
	}
	
	
	public long adicionarLectorLocal(PersistenceManager pm, long idLector, long idLocal) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darLectorLocal () + "(idlector, idlocal) values (?, ?)");
        q.setParameters(idLector, idLocal);
        return (long) q.executeUnique();
	}

	
	public long eliminarLectorLocal (PersistenceManager pm, long idLector, long idLocal)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darLectorLocal () + " WHERE idlector = ? AND idlocal = ?");
        q.setParameters(idLector, idLocal);
        return (long) q.executeUnique();
	}

	
	public List<LectorLocal> darLectorLocal (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaLectorLocal ());
		q.setResultClass(LectorLocal.class);
		List<LectorLocal> resp = (List<LectorLocal>) q.execute();
		return resp;
	}

}