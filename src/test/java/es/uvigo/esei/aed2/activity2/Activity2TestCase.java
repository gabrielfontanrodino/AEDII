package es.uvigo.esei.aed2.activity2;

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
import static es.uvigo.esei.aed2.activity2.Activity2.areIdentical;
import static es.uvigo.esei.aed2.activity2.Activity2.calculateHeight;
import static es.uvigo.esei.aed2.activity2.Activity2.copyingToLevel;
import static es.uvigo.esei.aed2.activity2.Activity2.countNodesInLevel;
import static es.uvigo.esei.aed2.activity2.Activity2.isComplete;
import static es.uvigo.esei.aed2.activity2.Activity2.isLevelK;
import static es.uvigo.esei.aed2.activity2.Activity2.isPath;
import static es.uvigo.esei.aed2.activity2.Activity2.isSelectionTree;
import static es.uvigo.esei.aed2.activity2.Activity2.rebuild;
import static es.uvigo.esei.aed2.activity2.Activity2.removeLeaves;
import static es.uvigo.esei.aed2.tree.binary.IsEqualToBinaryTree.equalToTree;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import es.uvigo.esei.aed2.activity2.data.BinaryTreeRepository;

import es.uvigo.esei.aed2.tree.binary.BinaryTree;
import es.uvigo.esei.aed2.tree.binary.LinkedBinaryTree;

public class Activity2TestCase {

  private final BinaryTreeRepository<BinaryTree<String>> trees = new BinaryTreeRepository<>(
          LinkedBinaryTree::new, LinkedBinaryTree::new);

  // Exercise 1 - isComplete
  @Test
  public void testCompleteEmptyTree() {
    final BinaryTree<String> tree = trees.getEmptyTree();

    assertThat(isComplete(tree), is(true));
  }

  @Test
  public void testCompleteTree() {
    final BinaryTree<String> tree = trees.getComplete();

    assertThat(isComplete(tree), is(true));
  }

  @Test
  public void testNonCompleteTree() {
    final BinaryTree<String> tree = trees.getNonComplete();

    assertThat(isComplete(tree), is(false));
  }

  // Exercise 2 - areIdentical
  @Test
  public void testIdenticalEmptyTrees() {
    final BinaryTree<String> treeA = trees.getEmptyTree();
    final BinaryTree<String> treeB = trees.getEmptyTree();

    assertThat(areIdentical(treeA, treeB), is(true));
  }

  @Test
  public void testIdenticalTrees() {
    final BinaryTree<String> treeA = trees.getTreeA();
    final BinaryTree<String> treeB = trees.getTreeA();

    assertThat(areIdentical(treeA, treeB), is(true));
  }

  @Test
  public void testDifferentTrees() {
    final BinaryTree<String> treeA = trees.getTreeA();
    final BinaryTree<String> treeB = trees.getTreeB();

    assertThat(areIdentical(treeA, treeB), is(false));
  }

  @Test
  public void testDifferentTreesFromEmpty() {
    final BinaryTree<String> treeA = trees.getEmptyTree();
    final BinaryTree<String> treeB = trees.getTreeB();

    assertThat(areIdentical(treeA, treeB), is(false));
  }

  // Exercise 3 - countNodesInLevel
  @Test
  public void testCountNodesInLevel1() {
    final BinaryTree<String> tree = trees.getTreeA();
    final int expectedNodes = trees.getTreeANodesInLevel1();

    assertThat(countNodesInLevel(tree, 1), is(expectedNodes));
  }

  @Test
  public void testCountNodesInLevel2() {
    final BinaryTree<String> tree = trees.getTreeA();
    final int expectedNodes = trees.getTreeANodesInLevel2();

    assertThat(countNodesInLevel(tree, 2), is(expectedNodes));
  }

  @Test
  public void testCountNodesInLevel2TreeB() {
    final BinaryTree<String> tree = trees.getTreeB();
    final int expectedNodes = trees.getTreeBNodesInLevel2();

    assertThat(countNodesInLevel(tree, 2), is(expectedNodes));
  }

  // Exercise 4 - removeLeaves
  @Test
  public void testRemoveEmptyTreeLeaves() {
    final BinaryTree<String> tree = trees.getEmptyTree();
    final BinaryTree<String> treeWithoutLeaves = trees.getEmptyTree();

    assertThat(removeLeaves(tree), is(equalToTree(treeWithoutLeaves)));
  }

  @Test
  public void testRemoveTreeARootLeaves() {
    final BinaryTree<String> treeARoot = trees.getTreeARoot();
    final BinaryTree<String> treeARootWithoutLeaves = trees.getEmptyTree();

    assertThat(removeLeaves(treeARoot), is(equalToTree(treeARootWithoutLeaves)));
  }

  @Test
  public void testRemoveTreeALeaves() {
    final BinaryTree<String> treeA = trees.getTreeA();
    final BinaryTree<String> treeAWithoutLeaves = trees.getTreeAWithoutLeaves();

    assertThat(removeLeaves(treeA), is(equalToTree(treeAWithoutLeaves)));
  }

  @Test
  public void testRemoveTreeBLeaves() {
    final BinaryTree<String> treeB = trees.getTreeB();
    final BinaryTree<String> treeBWithoutLeaves = trees.getTreeBWithoutLeaves();

    assertThat(removeLeaves(treeB), is(equalToTree(treeBWithoutLeaves)));
  }

  // Exercise 5 - calculateHeight
  @Test
  public void testCalculateHeightEmptyTree() {
    final BinaryTree<String> tree = trees.getEmptyTree();

    assertThat(calculateHeight(tree), is(-1));
  }

  @Test
  public void testCalculateHeightTreeARoot() {
    final BinaryTree<String> tree = trees.getTreeARoot();

    assertThat(calculateHeight(tree), is(0));
  }

  @Test
  public void testCalculateHeightTreeA() {
    final BinaryTree<String> tree = trees.getTreeA();

    assertThat(calculateHeight(tree), is(2));
  }

  @Test
  public void testCalculateHeightTreeB() {
    final BinaryTree<String> tree = trees.getTreeB();

    assertThat(calculateHeight(tree), is(2));
  }

  // Exercise 6 - rebuild
  @Test
  public void testRebuildTreeARoot() {
    final BinaryTree<String> tree = trees.getTreeARoot();

    assertThat(rebuild("A", "A"), is(equalToTree(tree)));
  }

  @Test
  public void testRebuildEmptyTree() {
    final BinaryTree<String> tree = trees.getEmptyTree();

    assertThat(rebuild("", ""), is(equalToTree(tree)));
  }

  @Test
  public void testRebuildTreeA() {
    final String treePreorder = trees.getTreeAPreorder();
    final String treeInorder = trees.getTreeAInorder();
    final BinaryTree<String> tree = trees.getTreeA();

    assertThat(rebuild(treePreorder, treeInorder), is(equalToTree(tree)));
  }

  @Test
  public void testRebuildTreeB() {
    final String treePreorder = trees.getTreeBPreorder();
    final String treeInorder = trees.getTreeBInorder();
    final BinaryTree<String> tree = trees.getTreeB();

    assertThat(rebuild(treePreorder, treeInorder), is(equalToTree(tree)));
  }

  // Exercise 7 - selectionTree
  @Test
  public void testSelectionEmptyTree() {
    final BinaryTree<String> tree = trees.getEmptyTree();

    assertThat(isSelectionTree(tree), is(true));
  }

  @Test
  public void testSelectionTreeARoot() {
    final BinaryTree<String> tree = trees.getTreeARoot();

    assertThat(isSelectionTree(tree), is(true));
  }

  @Test
  public void testSelectionTreeA() {
    final BinaryTree<String> tree = trees.getSelection();

    assertThat(isSelectionTree(tree), is(true));
  }

  @Test
  public void testSelectionTreeB() {
    final BinaryTree<String> tree = trees.getNonSelection();

    assertThat(isSelectionTree(tree), is(false));
  }

  // Exercise 8 - isPath
  @Test
  public void testisPathEmptyTree() {
    final BinaryTree<String> tree = trees.getEmptyTree();

    assertThat(isPath(tree, "A"), is(false));
  }

  @Test
  public void testisPathEmptyTreeEmptyPath() {
    final BinaryTree<String> tree = trees.getEmptyTree();

    assertThat(isPath(tree, ""), is(true));
  }

  @Test
  public void testisPathTreeARootPath() {
    final BinaryTree<String> tree = trees.getTreeARoot();

    assertThat(isPath(tree, "A"), is(true));
  }

  @Test
  public void testisPathTreeASingleElement() {
    final BinaryTree<String> tree = trees.getTreeA();
    final String path = trees.getTreeAValidPathSingleElement();

    assertThat(isPath(tree, path), is(true));
  }

  @Test
  public void testisPathTreeAThreeElements() {
    final BinaryTree<String> tree = trees.getTreeA();
    final String path = trees.getTreeAValidPathThreeElements();

    assertThat(isPath(tree, path), is(true));
  }

  @Test
  public void testisPathTreeAPartiallyInvalidPath() {
    final BinaryTree<String> tree = trees.getTreeA();
    final String path = trees.getTreeAPartiallyInvalidPath();

    assertThat(isPath(tree, path), is(false));
  }

  @Test
  public void testisPathTreeAInvalidPath() {
    final BinaryTree<String> tree = trees.getTreeA();
    final String path = trees.getTreeAInvalidPath();

    assertThat(isPath(tree, path), is(false));
  }

  // Exercise 9 - copyingToLevel 
  @Test
  public void testCopyToLevelEmptyTree() {
    final BinaryTree<String> treeA = trees.getTreeA();
    final BinaryTree<String> expectedTree = trees.getTreeARoot();

    assertThat(copyingToLevel(treeA, 0), is(equalToTree(expectedTree)));
  }

  @Test
  public void testCopyToLevelTreeA() {
    final BinaryTree<String> treeA = trees.getTreeA();
    final BinaryTree<String> newTree = trees.getNonSelection();

    assertThat(copyingToLevel(treeA, 1), is(equalToTree(newTree)));
  }

  @Test
  public void testCopyToLevelSuperiorTreeA() {
    final BinaryTree<String> treeA = trees.getTreeA();
    final BinaryTree<String> expectedTree = trees.getTreeA();

    assertThat(copyingToLevel(treeA, 6), is(equalToTree(expectedTree)));
  }

  // Exercise 10 - isLevelK
  @Test
  public void testisLevelKEmptyTree() {
    final BinaryTree<String> tree = trees.getEmptyTree();

    assertThat(isLevelK(tree, "A", 0), is(false));
  }

  @Test
  public void testisLevelKTreeARoot() {
    final BinaryTree<String> tree = trees.getTreeARoot();

    assertThat(isLevelK(tree, "A", 0), is(true));
  }

  @Test
  public void testisLevelNotKTreeARoot() {
    final BinaryTree<String> tree = trees.getTreeARoot();

    assertThat(isLevelK(tree, "A", 10), is(false));
  }

  @Test
  public void testisLevelK() {
    final BinaryTree<String> tree = trees.getTreeA();
    final String element = trees.getTreeAElementInLevel2();

    assertThat(isLevelK(tree, element, 2), is(true));
  }

  @Test
  public void testisNotLevelK() {
    final BinaryTree<String> tree = trees.getTreeA();
    final String element = trees.getTreeAElementNotInLevel1();

    assertThat(isLevelK(tree, element, 1), is(false));
  }
}
