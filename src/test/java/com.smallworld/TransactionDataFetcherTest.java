import com.smallworld.Transaction;
import com.smallworld.TransactionDataFetcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class TransactionDataFetcherTest {

    private TransactionDataFetcher dataFetcher;

    @BeforeEach
    public void setUp() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction(663458, 430.2, "Tom Shelby", 22, "Alfie Solomons", 33, 1, false, "Looks like money laundering"),
                new Transaction(1284564, 150.2, "Tom Shelby", 22, "Arthur Shelby", 60, 2, true, "Never gonna give you up")
        );
        dataFetcher = new TransactionDataFetcher(transactions);
    }

    @Test
    public void testGetTotalTransactionAmount() {
        double totalAmount = dataFetcher.getTotalTransactionAmount();
        assertEquals(580.4, totalAmount);
    }

    @Test
    public void testGetTotalTransactionAmountSentBy() {
        double totalAmountSentByTom = dataFetcher.getTotalTransactionAmountSentBy("Tom Shelby");
        assertEquals(580.4, totalAmountSentByTom);
    }

    @Test
    public void testGetMaxTransactionAmount() {
        double maxAmount = dataFetcher.getMaxTransactionAmount();
        assertEquals(430.2, maxAmount);
    }

    @Test
    public void testCountUniqueClients() {
        long uniqueClients = dataFetcher.countUniqueClients();
        assertEquals(3, uniqueClients);
    }

    @Test
    public void testHasOpenComplianceIssues() {
        boolean hasIssues = dataFetcher.hasOpenComplianceIssues("Alfie Solomons");
        assertTrue(hasIssues);

        boolean noIssues = dataFetcher.hasOpenComplianceIssues("Arthur Shelby");
        assertFalse(noIssues);
    }

    @Test
    public void testGetTransactionsByBeneficiaryName() {
        Map<String, List<Transaction>> transactionsByBeneficiary = dataFetcher.getTransactionsByBeneficiaryName();
        assertEquals(2, transactionsByBeneficiary.size());
        assertTrue(transactionsByBeneficiary.containsKey("Alfie Solomons"));
        assertTrue(transactionsByBeneficiary.containsKey("Arthur Shelby"));
    }

    @Test
    public void testGetUnsolvedIssueIds() {
        Set<Integer> unsolvedIssueIds = dataFetcher.getUnsolvedIssueIds();
        assertEquals(1, unsolvedIssueIds.size());
        assertTrue(unsolvedIssueIds.contains(1));
    }

    @Test
    public void testGetAllSolvedIssueMessages() {
        List<String> solvedIssueMessages = dataFetcher.getAllSolvedIssueMessages();
        assertEquals(1, solvedIssueMessages.size());
        assertTrue(solvedIssueMessages.contains("Never gonna give you up"));
    }

    @Test
    public void testGetTop3TransactionsByAmount() {
        List<Transaction> top3Transactions = dataFetcher.getTop3TransactionsByAmount();
        assertEquals(2, top3Transactions.size());
        assertEquals(430.2, top3Transactions.get(0).getAmount());
        assertEquals(150.2, top3Transactions.get(1).getAmount());
    }

    @Test
    public void testGetTopSender() {
        Optional<String> topSender = dataFetcher.getTopSender();
        assertTrue(topSender.isPresent());
        assertEquals("Tom Shelby", topSender.get());
    }
}
