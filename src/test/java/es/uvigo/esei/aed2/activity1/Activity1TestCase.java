package es.uvigo.esei.aed2.activity1;

/*-
 * #%L
 * AEDII - Activities
 * %%
 * Copyright (C) 2025 Rosalía Laza Fidalgo, María Reyes Pavón Rial,
 * Florentino Fernández Riverola, María Novo Lourés, and Miguel Reboiro Jato
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

import java.util.Arrays;
import java.util.Stack;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Activity1TestCase {

  public Activity1TestCase() {
  }

  /**
   * Test of factorial method, of class Activity1.
   */
  @Test
  public void testFactorialZero() {
    int number = 0;
    long expResult = 1;
    long result = Activity1.factorial(number);
    assertEquals(expResult, result);
  }

  @Test
  public void testFactorial() {
    int number = 4;
    long expResult = 24;
    long result = Activity1.factorial(number);

    assertEquals(expResult, result);
  }

  /**
   * Test of square method, of class Activity1.
   */
  @Test
  public void testSquareZero() {
    int number = 0;
    int expResult = 0;
    int result = Activity1.square(number);

    assertEquals(expResult, result);
  }

  @Test
  public void testSquare() {
    int number = 5;
    int expResult = 25;
    int result = Activity1.square(number);

    assertEquals(expResult, result);
  }

  /**
   * Test of sumDigits method, of class Activity1.
   */
  @Test
  public void testSumDigits() {
    int number = 1111;
    int expResult = 4;
    int result = Activity1.sumDigits(number);

    assertEquals(expResult, result);
  }

  /**
   * Test of mcd method, of class Activity1.
   */
  @Test
  public void testMcd() {
    int number1 = 22;
    int number2 = 12;
    int expResult = 2;
    int result = Activity1.mcd(number1, number2);

    assertEquals(expResult, result);
  }

  /**
   * Test of invert method, of class Activity1.
   */
  @Test
  public void testInvert() {
    String str = "example";
    String expResult = "elpmaxe";
    String result = Activity1.invert(str);

    assertEquals(expResult, result);
  }

  /**
   * Test of addValues method, of class Activity1.
   */
  @Test
  public void testAddValues() {
    int[] values = {
      2, 4, 6, 8
    };
    int expResult = 20;
    int result = Activity1.addValues(values);

    assertEquals(expResult, result);
  }

  /**
   * Test of invertArray method, of class Activity1.
   */
  @Test
  public void testInvertArray() {
    int[] values = {
      2, 4, 6, 8
    };
    int[] expResult = {
      8, 6, 4, 2
    };
    Activity1.invertArray(values);

    assertArrayEquals(expResult, values);
  }

  /**
   * Test of minimum method, of class Activity1.
   */
  @Test
  public void testMinimum() {
    int[] values = {
      4, 3, 6, 2, 8
    };
    int expResult = 2;
    int result = Activity1.minimum(values);

    assertEquals(expResult, result);
  }

  /**
   * Test of binarySearch method, of class Activity1.
   */
  @Test
  public void testBinarySearch() {
    double[] values = {
      1.1, 2.2, 3.3, 4.4, 5.5
    };
    double number = 3.3;
    int expResult = 2;
    int result = Activity1.binarySearch(values, number);

    assertEquals(expResult, result);
  }

  /**
   * Test of copyStack method, of class Activity1.
   */
  @Test
  public void testCopyStack() {
    Stack<String> expResult = new Stack<>();
    expResult.addAll(Arrays.asList(new String[] {
      "AEDII", "Actividad", "Uno"
    }));
    Stack<String> result = Activity1.copyStack(expResult);

    assertEquals(expResult, result);
  }

}
