package homework.hw11;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;
import java.util.function.IntConsumer;

/**
 * 열거형 타입과 함수형 프로그래밍을 이용하여 플레이어의 공격력을 계산하는 알고리즘을 구현하시오.
 *
 * 플레이어 공격력을 계산하는 과정은 아래와 같다.
 * 1. 플레이어의 무기에 따라 공격력이 변화한다. 무기는 최근에 장착한 무기의 공격력 만으로 계산된다.
 *   1-1. BARE_HANDS - 공격력 5
 *   1-2. DAGGER - 공격력 40
 *   1-3. LONG_SWORD - 공격력 100
 *   1-4. DRAGON_SLAYER -  공격력 250
 * 2. 플레이어의 공격력에 영향을 주는 아이템에 따라 공격력 증가 방식이 다르며, 아이템은 중복 적용된다.
 *   2-1. BLACK_POTION - 공격력 10% 증가
 *   2-2. WHITE_POTION - 모든 공격력 계산이 끝난 후에 공격력 + 200
 *   2-3. MUSHROOM - 무기 공격력 + 20
 *
 */

enum Weapon {
    // 무기 구현

    BARE_HANDS(5.0), DAGGER(40.0), LONG_SWORD(100.0), DRAGON_SLAYER(250.0);
    double weaponDamage ;

     Weapon(double weaponDamage){
        this.weaponDamage = weaponDamage;
    }

    public double getWeaponDamage() {
        return weaponDamage;
    }
}

enum Item {
    // 소비 아이템 구현
    BLACK_POTION, WHITE_POTION, MUSHROOM
}

class Player {
    Weapon currentWeapon;
    List<Item> items;

    double weaponDamage;

    double buffWhite;
    double buffBalck;
    double buffMushRoom;


    public Player() {
        currentWeapon = Weapon.BARE_HANDS;
        weaponDamage = getCurrentWeapon().weaponDamage;
        items = new ArrayList<>();
    }

//    public void weaponChange(int i) {
//        switch (i) {
//            case 1 -> currentWeapon = Weapon.BARE_HANDS;
//            case 2 -> currentWeapon = Weapon.DAGGER;
//            case 3 -> currentWeapon = Weapon.LONG_SWORD;
//            case 4 -> currentWeapon = Weapon.DRAGON_SLAYER;
//            default -> System.out.println("Wrong Input");
//        }
//    }


    public void takeItem(String str) {
        switch (str) {
            case "q" -> items.add(Item.BLACK_POTION);
            case "w" -> items.add(Item.WHITE_POTION);
            case "e" -> items.add(Item.MUSHROOM);
            default -> System.out.println("Wrong Input");
        }
}

   public double calcAttack(){

        for (Item item : items ){
            switch (item){
                case MUSHROOM -> buffMushRoom++;
                case BLACK_POTION -> buffBalck++;
                case WHITE_POTION -> buffWhite++;
            }
        }

        return  (weaponDamage+(20.0*buffMushRoom)) * (1.0+(buffBalck/10.0))  + (200.0*buffWhite);

   }

    public void setCurrentWeapon(Weapon currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    public List<Item> getItems() {
        return items;
    }
    // TODO: Player에 필요한 메소드 구현
    // 무기 교체, 아이템 사용, 아이템 효과 종료 메소드 구현
}

public class DamageCalculation {
    public static void main(String[] args) {
        // 무기 및 아이템 장착/사용 시나리오 및 플레이어 공격력 출력

        Player hansol = new Player();

        //무기교체, 1=BareHand, 2 =DAGGER, 3 = LONG_SWORD, 4 =DRAGON_SLAYER

        IntConsumer weaponChange1 = i -> hansol.setCurrentWeapon(i ==1 ? Weapon.BARE_HANDS : hansol.getCurrentWeapon());
        IntConsumer weaponChange2 = i -> hansol.setCurrentWeapon(i ==2 ? Weapon.DAGGER : hansol.getCurrentWeapon());
        IntConsumer weaponChange3 = i -> hansol.setCurrentWeapon(i ==3 ? Weapon.LONG_SWORD : hansol.getCurrentWeapon());
        IntConsumer weaponChange4 = i -> hansol.setCurrentWeapon(i ==4 ? Weapon.DRAGON_SLAYER : hansol.getCurrentWeapon());
        IntConsumer weaponChange1_2 = weaponChange1.andThen(weaponChange2);
        IntConsumer weaponChange3_4 = weaponChange3.andThen(weaponChange4);
        IntConsumer weaponChange = weaponChange1_2.andThen(weaponChange3_4);

        // DAGGER로 교체
        weaponChange.accept(2);

        //아이템 사용, q= BLACK_POTION, w = WHITE_POTION, e = MUSHROOM


        Consumer<String> itemUse = s -> {
            hansol.takeItem(s);
            System.out.println(hansol.getItems().get(hansol.getItems().size()-1) + "을 사용했습니다");
        };
        //itemUse.accept("q");

        //공격력 계산
        DoubleSupplier calAttackPower = hansol::calcAttack;
        DoubleConsumer printAttackPower = (d) -> System.out.println("현재 공격력은 " + d +" 입니다");


    }
}