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

import es.uvigo.esei.aed2.tree.binary.BinaryTree;
import es.uvigo.esei.aed2.tree.binary.util.BinaryTreeParser;

import java.util.function.Function;
import java.util.function.Supplier;


public class BinaryTreeRepository<B extends BinaryTree<String>> {
    private final static String TREE_A = BinaryTreeRepository.COMPLETE_TREE;
    private final static String TREE_B = BinaryTreeRepository.NON_COMPLETE_TREE;

    private final static String COMPLETE_TREE =
        """
            root:
              value: A
              left:
                value: B
              right:
                value: C
                left:
                  value: D
                right:
                  value: E
            """;

    private final static String NON_COMPLETE_TREE =
        """
            root:
              value: A
              left:
                value: B
              right:
                value: C
                left:
                  value: D
            """;

    private final static String TREE_A_ROOT =
        """
            root:
              value: A
            """;

    private final static String TREE_A_WITHOUT_LEAVES =
        """
            root:
              value: A
              right:
                value: C
            """;

    private final static String TREE_B_WITHOUT_LEAVES =
        """
            root:
              value: A
              right:
                value: C
            """;

    private final static String SELECTION_TREE =
        """
            root:
              value: A
              left:
                value: B
              right:
                value: A
                left:
                  value: A
                right:
                  value: D
            """;

    private final static String NON_SELECTION_TREE =
        """
            root:
              value: A
              left:
                value: B
              right:
                value: C
            """;


    private final BinaryTreeParser<String, B> parser;
    private final Supplier<B> emptyTreeBuilder;

//  public BinaryTreeRepository() {
//    this(LinkedBinaryTree::new, LinkedBinaryTree::new);
//  }

    public BinaryTreeRepository(
        Supplier<B> emptyTreeBuilder,
        BinaryTreeParser.BinaryTreeBuilder<String, B> builder
    ) {
        this.emptyTreeBuilder = emptyTreeBuilder;
        this.parser = new BinaryTreeParser<>(emptyTreeBuilder, builder, Function.identity());
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

    public B getTreeARoot() {
        return this.parser.parseTree(TREE_A_ROOT);
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

    public B getSelection() {
        return this.parser.parseTree(SELECTION_TREE);
    }

    public B getNonSelection() {
        return this.parser.parseTree(NON_SELECTION_TREE);
    }

    public int getTreeANodesInLevel1() {
        return 2;
    }

    public int getTreeANodesInLevel2() {
        return 2;
    }

    public int getTreeBNodesInLevel2() {
        return 1;
    }

    public int getTreeAHeight() {
        return 2;
    }

    public int getTreeBHeight() {
        return 2;
    }

    public String getTreeAPreorder() {
        return "ABCDE";
    }

    public String getTreeAInorder() {
        return "BADCE";
    }

    public String getTreeBPreorder() {
        return "ABCD";
    }

    public String getTreeBInorder() {
        return "BADC";
    }

    public String getTreeAValidPathSingleElement() {
        return "A";
    }

    public String getTreeAValidPathThreeElements() {
        return "ACD";
    }

    public String getTreeAPartiallyInvalidPath() {
        return "ACEF";
    }

    public String getTreeAInvalidPath() {
        return "CE";
    }

    public String getTreeAElementNotInLevel1() {
        return "D";
    }

    public String getTreeAElementInLevel2() {
        return "D";
    }
}
