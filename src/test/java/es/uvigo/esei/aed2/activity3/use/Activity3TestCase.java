package es.uvigo.esei.aed2.activity3.use;

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

import es.uvigo.esei.aed2.activity2.data.BinaryTreeRepository;
import es.uvigo.esei.aed2.activity3.implementation.LinkedBinaryTree;
import es.uvigo.esei.aed2.tree.binary.BinaryTree;
import org.junit.jupiter.api.Test;

import static es.uvigo.esei.aed2.activity3.use.Activity3.getParent;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class Activity3TestCase {

    private final BinaryTreeRepository<BinaryTree<String>> trees = new BinaryTreeRepository<>(
        LinkedBinaryTree::new, LinkedBinaryTree::new
    );

    @Test
    public void exerciseGetParentEmptyTree() {
        final BinaryTree<String> treeEmpty = trees.getTreeA();

        assertThat(getParent(treeEmpty, "x"), is(nullValue()));
    }

    @Test
    public void exerciseGetParentTreeA() {
        final BinaryTree<String> treeEmpty = trees.getTreeA();

        assertThat(getParent(treeEmpty, "A"), is(nullValue()));
    }

    @Test
    public void exerciseGetParentTreeB() {
        final BinaryTree<String> treeEmpty = trees.getTreeB();

        assertThat(getParent(treeEmpty, "D"), is("C"));
    }

}
