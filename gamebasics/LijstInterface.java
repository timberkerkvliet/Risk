package gamebasics;

interface LijstInterface<E extends Comparable> {

    /* Elementen: objecten van het type E
       Structuur: lineair
       Domein:    Het type van alle objecten in de lijst is identiek en de 
		  objecten zijn monotoon niet-dalend gesorteerd.
                  De lijst mag iedere waarde van het type Lijst hebben.
		  Bij iedere niet-lege lijst wijst de referentie 'current'
		  een object uit die lijst aan. Bij de lege lijst wijst
		  current geen object uit de lijst aan.
    */

    /* Opmerking: 
       Indien alle objecten in een lijst l van het type Lijst als argument 
       moeten worden meegegeven aan de methode doeIets(), dan kan dat o.a.
       als volgt:

	for (boolean inList=l.setFirst(); inList; inList=l.getNext()) {
	    doeIets(l.retrieve());
	}
    */

    /* Constructors

    Lijst ();
    PRE  -
    POST - het nieuwe Lijst-object is leeg, d.w.z.: heeft nul elementen

    */

    boolean isEmpty ();
    /* PRE  -
       POST - FALSE: lijst is niet leeg.
	      TRUE:  lijst is leeg.
    */

    Lijst<E> init ();
    /* PRE  -
       POST - lijst-POST is leeg en is geretourneerd.
    */

    int size ();
    /* PRE  -
       POST - Het aantal objecten van de lijst is geretourneerd.
    */

    Lijst<E> insert (E d);
    /* PRE  -
       POST - Aan lijst-PRE is een kopie van d toegevoegd. Dit toegevoegde 
	      object is het current object.
              lijst-POST is geretourneerd.
    */


    E retrieve ();
    /* PRE  - De lijst is niet leeg.
       POST - Een kopie van het current object is geretourneerd.
    */


    Lijst<E> remove ();
    /* PRE  - De lijst is niet leeg.
       POST - Het current object van lijst-PRE zit niet in lijst-POST.
	      De referentie current-POST wijst
	       - als lijst-POST leeg is: 
		     null aan.
	       - anders,
	         als current-PRE het laatste object van lijst-PRE aanwees:
		     het laatste object van lijst-POST aan.
	       - anders: 
		     het object knoop aan, dat in lijst-PRE na het current 
		     object kwam.
              lijst-POST is geretourneerd.
    */


    boolean find (E d);
    /* PRE  - 
       POST - TRUE:  Lijst bevat een kopie van d.
		     Current wijst naar het eerste object in lijst waarvoor 
		     geldt dat dit een kopie van d is.
	      FALSE: Lijst bevat geen kopie van d.
		     current wijst naar
		      - lijst is leeg:
                            null
		      - het eerste object in lijst > d:
                            de eerste knoop
		      - anders:
                            het laatste object in lijst waarvoor geldt < d
    */


    boolean setFirst ();
    /* PRE  - 
       POST - FALSE: lijst is leeg
	      TRUE:  current wijst het eerste object van lijst aan
    */


    boolean setLast ();
    /* PRE  - 
       POST - FALSE: lijst is leeg
	      TRUE:  current wijst het laatste object van lijst aan
    */


    boolean getNext ();
    /* PRE  - 
       POST - FALSE: lijst is leeg of current wijst het laatste object aan
	      TRUE:  current-POST wijst de opvolger van current-PRE aan
    */


    boolean getPrior ();
    /* PRE  - 
       POST - FALSE: lijst is leeg of current wijst het eerste object aan
	      TRUE:  current-POST wijst de voorganger van current-PRE aan
    */

}
