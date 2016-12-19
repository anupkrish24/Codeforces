import java.util.InputMismatchException;
import java.math.BigInteger;
import java.io.*;
import java.util.*;

/**
 * Generated by Contest helper plug-in
 * Actual solution is at the bottom
 */
public class Problem3Contest387 {
	public static void main(String[] args) {
		InputReader in = new StreamInputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		run(in, out);
	}

	public static void run(InputReader in, PrintWriter out) {
		Solver solver = new Task();
		solver.solve(1, in, out);
		Exit.exit(in, out);
	}
}

abstract class InputReader {
	private boolean finished = false;

	public abstract int read();

	public int readInt() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		int res = 0;
		do {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = read();
		} while (!isSpaceChar(c));
		return res * sgn;
	}

	public String readString() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		StringBuffer res = new StringBuffer();
		do {
			res.appendCodePoint(c);
			c = read();
		} while (!isSpaceChar(c));
		return res.toString();
	}

	private boolean isSpaceChar(int c) {
		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public abstract void close();
}

class StreamInputReader extends InputReader {
	private InputStream stream;
	private byte[] buf = new byte[1024];
	private int curChar, numChars;

	public StreamInputReader(InputStream stream) {
		this.stream = stream;
	}

	public int read() {
		if (numChars == -1)
			throw new InputMismatchException();
		if (curChar >= numChars) {
			curChar = 0;
			try {
				numChars = stream.read(buf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (numChars <= 0)
				return -1;
		}
		return buf[curChar++];
	}

	public void close() {
		try {
			stream.close();
		} catch (IOException ignored) {
		}
	}
}

class Exit {
	private Exit() {
	}

	public static void exit(InputReader in, PrintWriter out) {
		in.setFinished(true);
		in.close();
		out.close();
	}
}

interface Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out);
}

class GeometryUtils {
	public static double epsilon = 1e-8;

	}

class Task implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int numServ = in.readInt();
		int numTasks = in.readInt();
		int[] servers = new int[numServ];
		for (int i = 0; i < numTasks; i++) {
			int time = in.readInt();
			int serv = in.readInt();
			int taskTime = in.readInt();
			int ans = -1;
			int k = 0;
			List<Integer> indices = new LinkedList<Integer>();
			for (int j = 0; j < numServ; j++) {
				if (servers[j] <= time) {
					indices.add(j);
					k++;
					if (k == serv) {
						break;
					}
				}
			}
			if (k < serv) {
				out.println(ans);
			} else {
				int sum = 0;
				for (int n = 0; n < indices.size(); n++) {
					int indx = indices.get(n);
					sum += (indx + 1);
					servers[indx] = time + taskTime;
				}
				ans = sum;
				out.println(ans);
			}

		}
	}
}