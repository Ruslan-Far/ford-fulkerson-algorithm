public class Main {

	public static final int INF_MAX = 999999999;
	public static final int INF_MIN = -999999999;

	public static int[][] initialMatrix = { // пропускная способность
			{0, 5, 6, 5, 0, 0, 0},
			{0, 0, 2, 0, 3, 0, 0},
			{0, 2, 0, 3, 3, 7, 0},
			{0, 0, 0, 0, 0, 5, 0},
			{0, 0, 0, 0, 0, 1, 8},
			{0, 0, 0, 0, 1, 0, 7},
			{0, 0, 0, 0, 0, 0, 0}
	};

	private static int[][] f; // запущенные потоки
	public static int s = 0; // начало движения
	public static int t = 6; // конец движения

	public static void main(String[] args) {
		int[][] copyInitialMatrix;
		int minFlowPower;
		int sumMinFlowPower;
		int count;

		copyInitialMatrix = getCopyInitialMatrix();
		sumMinFlowPower = 0;
		count = 0;
		while (true) {
			System.out.println(++count + " итерация");
			Dijkstra.algorithm();
			if (Dijkstra.shortestWay == null)
				break;
			minFlowPower = getMinFlowPower();
			System.out.println("Минимальная мощность потока на данном пути = " + minFlowPower);
			sumMinFlowPower += minFlowPower;
			redraw(minFlowPower);
			System.out.println("Пропускная способность");
			CommonFunctions.printMatrix(initialMatrix);
			System.out.println("\n\n\n");
		}
		getAnswer(copyInitialMatrix, sumMinFlowPower);
	}

	private static int[][] getCopyInitialMatrix() {
		int[][] copyInitialMatrix;

		copyInitialMatrix = new int[initialMatrix.length][initialMatrix[0].length];
		for (int i = 0; i < copyInitialMatrix.length; i++) {
			for (int j = 0; j < copyInitialMatrix[i].length; j++) {
				copyInitialMatrix[i][j] = initialMatrix[i][j];
			}
		}
		return copyInitialMatrix;
	}

	private static int getMinFlowPower() {
		int min;
		int[] coords;

		min = INF_MAX;
		for (int i = 0; i < Dijkstra.shortestWay.length; i++) {
			coords = Dijkstra.shortestWay[i];
			if (initialMatrix[coords[0]][coords[1]] < min) {
				min = initialMatrix[coords[0]][coords[1]];
			}
		}
		return min;
	}

	private static void redraw(int minFlowPower) {
		int[] coords;

		for (int i = 0; i < Dijkstra.shortestWay.length; i++) {
			coords = Dijkstra.shortestWay[i];
			initialMatrix[coords[0]][coords[1]] -= minFlowPower;
			initialMatrix[coords[1]][coords[0]] += minFlowPower;
		}
	}

	private static void getAnswer(int[][] copyInitialMatrix, int sumMinFlowPower) {
		initF(copyInitialMatrix);
		System.out.println("Матрица запущенных потоков");
		CommonFunctions.printMatrix(f);
		System.out.println("\nМаксимальный поток через систему = " + sumMinFlowPower);
	}

	private static void initF(int[][] copyInitialMatrix) {
		f = new int[initialMatrix.length][initialMatrix[0].length];
		for (int i = 0; i < f.length; i++) {
			for (int j = 0; j < f[i].length; j++) {
				if (copyInitialMatrix[i][j] > initialMatrix[i][j])
					f[i][j] = copyInitialMatrix[i][j] - initialMatrix[i][j];
			}
		}
	}
}
