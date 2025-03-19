import json

from helper import draw_coloured_map, generate_graph_map


def greedy(graph: dict[str, set], colors: list[str]) -> dict[str, str]:
    nodes = list(graph.keys())
    graph_colors = {node: None for node in nodes}
    nodes_to_color = [nodes[0]]

    while nodes_to_color:
        current_node = nodes_to_color.pop(0)

        neighbors = graph.get(current_node, set())
        colors_available = colors.copy()

        for neighbor in neighbors:
            if graph_colors.get(str(neighbor)) is not None:
                if graph_colors[str(neighbor)] in colors_available:
                    colors_available.remove(graph_colors[str(neighbor)])
            else:
                if(str(neighbor) not in nodes_to_color):
                    nodes_to_color.append(str(neighbor))

        graph_colors[current_node] = colors_available[0]

    return graph_colors


if __name__ == "__main__":
    n = 16384
    map = generate_graph_map(n)
    colors = ["red", "blue", "green", "yellow", "orange", "purple", "cyan", "magenta", "lime"]
    solution = greedy(map["graph"], colors)

    if solution:
        print("Solution found:", solution)
        draw_coloured_map(map, solution)
        with open('solution.json', 'w') as f:
            json.dump(solution, f)
            f.close()
    else:
        print("Solution not found.")
