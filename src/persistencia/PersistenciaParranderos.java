
package uniandes.isis2304.aforocc.persistencia;


import java.math.BigDecimal;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.aforocc.negocio.Calendario;
import uniandes.isis2304.aforocc.negocio.Carnet;
import uniandes.isis2304.aforocc.negocio.CentroComercial;
import uniandes.isis2304.aforocc.negocio.Lector;
import uniandes.isis2304.aforocc.negocio.Local;
import uniandes.isis2304.aforocc.negocio.Visitante;


public class PersistenciaParranderos 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(PersistenciaParranderos.class.getName());
	
	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	 */
	private static PersistenciaParranderos instance;
	
	/**
	 * Fábrica de Manejadores de persistencia, para el manejo correcto de las transacciones
	 */
	private PersistenceManagerFactory pmf;
	
	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden:
	 * Secuenciador, Lector, Visitante, Local, bebedor, CentroComercial, Carnet y Calendario
	 */
	private List <String> tablas;
	
	/**
	 * Atributo para el acceso a las sentencias SQL propias a PersistenciaParranderos
	 */
	private SQLUtil sqlUtil;
	
	/**
	 * Atributo para el acceso a la tabla Lector de la base de datos
	 */
	private SQLLector sqlLector;
	
	/**
	 * Atributo para el acceso a la tabla Visitante de la base de datos
	 */
	private SQLVisitante sqlVisitante;
	
	/**
	 * Atributo para el acceso a la tabla Local de la base de datos
	 */
	private SQLLocal sqlLocal;

	/**
	 * Atributo para el acceso a la tabla CentroComercial de la base de datos
	 */
	private SQLCentroComercial sqlCentroComercial;
	
	/**
	 * Atributo para el acceso a la tabla Carnet de la base de datos
	 */
	private SQLCarnet sqlCarnet;
	
	/**
	 * Atributo para el acceso a la tabla Calendario de la base de datos
	 */
	private SQLCalendario sqlCalendario;
	
	/* ****************************************************************
	 * 			Métodos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patrón SINGLETON
	 */
	private PersistenciaParranderos ()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("Parranderos");		
		crearClasesSQL ();
		
		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add ("Parranderos_sequence");
		tablas.add ("LECTOR");
		tablas.add ("VISITANTE");
		tablas.add ("LOCAL");
		tablas.add ("CENTROCOMERCIAL");
		tablas.add ("CARNET");
		tablas.add ("CALENDARIO");
}

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patrón SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaParranderos (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);
		
		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

	/**
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaParranderos getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaParranderos ();
		}
		return instance;
	}
	
	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaParranderos getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaParranderos (tableConfig);
		}
		return instance;
	}

	/**
	 * Cierra la conexión con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}
	
	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}
		
		return resp;
	}
	
	/**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL ()
	{
		sqlLector = new SQLLector(this);
		sqlVisitante = new SQLVisitante(this);
		sqlLocal = new SQLLocal(this);
		sqlCentroComercial = new SQLCentroComercial(this);
		sqlCarnet = new SQLCarnet (this);
		sqlCalendario = new SQLCalendario(this);		
		sqlUtil = new SQLUtil(this);
	}

	/**
	 * @return La cadena de caracteres con el nombre del secuenciador de parranderos
	 */
	public String darSeqParranderos ()
	{
		return tablas.get (0);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Lector de parranderos
	 */
	public String darTablaLector ()
	{
		return tablas.get (1);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitante de parranderos
	 */
	public String darTablaVisitante ()
	{
		return tablas.get (2);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Local de parranderos
	 */
	public String darTablaLocal ()
	{
		return tablas.get (3);
	}

	

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de CentroComercial de parranderos
	 */
	public String darTablaCentroComercial ()
	{
		return tablas.get (4);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Carnet de parranderos
	 */
	public String darTablaCarnet ()
	{
		return tablas.get (5);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Calendario de parranderos
	 */
	public String darTablaCalendario ()
	{
		return tablas.get (6);
	}
	
	/**
	 * Transacción para el generador de secuencia de Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de Parranderos
	 */
	private long nextval ()
	{
        long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
    }
	
	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle específico del problema encontrado
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los LECTORES
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Lector
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de Visitante
	 * @return El objeto Lector adicionado. null si ocurre alguna Excepción
	 */
	public Lector adicionarLector(String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idLector = nextval ();
            long tuplasInsertadas = sqlLector.adicionarLector(pm, idLector, nombre);
            tx.commit();
            
            log.trace ("Inserción de tipo de Visitante: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Lector (idLector, nombre);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}


	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Lector, dado el identificador del tipo de Visitante
	 * Adiciona entradas al log de la aplicación
	 * @param idLector - El identificador del tipo de Visitante
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarLectorPorId (long idLector) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlLector.eliminarLectorPorId(pm, idLector);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Lector
	 * @return La lista de objetos Lector, construidos con base en las tuplas de la tabla Lector
	 */
	public List<Lector> darLectores ()
	{
		return sqlLector.darLectores (pmf.getPersistenceManager());
	}
 
	/**
	 * Método que consulta todas las tuplas en la tabla Lector que tienen el nombre dado
	 * @param nombre - El nombre del tipo de Visitante
	 * @return La lista de objetos Lector, construidos con base en las tuplas de la tabla Lector
	 */
	public List<Lector> darLectoresPorUbicacion (String nombre)
	{
		return sqlLector.darLectoresPorUbicacion(pmf.getPersistenceManager(), nombre);
	}
 
	/**
	 * Método que consulta todas las tuplas en la tabla Lector con un identificador dado
	 * @param idLector - El identificador del tipo de Visitante
	 * @return El objeto Lector, construido con base en las tuplas de la tabla Lector con el identificador dado
	 */
	public Lector darLectorPorId (long idLector)
	{
		return sqlLector.darLectorPorId (pmf.getPersistenceManager(), idLector);
	}
 
	/* ****************************************************************
	 * 			Métodos para manejar las VisitanteS
	 *****************************************************************/
	
	public Visitante adicionarVisitante(String nombre, String tipo, int numTelefono, String correo, String nomContacto, int numContacto) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long idVisitante = nextval ();
            long tuplasInsertadas = sqlVisitante.adicionarVisitante(pm, idVisitante, nombre, tipo, numTelefono, correo, nomContacto, numContacto);
            tx.commit();
            
            log.trace ("Inserción Visitante: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            return new Visitante (idVisitante, nombre, tipo, numTelefono, correo, nomContacto, numContacto);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public long eliminarVisitantePorNombre (String nombreVisitante) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlVisitante.eliminarVisitantePorNombre(pm, nombreVisitante);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public long eliminarVisitantePorId (long idVisitante) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlVisitante.eliminarVisitantePorId (pm, idVisitante);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public List<Visitante> darVisitantesPorNombre (String nombreVisitante)
	{
		return sqlVisitante.darVisitantesPorNombre (pmf.getPersistenceManager(), nombreVisitante);
	}
 
	/**
	 * Método que consulta todas las tuplas en la tabla Visitante
	 * @return La lista de objetos Visitante, construidos con base en las tuplas de la tabla Visitante
	 */
	public List<Visitante> darVisitantes ()
	{
		return sqlVisitante.darVisitantes (pmf.getPersistenceManager());
	}
 
	

	
	
	/* ****************************************************************
	 * 			Métodos para manejar los LocalES
	 *****************************************************************/
	
	
	public Local adicionarLocal(String tipo, String nombre, int aforoMax, String ubicacion, long idCC) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idLocal = nextval ();
            long tuplasInsertadas = sqlLocal.adicionarLocal(pm, idLocal, tipo, nombre, aforoMax,ubicacion,idCC);
            tx.commit();

            log.trace ("Inserción de Local: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

            return new Local (idLocal, tipo, nombre, aforoMax,ubicacion,idCC);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Local, dado el nombre del Local
	 * Adiciona entradas al log de la aplicación
	 * @param nombreLocal - El nombre del Local
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarLocalPorNombre (String nombreLocal) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlLocal.eliminarLocalPorNombre(pm, nombreLocal);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public long eliminarLocalPorId (long idLocal) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlLocal.eliminarLocalPorId (pm, idLocal);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<Local> darLocales ()
	{
		return sqlLocal.darLocales (pmf.getPersistenceManager());
	}
 
	
	public List<Local> darLocalesPorNombre (String nombreLocal)
	{
		return sqlLocal.darLocalesPorNombre (pmf.getPersistenceManager(), nombreLocal);
	}
 
	
	public Local darLocalPorId (long idLocal)
	{
		return sqlLocal.darLocalPorId (pmf.getPersistenceManager(), idLocal);
	}
 
	
	
	/* ****************************************************************
	 * 			Métodos para manejar la relación CentroComercial
	 *****************************************************************/
	
	public CentroComercial adicionarCentroComercial(String nombre, int aforoMax) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idCC = nextval ();
            long tuplasInsertadas = sqlCentroComercial.adicionarCentroComercial (pm,idCC, nombre, aforoMax);
            tx.commit();

            log.trace ("Inserción de CentroComercial: [" + idCC + ", " + nombre + "]. " + tuplasInsertadas + " tuplas insertadas");

            return new CentroComercial (idCC, nombre, aforoMax);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public long eliminarCentroComercialPorId (long idcc) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlCentroComercial.eliminarCentroComercial(pm, idcc);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<CentroComercial> darCentroComercial ()
	{
		return sqlCentroComercial.darCentroComercial (pmf.getPersistenceManager());
	}
 
 
	/* ****************************************************************
	 * 			Métodos para manejar la relación Carnet
	 *****************************************************************/
	
	
	public Carnet adicionarCarnet ( long idVisitante) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlCarnet.adicionarCarnet (pmf.getPersistenceManager(), id, idVisitante);
    		tx.commit();

            log.trace ("Inserción de Carnet: [" + id + ", " + idVisitante + "]. " + tuplasInsertadas + " tuplas insertadas");

            return new Carnet (id, idVisitante);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
 
	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Carnet, dados los identificadores de Local y Visitante
	 * @param idLocal - El identificador del Local
	 * @param idVisitante - El identificador de la Visitante
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarCarnet (long idLocal, long idVisitante) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
	        Transaction tx=pm.currentTransaction();
	        try
	        {
	            tx.begin();
	            long resp = sqlCarnet.eliminarCarnet (pm, idLocal, idVisitante);	            
	            tx.commit();

	            return resp;
	        }
	        catch (Exception e)
	        {
//	        	e.printStackTrace();
	        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
	        	return -1;
	        }
	        finally
	        {
	            if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
	        }
	}
 
	/**
	 * Método que consulta todas las tuplas en la tabla Carnet
	 * @return La lista de objetos Carnet, construidos con base en las tuplas de la tabla Carnet
	 */
	public List<Carnet> darCarnet ()
	{
		return sqlCarnet.darCarnet (pmf.getPersistenceManager());
	}
 
	
 
	/* ****************************************************************
	 * 			Métodos para manejar la relación Calendario
	 *****************************************************************/

	
	public Calendario adicionarCalendario ( int anio, int mes, int dia, long idHorario) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlCalendario.adicionarCalendario (pmf.getPersistenceManager(), id, anio,mes,dia,idHorario);
    		tx.commit();

            log.trace ("Inserción de Carnet: [" + id + ", " + idHorario + "]. " + tuplasInsertadas + " tuplas insertadas");
            
            return new Calendario (id, anio,mes,dia,idHorario);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}


	
	public long eliminarCalendario (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
	        Transaction tx=pm.currentTransaction();
	        try
	        {
	            tx.begin();
	            long resp = sqlCalendario.eliminarCalendario (pm, id);	            
	            tx.commit();

	            return resp;
	        }
	        catch (Exception e)
	        {
//	        	e.printStackTrace();
	        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
	        	return -1;
	        }
	        finally
	        {
	            if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
	        }
	}

	public List<Calendario> darCalendario ()
	{
		return sqlCalendario.darCalendario (pmf.getPersistenceManager());
	}	

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de Parranderos
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas CentroComercial, Carnet, Calendario, Visitante,
	 * Lector, BEBEDOR y Local, respectivamente
	 */
	public long [] limpiarParranderos ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long [] resp = sqlUtil.limpiarParranderos (pm);
            tx.commit ();
            log.info ("Borrada la base de datos");
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return new long[] {-1, -1, -1, -1, -1, -1, -1};
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
		
	}
	

 }
