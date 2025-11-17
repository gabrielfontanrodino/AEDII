package es.uvigo.esei.aed2.activity10;

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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

public class DivideAndConquerAlgorithmTestCase {
  private static final int[] QUICK_SELECT = { 4, 2, 8, 12, 6, 9, 23, 43, 78, 14 };
  private static final int[] SEARCH_POSITION_K = { 9, 7, 5, 4, 3, 2, 1, 3, 4, 7 };
  private static final int[] INVERSIONS = { 1, 5, 4, 8, 10, 2, 6, 9, 12, 11, 3, 7 };
  private static final int[] NUMBER_EXISTS = { -3, 0, 2, 9, 10, 12, 15 };
  private static final StringBuilder MOVEMENTS = new StringBuilder()
    .append("Disc 1 from A to C\n")
    .append("Disc 2 from A to B\n")
    .append("Disc 1 from C to B\n")
    .append("Disc 3 from A to C\n")
    .append("Disc 1 from B to A\n")
    .append("Disc 2 from B to C\n")
    .append("Disc 1 from A to C\n");

  /**
   * Test of quickSelection method, of class DivideAndConquerAlgorithm.
   */
  @Test
  public void testQuickSelection() {
    int expectedSelection = 9;
    
    int kMinor = 5;
    int resultSelection = DivideAndConquerAlgorithm.quickSelection(QUICK_SELECT, kMinor, 0, QUICK_SELECT.length - 1);
    
    assertThat(resultSelection, is(equalTo(expectedSelection)));
  }

  /**
   * Test of moveHanoiDiscs method, of class DivideAndConquerAlgorithm.
   */
  @Test
  public void testMoveHanoiDiscs() {
    String expectedMovements = MOVEMENTS.toString();
    
    int numDiscs = 3;
    String startPost = "A";
    String auxiliaryPost = "B";
    String endPost = "C";
    
    String resultMovements = DivideAndConquerAlgorithm.moveHanoiDiscs(numDiscs, startPost, auxiliaryPost, endPost);
    
    assertThat(resultMovements, is(equalTo(expectedMovements)));
  }

  /**
   * Test of numberExists method, of class DivideAndConquerAlgorithm.
   */
  @Test
  public void testNumberExists() {
    int expectedNumber = 2;
    
    int resultNumber = DivideAndConquerAlgorithm.numberExists(NUMBER_EXISTS, 0, NUMBER_EXISTS.length - 1);
    
    assertThat(resultNumber, is(equalTo(expectedNumber)));
  }

  /**
   * Test of searchPosK method, of class DivideAndConquerAlgorithm.
   */
  @Test
  public void testSearchPosK() {
    int expectedNumber = 6;
    
    int resultNumber = DivideAndConquerAlgorithm.searchPositionK(SEARCH_POSITION_K, 0, SEARCH_POSITION_K.length - 1);
    
    assertThat(resultNumber, is(equalTo(expectedNumber)));
  }

  /**
   * Test of inversions method, of class DivideAndConquerAlgorithm.
   */
  @Test
  public void testInversions() {
    int expectedNumber = 22;
    
    int resultNumber = DivideAndConquerAlgorithm.inversions(INVERSIONS, 0, INVERSIONS.length - 1);
    
    assertThat(resultNumber, is(equalTo(expectedNumber)));
  }
}
