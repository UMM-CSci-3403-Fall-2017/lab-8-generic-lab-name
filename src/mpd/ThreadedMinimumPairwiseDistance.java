//Travis Warling and Sam Miller

package mpd;

import java.io.IOException;

public class ThreadedMinimumPairwiseDistance implements MinimumPairwiseDistance {
	static int min = Integer.MAX_VALUE;

	@Override
	public int minimumPairwiseDistance(int[] values) {
		
		//Makes sure the global minimum is reset from the last time the program was run
		min = Integer.MAX_VALUE;

		//Creates 4 threads for each section being checked
		Thread lowerLeft = new Thread(new lowerLeft(min, values));
		Thread lowerRight = new Thread(new lowerRight(min, values));
		Thread middle = new Thread(new middle(min, values));
		Thread upperRight = new Thread (new upperRight(min, values));
		
		//Starts each thread
		lowerLeft.start();
		lowerRight.start();
		middle.start();
		upperRight.start();
		
		try{
			
			//Makes sure each thread waits for the rest to finish 
			lowerLeft.join();
			lowerRight.join();
			middle.join();
			upperRight.join();
			
		} catch (InterruptedException fail){
			fail.printStackTrace();
		}
		
		return min;

	}
	
	//Checks if the value passed in is smaller than that of the global min, and changes it accordingly
	public void updateGlobalResult(int challenger){
		if(challenger < min){
			min = challenger;
		}
	}
		
	class lowerLeft implements Runnable{
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
					int diff = Math.abs(values[i] - values[j]);
					if (diff < result) {
						result = diff;
					}
				}
			}
			updateGlobalResult(result);
		}
	}
	
	class lowerRight implements Runnable{
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
					int diff = Math.abs(values[j] - values[i]);
					if (diff < result) {
						result = diff;
					}
				}
			}
			updateGlobalResult(result);
		}
	}
	
	class middle implements Runnable{
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
					int diff = Math.abs(values[i] - values[j]);
					if (diff < result) {
						result = diff;
					}
				}
			}
			updateGlobalResult(result);
		}
	}
	
	class upperRight implements Runnable{
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
					int diff = Math.abs(values[i] - values[j]);
					if (diff < result) {
						result = diff;
					}
				}
			}
			updateGlobalResult(result);
		}
	}

}