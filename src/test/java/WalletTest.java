import Wallet.Wallet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class WalletTest {
    Wallet mine = new Wallet("Rifa");


    //    TEST PER-METHOD

    @Test
    void testOwnerWallet(){ // ngecek kalo wallet ada owner
        Assertions.assertNotNull(mine.getOwner(), "Ngecek kalo wallet ada yg punya");
    }

    @Test
    void testCheckOwner(){ // ngecek nama owner wallet
        Assertions.assertEquals("Rifa", mine.getOwner(), "Nama dari owner mine harusnya Rifa");
    }

    @Test
    void testAddCard(){ // ngecek nambah kartu ke wallet
        mine.addCard("BRI");
        mine.addCard("Mandiri");
        Assertions.assertEquals(2, mine.getCards().size(), "Nambahin kartu ke wallet");
    }

    @Test
    void testTakeCard(){ // ngecek ngambil kartu dari wallet
        mine.addCard("BRI");
        mine.addCard("Mandiri");
        mine.takeCard("BRI");  // ngehapus kartu BRI
        mine.addCard("BNI");
        Assertions.assertEquals(2, mine.getCards().size(), "Nambahin kartu ke wallet");
    }

    @Test
    void testAddMoney(){ // ngecek nambah uang kertas
        mine.addMoney(50000);
        mine.addMoney(10000);
        mine.addMoney(50000);
        mine.addMoney(2000);
        Assertions.assertEquals(2, mine.getMoneys().get(50000), "Ngecek jumlah uang 50k an di wallet (haskmap)");
    }

    @Test
    void testTakeMoney(){ // ngecek ngambil uang kertas
        mine.addMoney(50000);
        mine.addMoney(50000);
        mine.addMoney(10000);
        mine.addMoney(2000);
        mine.takeMoneys(50000);
        Assertions.assertEquals(1, mine.getMoneys().get(50000), "Ngecek jumlah uang 50k an di wallet (haskmap)");
    }

    @Test
    void testAddCoin(){ // ngecek nambah uang koin
        mine.addCoin(100);
        mine.addCoin(500);
        mine.addCoin(100);
        mine.addCoin(1000);
        Assertions.assertEquals(2, mine.getCoins().get(100), "Ngecek jumlah uang koin 100 an di wallet (haskmap)");
    }

    @Test
    void testTakeCoin(){ // ngecek ngambil uang koin
        mine.addCoin(100);
        mine.addCoin(500);
        mine.addCoin(100);
        mine.addCoin(1000);
        mine.takeCoins(100);
        Assertions.assertEquals(1, mine.getCoins().get(100), "Ngecek jumlah uang koin 100 an di wallet (haskmap)");
    }
    @Test
    void testCalculateMoneys(){ // ngecek total uang kertas
        mine.addMoney(50000);
        mine.addMoney(10000);
        mine.addMoney(50000);
        mine.addMoney(2000);
        Assertions.assertEquals(112000, mine.calculateMoneys(), "Ngecek jumlah uang kertas di wallet");
    }

    @Test
    void testCalculateCoins(){ // ngecek total uang koin
        mine.addCoin(100);
        mine.addCoin(500);
        mine.addCoin(100);
        mine.addCoin(1000);
        Assertions.assertEquals(1700, mine.calculateCoins(), "Ngecek jumlah uang koin di wallet");
    }

    @Test
    void testGetMoneyAvailable(){ // ngecek uang kertas + koin
        mine.addMoney(50000);
        mine.addMoney(10000);
        mine.addCoin(100);
        mine.addCoin(100);
        mine.addCoin(500);
        mine.addCoin(500);
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
