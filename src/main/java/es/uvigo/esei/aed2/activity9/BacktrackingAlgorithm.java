package es.uvigo.esei.aed2.activity9;

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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class BacktrackingAlgorithm {

    public static final int SUDOKU_DIMENSION = 9;
    public static final int PUZZLE_DIMENSION = 4; // Puzzle cuadrado

    // Exercise 1
    public static boolean giveChange(int amountReturned, Map<Integer, Integer> changeAvailable, Map<Integer, Integer> solution) {
        boolean objetivo = false;

        Iterator<Integer> candidates = changeAvailable.getKeys().iterator();

        while (candidates.hasNext() && !objetivo) {
            // Obtener un billete de la lista
            int billete = candidates.next();

            //Si no quedan billetes de un tamaño, probamos con otro
            if (changeAvailable.get(billete) > 0 && amountReturned >= billete) {
                //Si la resta es exacta
                if (amountReturned - billete == 0) {
                    objetivo = true;
                } else {
                    // Restamos un billete de la cantidad a probar a la lista de billetes disponibles (la caja)
                    changeAvailable.add(billete, changeAvailable.get(billete) - 1);

                    // Usamos el mismo algoritmo para comprobar las opciones más pequeñas
                    objetivo = giveChange(amountReturned - billete, changeAvailable, solution);

                    // Si no encontramos una solución posible
                    if (!objetivo) {
                        // Deshacemos el cambio
                        changeAvailable.add(billete, changeAvailable.get(billete) + 1);
                        // Probamos el siguiente billete (de la lista)
                    }
                    // Como la cantidad a buscar es menor que el tamaño del billete,
                    // probamos con otro billete de distinto valor.
                    // (Volvemos al inicio)
                }

                if (objetivo) {
                    // Añadimos un billete al mapa de solución ya que encontramos un caso favorable
                    if (solution.get(billete) == null) {
                        solution.add(billete, 1);
                    } else {
                        solution.add(billete, solution.get(billete) + 1);
                    }
                }
            }
        }

        return objetivo;
    }

    // Exercise 2
    public static boolean fillUpFlashDrive(int capacityCD, Map<String, Integer> espacePrograms, Set<String> flashDrive) {
        // Convertimos las llaves a una lista para tener un orden fijo y usar un índice
        List<String> candidatos = new ArrayList<>(espacePrograms.getKeys());

        // Llamamos a la función auxiliar empezando en el índice 0
        return fillUpFlashDriveAux(capacityCD, espacePrograms, candidatos, flashDrive, 0);
    }


    private static boolean fillUpFlashDriveAux(int capacity, Map<String, Integer> programs, List<String> candidates, Set<String> drive, int programIndex) {
        boolean objetivo = false;

        // Caso base: Si la capacidad llegó a 0, completamos el ejercicio
        if (capacity == 0) {
            objetivo = true;
        } else {
            while (programIndex < candidates.size() && !objetivo) {
                String prog = candidates.get(programIndex);
                int size = programs.get(prog);

                // Comprobamos si el programa cabe en el espacio restante
                if (capacity >= size) {
                    // 1. MARCAR: Añadimos el programa
                    drive.add(prog);

                    // 2. EXPLORAR: Llamada recursiva pasando el siguiente índice (i + 1)
                    objetivo = fillUpFlashDriveAux(capacity - size, programs, candidates, drive, programIndex + 1);

                    // 3. DESHACER (Backtracking): Si este camino no funcionó, deshacemos los cambios
                    if (!objetivo) {
                        drive.remove(prog);
                    }
                }

                // Si no hemos encontrado el objetivo, pasamos al siguiente candidato
                if (!objetivo) {
                    programIndex++;
                }
            }
        }

        return objetivo;
    }

    // Exercise 3
    public static boolean subgroups(int[] values, int[] solution, int result, int index) {
        boolean objetivo = false;

        while (index < values.length && !objetivo) {
            int candidato = values[index];
            if (candidato <= result) {
                solution[index] = 1;

                if (candidato == result) {
                    objetivo = true;
                } else {
                    objetivo = subgroups(values, solution, result - candidato, index + 1);

                    if (!objetivo) {
                        solution[index] = 0;
                    }
                }

            }
            index++;
        }

        return objetivo;
    }

    // Exercise 4
    public static boolean placeQueens(int queen, int[] board) {
        boolean objetivo = false;

        int columna = 0;

        while (columna < board.length && !objetivo) {
            if (goodPlace(queen, columna, board)) {
                board[queen] = columna; //Anoto el nuevo paso

                if (queen == board.length - 1) {
                    objetivo = true;
                } else {
                    objetivo = placeQueens(queen + 1, board);

                    if (!objetivo) {
                        board[queen] = -1;
                    }
                }
            }
            columna++;
        }

        return objetivo;
    }

    private static boolean goodPlace(int queen, int column, int[] board) {
        // ¿Es amenaza colocar la reina 'queen' en column, con las anteriores?

        int previousQueen = 0;
        boolean isGood = true;

        while (previousQueen < queen && isGood) { //Comparar solo con las reinas anteriores
            if (board[previousQueen] == column) { //Coinciden columnas de las 2 reinas
                isGood = false;
            } else if (Math.abs(previousQueen - queen) == Math.abs(board[previousQueen] - column)) { //Diagonales
                isGood = false;
            }
            previousQueen++;
        }

        return isGood;
    }

    // Exercise 5
    public static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    boolean objetivo = false;

                    for (int i = 1; i <= 9 && !objetivo; i++) {
                        if (canInsert(board, row, col, i)) {
                            board[row][col] = i;

                            if (row == 8 && col == 8) {
                                objetivo = true;
                            } else {
                                objetivo = solveSudoku(board);
                                if (!objetivo) {
                                    board[row][col] = 0;
                                }
                            }
                        }
                    }   //END_FOR_CANDIDATES
                    return objetivo;
                }   //END_IF
            }   //END_FOR_COL
        }   //END_FOR_ROW

        return true;
    }

    private static boolean canInsert(int[][] board, int i, int j, int value) {
        // Comprueba columna
        for (int a = 0; a < SUDOKU_DIMENSION; a++) {
            if (a != i && board[a][j] == value) {
                return false;
            }
        }
        // Comprueba fila
        for (int a = 0; a < SUDOKU_DIMENSION; a++) {
            if (a != j && board[i][a] == value) {
                return false;
            }
        }
        // Comprueba cuadrado
        int y = (i / 3) * 3; // me coloco en la primera fila del cuadrado a comprobar
        int x = (j / 3) * 3; // me coloco en la primera column del cuadrado a comprobar
        for (int a = 0; a < 3; a++) { // cuadrado tiene 3 filas
            for (int b = 0; b < 3; b++) { // cuadrado tiene 3 columns
                if (a != i && b != j && board[y + a][x + b] == value) {
                    return false;
                }
            }
        }
        return true;
    }

    // Exercise 6
    public static boolean rehearseWords(char[][] maze, int positionX, int positionY, String word, int positionWord) {
        boolean objetivo = false;

        //Es cuadrada pero lo comprobamos igualmente
        int n = maze.length;
        int m = maze[0].length;

        // Checkear que las posiciones sean correctas (para los movimientos que se intenten hacer)
        if (positionX >= 0 && positionX < n && positionY >= 0 && positionY < m) {
            if (maze[positionX][positionY] == word.charAt(positionWord)) {
                char originalChar = maze[positionX][positionY];
                maze[positionX][positionY] = ' '; // Marcar como visitado

                // Si llegamos a la última posición posible...
                if (positionX == n - 1 && positionY == m - 1) {
                    objetivo = true;
                } else {
                    int nextWordPos = (positionWord + 1) % word.length();

                    // Intentar moverse en las 4 direcciones
                    if (rehearseWords(maze, positionX + 1, positionY, word, nextWordPos)    // DERECHA
                        || rehearseWords(maze, positionX, positionY + 1, word, nextWordPos) // ABAJO
                        || rehearseWords(maze, positionX - 1, positionY, word, nextWordPos) // IZQUIERDA
                        || rehearseWords(maze, positionX, positionY - 1, word, nextWordPos))// ARRIBA
                    {
                        objetivo = true;
                    }

                    if (!objetivo) {
                        maze[positionX][positionY] = originalChar; // Backtracking: restaurar valor
                    }
                }
            }
        }

        return objetivo;
    }

    // Exercise 7
    /*
     * Respuestas a las cuestiones de diseño:
     * a) Representación: Se usa PuzzleCard. 'availableSides' gestiona la orientación y lados libres tras colocarla.
     * b) Esquinas: Se detectan por índices (0,0), (0,SIZE), etc. Las comprobaciones varían según qué vecinos (izquierda/arriba) existan ya.
     * c) Bordes: Se detectan si i o j son 0/SIZE. Se comprueba que tengan un '0' y encajen con los vecinos colocados (izq y/o arriba).
     * d) Centro: No pueden tener '0'. Deben encajar con el vecino de la izquierda y el de arriba.
     * e) Backtracking: Al retirar una ficha, se debe resetear su estado y restaurar los lados disponibles de los vecinos que se habían conectado a ella.
     */
    public static boolean solvePuzzle(PuzzleCard[][] board, List<PuzzleCard> cards) {
        for (int row = 0; row < PUZZLE_DIMENSION; row++) {
            for (int column = 0; column < PUZZLE_DIMENSION; column++) {
                //Si no hay carta colocada se prueba a colocar UNA
                if (board[row][column] == null) {
                    /*
                     * Una vez encontrada una casilla vacía (i, j),
                     * itera sobre todas las cartas disponibles en la lista cards.
                     * */

                    boolean objetivo = false;

                    //Probamos con todas las cartas
                    for (int k = 0; k < cards.size() && !objetivo; k++) {
                        PuzzleCard card = cards.get(k);
                        boolean fits = false;

                        // Resetear estado de la ficha antes de probar
                        card.setAvailableSides(card.getNumPuzzleCard());

                        // Si está en una esquina...
                        if ((row == 0 || row == PUZZLE_DIMENSION - 1) && (column == 0 || column == PUZZLE_DIMENSION - 1)) {
                            fits = canInsertCorner(board, row, column, card);
                            // Si está en el borde del puzzle (esto implicaría que también podría ser un borde,
                            // pero como ya lo comprobamos antes, nos libramos de más lógica
                        } else if ((row == 0 || row == PUZZLE_DIMENSION - 1) || (column == 0 || column == PUZZLE_DIMENSION - 1)) {
                            fits = canInsertEdge(board, row, column, card);
                            // "Centre" se refiere a cualquier otra posición que no sea esquina o borde
                        } else {
                            fits = isPossibleInsertCentre(board, row, column, card);
                        }

                        if (fits) {
                            // Colocamos ficha
                            board[row][column] = card;
                            // Eliminamos de la lista de cartas para usar
                            cards.remove(k);

                            // Delegamos el resto de pruebas a la recursividad
                            objetivo = solvePuzzle(board, cards);

                            // Si no es válida
                            if (!objetivo) {
                                // Liberamos el espacio del tablero
                                board[row][column] = null;
                                // Reañadimos la carta a la lista
                                cards.add(k, card);
                                // Reseteamos el estado de la ficha, ya que
                                // las operaciones realizadas en las funciones
                                // "canInsert" modifican el estado
                                card.setAvailableSides(card.getNumPuzzleCard());
                                // Reparamos el estado de los vecinos
                                changeSidesPuzzleCards(board);
                            }
                        }
                    }
                    // Vamos bien por el camino o no
                    return objetivo;
                }
            }
        }
        /* Si no hay huecos libres (que significa que nos saltamos
         * todas las pruebas de cartas porque no usamos el return del
         * medio), el puzzle está resuelto
         */
        return true;
    }

    // Una vez que elimina una ficha del tablero, deja la ficha y el tablero en la situación anterior a colocarla
    private static void changeSidesPuzzleCards(PuzzleCard[][] board) {
        PuzzleCard[][] board2 = new PuzzleCard[board.length][board.length];

        for (int i = 0; i < PUZZLE_DIMENSION; i++) {
            for (int j = 0; j < PUZZLE_DIMENSION; j++) {
                PuzzleCard k = board[i][j];
                if (k != null) {
                    k.setAvailableSides(k.getNumPuzzleCard());
                    if (canInsertCorner(board2, i, j, k) || canInsertEdge(board2, i, j, k) || isPossibleInsertCentre(board2, i, j, k)) {
                        board2[i][j] = k;
                    }
                }
            }
        }

        board = board2;
    }

    /*
     * Comprueba si es posible colocar la ficha en una de las esquinas del tablero. Para que devuelva true, la ficha debe
     * contener la subcadena “00”, las posiciones i-j deben ser esquina, y la ficha encaja con las fichas con las que
     * linda que ya están colocadas en el tablero. En otro caso devolverá falso.
     */
    private static boolean canInsertCorner(PuzzleCard[][] board, int i, int j, PuzzleCard value1) {
        if (value1.getNumPuzzleCard().contains("00")) {
            String piece1;
            String piece2;

            String value = "";
            // rotar ficha y eliminar esquinas
            for (int p = 0; p < value1.getNumPuzzleCard().length(); p++) {
                // roto a partir de un borde trato la ficha como algo circular
                int position = (value1.getNumPuzzleCard().indexOf('0') + p) % value1.getNumPuzzleCard().length();
                if (value1.getNumPuzzleCard().charAt(position) != '0') {
                    value += value1.getNumPuzzleCard().charAt(position);
                }
            }

            if (i == 0 && j == 0) {
                value1.setAvailableSides(value);
                return true;
            } else if (i == 0 && j == PUZZLE_DIMENSION - 1) {
                boolean isPossible = false;
                piece1 = board[0][PUZZLE_DIMENSION - 2].getAvailableSides();
                if (board[0][PUZZLE_DIMENSION - 2].getAvailableSides().charAt(0) == value.charAt(value.length() - 1)) {
                    isPossible = true;
                    board[0][PUZZLE_DIMENSION - 2].setAvailableSides(board[0][PUZZLE_DIMENSION - 2].getAvailableSides().substring(1)); // quito el primer lado
                    value = value.substring(0, value.length() - 1); // quito el último lado
                }

                if (isPossible) {
                    value1.setAvailableSides(value);
                } else {
                    board[0][PUZZLE_DIMENSION - 2].setAvailableSides(piece1);
                }

                return isPossible;
            } else if (i == PUZZLE_DIMENSION - 1 && j == 0) {
                boolean isPossible = false;
                piece1 = board[PUZZLE_DIMENSION - 2][0].getAvailableSides();
                if (board[PUZZLE_DIMENSION - 2][0].getAvailableSides().charAt(0) == value.charAt(0)) {
                    // A la ficha de arriba solo le queda un lado disponible
                    isPossible = true;
                    board[PUZZLE_DIMENSION - 2][0].setAvailableSides("");// quito el último lado
                    value = value.substring(1); // quito el primer lado
                }

                if (isPossible) {
                    value1.setAvailableSides(value);
                } else {
                    board[PUZZLE_DIMENSION - 2][0].setAvailableSides(piece1);
                }

                return isPossible;
            } else if (i == PUZZLE_DIMENSION - 1 && j == PUZZLE_DIMENSION - 1) {
                boolean isPossible = false;
                piece2 = board[PUZZLE_DIMENSION - 2][PUZZLE_DIMENSION - 1].getAvailableSides();
                if (board[PUZZLE_DIMENSION - 2][PUZZLE_DIMENSION - 1].getAvailableSides().charAt(0) == value.charAt(value.length() - 1)) {
                    isPossible = true;
                    board[PUZZLE_DIMENSION - 2][PUZZLE_DIMENSION - 1].setAvailableSides("");// quito el último lado
                    value = value.substring(0, value.length() - 1); // quito el último lado
                }

                if (isPossible) {
                    piece1 = board[PUZZLE_DIMENSION - 1][PUZZLE_DIMENSION - 2].getAvailableSides();
                    isPossible = false;
                    if (board[PUZZLE_DIMENSION - 1][PUZZLE_DIMENSION - 2].getAvailableSides().charAt(0) == value.charAt(0)) {
                        // en ambas cards me queda un lado disponible
                        isPossible = true;
                        board[PUZZLE_DIMENSION - 1][PUZZLE_DIMENSION - 2].setAvailableSides("");// quito el último lado
                        value = ""; // quito el último lado
                    }

                    if (isPossible) {
                        value1.setAvailableSides(value);
                    } else {
                        board[PUZZLE_DIMENSION - 1][PUZZLE_DIMENSION - 2].setAvailableSides(piece1);
                        board[PUZZLE_DIMENSION - 2][PUZZLE_DIMENSION - 1].setAvailableSides(piece2);
                    }

                } else {
                    board[PUZZLE_DIMENSION - 2][PUZZLE_DIMENSION - 1].setAvailableSides(piece2);
                }

                return isPossible;
            }

        }

        return false;
    }

    /*
     * Comprueba si es posible colocar la ficha en uno de los bordes del tablero. Para que devuelva true la ficha debe
     * contener el carácter “0”, las posiciones i-j deben ser borde, y la ficha encaja con las fichas con las que linda
     * que ya están colocadas en el tablero. En otro caso devolverá falso.
     */
    private static boolean canInsertEdge(PuzzleCard[][] board, int i, int j, PuzzleCard puzzleCard) {
        if (puzzleCard.getNumPuzzleCard().contains("0") && !puzzleCard.getNumPuzzleCard().contains("00")) {
            String piece1;
            String piece2;

            boolean isPossible = false;
            String value = "";
            // rotar ficha y eliminar borde
            for (int p = 0; p < puzzleCard.getNumPuzzleCard().length(); p++) {
                int position = (puzzleCard.getNumPuzzleCard().indexOf('0') + p) % puzzleCard.getNumPuzzleCard().length();
                if (puzzleCard.getNumPuzzleCard().charAt(position) != '0') {
                    value += puzzleCard.getNumPuzzleCard().charAt(position);
                }
            }

            if (i == 0 && j > 0 && j < PUZZLE_DIMENSION - 1) { // borde de arriba
                piece1 = board[0][j - 1].getAvailableSides();
                if (board[0][j - 1].getAvailableSides().charAt(0) == value.charAt(value.length() - 1)) {
                    isPossible = true;
                    board[0][j - 1].setAvailableSides(board[0][j - 1].getAvailableSides().substring(1));// quito el primer lado
                    value = value.substring(0, value.length() - 1); // quito el último lado
                }
                if (isPossible) {
                    puzzleCard.setAvailableSides(value);
                } else {
                    board[0][j - 1].setAvailableSides(piece1);
                }

                return isPossible;
            } else if (i == PUZZLE_DIMENSION - 1 && j > 0 && j < PUZZLE_DIMENSION - 1) { // borde de abajo
                piece2 = board[PUZZLE_DIMENSION - 2][j].getAvailableSides();
                if (board[PUZZLE_DIMENSION - 2][j].getAvailableSides().charAt(0) == value.charAt(1)) {
                    isPossible = true;
                    board[PUZZLE_DIMENSION - 2][j].setAvailableSides("");// quito el primer lado
                    value = value.charAt(0) + value.substring(2); // quito el lado del medio
                }

                if (isPossible) {
                    isPossible = false;
                    piece1 = board[PUZZLE_DIMENSION - 1][j - 1].getAvailableSides();
                    if (board[PUZZLE_DIMENSION - 1][j - 1].getAvailableSides().charAt(0) == value.charAt(0)) {
                        // En la ficha anterior sólo le queda un lado

                        isPossible = true;
                        board[PUZZLE_DIMENSION - 1][j - 1].setAvailableSides("");// quito el lado
                        value = value.substring(1); // quito el primer lado
                    }

                    if (isPossible) {
                        puzzleCard.setAvailableSides(value);
                    } else {
                        board[PUZZLE_DIMENSION - 1][j - 1].setAvailableSides(piece1);
                        board[PUZZLE_DIMENSION - 2][j].setAvailableSides(piece2);
                    }

                } else {
                    board[PUZZLE_DIMENSION - 2][j].setAvailableSides(piece2);
                }

                return isPossible;

            } else if (i > 0 && i < PUZZLE_DIMENSION - 1 && j == 0) { // borde izquierda
                piece1 = board[i - 1][j].getAvailableSides();
                if (board[i - 1][j].getAvailableSides().charAt(0) == value.charAt(0)) {
                    isPossible = true;
                    board[i - 1][j].setAvailableSides("");// quito el primer lado
                    value = value.substring(1); // quito el primer lado
                }

                if (isPossible) {
                    puzzleCard.setAvailableSides(value);
                } else {
                    board[i - 1][j].setAvailableSides(piece1);
                }

                return isPossible;
            } else if (i > 0 && i < PUZZLE_DIMENSION - 1 && j == PUZZLE_DIMENSION - 1) { // borde derecha
                piece1 = board[i - 1][j].getAvailableSides();
                if (board[i - 1][j].getAvailableSides().charAt(0) == value.charAt(value.length() - 1)) {
                    isPossible = true;
                    board[i - 1][j].setAvailableSides("");// quito el primer lado
                    value = value.substring(0, value.length() - 1); // quito el último lado
                }

                if (isPossible) {
                    piece2 = board[i][j - 1].getAvailableSides();
                    isPossible = false;
                    if (board[i][j - 1].getAvailableSides().charAt(0) == value.charAt(value.length() - 1)) {
                        isPossible = true;
                        board[i][j - 1].setAvailableSides(board[i][j - 1].getAvailableSides().substring(1));// quito el primer lado
                        value = value.substring(0, value.length() - 1); // quito el último lado
                    }

                    if (isPossible) {
                        puzzleCard.setAvailableSides(value);
                    } else {
                        board[i - 1][j].setAvailableSides(piece1);
                        board[i][j - 1].setAvailableSides(piece2);
                    }

                } else {
                    board[i - 1][j].setAvailableSides(piece1);
                }

                return isPossible;
            }
        }
        return false;
    }

    /*
     * Comprueba si es posible colocar la ficha en el centro del tablero. Para que devuelva true la ficha no puede
     * contener un carácter “0”, las posiciones i-j deben ser centro, y la ficha encaja con las fichas con las que linda
     * que ya están colocadas en el tablero. En otro caso devolverá falso.
     */
    private static boolean isPossibleInsertCentre(PuzzleCard[][] board, int i, int j, PuzzleCard puzzleCard) {
        if (!puzzleCard.getNumPuzzleCard().contains("0")) {
            String value = puzzleCard.getNumPuzzleCard();
            if (i != 0 && j != 0 && i != PUZZLE_DIMENSION - 1 && j != PUZZLE_DIMENSION - 1) {
                String piece1;
                String piece2;
                boolean isPossible = false;
                piece1 = board[i - 1][j].getAvailableSides();

                for (int p = 0; p < value.length() && !isPossible; p++) {
                    if (board[i - 1][j].getAvailableSides().charAt(0) == value.charAt(p)) {
                        isPossible = true;
                        board[i - 1][j].setAvailableSides("");
                        // girar la ficha correctamente y eliminar usada
                        StringBuilder aux = new StringBuilder();
                        for (int x = 0; x < value.length(); x++) {
                            int pos = (p + x) % value.length();
                            if (pos != p) {
                                aux.append(value.charAt(pos));
                            }
                        }
                        value = aux.toString();
                    }
                }

                if (isPossible) {
                    piece2 = board[i][j - 1].getAvailableSides();
                    isPossible = false;
                    if (board[i][j - 1].getAvailableSides().charAt(0) == value.charAt(value.length() - 1)) {
                        isPossible = true;
                        board[i][j - 1].setAvailableSides(board[i][j - 1].getAvailableSides().substring(1));// quito el primer lado
                        value = value.substring(0, value.length() - 1); // quito el último lado
                    }

                    if (isPossible) {
                        puzzleCard.setAvailableSides(value);
                    } else {
                        board[i - 1][j].setAvailableSides(piece1);
                        board[i][j - 1].setAvailableSides(piece2);
                    }
                } else {
                    board[i - 1][j].setAvailableSides(piece1);
                }

                return isPossible;
            }
        }

        return false;
    }

    // Exercise 8
    public static boolean exitMaze(char[][] maze, int positionX, int positionY) {
        boolean objetivo = false;

        if ((positionX >= 0 && positionX < maze.length) && (positionY >= 0 && positionY < maze[0].length)) {
            char candidato = maze[positionX][positionY];

            // Miramos si es alguna de las excepciones
            if (candidato == 'X' || candidato == 'C' || candidato == 'I') {
                return false;
            }

            // Caso base
            if (positionX == maze.length - 1 && positionY == maze[0].length - 1) {
                maze[positionX][positionY] = 'C';
                return true;
            }

            // CASO TELEPORT
            if (candidato == 'T') {
                // Marcamos la T actual como camino andado
                maze[positionX][positionY] = 'C';

                for (int i = 0; i < maze.length && !objetivo; i++) {
                    for (int j = 0; j < maze[i].length && !objetivo; j++) {
                        char place = maze[i][j];

                        // Buscamos la OTRA T (la actual ya es 'C', así que no la encontrará a sí misma)
                        if (place == 'T') {
                            int newX = i;
                            int newY = j;

                            maze[newX][newY] = 'C'; // Marcamos destino

                            // Extra: ¿Y si el destino del teletransporte es la meta?
                            if (newX == maze.length - 1 && newY == maze[0].length - 1) {
                                return true;
                            }

                            // Exploramos desde la nueva T. Prioridad: Abajo, Derecha
                            objetivo = (
                                exitMaze(maze, newX + 1, newY)      // Abajo
                                    || exitMaze(maze, newX, newY + 1)   // Derecha
                                    || exitMaze(maze, newX - 1, newY)   // Arriba
                                    || exitMaze(maze, newX, newY - 1)   // Izquierda
                            );

                            if (!objetivo) {
                                maze[newX][newY] = 'I';
                                maze[positionX][positionY] = 'I';
                            }
                        }
                    }
                }
            }

            // No buscado
            if (candidato == ' ') {
                maze[positionX][positionY] = 'C';

                // Intentamos ir hacia la meta en el orden indicado si no falla el test.
                // Esto evita explorar y marcar como 'I' zonas innecesarias.
                objetivo = (
                    exitMaze(maze, positionX + 1, positionY)      // Abajo
                        || exitMaze(maze, positionX, positionY + 1)      // Derecha
                        || exitMaze(maze, positionX - 1, positionY)      // Arriba
                        || exitMaze(maze, positionX, positionY - 1)      // Izquierda
                );

                if (!objetivo) {
                    maze[positionX][positionY] = 'I';
                }
            }
        }
        return objetivo;
    }

}
