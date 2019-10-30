package DesignPattern.HengYuan;

import java.util.ArrayList;
import java.util.List;

/**
 * 亨元模式
 *
 * 运用共享技术来有效的支持大量细粒度的对象
 */
public class Flyweight {

    private List list = new ArrayList();

    public Characters getCharacter(String name) {
        Characters characters = null;

        for (int i = 0; i < list.size(); i++) {
            characters = (Characters) list.get(i);
            if (name.equals(characters.getName())) {
                System.out.println(name + "不是第一次使用，未分配空间");
                break;
            } else {
                characters = null;
            }
        }

        if (characters == null) {
            characters = new Character(name);
            System.out.println(name + "第一次使用，并分配空间");
            list.add(characters);
        }

        return characters;
    }

}
