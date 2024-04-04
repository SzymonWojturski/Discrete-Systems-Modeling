import javax.swing.plaf.IconUIResource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Car {
	private int velocity;
	private int existance;
	private boolean movedAlready;
	public Car() {
		clear();
	}

	public Car makeCopy(){
		Car copy =new Car();
		copy.velocity=this.velocity;
		copy.existance=this.existance;
		copy.movedAlready=this.movedAlready;
		return copy;
	}

	public void clear(){
		this.existance=0;
		this.velocity=0;
		this.movedAlready=false;
	}
	public void clicked() {
		existance=1;
	}

	public void accelerate(){
		if (existance==1 && velocity <5){
			velocity+=1;
		}
	}
	public void slowDown(int x,int y,Car[][] freeway){
		if (existance==1) {
			for (int i = 1; i <= 5; i++) {
				if (freeway[(x + i) % freeway.length][y].getExistance() == 1 && velocity >= i) {
					velocity = i - 1;
					break;
				}
			}
		}
	}
	public void randomize(){
		if (existance==1) {
			Random random=new Random();
			if (random.nextInt(100)<5 && velocity>0){
				velocity-=1;
			}
		}
	}
	public void move(int x,int y,Car[][] freaway) {
		if (existance == 1) {
			//dissapear
			int len = freaway.length;
			if(x+velocity>= len){
				Random random=new Random();
				if(random.nextInt(100)<10){
					freaway[x][y].setExistance(0);
				}
			}

			if (existance == 1 && !movedAlready) {
				freaway[(x + velocity) % len][y] = this.makeCopy();
				freaway[(x + velocity) % len][y].movedAlready = true;
				this.clear();
			}
		}
	}
	public int getVelocity() {
		return velocity;
	}
	public int getExistance() {
		return existance;
	}
	public void setMovedAlready(boolean state){
		movedAlready=state;
	}

	public void setExistance(int newState){
		existance=newState;
	}
	public void setVelocity(int newVel){
		velocity=newVel;
	}

	@Override
	public String toString() {
		return "%d".formatted(velocity);
	}
}
