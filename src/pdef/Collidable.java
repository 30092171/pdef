/*
 * 
 */
package pdef;

/**
 * This interface provides method signatures for collision detection
 */
public interface Collidable {
	
	/**
	 * Checks collision between projectile and planet
	 *
	 * @param p The reference to the projectile
	 * @return true, if collision has occurred; false, otherwise.
	 */
	public boolean checkCollision(Projectile p);
}
