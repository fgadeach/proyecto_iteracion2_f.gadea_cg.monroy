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

package uniandes.isis2304.parranderos.test;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import Persistencia.PersistenciaEpsAndes;
import negocio.Administrador;
import negocio.Afiliado;
import negocio.Campana;
import negocio.Gerente;
import negocio.OrganizadorCampana;
import negocio.Recepcionista;
import negocio.Servicio;


/**
 * Clase con los métdos de prueba de funcionalidad sobre TIPOBEBIDA
 * @author Germán Bravo
 *
 */
public class RequerimientosTest
{
	private PersistenciaEpsAndes persistencia;
	private Afiliado afiliado;
	private Recepcionista recepcionista;
	private List lista= new ArrayList();
	private Administrador admin;
	private OrganizadorCampana organizador;
private Gerente gerente;
	@Test
	public void registroNuevoRolExitoso() {
		lista = persistencia.darRoles();
		assertEquals ("No debe haber roles creados!!", 0, lista.size ());

		admin.RegistrarRolUsuario("prueba");
		assertEquals ("Debe haber un rol de usuario creado!!", 1, lista.size ());
	}

	
	//---------------------------------------------------------------------

	@Test
	public void registroNuevoUsuarioExitoso() {
		lista = persistencia.darUsuarios();
		assertEquals ("No debe haber usuarios creados!!", 0, lista.size ());

		admin.registrarUsuario(1020835577, "juan", "CC", "prueba@gmail.com", 1);
		assertEquals ("Debe haber un usuario creado!!", 1, lista.size ());
	}

	
	//---------------------------------------------------------------------

	@Test
	public void registroNuevoIpsExitoso() {
		lista = persistencia.darIps();
		assertEquals ("No debe haber Ips creados!!", 0, lista.size ());

		admin.registarIPS("prueba", "Usaquen", 1);
		assertEquals ("Debe haber una ips creado!!", 1, lista.size ());
	}

	
	//----------------------------------------------------------------------

	@Test
	public void registroNuevoMedicoExitoso() {
		lista = persistencia.darMedicos();
		assertEquals ("No debe haber medicos creados!!", 0, lista.size ());

		admin.registrarMedico(1, "pediatria", 11234, 1);
		assertEquals ("Debe haber una ips creado!!", 1, lista.size ());
	}

	
	//--------------------------------------------------------------------


	@Test
	public void registroNuevoAfiliadoExitoso() {
		lista = persistencia.darAfiliados();
		assertEquals ("No debe haber afiliados creados!!", 0, lista.size ());

		Date date= new Date(1998, 2, 4);
		admin.registrarAfiliado(1, date, 1);
		assertEquals ("Debe haber un afiliado creado!!", 1, lista.size ());
	}


	//----------------------------------------------------------------------

	@Test
	public void registroNuevoServicioExitoso() {
		lista = persistencia.darServicios();
		assertEquals ("No debe haber servicios creados!!", 0, lista.size ());

		admin.registrarServicio(3, 4, 16, 2, 3, 1);
		assertEquals ("Debe haber un servicio creado!!", 1, lista.size ());
	}

	
	//---------------------------------------------------------------------------

	@Test
	public void registroNuevoReservaExitoso() {
		lista = persistencia.darReservas();
		assertEquals ("No debe haber reservas creados!!", 0, lista.size ());

		Date inicio= new Date(1998, 2, 4);
		Date fin= new Date(1999, 2, 4);

		afiliado.realizarReservaServicioServicio(1, 1, inicio,fin);
		assertEquals ("Debe haber un servicio creado!!", 1, lista.size ());
	}

	
	//-----------------------------------------------------------------------

	@Test
	public void registroNuevoPrestacionDeServicioExitoso() {
		recepcionista.registarPrestacionServicio(1);
		assertEquals ("Debe haber un servicio creado!!", 1, lista.size ());
	}


	//-----------------------------------------------------------------------

	@Test
	public void registroNuevoCampanhaExitoso() {
		lista = persistencia.darCampanas();
		assertEquals ("No debe haber campanas creados!!", 0, lista.size ());


		organizador.registrarUnaCampana("por la sangre", "Compensar");	
		assertEquals ("Debe haber un servicio creado!!", 1, lista.size ());
	}

	
	//-----------------------------------------------------------------------

	@Test
	public void registroNuevoCancelarCampanhaExitoso() {
		organizador.registrarUnaCampana("por la sangre", "Compensar");	
		assertEquals ("Debe haber un campa�a creado!!", 1, lista.size ());
		
		Campana campana= persistencia.darCampanaPorId(1);
		Servicio servicio= persistencia.darServicioPorId(1);
		organizador.cancelarServicioCampana(servicio,campana);
		assertEquals ("no Debe haber  campa�as creado!!", 0, lista.size ());




	}


	//-----------------------------------------------------------------------

	@Test
	public void registroNuevoDeshabilitarServicioExitoso() {
		lista = persistencia.darMantenimientos();
		assertEquals ("No debe haber mantenimientos creados!!", 0, lista.size ());

gerente.deshabilitarServicioDeSalud(s, fechaInicio, fechaFin, ips, "Prueba");
		assertEquals ("Debe haber un servicio creado!!", 1, lista.size ());
	}

	@Test
	public void registroNuevoDeshabilitarServicioNoExitoso() {

	}
	//-----------------------------------------------------------------------

	@Test
	public void registroNuevoReaperturaServicioExitoso() {

	}

	@Test
	public void registroNuevoReaperturaServicioNoExitoso() {

	}
	//-----------------------------------------------------------------------


}