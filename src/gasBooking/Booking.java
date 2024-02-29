package gasBooking;
import Customers.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Booking extends GasConnection{
    public double otp=5678,amount=825.0,refund=0;
    public String dt,delDate,DelMobileNo="123456",status;
    public Date dt_1,dt_2;
    public Booking(String name, String mobile, String street, String area, String pincode, int numberOfCylinders) {
        super(name, mobile, street, area, pincode, numberOfCylinders);
    }
    public void getDates(){
        System.out.println("Enter Booking Date:");
        dt = new Scanner(System.in).nextLine();
        dt_1 = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try{
            dt_1 = dateFormat.parse(dt);
        }catch(Exception e){
            System.out.println("error in getDates function:"+e);
        }
        //delivery details
        System.out.println("Enter delivery date:");
        delDate = new Scanner(System.in).nextLine();
        try{
            dt_2 = dateFormat.parse(delDate);
        }catch(Exception exp){
            System.out.println("Error in parsing second date"+exp);
        }

        try {
            long difference = dt_2.getTime() - dt_1.getTime();

            long newdifference = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
            if (newdifference > 7) {
                status = "P";
            }
        }catch(Exception e){
            System.out.println("Error while finding difference:"+e);
        }
    }
    public void validate(){
        Date lastDate = new Date();
        long elapsedms = dt_1.getTime() - lastDate.getTime();
        long diff = TimeUnit.DAYS.convert(elapsedms,TimeUnit.MILLISECONDS);

        System.out.println("difference between two dates is:"+diff);
        int numberOfCylinders = 1;
        if(numberOfCylinders == 1){
            if(diff<30){
                System.out.println("booking can't be done");
                status = "C";
            }else{
                status = "B";
                lastDate = dt_1;
            }
        }else if(numberOfCylinders == 2){
            if(diff<50){
                System.out.println("booking can't be done");
                status = "C";
            }else{
                status = "B";
                lastDate = dt_1;
            }
        }
    }
}
