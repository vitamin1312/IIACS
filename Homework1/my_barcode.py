import aspose.barcode as barcode
from matplotlib import pyplot as plt
import os
import numpy as np
import cv2 as cv

def normalize(image):
    least = image.min()
    great = image.max()
    return (image - least) / (great - least) * 255


def down_cascade_removing(image, least=70, great=160):
    h, w = image.shape
    for y in range(1, h):
        for x in range(w):
            prev_value = image[y - 1, x] 
            cur_value = image[y, x]
            is_watermark = least < cur_value < great
            if is_watermark:
                image[y, x] = prev_value
    return normalize(image)

def up_cascade_removing(image, least=70, great=253):
    h, w = image.shape
    for y in range(h-2, 0, -1):
        for x in range(w):
            prev_value = image[y + 1, x] 
            cur_value = image[y, x]
            is_watermark = least < cur_value < great
            if is_watermark:
                image[y, x] = prev_value
    return normalize(image)

def left_cascade_removing(image, least=70, great=253):
    h, w = image.shape
    for y in range(h):
        for x in range(1, w):
            prev_value = image[y, x - 1] 
            cur_value = image[y, x]
            is_watermark = least < cur_value < great
            if is_watermark:
                image[y, x] = prev_value
    return normalize(image)

def right_cascade_removing(image, least=70, great=253):
    h, w = image.shape
    for y in range(h):
        for x in range(w - 2, 0, -1):
            prev_value = image[y, x + 1]
            cur_value = image[y, x]
            is_watermark = least < cur_value < great
            if is_watermark:
                image[y, x] = prev_value
    return normalize(image)


def delete_watermark(image):
    image = (down_cascade_removing(image)
             + up_cascade_removing(image)
             + left_cascade_removing(image)
             + right_cascade_removing(image)) / 4

    image = ((image > 70) * 255).astype(np.uint8)
    image = cv.morphologyEx(image, cv.MORPH_CLOSE, np.ones((3, 3), dtype=np.uint8))
    return image

number = '090006202003'

generator = barcode.generation.BarcodeGenerator(barcode.generation.EncodeTypes.EAN13, number)

generator.parameters.barcode.x_dimension.pixels = 6
generator.parameters.barcode.supplement.supplement_space.pixels = 20

filename = "ean-barcode.png"
generator.save(filename)
image = plt.imread(filename)
os.remove(filename)

image = image.mean(2)
image = (image * 255).astype(np.uint8)
image = delete_watermark(image)

plt.imshow(image, cmap='gray')
plt.show()