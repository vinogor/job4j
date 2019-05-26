public class AlarmClocks {

	public static void main(String[] args) {

//		int[] timeToRing = new int[]{1, 2, 3, 4, 5, 6};
//		int[] nxk = new int[] {6, 5, 10};

		int[] nxk = new int[] {5, 7, 12};
		int[] timeToRing = new int[]{5, 22, 17, 13, 8};

		int numOfClocks = nxk[0];
		int period = nxk[1];
		int alarmsToWakeUp = nxk[2];

		int k = 0; // сколько раз звонил будильник
		int i = 0; // текущий период времени

		do {
			i++;
			for (int j = 0; j < numOfClocks; j++) {
				if (((i - timeToRing[j]) >= 0) && ((i - timeToRing[j]) % period == 0)) {
					k++;
					break; // одновременные срабатывания = 1 срабатывание
				}
			}
		} while (k != alarmsToWakeUp);
		System.out.println(i);
	}
}
