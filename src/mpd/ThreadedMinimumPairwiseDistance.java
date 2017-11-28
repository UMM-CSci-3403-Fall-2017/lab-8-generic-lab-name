package mpd;

import java.io.IOException;

public class ThreadedMinimumPairwiseDistance implements MinimumPairwiseDistance {
	static int min = Integer.MAX_VALUE;

	@Override
	public int minimumPairwiseDistance(int[] values) {
		/*	if(values.length == 0){
    		return Integer.MAX_VALUE; 
    	}*/

		Thread lowerLeft = new Thread(new lowerLeft(min, values));
		Thread lowerRight = new Thread(new lowerRight(min, values));
		Thread middle = new Thread(new middle(min, values));
		Thread upperRight = new Thread (new upperRight(min, values));
		
		min = Integer.MAX_VALUE;
		
		lowerLeft.start();
		lowerRight.start();
		middle.start();
		upperRight.start();
		
		try{
			lowerLeft.join();
			lowerRight.join();
			middle.join();
			upperRight.join();
			
		} catch (InterruptedException fail){
			fail.printStackTrace();
		}
		System.out.println("This is the global min: " + min);
		//System.out.println("This is the number returned: " + min);
		return min;

		//throw new UnsupportedOperationException();
	}
	
	public void updateGlobalResult(int challenger){
		if(challenger < min){
			min = challenger;
		}
	}
	class lowerLeft implements Runnable{
		//initialize vars
		private int localMin;
		private int[] values;

		lowerLeft(int minimum, int[] val){
			localMin = minimum;
			values = val;
		}


		public void run() { 
			int result = Integer.MAX_VALUE;
			for (int i = 0; i < values.length / 2; i++) {
				for (int j = 0; j < i; j++) {
					// Gives us all the pairs (i, j) where 0 ≤ j < i < values.length
					int diff = Math.abs(values[i] - values[j]);
					if (diff < result) {
						result = diff;
					}
				}
			}
			//System.out.println("This is what the lower left sends: " + result);
			updateGlobalResult(result);
		}
	}
	
	class lowerRight implements Runnable{
		//initialize vars 
		private int localMin;
		private int[] values;

		lowerRight(int minimum, int[] val){
			localMin = minimum;
			values = val;
		}


		public void run() { 
			int result = Integer.MAX_VALUE;
			
			for (int i = (values.length / 2) + 1;  i < values.length; ++i) {
				for (int j = 0; j < i - (values.length / 2); ++j) {
					// Gives us all the pairs (i, j) where 0 ≤ j < i < values.length
					int diff = Math.abs(values[j] - values[i]);
					if (diff < result) {
						result = diff;
					}
				}
			}
			
			//System.out.println("This is what the lower right sends: " + result);
			updateGlobalResult(result);
		}
	}
	
	class middle implements Runnable{
		//initialize vars
		private int localMin;
		private int[] values;

		middle(int minimum, int[] val){
			localMin = minimum;
			values = val;
		}


		public void run() { 
			int result = Integer.MAX_VALUE;
			for (int j = 0; j < values.length / 2; j++) {
				for (int i = (values.length / 2); i < j + (values.length / 2); i++) {
					// Gives us all the pairs (i, j) where 0 ≤ j < i < values.length
					int diff = Math.abs(values[i] - values[j]);
					if (diff < result) {
						result = diff;
					}
				}
			}
			//System.out.println("This is what the middle sends: " + result);
			updateGlobalResult(result);
		}
	}
	
	class upperRight implements Runnable{
		//initialize vars
		private int localMin;
		private int[] values;

		upperRight(int minimum, int[] val){
			localMin = minimum;
			values = val;
		}


		public void run() { 
			int result = Integer.MAX_VALUE;
			for (int i = (values.length / 2) + 1; i < values.length; ++i) {
				for (int j = values.length / 2; j < i; ++j) {
					// Gives us all the pairs (i, j) where 0 ≤ j < i < values.length
					int diff = Math.abs(values[i] - values[j]);
					if (diff < result) {
						result = diff;
					}
				}
			}
			
			//System.out.println("This is what the upper right sends: " + result);
			updateGlobalResult(result);
		}
	}

}


