package domain;

import java.util.*;

/**
 * @Author: 轩轩
 * @Date: 2020/2/22 18:12
 * @description: 用于spring复杂类型注入示例
 */
public class UserDetail {
    private List<Integer> myList;
    private int[] myArray;
    private Map myMap;
    private Set mySet;
    private Properties myPro;

    public List getMyList() {
        return myList;
    }

    public void setMyList(List myList) {
        this.myList = myList;
    }

    public int[] getMyArray() {
        return myArray;
    }

    public void setMyArray(int[] myArray) {
        this.myArray = myArray;
    }

    public Map getMyMap() {
        return myMap;
    }

    public void setMyMap(Map myMap) {
        this.myMap = myMap;
    }

    public Set getMySet() {
        return mySet;
    }

    public void setMySet(Set mySet) {
        this.mySet = mySet;
    }

    public Properties getMyPro() {
        return myPro;
    }

    public void setMyPro(Properties myPro) {
        this.myPro = myPro;
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "myList=" + myList +
                ", myArray=" + Arrays.toString(myArray) +
                ", myMap=" + myMap +
                ", mySet=" + mySet +
                ", myPro=" + myPro +
                '}';
    }
}
