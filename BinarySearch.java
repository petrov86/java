public class BinarySearch {

	public static int[] arr = { -2, 1, 2, 3, 4, 5, 6, 7, 8, 9, 15, 16, 17 };

	public static void main(String[] args) {
		int elementToFind = 16;
		int position = binarySearch(elementToFind, 0, arr.length - 1);
		if (position == -1)
			System.out.println("Cannot be found!");
		else
			System.out.println("Position of the element in the Array is -> "
					+ (position + 1));
	}

	public static int binarySearch(int element, int left, int right) {
		int l = left;
		int r = right;
		int middle = (l + r) / 2;
		int count = 1;
		while (l <= r) {
			System.out.println("Iteration: " + count);
			if (element == arr[middle]) {
				return middle;
			} else if (element < arr[middle]) {
				r = middle - 1;
			} else {
				l = l + 1;
			}
			middle = (l + r) / 2;
			count++;
		}
		return -1;
	}
}
