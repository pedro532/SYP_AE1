package Manufacture;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Manufacture {
    private static final int MAX_MACHINES = 8;
    private List<String> piecesList = new ArrayList<>();

    public void startManufacturing(String type, int quantity, boolean writeToFile, String filename) {
        for (int i = 0; i < quantity; i++) {
            synchronized (this) {
                while (piecesList.size() >= MAX_MACHINES) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                String piece = type + "_" + getFormattedTimestamp();
                piecesList.add(piece);
                manufacturePiece(type);
                addToPiecesList(piece);
                if (writeToFile) {
                    writeToFile(filename);
                } else {
                    printToConsole(piece);
                }
                notifyAll();
            }
        }
    }

    private void manufacturePiece(String type) {
        try {
            int manufacturingTime = getManufacturingTime(type);
            Thread.sleep(manufacturingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            for (String piece : piecesList) {
                writer.println(piece);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getManufacturingTime(String type) {
        switch (type) {
            case "I":
                return 1000;
            case "O":
                return 2000;
            case "T":
                return 3000;
            case "J":
            case "L":
                return 4000;
            case "S":
            case "Z":
                return 5000;
            default:
                return 0;
        }
    }

    private synchronized void addToPiecesList(String piece) {
        piecesList.add(piece);
    }

    private void printToConsole(String message) {
        System.out.println(message);
    }

    private String getFormattedTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return dateFormat.format(new Date());
    }
}