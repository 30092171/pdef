/*
 * 
 */
package pdef;

/**
 * The interface provides method signatures needed for collision detection
 */
public interface Collidable {
	
	/**
	 * Checks collision between projectile and planet
	 *
	 * @param p The reference to the projectile
	 * @return true, if collision. false, if no collision.
	 */
	public boolean checkCollision(Projectile p);
}
