package vsu.labs.crypto.algs.compression.wavelet;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;


public final class Wavelet {

    private Wavelet() {}

    /**
     * Сжатие изображение с помощью вейвлета Хаара
     * @param pathToImage путь к изображению
     * @param newImagePath путь к сжатому изображению(с названием)
     * @param eps коэффициент, определяющий границу обнуления элементов во время сжатия
     * @return коэффициент сжатия изображения, выражающийся в усреднённом среди пикселей каждого цвета
     * отношении ненулевых элементов до и после сжатия
     */
    public static double haarCompression(String pathToImage, String newImagePath, double eps) {
        File imageSrc = new File(pathToImage);
        try {
            BufferedImage bufferedImageSrc = ImageIO.read(imageSrc);
            int w = bufferedImageSrc.getWidth(), h = bufferedImageSrc.getHeight();
            if (w != h || (w & (w - 1)) != 0)
                throw new IllegalArgumentException("Image size must be power of 2 and width must be equals height");
            String fileType = getFileExtension(newImagePath);
            if (fileType.equals(""))
                throw new IllegalArgumentException("File extension not specified for compressed image");
            double[][] pixRed = new double[w][h],pixBlue = new double[w][h], pixGreen = new double[w][h];
            for( int i = 0; i < w; i++ )
                for( int j = 0; j < h; j++ ) {
                    int pix = bufferedImageSrc.getRGB(i, j);
                    pixBlue[i][j] = pix & 0xFF;
                    pixGreen[i][j] = (pix >> 8) & 0xFF;
                    pixRed[i][j] = (pix >> 16) & 0xFF;
                }

            haarTransformData(pixBlue);
            int blueNonZerosOld = countNonZeros(pixBlue);
            zeroing(pixBlue, eps);
            haarTransformData(pixRed);
            int redNonZerosOld = countNonZeros(pixRed);
            zeroing(pixRed, eps);
            haarTransformData(pixGreen);
            int greenNonZerosOld = countNonZeros(pixGreen);
            zeroing(pixGreen, eps);

            int redNonZerosNew = countNonZeros(pixRed);
            int greenNonZerosNew = countNonZeros(pixGreen);
            int blueNonZerosNew = countNonZeros(pixBlue);
            double compCoef = (redNonZerosOld/(double)redNonZerosNew
                    + greenNonZerosOld/(double)greenNonZerosNew
                    + blueNonZerosOld/(double)blueNonZerosNew) / 3;

            haarTransformDataBack(pixRed);
            haarTransformDataBack(pixBlue);
            haarTransformDataBack(pixGreen);

            int[] newPixels = getImagePixelArray(pixBlue, pixGreen, pixRed);
            writeImageWithPixels(newImagePath, newPixels, w, h, fileType);

            return compCoef;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Подсчёт ненулевых элементов в двумерном массиве
     */
    private static int countNonZeros(double[][] data) {
        int size = data.length;
        int cnt = 0;
        for (int i = 0; i < size; ++i)
            for (int j = 0; j < size; ++j)
                if (data[i][j] >= 2 * Double.MIN_VALUE)
                    ++cnt;
        return cnt;
    }

    /**
     * Запись изображения с помощью массива пикселей
     * @param pathToImage путь для сохранения изображения
     * @param pixels массив пикселей
     * @param w ширина изображения
     * @param h высота изображения
     */
    private static void writeImageWithPixels(String pathToImage, int[] pixels, int w, int h, String fileType) throws IOException {
        BufferedImage pixelImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        pixelImage.setRGB(0, 0, w, h, pixels, 0, w);
        File imageFile = new File(pathToImage);
        ImageIO.write(pixelImage, fileType, imageFile);
    }

    /**
     * Формирование массива пикселей с помощью массивов пикселей красного, зеленого и синего цветов
     */
    private static int[] getImagePixelArray(double[][] bluePixels, double[][] greenPixels, double[][] redPixels) {
        int w = bluePixels[0].length, h = bluePixels.length;
        int[] pixels = new int[w * h];
        int[][] nPixRed = intToDoubleArray(redPixels);
        int[][] nPixBlue = intToDoubleArray(bluePixels);
        int[][] nPixGreen = intToDoubleArray(greenPixels);
        for (int i = 0; i < w; ++i)
            for(int j = 0; j < h; ++j)
                pixels[j*(w) + i] = (nPixBlue[i][j] & 0xff) + ((nPixGreen[i][j] & 0xff) << 8) + ((nPixRed[i][j] & 0xff) << 16);
        return pixels;
    }

    /**
     * Приведение двумерного массива типа double к типу int
     */
    private static int[][] intToDoubleArray(double[][] data) {
        int size = data.length;
        int[][] newData = new int[size][size];
        for (int i = 0; i < size; ++i)
            for (int j = 0; j < size; ++j)
                newData[i][j] = (int)(Math.round(data[i][j])) & 0xff;
        return newData;
    }

    /**
     * Зануление элементов двумерного массива, которые меньше eps
     */
    private static void zeroing(double[][] data, double eps) {
        int size = data.length;
        for (int i = 0; i < size; ++i)
            for (int j = 0; j < size; ++j)
                if (Math.abs(data[i][j]) < eps)
                    data[i][j] = 0;
    }

    /**
     * Преобразование Хаара для двумерного массива
     */
    private static void haarTransformData(double[][] data) {
        int size = data.length;

        while (size != 1) {
            int newSize = size / 2;
            double[][] dataCopy = new double[size][size];
            for (int i = 0; i < size; ++i)
                for (int j = 0; j < newSize; ++j) {
                    dataCopy[i][j] = (data[i][2 * j] + data[i][2 * j + 1]) / Math.sqrt(2);
                    dataCopy[i][newSize + j] = (data[i][2 * j] - data[i][2 * j + 1]) / Math.sqrt(2);
                }

            double[] temp = new double[size];
            for (int j = 0; j < size; ++j) {
                for (int i = 0; i < newSize; ++i) {
                    temp[i] = (dataCopy[2 * i][j] + dataCopy[2 * i + 1][j]) / Math.sqrt(2);
                    temp[newSize + i] = (dataCopy[2 * i][j] - dataCopy[2 * i + 1][j]) / Math.sqrt(2);
                }
                for (int i = 0; i < size; ++i)
                    dataCopy[i][j] = temp[i];
            }
            for (int i = 0; i < size; ++i) {
                System.arraycopy(dataCopy[i], 0, data[i], 0, size);
            }
            size = newSize;
        }
    }

    /**
     * Обратное преобразование Хаара для двумерного массива
     */
    private static void haarTransformDataBack(double[][] data) {
        int length = data.length;
        int size = 1;

        while (size != length) {
            int newSize = size * 2;
            double[][] dataCopy = new double[newSize][newSize];
            for (int j = 0; j < newSize; ++j)
                for (int i = 0; i < size; ++i) {
                    dataCopy[2*i][j] = (data[i][j] + data[size + i][j])/Math.sqrt(2);
                    dataCopy[2*i + 1][j] = (data[i][j] - data[size + i][j])/Math.sqrt(2);
                }

            double[] temp = new double[newSize];
            for (int i = 0; i < newSize; ++i) {
                for (int j = 0; j < size; ++j) {
                    temp[2 * j] = (dataCopy[i][j] + dataCopy[i][size + j]) / Math.sqrt(2);
                    temp[2 * j + 1] = (dataCopy[i][j] - dataCopy[i][size + j]) / Math.sqrt(2);
                }
                System.arraycopy(temp, 0, dataCopy[i], 0, newSize);
            }
            for (int i = 0; i < newSize; ++i) {
                System.arraycopy(dataCopy[i], 0, data[i], 0, newSize);
            }
            size = newSize;
        }

        for (int i = 0; i < length; ++i)
            for (int j = 0; j < length; ++j) {
                if (data[i][j] < 0)
                    data[i][j] = 0;
                if (data[i][j] > 255)
                    data[i][j] = 255;
            }
    }

    private static String getFileExtension(String fileName) {
        String extension = "";
        int i = fileName.lastIndexOf('.');
        int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));

        if (i > p) {
            extension = fileName.substring(i+1);
        }
        return extension;
    }
}
