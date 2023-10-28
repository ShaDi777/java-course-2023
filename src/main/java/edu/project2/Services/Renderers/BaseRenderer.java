package edu.project2.Services.Renderers;

import edu.project2.Entities.Maze;
import edu.project2.Models.Cell;
import edu.project2.Models.Coordinate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseRenderer implements Renderer {
    private static final char EMPTY_CELL = ' ';
    private static final char NEW_LINE = '\n';
    private final char wall;
    private final char pathCell;

    public BaseRenderer(char wallCell, char pathCell) {
        this.wall = wallCell;
        this.pathCell = pathCell;
    }

    @Override
    public String render(Maze maze) {
        return render(maze, List.of());
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        Map<Integer, List<Integer>> pathMap = new HashMap<>();
        for (Coordinate coordinate : path) {
            if (!pathMap.containsKey(coordinate.row())) {
                pathMap.put(coordinate.row(), new ArrayList<>());
            }
            pathMap.get(coordinate.row()).add(coordinate.col());
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int row = 0; row < maze.getHeight(); row++) {
            for (int col = 0; col < maze.getWidth(); col++) {
                if (maze.getGrid()[row][col].type() == Cell.Type.PASSAGE) {
                    if (pathMap.containsKey(row) && pathMap.get(row).contains(col)) {
                        stringBuilder.append(pathCell);
                    } else {
                        stringBuilder.append(EMPTY_CELL);
                    }
                    continue;
                }

                stringBuilder.append(wall);
            }
            stringBuilder.append(NEW_LINE);
        }
        return stringBuilder.toString();
    }
}
