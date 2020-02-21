package pdef;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.*;

class ProjectileTest {

	@Test
	void testProjectile() {
		Projectile Projectile1 = new Projectile(5000, "Projectile Z");
		assertEquals("Projectile Z", Projectile1.getName());
		assertEquals(5000, Projectile1.getDistance());
	}

	@Test
	void testSetName() {
		Projectile Projectile1 = new Projectile(100, "Projectile A");
		Projectile1.setName("Projectile B");
		assertEquals("Projectile B", Projectile1.getName());
	}

	@Test
	public void testGetName() {
		Projectile Projectile1 = new Projectile(100, "Projectile A");
		assertEquals("Projectile A", Projectile1.getName());
	}

}
