package es.uvigo.esei.aed2.activity5.data;

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

import es.uvigo.esei.aed2.tree.nary.Tree;
import es.uvigo.esei.aed2.tree.nary.util.TreeParser;

import java.util.function.Supplier;

public class IntTreeRepository {

  private final static String TREE_A = IntTreeRepository.TREE;

  private final static String TREE = 
"root:\n" +
"  value: 10\n" +
"  child1:\n" +
"    value: 8\n" +
"    child1: \n" +
"      value: 7\n" +
"  child2:\n" +
"    value: 15\n" +
"    child1:\n" +
"      value: 4\n" +
"    child2:\n" +
"      value: 20\n" +
"  child3: \n" +
"    value: 3\n";

  private final static String TREE_A_ROOT = 
"root:\n" +
"  value: 10\n";

  private final static String TREE_A_WITHOUT_LEAVES = 
"root:\n" +
"  value: 10\n" +
"  child1:\n" +
"    value: 8\n" +
"  child2:\n" +
"    value: 15\n";

  private final TreeParser<Integer> parser;
  private final Supplier<Tree<Integer>> emptyTreeBuilder;

  public IntTreeRepository(
          TreeParser.TreeBuilder<Integer> builder,
          Supplier<Tree<Integer>> emptyTreeBuilder
  ) {
    this.parser = new TreeParser<>(emptyTreeBuilder, builder, Integer::parseInt);
    this.emptyTreeBuilder = emptyTreeBuilder;
  }

  public Tree<Integer> getEmtpyTree() {
    return this.emptyTreeBuilder.get();
  }

  public Tree<Integer> getTreeA() {
    return this.parser.parseTree(TREE_A);
  }

  public Tree<Integer> getTreeARoot() {
    return this.parser.parseTree(TREE_A_ROOT);
  }

  public Tree<Integer> getTreeAWithoutLeaves() {
    return this.parser.parseTree(TREE_A_WITHOUT_LEAVES);
  }

}
