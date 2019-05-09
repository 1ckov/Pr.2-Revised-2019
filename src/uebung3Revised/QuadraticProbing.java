package uebung3Revised;

public class QuadraticProbing implements Probing {
	
	private int size;
	private int growth;
	private int num;
	private int pos;
	private int sign = -1;
	
	public QuadraticProbing(int size,int pos) {
		this.size = size;
		this.pos = pos;
	}
	
	public QuadraticProbing() {
		this(0,0);
	}

	@Override
	public int nextNum() {
		//The formula here is such that the frog, Jumps to diffrent leaves, but returns after each jump to its original leave.
		if(sign < 0) {
			sign = 1;
			growth++;
			num = (pos + sign*growth*growth) % size;
		}
		else if(sign > 0) {
			sign = -1;
			num = (pos + sign*growth*growth) % size;
		}
		
		if(num < 0) {
			num += size;
		}
		return num;
	}

	@Override
	public int getNum() {
		return num;
	}

	@Override
	public void startProbing() {
		this.growth = 0;
		nextNum();
	}

}
