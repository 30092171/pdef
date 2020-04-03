/*
 * 
 */
package pdef;

/**
 * The Interface Collidable.
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
