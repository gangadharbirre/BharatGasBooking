package gasSupplier;

public interface gasAgency {

    public String agencyName = " Bharat Gas ";

    public int agencyCode = 1234;

    public int phNumber = 783555;

    public int pincode = 533430;

    default void agencyDisplay()
    {
        System.out.println("The agency name is : " + agencyName);
        System.out.println("The agency Code is : " + agencyCode);
        System.out.println("The agency phone Number is : " + phNumber);
    }
}
