
package uniandes.isis2304.aforocc.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;


class SQLUtil
{
	
	private final static String SQL = PersistenciaParranderos.SQL;

	
	private PersistenciaParranderos pp;

	
	public SQLUtil (PersistenciaParranderos pp)
	{
		this.pp = pp;
	}
	
	
	public long nextval (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqParranderos () + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}

	public long [] limpiarParranderos (PersistenceManager pm)
	{
        Query qCalendario = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCalendario ());          
        Query qCarnet = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCarnet ());
        Query qLector = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaLector ());
        Query qLocal = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaLocal ());
        Query qVisitante = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVisitante ());
        Query qCentroComercial = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCentroComercial ());
        

        long CalendarioEliminados = (long) qCalendario.executeUnique ();
        long CarnetEliminados = (long) qCarnet.executeUnique ();
        long LectorEliminadas = (long) qLector.executeUnique ();
        long LocalsEliminadas = (long) qLocal.executeUnique ();
        long tiposLocalEliminados = (long) qVisitante.executeUnique ();
        long CentroComercialesEliminados = (long) qCentroComercial.executeUnique ();
        
        return new long[] {CalendarioEliminados, CarnetEliminados, LectorEliminadas, LocalsEliminadas, 
        		tiposLocalEliminados, CentroComercialesEliminados};
	}

}
