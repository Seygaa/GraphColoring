# GraphColoring
An algorithm that colors the vertices of the graph with a maximum of 5 colors using 5-color theorem (very planar graph is 5-colorable).

## Input:

'Graph Vertex' : '1st Vertex Neighbor' , '2nd Vertex Neighbor', ...

## Output

'Graph Vertex' ( 'Vertex Color' ) :"1st Vertex Neighbor" ( 'Vertex neighbor Color' ) , ...

## Example
### Input
```
1: 2, 5, 6
2: 1, 3, 6
3: 2, 4, 6
4: 3, 5, 6
5: 1, 4, 6
6: 1, 2, 3, 4, 5
```

### Output

```

1(1): 2(2), 5(3), 6(4)
2(2): 1(1), 3(3), 6(4)
3(3): 2(2), 4(2), 6(4)
4(2): 3(3), 5(3), 6(4)
5(3): 1(1), 4(2), 6(4)
6(4): 1(1), 2(2), 3(3), 4(2), 5(3)
```
