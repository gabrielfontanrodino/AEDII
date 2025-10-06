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

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;


public interface BinaryTreeWithFunctional<T> extends BinaryTree<T> {
    /**
     * recorre todos los valores del árbol en preorden
     *
     * @param action Consumer que recibe un valor del árbol
     *               <p>
     *               Devuelve todos los valores, porque filter siempre es verdadero
     */
    public default void forEach(Consumer<T> action) {
        forEach(action, (x) -> true);
    }

    /**
     * recorre todos los valores del árbol en preorden
     * y muestra los que cumplan una condición
     *
     * @param action Consumer que recibe un valor del árbol
     * @param filter condición a cumplir
     */
    public void forEach(Consumer<T> action, Predicate<T> filter);

    /**
     * crea un árbol binario con valores a los que se ha aplicado un mapper
     *
     * @param <E>    tipo del arbol binario que se va a crear
     * @param mapper Function que modifica los valores del árbol binario
     * @return arbol binario modificado
     */
    public <E> BinaryTree<E> map(Function<T, E> mapper);

}
