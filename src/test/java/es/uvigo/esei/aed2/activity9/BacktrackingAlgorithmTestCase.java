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

import static es.uvigo.esei.aed2.activity8.data.IsEqualToMap.equalToMap;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import es.uvigo.esei.aed2.activity9.data.BacktrackingRepository;
import es.uvigo.esei.aed2.map.HashMap;
import es.uvigo.esei.aed2.map.Map;

public class BacktrackingAlgorithmTestCase {

  private final BacktrackingRepository dataBacktracking = new BacktrackingRepository();

  /**
   * Test of giveChange method, of class BacktrackingAlgorithm.
   */
  @Test
  public void testGiveChange() {
    Map<Integer, Integer> expectedMap = this.dataBacktracking.getMapGiveChangeResult();

    Map<Integer, Integer> resultMap = new HashMap<>();
    BacktrackingAlgorithm.giveChange(2100, this.dataBacktracking.getMapGiveChange(), resultMap);

    assertThat(resultMap, is(equalToMap(expectedMap)));
  }

  /**
   * Test of fillUpFlashDrive method, of class BacktrackingAlgorithm.
   */
  @Test
  public void testFillUpFlashDrive() {
    Set<String> expectedMap = this.dataBacktracking.getProgramResult();

    Set<String> resultMap = new HashSet<>();
    BacktrackingAlgorithm.fillUpFlashDrive(21, this.dataBacktracking.getMapFlash(), resultMap);

    assertThat(resultMap, is(equalTo(expectedMap)));
  }

  /**
   * Test of subgroups method, of class BacktrackingAlgorithm.
   */
  @Test
  public void testSubgroups() {
    int[] expectedSubgroups = this.dataBacktracking.getSubgroupsResult();
    
    int[] resultSubgroups = new int[expectedSubgroups.length];
    BacktrackingAlgorithm.subgroups(this.dataBacktracking.getValues(), resultSubgroups, 21, 0);

    assertThat(resultSubgroups, is(equalTo(expectedSubgroups)));
  }

  /**
   * Test of placeQueens method, of class BacktrackingAlgorithm.
   */
  @Test
  public void testPlaceQueens() {
    int[] expectedQueens = this.dataBacktracking.getQueensResult();
    
    int[] resultQueens = new int[expectedQueens.length];
    BacktrackingAlgorithm.placeQueens(0, resultQueens);
    
    assertThat(resultQueens, is(equalTo(expectedQueens)));
  }

  /**
   * Test of solveSudoku method, of class BacktrackingAlgorithm.
   */
  @Test
  public void testSolveSudoku() {
    int[][] expectedSolution = this.dataBacktracking.getSudokuBoardResult();
    
    int[][] resulSolution = this.dataBacktracking.getSudokuBoard();
    BacktrackingAlgorithm.solveSudoku(resulSolution);

    assertThat(resulSolution, is(equalTo(expectedSolution)));
  }

  /**
   * Test of rehearseWords method, of class BacktrackingAlgorithm.
   */
  @Test
  public void testRehearseWords() {
    char[][] expectedMazeWords = this.dataBacktracking.getMazeWordsResult();
    char[][] resultMazeWords = this.dataBacktracking.getMazeWords();
    
    BacktrackingAlgorithm.rehearseWords(resultMazeWords, 0, 0, "ADE", 0);
    
    assertThat(resultMazeWords, is(equalTo(expectedMazeWords)));
  }

  /**
   * Test of solvePuzzle method, of class BacktrackingAlgorithm.
   */
  @Test
  public void testSolvePuzzle() {
    PuzzleCard[][] expectedSolution = this.dataBacktracking.getBoardResult();
    
    PuzzleCard[][] resultSolution = new PuzzleCard[expectedSolution.length][expectedSolution.length];
    BacktrackingAlgorithm.solvePuzzle(resultSolution, this.dataBacktracking.getListPuzzleCards());
    
    assertThat(resultSolution, is(equalTo(expectedSolution)));
  }

  /**
   * Test of exitMaze method, of class BacktrackingAlgorithm.
   */
  @Test
  public void testExitMaze() {
    char[][] expectedPath = this.dataBacktracking.getMazeResult();
    char[][] resultPath = this.dataBacktracking.getMaze();
    
    BacktrackingAlgorithm.exitMaze(resultPath, 0, 0);
    
    assertThat(resultPath, is(equalTo(expectedPath)));
  }

}
