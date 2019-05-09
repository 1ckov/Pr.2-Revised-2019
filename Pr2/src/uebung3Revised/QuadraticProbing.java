package uebung3Revised;

public class QuadraticProbing implements Probing {
	
	private int size;
	private int num;
	private int pos;
	private int sign;
	
	public QuadraticProbing(int size,int pos) {
		this.size = size;
		this.pos = pos;
	}
	
	public QuadraticProbing() {
		this(0,0);
	}

	@Override
	public int nextNum() {
		if(sign == 0) {
			return 0;
		}
		return 0;
	}

	@Override
	public int getNum() {
		return this.num;
	}

	@Override
	public void startProbing() {
		this.num = 0;
		

	}

}
