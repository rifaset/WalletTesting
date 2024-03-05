import Wallet.Wallet;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.HashMap;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WalletTest {

    private static Wallet mine;
    //    TEST PER-METHOD
    @BeforeAll
    public static void InitClass(){
        mine = new Wallet("Rifa");
    }
    @BeforeEach
    public void setUp() {
        // gak perlu diisi apa apa
        System.out.println("Before each setup");
    }

    @AfterEach
    public void tearDown() {
        mine.getCards().clear();
        mine.getMoneys().entrySet().forEach(entry -> {
            entry.setValue(0);
        });
        mine.getCoins().entrySet().forEach(entry -> {
            entry.setValue(0);
        });
    }

    @AfterAll
    public static void cleanMethod(){
        mine = null;
    }

    @Test
    @Order(1)
    void testOwnerWallet(){ // ngecek kalo wallet ada owner
        Assertions.assertNotNull(mine.getOwner(), "Ngecek kalo wallet ada yg punya");
    }

    @Test
    @Order(2)
    void testCheckOwner(){ // ngecek nama owner wallet
        Assertions.assertSame("Rifa", mine.getOwner(), "Nama dari owner mine harusnya Rifa");
    }

    @Test
    @Order(3)
    void testAddCard(){ // ngecek nambah kartu ke wallet
        mine.addCard("BRI");
        mine.addCard("Mandiri");
        System.out.println(mine.getCards());
        Assertions.assertEquals(2, mine.getCards().size(), "Nambahin kartu ke wallet");
    }

    @Test
    @Order(4)
    void testTakeCard(){ // ngecek ngambil kartu dari wallet
        mine.addCard("BRI");
        mine.addCard("Mandiri");
        mine.takeCard("BRI");  // ngehapus kartu BRI
        mine.addCard("BNI");
        System.out.println(mine.getCards());
        Assertions.assertEquals(2, mine.getCards().size(), "Nambahin kartu ke wallet");
    }

    @Test
    @Order(5)
    void testAddMoney(){ // ngecek nambah uang kertas
        mine.addMoney(50000);
        mine.addMoney(10000);
        mine.addMoney(50000);
        mine.addMoney(2000);
        System.out.println(mine.getMoneys());
        Assertions.assertEquals(2, mine.getMoneys().get(50000), "Ngecek jumlah uang 50k an di wallet (haskmap)");
    }


    @Test
    @Order(6)
    void testAddCoin(){ // ngecek nambah uang koin
        mine.addCoin(100);
        mine.addCoin(500);
        mine.addCoin(100);
        mine.addCoin(1000);
        System.out.println(mine.getCoins());
        Assertions.assertEquals(2, mine.getCoins().get(100), "Ngecek jumlah uang koin 100 an di wallet (haskmap)");
    }
    @Test
    @Order(6)
    void testTakeMoney(){ // ngecek ngambil uang kertas
        mine.addMoney(50000);
        mine.addMoney(50000);
        mine.addMoney(10000);
        mine.addMoney(2000);
        mine.takeMoneys(50000);
        System.out.println(mine.getMoneys());
        Assertions.assertEquals(1, mine.getMoneys().get(50000), "Ngecek jumlah uang 50k an di wallet (haskmap)");
    }

    @Test
    @Order(7)
    void testTakeCoin(){ // ngecek ngambil uang koin
        mine.addCoin(100);
        mine.addCoin(500);
        mine.addCoin(100);
        mine.addCoin(1000);
        mine.takeCoins(100);
        System.out.println(mine.getCoins());
        Assertions.assertEquals(1, mine.getCoins().get(100), "Ngecek jumlah uang koin 100 an di wallet (haskmap)");
    }
    @Test
    @Order(8)
    void testCalculateMoneys(){ // ngecek total uang kertas
        mine.addMoney(50000);
        mine.addMoney(10000);
        mine.addMoney(50000);
        mine.addMoney(2000);
        System.out.println(mine.calculateMoneys());
        Assertions.assertEquals(112000, mine.calculateMoneys(), "Ngecek jumlah uang kertas di wallet");
    }

    @Test
    @Order(9)
    void testCalculateCoins(){ // ngecek total uang koin
        mine.addCoin(100);
        mine.addCoin(500);
        mine.addCoin(100);
        mine.addCoin(1000);
        System.out.println(mine.calculateCoins());
        Assertions.assertEquals(1700, mine.calculateCoins(), "Ngecek jumlah uang koin di wallet");
    }

    @Test
    @Order(10)
    void testGetMoneyAvailable(){ // ngecek uang kertas + koin
        mine.addMoney(50000);
        mine.addMoney(10000);
        mine.addCoin(100);
        mine.addCoin(100);
        mine.addCoin(500);
        mine.addCoin(500);
        System.out.println(mine.getMoneyAvailable());
        Assertions.assertEquals(61200, mine.getMoneyAvailable(), "Ngecek jumlah uang di wallet");
    }

    // NYOBA TEST FULL

    @Test
    void testFullProgram(){
        mine.addCard("BRI");
        mine.addCard("Mandiri");
        mine.addMoney(50000);
        mine.addMoney(10000);
        mine.addCoin(100);
        mine.addCoin(100);
        mine.addCoin(500);
        mine.addCoin(500);
        mine.takeCard("BRI");
        mine.takeMoneys(50000);
        mine.takeCoins(100);
        Assertions.assertEquals(1, mine.getCards().size(), "Total kartu di wallet");
        Assertions.assertEquals(0, mine.getMoneys().get(50000), "Ngecek jumlah uang 50k an di wallet (haskmap)");
        Assertions.assertEquals(1, mine.getCoins().get(100), "Ngecek jumlah uang koin 100 an di wallet (haskmap)");

        Assertions.assertEquals(10000, mine.calculateMoneys());
        Assertions.assertEquals(1100, mine.calculateCoins());
        Assertions.assertEquals(11100, mine.getMoneyAvailable(), "Ngecek jumlah uang di wallet");
    }
}
