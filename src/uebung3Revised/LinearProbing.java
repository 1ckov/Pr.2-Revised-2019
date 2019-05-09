package uebung3Revised;

public class LinearProbing implements Probing {
	
	private int num;
	private int size;
	private int pos;
	private int sign = 1;
	private int prime = 7;
	
	public LinearProbing() {
		this(0,0);
	}
	
	public LinearProbing(int size, int pos) {
		this.size = size;
		this.pos = pos;
	}

	@Override
	public int nextNum() {
//		num += 1;
//		sign *= -1;
//		if(num*sign + pos >= size) {
//			int keep = num * sign + pos;
//			pos = (0 + keep) % size;
//		}
//		else if(num*sign + pos < 0) {
//			int keep = num * sign + pos;
//			pos = (size  - keep) % size;
//			
//		}
		//You can imagine this formula as a frog jumping to diffrent leaves.
		num+=1;
		sign*=-1;
		pos = (pos + num*sign*prime) % size;
		if (pos < 0) {
			pos += size;
		}
		return pos;
	}

	@Override
	public int getNum() {
		return pos;
	}

	@Override
	public void startProbing() {
		num = 0;
		nextNum();
	}
	

}
