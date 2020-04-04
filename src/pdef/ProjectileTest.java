package pdef;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;


class ProjectileTest {
	
	@Test
	void testConstructorWithoutCircle() {
		Point2D origin = new Point2D(400,300);
		Projectile Projectile1 = new DefaultProjectile("Projectile Z", new PolarCoord(100, Math.PI, origin));
		assertEquals("Projectile Z", Projectile1.getName());
		assertEquals(new PolarCoord(100, Math.PI, origin), Projectile1.getPolarCoordinates());
	}
	
	@Test
	void testConstructorWithCircle() {
		Point2D origin = new Point2D(400,300);
		Projectile Projectile1 = new DefaultProjectile("Projectile Z", new PolarCoord(100,Math.PI,origin),new Circle(50));
		assertEquals("Projectile Z", Projectile1.getName());
		assertEquals(new PolarCoord(100, Math.PI, origin), Projectile1.getPolarCoordinates());
		assertEquals(300 , Projectile1.getCircle().getCenterX(),0.01);
		assertEquals(300 , Projectile1.getCircle().getCenterY(),0.01);
		assertEquals(50 , Projectile1.getCircle().getRadius(),0.01);
	}
	
	@Test
	void testSetName() {
		Point2D origin = new Point2D(400,300);
		Projectile Projectile1 = new DefaultProjectile("Projectile A", new PolarCoord(50, Math.PI, origin));
		Projectile1.setName("Projectile B");
		assertEquals("Projectile B", Projectile1.getName());
	}

	@Test
	public void testGetName() {
		Point2D origin = new Point2D(400,300);
		Projectile Projectile1 = new DefaultProjectile("Projectile A", new PolarCoord (75, 10.5, origin));
		assertEquals("Projectile A", Projectile1.getName());
	}
	
	@Test
	public void testGetPolarCoordinates() {
		Point2D origin = new Point2D(400,300);
		Projectile Projectile1 = new DefaultProjectile("Projectile B", new PolarCoord (200, 11, origin));
		assertEquals(new PolarCoord (200, 11, origin), Projectile1.getPolarCoordinates());
	}
	
	@Test
	void testSetPolarCoordinatesWithoutCircle() {
		Point2D origin = new Point2D(400,300);
		Projectile Projectile1 = new DefaultProjectile("Projectile B", new PolarCoord (225, 11.5, origin));
		Projectile1.setPolarCoordinates(new PolarCoord (300,15,origin));
		assertEquals(new PolarCoord (300, 15, origin), Projectile1.getPolarCoordinates());
	}
	
	@Test
	void testSetPolarCoordinatesWithCircle() {
		Point2D origin = new Point2D(400,300);
		Projectile Projectile1 = new DefaultProjectile("Projectile B", new PolarCoord(200,10,origin),new Circle(50));
		Projectile1.setPolarCoordinates(new PolarCoord (100,Math.PI,origin));
		assertEquals(new PolarCoord (100, Math.PI, origin), Projectile1.getPolarCoordinates());
		assertEquals(300, Projectile1.getCircle().getCenterX(),0.01);
		assertEquals(300, Projectile1.getCircle().getCenterY(),0.01);
		assertEquals(50, Projectile1.getCircle().getRadius(),0.01);
	}
	
	@Test
	public void testGetCircle(){
		Point2D origin = new Point2D(400,300);
		Circle testCircle = new Circle(300,300,25);
		Projectile Projectile1 = new DefaultProjectile("Projectile C", new PolarCoord (100,Math.PI,origin),new Circle(25));
		assertEquals(testCircle.getCenterX(), Projectile1.getCircle().getCenterX(),0.01);
		assertEquals(testCircle.getCenterY(), Projectile1.getCircle().getCenterY(),0.01);
		assertEquals(testCircle.getRadius(), Projectile1.getCircle().getRadius(),0.01);
	}
	
	@Test
	public void testGetCircleRadius(){
		Point2D origin = new Point2D(400,300);
		Projectile Projectile1 = new DefaultProjectile("Projectile C", new PolarCoord (75, 10, origin),new Circle(25));
		assertEquals(25,Projectile1.getCircleRadius(),0.001);
	}
	
	@Test
	void testSetCircleRadius() {
		Point2D origin = new Point2D(400,300);
		Projectile Projectile1 = new DefaultProjectile("Projectile C", new PolarCoord(69, 4.20, origin),new Circle(100));
		Projectile1.setCircleRadius(75);
		assertEquals(75, Projectile1.getCircleRadius(),0.001);
	}
}


