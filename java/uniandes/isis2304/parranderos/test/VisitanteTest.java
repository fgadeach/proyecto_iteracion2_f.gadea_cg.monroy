/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.aforocc.test;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.io.FileReader;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import org.junit.Test;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import uniandes.isis2304.aforocc.negocio.Parranderos;
import uniandes.isis2304.aforocc.negocio.VOVisitante;
import uniandes.isis2304.aforocc.negocio.Visitante;

/**
 * Clase con los métdos de prueba de funcionalidad sobre Visitante
 * @author Germán Bravo
 *
 */
public class VisitanteTest
{
	
	private static Logger log = Logger.getLogger(VisitanteTest.class.getName());
	
	private static final String CONFIG_TABLAS_A = "./src/main/resources/config/TablasBD_A.json"; 
	
	
    private JsonObject tableConfig;
    
	
    private Parranderos parranderos;
	
   
    @Test
	public void CRDVisitanteTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando las operaciones CRD sobre Visitante");
			parranderos = new Parranderos (openConfig (CONFIG_TABLAS_A));
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de CRD de Visitante incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de CRD de Visitante incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		
    	try
		{
		
			List <VOVisitante> lista = parranderos.darVOVisitantes();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());

			// Lectura de los tipos de bebida con un tipo de bebida adicionado
			String nombre = "Federico Gadea";
			String tipo = "Empleado";
			int numTelefono = 31837754;
			
			String correo = "f.gadea";
			String nomContacto = "Carlos Monroy";
			int numContacto = 1234;
			
			
			
			VOVisitante Visitante1 = parranderos.adicionarVisitante(nombre, tipo, numTelefono, correo, nomContacto, numContacto);
			lista = parranderos.darVOVisitantes();
			assertEquals ("Debe haber un visitante creado !!", 1, lista.size ());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", Visitante1, lista.get (0));

			// Lectura de los tipos de bebida con dos tipos de bebida adicionados
			String nombre1 = "frank";
			String tipo1 = "Cliente";
			int numTelefono1 = 3183775;
			
			String correo1 = "f.c";
			String nomContacto1 = "Manu Monroy";
			int numContacto1 = 1234;
			
			VOVisitante Visitante2 = parranderos.adicionarVisitante (nombre1, tipo1, numTelefono1, correo1, nomContacto1, numContacto1);
			lista = parranderos.darVOVisitantes();
			assertEquals ("Debe haber dos visitantes creados !!", 2, lista.size ());
			assertTrue ("El primer visitante adicionado debe estar en la tabla", Visitante1.equals (lista.get (0)) || Visitante1.equals (lista.get (1)));
			assertTrue ("El segundo visitante adicionado debe estar en la tabla", Visitante2.equals (lista.get (0)) || Visitante2.equals (lista.get (1)));

			// Prueba de eliminación de un tipo de bebida, dado su identificador
			long tbEliminados = parranderos.eliminarVisitantePorId (Visitante1.getId ());
			assertEquals ("Debe haberse eliminado un visitante !!", 1, tbEliminados);
			lista = parranderos.darVOVisitantes();
			assertEquals ("Debe haber un solo visitante !!", 1, lista.size ());
			assertFalse ("El primer visitante adicionado NO debe estar en la tabla", Visitante1.equals (lista.get (0)));
			assertTrue ("El segundo visitante adicionado debe estar en la tabla", Visitante2.equals (lista.get (0)));
			
			// Prueba de eliminación de un tipo de bebida, dado su identificador
			tbEliminados = parranderos.eliminarVisitantePorNombre(nombre1);
			assertEquals ("Debe haberse eliminado un visitante !!", 1, tbEliminados);
			lista = parranderos.darVOVisitantes();
			assertEquals ("La tabla debió quedar vacía !!", 0, lista.size ());
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de operaciones sobre la tabla Visitante.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas sobre la tabla Visitante");
		}
		finally
		{
			parranderos.limpiarParranderos ();
    		parranderos.cerrarUnidadPersistencia ();    		
		}
	}

    /**
     * Método de prueba de la restricción de unicidad sobre el nombre de Visitante
     */
	@Test
	public void unicidadVisitanteTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando la restricción de UNICIDAD del nombre del tipo de bebida");
			parranderos = new Parranderos (openConfig (CONFIG_TABLAS_A));
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de UNICIDAD de Visitante incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de UNICIDAD de Visitante incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
		try
		{
			// Lectura de los tipos de bebida con la tabla vacía
			List <VOVisitante> lista = parranderos.darVOVisitantes();
			assertEquals ("No debe haber visirantes creados!!", 0, lista.size ());

			String nombre1 = "frank";
			String tipo1 = "Cliente";
			int numTelefono1 = 3183775;
			
			String correo1 = "f.c";
			String nomContacto1 = "Manu Monroy";
			int numContacto1 = 1234;
			
			VOVisitante Visitante1 = parranderos.adicionarVisitante (nombre1, tipo1, numTelefono1, correo1, nomContacto1, numContacto1);
			lista = parranderos.darVOVisitantes();
			assertEquals ("Debe haber un tipo de bebida creado !!", 1, lista.size ());

			VOVisitante Visitante2 = parranderos.adicionarVisitante (nombre1, tipo1, numTelefono1, correo1, nomContacto1, numContacto1);
			assertEquals ("Debe haber un visitante con el mismo nombre !!", 2, lista.size ());
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD sobre la tabla Visitante.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas de UNICIDAD sobre la tabla Visitante");
		}    				
		finally
		{
			parranderos.limpiarParranderos ();
    		parranderos.cerrarUnidadPersistencia ();    		
		}
	}

	/* ****************************************************************
	 * 			Métodos de configuración
	 *****************************************************************/
    /**
     * Lee datos de configuración para la aplicación, a partir de un archivo JSON o con valores por defecto si hay errores.
     * @param tipo - El tipo de configuración deseada
     * @param archConfig - Archivo Json que contiene la configuración
     * @return Un objeto JSON con la configuración del tipo especificado
     * 			NULL si hay un error en el archivo.
     */
    private JsonObject openConfig (String archConfig)
    {
    	JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración de tablas válido");
		} 
		catch (Exception e)
		{
//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de tablas válido: ", "VisitanteTest", JOptionPane.ERROR_MESSAGE);
		}	
        return config;
    }	
}
