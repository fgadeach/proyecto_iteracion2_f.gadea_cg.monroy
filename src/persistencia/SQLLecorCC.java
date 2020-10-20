package uniandes.isis2304.aforocc.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.aforocc.negocio.LectorCC;


class SQLLectorCC
{
	// 			Constantes
	 
	private final static String SQL = PersistenciaParranderos.SQL;

	// 			Atributos
	
	private PersistenciaParranderos pp;

	//			Métodos
	
	public SQLLectorCC (PersistenciaParranderos pp)
	{
		this.pp = pp;
	}
	
	
	public long adicionarLectorCC(PersistenceManager pm, long idLector, long idCentroComercial) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darLectorCC () + "(idlector, idcentrocomercial) values (?, ?)");
        q.setParameters(idLector, idCentroComercial);
        return (long) q.executeUnique();
	}

	
	public long eliminarLectorCc (PersistenceManager pm, long idLector, long idCentroComercial)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darLectorCC () + " WHERE idlector = ? AND idcentrocomercial = ?");
        q.setParameters(idLector, idCentroComercial);
        return (long) q.executeUnique();
	}

	
	public List<LectorCC> darLectorCC (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaLectorCC ());
		q.setResultClass(LectorCC.class);
		List<LectorCC> resp = (List<LectorCC>) q.execute();
		return resp;
	}

}