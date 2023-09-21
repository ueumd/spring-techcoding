package func.ueumd.tech.collection;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * List 接口实现类
 * ArrayList    查找快，增删慢     数组
 * LinkedList   增删快，查找慢     链表
 */
public class ArrayListTest {
    @Test
    public void testList() {
        // 存任意类型元素
        ArrayList list = new ArrayList();

        // 只能添加String
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("React");
        list1.add("Vue");
        list1.add("Next.js");

        System.out.println(list1.get(1));

        System.out.println("\n-----------------");

        for (int i =0; i < list1.size(); i ++) {
            System.out.println(list1.get(i));
        }

        System.out.println("\n-----------------");

        // 迭代器遍历
        Iterator<String> it = list1.iterator();
        // System.out.println(it.next());

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Test
    public void toArray() {
        // 只能添加String
        ArrayList<String> list = new ArrayList<>();
        list.add("React");
        list.add("Vue");
        list.add("Next.js");

        Object[] objects = list.toArray();

        for (int i  = 0; i< objects.length;i++) {
            System.out.println(objects[i]);
        }

        String[] strings = list.toArray(new String[0]);
        for (String str: strings) {
            System.out.println(str);
        }
    }

    /**
     * 靠谱
     * 普通 for 循环倒序删除
     */
    @Test
    public void remove3() {
        // 只能添加String
        ArrayList<String> list = new ArrayList<>();
        list.add("React");
        list.add("Vue");
        list.add("Next.js");

        for (int i = list.size() - 1; i > 0; i--) {
            String str = list.get(i);
            if (str.startsWith("V")) {
                list.remove(i);
            }
        }

        System.out.println(list);
    }

    /**
     * 迭代器循环删除（iterator.remove）
     * 靠谱
     */
    @Test
    public void remove4() {
        ArrayList<String> list = new ArrayList<>();
        list.add("React");
        list.add("Vue");
        list.add("Next.js");

        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            String str = iterator.next();
            if (str.contains("u")) {
                iterator.remove();
            }
        }
        System.out.println(list);
    }

    @Test
    public void remove5() {
        ArrayList<String> list = new ArrayList<>();
        list.add("React");
        list.add("Vue");
        list.add("Next.js");

        List<String> newList = list.stream()
                .filter(e -> !"Vue".equals(e))
                .collect(Collectors.toList());

        System.out.println(newList);
    }


    /**
     * Stream 去重
     * @from: https://www.javastack.cn/remove-list-duplicated-elements/
     */
    @Test
    public void remove6() {
        List<String> list = new ArrayList();
        list.add("React");
        list.add("Vue");
        list.add("Vue");
        list.add("Next.js");
        List list2 = list.stream().distinct().collect(Collectors.toList());
        System.out.println(list2);
    }
}
