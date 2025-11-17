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

import es.uvigo.esei.aed2.activity8.Activity8;
import es.uvigo.esei.aed2.graph.Graph;
import es.uvigo.esei.aed2.graph.Vertex;
import es.uvigo.esei.aed2.graph.util.GraphParser;
import es.uvigo.esei.aed2.map.HashMap;
import es.uvigo.esei.aed2.map.Map;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import java.util.function.Function;
import java.util.function.Supplier;

public class GreedyRepository {

  private final static String GRAPH_TRAVELLER_PRIM = """
vertices:
  - ONE
  - TWO
  - THREE
  - FOUR
  - FIVE
  - SEIX
edges:
  - from: ONE
    to: TWO
    value: 3
  - from: TWO
    to: ONE
    value: 3
  - from: TWO
    to: THREE
    value: 7
  - from: THREE
    to: TWO
    value: 7
  - from: THREE
    to: FOUR
    value: 5
  - from: FOUR
    to: THREE
    value: 5
  - from: TWO
    to: SEIX
    value: 10
  - from: SEIX
    to: TWO
    value: 10
  - from: ONE
    to: SEIX
    value: 5
  - from: SEIX
    to: ONE
    value: 5
  - from: THREE
    to: FIVE
    value: 1
  - from: FIVE
    to: THREE
    value: 1
  - from: SEIX
    to: FOUR
    value: 2
  - from: FOUR
    to: SEIX
    value: 2
  - from: SEIX
    to: THREE
    value: 8
  - from: THREE
    to: SEIX
    value: 8
  - from: FOUR
    to: FIVE
    value: 6
  - from: FIVE
    to: FOUR
    value: 6
""";

  private final static String GRAPH_TRAVELLER = """
vertices:
  - ONE
  - TWO
  - THREE
  - FOUR
  - FIVE
  - SEIX
edges:
  - from: ONE
    to: TWO
    value: 3
  - from: TWO
    to: THREE
    value: 7
  - from: THREE
    to: FIVE
    value: 1
  - from: FIVE
    to: FOUR
    value: 6
  - from: FOUR
    to: SEIX
    value: 2
""";

  private final static String GRAPH_PRIM = """
vertices:
  - ONE
  - TWO
  - THREE
  - FOUR
  - FIVE
  - SEIX
edges:
  - from: ONE
    to: TWO
    value: 3
  - from: SEIX
    to: ONE
    value: 5
  - from: FOUR
    to: THREE
    value: 5
  - from: THREE
    to: FIVE
    value: 1
  - from: SEIX
    to: FOUR
    value: 2
""";

  private final static String GRAPH_COLOUR = """
vertices:
  - ONE
  - TWO
  - THREE
  - FOUR
  - FIVE
  - SEIX
edges:
  - from: ONE
    to: TWO
    value: 3
  - from: TWO
    to: ONE
    value: 3
  - from: ONE
    to: SEIX
    value: 5
  - from: SEIX
    to: ONE
    value: 5
  - from: TWO
    to: THREE
    value: 7
  - from: THREE
    to: TWO
    value: 7
  - from: TWO
    to: SEIX
    value: 10
  - from: SEIX
    to: TWO
    value: 10
  - from: SEIX
    to: THREE
    value: 8
  - from: THREE
    to: SEIX
    value: 8
  - from: SEIX
    to: FOUR
    value: 2
  - from: FOUR
    to: SEIX
    value: 2
  - from: THREE
    to: FOUR
    value: 5
  - from: FOUR
    to: THREE
    value: 5
  - from: THREE
    to: FIVE
    value: 1
  - from: FIVE
    to: THREE
    value: 1
  - from: FOUR
    to: FIVE
    value: 6
  - from: FIVE
    to: FOUR
    value: 6
  - from: TWO
    to: FOUR
    value: 6
  - from: FOUR
    to: TWO
    value: 6
""";

  private final static String GRAPH_SCHEDULER = """
vertices:
  - SO
  - BD
  - AL
  - AEDII
  - EST
edges:
  - from: SO
    to: AL
    value: 0
  - from: AL
    to: SO
    value: 0
  - from: SO
    to: AEDII
    value: 0
  - from: AEDII
    to: SO
    value: 0
  - from: SO
    to: EST
    value: 0
  - from: EST
    to: SO
    value: 0
  - from: SO
    to: BD
    value: 0
  - from: BD
    to: SO
    value: 0
  - from: AEDII
    to: BD
    value: 0
  - from: BD
    to: AEDII
    value: 0
  - from: AL
    to: AEDII
    value: 0
  - from: AEDII
    to: AL
    value: 0
""";

  private final static List<Vertex<String>> KEYS_DIJKSTRA = List.of(
    new Vertex<>("ONE"), new Vertex<>("TWO"), new Vertex<>("THREE"), new Vertex<>("FOUR"), new Vertex<>("FIVE"),
    new Vertex<>("SEIX")
  );
  private final static List<Integer> VALUES_DIJKSTRA = List.of(0, 3, 10, 7, 11, 5);

  private final static List<Vertex<String>> KEYS_COLOUR = List.of(
    new Vertex<>("ONE"), new Vertex<>("TWO"), new Vertex<>("THREE"), new Vertex<>("FOUR"), new Vertex<>("FIVE"),
    new Vertex<>("SEIX")
  );
  private final static List<String> VALUES_COLOUR = List.of("red", "yellow", "yellow", "blue", "red", "green");
  private final static String[] COLOURS = { "red", "blue", "green", "yellow" };

  private final static List<Integer> KEYS_CHANGE = List.of(500, 200);
  private final static List<Integer> VALUES_CHANGE = List.of(20, 10);
  private final static List<Integer> KEYS_CHANGE_RESULT = List.of(500, 200);
  private final static List<Integer> VALUES_CHANGE_RESULT = List.of(2, 1);

  private final static List<String> KEYS_CD = List.of("p1", "p2", "p3", "p4", "p5");
  private final static List<Integer> VALUES_CD = List.of(10, 3, 5, 3, 3);

  private final static List<String> KEYS_RUCKSACK_VOLUMES = List.of("car", "doll");
  private final static List<Integer> VALUES_RUCKSACK_VOLUMES = List.of(10, 20);
  private final static List<String> KEYS_RUCKSACK_AMOUNTS = List.of("car", "doll");
  private final static List<Integer> VALUES_RUCKSACK_AMOUNTS = List.of(5, 4);
  private final static List<String> KEYS_RUCKSACK_RESULT = List.of("car", "doll");
  private final static List<Integer> VALUES_RUCKSACK_RESULT = List.of(1, 2);

  private final static List<Vertex<String>> KEYS_SCHEDULE = List
    .of(new Vertex<>("SO"), new Vertex<>("BD"), new Vertex<>("EST"), new Vertex<>("AEDII"), new Vertex<>("AL"));
  private final static List<String> VALUES_SCHEDULE = List.of("thursday", "monday", "monday", "tuesday", "monday");
  private final static String[] DAYS_WEEK = { "monday", "tuesday", "wednesday", "thursday", "friday" };

  private final List<Activity8> listActivities = new LinkedList<>();

  private final Set<String> programResult = new HashSet<>();
  private final Set<String> activitiesResult = new HashSet<>();

  private final GraphParser<String, Integer> parser;
  private final Supplier<Graph<String, Integer>> emptyGraphBuilder;

  public GreedyRepository(
    GraphParser.GraphBuilder<String, Integer> builder, Supplier<Graph<String, Integer>> emptyGraphBuilder
  ) {
    this.parser = new GraphParser<>(emptyGraphBuilder, builder, Function.identity(), Integer::parseInt);
    this.emptyGraphBuilder = emptyGraphBuilder;

    this.listActivities.add(new Activity8("act1", 8, 10));
    this.listActivities.add(new Activity8("act2", 9, 12));
    this.listActivities.add(new Activity8("act3", 10, 13));
    this.listActivities.add(new Activity8("act4", 13, 14));
    this.listActivities.add(new Activity8("act5", 10, 12));
    this.listActivities.add(new Activity8("act6", 12, 14));
    this.programResult.add("p1");
    this.programResult.add("p3");
    this.programResult.add("p2");
    this.programResult.add("p4");
    this.activitiesResult.add("act1");
    this.activitiesResult.add("act5");
    this.activitiesResult.add("act4");
  }

  public Graph<String, Integer> getEmptyGraph() {
    return this.emptyGraphBuilder.get();
  }

  // Activity8 8
  public Graph<String, Integer> getGraphTravellerPrim() {
    return this.parser.parseGraph(GRAPH_TRAVELLER_PRIM);
  }

  public Graph<String, Integer> getGraphTraveller() {
    return this.parser.parseGraph(GRAPH_TRAVELLER);
  }

  public Graph<String, Integer> getGraphPrim() {
    return this.parser.parseGraph(GRAPH_PRIM);
  }

  public Graph<String, Integer> getGraphColour() {
    return this.parser.parseGraph(GRAPH_COLOUR);
  }

  public Graph<String, Integer> getGraphScheduler() {
    return this.parser.parseGraph(GRAPH_SCHEDULER);
  }

  public Vertex<String> getVertexONE() {
    return new Vertex<>("ONE");
  }

  public Vertex<String> getVertexSEIX() {
    return new Vertex<>("SEIX");
  }

  private <K, V> Map<K, V> createMapWith(List<K> keys, List<V> values) {
    Map<K, V> map = new HashMap<>();

    for (int i = 0; i < keys.size(); i++) {
      map.add(keys.get(i), values.get(i));
    }
    return map;
  }

  public Map<Vertex<String>, Integer> getMapDijkstra() {
    return this.createMapWith(KEYS_DIJKSTRA, VALUES_DIJKSTRA);
  }

  public Map<Vertex<String>, String> getMapColour() {
    return this.createMapWith(KEYS_COLOUR, VALUES_COLOUR);
  }

  public Map<Integer, Integer> getMapChange() {
    return this.createMapWith(KEYS_CHANGE, VALUES_CHANGE);
  }

  public Map<Integer, Integer> getMapChangeResult() {
    return this.createMapWith(KEYS_CHANGE_RESULT, VALUES_CHANGE_RESULT);
  }

  public Map<String, Integer> getMapCD() {
    return this.createMapWith(KEYS_CD, VALUES_CD);
  }

  public Map<String, Integer> getMapRucksackVolumes() {
    return this.createMapWith(KEYS_RUCKSACK_VOLUMES, VALUES_RUCKSACK_VOLUMES);
  }

  public Map<String, Integer> getMapRucksackAmounts() {
    return this.createMapWith(KEYS_RUCKSACK_AMOUNTS, VALUES_RUCKSACK_AMOUNTS);
  }

  public Map<String, Integer> getMapRucksackResult() {
    return this.createMapWith(KEYS_RUCKSACK_RESULT, VALUES_RUCKSACK_RESULT);
  }

  public Map<Vertex<String>, String> getMapSchedule() {
    return this.createMapWith(KEYS_SCHEDULE, VALUES_SCHEDULE);
  }

  public String[] getColours() {
    return COLOURS;
  }

  public String[] getDaysWeek() {
    return DAYS_WEEK;
  }

  public List<Activity8> getListActivities() {
    return listActivities;
  }

  public Set<String> getProgramResult() {
    return programResult;
  }

  public Set<String> getActivitiesResult() {
    return activitiesResult;
  }

}
