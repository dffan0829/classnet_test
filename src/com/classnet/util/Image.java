package com.classnet.util;

import java.awt.image.BufferedImage;
import java.io.OutputStream;

public interface Image {

	public abstract BufferedImage getImage();

	public abstract void out(OutputStream outputstream) throws Exception;
}
