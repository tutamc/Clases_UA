package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/*
 * 
 * 
 * Añade los tests de esta clase inspirándote en los de la clase JPieceTestP3. Ambas clases se 
 * parecerán mucho, pero pon especial atención al método setUpBeforeClass donde sí puede haber
 * múltiples diferencias.
 * 
 * 
 */

/**
 * 
 * @author Lucas Meirelles
 *
 */
public class ZPieceTestP3 {
    Piece p1;
    static ArrayList<Coordinate> coorD0, coorD90, coorD180, coorD270;
    static String sD0, sD90, sD180, sD270;
    static char symbol = '◪';
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		coorD0 = new ArrayList<Coordinate>();
		coorD90 = new ArrayList<Coordinate>();
		coorD180 = new ArrayList<Coordinate>();
		coorD270 = new ArrayList<Coordinate>();
		symbol = '◫';
		for (int i=0; i < 2; i++) {
			coorD0.add(new Coordinate(0,i));
			coorD0.add(new Coordinate(1,i+1));
			coorD270.add(new Coordinate(i+1,1));
			coorD270.add(new Coordinate(i,2));
			coorD180.add(new Coordinate(1,i));
			coorD180.add(new Coordinate(2,i+1));
			coorD90.add(new Coordinate(i+1,0));
			coorD90.add(new Coordinate(i,1));
		}
		coorD0.add(new Coordinate(0,2));
		coorD270.add(new Coordinate(2,2));
		coorD180.add(new Coordinate(2,0));
		coorD90.add(new Coordinate(0,0));
		
		 sD0 = "◫◫··\n·◫◫·\n····\n····\n";
		 sD270 = "··◫·\n·◫◫·\n·◫··\n····\n";
		 sD180 = "····\n◫◫··\n·◫◫·\n····\n";
		 sD90 = "·◫··\n◫◫··\n◫···\n····\n";
	}


	@Before
	public void setUp() throws Exception {
		p1 = PieceFactory.createPiece("Z");
		
	}

	@After
	public void tearDown() throws Exception {
	}

	//Test del constructor por defecto de Pieza.
	@Test
	public void testZPiece() {
		assertNotNull("P no es null",p1);
		assertEquals("Orientacion==D0",Rotation.D0, p1.getOrientation());
		assertFalse("fixed == false",p1.isFixed());
		assertEquals("Symbol == "+symbol,symbol,p1.getBlockSymbol());
	}

	//Test  copia de ZPieza
	@Test
	public void testCopyZPiece1() {
		Piece p = p1.copy();
		assertNotNull("P no es null",p);
		assertNotSame("p != p1",p1,p);
		assertEquals("Orientacion==D0",Rotation.D0, p.getOrientation());
		assertFalse("fixed == false",p.isFixed());
		assertEquals("Symbol == "+symbol,symbol,p.getBlockSymbol());
		
	}

	//Test copia de una Pieza previamente modificada.
	@Test
	public void testCopyZPiece2() {
		p1.setOrientation(Rotation.D270);
		p1.setFixed(true);
		Piece p = p1.copy();
		assertNotNull("P no es null",p);
		assertNotSame("p != p1",p1,p);
		assertEquals("Orientacion==D270",Rotation.D270, p.getOrientation());
		assertTrue("fixed == false",p.isFixed());
		assertEquals("Symbol == ◫",'◫',p.getBlockSymbol());
		
	}
	
	//Test getAbsoluteCells para D0
	@Test
	public void testGetAbsoluteCellsD0() {
		Coordinate c1 = new Coordinate(13,27);
		Set<Coordinate> cells = p1.getAbsoluteCells(c1);
		for (int i=0; i<4; i++){
			assertTrue("Valores Absolutos celdas D0+c1", cells.contains(coorD0.get(i).add(c1)));
		}
		
	}

	//Test getAbsoluteCells para D90
	@Test
	public void testGetAbsoluteCellsD90() {
		Coordinate c1 = new Coordinate(0,0);
		p1.setOrientation(Rotation.D90);
		Set<Coordinate> cells = p1.getAbsoluteCells(c1);
		for (int i=0; i<4; i++){
			assertTrue("Valores Absolutos celdas D90+c1", cells.contains(coorD90.get(i).add(c1)));
		}
		
	}
	
	//Test getAbsoluteCells para D180
	@Test
	public void testGetAbsoluteCellsD180() {
		Coordinate c1 = new Coordinate(300,700);
		p1.setOrientation(Rotation.D180);
		Set<Coordinate> cells = p1.getAbsoluteCells(c1);
		for (int i=0; i<4; i++){
			assertTrue("Valores Absolutos celdas D180+c1", cells.contains(coorD180.get(i).add(c1)));
		}
		
	}
	
	//Test getAbsoluteCells para D270
	@Test
	public void testGetAbsoluteCellsD270() {
		Coordinate c1 = new Coordinate(11,11);
		p1.setOrientation(Rotation.D270);
		Set<Coordinate> cells = p1.getAbsoluteCells(c1);
		for (int i=0; i<4; i++){
			assertTrue("Valores Absolutos celdas 270+c1", cells.contains(coorD270.get(i).add(c1)));
		}
		
	}
	
	//Test toString para Todas rotaciones
	@Test
	public void testToStringRotations() {
		assertEquals("D0 toString",sD0,p1.toString());
		p1.rotateCounterclockwise();
		assertEquals("D90 toString",sD90,p1.toString());
		p1.rotateCounterclockwise();
		assertEquals("D180 toString",sD180,p1.toString());
		p1.rotateCounterclockwise();
		assertEquals("D270 toString",sD270,p1.toString());
	}
}

