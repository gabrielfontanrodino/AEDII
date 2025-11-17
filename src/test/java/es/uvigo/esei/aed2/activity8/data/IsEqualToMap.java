package es.uvigo.esei.aed2.activity8.data;

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

import es.uvigo.esei.aed2.map.Map;
import java.util.Set;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class IsEqualToMap<T, E> extends TypeSafeMatcher<Map<T, E>> {
  private final Map<T, E> expected;

  public IsEqualToMap(Map<T, E> expected) {
    this.expected = expected;
  }

  @Override
  public void describeTo(Description description) {
    description.appendText("equal map");
  }

  @Override
  protected boolean matchesSafely(Map<T, E> actual) {
    // Comparar claves
    Set<T> keysActual = actual.getKeys();
    Set<T> keysExpected = expected.getKeys();
    if (!keysActual.equals(keysExpected)) {
      return false;
    }

    // Comparar valores
    for (T KeyValue : actual.getKeys())
      if (!actual.get(KeyValue).equals(expected.get(KeyValue)))
        return false;

    return true;

  }

  public static <T, E> IsEqualToMap<T, E> equalToMap(Map<T, E> expected) {
    return new IsEqualToMap<>(expected);
  }
}
