package fr.norsys.stringcalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public  class ClazzTest {

    @Test
    public  void test(){
       assertEquals(0,Clazz.Add(""));
    }

    @Test
    public void testOne(){
        assertEquals(6,Clazz.Add("1,2,3"));
    }

   @Test
    public void testTwo(){
       ByteArrayOutputStream a =new  ByteArrayOutputStream();

       System.setErr(new PrintStream(a));

       Clazz.Add("1,2,abc,3");

       System.setErr(System.err);

       String output =a.toString().trim();

       assertEquals("Invalid number: abc",output);

   }

   @Test
    public void testThree(){
        assertEquals(6,Clazz.Add("1\n2,3"));
   }

   @Test
    public void testFour(){
        assertEquals(1,Clazz.Add("1,\n"));
   }

   @Test
    public void testFive(){
        assertEquals(3,Clazz.Add("//;\n1;2"));
    }

    @Test
    public void testSix(){
        try {
            Clazz.Add("-1,-2,-3");
            fail("Expected exception not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Negatives not allowed: -1, -2, -3", e.getMessage());
        }

    }

    @Test
    public void testSeven(){
        Assertions.assertEquals(2,Clazz.Add("2,1001"));
    }

    @Test
    public void testEight(){
        Assertions.assertEquals(6,Clazz.Add("//[***]\n1***2***3"));
    }

    @Test
    public void testNine(){
        Assertions.assertEquals(6,Clazz.Add("//[*][%]\n1*2*3"));
    }


}

