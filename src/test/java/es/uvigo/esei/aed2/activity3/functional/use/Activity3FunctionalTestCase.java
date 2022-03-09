
package es.uvigo.esei.aed2.activity3.functional.use;

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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.text.IsBlankString.blankString;
import static org.hamcrest.text.IsEqualCompressingWhiteSpace.equalToCompressingWhiteSpace;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.uvigo.esei.aed2.activity2.data.IntBinaryTreeRepository;

import es.uvigo.esei.aed2.activity3.functional.LinkedBinaryTreeWithFunctional;
import es.uvigo.esei.aed2.activity3.functional.BinaryTreeWithFunctional;
import es.uvigo.esei.aed2.tree.binary.BinaryTree;
import static es.uvigo.esei.aed2.tree.binary.IsEqualToBinaryTree.equalToTree;

public class Activity3FunctionalTestCase {
  private final IntBinaryTreeRepository<BinaryTreeWithFunctional<Integer>> trees = new IntBinaryTreeRepository<>(
    LinkedBinaryTreeWithFunctional::new, LinkedBinaryTreeWithFunctional::new
  );

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  @BeforeEach
  public void setUpStreams() {
    // Redirige la salida del sistema al ByteArrayOutputStream
    System.setOut(new PrintStream(outContent));
  }

  @AfterEach
  public void restoreStreams() {
    // Restaura la salida original del sistema
    System.setOut(originalOut);
  }

  /**
   * Test of printAllValues method, of class Activity3Functional.
   */
  @Test
  public void testPrintAllValuesEmptyTree() {
    Activity3Functional.printAllValues(this.trees.getEmptyTree());

    assertThat(outContent.toString(), is(blankString()));

  }

  @Test
  public void testPrintAllValuesCompleteTree() {
    Activity3Functional.printAllValues(this.trees.getComplete());

    assertThat(outContent.toString(), is(equalToCompressingWhiteSpace("10\n20\n30\n40\n50")));
  }

  @Test
  public void testPrintAllValuesNonCompleteTree() {
    Activity3Functional.printAllValues(this.trees.getNonComplete());

    assertThat(outContent.toString(), is(equalToCompressingWhiteSpace("10\n20\n30\n40")));
  }

  /**
   * Test of listEvenValules method, of class Activity3Functional.
   */
  @Test
  public void testPrintEvenValuesEmptyTree() {
    List<Integer> result = Activity3Functional.listEvenValues(this.trees.getEmptyTree());
    List<Integer> expected = List.of();

    assertThat(result, is(expected));
  }

  @Test
  public void testPrintEvenValuesCompleteTree() {
    List<Integer> result = Activity3Functional.listEvenValues(this.trees.getComplete());
    List<Integer> expected = List.of(10, 20, 30, 40, 50);

    assertThat(result, is(expected));
  }

  @Test
  public void testPrintEvenValuesNonCompleteTree() {
    List<Integer> result = Activity3Functional.listEvenValues(this.trees.getNonComplete());
    List<Integer> expected = List.of(10, 20, 30, 40);

    assertThat(result, is(expected));
  }

  /**
   * Test of printAllValues2 method, of class Activity3Functional.
   */
  @Test
  public void testPrintValuesHigherThan30EmptyTree() {
    Activity3Functional.printValuesGreaterThan30(this.trees.getEmptyTree());

    assertThat(outContent.toString().trim(), is(""));
  }

  @Test
  public void testPrintValuesHigherThan30CompleteTree() {
    Activity3Functional.printValuesGreaterThan30(this.trees.getComplete());

    assertThat(outContent.toString(), is(equalToCompressingWhiteSpace("40\n50")));
  }

  @Test
  public void testPrintValuesHigherThan30NonCompleteTree() {
    Activity3Functional.printValuesGreaterThan30(this.trees.getNonComplete());
    assertThat(outContent.toString(), is(equalToCompressingWhiteSpace("40")));
  }

  /**
   * Test of binaryTreeWithTripleValues method, of class Activity3Functional.
   */
  @Test
  public void TestBinaryTreeWithTripleValuesEmptyTree() {
    BinaryTree<Integer> treeTripleValues = Activity3Functional.binaryTreeWithTripleValues(this.trees.getEmptyTree());

    assertThat(treeTripleValues.isEmpty(), is(true));
  }

  @Test
  public void TestBinaryTreeWithTripleValuesCompleteTree() {
    BinaryTree<Integer> treeTripleValues = Activity3Functional.binaryTreeWithTripleValues(this.trees.getComplete());
    BinaryTree<Integer> treeExpected = this.trees.getTripleComplete();

    assertThat(treeTripleValues, is(equalToTree(treeExpected)));
  }

}
