package mpd;

public class ThreadedMinimumPairwiseDistance implements MinimumPairwiseDistance {
	static int min = Integer.MAX_VALUE;

	@Override
	public int minimumPairwiseDistance(int[] values) {
		/*	if(values.length == 0){
    		return Integer.MAX_VALUE; 
    	}*/

		lowerLeft lowerLeft = new lowerLeft(min, values);
		lowerLeft.run();
		
		lowerRight lowerRight = new lowerRight(min, values);
		lowerRight.run();
		
		middle middle = new middle(min, values);
		middle.run();
		
		upperRight upperRight = new upperRight(min, values);
		upperRight.run();

		return min;

		//throw new UnsupportedOperationException();
	}
	class lowerLeft extends Thread{
		//initialize vars
		private Thread thread; 
		private int localMin;
		private int[] values;

		lowerLeft(int minimum, int[] val){
			localMin = minimum;
			values = val;
		}


		public void run() { 
			int result = Integer.MAX_VALUE;
			for (int i = 0; i < values.length / 2; ++i) {
				for (int j = 0; j < i; ++j) {
					// Gives us all the pairs (i, j) where 0 ≤ j < i < values.length
					int diff = Math.abs(values[i] - values[j]);
					if (diff < result) {
						result = diff;
					}
				}
			}
			if(result < localMin){
				min = result;
			}
		}
	}
	
	class lowerRight extends Thread{
		//initialize vars
		private Thread thread; 
		private int localMin;
		private int[] values;

		lowerRight(int minimum, int[] val){
			localMin = minimum;
			values = val;
		}


		public void run() { 
			int result = Integer.MAX_VALUE;
			
			for (int i = values.length / 2; i < values.length; ++i) {
				for (int j = values.length / 2; j < i; ++j) {
			
			//for (int j = values.length / 2; j < values.length; ++j) {
			//	for (int i = values.length / 2; i < j; ++i) {
					// Gives us all the pairs (i, j) where 0 ≤ j < i < values.length
					int diff = Math.abs(values[j] - values[i]);
					if (diff < result) {
						result = diff;
					}
				}
			}
			if(result < localMin){
				min = result;
			}
		}
	}
	
	class middle extends Thread{
		//initialize vars
		private Thread thread; 
		private int localMin;
		private int[] values;

		middle(int minimum, int[] val){
			localMin = minimum;
			values = val;
		}


		public void run() { 
			int result = Integer.MAX_VALUE;
			for (int i = values.length / 2; i < values.length; ++i) {
				for (int j = 0; j < i; ++j) {
					// Gives us all the pairs (i, j) where 0 ≤ j < i < values.length
					int diff = Math.abs(values[i] - values[j]);
					if (diff < result) {
						result = diff;
					}
				}
			}
			if(result < localMin){
				min = result;
			}
		}
	}
	
	class upperRight extends Thread{
		//initialize vars
		private Thread thread; 
		private int localMin;
		private int[] values;

		upperRight(int minimum, int[] val){
			localMin = minimum;
			values = val;
		}


		public void run() { 
			int result = Integer.MAX_VALUE;
			for (int i = 0; i < values.length / 2; ++i) {
				for (int j = 0; j < i; ++j) {
					// Gives us all the pairs (i, j) where 0 ≤ j < i < values.length
					int diff = Math.abs(values[i] - values[j]);
					if (diff < result) {
						result = diff;
					}
				}
			}
			if(result < localMin){
				min = result;
			}
		}
	}

}


