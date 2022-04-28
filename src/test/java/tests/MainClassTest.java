package tests;

import org.junit.Test;

import main.MainClass;

public class MainClassTest{

    MainClass mMainClass = new MainClass();

    @Test
    public void myFirstTest()
    {
        if(mMainClass.getLocalNumber()==14){
            System.out.println("Это число 14");
        }else {
            System.out.println("Это число не 14 =)");
        }
    }

    @Test
    public void testGetClassNumber()
    {
        if(mMainClass.getClassNumber()>45){
            System.out.println("Это число больше 45");
        }else {
            System.out.println("Это число меньше 45");
        }
    }
    @Test
    public void testGetClassString()
    {
        if(mMainClass.getClassString().contains("hello") || mMainClass.getClassString().contains("Hello")){
            System.out.println("Метод возвращает подстроку hello");
        }else {
            System.out.println("Метод не возвращает подстроку hello");
        }
    }
}
