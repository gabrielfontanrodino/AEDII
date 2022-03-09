package es.uvigo.esei.aed2.activity5;

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

import static es.uvigo.esei.aed2.activity5.Activity5.breadthOrder;
import static es.uvigo.esei.aed2.activity5.Activity5.getGrade;
import static es.uvigo.esei.aed2.activity5.Activity5.getHeight;
import static es.uvigo.esei.aed2.activity5.Activity5.getLevel;
import static es.uvigo.esei.aed2.activity5.Activity5.getListLeaves;
import static es.uvigo.esei.aed2.activity5.Activity5.getNumberOfEvenValues;
import static es.uvigo.esei.aed2.activity5.Activity5.getSum;
import static es.uvigo.esei.aed2.activity5.Activity5.is23Tree;
import static es.uvigo.esei.aed2.activity5.Activity5.isEqualStructure;
import static es.uvigo.esei.aed2.activity5.Activity5.isSelection;

import es.uvigo.esei.aed2.activity5.data.IntTreeRepository;
import es.uvigo.esei.aed2.activity5.data.TreeRepository;

import es.uvigo.esei.aed2.tree.nary.LinkedTree;
import es.uvigo.esei.aed2.tree.nary.Tree;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsBlankString.blankString;
import static org.hamcrest.text.IsEqualCompressingWhiteSpace.equalToCompressingWhiteSpace;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Activity5TestCase {

  private final TreeRepository treesOfString = new TreeRepository(
          LinkedTree::new, LinkedTree::new);

  private final IntTreeRepository treesOfInteger = new IntTreeRepository(
          LinkedTree::new, LinkedTree::new);

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

  // Exercise 1 - getSum
  @Test  // Sum of empty tree
  public void testGetSumEmptyTree() {
    final Tree<Integer> treeA = treesOfInteger.getEmtpyTree();

    assertThat(getSum(treeA), is(0));
  }

  @Test  // Sum of tree with root only
  public void testGetSumTreeARoot() {
    final Tree<Integer> treeA = treesOfInteger.getTreeARoot();

    assertThat(getSum(treeA), is(10));
  }

  @Test  // Sum of complete tree
  public void testGetSumTreeA() {
    final Tree<Integer> treeA = treesOfInteger.getTreeA();

    assertThat(getSum(treeA), is(67));
  }

  // Exercise 2 - Equal Structure
  @Test  // Equal structure with empty trees
  public void testEqualStructureEmptyTrees() {
    final Tree<String> treeA = treesOfString.getEmtpyTree();
    final Tree<String> treeB = treesOfString.getEmtpyTree();

    assertThat(isEqualStructure(treeA, treeB), is(true));
  }

  @Test  // Equal structure with identical trees
  public void testEqualStructureTrees() {
    final Tree<String> treeA = treesOfString.getTreeA();
    final Tree<String> treeB = treesOfString.getTreeA();

    assertThat(isEqualStructure(treeA, treeB), is(true));
  }

  @Test  // Different structure between trees
  public void testDifferentStructureTrees() {
    final Tree<String> treeA = treesOfString.getTreeA();
    final Tree<String> treeB = treesOfString.getTreeB();

    assertThat(isEqualStructure(treeA, treeB), is(false));
  }

  // Exercise 3 - is23Tree
  @Test  // Empty tree is 2-3 tree
  public void testIs23TreeEmptyTree() {
    final Tree<String> tree = treesOfString.getEmtpyTree();

    assertThat(is23Tree(tree), is(true));
  }

  @Test  // Single node tree is 2-3 tree
  public void testIs23TreeARoot() {
    final Tree<String> tree = treesOfString.getTreeARoot();

    assertThat(is23Tree(tree), is(true));
  }

  @Test  // Valid 2-3 tree
  public void testIs23TreeA() {
    final Tree<String> tree = treesOfString.getTreeA();

    assertThat(is23Tree(tree), is(true));
  }

  @Test  // Invalid 2-3 tree
  public void testIs23TreeB() {
    final Tree<String> tree = treesOfString.getTreeB();

    assertThat(is23Tree(tree), is(false));
  }

  // Exercise 4 - isSelection
  @Test  // Empty tree is selection tree
  public void testIsSelectionEmptyTree() {
    final Tree<String> tree = treesOfString.getEmtpyTree();

    assertThat(isSelection(tree), is(true));
  }

  @Test  // Single node is selection tree
  public void testIsSelectionTreeARoot() {
    final Tree<String> tree = treesOfString.getTreeARoot();

    assertThat(isSelection(tree), is(true));
  }

  @Test  // Valid selection tree
  public void testIsSelectionTreeA() {
    final Tree<String> tree = treesOfString.getTreeA();

    assertThat(isSelection(tree), is(true));
  }

  @Test  // Invalid selection tree
  public void testIsSelectionTreeB() {
    final Tree<String> tree = treesOfString.getTreeB();

    assertThat(isSelection(tree), is(false));
  }

  // Exercise 5 - getLevel
  @Test  // Get level in empty tree
  public void testGetLevelEmptyTree() {
    final Tree<String> tree = treesOfString.getEmtpyTree();

    assertThat(getLevel(tree, "A"), is(-1));
  }

  @Test  // Get level in root-only tree
  public void testGetLevelTreeARoot() {
    final Tree<String> tree = treesOfString.getTreeARoot();

    assertThat(getLevel(tree, "A"), is(0));
  }

  @Test  // Get level for node at level 1
  public void testGetLevelTreeBValueD() {
    final Tree<String> tree = treesOfString.getTreeB();

    assertThat(getLevel(tree, "D"), is(1));
  }

  @Test  // Get level for node at level 2
  public void testGetLevelTreeBValueG() {
    final Tree<String> tree = treesOfString.getTreeB();

    assertThat(getLevel(tree, "G"), is(2));
  }

  @Test  // Get level for non-existent node
  public void testGetLevelTreeB() {
    final Tree<String> tree = treesOfString.getTreeB();

    assertThat(getLevel(tree, "X"), is(-1));
  }

  // Exercise 6 - getGrade
  @Test  // Get grade of empty tree
  public void testGetGradeEmptyTree() {
    final Tree<String> tree = treesOfString.getEmtpyTree();

    assertThat(getGrade(tree), is(0));
  }

  @Test  // Get grade of root-only tree
  public void testGetGradeTreeARoot() {
    final Tree<String> tree = treesOfString.getTreeARoot();

    assertThat(getGrade(tree), is(0));
  }

  @Test  // Get grade of tree A
  public void testGetGradeTreeA() {
    final Tree<String> tree = treesOfString.getTreeA();

    assertThat(getGrade(tree), is(3));
  }

  @Test  // Get grade of tree B
  public void testGetGradeTreeB() {
    final Tree<String> tree = treesOfString.getTreeB();

    assertThat(getGrade(tree), is(4));
  }

  @Test  // Get grade of tree B without leaves
  public void testGetGradeTreeBWithoutLeaves() {
    final Tree<String> tree = treesOfString.getTreeBWithoutLeaves();

    assertThat(getGrade(tree), is(2));
  }

  // Exercise 7 - getHeight
  @Test  // Get height of empty tree
  public void testGetHeightEmptyTree() {
    final Tree<String> tree = treesOfString.getEmtpyTree();

    assertThat(getHeight(tree), is(0));
  }

  @Test  // Get height of root-only tree
  public void testGetHeightTreeARoot() {
    final Tree<String> tree = treesOfString.getTreeARoot();

    assertThat(getHeight(tree), is(0));
  }

  @Test  // Get height of tree A
  public void testGetHeightTreeA() {
    final Tree<String> tree = treesOfString.getTreeA();

    assertThat(getHeight(tree), is(2));
  }

  @Test  // Get height of tree B without leaves
  public void testGetHeightTreeB() {
    final Tree<String> tree = treesOfString.getTreeBWithoutLeaves();

    assertThat(getHeight(tree), is(1));
  }

  // Exercise 8 - breadthOrder
  @Test  // Breadth order of empty tree
  public void testBreadthOrderEmptyTree() {
    final Tree<String> tree = treesOfString.getEmtpyTree();
    breadthOrder(tree);

    assertThat(outContent.toString(), is(blankString()));
  }

  @Test  // Breadth order of root-only tree
  public void testBreadthOrderTreeARoot() {
    final Tree<String> tree = treesOfString.getTreeARoot();
    breadthOrder(tree);

    assertThat(outContent.toString(), is(equalToCompressingWhiteSpace("A")));
  }

  @Test  // Breadth order of tree B
  public void testBreadthOrderTreeB() {
    final Tree<String> tree = treesOfString.getTreeB();
    breadthOrder(tree);

    assertThat(outContent.toString(), is(equalToCompressingWhiteSpace("A\nB\nC\nD\nH\nE\nF\nG")));
  }

  // Exercise 9 - getNumberOfEvenValues
  @Test  // Count even values in empty tree
  public void testGetNumberOfEvenValues() {
    final Tree<Integer> treeA = treesOfInteger.getEmtpyTree();

    assertThat(getNumberOfEvenValues(treeA), is(0));
  }

  @Test  // Count even values in root-only tree
  public void testGetNumberOfEvenValuesTreeARoot() {
    final Tree<Integer> treeA = treesOfInteger.getTreeARoot();

    assertThat(getNumberOfEvenValues(treeA), is(1));
  }

  @Test  // Count even values in complete tree
  public void testGetNumberOfEvenValuesTreeA() {
    final Tree<Integer> treeA = treesOfInteger.getTreeA();

    assertThat(getNumberOfEvenValues(treeA), is(4));
  }

  // Exercise 10 - getListLeaves
  @Test  // Get leaves of empty tree
  public void testgetListLeavesEmptyTree() {
    final Tree<String> tree = treesOfString.getEmtpyTree();
    final List<String> list = new LinkedList<>();
    final List<String> expectedList = new LinkedList<>();
    getListLeaves(tree, list);

    assertEquals(list, expectedList);
  }

  @Test  // Get leaves of tree A
  public void testgetListLeavesTreeA() {
    final Tree<String> tree = treesOfString.getTreeA();
    final List<String> list = new LinkedList<>();
    final List<String> expectedList = Arrays.asList("A", "B", "C", "D", "E");
    getListLeaves(tree, list);

    assertEquals(list, expectedList);
  }

  @Test  // Get leaves of tree B
  public void testgetListLeavesTreeB() {
    final Tree<String> tree = treesOfString.getTreeB();
    final List<String> list = new LinkedList<>();
    final List<String> expectedList = Arrays.asList("E", "F", "G", "D", "H");
    getListLeaves(tree, list);

    assertEquals(list, expectedList);
  }
}
