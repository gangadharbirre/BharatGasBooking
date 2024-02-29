package gasBooking;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Delivery extends Booking{
    public String delPersonName;
    int customerOtp;

    public Delivery(String name, String mobile, String street, String area, String pincode, int numberOfCylinders) {
        super(name, mobile, street, area, pincode, numberOfCylinders);
    }
    public void amountCalc(){
        long dayDiff = dt_2.getTime() - dt_1.getTime();
        long newDiff = TimeUnit.DAYS.convert(dayDiff,TimeUnit.MILLISECONDS);

        if(newDiff > 7){
            refund = 41.25;
            amount = amount - refund;
        }
    }

    public void verifyOtp(){
        if(status.equals("B")){
            System.out.println("enter otp:");
            customerOtp = new Scanner(System.in).nextInt();

            if(customerOtp!=otp){
                status = "C";
            }else{
                status = "D";
                System.out.println("Delivered");
            }
        }else{
            System.out.println("No booking found!!!");
        }
    }
    public void delPersonDetails(){
        System.out.println("Enter delivery person name:");
        delPersonName = new Scanner(System.in).nextLine();
    }
}
