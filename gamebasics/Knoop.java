package gamebasics;

class Knoop<E extends Comparable>  {

    E data;
    Knoop<E> prior,
             next;
    

    public Knoop (E d) {
	    this(d, null, null);
    }

    public Knoop (E d, Knoop p, Knoop n) {
	    data = d == null ? null : (E) d;
	    prior = p;
	    next = n;
    }


}
