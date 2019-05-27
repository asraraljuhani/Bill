/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bill;

/**
 *
 * @author asrar
 */
public class thing {
   private String name;
   private int quantity;
   private double price;
   private double total;
   
    public thing(String nameOfThing,double price,int Quantity){
    this.name=nameOfThing;
   this.quantity= Quantity;
   this.price=price;
   
    }
public thing(){
    this.name="";
   this.quantity= 0;
   this.price=0.0;
   this.total=0.0;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int Quantity) {
        this.quantity = Quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
     public double getTotal() {
        total=this.price*this.quantity;
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
   @Override
    public String toString(){
    return String.format("%10s"+"%10d"+"%10.2f"+"%10.2f",name,quantity,price,getTotal());
    }

   
    
    
}
