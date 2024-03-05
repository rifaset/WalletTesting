package Wallet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Wallet {
    String owner;
    List<String> cards;
    HashMap<Integer, Integer> moneys;
    HashMap<Integer, Integer> coins;
    int[] coinTypes = {100, 500, 1000};
    int[] moneyTypes = {1000, 2000, 5000, 10000, 20000, 50000, 100000};

    //    Constructor
    public Wallet(String owner) {
        this.owner = owner;
        this.coins = new HashMap<Integer, Integer>();
        for(int i=0; i<coinTypes.length; i++){
            this.coins.put(coinTypes[i], 0);
        }

        this.moneys = new HashMap<Integer, Integer>();
        for(int i=0; i<moneyTypes.length; i++){
            this.moneys.put(moneyTypes[i], 0);
        }

        this.cards = new ArrayList<String>();
    }

    //    Getter Setter
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public List<String> getCards() {
        return cards;
    }
    public HashMap<Integer, Integer> getMoneys() {
        return moneys;
    }
    public HashMap<Integer, Integer> getCoins() {
        return coins;
    }

    //    Methods
    public void addCard(String card) {
        this.cards.add(card);
    }
    public void takeCard(String card) {
        this.cards.remove(card);
    }
    public void addMoney(Integer moneyType) {
        boolean isMoneyValid = false;

        for(int i=0; i<moneyTypes.length; i++){ // dicek satuan uang kertasnya
            if(moneyType==moneyTypes[i]){
                isMoneyValid = true;
            }
        }
        if(isMoneyValid){
            int initialValue = this.moneys.get(moneyType); // untuk mengambil nilai awal dari uang kertas
            this.moneys.put(moneyType, initialValue+1); // untuk menambahkan value ke uang kertas
        }
    }
    public void addCoin(Integer moneyType) { // sama dengan addMoney tapi bwt coin
        boolean isMoneyValid = false;

        for(int i=0; i<coinTypes.length; i++){
            if(moneyType==coinTypes[i]){
                isMoneyValid = true;
            }
        }
        if(isMoneyValid){
            int initialValue = this.coins.get(moneyType);
            this.coins.put(moneyType, initialValue+1);
        }
    }
    public void takeCoins(Integer coinType){ // untuk ngambil coin
        int initialValue = this.coins.get(coinType);
        this.coins.put(coinType, initialValue-1);
    }
    public void takeMoneys(Integer moneyType){ // untuk ngambil uang kertas
        int initialValue = this.moneys.get(moneyType);
        this.moneys.put(moneyType, initialValue-1);
    }
    public int getMoneyAvailable(){
        int total = this.calculateCoins() + this.calculateMoneys();
        return total;
    }
    public int calculateMoneys() {
        int total = 0;
        for (Integer key : this.moneys.keySet()){ // untuk mengambil key dari moneys karena pakai hasmap (dictionary)
            total += key * this.moneys.get(key); // untuk mengambil value dari moneys
        }
        return total;
    }
    public int calculateCoins(){
        int total = 0;
        for (Integer key : this.coins.keySet()){
            total += key * this.coins.get(key);
        }
        return total;
    }
}