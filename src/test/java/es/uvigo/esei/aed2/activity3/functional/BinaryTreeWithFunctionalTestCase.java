package es.uvigo.esei.aed2.activity3.functional;

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
import es.uvigo.esei.aed2.tree.binary.BinaryTreeBuilder;
import es.uvigo.esei.aed2.tree.binary.BinaryTreeTestCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static es.uvigo.esei.aed2.tree.binary.IsEqualToBinaryTree.equalToTree;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public abstract class BinaryTreeWithFunctionalTestCase<B extends BinaryTreeWithFunctional<String>>
    extends BinaryTreeTestCase<B> {
    public BinaryTreeWithFunctionalTestCase(
        Supplier<B> newEmptyTree,
        Function<String, B> newTree,
        BinaryTreeBuilder<String, B> newTreeWithChildren
    ) {
        super(newEmptyTree, newTree, newTreeWithChildren);
    }

    /*
     * Test of forEach method, of class BinaryTreeWithFunctional.
     */
    @Test
    public void testForeach() {
        final B tree = this.data.getTree();
        final String[] expected = this.data.listTreeValuesInPreorder();

        final List<String> collectedValues = new ArrayList<>();
        tree.forEach(collectedValues::add);

        assertThat(collectedValues, contains(expected));
    }

    @Test
    public void testForeachEmptyTree() {
        final B tree = this.data.getEmptyTree();

        final List<String> collectedValues = new ArrayList<>();
        tree.forEach(collectedValues::add);

        assertThat(collectedValues, is(empty()));
    }

    /*
     * Test of forEach method with filter, of class BinaryTreeWithFunctional
     */
    @Test
    public void testFilteredForeach() {
        final B tree = this.data.getTree();
        final Predicate<String> filter = s -> s.matches("[AEIOU]"); // Filtra solo vocales mayúsculas
        final String[] expected = this.data.listFilteredTreeValuesInPreorder(filter);

        final List<String> collectedValues = new ArrayList<>();
        tree.forEach(collectedValues::add, filter);

        assertThat(collectedValues, contains(expected));
    }

    @Test
    public void testFilteredForeachEmptyTree() {
        final B tree = this.data.getEmptyTree();

        final List<String> collectedValues = new ArrayList<>();
        tree.forEach(collectedValues::add, s -> true);

        assertThat(collectedValues, is(empty()));
    }

    /*
     * Test of Map, of class BinaryTreeWithFunctional
     */
    @Test
    public void testMapTree() {
        final B tree = this.data.getTree();
        final Function<String, String> mapperLowerCase = String::toLowerCase; // convierte los caracteres a minúsculas

        final BinaryTree<String> expectedTree = this.data.getTreeLowerCase();

        BinaryTree<String> mappedTree = tree.map(mapperLowerCase);

        assertThat(expectedTree, is(equalToTree(mappedTree)));
    }

    @Test
    public void testMapEmptyTree() {
        final B tree = this.data.getEmptyTree();
        final Function<String, String> mapperLowerCase = String::toLowerCase; // convierte los caracteres a minúsculas
        final BinaryTree<String> expectedTree = this.data.getEmptyTree();

        BinaryTree<String> mappedTree = tree.map(mapperLowerCase);

        assertThat(expectedTree, is(equalToTree(mappedTree)));
    }

}
