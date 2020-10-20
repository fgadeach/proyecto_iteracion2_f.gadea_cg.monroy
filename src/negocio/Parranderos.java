
package uniandes.isis2304.aforocc.negocio;

import java.sql.Timestamp;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import com.google.gson.JsonObject;
import uniandes.isis2304.aforocc.persistencia.PersistenciaParranderos;

public class Parranderos 
{
	
	private static Logger log = Logger.getLogger(Parranderos.class.getName());
	
	
	private PersistenciaParranderos pp;
	
	
	public Parranderos ()
	{
		pp = PersistenciaParranderos.getInstance ();
	}
	
	
	public Parranderos (JsonObject tableConfig)
	{
		pp = PersistenciaParranderos.getInstance (tableConfig);
	}
	

	public void cerrarUnidadPersistencia ()
	{
		pp.cerrarUnidadPersistencia ();
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los Calendarios
	 *****************************************************************/
	
	
	public Calendario adicionarCalendario ( int anio, int mes, int dia, long idHorario)
	{
        log.info ("Adicionando calendario: " + anio+ "/" + mes +"/"+dia);
        Calendario Calendario = pp.adicionarCalendario (anio,mes,dia,idHorario);		
        log.info ("Adicionando Tipo de Carnet: " + Calendario);
        return Calendario;
	}
	
	
	public long eliminarCalendarioPorId (long idCalendario)
	{
		log.info ("Eliminando Tipo de Carnet por id: " + idCalendario);
        long resp = pp.eliminarCalendario(idCalendario);		
        log.info ("Eliminando Tipo de Carnet por id: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	
	public List<Calendario> darCalendarios ()
	{
		log.info ("Consultando Tipos de Carnet");
        List<Calendario> calendario = pp.darCalendario();	
        log.info ("Consultando calendarios: " + calendario.size() + " existentes");
        return calendario;
	}

	
	public List<VOCalendario> darVOCalendarios()
	{
		log.info ("Generando los VO de Tipos de Carnet");        
        List<VOCalendario> voTipos = new LinkedList<VOCalendario> ();
        for (Calendario tb : pp.darCalendario())
        {
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de Calendario: " + voTipos.size() + " existentes");
        return voTipos;
	}


	/* ****************************************************************
	 * 			Métodos para manejar las CarnetS
	 *****************************************************************/
	
	
	public Carnet adicionarCarnet (long idVisitante)
	{
		log.info ("Adicionando Carnet con el id del visitante " + idVisitante);
		Carnet Carnet = pp.adicionarCarnet(idVisitante);
        log.info ("Adicionando Carnet: " + Carnet);
        return Carnet;
	}
	
	
	public long eliminarCarnetPorNombre (long id, long idVisitante)
	{
        log.info ("Eliminando Carnet por id: " + id);
        long resp = pp.eliminarCarnet(id, idVisitante);
        log.info ("Eliminando Carnet por nombre: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	public List<Carnet> darCarnets ()
	{
        log.info ("Consultando Carnets");
        List<Carnet> Carnets = pp.darCarnet ();	
        log.info ("Consultando Carnets: " + Carnets.size() + " Carnets existentes");
        return Carnets;
	}


	/* ****************************************************************
	 * 			Métodos para manejar los LocalES
	 *****************************************************************/

	
	public Local adicionarLocal (String tipo, String nombre, int aforoMax, String ubicacion, long idC)
	{
        log.info ("Adicionando Local: " + nombre);
        Local Local = pp.adicionarLocal (tipo, nombre, aforoMax,ubicacion,idC);
        log.info ("Adicionando Local: " + Local);
        return Local;
	}

	/**
	 * Elimina un Local por su nombre
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del Local a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarLocalPorNombre (String nombre)
	{
        log.info ("Eliminando Local por nombre: " + nombre);
        long resp = pp.eliminarLocalPorNombre (nombre);
        log.info ("Eliminando Local por nombre: " + resp + " tuplas eliminadas");
        return resp;
	}

	/**
	 * Elimina un Local por su identificador
	 * Adiciona entradas al log de la aplicación
	 * @param idLocal - El identificador del Local a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarLocalPorId (long idLocal)
	{
        log.info ("Eliminando Local por id: " + idLocal);
        long resp = pp.eliminarLocalPorId (idLocal);
        log.info ("Eliminando Local por Id: " + resp + " tuplas eliminadas");
        return resp;
	}

	
	public Local darLocalPorId (long idLocal)
	{
        log.info ("Dar información de un Local por id: " + idLocal);
        Local Local = pp.darLocalPorId (idLocal);
        log.info ("Buscando Local por Id: " + Local != null ? Local : "NO EXISTE");
        return Local;
	}

	public List<Local> darLocalesPorNombre (String nombre)
	{
        log.info ("Dar información de Locales por nombre: " + nombre);
        List<Local> Locales = pp.darLocalesPorNombre (nombre);
        log.info ("Dar información de Locales por nombre: " + Locales.size() + " Locales con ese nombre existentes");
        return Locales;
 	}

	
	public List<VOLocal> darVOLocalesPorNombre (String nombre)
	{
        log.info ("Generando VO de Locales por nombre: " + nombre);
        List<VOLocal> voLocales = new LinkedList<VOLocal> ();
       for (Local bdor : pp.darLocalesPorNombre (nombre))
       {
          	voLocales.add (bdor);
       }
       log.info ("Generando los VO de Locales: " + voLocales.size() + " Locales existentes");
      return voLocales;
 	}

	/**
	 * Encuentra todos los Locales en Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos Local con todos las Locales que conoce la aplicación, llenos con su información básica
	 */
	public List<Local> darLocales ()
	{
        log.info ("Listando Locales");
        List<Local> Locales = pp.darLocales ();	
        log.info ("Listando Locales: " + Locales.size() + " Locales existentes");
        return Locales;
	}
	
	/**
	 * Encuentra todos los Locales en Parranderos y los devuelve como VOLocal
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOLocal con todos las Locales que conoce la aplicación, llenos con su información básica
	 */
	public List<VOLocal> darVOLocales ()
	{
        log.info ("Generando los VO de Locales");
         List<VOLocal> voLocales = new LinkedList<VOLocal> ();
        for (Local bdor : pp.darLocales ())
        {
        	voLocales.add (bdor);
        }
        log.info ("Generando los VO de Locales: " + voLocales.size() + " Locales existentes");
       return voLocales;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los VisitanteES
	 *****************************************************************/

	
	public Visitante adicionarVisitante (String nombre, String tipo, int numTelefono, String correo, String nomContacto, int numContacto)
	{
        log.info ("Adicionando Visitante: " + nombre);
        Visitante Visitante = pp.adicionarVisitante (nombre,tipo, numTelefono,correo,nomContacto,numContacto);
        log.info ("Adicionando Visitante: " + Visitante);
        return Visitante;
	}
	
	
	public long eliminarVisitantePorNombre (String nombre)
	{
        log.info ("Eliminando Visitante por nombre: " + nombre);
        long resp = pp.eliminarVisitantePorNombre (nombre);
        log.info ("Eliminando Visitante: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	
	public long eliminarVisitantePorId (long idVisitante)
	{
        log.info ("Eliminando Visitante por id: " + idVisitante);
        long resp = pp.eliminarVisitantePorId (idVisitante);
        log.info ("Eliminando Visitante: " + resp);
        return resp;
	}
	
	public List<Visitante> darVisitantes ()
	{
        log.info ("Listando Visitantees");
        List<Visitante> Visitantees = pp.darVisitantes ();	
        log.info ("Listando Visitantees: " + Visitantees.size() + " Visitantees existentes");
        return Visitantees;
	}

	public List<VOVisitante> darVOVisitantes ()
	{
		log.info ("Generando los VO de Visitantees");
		List<VOVisitante> voVisitantees = new LinkedList<VOVisitante> ();
		for (Visitante Visitante: pp.darVisitantes ())
		{
			voVisitantees.add (Visitante);
		}
		log.info ("Generando los VO de Visitantees: " + voVisitantees.size () + " Visitantees existentes");
		return voVisitantees;
	}

	
	/* ****************************************************************
	 * 			Métodos para manejar la relación Lector
	 *****************************************************************/

	
	public Lector adicionarLector (String ubicacion)
	{
        log.info ("Adicionando Lector [" +ubicacion + "]");
        Lector resp = pp.adicionarLector (ubicacion);
        log.info ("Adicionando Lector: " + resp + " tuplas insertadas");
        return resp;
	}
	
	
	public long eliminarLector (long idlector)
	{
        log.info ("Eliminando Lector");
        long resp = pp.eliminarLocalPorId(idlector);
        log.info ("Eliminando Lector: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	
	public List<Lector> darLector ()
	{
        log.info ("Listando Lector");
        List<Lector> Lector = pp.darLectores();	
        log.info ("Listando Lector: " + Lector.size() + " preferencias de gusto existentes");
        return Lector;
	}
	
	public List<Lector> darLectorPorId (String ubicacion)
	{
        log.info ("Listando Lector por ubicacion " + ubicacion);
        List<Lector> Lector = pp.darLectoresPorUbicacion(ubicacion);	
        log.info ("Listando Lector: " + Lector.size() + " preferencias de gusto existentes");
        return Lector;
	}

	
	public List<VOLector> darVOLector ()
	{
		log.info ("Generando los VO de Lector");
		List<VOLector> voLector = new LinkedList<VOLector> ();
		for (VOLector Visitante: pp.darLectores ())
		{
			voLector.add (Visitante);
		}
		log.info ("Generando los VO de Lector: " + voLector.size () + " Lector existentes");
		return voLector;
	}

	/* ****************************************************************
	 * 			Métodos para manejar la relación CentroComercial
	 *****************************************************************/

	
	public CentroComercial adicionarCentroComercial (String nombre, int aforoMax)
	{
        log.info ("Adicionando CentroComercial [" + nombre + ", " + aforoMax + "]");
        CentroComercial resp = pp.adicionarCentroComercial (nombre,aforoMax);
        log.info ("Adicionando CentroComercial: " + resp + " tuplas insertadas");
        return resp;
	}
	
	
	public long eliminarCentroComercial (long id)
	{
        log.info ("Eliminando CentroComercial");
        long resp = pp.eliminarCentroComercialPorId(id);
        log.info ("Eliminando CentroComercial: " + resp + "tuplas eliminadas");
        return resp;
	}
	
	
	public List<CentroComercial> darCentroComercial ()
	{
        log.info ("Listando CentroComercial");
        List<CentroComercial> CentroComercial = pp.darCentroComercial ();	
        log.info ("Listando CentroComercial: " + CentroComercial.size() + " CentroComercial existentes");
        return CentroComercial;
	}

	
	public List<VOCentroComercial> darVOCentroComercial ()
	{
		log.info ("Generando los VO de CentroComercial");
		List<VOCentroComercial> voLector = new LinkedList<VOCentroComercial> ();
		for (VOCentroComercial CentroComercial: pp.darCentroComercial ())
		{
			voLector.add (CentroComercial);
		}
		log.info ("Generando los VO de CentroComercial: " + voLector.size () + " CentroComercial existentes");
		return voLector;
	}

	
	/* ****************************************************************
	 * 			Métodos para administración
	 *****************************************************************/

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de Parranderos
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas Lector, CentroComercial, VISITAN, Carnet,
	 * Calendario, Local y Visitante, respectivamente
	 */
	public long [] limpiarParranderos ()
	{
        log.info ("Limpiando la BD de Parranderos");
        long [] borrrados = pp.limpiarParranderos();	
        log.info ("Limpiando la BD de Parranderos: Listo!");
        return borrrados;
	}
}
