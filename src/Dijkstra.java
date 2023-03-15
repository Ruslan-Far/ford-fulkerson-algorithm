public class Dijkstra {

	public static int[][] shortestWay;

	public static void algorithm() {
		int d[] = new int[Main.initialMatrix.length];
		int v[] = new int[d.length];
		int temp, minindex, min;

		for (int i = 0; i < d.length; i++)
		{
			d[i] = Main.INF_MAX;
			v[i] = 1;
		}
		d[Main.s] = 0;
		do {
			minindex = Main.INF_MAX;
			min = Main.INF_MAX;
			for (int i = 0; i < d.length; i++)
			{
				if ((v[i] == 1) && (d[i] < min))
				{
					min = d[i];
					minindex = i;
				}
			}
			if (minindex != Main.INF_MAX)
			{
				for (int i = 0; i < d.length; i++)
				{
					if (Main.initialMatrix[minindex][i] != 0)
					{
						if (Main.initialMatrix[minindex][i] < 0)
							temp = min;
						else
							temp = min + Main.initialMatrix[minindex][i];
						if (temp < d[i])
						{
							d[i] = temp;
						}
					}
				}
				v[minindex] = 0;
			}
		} while (minindex < Main.INF_MAX);
		if (isReachability(d)) {
			restorePath(d);
		}
		else {
			System.out.println("Невозможно добраться от начальной точки до конечной точки");
			shortestWay = null;
		}
	}

	private static boolean isReachability(int[] d) {
		if (d[Main.t] == Main.INF_MAX)
			return false;
		return true;
	}

	private static void restorePath(int[] d) {
		int v[] = new int[d.length];
		int begin = Main.s;
		int end = Main.t;
		v[0] = end;
		int k = 1;
		int weight = d[end];

		while (end != begin)
		{
			for (int i = 0; i < d.length; i++)
				if (Main.initialMatrix[i][end] != 0)
				{
					int temp;

					if (Main.initialMatrix[i][end] < 0)
						temp = weight;
					else
						temp = weight - Main.initialMatrix[i][end];
					if (temp == d[i])
					{
						weight = temp;
						end = i;
						v[k] = end;
						k++;
					}
				}
		}
		shortestWay = new int[k - 1][2];
		System.out.println("Вывод пути");
		for (int i = k - 1; i >= 1; i--) {
			shortestWay[begin][0] = v[i];
			shortestWay[begin][1] = v[i - 1];
			begin++;
		}
		printShortestWay(shortestWay);
	}

	public static void printShortestWay(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.printf("%4d\t", matrix[i][j] + 1);
			}
			System.out.println();
		}
		System.out.println();
	}
}
