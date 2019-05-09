package uebung2;

public class IntElement implements Element{
	private int value;

	public IntElement(int value){
		this.value = value;
	}
	
	@Override
	public int compareTo(Element e) {
		if(((IntElement)e).getValue() == this.value){
		return 0;
		}
		if(((IntElement)e).getValue() > this.value){
		return -1;
		}
		
		return 1;
		
	}

	@Override
	public Element clone() {
		// TODO Auto-generated method stub
		return new IntElement(value);
	}

	@Override
	public void print() {
		System.out.print(value + " ");
		
	}

	@Override
	public Element getVal() {
		
		return this;
	}
	
	public int getValue(){
		return this.value;
	}

}
