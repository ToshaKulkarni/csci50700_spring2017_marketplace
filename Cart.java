//Data model Cart
public class Cart {
//Model Cart
private int cartid; //attibute in DB
//Parameterized constructor
public Cart(int cartid, float cartamount) {
	super();
	this.cartid = cartid;
	this.cartamount = cartamount;
}
private float cartamount;
//getters and setters
public int getCartid() {
	return cartid;
}
public void setCartid(int cartid) {
	this.cartid = cartid;
}
public float getCartamount() {
	return cartamount;
}
public void setCartamount(float cartamount) {
	this.cartamount = cartamount;
}
}
