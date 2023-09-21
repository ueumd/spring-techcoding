package jdk8;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
 
/**
 * Lambda表达式写法
 *
 * @author gilbert
 */
public class LambdaDemo {
 
    /**
     * 功能描述 无参无返回值
     *
     * @param list
     * @return void
     */
    public static void lambdaWithParamAndNoReturn(List<UserBean> list) {
        list.forEach(userBean -> System.out.println("hello," + userBean.getUsername()));
    }
 
    /**
     *功能描述 重新封装集合数据
     * @param [list]
     * @return void
     */
    public static void initList(List<UserBean> list){
        List<UserBean> userList = list.stream().map(userBean -> new UserBean(userBean.getUserid(), userBean.getWorkid(), userBean.getUsername())).collect(Collectors.toList());
        userList.forEach(userBean -> System.out.println("new list:" + userBean.getUsername()));
    }
 
    /**
     *功能描述 集合过滤
     * @param [list]
     * @return void
     */
    public static void filterList(List<UserBean> list){
        //List<UserBean> filterList = list.stream().filter(userBean -> userBean.getUsername().contains("g")).collect(Collectors.toList());
        List<UserBean> filterList = list.stream().filter(userBean -> Integer.valueOf(userBean.getUserid()) > 6).collect(Collectors.toList());
        filterList.forEach(userBean -> System.out.println("filter list:" + userBean.getUsername()));
    }
 
    /**
     *功能描述 排序
     * @param [list]
     * @return void
     */
    public static void sortList(List<UserBean> list){
        //按userid排序
        List<UserBean> sortList = list.stream().sorted((userBean1,userBean2) -> userBean1.getUsername().compareTo(userBean2.getUsername())).collect(Collectors.toList());
        sortList.forEach(userBean -> System.out.println("sortList:" + userBean.getUserid() + "," + userBean.getUsername()));
    }
 
    /**
     *功能描述 多条件排序
     * @param [list]
     * @return void
     */
    public static void multiSortList(List<UserBean> list){
        list.sort(Comparator.comparing(UserBean::getUserid).thenComparing(UserBean::getUsername));
        list.forEach(userBean -> System.out.println("multiSortList:" + userBean.getUserid() + "," + userBean.getUsername()));
    }
 
    /**
     *功能描述 倒序
     * @param [list]
     * @return void
     */
    public static void reversedSortList(List<UserBean> list){
        //第一种写法
        Comparator<UserBean> comparator = (userBean1,userBean2) -> userBean1.getUsername().compareTo(userBean2.getUsername());
        list.sort(comparator.reversed());
        //第二种写法
        //list.sort(Comparator.comparing(UserBean::getUserid).reversed());
 
        list.forEach(userBean -> System.out.println("reverseSortList:" + userBean.getUserid() + "," + userBean.getUsername()));
    }
 
    /**
     *功能描述 多条件倒序
     * @param [list]
     * @return void
     */
    public static void multiReversedSortList(List<UserBean> list){
        list.sort(Comparator.comparing(UserBean::getUserid).thenComparing(UserBean::getUsername).reversed());
        list.forEach(userBean -> System.out.println("multiReversedSortList:" + userBean.getUserid() + "," + userBean.getUsername()));
    }
 
    /**
     *功能描述 集合分组
     * @param [list]
     * @return void
     */
    public static void groupByList(List<UserBean> list){
        Map<String,List<UserBean>> groupByMap = list.stream().collect(Collectors.groupingBy(UserBean::getWorkid));
        groupByMap.forEach((k,v) -> System.out.println(k+"," + v));
    }
 
    /**
     *功能描述 求和
     * @param [list]
     * @return void
     */
    public static void sumByList(List<UserBean> list){
        System.out.println("sum="+ list.stream().mapToInt(UserBean::getUserid).sum());
    }
 
    /**
     *功能描述 最大值
     * @param [list]
     * @return void
     */
    public static void maxByList(List<UserBean> list){
        OptionalInt optional = list.stream().mapToInt(UserBean::getUserid).max();
        System.out.println("max=" + optional.getAsInt());
    }
 
    /**
     *功能描述 最小值
     * @param [list]
     * @return void
     */
    public static void minByList(List<UserBean> list){
        OptionalInt optional = list.stream().mapToInt(UserBean::getUserid).min();
        System.out.println("min=" + optional.getAsInt());
    }
 
    /**
     *功能描述 平均值

     * @param [list]
     * @return void
     */
    public static void averageByList(List<UserBean> list){
        OptionalDouble optionalDouble = list.stream().mapToInt(UserBean::getUserid).average();
        System.out.println("average=" + optionalDouble.getAsDouble());
    }
 
    /**
     *功能描述 List转map
     * @param [list]
     * @return void
     */
    public static void listToMap(List<UserBean> list){
        //用 (k1,k2)->k1 来设置，如果有重复的key,则保留key1,舍弃key2
        Map<Integer,UserBean> map = list.stream().collect(Collectors.toMap(UserBean::getUserid,userBean -> userBean, (k1, k2) -> k1));
        map.forEach((k,v) -> System.out.println("k=" + k + ",v=" + v));
    }
 
    /**
     *功能描述 map转list
     * @param [map]
     * @return void
     */
    public static void mapToList(Map<Integer,String> map){
        List<UserBean> list = map.entrySet().stream().sorted(Comparator.comparing(key -> key.getKey())).map(key -> new UserBean(Integer.valueOf(key.getKey()),key.getValue(),key.getValue())).collect(Collectors.toList());
        list.forEach(userBean -> System.out.println(userBean.getUserid() + "," + userBean.getUsername()));
    }
 
    /**
     *功能描述 字符串转list
     * @param [str]
     * @return void
     */
    public static void stringToList(String str){
        //不需要处理
        //<String> list = Arrays.asList(str.split(","));
        //需要处理
        List<String> list = Arrays.asList(str.split(",")).stream().map(string -> String.valueOf(string)).collect(Collectors.toList());
        list.forEach(string -> System.out.println(string));
    }
 
    /**
     *功能描述 姓名以逗号拼接
     * @param [list]
     * @return void
     */
    public static void joinStringValueByList(List<UserBean> list){
        System.out.println(list.stream().map(UserBean::getUsername).collect(Collectors.joining(",")));
    }
 
    /**
     *功能描述 分组统计
     * @param [list]
     * @return void
     */
    public static void countByList(List<UserBean> list){
        Map<String, Long> map = list.stream().collect(Collectors.groupingBy(UserBean::getWorkid,Collectors.counting()));
        map.forEach((k,v) -> System.out.println("key=" + k + ",value=" + v));
    }
 
    public static void main(String[] args) {
        //List<UserBean> list = Arrays.asList(new UserBean(1, "AKB001", "gilbert"), new UserBean("2", "AKB002", "apple"), new UserBean("3", "AKB003", "cat"));
        List<UserBean> list = Stream.of(new UserBean(1, "AKB001", "gilbert"),
                                        new UserBean(2, "AKB002", "apple"),
                                        new UserBean(4, "AKB004", "dog"),
                                        new UserBean(5, "AKB005", "egg"),
                                        new UserBean(6, "AKB006", "frog"),
                                        new UserBean(6, "AKB006", "banana"),
                                        new UserBean(7, "AKB007", "google"),
                                        new UserBean(3, "AKB003", "cat"))
                .collect(Collectors.toList());
        lambdaWithParamAndNoReturn(list);
        initList(list);
        filterList(list);
        sortList(list);
        reversedSortList(list);
        multiSortList(list);
        multiReversedSortList(list);
        groupByList(list);
        sumByList(list);
        maxByList(list);
        minByList(list);
        averageByList(list);
        listToMap(list);
        String str = "apple,banana,cat,dog";
        stringToList(str);
        Map<Integer,String> map =new HashMap<Integer, String>() {
            {
                put(1, "apple");
                put(2, "banana");
                put(3, "cat");
                put(4, "dog");
                put(5, "frog");
            }
        };
        mapToList(map);
        joinStringValueByList(list);
        countByList(list);
    }
}