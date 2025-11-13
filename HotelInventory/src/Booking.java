public class Booking {
    private static int nextInvoiceNumber = 1001;
    private String customerName;
    private Hotel hotel;
    private Room room;
    private int nights;
    private double totalAmount;
    private int invoiceNumber;

    public Booking(String customerName, Hotel hotel, Room room, int nights) {
        this.customerName = customerName;
        this.hotel = hotel;
        this.room = room;
        this.nights = nights;
        this.totalAmount = room.getPrice() * nights;
        this.invoiceNumber = nextInvoiceNumber++;
    }

    public double getTotalAmount() { return totalAmount; }

    public void generateInvoice() {
        System.out.println("\n\n=============================================");
        System.out.println("            HOTEL BOOKING INVOICE");
        System.out.println("=============================================");
        System.out.printf("INVOICE NO: HBI-%d%n", invoiceNumber);
        System.out.printf("CUSTOMER:   %s%n", customerName.toUpperCase());
        System.out.println("---------------------------------------------");
        System.out.printf("HOTEL:      %s, %s%n", hotel.getName(), hotel.getCity());
        System.out.printf("ROOM TYPE:  %s%n", room.getType());
        System.out.printf("NIGHTS:     %d%n", nights);
        System.out.printf("RATE/NIGHT: $%.2f%n", room.getPrice());
        System.out.println("---------------------------------------------");
        System.out.printf("TOTAL BILL: $%.2f%n", totalAmount);
        System.out.println("=============================================");
    }
}