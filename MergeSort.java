import java.util.Arrays;

public class MergeSort {
	private int[] numbers;
	private int[] tempArr;
	int[] myArr = { 100, 1, 2, -5, 30, 15, 20, 0 };
	private int length;
	int iteration = 0;

	public void sort(int[] values) {
		this.numbers = values;
		length = values.length;
		this.tempArr = new int[length];
		mergesort(0, length - 1);
	}

	private void mergesort(int low, int high) {
		// check if low is smaller then high, if not then the array is sorted

		if (low < high) {
			// Get the index of the element which is in the middle
			// int middle = low + (high - low) / 2;
			int middle = (low + high) / 2;
			// Sort the left side of the array
			mergesort(low, middle);
			// Sort the right side of the array
			mergesort(middle + 1, high);
			// Combine them both
			merge(low, middle, high);
		}
	}

	private void merge(int low, int middle, int high) {

		System.out.println("Iteration " + ++iteration);
		System.out.println("Middle " + middle);
		System.out.println("Low " + low);
		System.out.println("High " + high);

		// Copy both parts into the helper array
		for (int i = low; i <= high; i++) {
			tempArr[i] = numbers[i];
		}

		int i = low;
		int j = middle + 1;
		int k = low;
		// Copy the smallest values from either the left or the right side back
		// to the original array
		while (i <= middle && j <= high) {
			if (tempArr[i] <= tempArr[j]) {
				numbers[k] = tempArr[i];
				i++;
			} else {
				numbers[k] = tempArr[j];
				j++;
			}
			k++;
		}
		// Copy the rest of the left side of the array into the target array
		while (i <= middle) {
			numbers[k] = tempArr[i];
			k++;
			i++;
		}
		System.out.println("My Array " + Arrays.toString(myArr));
		System.out.println("Temp Arr " + Arrays.toString(tempArr));
		System.out
				.println("===================================================");
	}

	public static void main(String[] args) {

		MergeSort instance = new MergeSort();
		System.out.println(Arrays.toString(instance.myArr));
		instance.sort(instance.myArr);
		System.out
				.println("SORTED ARRAY is " + Arrays.toString(instance.myArr));

	}
}
