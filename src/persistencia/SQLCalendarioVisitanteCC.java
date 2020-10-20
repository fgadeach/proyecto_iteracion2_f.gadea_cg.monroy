package uniandes.isis2304.aforocc.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.aforocc.negocio.CalendarioVisitanteCC;

class SQLCalendarioVisitanteCC
{
	// 			Constantes
	 
	private final static String SQL = PersistenciaParranderos.SQL;

	// 			Atributos
	
	private PersistenciaParranderos pp;

	//			Métodos
	
	public SQLCalendarioVisitanteCC (PersistenciaParranderos pp)
	{
		this.pp = pp;
	}
	
	
	public long adicionarCalendarioVisitanteCC(PersistenceManager pm, long idCalendario, long idCentroComercial, long idVisitante) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCalendarioVisitanteCC () + "(idcalendario, idcentrocomercial, idvisitante) values (?, ?, ?)");
        q.setParameters(idCalendario, idCentroComercial, idVisitante);
        return (long) q.executeUnique();
	}

	
	public long eliminarCalendarioVisitanteCC (PersistenceManager pm, long idCalendario, long idCentroComercial, long idVisitante)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCalendarioVisitanteCC () + " WHERE idcalendario = ? AND idcentrocomercial = ? AND idvisitante = ?");
        q.setParameters(idCalendario, idCentroComercial, idVisitante);
        return (long) q.executeUnique();
	}

	
	public List<CalendarioVisitanteCC> darCalendarioVisitanteCC (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCalendarioVisitanteCC ());
		q.setResultClass(CalendarioVisitanteCC.class);
		List<CalendarioVisitanteCC> resp = (List<CalendarioVisitanteCC>) q.execute();
		return resp;
	}

}