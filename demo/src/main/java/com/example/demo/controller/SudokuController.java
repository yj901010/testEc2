package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
public class SudokuController {

    @GetMapping("/api/generate")
    public int[][] generateSudoku() {
        // 스토쿠 생성 로직 추가
        int[][] board = new int[9][9];
        // 임의로 채우는 로직 (여기서는 간단히 비워진 보드를 반환)
        return board;
    }

    @PostMapping("/api/validate")
    public boolean validateSudoku(@RequestBody int[][] board) {

        return isValidSudoku(board);
    }

    private boolean isValidSudoku(int[][] board) {

        for (int i = 0; i < 9; i++) {
            if (!isValidRow(board, i)) {
                System.out.print("실");
                return false;
            }
        }

        // 열 검증
        for (int j = 0; j < 9; j++) {
            if (!isValidColumn(board, j)) {
                System.out.print("실");
                return false;
            }
        }

        // 3x3 박스 검증
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                if (!isValidBox(board, i, j)) {
                    System.out.print("실");
                    return false;
                }
            }
        }
        System.out.print("성");

        return true; // 단순화된 검증 로직
    }

    private boolean isValidRow(int[][] board, int row) {
        Set<Integer> set = new HashSet<>();
        for (int j = 0; j < 9; j++) {
            int value = board[row][j];
            if (value == 0) {
                return false; // 하나라도 값이 입력되지 않았으면 false 반환
            }
            if (!set.add(value)) {
                return false; // 중복된 값이 존재하면 false 반환
            }
        }
        return true;
    }

    private boolean isValidColumn(int[][] board, int col) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            int value = board[i][col];
            if (value == 0) {
                return false; // 하나라도 값이 입력되지 않았으면 false 반환
            }
            if (!set.add(value)) {
                return false; // 중복된 값이 존재하면 false 반환
            }
        }
        return true;
    }

    private boolean isValidBox(int[][] board, int startRow, int startCol) {
        Set<Integer> set = new HashSet<>();
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                int value = board[i][j];
                if (value == 0) {
                    return false; // 하나라도 값이 입력되지 않았으면 false 반환
                }
                if (!set.add(value)) {
                    return false; // 중복된 값이 존재하면 false 반환
                }
            }
        }
        return true;
    }
}
