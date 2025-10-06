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

import java.util.function.Function;
import java.util.function.Supplier;

public class TreeRepository {

    private final static String TREE_A = TreeRepository.SELECTION_TREE;
    private final static String TREE_B = TreeRepository.NON_SELECTION_TREE;

    private final static String SELECTION_TREE =
        """
            root:
              value: A
              child1:
                value: A
                child1:
                  value: A
                child2:
                  value: B
                child3:
                  value: C
              child2:
                value: D
                child1:
                  value: D
                child2:
                  value: E
            """;

    private final static String NON_SELECTION_TREE =
        """
            root:
              value: A
              child1:
                value: B
                child1:
                  value: E
              child2:
                value: C
                child1:
                  value: F
                child2:
                  value: G
              child3:
                value: D
              child4:
                value: H
            """;

    private final static String TREE_A_ROOT =
        """
            root:
              value: A
            """;

    private final static String TREE_B_WITHOUT_LEAVES =
        """
            root:
              value: A
              child1:
                value: B
              child2:
                value: C
            """;


    private final TreeParser<String> parser;
    private final Supplier<Tree<String>> emptyTreeBuilder;

    public TreeRepository(
        TreeParser.TreeBuilder<String> builder,
        Supplier<Tree<String>> emptyTreeBuilder
    ) {
        this.parser = new TreeParser<>(emptyTreeBuilder, builder, Function.identity());
        this.emptyTreeBuilder = emptyTreeBuilder;
    }

    public Tree<String> getEmtpyTree() {
        return this.emptyTreeBuilder.get();
    }

    public Tree<String> getTreeA() {
        return this.parser.parseTree(TREE_A);
    }

    public Tree<String> getTreeB() {
        return this.parser.parseTree(TREE_B);
    }

    public Tree<String> getTreeARoot() {
        return this.parser.parseTree(TREE_A_ROOT);
    }

    public Tree<String> getTreeBWithoutLeaves() {
        return this.parser.parseTree(TREE_B_WITHOUT_LEAVES);
    }
}
