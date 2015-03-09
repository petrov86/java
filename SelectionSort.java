class SelectionSort {

	public static void main(String[] arr) {

		int[] array = new int[] { 64, 25, 12, 22, 11, 0, -4, 11 };
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i] > array[j]) // swap items
				{
					array[i] += array[j];
					array[j] = array[i] - array[j];
					array[i] = array[i] - array[j];
				}
			}
		}
		for (int i = 0; i < array.length; i++) // print sorted array
		{
			System.out.println(array[i] + " ");
		}
	}
}
