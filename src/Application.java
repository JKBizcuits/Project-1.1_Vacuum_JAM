/**
 * Application class holds main and creates Vacuum object that runs all the logic with a 4x4 board.
 * @author jacob
 * @version 1.2.3
 */
public class Application {

	public static void main(String[] args) {
		Vacuum vacuum = new Vacuum(new Board(4,4));

	}

}
