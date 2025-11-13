
public class BinarySearch {
	public static int binarySearch(int[] arr, int target) {
	        int low = 0;
	        int high = arr.length - 1;
	        
	        while (low <= high) {
	            
	            int mid = low + (high - low) / 2; 
	            
	            if (arr[mid] == target) {
	                return mid; 
	            }

	           
	            else if (arr[mid] < target) {
	                low = mid + 1;
	            }

	           
	            else {
	                high = mid - 1;
	            }
	        }

	        
	        return -1;
	    }

	    public static void main(String[] args) {
	        int[] sortedArray = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
	        int targetValue = 23;
	        
	        int index = binarySearch(sortedArray, targetValue);

	        if (index != -1) {
	            System.out.println("Iterative Search: Element " + targetValue + " found at index " + index);
	        } else {
	            System.out.println("Iterative Search: Element " + targetValue + " not found in the array.");
	        }
	    }
	}

