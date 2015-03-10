package exc;

import java.util.Arrays;

public class QuickSort {

	public static void main(String[] args) {
		int[] arr = { 100, -1, 25, 40, 0, 6 };
		System.out.println(Arrays.toString(arr));
		quickSort(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));
	}

	public static int partition(int arr[], int left, int right) {
		int i = left, j = right;
		int tmp;
		int middleElement = arr[(left + right) / 2];

		while (i <= j) {

			while (arr[i] < middleElement)
				i++;

			while (arr[j] > middleElement)
				j--;

			if (i <= j) {
				tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
				i++;
				j--;
			}
		}
		return i;
	}

	public static void quickSort(int arr[], int left, int right) {

		int index = partition(arr, left, right);
		if (left < index - 1) {
			quickSort(arr, left, index - 1);
		}

		if (index < right) {
			quickSort(arr, index, right);
		}
	}

}
