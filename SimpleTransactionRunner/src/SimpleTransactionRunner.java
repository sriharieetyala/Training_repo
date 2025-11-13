import java.io.*;
import java.nio.file.*;
import java.util.*;

public class SimpleTransactionRunner {

    // Where we write/read the raw lines
	private static final Path TRAN_PATH = Paths.get("C:\\Users\\sriha\\eclipse-workspace\\filespractice\\accts.txt");

    public static void main(String[] args) {
        List<String> rawLines = Arrays.asList(
            "Ajay,India,424243443,HDFC00002131,230000,200000,NEFT,James,India,5455535354,HDFC00002301",
            "Ajay,India,424243443,HDFC00002131,230000,200000,NEFT,Roy,India,54555353556,HDFC00002302",
            "Aashish Choudhary",
            "3:27 PM",
            "Rohan,India,424243434,ICICI00002131,20000,20000,NEFT,Kriti,India,5455535358,SBI00002301",
            "Tim,India,424243456,HDFC00002131,230000,100000,NEFT,Praveen,India,54555356454,BOI00002301",
            "Aashish Choudhary",
            "3:28 PM"
        );

        try {
            // 1) ensure folder exists and write the raw file
            Files.createDirectories(TRAN_PATH.getParent());
            Files.write(TRAN_PATH, rawLines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Wrote raw transactions to: " + TRAN_PATH.toAbsolutePath());

            // 2) read and process
            Map<String, Long> balances = new LinkedHashMap<>(); // account -> balance (use long for currency)
            long totalPaidByHDFC = 0L;

            try (BufferedReader br = Files.newBufferedReader(TRAN_PATH)) {
                String line;
                int lineNo = 0;
                while ((line = br.readLine()) != null) {
                    lineNo++;
                    line = line.trim();
                    if (line.isEmpty()) continue;

                    // only process lines that look like CSV transaction lines (contain comma)
                    if (!line.contains(",")) {
                        System.out.println("Line " + lineNo + " (note): " + line);
                        continue;
                    }

                    String[] f = line.split(",", -1);
                    if (f.length != 11) {
                        System.out.println("Line " + lineNo + " - skipping: unexpected field count = " + f.length);
                        continue;
                    }

                    // parse fields (based on your format)
                    String fromName = f[0].trim();
                    // f[1] fromCountry not used for math
                    String fromAcc = f[2].trim();
                    String fromIFSC = f[3].trim();
                    long amount = parseLongSafe(f[4]);
                    long fromBalanceField = parseLongSafe(f[5]); // starting balance for sender as present in CSV
                    // f[6] mode
                    String toName = f[7].trim();
                    // f[8] toCountry
                    String toPhone = f[9].trim();
                    String toIFSC = f[10].trim();

                    // initialize balances map if sender not seen before: use the CSV-provided balance as starting balance
                    balances.putIfAbsent(fromAcc, fromBalanceField);

                    // ensure receiver is present in map (start at 0 if unknown)
                    // we don't have an initial balance for receiver in this CSV format, so start 0 if absent
                    // if later the receiver appears as a sender in any line, its balance will be initialized from that sender line
                    String toAcc = "TO-" + toPhone; // create a unique key for recipient (we don't have recipient acc number in CSV)
                    balances.putIfAbsent(toAcc, 0L);

                    // Now perform transfer rules:
                    // rule1: amount must be > 0
                    // rule2: sender must have >= amount
                    System.out.println("Line " + lineNo + " - Transfer request: " + fromName + "(" + fromAcc + ", " + fromIFSC + ") -> "
                            + toName + "(" + toAcc + ", " + toIFSC + ")  amount=" + amount + "  senderBalance=" + balances.get(fromAcc));

                    if (amount <= 0) {
                        System.out.println("  -> FAILED: amount must be greater than zero.");
                        continue;
                    }

                    long senderBal = balances.get(fromAcc);
                    if (senderBal < amount) {
                        System.out.println("  -> FAILED: insufficient funds (has " + senderBal + ").");
                        continue;
                    }

                    // debit sender, credit receiver
                    balances.put(fromAcc, senderBal - amount);
                    balances.put(toAcc, balances.get(toAcc) + amount);

                    System.out.println("  -> SUCCESS. New sender balance = " + balances.get(fromAcc) + ", receiver balance = " + balances.get(toAcc));

                    // if sender IFSC belongs to HDFC, accumulate total paid by HDFC
                    if (fromIFSC != null && fromIFSC.toUpperCase().contains("HDFC")) {
                        totalPaidByHDFC += amount;
                    }
                }
            }

            // 3) report final balances and HDFC total
            System.out.println("\n=== Final balances ===");
            balances.forEach((acc, bal) -> System.out.println(acc + " => " + bal));

            System.out.println("\nTotal amount successfully paid by HDFC-originated senders: " + totalPaidByHDFC);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long parseLongSafe(String s) {
        try {
            return Long.parseLong(s.trim());
        } catch (Exception e) {
            return 0L;
        }
    }
}
