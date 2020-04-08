package net.marvy.core;

public class GameMath {

	public static int ensureRange(int n, int min, int max) {
		return Math.min(Math.max(n, min), max);
	}
	
	public static class Vector2<T> {
		
		private T a, b;
		
		public Vector2() {
			this.a = null;
			this.b = null;
		}
		
		public Vector2(T a, T b) {
			this.a = a;
			this.b = b;
		}
		
		public T getA() {
			return a;
		}
		
		public void setA(T a) {
			this.a = a;
		}
		
		public T getB() {
			return b;
		}
		
		public void setB(T b) {
			this.b = b;
		}
		
	}
	
}
