
package app;

import java.math.BigDecimal;
import java.util.List;


import exception.InvalidBillException;
import model.Bill;
import service.BillService;
import serviceimpl.BillServiceImpl;
import util.InputUtil;

public class BillingMenu {

    private BillService billService;

    public BillingMenu() {
        billService = new BillServiceImpl();
    }

    
    // BILLING MENU
    // =========================================
    public void showMenu() {

        while (true) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("            BILLING MANAGEMENT");
            System.out.println("========================================");
            System.out.println("1. Generate Bill");
            System.out.println("2. View All Bills");
            System.out.println("3. Search Bill");
            System.out.println("4. Search Bill by Appointment");
            System.out.println("5. Update Payment Status");
            System.out.println("6. Back");
            System.out.println("========================================");

            int choice =
                    InputUtil.getInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    generateBill();
                    break;

                case 2:
                    viewAllBills();
                    break;

                case 3:
                    searchBill();
                    break;

                case 4:
                    searchBillByAppointment();
                    break;

                case 5:
                    updatePaymentStatus();
                    break;

                case 6:
                    return;

                default:
                    System.out.println(
                            "Invalid choice. Please try again.");
            }
        }
    }

    // 1. GENERATE BILL
    private void generateBill() 
    {

        try {

            Bill bill = new Bill();

            bill.setAppointmentId(InputUtil.getInt("Enter appointment ID: "));

            bill.setConsultationFee(InputUtil.getBigDecimal("Enter consultation fee: "));

            bill.setMedicineCharge(InputUtil.getBigDecimal("Enter medicine charge: "));

            bill.setLabCharge(InputUtil.getBigDecimal("Enter lab charge: "));

            bill.setPaymentStatus("PENDING");

            boolean result =billService.addBill(bill);

            if (result) 
            {
                System.out.println();
                System.out.println("Bill generated successfully.");

                System.out.println("Total Amount: ₹"+ bill.getTotalAmount());

                System.out.println("Payment Status: "+ bill.getPaymentStatus());

            } else 
            {

                System.out.println("Bill generation failed.");
            }

        } catch (InvalidBillException e) {

            System.out.println("Billing Error: " + e.getMessage());

        } catch (Exception e) 
        {

            System.out.println("Unable to generate bill: " + e.getMessage());
        }
    }
    // 2. VIEW ALL BILLS

    private void viewAllBills() 
    {

        List<Bill> bills = billService.getAllBills();

        if (bills.isEmpty()) 
        {

            System.out.println("No bills found.");

            return;
        }

        System.out.println();
        System.out.println("========================================");
        System.out.println("              ALL BILLS");
        System.out.println("========================================");

        for (Bill bill : bills) 
        {

            System.out.println(bill);
        }
    }

    // 3. SEARCH BILL
    private void searchBill() {

        try {

            int billId =InputUtil.getInt("Enter bill ID: ");

            Bill bill =billService.getBillById(billId);

            if (bill != null) 
            {

                System.out.println();
                System.out.println("Bill Found:");

                System.out.println(bill);

            } else {

                System.out.println("Bill not found.");
            }

        } catch (Exception e) {

            System.out.println("Unable to search bill: "+ e.getMessage());
        }
    }

    // 4. SEARCH BILL BY APPOINTMENT

    private void searchBillByAppointment() {

        try {

            int appointmentId =InputUtil.getInt("Enter appointment ID: ");

            Bill bill =billService.getBillByAppointmentId(appointmentId);

            if (bill != null) 
            {

                System.out.println();
                System.out.println("Bill Found:");

                System.out.println(bill);

            } else {

                System.out.println("No bill found for appointment ID: "+ appointmentId);
            }

        } catch (Exception e) {

            System.out.println("Unable to search bill: "+ e.getMessage());
        }
    }

    // 5. UPDATE PAYMENT STATUS

    private void updatePaymentStatus() {

        try {

            int billId =InputUtil.getInt("Enter bill ID: ");

            Bill bill =billService.getBillById(billId);

            if (bill == null) {

                System.out.println("Bill not found.");

                return;
            }

            System.out.println();
            System.out.println("Current Bill Details:");

            System.out.println(bill);

            String paymentStatus =InputUtil.getString("Enter payment status (PENDING/PAID): ");

            boolean result =billService.updatePaymentStatus(billId,paymentStatus);

            if (result) {

                System.out.println("Payment status updated successfully.");

            } else {

                System.out.println("Payment status update failed.");
            }

        } catch (InvalidBillException e) {

            System.out.println("Billing Error: " + e.getMessage());

        } catch (Exception e) {

            System.out.println("Unable to update payment status: "+ e.getMessage());
        }
    }
}
