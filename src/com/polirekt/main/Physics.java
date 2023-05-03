package com.polirekt.main;

public class Physics {

	public static boolean Collision(EntityA entA, EntityB entB) {
		if (entA.getBounds().intersects(entB.getBounds())) {
			return true;
		}
		return false;
	}
	
	public static boolean Collision(EntityB entB, EntityA entA) {
		if (entB.getBounds().intersects(entA.getBounds())) {
			return true;
		}
		return false;
	}
	public static boolean Collision(EntityA entA, EntityC entC) {
		if (entC.getBounds().intersects(entA.getBounds())) {
			return true;
		}
		return false;
	}
}
