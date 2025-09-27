package es.uvigo.esei.aed2.activity1;

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

import java.util.Stack;

public class Activity1 {

  /**
   * exercise 1
   *
   * @param number numero usado para calcular factorial.
   * @return el factorial de number.
   */
  public static long factorial(int number) {
    if (number < 0) {
      throw new IllegalArgumentException("El número debe ser positivo");
    }

    if (number == 0) {
      return 1;
    } else {
      return number * factorial(number - 1);
    }
  }

  /**
   * exercise 2
   *
   * @param number numero usado para calcular el cuadrado.
   * @return el cuadrado de number.
   */
  public static int square(int number) {
    if (number < 0) {
      throw new IllegalArgumentException("El número debe ser positivo");
    }

    if (number == 0) {
      return number;
    } else {
      return square(number - 1) + ((2 * number) - 1);
    }
  }

  /**
   * exercise 3
   *
   * @param number numero usado para sumar sus digitos.
   * @return la suma de los dígitos de number.
   */
  public static int sumDigits(int number) {
    if (number < 0) {
      throw new IllegalArgumentException("El número debe ser positivo");
    }

    int digit = number % 10;
    int rest = number / 10;

    if (rest == 0) {
      return digit;
    } else {
      return digit + sumDigits(rest);
    }
  }

  /**
   * exercise 4
   *
   * @param number1 primer numero usado para calcular el MCD
   * @param number2 segundo numero usado para calcular el MCD
   * @return el MCD de number1 y number2
   */
  public static int mcd(int number1, int number2) {

    if (number1 < 0 || number2 < 0) {
      throw new IllegalArgumentException("Los números deben ser positivos");
    }

    if (number1 > number2) {
      return mcd(number1 - number2, number2);
    } else if (number1 < number2) {
      return mcd(number1, number2 - number1);
    } else {
      return number1;
    }
  }

  /**
   * exercise 5
   *
   * @param str String usado para invertir caracteres.
   * @return el string invertido.
   */
  public static String invert(String str) {
    if (str == null || str.length() <= 1) {
      return str;
    } else {
      return invert(str.substring(1)) + str.charAt(0);
    }
  }

  /**
   * exercise 5
   *
   * @param str String usado para invertir caracteres.
   * @return el string invertido.
   */
  public static String invert2(String str) {
    if (str == null) {
      throw new IllegalArgumentException("El string no puede ser nulo");
    }

    return invertHelper(str, 0);

  }

  private static String invertHelper(String str, int pos) {
    if (pos == str.length() - 1) {
      return String.valueOf(str.charAt(pos));
    } else {
      return str.charAt(str.length() - 1 - pos) + invertHelper(str, pos + 1);
    }
  }

  /**
   * exercise 6
   *
   * @param values el array usado para sumar los valores.
   * @return suma todos los valores del array.
   */
  public static int addValues(int[] values) {
    if( values == null) {
      throw new IllegalArgumentException("El array no puede ser nulo");
    }

    if (values.length == 0) {
      return 0;
    }

    if( values.length == 1) {
      return values[0];
    }

    return addValuesHelper(values, 0);
  }

  private static int addValuesHelper(int[] values, int index) {
    if (index == values.length - 1) {
      return values[index];
    } else {
      return values[index] + addValuesHelper(values, index + 1);
    }
  }

  /**
   * exercise 7
   *
   * @param values array usado para el proceso de inversion.
   */
  public static void invertArray(int[] values) {
    if( values == null) {
      throw new IllegalArgumentException("El array no puede ser nulo");
    }

    if (values.length <= 1) {
      return;
    }

    invertArrayHelper(values, 0, values.length - 1);
  }

  private static void invertArrayHelper(int[] values, int start, int end) {
    if (start < end) {
      int temp = values[start];
      values[start] = values[end];
      values[end] = temp;
      invertArrayHelper(values, start + 1, end - 1);
    }
  }

  /**
   * exercise 8
   *
   * @param values array usado para encontrar el entero menor.
   * @return el menor entero del array.
   */
  public static int minimum(int[] values) {
    // TODO: Implementa la búsqueda recursiva del menor valor de un array
    return 0;
  }

  /**
   * exercise 9
   *
   * @param values array usado para la busqueda binaria.
   * @param number el numero a buscar.
   * @return el indice de number en el array o -1 si no está.
   */
  public static int binarySearch(double[] values, double number) {
    // TODO: Implementa la búsqueda binaria recursiva en un array
    return -1;
  }

  /**
   * exercise 10
   *
   * @param <T>   Tipo de los elementos contenidos en el Stack.
   * @param stack la pila a copiar.
   * @return una nueva pila con los mismos elementos que la original.
   */
  public static <T> Stack<T> copyStack(Stack<T> stack) {
    // TODO: Implementa la copia recursiva de una pila
    return new Stack<>();
  }
}
