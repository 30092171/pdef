package pdef;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import javafx.geometry.Point2D;

import org.junit.*;

class ProjectileTest {

	@Test
	void testProjectile() {
		Point2D origin = new Point2D(400,300);
		Projectile Projectile1 = new DefaultProjectile("Projectile Z", new PolarCoord(100, Math.PI, origin));
		assertEquals("Projectile Z", Projectile1.getName());
		assertEquals(new PolarCoord(100, Math.PI, origin), Projectile1.getPolarCoordinates());
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

}
