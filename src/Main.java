import gasBooking.*;

import java.text.SimpleDateFormat;
import java.util.*;

import static gasSupplier.gasAgency.*;

public class Main {
    static int count;
    static int ccount = 0,bcount=0,dcount=0,pcount=0;
    static String dpname;
    public static void cylinderCount(Delivery[] obj){
        String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};
        for(Delivery delivery : obj){
            count=0;
            System.out.println("In the month of "+months[delivery.dt_2.getMonth()]+":");
            System.out.println("In "+delivery.area);
            if(delivery.status.equals("D")){
                count += delivery.numberOfCylinders;
            }
            System.out.println(" - "+count+" cylinders delivered");
        }
        System.out.println("\n");
    }
    public static void checkLateDel(Delivery[] obj){
        String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};
        int[] month = new int[12];
        for(Delivery delivery : obj){
            if(delivery.status.equals("D") && delivery.amount == 783.75){
                month[delivery.dt_2.getMonth()] += 1;
            }
        }
        System.out.println("**************** late delivery ****************");
        for(int i=0;i<12;i++){
            if(month[i]!=0){
                System.out.println(" * In "+months[i]+" there are "+month[i]);
            }
        }
        System.out.println("\n");
    }

    public static void numOfSingleCylinders(Delivery[] obj){
        System.out.println("----------------- single cylinder holders ---------------------");
        for(int i=0;i<obj.length;i++){
            if(obj[i].numberOfCylinders==1){
                System.out.println(" * customer name:"+obj[i].name);
                System.out.println(" * mobile no:"+obj[i].mobile);
                System.out.println(" * gas connection no:"+(i+101));
            }
        }
        System.out.println("\n");
    }
    public static void DeliveryDetails(Delivery[] obj){
        System.out.println("----------------- Delivery Details ---------------------");
        System.out.println("enter the name of delivery person:");
        dpname = new Scanner(System.in).nextLine();
        for(Delivery delivery : obj){
            if(delivery.status.equals("D") && delivery.delPersonName.equals(dpname)){
                System.out.println(" * customer name "+delivery.name);
                System.out.println(" - "+delivery.Street+","+delivery.area+","+delivery.pincode);
            }
        }
        System.out.println("\n");
    }

    public static void printReport(Delivery[] obj){
        System.out.println("-----------Delivery Report----------------");
        for(int i=0;i<obj.length;i++){
            if(obj[i].status.equals("D")){
                dcount++;
            }else if(obj[i].status.equals("B")){
                bcount++;
            }else if(obj[i].status.equals("C")){
                ccount++;
            }else if(obj[i].status.equals("P")){
                pcount++;
            }else{
                System.out.println("status invalid");
            }
        }
        System.out.println("* Booked");
        System.out.println(" - "+bcount+" booked");
        System.out.println("* Delivered");
        System.out.println(" - "+dcount+" delivered");
        System.out.println("* Cancelled");
        System.out.println(" - "+ccount+" cancelled");
        System.out.println("* Pending");
        System.out.println(" - "+pcount+" pending");
        System.out.println("\n");
    }

    public static void printInvoice(Delivery[] obj){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String invoiceDate = sdf.format(d);
        for(int i=0;i<obj.length;i++){
            if(obj[i].status.equals("D")){
                System.out.println("---------------------------------------------------------------");
                System.out.println("                             Invoice                           ");
                System.out.println("----------------------------------------------------------------");
                System.out.println("Gas Agency Code: "+agencyCode+"\t\t\t"+" Date of invoice: "+invoiceDate);
                String phnNumber = "9666594932";
                System.out.println("Gas Agency Name: "+agencyName+"\t\t\t"+" Agency Phone no: "+phnNumber);
                System.out.println("Gas Connection No: "+(i+101)+"\t\t\t"+" customer name: "+obj[i].name);
                System.out.println("Booking date: "+sdf.format(obj[i].dt_1)+"\t\t"+" customer mobile no: "+obj[i].mobile);
                System.out.println("-----------------------------------------------------------------");
                System.out.println("Amount: "+obj[i].amount);
                System.out.println("Refund: "+obj[i].refund);
                System.out.println("Total Amount: "+(obj[i].amount - obj[i].refund));
                System.out.println("------------------------------------------------------------------");
                System.out.println("Delivery Person Name: "+obj[i].delPersonName+"\t\t"+" Delivery person mobile: "+obj[i].DelMobileNo);
                System.out.println("Delivery Date: "+sdf.format(obj[i].dt_2));
                System.out.println("-------------------------------------------------------------------");
                System.out.println("\n\n");
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("********************************************************");
        System.out.println("                  Bharat Gas Agency                      ");
        System.out.println("*********************************************************");
        Delivery[] deliveryObject = new Delivery[5];
        deliveryObject[0] = new Delivery("mani","98979695","library","center","523166",1);
        deliveryObject[1] = new Delivery("balu","1234","ground","right","523166",2);
        deliveryObject[2] = new Delivery("venky","3214","park","left","523166",0);
        deliveryObject[3] = new Delivery("kiran","9897","bus stand","top","523166",3);
        deliveryObject[4] = new Delivery("kanta","9695","temple","bottom","523166",4);

        for(Delivery delivery : deliveryObject){
            delivery.delPersonDetails();
            delivery.getLastDate();
            delivery.getDates();
            delivery.validate();
            delivery.amountCalc();
            delivery.verifyOtp();
        }
        System.out.println();
        cylinderCount(deliveryObject);
        checkLateDel(deliveryObject);
        numOfSingleCylinders(deliveryObject);
        DeliveryDetails(deliveryObject);
        printReport(deliveryObject);
        printInvoice(deliveryObject);
    }
}