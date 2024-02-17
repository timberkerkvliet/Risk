package gamebasics;

import java.io.PrintStream;

class Lijst<E extends Comparable> implements LijstInterface<E>  {
	
	PrintStream out = new PrintStream(System.out);
	
	private int elements;
	
	private Knoop<E> current,
					first,
					last;

	Lijst()  {
		current = null;
		init();
	}

	public Lijst<E> init()  {
		elements = 0;
		return this;
	}

	public boolean isEmpty()  {
		return elements == 0;
	}

	public int size()  {
		return elements;
	}

	public Lijst<E> insert(E d)  {
		if(isEmpty())  {
			current = first = last = new Knoop<E>(d);
		} else if (first.data.compareTo(d)>=0) {
			current = first =  first.prior = new Knoop<E>(d,null,first);
		} else if (last.data.compareTo(d)<=0) {
			current = last = last.next = new Knoop<E>(d,last,null);	
		} else {
			setFirst();
			while (current.data.compareTo(d)<=0) {
				current = current.next;
			}
				Knoop<E> knoop = new Knoop<E>(d,current.prior,current);
				current.prior.next = knoop;
				current.prior = knoop;
				current = knoop;
		}
		elements++;
		return this;
	}

	public Lijst<E> remove()  {
		
		if (current.prior == null)  {
			if(current.next == null){
				current = first = last = null;
			}else{
				current.next.prior = null;
				current = first = current.next;
			}

		} else if (current.next == null)  {
			current.prior.next = null;
			current = last = current.prior;
		}  else {
			current.next.prior = current.prior;
			current.prior.next = current.next;

			current = current.next;
		}
		elements--;
		return this;
	}

	public E retrieve()  {
		if (current==null) {
			return null;	
		}
		return (E)current.data;
	}

	public boolean find(E d)  {
		if(isEmpty())  { 
			return false;
		}
		if (setFirst()) {
			do { 
				if(current.data.compareTo(d) == 0){ 
					return true;
				}
	
				if(current.prior == null && current.data.compareTo(d) > 0){
					return false; 
				}
	
				if(current.data.compareTo(d) > 0){ 
					current = current.prior;
					return false; 
				}
				
			} while(getNext());
		}
		return false;
	}

	public boolean setFirst()  { 
		if(isEmpty())  { 
			return false;
		} 
		while (getPrior()) { }
		return true;
	}

	public boolean setLast()  {
		if(isEmpty())  { 
			return false;
		} 
		while (getNext()) { } 
		return true;
	}

	public boolean getNext()  {
		if(isEmpty() || current.next == null)  {
			return false;
		}
		current = current.next; 
		return true;
	}

	public boolean getPrior()  { 
		if(isEmpty() || current.prior == null)  {
			return false;
		}
		current = current.prior; 
		return true;
	}

}





