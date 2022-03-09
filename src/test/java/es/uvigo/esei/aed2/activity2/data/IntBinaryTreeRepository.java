package es.uvigo.esei.aed2.activity2.data;

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

import es.uvigo.esei.aed2.tree.binary.util.BinaryTreeParser;
import es.uvigo.esei.aed2.tree.binary.BinaryTree;

import java.util.function.Supplier;


public class IntBinaryTreeRepository<B extends BinaryTree<Integer>> {
  private final static String TREE_A = IntBinaryTreeRepository.COMPLETE_TREE;
  private final static String TREE_B = IntBinaryTreeRepository.NON_COMPLETE_TREE;
  
  private final static String COMPLETE_TREE =
    "root:\n" +
    "  value: 10\n" +
    "  left:\n" +
    "    value: 20\n" +
    "  right:\n" +
    "    value: 30\n" +
    "    left:\n" +
    "      value: 40\n" +
    "    right:\n" +
    "      value: 50";

  private final static String NON_COMPLETE_TREE =
    "root:\n" +
    "  value: 10\n" +
    "  left:\n" +
    "    value: 20\n" +
    "  right:\n" +
    "    value: 30\n" +
    "    left:\n" +
    "      value: 40";

  private final static String TREE_A_WITHOUT_LEAVES =
    "root:\n" +
    "  value: 10\n" +
    "  right:\n" +
    "    value: 30";

  private final static String TREE_B_WITHOUT_LEAVES =
    "root:\n" +
    "  value: 10\n" +
    "  right:\n" +
    "    value: 30";
  
  private final static String TRIPLE_COMPLETE_TREE =
    "root:\n" +
    "  value: 30\n" +
    "  left:\n" +
    "    value: 60\n" +
    "  right:\n" +
    "    value: 90\n" +
    "    left:\n" +
    "      value: 120\n" +
    "    right:\n" +
    "      value: 150";
  
  private final BinaryTreeParser<Integer, B> parser;
  private final Supplier<B> emptyTreeBuilder;

//  public IntBinaryTreeRepository() {
//    this(LinkedBinaryTree::new, LinkedBinaryTree::new);
//  }

  public IntBinaryTreeRepository(
    Supplier<B> emptyTreeBuilder,
    BinaryTreeParser.BinaryTreeBuilder<Integer, B> builder
  ) {
    this.parser = new BinaryTreeParser<>(emptyTreeBuilder, builder, Integer::parseInt);
    this.emptyTreeBuilder = emptyTreeBuilder;
  }
  
  public B getEmptyTree() {
    return this.emptyTreeBuilder.get();
  }
  
  public B getTreeA() {
    return this.parser.parseTree(TREE_A);
  }
  
  public B getTreeAWithoutLeaves() {
    return this.parser.parseTree(TREE_A_WITHOUT_LEAVES);
  }
  
  public B getTreeB() {
    return this.parser.parseTree(TREE_B);
  }

  public B getTreeBWithoutLeaves() {
    return this.parser.parseTree(TREE_B_WITHOUT_LEAVES);
  }

  public B getComplete() {
    return this.parser.parseTree(COMPLETE_TREE);
  }

  public B getNonComplete() {
    return this.parser.parseTree(NON_COMPLETE_TREE);
  }

  public B getTripleComplete() {
    return this.parser.parseTree(TRIPLE_COMPLETE_TREE);
  }
}
