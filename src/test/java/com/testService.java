package com;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



import com.serviceCal;



class testService
{


int a=20,b=10;

serviceCal s = new serviceCal();

@Test
public void testAdd()
{

Assertions.assertEquals(s.Add(a,b), 30);

}

@Test

public void testSub()
{
Assertions.assertEquals(s.Sub(a,b), 10);
}

@Test
public void testMul()
{

Assertions.assertEquals(s.Mul(a,b), 200);
}

@Test
public void testDiv()
{
Assertions.assertEquals(s.Div(a,b), 2);
}

@Test
public void testRem()
{
Assertions.assertEquals(s.Rem(a,b), 0);

}

}