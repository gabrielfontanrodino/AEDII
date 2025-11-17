package es.uvigo.esei.aed2.activity9.data;

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

import es.uvigo.esei.aed2.activity9.PuzzleCard;
import es.uvigo.esei.aed2.map.HashMap;
import es.uvigo.esei.aed2.map.Map;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class BacktrackingRepository {

  private final static List<Integer> KEYS_Ch = List.of(500, 200);
  private final static List<Integer> VALUES_Ch = List.of(20, 10);
  private final static List<Integer> KEYS_ChResult = List.of(500, 200);
  private final static List<Integer> VALUES_ChResult = List.of(3, 3);

  private final static List<String> KEYS_Flash = List.of("p1", "p2", "p3", "p4", "p5");
  private final static List<Integer> VALUES_Flash = List.of(10, 3, 5, 3, 3);

  private final static Set<String> programResult = new HashSet<>() {
    {
      add("p4");
      add("p3");
      add("p2");
      add("p1");
    }
  };
  private final static int[] values = {4, 7, 3, 5, 1, 9, 8, 2, 6};
  private final static int[] subgroupsResult = {1, 1, 1, 1, 0, 0, 0, 1, 0};
  
  private static final char[][] maze = {{' ','T',' ','X'},
                                        {' ',' ',' ',' '},
                                        {' ','T','X',' '},
                                        {' ','X',' ',' '}};
  
  private static final char [][] mazeResult = {{'C','C',' ','X'},
                                               {'C','C','C','C'},
                                               {'C','C','X','C'},
                                               {'I','X',' ','C'}};
  
  private static final char[][] mazeWords = {{'A','O','O','X','X'},
                                             {'D','D','X','O','X'},
                                             {'E','A','D','E','X'},
                                             {'O','D','O','A','D'},
                                             {'O','O','O','A','E'}};
  
  private static final char [][] mazeWordsResult = {{' ','O','O','X','X'},
                                                    {' ','D','X','O','X'},
                                                    {' ',' ',' ',' ','X'},
                                                    {'O','D','O',' ',' '},
                                                    {'O','O','O','A',' '}};
  
  private static final int [] queensResult = {0,4,7,5,2,6,1,3};
  
  
  private final static PuzzleCard[][] boardResult = {{new PuzzleCard("0054"), new PuzzleCard("0135"),new PuzzleCard("0321"),new PuzzleCard("0043")},
                                                     {new PuzzleCard("4210"), new PuzzleCard("2354"),new PuzzleCard("1523"),new PuzzleCard("0234")},
                                                     {new PuzzleCard("5201"), new PuzzleCard("3542"),new PuzzleCard("3421"),new PuzzleCard("3205")},
                                                     {new PuzzleCard("2400"), new PuzzleCard("4320"),new PuzzleCard("2410"),new PuzzleCard("1500")}};

    
  private final static List<PuzzleCard> listCards = new LinkedList<>(){
    {
      add(new PuzzleCard("0321"));
      add(new PuzzleCard("0135"));
      add(new PuzzleCard("0054"));
      add(new PuzzleCard("1523"));
      add(new PuzzleCard("2354"));
      add(new PuzzleCard("3421"));
      add(new PuzzleCard("2410"));
      add(new PuzzleCard("3205"));
      add(new PuzzleCard("1500"));
      add(new PuzzleCard("4320"));
      add(new PuzzleCard("2400"));
      add(new PuzzleCard("0043"));
      add(new PuzzleCard("3542"));
      add(new PuzzleCard("4210"));
      add(new PuzzleCard("5201"));
      add(new PuzzleCard("0234"));
    }
  };
  
  private final static int[][] SudokuBoard = new int[][]{
    {0, 7, 0, 0, 0, 0, 0, 8, 0},
    {0, 5, 0, 6, 0, 0, 0, 0, 1},
    {0, 0, 3, 1, 4, 0, 0, 0, 0},
    {9, 0, 6, 0, 5, 0, 3, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 5, 0, 2, 0, 1, 0, 7},
    {0, 0, 0, 0, 6, 5, 7, 0, 0},
    {3, 0, 0, 0, 0, 1, 9, 2, 0},
    {0, 4, 0, 0, 0, 0, 0, 1, 0},
  };
  
  private final static int[][] SudokuBoardResult = new int [][]{
    {4,7,1,5,9,2,6,8,3},
    {2,5,9,6,8,3,4,7,1},
    {6,8,3,1,4,7,2,5,9},
    {9,1,6,7,5,8,3,4,2},
    {7,2,4,3,1,6,5,9,8},
    {8,3,5,9,2,4,1,6,7},
    {1,9,2,8,6,5,7,3,4},
    {3,6,8,4,7,1,9,2,5},
    {5,4,7,2,3,9,8,1,6},
  };

  private <K, V> Map<K, V> createMapWith(List<K> keys, List<V> values) {
    Map<K, V> map = new HashMap<>();

    for (int i = 0; i < keys.size(); i++) {
      map.add(keys.get(i), values.get(i));
    }
    return map;
  }

  public Map<Integer, Integer> getMapGiveChange() {
    return this.createMapWith(KEYS_Ch, VALUES_Ch);
  }

  public Map<Integer, Integer> getMapGiveChangeResult() {
    return this.createMapWith(KEYS_ChResult, VALUES_ChResult);
  }

  public Map<String, Integer> getMapFlash() {
    return this.createMapWith(KEYS_Flash, VALUES_Flash);
  }

  public Set<String> getProgramResult() {
    return programResult;
  }

  public int[] getValues() {
    return values;
  }

  public int[] getSubgroupsResult() {
    return subgroupsResult;
  }

  public List<PuzzleCard> getListPuzzleCards() {
    return listCards;
  }

  public PuzzleCard[][] getBoardResult() {
    return boardResult;
  }

  public char[][] getMaze() {
    return maze;
  }

  public char[][] getMazeResult() {
    return mazeResult;
  }

  public char[][] getMazeWords() {
    return mazeWords;
  }

  public char[][] getMazeWordsResult() {
    return mazeWordsResult;
  }

  public int[] getQueensResult() {
    return queensResult;
  }

  public int[][] getSudokuBoard() {
    return SudokuBoard;
  }

  public int[][] getSudokuBoardResult() {
    return SudokuBoardResult;
  }
}
