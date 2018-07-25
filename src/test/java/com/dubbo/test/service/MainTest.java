package com.dubbo.test.service;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author zhangxin
 *         Created on 18/6/11.
 */
public class MainTest {

    public static final int N = 105;
    public static char map[][] = new char[N][N];
    public static int dir[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    public static Queue<Node> queue = new ArrayDeque<Node>();

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        while (cin.hasNext()) {
            int row = cin.nextInt();
            int column = cin.nextInt();
            cin.nextLine();
            for (int i = 0; i < row; i++) {
                String str = cin.nextLine();
                for (int ii = 0; ii < column; ii++) {
                    map[i][ii] = str.charAt(ii);
                }
            }

            int count = 0;
            for (int i = 0; i < row; i++) {
                for (int ii = 0; ii < column; ii++) {
                    if (map[i][ii] == 'W') {
                        bfs(i, ii, row, column);
                        count++;
                    }
                }
            }
            System.out.println(count);
        }
    }

    public static void bfs(int x, int y, int row, int column) {
        map[x][y] = '.';
        queue.offer(new Node(x, y));
        Node cur = null;
        while ((cur = queue.poll()) != null) {
            x = cur.x;
            y = cur.y;
            for (int i = 0; i < 8; i++) {
                int newx = x + dir[i][0];
                int newy = y + dir[i][1];
                if (newx < 0 || newx >= row || newy < 0 || newy >= column || map[newx][newy] == '.') {
                    continue;
                } else {
                    map[newx][newy] = '.';
                    queue.add(new Node(newx, newy));
                }
            }
        }
    }

    public static void show(int row, int column) {
        for (int i = 0; i < row; i++) {
            for (int ii = 0; ii < column; ii++) {
                System.out.print(map[i][ii]);
            }
            System.out.println();
        }
    }

    public static class Node {
        public int x;
        public int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

