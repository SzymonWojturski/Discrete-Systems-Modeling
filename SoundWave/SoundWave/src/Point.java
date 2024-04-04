public class Point {

	private Point nNeighbor;
	private Point wNeighbor;
	private Point eNeighbor;
	private Point sNeighbor;
	private float nVel;
	private float eVel;
	private float wVel;
	private float sVel;
	private float pressure;
	private int sinInput;

	private static Integer []types ={0,1,2};
	int type;

	public Point() {
		sinInput=0;
		clear();
	}

	public void addNeighbors(Point north,Point east,Point south,Point west){
		nNeighbor=north;
		eNeighbor=east;
		sNeighbor=south;
		wNeighbor=west;
	}
	public void clicked() {
		pressure = 1;
	}

	public void clear() {
		nVel=0;
		eVel=0;
		sVel=0;
		wVel=0;
		pressure=0;
		type=0;
	}

	public void updateVelocity() {
		if (type==0) {
			nVel = nVel - (nNeighbor.getPressure() - pressure);
			eVel = eVel - (eNeighbor.getPressure() - pressure);
			wVel = wVel - (wNeighbor.getPressure() - pressure);
			sVel = sVel - (sNeighbor.getPressure() - pressure);
		}
	}

	public void updatePresure() {
		if (type==0) {
			pressure = pressure - (eVel + sVel + nVel + wVel) / 2;
		}

		if (type==2){
			double radians = Math.toRadians(sinInput);
			pressure = (float) (Math.sin(radians));
		}
	}

	public float getPressure() {
		return pressure;
	}

	public static Integer[] getTypes(){
		return types;
	}

	public void addSinInput(int numberToAdd){
		sinInput+=numberToAdd;
	}
}