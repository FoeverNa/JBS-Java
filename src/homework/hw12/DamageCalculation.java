package homework.hw12;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    BARE_HANDS(5), DAGGER(40), LONG_SWORD(100), DRAGON_SLAYER(250);
    int weaponDamage ;

    Weapon(int weaponDamage){
        this.weaponDamage = weaponDamage;
    }

    public int getWeaponDamage() {
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
    int weaponDamage;

    public Player() {
        currentWeapon = Weapon.BARE_HANDS;
        items = new ArrayList<>();
        weaponDamage = currentWeapon.getWeaponDamage();
    }

    void changeWeapon (Weapon weapon){
        currentWeapon = weapon;
        weaponDamage = currentWeapon.getWeaponDamage();
    }

    double calcDamage(){
        Stream<Item> stream = items.stream();
        Map<String, Long> map =  stream.collect(Collectors.groupingBy(s -> s.toString(),Collectors.counting()));
        long mushroom = map.get("MUSHROOM") != null ? map.get("MUSHROOM") : 0;
        long black_potion = map.get("BLACK_POTION") != null ? map.get("BLACK_POTION") : 0;
        long white_potion = map.get("WHITE_POTION") != null ? map.get("WHITE_POTION") : 0;

        return (weaponDamage +(mushroom*20)) * (1 + (black_potion*0.1)) + (white_potion*200);
    }

    void useItem(Item item){
        items.add(item);
    }

    // TODO: Player에 필요한 메소드 구현
    // 무기 교체, 아이템 사용, 아이템 효과 종료 메소드 구현
}

public class DamageCalculation {
    public static void main(String[] args) {
        // 무기 및 아이템 장착/사용 시나리오 및 플레이어 공격력 출력
        Player player = new Player();

        System.out.println(player.calcDamage());
        player.changeWeapon(Weapon.DAGGER);
        System.out.println(player.calcDamage());

        player.useItem(Item.MUSHROOM);
        System.out.println(player.calcDamage());
        player.useItem(Item.BLACK_POTION);
        System.out.println(player.calcDamage());
        player.useItem(Item.WHITE_POTION);
        System.out.println(player.calcDamage());
        player.changeWeapon(Weapon.LONG_SWORD);
        System.out.println(player.calcDamage());

    }
}