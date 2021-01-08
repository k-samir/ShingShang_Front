package control;


/**
 * Tuple<S1, S2> class : structure created to return a boolean and a string at the same time
 * used in MoveValidator for exemple 
 * @author Samir KAMAR
 *
 */
public class Tuple<S1, S2> {
	
	  private int first;
	  private int second;
	  
	    public Tuple(int first, int second){
	        this.first = first;
	        this.second = second;
	    }

		public int getFirst() {
			return first;
		}

		public void setFirst(int first) {
			this.first = first;
		}

		public int getSecond() {
			return second;
		}

		public void setSecond(int second) {
			this.second = second;
		}
	    

}
